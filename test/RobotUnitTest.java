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
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import robot.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RobotUnitTest{

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetXPosition()
    {
        Robot r = new Robot();
        r.land(new Coordinates(54,0), new LandSensor(new Random()));
        int rep = 0;
        try {
            rep = r.getXposition();
        } catch (UnlandedRobotException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Assert.assertEquals(54, rep);
    }

    @Test
    public void testGetXPositionException() throws UnlandedRobotException {
        exception.expect(UnlandedRobotException.class);
        Robot r = new Robot();
        r.getXposition();
    }

    @Test
    public void testGetYPosition()
    {
        Robot r = new Robot();
        r.land(new Coordinates(0,54), new LandSensor(new Random()));
        int rep = 0;
        try {
            rep = r.getYposition();
        } catch (UnlandedRobotException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Assert.assertEquals(54, rep);
    }

    @Test
    public void testGetYPositionException() throws UnlandedRobotException {
        exception.expect(UnlandedRobotException.class);
        Robot r = new Robot();
        r.getYposition();
    }

    @Test
    public void testGetDirection()
    {
        Robot r = new Robot();
        r.land(new Coordinates(0,54), new LandSensor(new Random()));
        Direction rep = null;
        try {
            rep = r.getDirection();
        } catch (UnlandedRobotException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Assert.assertEquals(Direction.NORTH, rep);
    }

    @Test
    public void testGetDirectionPositionException() throws UnlandedRobotException {
        exception.expect(UnlandedRobotException.class);
        Robot r = new Robot();
        r.getDirection();
    }

    @Test
    public void testTurnLeft()
    {
        Robot r = new Robot();
        r.land(new Coordinates(0,0), new LandSensor(new Random()));
        try {
            Assert.assertEquals(Direction.NORTH, r.getDirection());
            r.turnLeft();
            Assert.assertEquals(Direction.WEST, r.getDirection());
            r.turnLeft();
            Assert.assertEquals(Direction.SOUTH, r.getDirection());
            r.turnLeft();
            Assert.assertEquals(Direction.EAST, r.getDirection());
            r.turnLeft();
            Assert.assertEquals(Direction.NORTH, r.getDirection());
        } catch (UnlandedRobotException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testTurnLeftException() throws UnlandedRobotException {
        exception.expect(UnlandedRobotException.class);
        Robot r = new Robot();
        r.turnLeft();
    }

    @Test
    public void testTurnRight()
    {
        Robot r = new Robot();
        r.land(new Coordinates(0,0), new LandSensor(new Random()));
        try {
            Assert.assertEquals(Direction.NORTH, r.getDirection());
            r.turnRight();
            Assert.assertEquals(Direction.EAST, r.getDirection());
            r.turnRight();
            Assert.assertEquals(Direction.SOUTH, r.getDirection());
            r.turnRight();
            Assert.assertEquals(Direction.WEST, r.getDirection());
            r.turnRight();
            Assert.assertEquals(Direction.NORTH, r.getDirection());
        } catch (UnlandedRobotException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testTurnRightException() throws UnlandedRobotException {
        exception.expect(UnlandedRobotException.class);
        Robot r = new Robot();
        r.turnRight();
    }

    @Test
    public  void testMoveForward()
    {
        Random rand = Mockito.mock(Random.class);
        // On s'assure que le terrain et toujours franchissable.
        Mockito.when(rand.nextInt(Land.CountLand())).thenReturn(0);
        Robot r = new Robot();
        r.land(new Coordinates(0,0), new LandSensor(rand));
        try {
            Assert.assertEquals(0, r.getXposition());
            Assert.assertEquals(0, r.getYposition());
            r.moveForward();
            Assert.assertEquals(0, r.getXposition());
            Assert.assertEquals(1, r.getYposition());
            r.turnRight();
            r.moveForward();
            Assert.assertEquals(1, r.getXposition());
            Assert.assertEquals(1, r.getYposition());
            r.turnRight();
            r.moveForward();
            Assert.assertEquals(1, r.getXposition());
            Assert.assertEquals(0, r.getYposition());
            r.turnRight();
            r.moveForward();
            Assert.assertEquals(0, r.getXposition());
            Assert.assertEquals(0, r.getYposition());
        } catch (UnlandedRobotException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InsufficientChargeException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (LandSensorDefaillance landSensorDefaillance) {
            landSensorDefaillance.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InaccessibleCoordinate inaccessibleCoordinate) {
            inaccessibleCoordinate.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testMoveForwardUnlanded() throws UnlandedRobotException {
        exception.expect(UnlandedRobotException.class);
        Robot r = new Robot();
        try {
            r.moveForward();
        } catch (InsufficientChargeException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (LandSensorDefaillance landSensorDefaillance) {
            landSensorDefaillance.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InaccessibleCoordinate inaccessibleCoordinate) {
            inaccessibleCoordinate.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testMoveForwardCharge() throws InsufficientChargeException
    {
        exception.expect(InsufficientChargeException.class);
        Random rand = Mockito.mock(Random.class);
        // On s'assure que le terrain et toujours franchissable.
        Mockito.when(rand.nextInt(Land.CountLand())).thenReturn(0);
        Robot r = new Robot(1000000.0, new Battery());
        r.land(new Coordinates(0,0), new LandSensor(rand));
        try {
            r.moveForward();
        } catch (UnlandedRobotException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (LandSensorDefaillance landSensorDefaillance) {
            landSensorDefaillance.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InaccessibleCoordinate inaccessibleCoordinate) {
            inaccessibleCoordinate.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public  void testMoveBackward()
    {
        Random rand = Mockito.mock(Random.class);
        // On s'assure que le terrain et toujours franchissable.
        Mockito.when(rand.nextInt(Land.CountLand())).thenReturn(0);
        Robot r = new Robot();
        r.land(new Coordinates(0,0), new LandSensor(rand));
        try {
            Assert.assertEquals(0, r.getXposition());
            Assert.assertEquals(0, r.getYposition());
            r.moveBackward();
            Assert.assertEquals(0, r.getXposition());
            Assert.assertEquals(-1, r.getYposition());
            r.turnRight();
            r.moveBackward();
            Assert.assertEquals(-1, r.getXposition());
            Assert.assertEquals(-1, r.getYposition());
            r.turnRight();
            r.moveBackward();
            Assert.assertEquals(-1, r.getXposition());
            Assert.assertEquals(0, r.getYposition());
            r.turnRight();
            r.moveBackward();
            Assert.assertEquals(0, r.getXposition());
            Assert.assertEquals(0, r.getYposition());
        } catch (UnlandedRobotException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InsufficientChargeException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (LandSensorDefaillance landSensorDefaillance) {
            landSensorDefaillance.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InaccessibleCoordinate inaccessibleCoordinate) {
            inaccessibleCoordinate.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testMoveBackwardUnlanded() throws UnlandedRobotException {
        exception.expect(UnlandedRobotException.class);
        Robot r = new Robot();
        try {
            r.moveBackward();
        } catch (InsufficientChargeException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (LandSensorDefaillance landSensorDefaillance) {
            landSensorDefaillance.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InaccessibleCoordinate inaccessibleCoordinate) {
            inaccessibleCoordinate.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testMoveBackwardCharge() throws InsufficientChargeException
    {
        exception.expect(InsufficientChargeException.class);
        Random rand = Mockito.mock(Random.class);
        // On s'assure que le terrain et toujours franchissable.
        Mockito.when(rand.nextInt(Land.CountLand())).thenReturn(0);
        Robot r = new Robot(1000000.0, new Battery());
        r.land(new Coordinates(0,0), new LandSensor(rand));
        try {
            r.moveBackward();
        } catch (UnlandedRobotException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (LandSensorDefaillance landSensorDefaillance) {
            landSensorDefaillance.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InaccessibleCoordinate inaccessibleCoordinate) {
            inaccessibleCoordinate.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testLetsGoUndefinedRoadBook() throws UndefinedRoadbookException
    {
        exception.expect(UndefinedRoadbookException.class);
        Robot r = new Robot();
        try {
            r.letsGo();
        } catch (UnlandedRobotException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InsufficientChargeException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (LandSensorDefaillance landSensorDefaillance) {
            landSensorDefaillance.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InaccessibleCoordinate inaccessibleCoordinate) {
            inaccessibleCoordinate.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testLetsGo()
    {
        Random rand = Mockito.mock(Random.class);
        // On s'assure que le terrain et toujours franchissable.
        Mockito.when(rand.nextInt(Land.CountLand())).thenReturn(0);
        Robot r = new Robot();
        r.land(new Coordinates(0,0), new LandSensor(rand));
        List<Instruction> instructions = new ArrayList<Instruction>();
        instructions.add(Instruction.FORWARD);
        instructions.add(Instruction.TURNLEFT);
        instructions.add(Instruction.BACKWARD);
        instructions.add(Instruction.TURNRIGHT);
        r.setRoadBook(new RoadBook(instructions));
        try {
            r.letsGo();
            Assert.assertEquals(1, r.getXposition());
            Assert.assertEquals(1, r.getYposition());
        } catch (UnlandedRobotException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UndefinedRoadbookException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InsufficientChargeException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (LandSensorDefaillance landSensorDefaillance) {
            landSensorDefaillance.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InaccessibleCoordinate inaccessibleCoordinate) {
            inaccessibleCoordinate.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
