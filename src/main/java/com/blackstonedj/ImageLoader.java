package com.blackstonedj;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader 
{
	public BufferedImage image;
	
	public ImageLoader()
	{
		image = new BufferedImage(0, 0, 0);
	}
	
	public ImageLoader(String path)
	{
		image = load(path);
	}
	
	public BufferedImage load(String path)
	{
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		}
		
		catch(IOException e)
		{
			System.out.println("Image cannot be loaded.");
		}
		
		return img;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
}
