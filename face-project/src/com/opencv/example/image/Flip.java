package com.opencv.example.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
/**
 * 图像倒转、翻转
 */
public class Flip {
	public static void main(String[] args) {
			try{
				System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
				
				// 将图像存入矩阵
				Mat src = Imgcodecs.imread("images/dog.jpg");
				// 判断图像是否存在
				if(!src.empty()){
					// 克隆一个矩阵
					Mat dst = src.clone();
					// 108°倒转
					Core.flip(src,dst,0);
					Imgcodecs.imwrite("images/test-filp0.jpg", dst);
					// 翻转
					Core.flip(src,dst,1);
					Imgcodecs.imwrite("images/test-filp1.jpg", dst);
					// 180°倒转+翻转
					Core.flip(src,dst,-1);
					Imgcodecs.imwrite("images/test-filp2.jpg", dst);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
	}
}