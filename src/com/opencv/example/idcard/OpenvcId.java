package com.opencv.example.idcard;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class OpenvcId {
	static{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 输入源图
		Mat source = Imgcodecs.imread("./images/idcard.jpg", Imgcodecs.CV_LOAD_IMAGE_ANYCOLOR);
		// 获得截剪后的图
		Mat result = getIdRegion(source);
		Imgcodecs.imwrite("./images/part/result.png", result);
		// 获取关键信息区域
		List<Mat> matList = getInfo(result);
		for(Mat m:matList){
			// 保存结果图
			String filePath = "images/part2/result"+UUID.randomUUID()+".jpg";
			Imgcodecs.imwrite(filePath, m);
			System.out.println(BaiDuApi.ApiorcText(filePath));
		}
	  
	}
	
	public static Mat getIdRegion(Mat source){
		// 创建存储矩阵
		Mat destination = new Mat(source.rows(), source.cols(), source.type());
		// 将图转换成灰色
		Imgproc.cvtColor(source, destination, Imgproc.COLOR_RGB2GRAY);
		Imgcodecs.imwrite("./images/part/testId1.jpg", destination);
		// 二值化
		Imgproc.threshold(destination,destination,159,250,Imgproc.THRESH_BINARY);
		Imgcodecs.imwrite("./images/part/testId2.jpg", destination);
		// 取反色
		Imgproc.erode(destination,destination,new Mat());
		Imgcodecs.imwrite("./images/part/testId3.jpg", destination);
		// Canny算子 用于边缘检测
		int threshold = 100;
		Imgproc.Canny(destination, destination,10, 50);
		Imgcodecs.imwrite("./images/part/testId4.jpg", destination);
		Imgproc.Canny(destination, destination, threshold, threshold*3);
		Imgcodecs.imwrite("./images/part/testId5.jpg", destination);
		// 寻找图像中物体的轮廓
		ArrayList<MatOfPoint> contoursList = new ArrayList<MatOfPoint>();
		Mat hierarchy = new Mat();
		Imgproc.findContours(destination, contoursList, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
		// 设置边界大小
	    int borderWidth = 10;   
		// 绘制边界
		Imgproc.drawContours(source, contoursList,-1, new Scalar(255,0,0), 10);
		Imgcodecs.imwrite("./images/part/testId6.jpg", source);
	    // 寻找面积最大的边界框框
		double max = 0;
	    Rect rectMax = new Rect();
	    for (MatOfPoint contour : contoursList) {
	        Rect rec = Imgproc.boundingRect(contour);
	        double area = rec.width*rec.height;
	        if (area > max)
	        {
	            max = area;
	            rectMax = rec;
	        }                
	    }
	    // 设置多去除值
	    int val = 2;
	    // 去除边界
	    rectMax.x = rectMax.x + borderWidth + val;
	    rectMax.y = rectMax.y + borderWidth + val;
	    rectMax.width = rectMax.width- (2 * (borderWidth + val));
	    rectMax.height = rectMax.height-(2 * (borderWidth + val));
		// 复制原图
		Mat original = source.clone();
	    // 返回结果
	    Mat result = original.submat(rectMax);     
		return result;
	}
	
	public static List<Mat> getInfo(Mat source) {
		// 将原图转换成灰色图
		Imgproc.cvtColor(source, source, Imgproc.COLOR_BGR2GRAY);
		Imgcodecs.imwrite("images/part2/1.jpg", source);
		// 取反色
		// Core.bitwise_not(source, source);
		// Imgcodecs.imwrite("images/part2/2.jpg", source);
		// 图像二值化，控制阀值进行
		Imgproc.threshold(source, source, 115, 255, Imgproc.THRESH_BINARY);
		Imgcodecs.imwrite("images/part2/3.jpg", source);
		// 3*3图像腐蚀
		Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(1.5, 1.5));
		Imgproc.erode(source, source, element);
		Imgcodecs.imwrite("images/part2/4.jpg", source);
		// 循环从下往上循环像素点，截图身份证号码区域
		int yUp = 0;
		int yDown = 0;
		boolean flag = true;
		
		for (int y = source.height()-1; y > 0; y--) {
			// 阈值
			int count = 0;
			for (int x = 0; x < source.width(); x++) {
				// 得到该行像素点的值
				byte[] data = new byte[1];
				source.get(y, x, data);
				//System.err.println(data[0]);
				if (data[0] == 0) {
					count = count + 1;
				}
			}
			//System.out.println("count"+count);
			// 找到开始行
			if (count > 10 && flag) {
				System.out.println("yDown:"+y);
				yDown = y;
				flag = false;
			}
			if (count <= 0 && !flag) {
				System.out.println("yUp:"+y);
				yUp = y;
				break;
			}
		}
		System.out.println(yUp);
		System.out.println(yDown);
		// 往上、下多截取三行像素
		yDown = yDown + 3;
		yUp = yUp - 3;
		// 截取身份证区域
		Rect rectId = new Rect(0,yUp,source.width(),yDown - yUp);
		Mat resultId = source.submat(rectId); 
		// 循环上往下截取姓名区域
		flag = true;
		for (int y = 0; y < source.height(); y++) {
			// 阈值
			int count = 0;
			// 因为姓名最多占身份证一半，为了方便排除头像影响，把查找区域设置为1/2宽
			for (int x = 0; x < source.width()/2; x++) {
				// 得到该行像素点的值
				byte[] data = new byte[1];
				source.get(y, x, data);
				if (data[0] == 0) {
					count = count + 1;
				}
			}
			System.out.println(count);
			// 找到开始行
			if (count > 10 && flag) {
				System.out.println("yUp:"+y);
				yUp = y;
				flag = false;
			}
			if (count <= 0 && !flag) {
				System.out.println("yDown:"+y);
				yDown = y;
				break;
			}
		}
		// 往上、下多截取三行像素
		yDown = yDown + 3;
		yUp = yUp - 3;
		// 截取姓名区域
		Rect rectName = new Rect(0,yUp,source.width()/2,yDown - yUp);
		Mat resultName = source.submat(rectName); 
		// 新建集合,并将结果mat加入集合
		List<Mat> matList = new ArrayList<Mat>();
		matList.add(resultId);
		matList.add(resultName);
		// 返回
		return matList;
	}
	
}
