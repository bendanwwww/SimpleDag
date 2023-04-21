package com.dag.driver.driverMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 执行图定义
 *
 * @author lsy
 * @date 2023/04/20
 */
public abstract class DriverMap {

    /** 起始节点 */
    protected List<SimpleNode> headList = new ArrayList<>();
    /** 节点列表 */
    protected List<SimpleNode> nodeList = new ArrayList<>();

    public void head(SimpleNode... node) {
        Arrays.stream(node).forEach(n -> {
            headList.add(n);
        });
    }

    public void addNode(SimpleNode... node) {
        Arrays.stream(node).forEach(n -> {
            n.setNodeIndex(nodeList.size());
            nodeList.add(n);
        });
    }

    abstract void build();

    abstract void run();
}