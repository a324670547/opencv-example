package com.opencv.example.recognition;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
/**
 * 图片人脸检测
 */
public class FaceCheck {
	private static Mat dobj(Mat src){
		Mat dst=src.clone();
		
		CascadeClassifier detector=new CascadeClassifier("./data/haarcascades/lbpcascade_frontalface.xml");
		
		MatOfRect objDetections=new MatOfRect();
		
		detector.detectMultiScale(dst, objDetections);
		// 检测有无人脸
		if(objDetections.toArray().length<=0){
			return src;
		}
		// 根据人脸数目画矩形
		for(Rect rect:objDetections.toArray()){
			Imgproc.rectangle(dst, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.width), new Scalar(0,0,255),2);
		}
		return dst;
	}
 
	public static void main(String[] args) {
		try{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			
			Mat src=Imgcodecs.imread("./images/face.jpg");
			if(!src.empty()){
				Mat dst=dobj(src);
				Imgcodecs.imwrite("./images/face-finish.jpg", dst);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}