package com.opencv.example.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
/**
 * 方框滤波
 */
public class BoxFilter {
	public static void main(String[] args) {
		try{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			
			// 将图像存入矩阵
			Mat mat = Imgcodecs.imread("./images/testid.jpg");
			// 判断图像是否存在
			if(!mat.empty()){
				// 克隆一个矩阵
				Mat dst = mat.clone();
				/**
				 * 图像深度是指存储每个像素所用的位数,也用于量度图像的色彩分辨率.
				 * 图像深度确定彩色图像的每个像素可能有的颜色数,或者确定灰度图像的
				 * 每个像素可能有的灰度级数.它决定了彩色图像中可出现的最多颜色数,或
				 * 灰度图像中的最大灰度等级.比如一幅单色图像,若每个象素有8位,则最大
				 * 灰度数目为2的8次方,即256.一幅彩色图像RGB3个分量的象素位数分别为
				 * 4,4,2,则最大颜色数目为2的4+4+2次方,即1024,就是说像素的深度为
				 * 10位,每个像素可以是1024种颜色中的一种.
					例如：一幅画的尺寸是1024*768，深度为16，则它的数据量为1.5M。
					计算如下：1024*768*16bit=(1024*768*16)/8字节=[(1024*768*16)/8]/1024KB={[(1024*768*16)/8]/1024}/1024MB。
				 */
				// 方框滤波器
				Imgproc.boxFilter(mat,dst,dst.depth(),new Size(3,3));
				// 保存图像
				Imgcodecs.imwrite("./images/testid-finsh.jpg", dst);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}