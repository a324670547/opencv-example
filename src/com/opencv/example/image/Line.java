package com.opencv.example.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
/**
 * 画直线
 */
public class Line {
	public static void main(String[] args) {
		try{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			
			// 将图像存入矩阵
			Mat src = Imgcodecs.imread("images/white.jpg");
			// 判断图像是否存在
			if(!src.empty()){
				// 克隆一个矩阵
				Mat dst = src.clone();
				// 画颜色为蓝色的直线，从坐标(50，175)坐标到(300，175)
				Imgproc.line(dst,new Point(50,175),new Point(300,175),new Scalar(255,0,0));
				// 画颜色为绿色的绿线，从坐标(300，50)坐标到(50，300)，颜色为绿色
				Imgproc.line(dst,new Point(300,50),new Point(50,300),new Scalar(0,255,0),5);
				// 画颜色为红色的直线，从坐标(50，100)坐标到(200，50)，颜色为绿色
				Imgproc.line(dst,new Point(50,50),new Point(300,300),new Scalar(0,0,255),10);
				// 画颜色为黄色的直线，从坐标(50，100)坐标到(200，50)，颜色为绿色
				Imgproc.line(dst,new Point(175,50),new Point(175,300),new Scalar(0,255,255),10);
				// 保存图像
				Imgcodecs.imwrite("images/line.jpg", dst);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}