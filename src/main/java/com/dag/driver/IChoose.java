package com.dag.driver;

import java.util.Set;

/**
 * 执行节点 定义
 *
 * @author lsy
 * @date 2023/04/16
 */
public interface IChoose {

    /** 节点后继节点选择函数 */
    Set<String> choose(NodeWrapper wrapper);
}
