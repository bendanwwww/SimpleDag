package com.dag.driver.driverMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 邻接表 算法构图
 *
 * @author lsy
 * @date 2023/04/21
 */
public class AdjacencyListDriverMap extends DriverMap {

    @Override
    public void build() {
        // 无需额外的构图 只使用 node next 指针
    }

    @Override
    public void run() {
        // 图的广度优先遍历 暂未考虑入度和裁剪边
        // 遍历栈
        Stack<SimpleNode> nodeStack = new Stack<>();
        // 访问标识
        Set<SimpleNode> nodeState = new HashSet<>();
        // 从图的起始节点开始遍历
        for (SimpleNode node : headList) {
            nodeStack.push(node);
        }
        while(!nodeStack.isEmpty()) {
            List<SimpleNode> nextNodeList = new ArrayList<>();
            while(!nodeStack.isEmpty()) {
                // 出栈
                SimpleNode node = nodeStack.pop();
                if (!nodeState.contains(node)) {
//                    System.out.print(node.getVal() + " ");
                    nodeState.add(node);
                }
                // 查询 node 的后继节点
                nextNodeList.addAll(node.getNextNodes());
            }
            // 后继节点入栈
            for (SimpleNode node : nextNodeList) {
                nodeStack.push(node);
            }
        }
    }
}