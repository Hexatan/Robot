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
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import robot.*;

import java.util.Random;

public class LandSensorUnitTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void testLandSensor()
    {
        Random rand = Mockito.mock(Random.class);
        LandSensor ls = new LandSensor(rand);
        Mockito.when(rand.nextInt(Land.CountLand())).thenReturn(0);
        double rep = 0;
        try {
            rep = ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(0,1));
        } catch (LandSensorDefaillance landSensorDefaillance) {
            landSensorDefaillance.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InaccessibleCoordinate inaccessibleCoordinate) {
            inaccessibleCoordinate.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Assert.assertEquals(1, rep, 0);
    }

    @Test
    public void testLandSensorUnknowTerrain1() throws LandSensorDefaillance {
        exception.expect(LandSensorDefaillance.class);
        Random rand = Mockito.mock(Random.class);
        LandSensor ls = new LandSensor(rand);
        Mockito.when(rand.nextInt(Land.CountLand())).thenReturn(42);
        double rep = 0;
        try {
            rep = ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(0,1));
        }catch (InaccessibleCoordinate inaccessibleCoordinate) {
            inaccessibleCoordinate.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Assert.assertEquals(1, rep, 0);
    }

    @Test
    public void testLandSensorUnknowTerrain2() throws LandSensorDefaillance {
        exception.expect(LandSensorDefaillance.class);
        Random rand = Mockito.mock(Random.class);
        LandSensor ls = new LandSensor(rand);
        Mockito.when(rand.nextInt(Land.CountLand())).thenReturn(0,42);
        double rep = 0;
        try {
            rep = ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(0,1));
        }catch (InaccessibleCoordinate inaccessibleCoordinate) {
            inaccessibleCoordinate.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Assert.assertEquals(1, rep, 0);
    }

    @Test
    public void testLandSensorInaccessible() throws InaccessibleCoordinate {
        exception.expect(InaccessibleCoordinate.class);
        Random rand = Mockito.mock(Random.class);
        LandSensor ls = new LandSensor(rand);
        Mockito.when(rand.nextInt(Land.CountLand())).thenReturn(4);
        double rep = 0;
        try {
            rep = ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(0,1));
        } catch (LandSensorDefaillance landSensorDefaillance) {
            landSensorDefaillance.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Assert.assertEquals(1, rep, 0);
    }
}
