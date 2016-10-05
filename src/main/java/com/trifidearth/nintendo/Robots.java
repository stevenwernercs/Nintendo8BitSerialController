package com.trifidearth.nintendo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Robots 
{

	public static void main(String[] args) 
	{
		try {
        int xCoord = 50;
        int yCoord = 100;
 
        // Move the cursor
        Robot robot = new Robot();
        robot.mouseMove(xCoord, yCoord);
         
        // Simulate a mouse click
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
 
        // Simulate a key press
        robot.keyPress(KeyEvent.VK_H);
        robot.keyRelease(KeyEvent.VK_H);
        robot.keyPress(KeyEvent.VK_E);
        robot.keyRelease(KeyEvent.VK_E);
        robot.keyPress(KeyEvent.VK_L);
        robot.keyRelease(KeyEvent.VK_L);
        robot.keyPress(KeyEvent.VK_L);
        robot.keyRelease(KeyEvent.VK_L);
        robot.keyPress(KeyEvent.VK_O);
        robot.keyRelease(KeyEvent.VK_O);
         
    } catch (AWTException e) {
            System.out.println("Low level input control is not allowed " + e.getMessage());
    }
       
  }
 
}
