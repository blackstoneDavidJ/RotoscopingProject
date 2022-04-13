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
		//loop that gets a rgb pixel, gets the r, g, b values, due to wavelength each color requires a weight
		//being .3 for r, .59 for g, .11 for b
		for(int i = 0; i < img.getWidth(); i++) 
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				int val = getGreyPixel(img.getRGB(i, j));
				Color color = new Color(val, val, val);
				img.setRGB(i, j, color.getRGB());
			}
		}
		
		return img;
	}
	
	private int getGreyPixel(int rgb) 
	{
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb) & 0xff;

        int gray = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
	
        return gray;	
	}
}
