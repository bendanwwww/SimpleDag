import java.util.HashSet;
import java.util.Random;

import org.junit.Test;

import com.dag.business.ServingContext;
import com.dag.driver.node.DagDriver;
import com.dag.driver.node.DagDriverConfig;
import com.dag.driver.node.NodeOperatorListener;
import com.dag.driver.node.NodeWrapper;

/**
 * dag test
 *
 * @author lsy
 * @date 2023/04/16
 */
public class DagTest {

    @Test
    public void test() {

        /** 初始化全局上下文 和 dag 引擎 */
        ServingContext servingContext = new ServingContext();
        DagDriverConfig driverConfig = new DagDriverConfig()
                .nodeTimeout(200L)
                .iRateLimit((w) -> true)
                .iBreak((w) -> true)
                .iDownGrade((w) -> true);
        DagDriver<ServingContext> dagDriver = new DagDriver<>(servingContext, driverConfig);
        /** 定义节点 */
        NodeWrapper<ServingContext> node1 = new NodeWrapper<ServingContext>()
                .driver(dagDriver)
                .name("1")
                .operator((c) -> {
                    System.out.println(c.getClass().getName());
                    return null;
                });
        NodeWrapper<ServingContext> node2 = new NodeWrapper<ServingContext>()
                .driver(dagDriver)
                .name("2")
                .operator((c) -> {
                    System.out.println(c.getClass().getName());
                    return null;
                })
                .frontNode(node1.getNodeName())
                .nextNode("3", "4")
                .nodeTimeout(500L)
                .nodeChoose((w) -> (boolean) w.getOperatorResult().getResult() ? new HashSet<>() {{add("3");}} : new HashSet<>() {{add("4");}});
        NodeWrapper<ServingContext> node3 = new NodeWrapper<ServingContext>()
                .driver(dagDriver)
                .name("3")
                .operator((c) -> {
                    System.out.println(c.getClass().getName());
                    return null;
                })
                .iBreak((w) -> true)
                .nodeCondition((w) -> new Random().nextInt(10) > 5);
        NodeWrapper<ServingContext> node4 = new NodeWrapper<ServingContext>()
                .driver(dagDriver)
                .name("4")
                .operator((c) -> {
                    System.out.println(c.getClass().getName());
                    return null;
                })
                .iRateLimit((w) -> true)
                .iDownGrade((w) -> true)
                .operatorListener((w) -> System.out.println("node 4 run"), NodeOperatorListener.OperatorListenerEvent.START);
        /** 构图 */
        dagDriver.buildDag();
        /** 执行 */
        dagDriver.run();


    }
}