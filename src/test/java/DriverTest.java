import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dag.driver.driverMap.AdjacencyListDriverMap;
import com.dag.driver.driverMap.AdjacencyMatrixCSRDriverMap;
import com.dag.driver.driverMap.DriverMap;
import com.dag.driver.driverMap.IncidenceMatrixCSRDriverMap;
import com.dag.driver.driverMap.SimpleNode;

/**
 * driver test
 *
 * @author lsy
 * @date 2023/04/20
 */
public class DriverTest {

    @Test
    public void test() {
        // 设置节点
        SimpleNode a = new SimpleNode("a");
        SimpleNode b = new SimpleNode("b");
        SimpleNode c = new SimpleNode("c");
        SimpleNode d = new SimpleNode("d");
        SimpleNode e = new SimpleNode("e");
        SimpleNode f = new SimpleNode("f");
        SimpleNode g = new SimpleNode("g");
        SimpleNode h = new SimpleNode("h");
        SimpleNode i = new SimpleNode("i");
        SimpleNode j = new SimpleNode("j");
        SimpleNode k = new SimpleNode("k");
        a.next(d);
        a.next(e);
        b.next(e);
        c.next(e);
        d.next(f);
        e.next(f);
        f.next(g);
        f.next(h);
        f.next(i);
        h.next(j);
        i.next(j);
        j.next(k);

        // 邻接矩阵 - csr 算法
        System.out.print("邻接矩阵 - csr 算法: ");
        AdjacencyMatrixCSRDriverMap adjacencyMatrixCSRDriverMap = new AdjacencyMatrixCSRDriverMap();
        adjacencyMatrixCSRDriverMap.addNode(a, b, c, d, e, f, g, h, i, j, k);
        adjacencyMatrixCSRDriverMap.head(a, b, c);
        // 构图
        adjacencyMatrixCSRDriverMap.build();
        // 执行
        double runTime = runAndStatisticsTimeSum(adjacencyMatrixCSRDriverMap, 1000000);
        System.out.println(" " + runTime + " ms");

        // 关联矩阵 - csr 算法
        System.out.print("关联矩阵 - csr 算法: ");

        IncidenceMatrixCSRDriverMap incidenceMatrixCsrDriverMap = new IncidenceMatrixCSRDriverMap();
        incidenceMatrixCsrDriverMap.addNode(a, b, c, d, e, f, g, h, i, j, k);
        incidenceMatrixCsrDriverMap.head(a, b, c);
        // 构图
        incidenceMatrixCsrDriverMap.build();
        // 执行
        runTime = runAndStatisticsTimeSum(incidenceMatrixCsrDriverMap, 1000000);
        System.out.println(" " + runTime + " ms");

        // 邻接表
        System.out.print("邻接表: ");
        AdjacencyListDriverMap adjacencyListDriverMap = new AdjacencyListDriverMap();
        adjacencyListDriverMap.addNode(a, b, c, d, e, f, g, h, i, j, k);
        adjacencyListDriverMap.head(a, b, c);
        // 构图
        adjacencyListDriverMap.build();
        // 执行
        runTime = runAndStatisticsTimeSum(adjacencyListDriverMap, 1000000);
        System.out.println(" " + runTime + " ms");
    }

    private double runAndStatisticsTime(DriverMap driverMap, int runNumber, double quantile) {
        List<Long> runTimeList = new ArrayList<>();
        for (int n = 0 ; n < runNumber ; n++) {
            long startTime = System.currentTimeMillis();
            driverMap.run();
            runTimeList.add(System.currentTimeMillis() - startTime);
        }
        runTimeList.sort((t1, t2) -> (int) (t1 - t2));
        int removeSize = (int) (runNumber * (1 - quantile / 100) / 2);
        return runTimeList.subList(removeSize, runTimeList.size() - removeSize).stream().mapToLong(t -> t).average().getAsDouble();
    }

    private double runAndStatisticsTimeSum(DriverMap driverMap, int runNumber) {
        long startTime = System.currentTimeMillis();
        for (int n = 0 ; n < runNumber ; n++) {
            driverMap.run();
        }
        return System.currentTimeMillis() - startTime;
    }
}