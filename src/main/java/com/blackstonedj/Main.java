package com.blackstonedj;

import java.awt.image.BufferedImage;
import java.util.List;

public class Main 
{
	public static void main(String[] args) 
	{
		String path = "resources/input/image4.png";
		ImageModder modder = new ImageModder(path);
		BufferedImage img = modder.getImage();
		GreyScale grey = new GreyScale();
		//img = grey.greyScale(img);
		EdgeDetector edge = new SobelFilter();
		ImageDrawer draw = new ImageDrawer();
		NewGaussianBlur blur = new NewGaussianBlur(1);
		DoubleThreshold thresh = new DoubleThreshold(.4f, .2f);
		//modder.save("squarethinned_10std_41x41r", new SobelFilter().edgeDetection(blur.gaussianFilter(img), true,true));
		CannyEdge canny = new CannyEdge(edge, grey, blur, thresh, true,true);
		modder.save("image4before", canny.proccessor(img));
		//modder.save("image5", draw.draw5());
		//modder.save("circleblur10", blur.gaussianFilter(img));
		//modder.save("testblur", edge.edgeDetection(blur.gaussianFilter(img), false,false));
	}
}
