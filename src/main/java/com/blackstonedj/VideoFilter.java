package com.blackstonedj;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.Java2DFrameConverter;

public class VideoFilter 
{
	String videoPath;
	double frameRate;
	public VideoFilter(String path)
	{
		videoPath = path;
	}
	
	public void filter() throws IOException
	{
		Frame frame1;
		FFmpegFrameGrabber g = new FFmpegFrameGrabber(videoPath);
		Java2DFrameConverter f = new Java2DFrameConverter();
		GreyScale grey = new GreyScale();				
        FFmpegFrameRecorder recorder;

		int counter = 0;
		try {
			g.start();
			//g.setVideoCodec(avcodec.AV_CODEC_ID_MP4ALS);
			System.out.println("array length: " +g.getLengthInFrames());
			recorder = new FFmpegFrameRecorder("resources/movie.mp4", g.getImageWidth(), g.getImageHeight());
			recorder.setVideoCodec(g.getVideoCodec());
			recorder.setVideoBitrate(g.getVideoBitrate());
			//recorder.setVideoCodecName("test");
			recorder.setFrameRate(g.getFrameRate());
			recorder.setFormat("mp4");
			recorder.start();
			while(counter < g.getLengthInFrames())
			{
				try 
				{
					frame1 = g.grabImage();
					BufferedImage img = f.convert(frame1);
					img = grey.greyScale(img);	
					recorder.record(f.getFrame(img));
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
