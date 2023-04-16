package com.dag.driver;

/**
 * 执行节点 定义
 *
 * @author lsy
 * @date 2023/04/16
 */
public interface NodeOperator<P, R> {
    /** 节点执行逻辑 */
    R execute(P param);
    /** 节点返回默认值 */
    default R defaultValue() { return null; }
    /** 节点执行前 执行逻辑 */
    default void before(P param) {}
    /** 节点执行后 执行逻辑 */
    default void after(P param, NodeOperatorResult<R> result) {}
    /** 节点执行异常 执行逻辑 */
    default void error(P param, NodeOperatorResult<R> result) {}
}