package com.dag.driver.node;

/**
 * 执行节点 降级策略
 *
 * @author lsy
 * @date 2023/04/17
 */
public interface IDownGrade {

    /** 节点是否被降级 */
    boolean isDownGrade(NodeWrapper wrapper);
}
