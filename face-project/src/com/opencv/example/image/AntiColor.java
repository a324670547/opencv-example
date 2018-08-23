package com.opencv.example.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
/**
 * 去反色
 */
public class AntiColor {
	public static void main(String[] args) {
		try{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			
			// 将图像存入矩阵
			Mat mat1 = Imgcodecs.imread("./images/dog.jpg");
			// 判断图像是否存在
			if(!mat1.empty()){
				// 克隆一个矩阵
				Mat mat2 = mat1.clone();
				// 取反色
				Core.bitwise_not(mat2, mat2, new Mat());
				// 保存图像
				Imgcodecs.imwrite("./images/bitwise_not-finsh.jpg", mat2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}