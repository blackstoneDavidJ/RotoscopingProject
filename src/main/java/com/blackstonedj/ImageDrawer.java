package com.blackstonedj;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageDrawer  
{
	public ImageDrawer()
	{
		
	}
	
	public BufferedImage draw1()
	{
		int colorStart = 255;
		BufferedImage img = new BufferedImage(255,255,BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < img.getWidth(); i++) 
        {
            for (int j = 0; j < img.getHeight() / 2; j++) 
            {           	
            	img.setRGB(i,j, new Color(colorStart,colorStart,colorStart).getRGB());
            	System.out.println(colorStart);
            }
            
            colorStart--;
        }
		
		return img;
	}
	
	public BufferedImage draw2()
	{
		BufferedImage img = new BufferedImage(255,255,BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < img.getWidth(); i++) 
        {
            for (int j = 0; j < img.getHeight() / 2; j++) 
            {           	
            	img.setRGB(i,j, new Color(255,255,255).getRGB());
            }
        }
		
		int h = ((img.getWidth()) / 2);
		int k = ((img.getHeight()) / 4);

		int radius = 600;
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				double val = Math.pow(i - h,2) + Math.pow(j - k,2);
				if(val/4 <= radius)
				{
					img.setRGB(i, j, Color.LIGHT_GRAY.getRGB());
				}
				
			}
		}
		
		int h2 = (img.getWidth() /2);
		int k2 = (190);

		int radius2 = 600;
		for(int i = 0 ; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight() ; j++)
			{
				double val = Math.pow(i - h2,2) + Math.pow(j - k2,2);
				if(val/4 <= radius2)
				{
					img.setRGB(i, j, Color.DARK_GRAY.getRGB());
				}
				
			}
		}
		
		return img;
	}
	
	public BufferedImage draw3()
	{
		BufferedImage img = new BufferedImage(255,255,BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < img.getWidth(); i++) 
        {
            for (int j = 0; j < img.getHeight() / 2; j++) 
            {           	
            	img.setRGB(i,j, new Color(255,255,255).getRGB());
            }
        }
		
		int h = (190);
		int k = ((img.getHeight()) / 2);

		int radius = 600;
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				double val = Math.pow(i - h,2) + Math.pow(j - k,2);
				if(val/4 <= radius)
				{
					img.setRGB(i, j, Color.DARK_GRAY.getRGB());
				}
				
			}
		}
		
		int h2 = (65);
		int k2 = ((img.getHeight()) / 2);

		int radius2 = 600;
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				double val = Math.pow(i - h2,2) + Math.pow(j - k2,2);
				if(val/4 <= radius2)
				{
					img.setRGB(i, j, Color.LIGHT_GRAY.getRGB());
				}
				
			}
		}
		
		return img;
	}
	
	public BufferedImage draw4()
	{
		BufferedImage img = new BufferedImage(255,255,BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < img.getWidth(); i++) 
        {
            for (int j = 0; j < img.getHeight() / 2; j++) 
            {           	
            	img.setRGB(i,j, new Color(255,255,255).getRGB());
            }
        }
		
		int h = ((img.getWidth()) / 2);
		int k = ((img.getHeight()) / 2);
		
		for(int i=h;i<h+50;i++)
		{
			for(int j=k;j<k+50;j++)
			{
				img.setRGB(i, j, Color.DARK_GRAY.getRGB());
			}
		}
		
		return img;
	}
}
