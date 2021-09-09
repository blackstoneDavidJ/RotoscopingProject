package com.blackstonedj;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class WeightedMethod 
{
	public WeightedMethod()
	{
	}

	public BufferedImage greyScale(BufferedImage img)
	{
		int width = img.getWidth(null);
		int height = img.getHeight(null);
			
		//loop that gets a rgb pixel, gets the r, g, b values, due to wavelength each color requires a weight
		//being .3 for r, .59 for g, .11 for b
		for(int i = 0; i < height; i++) 
		{
			for(int j = 0; j < width; j++)
			{
				Color c = new Color(img.getRGB(j, i));
				int r = (int) (c.getRed() * 0.3);
				int g = (int) (c.getGreen() * 0.59);
				int b = (int) (c.getBlue() * 0.11);
				int val = (r+g+b);
				Color newColor = new Color(val,val,val);
				img.setRGB(j, i, newColor.getRGB());
			}
		}
		
		return img;
	}
}