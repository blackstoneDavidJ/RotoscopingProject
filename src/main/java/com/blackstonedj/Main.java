package com.blackstonedj;

import java.awt.image.BufferedImage;

public class Main 
{
	public static void main(String[] args) 
	{
		String path = "resources/input/dials.png";
		ImageModder modder = new ImageModder(path);
		BufferedImage img = modder.getImage();
		GreyScale grey = new GreyScale();
		//img = grey.greyScale(img);
		EdgeDetector edge = new SobelFilter();
		ImageDrawer draw = new ImageDrawer();
		NewGaussianBlur blur = new NewGaussianBlur(1);
		DoubleThreshold thresh = new DoubleThreshold(.4f, .2f);
		Palettization palette = new Palettization(5);
		modder.save("testpalette6", palette.runner(grey.greyScale(img)));
		//modder.save("squarethinned_10std_41x41r", new SobelFilter().edgeDetection(blur.gaussianFilter(img), true,true));
		CannyEdge canny = new CannyEdge(edge, grey, blur, thresh, true, true);
		//modder.save("mtn", canny.proccessor(img));
		//modder.save("image7", draw.draw7());
	}
}
