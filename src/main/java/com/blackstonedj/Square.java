package com.blackstonedj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Square 
{
	private int radius;
	public Square(int radius)
	{
		this.radius = radius;
	}
	
	public BufferedImage makeSquare(int width, int height)
	{
		BufferedImage square = new BufferedImage(width+200, height+200, BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < square.getWidth(); i++)
		{
			for(int j = 0; j < square.getHeight(); j++)
			{
				square.setRGB(i, j, new Color(255,255,255).getRGB());
			}
		}
		
		/*nt h = ((square.getWidth()) / 2);
		int k = ((square.getHeight()) / 2);
		double area = Math.pow(width, 2);

		for(int i = 0; i < square.getWidth(); i++)
		{
			for(int j = 0; j < square.getHeight(); j++)
			{
				Graphics2D graph = square.createGraphics();
				graph.setColor(Color.BLACK);
				graph.fill(new Rectangle(i, j, width, height));
				graph.dispose();
			}
		}*/
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		g.setColor(Color.blue);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		return g.;
	}
}