package project;

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

		int h = ((square.getWidth()) / 2);
		int k = ((square.getHeight()) / 2);
		for(int i = 0; i < square.getWidth(); i++)
		{
			for(int j = 0; j < square.getHeight(); j++)
			{
				
			}
		}
	}
}