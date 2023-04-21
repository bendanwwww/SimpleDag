package com.dag.driver.driverMap;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单节点定义
 *
 * @author lsy
 * @date 2023/04/20
 */
public class SimpleNode {

    /** node 行号 */
    private int nodeIndex;
    /** 节点值 */
    private String val;
    /** 后继节点列表 */
    private List<SimpleNode> nextNodes;
    /** 后继弱依赖节点列表 */
    private List<SimpleNode> nextNotMustNodes;

    public SimpleNode(String val) {
        this.val = val;
        this.nextNodes = new ArrayList<>();
        this.nextNotMustNodes = new ArrayList<>();
    }

    public String getVal() {
        return val;
    }

    public List<SimpleNode> getNextNodes() {
        return nextNodes;
    }

    public List<SimpleNode> getNextNotMustNodes() {
        return nextNotMustNodes;
    }

    public boolean isMust(SimpleNode node) {
        return nextNotMustNodes.contains(node);
    }

    public int getNodeIndex() {
        return nodeIndex;
    }

    public void setNodeIndex(int nodeIndex) {
        this.nodeIndex = nodeIndex;
    }

    public void next(SimpleNode node) {
        nextNodes.add(node);
    }

    public void next(SimpleNode node, boolean isMust) {
        nextNodes.add(node);
        if (!isMust) {
            nextNotMustNodes.add(node);
        }
    }

    @Override
    public String toString() {
        return "SimpleNode {" +
                "val='" + val + '\'' +
                '}';
    }
}