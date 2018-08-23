package com.opencv.example.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * 高斯模糊
 */
public class GaussianBlur {
	public static void main(String[] args) {
		try {
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

			// 将图像存入矩阵
			Mat src = Imgcodecs.imread("images/testid.jpg");
			// 判断图像是否存在
			if (!src.empty()) {
				// 克隆一个矩阵
				Mat dst = src.clone();
            	// 图像模糊化处理   
				Imgproc.GaussianBlur(src, dst, new Size(13, 13), 10, 10);
				Imgcodecs.imwrite("images/gaussianblur1.jpg", dst);
	            // 图像模糊化处理
				Imgproc.GaussianBlur(src, dst, new Size(31, 5), 80, 3);
				Imgcodecs.imwrite("images/gaussianblur2.jpg", dst);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}