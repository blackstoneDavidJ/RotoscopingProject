package com.blackstonedj;

import java.awt.image.BufferedImage;

public class Main 
{
	public static void main(String[] args) 
	{
		String path = "resources/input/circle.png";
		ImageModder modder = new ImageModder(path);
		BufferedImage img = modder.getImage();
		/*
		int radius = 5;
		CannyEdge canny = new CannyEdge(new SobelFilter(), new GreyScale(), new GaussianBlur(radius));
		modder.save("cannyImage_engine", canny.edgeDetector(img, 1));
		*/
		
		//GaussianBlur blur = new GaussianBlur(5,5);
		//modder.save("square_blur", blur.gaussianFilter(img));
				
		GreyScale grey = new GreyScale();
		img = grey.greyScale(img);
		modder.save("circle_small", new SobelFilter().edgeDetection(new GaussianBlur(5,1).gaussianFilter(img),true,false));
		//modder.save("circle_canny_colored", new CannyEdge(new SobelFilter(), new GreyScale(), new GaussianBlur(5,1), false, true).edgeDetector(img));
		//Circle cl = new Circle(5000);
		//modder.save("cl", cl.makeCircle(400, 400));
		//Square sq = new Square(250);
		//modder.save("square", sq.makeSquare(500,500));		
		//System.out.println(Math.toDegrees(Math.atan2(120,308)));
		
		 /*int gy = ((1 * 255) + (0 * 255) + (-1 * 255)) 
                 + ((2 * 255) + (0 * 255) + (-2 * 255))                        
                 + ((1 * 255) + (0 * 255) + (-1 * 255)); 
		 System.out.println(gy);*/
	}
}
