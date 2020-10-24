package com.github.alonwang.core.protocol;

import com.github.alonwang.logic.hello.message.HelloRequest;
import com.github.alonwang.logic.protobuf.Hello.HelloMessage;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * protobuf {@link Message}有效载荷的编解码
 *
 * @author alonwang
 * @date 2020/10/22 3:04 下午
 * @detail
 */
@Slf4j
public class ProtobufCodecDelegate {
    /**
     * 消息对应的有效荷载字段,eg {@link HelloRequest}的有效荷载字段为{@link HelloRequest#req}
     */
    private static final Map<Class<? extends ProtobufMessage>, Field> messageToField = new ConcurrentHashMap<>();
    /**
     * Protobuf生成类对应的parseFrom(ByteString)方法,eg {@link HelloMessage}对应{@link HelloMessage#parseFrom(ByteString)}
     */
    private static final Map<Class<? extends AbstractMessage>, Method> protoToParseFromMethod =
            new ConcurrentHashMap<>();
    /**
     * AbstractMessageLite#toByteString()的反射方法
     */
    private static final Method toByteStringMethod;

    static {
        try {
            toByteStringMethod = AbstractMessageLite.class.getMethod("toByteString", (Class<?>[]) null);
            toByteStringMethod.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException("reflect AbstractMessageLite#toByteString() error", e);
        }
    }

    /**
     * 将{@link Message#getPayload()}转换并设置到Protobuf的java表示
     *
     * @param <T>
     * @param message
     */
    public static <T extends ProtobufMessage> void decode(T message) {
        ByteString payLoadBytes = message.getPayload();
        if (payLoadBytes == null) {
            return;
        }


        Field payLoadField = messageToField.get(message.getClass());
        if (payLoadField == null) {
            return;
        }

        Method parseFromMethod = protoToParseFromMethod.get(payLoadField.getType());
        if (parseFromMethod == null) {
            return;
        }

        try {
            Object payloadObject = parseFromMethod.invoke(message, payLoadBytes);
            payLoadField.set(message, payloadObject);
        } catch (Exception e) {
            log.error(String.format("%s decode error", message.getClass()), e);
        }
    }

    /**
     * Protobuf的java表示转换并设置为{@link Message#getPayload()}
     *
     * @param message
     * @param <T>
     */
    public static <T extends ProtobufMessage> void encode(T message) {
        Field payLoadField = messageToField.get(message.getClass());
        if (payLoadField == null) {
            return;
        }

        Method parseFromMethod = protoToParseFromMethod.get(payLoadField.getType());
        if (parseFromMethod == null) {
            return;
        }


        Object payloadObject = null;
        Object payloadBytes = null;
        try {
            payloadObject = payLoadField.get(message);
            if (payloadObject != null) {
                payloadBytes = toByteStringMethod.invoke(payloadObject, (Object[]) null);
                message.setPayload((ByteString) payloadBytes);
            }
        } catch (Exception e) {
            log.error(String.format("%s encode error,field name(%s), value(%s),byteString(%s)", message.getClass(),
                    payLoadField.getName(), payloadObject, payloadBytes), e);
        }

    }

    /**
     * 解析{@link Message},记录messageToField,parseFromMethodMap
     *
     * @param messageClazz
     */
    @SuppressWarnings("unchecked")
    public static void register(Class<? extends Message<?>> messageClazz) {
        if (!ProtobufMessage.class.isAssignableFrom(messageClazz)) {
            log.debug("codec register ignore,{} is not sub class of ProtobufMessage", messageClazz);
            return;
        }

        Field bodyField =
                Arrays.stream(messageClazz.getDeclaredFields()).filter(f -> f.isAnnotationPresent(PayLoad.class)).findAny().orElse(null);
        if (bodyField == null) {
            List<Field> possibleFields =
                    Arrays.stream(messageClazz.getDeclaredFields()).filter(f -> AbstractMessage.class.isAssignableFrom(f.getType())).collect(Collectors.toList());

            if (possibleFields.size() == 1) {
                bodyField = possibleFields.get(0);
            } else if (possibleFields.size() > 1) {
                log.error("can't infer {} codec bodyField,if there is multiple field of type AbstractMessage,please " +
                        "use @BodyField to indicate", messageClazz);
            }
        }
        if (bodyField == null) {
            return;
        }

        Method parseFromMethod = null;
        try {
            parseFromMethod = bodyField.getType().getDeclaredMethod("parseFrom", ByteString.class);
        } catch (Exception e) {
            log.error(String.format("%s parse parseFrom(ByteString) method  error", messageClazz), e);
            return;
        }

        bodyField.setAccessible(true);
        messageToField.put((Class<? extends ProtobufMessage>) messageClazz, bodyField);

        parseFromMethod.setAccessible(true);
        protoToParseFromMethod.put((Class<? extends AbstractMessage>) bodyField.getType(), parseFromMethod);
        log.info("{} codec success register,field({} {})", messageClazz, bodyField.getType(), bodyField.getName());
    }

    public static Map<Class<? extends ProtobufMessage>, Field> getMessageToField() {
        return messageToField;
    }

    public static Map<Class<? extends AbstractMessage>, Method> getProtoToParseFromMethod() {
        return protoToParseFromMethod;
    }
}
