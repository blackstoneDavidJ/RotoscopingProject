package com.blackstonedj;

import java.awt.image.BufferedImage;

public class Main 
{
	public static void main(String[] args) 
	{
		String path = "resources/input/car2.png";
		ImageModder modder = new ImageModder(path);
		BufferedImage img = modder.getImage();
		GreyScale grey = new GreyScale();
		//img = grey.greyScale(img);
		EdgeDetector edge = new SobelFilter();
		NewGaussianBlur blur = new NewGaussianBlur(1);
		DoubleThreshold thresh = new DoubleThreshold(.3f, .1f);
		//modder.save("squarethinned_10std_41x41r", new SobelFilter().edgeDetection(blur.gaussianFilter(img), true,true));
		CannyEdge canny = new CannyEdge(edge, grey, blur, thresh, true, true);
		modder.save("car2", canny.proccessor(img));
		//modder.save("image1", new ImageDrawer().draw1());
		//modder.save("testblur", edge.edgeDetection(blur.gaussianFilter(img), false,false));
	}
}