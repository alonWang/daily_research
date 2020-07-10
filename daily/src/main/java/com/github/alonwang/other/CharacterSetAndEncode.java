package com.github.alonwang.other;

import cn.hutool.core.io.FileUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author alonwang
 * @date 2020/5/9 12:07
 * @detail
 */
public class CharacterSetAndEncode {
    public static void main(String[] args) throws IOException {
        //以GBK编码写入内容
        File file = FileUtil.newFile("test1.properties");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GBK"));
        String s = "abc=中国";
        writer.write(s);
        writer.flush();
        writer.close();
        //以 UTF-8编码读取内容
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        String str = bufferedReader.readLine();
        System.out.println(str);
        bufferedReader.close();

        //以GBK编码写入内容
        file = FileUtil.newFile("test2.properties");
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GBK"));
        //GB2312字符集的字符"中国"转换为unicode字符集在java中的表示是 "\u4e2d\u56fd"
        s = "abc=\\u4e2d\\u56fd";
        writer.write(s);
        writer.flush();
        writer.close();
        //以 UTF-8格式读取内容
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        str = bufferedReader.readLine();
        System.out.println(str);
        bufferedReader.close();

        //以UTF-8编码写入内容
        file = FileUtil.newFile("test3.properties");
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        s = "abc=中国";
        writer.write(s);
        writer.flush();
        writer.close();
        //以 UTF-8编码读取内容
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        str = bufferedReader.readLine();
        System.out.println(str);
        bufferedReader.close();

        //以UTF-8编码写入内容
        file = FileUtil.newFile("test4.properties");
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        //GB2312字符集的字符"中国"转换为unicode字符集在java中的表示是 "\u4e2d\u56fd"
        s = "abc=\\u4e2d\\u56fd";
        writer.write(s);
        writer.flush();
        writer.close();
        //以 UTF-8格式读取内容
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        str = bufferedReader.readLine();
        System.out.println(str);
        bufferedReader.close();


    }
}
