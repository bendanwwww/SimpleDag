package com.dag.driver.node;

/**
 * 执行节点 限流策略
 *
 * @author lsy
 * @date 2023/04/17
 */
public interface IRateLimit {

    /** 节点是否被限流 */
    boolean isRateLimit(NodeWrapper wrapper);
}
