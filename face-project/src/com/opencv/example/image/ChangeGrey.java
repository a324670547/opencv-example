package com.opencv.example.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
/**
* 调整图像灰色
*/
public class ChangeGrey {
	public static void main(String[] args) {
		try{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			
			// 将图像存入矩阵
			Mat mat1 = Imgcodecs.imread("./images/dog.jpg");
			// 判断图像是否存在
			if(!mat1.empty()){
				// 克隆一个矩阵
				Mat mat2 = mat1.clone();
				// 设置图像为灰色
				Imgproc.cvtColor(mat1, mat2, Imgproc.COLOR_BGR2GRAY);
				// 保存图像
				Imgcodecs.imwrite("./images/gray-finsh.jpg", mat2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}