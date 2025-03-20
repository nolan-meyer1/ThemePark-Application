package bsu.edu.cs.GUI;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    @Test
    public void convertToHoursLessThan60MinTest(){
        Controller controller = new Controller();
        assertEquals("45 min",controller.convertMinToHours(45));
    }

    @Test
    public void convertToHoursGreaterThan60MinTest(){
        Controller controller = new Controller();
        assertEquals("1 hr 30 min",controller.convertMinToHours(90));
    }

    @Test
    public void convertToHoursTest(){
        Controller controller = new Controller();
        assertEquals("2 hr",controller.convertMinToHours(120));
    }

    @Test
    public void getWaitColorLessThan45Test(){
        Controller controller = new Controller();
        assertEquals("low-wait-time",controller.getWaitTimeColor(43));
    }

    @Test
    public void getWaitColorLessThan90Test(){
        Controller controller = new Controller();
        assertEquals("medium-wait-time",controller.getWaitTimeColor(67));
    }

    @Test
    public void getWaitColorEqualTo90Test(){
        Controller controller = new Controller();
        assertEquals("medium-wait-time",controller.getWaitTimeColor(90));
    }

    @Test
    public void getWaitColorGreaterThan90Test(){
        Controller controller = new Controller();
        assertEquals("high-wait-time",controller.getWaitTimeColor(100));
    }

}
