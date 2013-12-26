/**
 * Created with IntelliJ IDEA.
 * User: gaetan
 * Date: 02/12/13
 * Time: 10:08
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import robot.Land;
import robot.TerrainNonRepertorieException;

public class LandUnitTest {

    @Test
    public void testCountLand()
    {
        Assert.assertEquals(Land.CountLand(), 5);
    }

    @Test
    public void testGetLandByOrdinal()
    {
        Land land = null;
        try {
            land = Land.getLandByOrdinal(0);
        } catch (TerrainNonRepertorieException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Assert.assertEquals(land.ordinal(), 0);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetLandByOrdinalException() throws TerrainNonRepertorieException {
        exception.expect(TerrainNonRepertorieException.class);
        Land land = Land.getLandByOrdinal(146880);
    }
}
