package bsu.edu.cs.GUI;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    @Test
    public void convertToHoursLessThan0MinTest(){
        GUIModel controller = new GUIModel();
        assertEquals("Invalid: minute cannot be negative",controller.convertMinToHours(-30));
    }

    @Test
    public void convertToHoursLessThan60MinTest(){
        GUIModel controller = new GUIModel();
        assertEquals("45 min",controller.convertMinToHours(45));
    }

    @Test
    public void convertToHoursGreaterThan60MinTest(){
        GUIModel controller = new GUIModel();
        assertEquals("1 hr 30 min",controller.convertMinToHours(90));
    }

    @Test
    public void convertToHoursTest(){
        GUIModel controller = new GUIModel();
        assertEquals("2 hr",controller.convertMinToHours(120));
    }

    @Test
    public void getWaitColorLessThan45Test(){
        GUIModel controller = new GUIModel();
        assertEquals("low-wait-time",controller.getWaitTimeColor(43));
    }

    @Test
    public void getWaitColorLessThan90Test(){
        GUIModel controller = new GUIModel();
        assertEquals("medium-wait-time",controller.getWaitTimeColor(67));
    }

    @Test
    public void getWaitColorEqualTo90Test(){
        GUIModel controller = new GUIModel();
        assertEquals("medium-wait-time",controller.getWaitTimeColor(90));
    }

    @Test
    public void getWaitColorGreaterThan90Test(){
        GUIModel controller = new GUIModel();
        assertEquals("high-wait-time",controller.getWaitTimeColor(100));
    }



}
