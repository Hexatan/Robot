/**
 * Created with IntelliJ IDEA.
 * User: gaetan
 * Date: 02/12/13
 * Time: 10:07
 * To change this template use File | Settings | File Templates.
 */


import org.junit.Test;
import org.junit.Assert;
import robot.Coordinates;


public class CoordinatesTestUnit {

    @Test
    public void testEquals()
    {
        Coordinates c1 = new Coordinates(0,1);
        Coordinates c2 = new Coordinates(0,1);
        Coordinates c3 = new Coordinates(2,1);
        String o = "01";
        Assert.assertEquals(c1.getX(),0);
        Assert.assertEquals(c1.getY(),1);
        Assert.assertTrue(c1.equals(c2));
        Assert.assertFalse(c1.equals(c3));
        Assert.assertFalse(c1.equals(o));
    }
}
