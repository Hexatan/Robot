/**
 * Created with IntelliJ IDEA.
 * User: gaetan
 * Date: 02/12/13
 * Time: 10:09
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import robot.*;

import java.util.ArrayList;
import java.util.List;

public class RoadBookUnitTest {
    @Test
    public void testRoadBook()
    {
        RoadBook rb = new RoadBook(new ArrayList<Instruction>());
        Assert.assertFalse(rb.hasInstruction());

        List<Instruction> l = new ArrayList<Instruction>();
        l.add(Instruction.FORWARD);

        rb = new RoadBook(l);
        Assert.assertTrue(rb.hasInstruction());
        Assert.assertEquals(Instruction.FORWARD, rb.next());
    }
}
