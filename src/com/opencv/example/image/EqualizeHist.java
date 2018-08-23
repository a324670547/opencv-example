package com.opencv.example.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
/**
* 均衡化
*/
public class EqualizeHist {
	public static void main(String[] args) {
		try{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			
			// 将图像存入矩阵
			Mat mat = Imgcodecs.imread("./images/dog.jpg");
			// 判断图像是否存在
			if(!mat.empty()){
				// 克隆一个矩阵
				Mat dst = mat.clone();
				// 设置图像为灰色
				Imgproc.cvtColor(mat, dst, Imgproc.COLOR_BGR2GRAY);
				// 均衡化处理
				Imgproc.equalizeHist(dst, dst);
				// 保存图像
				Imgcodecs.imwrite("./images/EqualizeHist-finsh.jpg", dst);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}