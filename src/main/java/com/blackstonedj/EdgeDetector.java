package project;

import java.awt.image.BufferedImage;

public interface EdgeDetector 
{
	public BufferedImage edgeDetection(BufferedImage img, boolean direction);
}
