package com.blackstonedj;

import java.awt.Color;

public class SobelDirectional implements EdgeDirection 
{
	public SobelDirectional()
	{
		
	}
	
	public int getColor(double angleVal, int edgeVal)
	{
		
		Color color;
		
		/*if(angleVal == 180 || angleVal == 0)
		{
			color = (int) new Color(0,255,0).getRGB();
		}
		
		if(angleVal == 90 || angleVal == -90)
		{
			color = (int) new Color(255,0,0).getRGB();
		}
		*/
		//red
		if(angleVal > 60 && angleVal < 120 ||
				angleVal <= -60 && angleVal >= -120)
		{
			color = new Color(255,0,0);
		}
		
		//blue
		else if(angleVal >= 120 && angleVal <= 150 ||
				angleVal <= -30 && angleVal >= -60) 
		{
			color = new Color(0,0,255);
		}
		
		//yellow
		else if(angleVal >= 30 && angleVal <= 60 ||
				angleVal <= -120 && angleVal >= -150)
		{
			color = new Color(255,255,0);
		}
		
		//green
		else if(angleVal <= 30 && angleVal >= -30 || 
				angleVal >= 150 || angleVal <= -150 && angleVal >= -180)
		{
			color = new Color(0,255,0);
		}
		
		//5769
		//System.out.println("EdgeVal: " +edgeVal);
		int edgePercentage = (edgeVal* 10 / 1442);
		int colorVal = (255 * edgePercentage);
		color = new Color (colorVal);
		
		
		return color.getRGB();
	}	
}
