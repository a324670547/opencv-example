package com.opencv.example.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
/**
 * 图像模糊化处理
 */
public class Blur {
	 
	public static void main(String[] args) {
		try{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			
			// 将图像存入矩阵
			Mat mat = Imgcodecs.imread("./images/testid.jpg");
			// 判断图像是否存在
			if(!mat.empty()){
				// 克隆一个矩阵
				Mat dst = mat.clone();
				// 图像模糊化处理
				Imgproc.blur(mat,dst,new Size(15,15));
				// 保存图像
				Imgcodecs.imwrite("./images/Blur-finish.jpg", dst);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}