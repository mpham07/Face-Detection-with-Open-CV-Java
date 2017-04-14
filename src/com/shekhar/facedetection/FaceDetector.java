package com.shekhar.facedetection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoWriter;

public class FaceDetector {

	static FaceDetector instance = new FaceDetector();
	
	final String pathMainFolder = "/Users/mac/Desktop/";
	final String pathFaceDetection = pathMainFolder + "FaceDetection/";
	final String pathVideoFileOutputPath = pathFaceDetection + "video_output.avi";
	final String pathImageNames = pathFaceDetection + "all_file.txt";
	final String pathOriginalImages = pathFaceDetection + "images/";
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		VideoWriter videoWriter = instance.videoProcedure();
		if ( videoWriter != null) {
			instance.detectProcedure(videoWriter);
		}
	}

	// ******** Convert To Video *******
	VideoWriter videoProcedure() {
		VideoWriter videoWriter = new VideoWriter();
		
		double width = 800;
		double height = 600;
		Size size = new Size(width, height);
		
		int codec = VideoWriter.fourcc('M','P','4','V');
		
		double fps = 25.0;
		
		File f = new File(pathVideoFileOutputPath);
		if(f.exists() && !f.isDirectory())  {
		   f.delete();
		}
		
		videoWriter.open(pathVideoFileOutputPath, codec, fps, size, true);
		
		if (!videoWriter.isOpened()) {
			System.out.println("Cannot open file " + pathVideoFileOutputPath);
			return null;
		}
		
		return videoWriter;
	}

	// ******** Detect Procedure *******
	ArrayList<String> readFiles() {
		ArrayList<String> fileNames = new ArrayList<String>();

		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(pathImageNames);
			br = new BufferedReader(fr);

			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				fileNames.add(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		return fileNames;
	}

	void detectProcedure(VideoWriter videoWriter) {
		
		System.out.println("\nRunning FaceDetector");

		ArrayList<String> fileNames = readFiles();

		detectSeveralFaces(fileNames, videoWriter);
	}

	void detectSeveralFaces(ArrayList<String> images, VideoWriter videoWriter) {
		int total = images.size();
		int i = 0;
		int idNum = 0;
		for (String originalFileName : images) {
			
			CascadeClassifier faceDetector = new CascadeClassifier(
					FaceDetector.class.getResource("haarcascade_frontalface_alt.xml").getPath());

			Mat image = Imgcodecs.imread(pathOriginalImages + originalFileName);
			
			MatOfRect faceDetections = new MatOfRect();
			faceDetector.detectMultiScale(image, faceDetections);


			for (Rect rect : faceDetections.toArray()) {
				idNum++;
				Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
						new Scalar(0, 255, 0), 2);
				
				Imgproc.putText(image, "#" + idNum, new Point(rect.x, rect.y + rect.height + 20), 1,1.2, new Scalar( 0,0,255));
				
				videoWriter.write(image);
			}
			
			// Write the Mat to an image file and save it into disk
			// String filename = "imageWithFaces/" + originalFileName;
			// Imgcodecs.imwrite(filename, image);
			
			i++;

			int percent = (int) (((double) i / (double) total) * 100);
			System.out.println("Processing... " + percent + "%");
		}
		
		videoWriter.release();
		System.out.println("Finished!");
	}
}