package project;

import java.awt.Color;

public class SobelDirectional
{
	public SobelDirectional()
	{
		
	}
	
	//new method for direction returning
	//45degress 360/8
	public Color getColor(double angle, int edgeVal, int largestEdge)
	{
		int max = largestEdge;
		Color color = new Color(0,0,0);
		double angleVal = angle;
		
		//red
		
		if(angleVal > 67.5  && angleVal < 112.5 ||
				angleVal < -67.5 && angleVal > -112.5)
		{
			int colorVal = ((255 * edgeVal) / max);
			if(colorVal > 255) colorVal = 255;

			color = new Color (colorVal,0,0);
		}
		
		//blue
		else if(angleVal >= 112.5 && angleVal <= 157.5 ||
				angleVal <= -22.5 && angleVal >= -67.5) 
		{
			int colorVal = ((255 * edgeVal) / max);
			if(colorVal > 255) colorVal = 255;

			color = new Color (0,0,colorVal);
		}
		
		//yellow
		else if(angleVal <= 67.5 && angleVal >= 22.5 ||
				angleVal <= -112.5 && angleVal >= -157.5)
		{
			int colorVal = ((255 * edgeVal) / max);
			if(colorVal > 255) colorVal = 255;

			color = new Color (colorVal,colorVal,0);
		}
		
		//green
		else if(angleVal < 22.5 && angleVal > -22.5 || 
				angleVal > 157.5 || angleVal < -157.5)
		{
			int colorVal = ((255 * edgeVal) / max);
			if(colorVal > 255) colorVal = 255;
			color = new Color (0,colorVal,0);
		}
		
		return color;
	}
	
	public Direction getDirection(double angle)
	{
		Direction dir = null;
		double angleVal = angle;
		if(angleVal > 67.5  && angleVal < 112.5 ||
				angleVal < -67.5 && angleVal > -112.5)
		{
			dir = Direction.NS;
		}
		
		//blue
		else if(angleVal >= 112.5 && angleVal <= 157.5 ||
				angleVal <= -22.5 && angleVal >= -67.5) 
		{
			dir = Direction.NESW;
		}
		
		//yellow
		else if(angleVal <= 67.5 && angleVal >= 22.5 ||
				angleVal <= -112.5 && angleVal >= -157.5)
		{
			dir = Direction.NWSE;
		}
		
		//green
		else if(angleVal < 22.5 && angleVal > -22.5 || 
				angleVal > 157.5 || angleVal < -157.5)
		{
			dir = Direction.EW;
		}
		
		return dir;
	}
	
	public enum Direction 
	{
		NS,
		EW,
		NESW,
		NWSE
	}
}