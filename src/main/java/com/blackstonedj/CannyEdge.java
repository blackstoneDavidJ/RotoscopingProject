package com.blackstonedj;

import java.awt.image.BufferedImage;

public class CannyEdge 
{
	private EdgeDetector filter;
	private GreyScale grey;
	private NewGaussianBlur blur;
	private DoubleThreshold thresh;
	private boolean direction = false;
	private boolean thinned = false;
	
	//constructor taking in edge filter, greyscale filter, and gaussianblur kernel
	public CannyEdge(EdgeDetector filter, GreyScale grey, NewGaussianBlur blur, DoubleThreshold thresh, boolean direction, boolean thinned)
	{
		this.filter    = filter;
		this.grey 	   = grey;
		this.blur      = blur;
		this.direction = direction;
		this.thinned   = thinned;
		this.thresh    = thresh;
	}
	
	//edge detection with edge thinner
	public BufferedImage proccessor(BufferedImage img)
	{
		//img = grey.greyScale(img);
		//modder.save("applesGrey", img);
		img = blur.gaussianFilter(img);
		//modder.save("applesblur", img);
		img = filter.edgeDetection(img, direction, thinned);
		//modder.save("applesedgedirectionthinned", img);

		img = thresh.DoubleThresholder(img, filter.getEdgeVals());
		//modder.save("applesdoubleThresholder", img);

		img = thresh.hysterisis(img, filter.getMax());
		//modder.save("applesHysterisis", img);
		
		return img;
	}
}