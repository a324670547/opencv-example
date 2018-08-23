package com.opencv.example.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
/**
 * 图像剪裁
 */
public class ClippingImage {
	
	public static void main(String[] args) {
		try{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			// 读取图像到矩阵
			Mat mat1=Imgcodecs.imread("./images/dog.jpg");
			// 判断图像文件是否存在
			if(!mat1.empty()){
				// 新建一个矩形
				Rect rect=new Rect(0,0,mat1.cols()-300,mat1.rows()-300);
				// 将矩阵mat1按照rect矩形大小裁剪，形成mat2矩阵
				Mat mat2=new Mat(mat1,rect);
				// 将矩阵mat2存储
				Imgcodecs.imwrite("./images/clipping-finish.jpg",mat2);
			}else{
				System.out.println("图像不存在！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
