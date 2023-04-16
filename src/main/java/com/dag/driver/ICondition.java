package com.dag.driver;

/**
 * 执行节点 定义
 *
 * @author lsy
 * @date 2023/04/16
 */
public interface ICondition {

    /** 判断是否可以执行当前节点 */
    boolean canExecute(NodeWrapper wrapper);

}
