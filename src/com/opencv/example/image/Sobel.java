package com.opencv.example.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
/**
* Sobel处理
*/
public class Sobel {
	public static void main(String[] args) {
		try{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			
			// 将图像存入矩阵
			Mat mat = Imgcodecs.imread("./images/dog.jpg");
			// 判断图像是否存在
			if(!mat.empty()){
				// 克隆一个矩阵
				Mat dst = mat.clone();
				// Sobel处理
				Imgproc.Sobel(mat,dst,-1,0,1);
				// 保存图像
				Imgcodecs.imwrite("./images/Sobel-finsh.jpg", dst);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
