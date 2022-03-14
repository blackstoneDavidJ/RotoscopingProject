package com.blackstonedj;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Combiner 
{
	public Combiner()
	{		
	}
	
	public BufferedImage combineImages(BufferedImage cannyImg, BufferedImage paletteImg)
	{	
		BufferedImage img = new BufferedImage(cannyImg.getWidth(), cannyImg.getHeight(), BufferedImage.TYPE_INT_RGB);		
		for(int i = 0; i < cannyImg.getWidth(); i++)
		{
			for(int j = 0; j < cannyImg.getHeight(); j++)
			{
				int pixelCol = cannyImg.getRGB(i,j);
				if(pixelCol == -16777216)
				{
					img.setRGB(i, j, Color.BLACK.getRGB());
				}
				
				else
				{
					img.setRGB(i, j, paletteImg.getRGB(i, j));
				}
			}
		}
		
		return img;		
	}
}
