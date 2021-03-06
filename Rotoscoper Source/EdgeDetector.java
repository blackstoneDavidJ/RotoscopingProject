package com.blackstonedj;

import java.awt.image.BufferedImage;

public interface EdgeDetector 
{
	public BufferedImage edgeDetection(BufferedImage img, boolean direction, boolean magnitude);
	public int[][] getEdgeVals();
	public double getMax();
}
