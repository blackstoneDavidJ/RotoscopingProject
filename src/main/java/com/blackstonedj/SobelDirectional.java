package com.blackstonedj;

import java.awt.Color;

public class SobelDirectional implements EdgeDirection 
{
	public SobelDirectional()
	{
		
	}
	
	public int getColor(double angleVal)
	{
		int color = 0;
		Color col;
		
		if(angleVal == 0 || angleVal == 180)
		{
			color = (int) new Color(255,0,0).getRGB();
		}
		
		else if(angleVal == 90 || angleVal == -180 || angleVal ==-90)
		{
			color = (int) new Color(0,0,255).getRGB();
		}
	
		else 
		{
			double temp = angleVal;
			temp *= 382.5;
			col = new Color((int)temp);
			color = (int) col.getRGB();
		}

		return color;
	}	
}
