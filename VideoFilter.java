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
	public VideoFilter(String path)
	{
		videoPath = path;
	}
	
	//opencv
	public void filter(CannyEdge canny, Combiner combine, Palettization palette, String path) throws IOException
	{
		Frame frame1;
		FFmpegFrameGrabber g = new FFmpegFrameGrabber(videoPath);
		Java2DFrameConverter f = new Java2DFrameConverter();
        FFmpegFrameRecorder recorder;
        ImageModder modder = new ImageModder();
		int counter = 0;
		try {
			g.start();
			//g.setVideoCodec(avcodec.AV_CODEC_ID_MP4ALS);
			System.out.println("array length: " +g.getLengthInFrames());
			recorder = new FFmpegFrameRecorder("resources/2min.mp4", g.getImageWidth(), g.getImageHeight());
			recorder.setVideoCodec(g.getVideoCodec());
			recorder.setVideoBitrate(g.getVideoBitrate());
			//recorder.setVideoCodecName("test");
			recorder.setFrameRate(g.getFrameRate());
			recorder.setFormat("mp4");
			recorder.start();
			while(counter < 3)
			{
				try 
				{
					frame1 = g.grabImage();
					BufferedImage img = f.convert(frame1);
					BufferedImage paletteImg = palette.runner(img, canny.proccessor(img));
					File output = new File("resources/frame_"+counter +".png");
					ImageIO.write(paletteImg, "png", output);
					recorder.record(f.getFrame(paletteImg));
					counter++;
					
					System.out.println("counter: " +counter);
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
