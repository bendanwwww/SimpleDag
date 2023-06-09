package com.dag.driver.node;


/**
 * OP执行过程的监听器
 * Created by ytyht226 on 2022/6/21.
 */
/**
 * 执行节点监听器
 *
 * @author lsy
 * @date 2023/04/16
 */
public interface NodeOperatorListener {
    /** 执行函数 */
    void onEvent(NodeWrapper wrapper);

    public enum OperatorListenerEvent {
        /** 开始执行 */
        START,
        /** 执行成功 */
        SUCCESS,
        /** 执行失败 */
        ERROR,
        ;
    }
}