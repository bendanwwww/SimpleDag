package com.dag.driver.driverMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

/**
 * 使用 csr 压缩算法的邻接矩阵构图
 *
 * @author lsy
 * @date 2023/04/20
 */
public class AdjacencyMatrixCSRDriverMap extends DriverMap {

    /** 行下标起始位置 rowStartArray[i] 表示 邻接矩阵第 i 行 在 colAndValueArray 的起始位置 */
    private int[] rowStartArray;
    /** 列下标和具体数值数组 colAndValueArray[i][0] 表示 邻接矩阵第 i 列, colAndValueArray[i][1] 表示 邻接矩阵对应位置具体的值 */
    private int[][] colAndValueArray;

    @Override
    public void build() {
        // 初始化数组
        int edgeNumber = nodeList.stream().mapToInt(n -> n.getNextNodes().size()).sum();
        rowStartArray = new int[nodeList.size()];
        colAndValueArray = new int[edgeNumber * 2][2];
        // 构图
        // 临时记录一下入边
        Map<Integer, List<Integer>> intoEdgeMap = new HashMap<>();
        for (SimpleNode node : nodeList) {
            new ArrayList<>();
            for (SimpleNode nextNode : node.getNextNodes()) {
                List<Integer> intoEdges = intoEdgeMap.get(nextNode.getNodeIndex());
                if (Objects.isNull(intoEdges)) {
                    intoEdges = new ArrayList<>();
                }
                intoEdges.add(node.getNodeIndex());
                intoEdgeMap.put(nextNode.getNodeIndex(), intoEdges);
            }
        }
        // 记录节点
        int colAndValueArrayIndex = 0;
        for (SimpleNode node : nodeList) {
            int nodeIndex = node.getNodeIndex();
            List<SimpleNode> nextNodes = node.getNextNodes();
            // 记录节点在 colAndValueArray 中的起始位置
            rowStartArray[nodeIndex] = colAndValueArrayIndex;
            // 出边
            for (SimpleNode nextNode : nextNodes) {
                colAndValueArray[colAndValueArrayIndex][0] = nextNode.getNodeIndex();
                colAndValueArray[colAndValueArrayIndex][1] = !node.isMust(nextNode) ? 1 : 2;
                colAndValueArrayIndex++;
            }
            // 入边
            List<Integer> intoEdgeIndexList = intoEdgeMap.get(nodeIndex);
            if (Objects.nonNull(intoEdgeIndexList)) {
                for (int intoEdgeIndex : intoEdgeIndexList) {
                    colAndValueArray[colAndValueArrayIndex][0] = intoEdgeIndex;
                    colAndValueArray[colAndValueArrayIndex][1] = !nodeList.get(intoEdgeIndex).isMust(node) ? -1 : -2;
                    colAndValueArrayIndex++;
                }
            }
            // 一个节点没有任何一条边, 抛出异常
            if (colAndValueArrayIndex == rowStartArray[nodeIndex]) {
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
//                    System.out.print(node.getVal() + " ");
                    nodeState.add(node);
                }
                // 查询 node 的后继节点
                int nodeIndex = node.getNodeIndex();
                // 遍历节点
                for (int i = rowStartArray[nodeIndex];
                     i < (nodeIndex < rowStartArray.length - 1 ? rowStartArray[nodeIndex + 1] : colAndValueArray.length) ; i++) {
                    if (colAndValueArray[i][1] == 1 || colAndValueArray[i][1] == 2) {
                        nextNodeList.add(nodeList.get(colAndValueArray[i][0]));
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