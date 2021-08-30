package com.blackstonedj;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AverageMethod 
{
	private BufferedImage img;
	
	public AverageMethod(String path)
	{
		ImageLoader loader = new ImageLoader(path);
		img = loader.getImage();
	}
	
	public void greyScale()
	{
		int width = img.getWidth(null);
		int height = img.getHeight(null);
				
		//loop that gets each pixel and their r, g, b values and averages it by 3
		for(int i = 0; i < height; i++) 
		{
			for(int j = 0; j < width; j++)
			{
				Color c = new Color(img.getRGB(j, i));
				int r = (int) (c.getRed());
				int g = (int) (c.getGreen());
				int b = (int) (c.getBlue());
				int val = ((r+g+b) / 3);
				Color newColor = new Color(val,val,val);
				img.setRGB(j, i, newColor.getRGB());
			}
		}
				
		File output = new File("resources/Average_apples.png");
		try 
		{
			ImageIO.write(img, "png", output);
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
				
		System.out.println("Average method done");
	}
}

