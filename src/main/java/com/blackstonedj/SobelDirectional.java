package project;

import java.awt.Color;

public class SobelDirectional
{
	public SobelDirectional()
	{
		
	}
	
	public int getColor(double angleVal, int edgeVal, int largestEdge)
	{
		int max = largestEdge;
		Color color = null;
		
		if(angleVal > 60 && angleVal < 120 ||
				angleVal <= -60 && angleVal >= -120)
		{
			int colorVal = ((255 * edgeVal) / max);
			if(colorVal > 255) colorVal = 255;

			color = new Color (colorVal,0,0);
		}
		
		//blue
		else if(angleVal >= 120 && angleVal <= 150 ||
				angleVal <= -30 && angleVal >= -60) 
		{
			int colorVal = ((255 * edgeVal) / max);
			if(colorVal > 255) colorVal = 255;

			color = new Color (0,0,colorVal);
		}
		
		//yellow
		else if(angleVal >= 30 && angleVal <= 60 ||
				angleVal <= -120 && angleVal >= -150)
		{
			int colorVal = ((255 * edgeVal) / max);
			if(colorVal > 255) colorVal = 255;

			color = new Color (colorVal,colorVal,0);
		}
		
		//green
		else if(angleVal <= 30 && angleVal >= -30 || 
				angleVal >= 150 || angleVal <= -150 && angleVal >= -180)
		{
			int colorVal = ((255 * edgeVal) / max);
			if(colorVal > 255) colorVal = 255;
			color = new Color (0,colorVal,0);
		}
		
		return color.getRGB();
	}	
}