package project;

import java.awt.image.BufferedImage;

public class CannyEdge 
{
	private EdgeDetector filter;
	private GreyScale grey;
	private GaussianBlur blur;
	
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
		blur.setStddev(stddev);
		return filter.edgeDetection(blur.gaussianFilter(grey.greyScale(img)));
	}
}
