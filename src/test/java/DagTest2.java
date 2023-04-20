import org.junit.Test;

import com.dag.business.ServingContext;
import com.dag.driver.node.DagDriver;
import com.dag.driver.node.NodeWrapper;

/**
 * dag test
 *
 * @author lsy
 * @date 2023/04/16
 */
public class DagTest2 {

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
                })
                .nextNode("2", "3", "4");
        NodeWrapper<ServingContext> node2 = new NodeWrapper<ServingContext>()
                .driver(dagDriver)
                .name("2")
                .operator((c) -> {
                    System.out.println(c.getClass().getName());
                    return null;
                })
                .nextNode("5", false);
        NodeWrapper<ServingContext> node3 = new NodeWrapper<ServingContext>()
                .driver(dagDriver)
                .name("3")
                .operator((c) -> {
                    System.out.println(c.getClass().getName());
                    return null;
                })
                .nextNode("5", false);
        NodeWrapper<ServingContext> node4 = new NodeWrapper<ServingContext>()
                .driver(dagDriver)
                .name("4")
                .operator((c) -> {
                    System.out.println(c.getClass().getName());
                    return null;
                })
                .nextNode("5", false);
        NodeWrapper<ServingContext> node5 = new NodeWrapper<ServingContext>()
                .driver(dagDriver)
                .name("5")
                .operator((c) -> {
                    System.out.println(c.getClass().getName());
                    return null;
                })
                .nextNode("6");
        NodeWrapper<ServingContext> node6 = new NodeWrapper<ServingContext>()
                .driver(dagDriver)
                .name("6")
                .operator((c) -> {
                    System.out.println(c.getClass().getName());
                    return null;
                });
        /** 构图 */
        dagDriver.buildDag();
        /** 执行 */
        dagDriver.run();


    }
}