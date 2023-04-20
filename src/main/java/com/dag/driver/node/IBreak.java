package com.dag.driver.node;

/**
 * 执行节点 熔断策略
 *
 * @author lsy
 * @date 2023/04/17
 */
public interface IBreak {

    /** 节点是否被熔断 */
    boolean isBreak(NodeWrapper wrapper);
}
