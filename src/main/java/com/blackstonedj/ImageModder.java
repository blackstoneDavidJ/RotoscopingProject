package com.blackstonedj;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//modifier for images
public class ImageModder 
{
	private BufferedImage image;
	
	public ImageModder()
	{
		image = new BufferedImage(0, 0, BufferedImage.TYPE_INT_RGB);
	}
	
	public ImageModder(String path)
	{
		image = load(path);
	}
	
	//load an image
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
	
	//save and image
	public void save(String name, BufferedImage img)
	{
		File output = new File("resources/"+name +".png");
		try 
		{
			ImageIO.write(img, "png", output);
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
				
		System.out.println("Image: " +name +" done");
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
}
