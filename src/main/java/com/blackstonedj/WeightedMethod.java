package com.blackstonedj;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WeightedMethod 
{
	public BufferedImage img;
	
	public WeightedMethod(String path)
	{
		ImageLoader loader = new ImageLoader(path);
		img = loader.getImage();
	}
	
	public void greyScale()
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
			
		File output = new File("resources/weighted_apples.png");
		try 
		{
			ImageIO.write(img, "png", output);
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
			
		System.out.println("Weighted Method done");
	}
}
