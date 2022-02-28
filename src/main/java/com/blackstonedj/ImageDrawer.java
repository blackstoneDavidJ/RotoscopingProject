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
            	img.setRGB(i,j, Color.WHITE.getRGB());
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
            	img.setRGB(i,j, Color.BLACK.getRGB());
            }
        }
		
		for (int i = 0; i < img.getWidth(); i++) 
        {
            for (int j = img.getHeight() / 3; j < img.getHeight(); j++) 
            {           	
            	img.setRGB(i,j, Color.WHITE.getRGB());
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
					img.setRGB(i, j, Color.YELLOW.getRGB());
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
            	img.setRGB(i,j, Color.WHITE.getRGB());
            }
        }
		
		int h = ((img.getWidth()) / 2);
		int k = ((img.getHeight()) -150);
		
		for(int i=h;i<h+50;i++)
		{
			for(int j=k;j<k+50;j++)
			{
				img.setRGB(i, j, Color.DARK_GRAY.getRGB());
			}
		}
		
		return img;
	}
	
	public BufferedImage draw5()
	{
		int colorStart = 255;
		BufferedImage img = new BufferedImage(550,255,BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < img.getWidth(); i++) 
        {
            for (int j = 0; j < img.getHeight(); j++) 
            {           	
            	if(colorStart < 0) colorStart = 0;
            	img.setRGB(i,j, new Color(colorStart,colorStart,colorStart).getRGB());
            }
            
            colorStart--;
        }
		
		int startI = 150;
		int startY = 40;
		
		while(startI < 480)
		{
			img.setRGB(startI, startY, Color.LIGHT_GRAY.getRGB());
			startI++;
		}
		
		
		
		int h = ((img.getWidth()) -450);
		int k = ((img.getHeight()) -150);
		
		for(int i=h;i<h+120;i++)
		{
			for(int j=k;j<k+100;j++)
			{
				img.setRGB(i, j, Color.LIGHT_GRAY.getRGB());
			}
		}
		
		int startI2 = 350;
		int startY2 = 166;
		
		while(startI2 < 470)
		{
			img.setRGB(startI2, startY2, Color.DARK_GRAY.getRGB());
			startI2++;
		}
		
		return img;
	}
	
	public BufferedImage draw6()
	{
		int colorStart = 255;
		BufferedImage img = new BufferedImage(550,255,BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < img.getWidth(); i++) 
        {
            for (int j = 0; j < img.getHeight(); j++) 
            {           	
            	if(colorStart < 0) colorStart = 0;
            	img.setRGB(i,j, new Color(colorStart,colorStart,colorStart).getRGB());
            }
            
            colorStart--;
        }
		
		int h = ((img.getWidth()) / 3);
		int k = ((img.getHeight()) / 2);

		int radius = 1500;
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
		
		int h2 = (50);
		int k2 = (50);

		int radius2 = 300;
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
		
		int h3 = (400);
		int k3 = (50);

		int radius3 = 300;
		for(int i = 0 ; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight() ; j++)
			{
				double val = Math.pow(i - h3,2) + Math.pow(j - k3,2);
				if(val/4 <= radius2)
				{
					img.setRGB(i, j, Color.DARK_GRAY.getRGB());
				}
				
			}
		}
		
		
		return img;
	}
}
