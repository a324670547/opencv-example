package com.opencv.example.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
/**
 * 图像的腐蚀
 */
public class Erode {
	public static void main(String[] args) {
		try{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			
			// 将图像存入矩阵
			Mat mat = Imgcodecs.imread("./images/BrownBear.jpg");
			// 判断图像是否存在
			if(!mat.empty()){
				// 克隆一个矩阵
				Mat dst = mat.clone();
				// 图像腐蚀
				Imgproc.erode(mat,dst,new Mat());
				// 保存图像
				Imgcodecs.imwrite("./images/Erode-finsh.jpg", dst);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}