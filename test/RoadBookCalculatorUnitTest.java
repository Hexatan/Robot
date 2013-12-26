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

public class RoadBookCalculatorUnitTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testTargetToTheTop()
    {
        Random rand = Mockito.mock(Random.class);
        // On s'assure que le terrain et toujours franchissable.
        Mockito.when(rand.nextInt(Land.CountLand())).thenReturn(0);
        Robot r = new Robot();
        r.land(new Coordinates(0,0), new LandSensor(rand));
        List<Instruction> parcour = new ArrayList<Instruction>();
        parcour.add(Instruction.FORWARD);
        try {
            r.computeRoadTo(new Coordinates(0,1));
            Assert.assertTrue(parcour.equals(r.getRoadBook().getInstructions()));
        } catch (UnlandedRobotException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testTargetToTheLeft()
    {
        Random rand = Mockito.mock(Random.class);
        // On s'assure que le terrain et toujours franchissable.
        Mockito.when(rand.nextInt(Land.CountLand())).thenReturn(0);
        Robot r = new Robot();
        r.land(new Coordinates(0,0), new LandSensor(rand));
        List<Instruction> parcour = new ArrayList<Instruction>();
        parcour.add(Instruction.TURNLEFT);
        parcour.add(Instruction.FORWARD);
        try {
            r.computeRoadTo(new Coordinates(-1,0));
            Assert.assertTrue(parcour.equals(r.getRoadBook().getInstructions()));
            /*
            Ce test echoue car le robot prefere faire :
            TURNRIGHT
            TURNRIGHT
            TURNRIGHT
            FORWARD

            Or, tourner dans le sens inverse des aiguille d'une montre savère etre
            plus efficace
             */
        } catch (UnlandedRobotException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testTargetToTheRight()
    {
        Random rand = Mockito.mock(Random.class);
        // On s'assure que le terrain et toujours franchissable.
        Mockito.when(rand.nextInt(Land.CountLand())).thenReturn(0);
        Robot r = new Robot();
        r.land(new Coordinates(0,0), new LandSensor(rand));
        List<Instruction> parcour = new ArrayList<Instruction>();
        parcour.add(Instruction.TURNRIGHT);
        parcour.add(Instruction.FORWARD);
        try {
            r.computeRoadTo(new Coordinates(1,0));
            Assert.assertTrue(parcour.equals(r.getRoadBook().getInstructions()));
        } catch (UnlandedRobotException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testTargetToTheBottom()
    {
        Random rand = Mockito.mock(Random.class);
        // On s'assure que le terrain et toujours franchissable.
        Mockito.when(rand.nextInt(Land.CountLand())).thenReturn(0);
        Robot r = new Robot();
        r.land(new Coordinates(0,0), new LandSensor(rand));
        List<Instruction> parcour = new ArrayList<Instruction>();
        parcour.add(Instruction.BACKWARD);
        try {
            r.computeRoadTo(new Coordinates(0,-1));
            Assert.assertTrue(parcour.equals(r.getRoadBook().getInstructions()));
            /*
            Ce test echoue car le robot prefere faire :
            TURNRIGHT
            TURNRIGHT
            FORWARD

            Or, la marche arriere permet d'atteindre la cible 3x plus vite et est donc
            plus efficace.
             */
        } catch (UnlandedRobotException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testComputeRoadLanded() throws UnlandedRobotException
    {
        exception.expect(UnlandedRobotException.class);
        Robot r = new Robot();
        r.computeRoadTo(new Coordinates(1, 1));
    }

    @Test
    public void testMeilleurParcour()
    {
        Random rand = Mockito.mock(Random.class);
        // On s'assure que le terrain et toujours franchissable.
        Mockito.when(rand.nextInt(Land.CountLand())).thenReturn(0);
        LandSensor sensor = new LandSensor(rand);

        Mockito.when(rand.nextInt(Land.CountLand())).thenReturn(0);

        try {
            sensor.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(0,1));
            sensor.getPointToPointEnergyCoefficient(new Coordinates(0,1), new Coordinates(1,1));
            sensor.getPointToPointEnergyCoefficient(new Coordinates(1,1), new Coordinates(2,1));
            sensor.getPointToPointEnergyCoefficient(new Coordinates(2,1), new Coordinates(3,1));
            sensor.getPointToPointEnergyCoefficient(new Coordinates(3,1), new Coordinates(4,1));
            sensor.getPointToPointEnergyCoefficient(new Coordinates(4,1), new Coordinates(5,1));
            sensor.getPointToPointEnergyCoefficient(new Coordinates(5,1), new Coordinates(5,0));
        } catch (LandSensorDefaillance landSensorDefaillance) {
            landSensorDefaillance.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InaccessibleCoordinate inaccessibleCoordinate) {
            inaccessibleCoordinate.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Mockito.when(rand.nextInt(Land.CountLand())).thenReturn(3);

        Robot r = new Robot();
        r.land(new Coordinates(0,0), sensor);
        List<Instruction> parcour = new ArrayList<Instruction>();

        parcour.add(Instruction.FORWARD);
        parcour.add(Instruction.TURNRIGHT);
        parcour.add(Instruction.FORWARD);
        parcour.add(Instruction.FORWARD);
        parcour.add(Instruction.FORWARD);
        parcour.add(Instruction.FORWARD);
        parcour.add(Instruction.FORWARD);
        parcour.add(Instruction.TURNRIGHT);
        parcour.add(Instruction.FORWARD);

        try {
            r.computeRoadTo(new Coordinates(5,0));
            Assert.assertTrue(parcour.equals(r.getRoadBook().getInstructions()));
            /*
            Ce test echoue car le robot prefere faire :
            TURNRIGHT
            FORWARD
            FORWARD
            FORWARD
            FORWARD
            FORWARD

            c'est a dire un chemin coutant 17 d'energie alors qu'il pourrait contourner la
            zone sableuse en utilisant le chemin de terre, chemin plus long mais ne coutant
            que 7 d'energie
             */
        } catch (UnlandedRobotException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
