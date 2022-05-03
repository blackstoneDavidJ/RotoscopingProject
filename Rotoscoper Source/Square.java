package com.blackstonedj;


import java.awt.Color;
import java.awt.image.BufferedImage;

public class Square 
{
	public Square()
	{
	}
	
	public BufferedImage makeSquare(int width, int height)
	{
		BufferedImage square = new BufferedImage(width+200, height+200, BufferedImage.TYPE_INT_RGB);
		int h = ((square.getWidth()) / 2);
		int k = ((square.getHeight()) / 2);
		
		for(int t=0;t<square.getWidth();t++)
		{
			for(int q=0; q<square.getHeight();q++)
			{
				square.setRGB(t, q, new Color(255,255,255).getRGB());
			}
		}
		
		for(int i=h-100;i<h+100;i++)
		{
			for(int j=k-100;j<k+100;j++)
			{
				square.setRGB(i, j, new Color(0,0,0).getRGB());
			}
		}
		
		return square;
	}
}