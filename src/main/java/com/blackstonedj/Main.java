package com.blackstonedj;

import java.awt.image.BufferedImage;

public class Main 
{
	public static void main(String[] args) 
	{
		//get image
		String path = "resources/engine.png";
		ImageModder modder = new ImageModder(path);
		BufferedImage img = modder.getImage();
		/*
		int radius = 5;
		CannyEdge canny = new CannyEdge(new SobelFilter(), new GreyScale(), new GaussianBlur(radius));
		modder.save("cannyImage_stddev_1", canny.edgeDetector(img, 1));
		*/
		GreyScale grey = new GreyScale();
		GaussianBlur blur = new GaussianBlur(10);
		blur.setStddev(1);
		modder.save("colorSobel", new PrewittFilter().edgeDetection(blur.gaussianFilter(grey.greyScale(img))));
	}
}
