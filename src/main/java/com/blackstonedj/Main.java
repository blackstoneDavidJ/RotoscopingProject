package com.blackstonedj;

import java.awt.image.BufferedImage;

public class Main 
{
	public static void main(String[] args) 
	{
		String path = "resources/input/car.png";
		ImageModder modder = new ImageModder(path);
		BufferedImage img = modder.getImage();
		/*
		int radius = 5;
		CannyEdge canny = new CannyEdge(new SobelFilter(), new GreyScale(), new GaussianBlur(radius));
		modder.save("cannyImage_engine", canny.edgeDetector(img, 1));
		*/
		
		//GaussianBlur blur = new GaussianBlur(5,5);
		//modder.save("square_blur", blur.gaussianFilter(img));
		
		
		//GreyScale grey = new GreyScale();
		//img = grey.greyScale(img);
		//modder.save("car_colored", new SobelFilter().edgeDetection(img,true,false));
		//modder.save("square_canny_colored", new CannyEdge(new SobelFilter(), new GreyScale(), new GaussianBlur(5,5), true, true).edgeDetector(img));
		//Circle cl = new Circle(5000);
		//modder.save("cl", cl.makeCircle(400, 400));
		//Square sq = new Square(250);
		//modder.save("square", sq.makeSquare(500,500));
		
		//System.out.println(Math.toDegrees(Math.atan2(120,308)));
	}
}
