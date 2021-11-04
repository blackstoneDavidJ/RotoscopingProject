package com.blackstonedj;

import java.awt.image.BufferedImage;

public class CannyEdge 
{
	public EdgeDetector filter;
	public GreyScale grey;
	public GaussianBlur blur;
	public boolean direction = false;
	public boolean thinned = false;
	
	//constructor taking in edge filter, greyscale filter, and gaussianblur kernel
	public CannyEdge(EdgeDetector filter, GreyScale grey, GaussianBlur blur, boolean direction, boolean thinned)
	{
		this.filter = filter;
		this.grey = grey;
		this.blur = blur;
		this.direction = direction;
		this.thinned = thinned;
	}
	
	//edge detection with edge thinner
	public BufferedImage edgeDetector(BufferedImage img)
	{
		return filter.edgeDetection(blur.gaussianFilter(grey.greyScale(img)),direction,thinned);
	}
}
 