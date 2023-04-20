package com.dag.driver.node;

/**
 * DAG 执行引擎 全局配置
 *
 * @author lsy
 * @date 2023/04/17
 */
public class DagDriverConfig {

    /** 节点超时 */
    private long nodeTimeout;
    /** 节点限流策略 */
    private IRateLimit iRateLimit;
    /** 节点降级策略 */
    private IDownGrade iDownGrade;
    /** 节点熔断策略 */
    private IBreak iBreak;

    public DagDriverConfig nodeTimeout(long nodeTimeout) {
        this.nodeTimeout = nodeTimeout;
        return this;
    }

    public DagDriverConfig iRateLimit(IRateLimit iRateLimit) {
        this.iRateLimit = iRateLimit;
        return this;
    }

    public DagDriverConfig iDownGrade(IDownGrade iDownGrade) {
        this.iDownGrade = iDownGrade;
        return this;
    }

    public DagDriverConfig iBreak(IBreak iBreak) {
        this.iBreak = iBreak;
        return this;
    }
}