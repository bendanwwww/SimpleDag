package com.dag.driver.driverMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * csr 算法构图
 *
 * @author lsy
 * @date 2023/04/20
 */
public class CSRDriverMap extends DriverMap {

    /** 行下标起始位置 rowStartArray[i] 表示 关联矩阵第 i 行 在 colAndValueArray 的起始位置 */
    private int[] rowStartArray;
    /** 列下标和具体数值数组 colAndValueArray[i][0] 表示 关联矩阵第 i 列, colAndValueArray[i][1] 表示 关联矩阵对应位置具体的值 */
    private int[][] colAndValueArray;

    @Override
    public void build() {
        // 初始化数组
        rowStartArray = new int[nodeList.size()];
        colAndValueArray = new int[edgeList.size() * 2][2];
        // 构图 遍历节点
        int colAndValueArrayIndex = 0;
        Map<SimpleNode, List<SimpleEdge>> nodeIntoEdgeMap = edgeList.stream().collect(Collectors.groupingBy(edge -> edge.getEndNode()));
        for (int i = 0 ; i < nodeList.size() ; i++) {
            SimpleNode node = nodeList.get(i);
            List<SimpleEdge> nextNodes = node.getNextNodes();
            // 记录节点在 colAndValueArray 中的起始位置
            rowStartArray[i] = colAndValueArrayIndex;
            // 出边
            for (SimpleEdge edge : nextNodes) {
                colAndValueArray[colAndValueArrayIndex][0] = edgeList.indexOf(edge);
                colAndValueArray[colAndValueArrayIndex][1] = edge.isMust() ? 1 : 2;
                colAndValueArrayIndex++;
            }
            // 入边
            if (nodeIntoEdgeMap.containsKey(node)) {
                List<SimpleEdge> nodeIntoEdge = nodeIntoEdgeMap.get(node);
                for (SimpleEdge edge : nodeIntoEdge) {
                    colAndValueArray[colAndValueArrayIndex][0] = edgeList.indexOf(edge);
                    colAndValueArray[colAndValueArrayIndex][1] = edge.isMust() ? -1 : -2;
                    colAndValueArrayIndex++;
                }
            }
            // 一个节点没有任何一条边, 抛出异常
            if (colAndValueArrayIndex == rowStartArray[i]) {
                throw new RuntimeException("node 不可达, node val: " + node.getVal());
            }
        }
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
                    System.out.println("node: " + node.getVal());
                    nodeState.add(node);
                }
                // 查询 node 的后继节点
                int nodeIndex = node.getNodeIndex();
                // 遍历边
                for (int i = rowStartArray[nodeIndex];
                     i < (nodeIndex < rowStartArray.length - 1 ? rowStartArray[nodeIndex + 1] : colAndValueArray.length) ; i++) {
                    if (colAndValueArray[i][1] == 1) {
                        SimpleEdge edge = edgeList.get(colAndValueArray[i][0]);
                        nextNodeList.add(edge.getEndNode());
                    }
                }
            }
            // 后继节点入栈
            for (SimpleNode node : nextNodeList) {
                nodeStack.push(node);
            }
        }
    }
}