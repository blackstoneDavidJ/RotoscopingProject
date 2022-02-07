package com.blackstonedj;

import java.awt.image.BufferedImage;

public class Main 
{
	public static void main(String[] args) 
	{
		String path = "resources/input/engine.png";
		ImageModder modder = new ImageModder(path);
		BufferedImage img = modder.getImage();
		GreyScale grey = new GreyScale();
		img = grey.greyScale(img);
		EdgeDetector edge = new SobelFilter();
		GaussianBlur blur = new GaussianBlur(1,1);
		//modder.save("circle5x5", new SobelFilter().edgeDetection(new GaussianBlur(5,5).gaussianFilter(img),true,true));
		CannyEdge canny = new CannyEdge(edge, grey, blur, true, true);
		modder.save("engine", canny.edgeDetector(img));
	}
}