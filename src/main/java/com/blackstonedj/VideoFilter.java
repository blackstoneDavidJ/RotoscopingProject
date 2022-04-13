package com.blackstonedj;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;

public class VideoFilter 
{
	String videoPath;
	double frameRate;
	double frameBatch = 0;
	public VideoFilter(String path)
	{
		videoPath = path;
	}
	
	//opencv
	public void filter(CannyEdge canny, Combiner combine, Palettization palette, String path, int frameBatch) throws IOException
	{
		Frame frame1;
		FFmpegFrameGrabber g = new FFmpegFrameGrabber(videoPath);
		Java2DFrameConverter f = new Java2DFrameConverter();
        FFmpegFrameRecorder recorder;
		int counter = 0;
		try {
			g.start();
			System.out.println("array length: " +g.getLengthInVideoFrames());
			recorder = new FFmpegFrameRecorder("C:\\Users\\David\\eclipse-workspace\\imageProcessinhg\\resources\\video.mp4", g.getImageWidth(), g.getImageHeight());
			recorder.setVideoCodec(g.getVideoCodec());
			recorder.setVideoBitrate(g.getVideoBitrate());
			recorder.setFrameRate(g.getFrameRate());
			recorder.setFormat("mp4");
			recorder.start();
			this.frameBatch = frameBatch;
			BufferedImage startImg = null;
			BufferedImage currImg = null;
			BufferedImage paletteImg = null;
			BufferedImage cannyImg = null;
			BufferedImage combined = null;
			BufferedImage[] palletteBatch = new BufferedImage[frameBatch];
			BufferedImage[] normalBatch = new BufferedImage[frameBatch];
			while(counter < 20)
			{
				try 
				{
					int imageCounter = 0;
					int setCounter = 0;
					frame1 = g.grabImage();
					startImg = f.convert(frame1);
					
					int i = 0;
					while(i < frameBatch)
					{
						frame1 = g.grabImage();
						currImg = f.convert(frame1);
						palletteBatch[i] = currImg;
						normalBatch[i] = currImg;
						i++;
						//imageCounter++;
					}
										
					int index = 0;
					paletteImg = palette.runner(startImg);
					while(index < frameBatch)
					{
						palletteBatch[index] = paletteImg;
						cannyImg = canny.proccessor(normalBatch[index]);
						combined = combine.combineImages(cannyImg, palletteBatch[index]);
						recorder.record(f.getFrame(combined));
						combined = null;
						//setCounter++;
						index++;
					}
					
					System.out.println("frame: " +counter);
					counter += 10;
				} 
				
				catch (Exception e) 		
				{
					e.printStackTrace();
				}
			}
			
			recorder.stop();
			recorder.release();
			g.close();
		} 		
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}  		
		
		
	}
}
