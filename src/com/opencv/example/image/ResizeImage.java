package com.opencv.example.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
/**
 * 图像大小调整
 */
public class ResizeImage {
	public static void main(String[] args) {
		try{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			// 将图像存入矩阵
			Mat mat1 = Imgcodecs.imread("./images/dog.jpg");
			// 判断图像是否存在
			if(!mat1.empty()){
				// 克隆一个矩阵
				Mat mat2 = mat1.clone();
				// 设置图像调整参数
				float scale=0.5f;
				float width=mat1.width();
				float height=mat1.height();
				Size size = new Size(width*scale,height*scale);
				// 调整图像缩放大小
				Imgproc.resize(mat1, mat2, size);
				Imgcodecs.imwrite("./images/ResizeImage-finsh.jpg", mat2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}