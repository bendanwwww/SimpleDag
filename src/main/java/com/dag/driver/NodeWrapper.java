package com.dag.driver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 执行节点包装器
 *
 * @author lsy
 * @date 2023/04/16
 */
public class NodeWrapper<P> {
    /** 节点执行引擎 */
    private DagDriver<P> dagDriver;
    /** 执行节点名称 */
    private String nodeName;
    /** 执行节点 */
    private NodeOperator<P, ?> nodeOperator;
    /** 执行节点 后继节点 */
    private Set<String> nextNodes = new HashSet<>();
    /** 执行节点 前继节点 */
    private Set<String> frontNodes = new HashSet<>();
    /** 节点执行条件 */
    private ICondition nodeCondition;
    /** 节点后继节点选择函数 */
    private IChoose nodeChoose;
    /** 节点执行监听器 */
    private NodeOperatorListener operatorListener;

    public NodeWrapper driver(DagDriver<P> dagDriver) {
        this.dagDriver = dagDriver;
        dagDriver.addWrapper(this);
        return this;
    }

    public NodeWrapper name(String name) {
        this.nodeName = name;
        return this;
    }

    public NodeWrapper operator(NodeOperator<P, ?> nodeOperator) {
        this.nodeOperator = nodeOperator;
        return this;
    }

    public NodeWrapper nextNode(String... nodeWrappers) {
        nextNodes.addAll(Arrays.stream(nodeWrappers).collect(Collectors.toSet()));
        return this;
    }

    public NodeWrapper frontNode(String... nodeWrappers) {
        frontNodes.addAll(Arrays.stream(nodeWrappers).collect(Collectors.toSet()));
        return this;
    }

    public NodeWrapper nodeCondition(ICondition nodeCondition) {
        this.nodeCondition = nodeCondition;
        return this;
    }

    public NodeWrapper nodeChoose(IChoose nodeChoose) {
        this.nodeChoose = nodeChoose;
        return this;
    }

    public NodeWrapper operatorListener(NodeOperatorListener operatorListener) {
        this.operatorListener = operatorListener;
        return this;
    }

    public String getNodeName() {
        return nodeName;
    }
}