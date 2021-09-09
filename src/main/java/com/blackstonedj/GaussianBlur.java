package com.blackstonedj;

import java.awt.Color;
import java.awt.image.BufferedImage;

//Gaussian Blur filter
public class GaussianBlur 
{
	private BufferedImage img;
	private int radius;

	//constructor takes an img and radius for kernal
	public GaussianBlur(BufferedImage img, int radius)
	{
		this.img = img;
		this.radius = radius;
	}
	
	//gaussian filter
	public BufferedImage gaussianFilter(double stDev)
	{
		return createGaussianImage(img, generateWeightMatrix(radius, stDev), radius);
	}
	
	//generates a weight matrix based given parameters
	private double[][] generateWeightMatrix(int radius, double stdev)
	{
		double[][] weights = new double[radius][radius];
		double sum = 0;
		for(int i = 0; i < weights.length; i++) 
		{
			for(int j = 0; j < weights[i].length; j++)
			{
				weights[i][j] = gaussianModel(i - radius / 2, j - radius / 2, stdev);
				sum += weights[i][j];
			}
		}
		
		//divides weights by sum of weights
		for(int i = 0; i < weights.length; i++) 
		{
			for(int j = 0; j < weights[i].length; j++)
			{
				weights[i][j] /= sum;
			}
		}
		
		return weights;
	}

	private BufferedImage createGaussianImage(BufferedImage img, double[][] weights, int radius)
	{
		System.out.println("working..");
		
		//for loop to access each pixel
		BufferedImage answer = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < img.getWidth() - radius; i++) 
		{
			for(int j = 0; j < img.getHeight() - radius; j++)
			{
				double[][] distributedColorRed = new double[radius][radius];
				double[][] distributedColorGreen = new double[radius][radius];
				double[][] distributedColorBlue = new double[radius][radius];
				
				//forloops to set color arrays with weighted
				for(int x = 0; x < weights.length; x++) 
				{
					for(int y = 0; y < weights[x].length; y++)
					{
						try {
							int sampleX = i + x - (weights.length / 2);
							int sampleY = j + y - (weights.length / 2);
							Color sampledColor = new Color(img.getRGB(sampleX, sampleY));
							
							distributedColorRed[x][y] = weights[x][y] * sampledColor.getRed();
							distributedColorGreen[x][y] = weights[x][y] * sampledColor.getGreen();
							distributedColorBlue[x][y] = weights[x][y] * sampledColor.getBlue();
						} 
						
						catch(Exception e) { /*System.out.println("out of bounds.");*/ }
					}
				}
				
				//sets each pixel(i,j) to specific rgb values
				answer.setRGB(i, j, new Color(getWeightedColorValue(distributedColorRed), getWeightedColorValue(distributedColorGreen),getWeightedColorValue(distributedColorBlue)).getRGB());
			}
		}
		
		return answer;
	}
	
	//gets the weighted color value for each color array for each pixel
	private int getWeightedColorValue(double[][] weightedColor)
	{
		double sum = 0;
		
		for(int i = 0; i < weightedColor.length; i++) {
			for(int j = 0; j < weightedColor[i].length; j++)
			{
				sum += weightedColor[i][j];
			}
		}
		
		return (int) sum;
	}
	
	//gaussian 2D Function
	private double gaussianModel(double x, double y, double std)
	{
		return (1 / (2 * Math.PI * Math.pow(std, 2)) * Math.exp(-(Math.pow(x,  2) + Math.pow(y, 2)) / (2 * Math.pow(std, 2))));
	}
}
