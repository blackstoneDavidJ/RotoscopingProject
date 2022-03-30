package com.blackstonedj;

import java.awt.Color;
import java.awt.image.BufferedImage;

//Gaussian Blur filter
public class GaussianBlur 
{
	private int radius;
	private double stddev;

	//constructor takes an img and radius for kernal
	public GaussianBlur(int radius, double stddev)
	{
		this.radius = radius;
		this.stddev = stddev;
	}
	
	//cjamge ,atrix 
	//renormalize change matrix value, divide pixel value by sum of matrix
	//gaussian filter
	public BufferedImage gaussianFilter(BufferedImage img)
	{
		return createGaussianImage(img, generateWeightMatrix(radius, stddev), radius);
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
		double sum2 = 0;
		//divides weights by sum of weights
		for(int i = 0; i < weights.length; i++) 
		{
			for(int j = 0; j < weights[i].length; j++)
			{
				weights[i][j] /= sum;
				System.out.print(weights[i][j] +" ");
			}
			System.out.println('\n');
		}
		System.out.print(sum2);
		return weights;
	}
	
	private BufferedImage createGaussianImage(BufferedImage img, double[][] weights, int radius)
	{		
		//for loop to access each pixel
		BufferedImage answer = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int i = 1; i < img.getWidth() - 1; i++) 
		{
			for(int j = 1; j < img.getHeight() - 1; j++)
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
		
		System.out.println(sum);
		
		return (int) sum;
	}
	
	//gaussian 2D Function
	private double gaussianModel(double x, double y, double std)
	{
		return (1 / (2 * Math.PI * Math.pow(std, 2)) * Math.exp(-(Math.pow(x,  2) + Math.pow(y, 2)) / (2 * Math.pow(std, 2))));
	}
}
