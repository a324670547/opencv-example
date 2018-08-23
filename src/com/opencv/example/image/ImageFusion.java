package com.opencv.example.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
/**
 * 多图像融合
 */
public class ImageFusion {
	public static void main(String[] args) {
		try{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			
			// 将图像存入矩阵
			Mat mat1 = Imgcodecs.imread("./images/dog.jpg");
			Mat mat2 = Imgcodecs.imread("./images/bg2.jpg");
			// 判断图像是否存在
			if(!mat1.empty()&&!mat2.empty()){
				// 图像1，掩码图像,处理后的图像
				Mat mat3 = mat1.clone();
				Core.bitwise_and(mat1,mat2,mat3);
				// 保存图像
				Imgcodecs.imwrite("./images/imwrite-finish.jpg", mat3);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}