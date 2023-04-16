import java.util.HashSet;
import java.util.Random;

import org.junit.Test;

import com.dag.business.ServingContext;
import com.dag.driver.DagDriver;
import com.dag.driver.NodeWrapper;

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
        DagDriver<ServingContext> dagDriver = new DagDriver<>(servingContext);
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
                .nodeChoose((w) -> new Random().nextInt(10) > 5 ? new HashSet<>() {{add("3");}} : new HashSet<>() {{add("4");}});
        NodeWrapper<ServingContext> node3 = new NodeWrapper<ServingContext>()
                .driver(dagDriver)
                .name("3")
                .operator((c) -> {
                    System.out.println(c.getClass().getName());
                    return null;
                })
                .nodeCondition((w) -> new Random().nextInt(10) > 5)
                .operatorListener((w) -> System.out.println("node 3 run"));
        NodeWrapper<ServingContext> node4 = new NodeWrapper<ServingContext>()
                .driver(dagDriver)
                .name("4")
                .operator((c) -> {
                    System.out.println(c.getClass().getName());
                    return null;
                })
                .operatorListener((w) -> System.out.println("node 4 run"));
        /** 构图 */
        dagDriver.buildDag();
        /** 执行 */
        dagDriver.run();


    }
}