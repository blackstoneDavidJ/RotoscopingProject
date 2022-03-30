package com.blackstonedj;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Combiner 
{
	public Combiner()
	{		
	}
	
	public BufferedImage combineImages(BufferedImage cannyImg, BufferedImage paletteImg) throws IOException
	{	
		//ImageIO.write(paletteImg, "png", new File("resources/uuuucomninedimg.png"));
		for(int i = 0; i < cannyImg.getWidth(); i++)
		{
			for(int j = 0; j < cannyImg.getHeight(); j++)
			{
				int pixelCol = cannyImg.getRGB(i,j);
				if(pixelCol == -16777216)
				{
					paletteImg.setRGB(i, j, Color.BLACK.getRGB());
				}
			}
		}
		//ImageIO.write(paletteImg, "png", new File("resources/befrercomninedimg.png"));
		return paletteImg;		
	}
}
