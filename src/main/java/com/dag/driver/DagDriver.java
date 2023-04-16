package com.dag.driver;

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
    /** 工作线程池 */
    private ExecutorService executor;
    /** wrapper 集合 */
    private Map<String, NodeWrapper<P>> wrappers = new HashMap<>();

    public DagDriver(P context) {
        this.context = context;
    }

    public DagDriver(P context, ExecutorService executor) {
        this.context = context;
        this.executor = executor;
    }

    /**
     * 添加节点
     * @param wrapper
     */
    public void addWrapper(NodeWrapper<P> wrapper) {
        wrappers.put(wrapper.getNodeName(), wrapper);
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