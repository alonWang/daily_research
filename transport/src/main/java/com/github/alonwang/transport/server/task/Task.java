package com.github.alonwang.transport.server.task;

/**
 * 任务
 *
 * @author alonwang
 * @date 2020/7/27 16:17
 * @detail 任务是有归属实体的, 例如用户A登录 ==> 登录是一个任务,用户A是一个实体 ==> 登录归属于用户A ==> 任务是有归属实体的.
 * 执行任务时,就需要将对应的实体T传递进去.
 * <p>
 * 主体需要有串行执行任务的能力.实体会有很多任务,这些任务添加后,需要按序执行,例如用户依次执行了注册,登录,退出这三个操作,也就有 注册,登录,退出三个任务依次添加,它们也需要串行执行.
 * 那么主体就需要有存储任务和串行执行任务的能力. 这里通过泛型限制 'T extends TaskExecutor<?>' 确保主体拥有此能力
 */
@FunctionalInterface
public interface Task<T extends TaskExecutor<?>> {
    /**
     * 执行任务
     *
     * @param t 主体对象
     */
    void execute(T t);
}
