import org.junit.Test;

import com.dag.driver.driverMap.CSRDriverMap;
import com.dag.driver.driverMap.SimpleNode;

/**
 * CSR driver test
 *
 * @author lsy
 * @date 2023/04/16
 */
public class CSRDriverTest {

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
        // 初始化图
        CSRDriverMap csrDriverMap = new CSRDriverMap();
        csrDriverMap.addNode(a, b, c, d, e, f, g, h, i, j, k);
        csrDriverMap.head(a, b, c);
        // 构图
        csrDriverMap.build();
        // 执行
        csrDriverMap.run();
    }
}