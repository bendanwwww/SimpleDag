package com.dag.driver.driverMap;

/**
 * 简单边定义
 *
 * @author lsy
 * @date 2023/04/20
 */
public class SimpleEdge {

    /** 起始节点 */
    private SimpleNode startNode;
    /** 结束节点 */
    private SimpleNode endNode;
    /** 是否为强依赖边 */
    private boolean isMust = true;

    public SimpleEdge(SimpleNode startNode, SimpleNode endNode) {
        this(startNode, endNode, true);
    }

    public SimpleEdge(SimpleNode startNode, SimpleNode endNode, boolean isMust) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.isMust = isMust;
    }

    public SimpleNode getStartNode() {
        return startNode;
    }

    public SimpleNode getEndNode() {
        return endNode;
    }

    public boolean isMust() {
        return isMust;
    }
}