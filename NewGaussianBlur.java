package com.blackstonedj;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class NewGaussianBlur 
{
	private int radius;
	private double stddev;

	//constructor takes an img and radius for kernal
	public NewGaussianBlur(double stddev)
	{
		radius = (int) ((int) 4 * stddev);
		if(radius % 2 == 0)
		{
			radius++;
		}
		this.stddev = stddev;
	}
	
	public BufferedImage gaussianFilter(BufferedImage img)
	{
		double[][] weights = generateWeights(radius, stddev);
		return gaussianBlur(img, weights, radius, stddev);
	}

	private BufferedImage gaussianBlur(BufferedImage img, double[][] weights, int radius, double stddev) 
	{		
		//for loop to access each pixel
		BufferedImage answer = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < img.getWidth(); i++) 
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				double[][] distributedColorRed = new double[radius][radius];
				double[][] distributedColorGreen = new double[radius][radius];
				double[][] distributedColorBlue = new double[radius][radius];
				
				//forloops to set color arrays with weighted
				for(int x = 0; x < weights.length; x++) 
				{
					for(int y = 0; y < weights[x].length; y++)
					{	
						int sampleX = i + x - (weights.length / 2);
						int sampleY = j + y - (weights.length / 2);
						
						if(sampleX > img.getWidth() - 1)
						{
							int errorOffset = sampleX - (img.getWidth() - 1);
							sampleX = (img.getWidth() - 1) - errorOffset;
						}
						
						if(sampleY > img.getHeight() - 1)
						{
							int errorOffset = sampleY - (img.getHeight() - 1);
							sampleY = (img.getHeight() - 1) - errorOffset;
						}
						
						if(sampleX < 0)
						{
							sampleX = Math.abs(sampleX);
						}
						
						if(sampleY < 0)
						{
							sampleY = Math.abs(sampleY);
						}					
						
						Color sampledColor = new Color(img.getRGB(sampleX, sampleY));
						//System.out.print(weights[x][y] +" ");
						distributedColorRed[x][y] = weights[x][y] * sampledColor.getRed();
						distributedColorGreen[x][y] = weights[x][y] * sampledColor.getGreen();
						distributedColorBlue[x][y] = weights[x][y] * sampledColor.getBlue();
					}
				}
				//System.out.print('\n');
				
				//sets each pixel(i,j) to specific rgb values
				answer.setRGB(i, j, new Color(getWeightedColorValue(distributedColorRed), 
						getWeightedColorValue(distributedColorGreen),
						getWeightedColorValue(distributedColorBlue)).getRGB());
				//System.out.print(img.get);
			}
		}
		
		return answer;
	}

	private int getWeightedColorValue(double[][] weightedColor) 
	{
		double sum = 0;
		
		for(int i = 0; i < weightedColor.length; i++) 
		{
			for(int j = 0; j < weightedColor[i].length; j++)
			{
				sum += weightedColor[i][j];
			}
		}		
		
		return (int) sum;
	}

	private double[][] generateWeights(int radius, double stddev) 
	{
		double[][] weights = new double[radius][radius];
		double sum = 0;
		for(int i = 0; i < weights.length; i++) 
		{
			for(int j = 0; j < weights[i].length; j++)
			{
				weights[i][j] = gaussianModel(i - radius / 2, j - radius / 2, stddev);
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

	//gaussian 2D Function
	private double gaussianModel(double x, double y, double std)
	{
		return (1 / (2 * Math.PI * Math.pow(std, 2)) * Math.exp(-(Math.pow(x,  2) + Math.pow(y, 2)) / (2 * Math.pow(std, 2))));
	}
}
