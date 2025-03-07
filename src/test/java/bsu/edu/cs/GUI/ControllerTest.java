package bsu.edu.cs.GUI;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest {

    @Test
    public void convertMinToHoursLessThan60MinTest(){
        Controller controller = new Controller();
        assertEquals("45 min",controller.convertMinToHours(45));
    }

    @Test
    public void convertMinToHoursGreaterThan60MinTest(){
        Controller controller = new Controller();
        assertEquals("1 hr 30 min",controller.convertMinToHours(90));
    }

    @Test
    public void convertMinToHoursTest(){
        Controller controller = new Controller();
        assertEquals("2 hr",controller.convertMinToHours(120));
    }

    @Test
    public void getRidesTest() throws noItemFoundException, networkErrorException, openInputStreamException {
        Controller controller = new Controller();
        //ID for Magic Kingdom the park we have been testing with
        assertEquals(44,controller.getRides(6).size());
    }

    @Test
    public void getWaitTimeColorLessThan45minTest(){
        Controller controller = new Controller();
        assertEquals("lowWaitTime",controller.getWaitTimeColor(43));
    }

    @Test
    public void getWaitTimeColorLessThan90minTest(){
        Controller controller = new Controller();
        assertEquals("mediumWaitTime",controller.getWaitTimeColor(67));
    }

    @Test
    public void getWaitTimeColorEqualTo90minTest(){
        Controller controller = new Controller();
        assertEquals("mediumWaitTime",controller.getWaitTimeColor(90));
    }

    @Test
    public void getWaitTimeColorGreaterThan90minTest(){
        Controller controller = new Controller();
        assertEquals("highWaitTime",controller.getWaitTimeColor(100));
    }
}
