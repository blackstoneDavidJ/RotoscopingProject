package com.blackstonedj;

import java.awt.image.BufferedImage;

public class CannyEdge 
{
	public EdgeDetector filter;
	public GreyScale grey;
	public GaussianBlur blur;
	
	//constructor taking in edge filter, greyscale filter, and gaussianblur kernel
	public CannyEdge(EdgeDetector filter, GreyScale grey, GaussianBlur blur)
	{
		this.filter = filter;
		this.grey = grey;
		this.blur = blur;
	}
	
	//edge detection
	public BufferedImage edgeDetector(BufferedImage img, int stddev)
	{
		return filter.edgeDetection(blur.gaussianFilter(grey.greyScale(img)),true, false);
	}
}
 