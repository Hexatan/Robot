/**
 * Created with IntelliJ IDEA.
 * User: gaetan
 * Date: 02/12/13
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Test;
import org.junit.Assert;
import robot.Battery;
import robot.InsufficientChargeException;

public class BatteryTestUnit {

    @Test
    public void testGetCharge()
    {
        Battery b = new Battery();
        Assert.assertEquals(b.getChargeLevel(), 100, 0);
    }

    @Test
    public void testCharge()
    {
        Battery b = new Battery();
        float chargeLevel = b.getChargeLevel();
        b.charge();
        Assert.assertTrue(b.getChargeLevel() > chargeLevel);
    }

    @Test
    public void testUse()
    {
        Battery b = new Battery();
        float chargeLevel = b.getChargeLevel();
        try {
            b.use(50);
        } catch (InsufficientChargeException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(b.getChargeLevel() < chargeLevel);
        try {
            b.use(55);
        } catch (InsufficientChargeException e) {
            Assert.assertTrue(b.getChargeLevel() < 55);
        }
    }

    @Test
    public void testTimeToSufficientCharge()
    {
        Battery b = new Battery();
        Assert.assertEquals(b.timeToSufficientCharge(100), 0);
        Assert.assertEquals(b.timeToSufficientCharge(150), 5000);
    }

    @Test
    public void testCanDeliver()
    {
        Battery b = new Battery();
        Assert.assertTrue(b.canDeliver(10));
        Assert.assertFalse(b.canDeliver(1000000));
    }

    @Test
    public void testSetUp()
    {
        Battery b = new Battery();
        // A faire si un jour je comprend comment ça marche.
    }
}
