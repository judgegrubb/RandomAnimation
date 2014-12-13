package animation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Animation
{
	/**
	 * This is the method that you need to rewrite to create a custom animation.
	 * This method is called repeatedly as the animation proceeds. It needs to
	 * draw to g how the animation should look after t milliseconds have passed.
	 * 
	 * @param g
	 *            Graphics object on which to draw
	 * @param t
	 *            Number of milliseconds that have passed since animation
	 *            started
	 */
	public static void paintFrame (Graphics g, int t)
	{

		// Draw the house
		paintHouse(g, t);
		
		// Draw the man
		paintMan(g, t);	
		
		// after 7.5 seconds display's the victory message
		if (t > 7500) {
			displayMessage(g, t);
		}
	}
	
	/**
	 * Draw a message depending on t and graphics object g
	 * @param g
	 * @param t
	 */
	
	public static void displayMessage(Graphics g, int t) {
		// set's the font of the message
		g.setFont(new Font("default", Font.BOLD, 30));
		
		// changes the message's color every 2 seconds
		if (t / 1000 % 2 == 0) {
			g.setColor(Color.RED);
		}
		else {
			g.setColor(Color.BLUE);
		}
		
		// display the message
		g.drawString("THE HOUSE WINS", 40, 200);
	}
	
	/**
	 * Draw a house making it move depending on t into graphics object g.
	 * @param g
	 * @param t
	 */
	
	public static void paintHouse(Graphics g, int t) {
		
		// set the house color
		g.setColor(Color.BLUE);
		
		// set a basis x and y
		int x = 280;
		int y = 50;
		
		// changes x and y based on t
		if (t > 2500 && t < 5000) {
			x = x + (t - 2500) / 100;
		} else if (t >= 5000 && t < 6000) {
			x = 305;
		} else if (t >= 6000 && t < 7000) {
			x = 305 - ((t - 6000) / 5);
		} else if (t >= 7000) {
			x =105;
		}
		
		if (t >= 7500) {
			x = doADanceX(g, t);
			y = doADanceY(t);
		}
		
		// draw the house with the appropriate x and y
		g.drawRect(x, y, 60, 45);
		g.drawLine(x, y, x + 30, y - 15);
		g.drawLine(x + 30, y - 15, x + 60, y);
	}
	
	/**
	 * Takes in a graphics object and time and makes the house change color and do a little dance on the X axis.
	 * @param g
	 * @param t
	 */
	
	public static int doADanceX(Graphics g, int t) {
		
		// based on t changed the color of the house rapidly
		if (t / 100 % 2 == 0)
		{
			g.setColor(Color.RED);
		}
		else if (t / 100 % 3 == 0)
		{
			g.setColor(Color.MAGENTA);
		} else {
			g.setColor(Color.CYAN);
		}
		
		// randomly changes x to make the house flash all over in style
		int x;
		if ((int)(Math.random() * 100) % 2 == 0) {
			x = 120;
		} else {
			x = 90;
		}
		
		return x;
	}
	
	/**
	 * Takes in a time and makes the house do a little dance on the Y axis.
	 * @param g
	 * @param t
	 */
	
	public static int doADanceY(int t) {
		
		// randomly changes y to make the house flash all over in style
		int y;
		if ((int)(Math.random() * 100) % 2 == 0) {
			y = 65;
		} else {
			y = 35;
		}
		return y;
	}
	
	/**
	 * Draws a man who moves after the house then gets run over by said house
	 * @param g
	 * @param t
	 */
	
	public static void paintMan(Graphics g, int t) {
		g.setColor(Color.BLACK);
		
		// Move the object steadily from left to right for 2 seconds
		int x = 60;
		if (t < 2000) {
			x += t / 10;
		} else if (t >=2000 && t < 5000){
			x = 260;
		} else if (t >= 5000 && t < 6000) {
			x = 260 - (t - 5000) / 10;
		} else if (t >= 6000 && t < 6720 ){
			x = 160;
		} else {
			x = 160 - (t - 6720);
		}
		
		// Move the arms and the legs
		int legX;
		if (t < 1000){
			legX = t / 100;
		} else if (t < 2000) {
			legX = (1000 - (t - 1000))/100;
		} else if (t >= 2000 && t < 5000){
			legX = 0;
		} else if (t >= 5000 && t < 6000) {
			legX = (t - 5000) / 100;
		} else {
			legX = 0;
		}
		
		// Make the man explode when house hits him
		int headY = 50;
		if (t >=6720) {
			headY = 50 - (t - 6720);
		}
		
		int legY = 80;
		if (t >= 6720) {
			legY = 80 + (t - 6720);
		}
		
		
		// Draw his head
		g.drawOval(x, headY, 10, 10);
		
		// Draw his body
		g.drawLine(x + 5, 60, x + 5, 80);
		
		// Draw his legs
		g.drawLine(x + 5, legY, x + legX, legY + 15);
		g.drawLine(x + 5, legY, x + 10 - legX, legY + 15);
		
		// Draw his arms
		g.drawLine(x + 5, headY + 15, x + legX, headY + 30);
		g.drawLine(x + 5, legY - 15, x + 10 - legX, legY);
	}
}
