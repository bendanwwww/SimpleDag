package com.dag.driver.node;

/**
 * 节点边 包装器
 *
 * @author lsy
 * @date 2023/04/18
 */
public class EdgeWrapper<P> {
    /** 边的起始节点 */
    private NodeWrapper<P> startNode;
    /** 边的结束节点 */
    private NodeWrapper<P> endNode;
    /** 是否为强依赖边 */
    private boolean mustEdge;
    /** 存在标识 */
    private boolean exist;
}