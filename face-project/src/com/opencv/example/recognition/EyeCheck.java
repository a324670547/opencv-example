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
 * 图片人眼检测
 */
public class EyeCheck {
	private static Mat dobj(Mat src){
		Mat dst=src.clone();
		
		CascadeClassifier detector=new CascadeClassifier("./data/haarcascades/haarcascade_eye.xml");
		
		MatOfRect objDetections=new MatOfRect();
		
		detector.detectMultiScale(dst, objDetections);
		// 检测有无眼睛
		if(objDetections.toArray().length<=0){
			return src;
		}
		// 根据眼睛数目画矩形
		for(Rect rect:objDetections.toArray()){
			Imgproc.rectangle(dst, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.width), new Scalar(0,0,255),2);
		}
		return dst;
	}
 
	public static void main(String[] args) {
		try{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			
			Mat src=Imgcodecs.imread("./images/face2.jpg");
			if(!src.empty()){
				Mat dst=dobj(src);
				Imgcodecs.imwrite("./images/eye-finish.jpg", dst);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}