package com.opencv.example.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
/**
 * Laplacian算子
 */
public class Laplacian {
	public static void main(String[] args) {
		try{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			
			// 将图像存入矩阵
			Mat src = Imgcodecs.imread("./images/dog.jpg");
			// 判断图像是否存在
			if(!src.empty()){
				// 克隆一个矩阵
				Mat dst = src.clone();
				// Laplacian处理
				Imgproc.Laplacian(src, dst, 2, 3, 1, 0, Core.BORDER_DEFAULT);
				// 保存图像
				Imgcodecs.imwrite("./images/Laplacian-finsh.jpg", dst);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}