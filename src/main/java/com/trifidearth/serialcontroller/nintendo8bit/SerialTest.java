package com.trifidearth.serialcontroller.nintendo8bit;

import java.io.InputStream;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.util.Enumeration;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;



public class SerialTest implements SerialPortEventListener 
{
	int prev_state = 0;
	SerialPort serialPort;
        /** The port we're normally going to use. */
	private static final String PORT_NAMES[] = { 
			"COM4", // Windows
			"/dev/tty.usbserial-A9007UX1", // Mac OS X
			"/dev/ttyUSB0", // Linux
			};
	/** Buffered input stream from the port */
	private InputStream input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 10000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 57600;

	public void initialize() {
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		// iterate through, looking for the port
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			//checks all names from different systems to get match
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}

		if (portId == null) {
			System.out.println("Could not find COM port.");
			//5 sec delay, to read error....
			long delay = System.currentTimeMillis();
			while(System.currentTimeMillis() - delay < 5000);
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = serialPort.getInputStream();
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) 
	{	
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) 
		{
			try 
			{
				int available = input.available();
				byte chunk[] = new byte[available];
				input.read(chunk, 0, available);

				// Displayed results are codepage dependent
				//System.out.println(new String(chunk));
				//System.out.println("\t\t" + chunk.length);
				//for(byte state : chunk)
				//{
					//System.out.println(Integer.toBinaryString(Math.abs((int)state)));
					//prev_state = action(Math.abs((int)state), prev_state);
					prev_state = action(Integer.parseInt((new String(chunk)),10),prev_state);
				//}
			} 
			
			catch (Exception e) 
			{
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}

	public static void main(String[] args) throws Exception {
		SerialTest main = new SerialTest();
		main.initialize();
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Welcome Mario :)");
		System.out.println();
	}
	
	int action(int code, int prev_code)
	{
		int temp = code;
		
		try 
		{
			Robot robot = new Robot();

			//iff new code, then do something
			if(code != prev_code)
			{
				System.out.println(code + " " + prev_code);
				//{RIGHT}
				if(prev_code < 128 && code >= 128)
		        	robot.keyPress(KeyEvent.VK_D);
				else if(prev_code >= 128 && code < 128)
		        	robot.keyRelease(KeyEvent.VK_D);
				if(code >= 128)
					code = code - 128;
				if(prev_code >= 128)
					prev_code = prev_code - 128;
		        
				//{LEFT}
				if(prev_code < 64 && code >= 64)
		        	robot.keyPress(KeyEvent.VK_A);
				else if(prev_code >= 64 && code < 64)
		        	robot.keyRelease(KeyEvent.VK_A);
				if(code >= 64)
					code = code - 64;
				if(prev_code >= 64)
					prev_code = prev_code - 64;
		        
				//{DOWN}
				if(prev_code < 32 && code >= 32)
		        	robot.keyPress(KeyEvent.VK_S);
				else if(prev_code >= 32 && code < 32)
		        	robot.keyRelease(KeyEvent.VK_S);
				if(code >= 32)
					code = code - 32;
				if(prev_code >= 32)
					prev_code = prev_code - 32;
		        
				//{UP}
				if(prev_code < 16 && code >= 16)
		        	robot.keyPress(KeyEvent.VK_W);
				else if(prev_code >= 16 && code < 16)
		        	robot.keyRelease(KeyEvent.VK_W);
				if(code >= 16)
					code = code - 16;
				if(prev_code >= 16)
					prev_code = prev_code - 16;
		        
				//{START}
				if(prev_code < 8 && code >= 8)
		        	robot.keyPress(KeyEvent.VK_P);
				else if(prev_code >= 8 && code < 8)
		        	robot.keyRelease(KeyEvent.VK_P);
				if(code >= 8)
					code = code - 8;
				if(prev_code >= 8)
					prev_code = prev_code - 8;
		        
				//{SELECT}
				if(prev_code < 4 && code >= 4)
		        	robot.keyPress(KeyEvent.VK_O);
				else if(prev_code >= 4 && code < 4)
		        	robot.keyRelease(KeyEvent.VK_O);
				if(code >= 4)
					code = code - 4;
				if(prev_code >= 4)
					prev_code = prev_code - 4;
				
				//{B}
				if(prev_code < 2 && code >= 2)
		        	robot.keyPress(KeyEvent.VK_N);
				else if(prev_code >= 2 && code < 2)
		        	robot.keyRelease(KeyEvent.VK_N);
				if(code >= 2)
					code = code - 2;
				if(prev_code >= 2)
					prev_code = prev_code - 2;
				
				//{A}
				if(prev_code < 1 && code == 1)
		        	robot.keyPress(KeyEvent.VK_M);
				else if(prev_code == 1 && code < 1)
		        	robot.keyRelease(KeyEvent.VK_M);		      
			}
		} 
		
		catch (AWTException e) 
		{
            System.out.println("Low level input control is not allowed " + e.getMessage());
		}
		
		return temp;
	}
	
}