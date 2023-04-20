package com.dag.driver.node;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * DAG 执行引擎
 *
 * @author lsy
 * @date 2023/04/16
 */
public class DagDriver<P> {
    /** 业务全局上下文 */
    private P context;
    /** 执行器全局配置 */
    private DagDriverConfig driverConfig;
    /** 工作线程池 */
    private ExecutorService executor;
    /** wrapper 集合 */
    private Map<String, NodeWrapper<P>> wrappers = new HashMap<>();
    /** node 执行结果 key: node name, value: node result */
    private Map<String, NodeOperatorResult> nodeOperatorResultMap = new HashMap<>();

    public DagDriver(P context) {
        this.context = context;
        DagDriverConfig driverConfig = new DagDriverConfig()
                .nodeTimeout(200L);
        this.driverConfig = driverConfig;
    }

    public DagDriver(P context, DagDriverConfig driverConfig) {
        this.context = context;
        this.driverConfig = driverConfig;
    }

    public DagDriver(P context, DagDriverConfig driverConfig, ExecutorService executor) {
        this.context = context;
        this.driverConfig = driverConfig;
        this.executor = executor;
    }

    /**
     * 添加全局配置
     * @param driverConfig
     */
    public void setDriverConfig(DagDriverConfig driverConfig) {
        this.driverConfig = driverConfig;
    }

    /**
     * 添加节点
     * @param wrapper
     */
    public void addWrapper(NodeWrapper<P> wrapper) {
        wrappers.put(wrapper.getNodeName(), wrapper);
    }

    /**
     * 获取节点执行结果
     * @param nodeName
     * @return
     */
    public NodeOperatorResult getOperatorResult(String nodeName) {
        return nodeOperatorResultMap.get(nodeName);
    }

    /**
     * 获取全局上下文
     * @return
     */
    public P getContext() {
        return context;
    }

    /**
     * 遍历所有 wrappers 构图
     */
    public void buildDag() {

    }

    /**
     * 执行图
     */
    public void run() {

    }
}