package com.dag.driver;

/**
 * 节点执行结果 封装类
 *
 * @author lsy
 * @date 2023/04/16
 */
public class NodeOperatorResult<R> {

    /** 节点执行结果 */
    private R result;
    /** 执行结果状态 */
    private ResultState resultState;
    /** 异常信息 */
    private Throwable ex;

    public enum ResultState {
        /** 成功 */
        SUCCESS,
        /** 超时 */
        TIMEOUT,
        /** 限流 */
        RATE_LIMIT,
        /** 降级 */
        DOWNGRADE,
        /** 熔断 */
        BREAK,
        /** 默认异常 */
        DEFAULT_EXCEPTION,
        ;
    }
}