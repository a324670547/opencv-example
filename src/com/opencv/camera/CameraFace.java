package com.opencv.camera;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
/**
 * opencv启动摄像头
 */
public class CameraFace {
	// 窗体
	private JFrame frame;
	// 视频容器
	static JLabel label;
	// 视频循环开关
	static boolean flag=true;
	
	/**
	 * 使用opencv前必须先调用此方法
	 */
	static{ 
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	/**
	 * 构造方法，对象实例化执行初始化
	 */
	public CameraFace() {
		initialize();
	}
 
	/**
	 * 初始化窗口
	 */
	private void initialize() {
		// 新建JFrame窗体
		frame = new JFrame();
		// 设置框体大小为1400*900
		frame.setBounds(100, 100, 1400,900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// 新建JLbel承载视频
		label=new JLabel();
		// 设置视频区域大小为1400*900,主要设置值别超过摄像头分辨率大小
		label.setBounds(0, 0, 1400, 900);
		frame.add(label,BorderLayout.CENTER);
	}
	
	/**
	 * 将mat转换成图像
	 * @param mat
	 * @return
	 */
	private static BufferedImage mat2BI(Mat mat){
		int dataSize =mat.cols()*mat.rows()*(int)mat.elemSize();
		byte[] data=new byte[dataSize];
		mat.get(0, 0,data);
		int type=mat.channels()==1?
				BufferedImage.TYPE_BYTE_GRAY:BufferedImage.TYPE_3BYTE_BGR;
		if(type==BufferedImage.TYPE_3BYTE_BGR){
			for(int i=0;i<dataSize;i+=3){
				byte blue=data[i+0];
				data[i+0]=data[i+2];
				data[i+2]=blue;
			}
		}
		BufferedImage image=new BufferedImage(mat.cols(),mat.rows(),type);
		image.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);
		return image;
	}
	
    /**
     * opencv实现人脸识别
     * @param img
     */
    public static Mat detectFace(Mat img)
    {
        // 从配置文件lbpcascade_frontalface.xml中创建一个人脸识别器，该文件位于opencv安装目录中
        CascadeClassifier faceDetector = new CascadeClassifier("data/haarcascades/haarcascade_frontalface_alt.xml");
        // 在图片中检测人脸
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(img, faceDetections);
        Rect[] rects = faceDetections.toArray();
        // 根据人脸多少画矩形
        if(rects != null && rects.length >= 1){          
            for (Rect rect : rects) {  
                Imgproc.rectangle(img, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),  
                        new Scalar(0, 0, 255), 2);  
            } 
        }
        return img;
    }
 
	/**
	 * Main方法
	 */
	public static void main(String[] args) {
		// 启动窗口
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CameraFace window = new CameraFace();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		// 新建camera操作对象
		VideoCapture camera=new VideoCapture();
		/* 
		 * 打开摄像头
		 * open函数中的0代表当前计算机中索引为0的摄像头，如果你的计算机有多个摄像头，那么一次1,2,3……
		 *  - 打开本机摄像头camera.open(0);
		 *  - 打开远程摄像头camera.open("rtsp://username:password@10.8.110.121/Streaming/channels/1/");
		 */
		camera.open(0);
		// 判断摄像头是否被打开，如果打开失败则抛出异常
		if(!camera.isOpened()){
			throw new RuntimeException("Camera Start Error");
		}
		// 摄像头打开成功，则循环抓取帧输出
		Mat mat=new Mat();
		while(flag){
			// 读取1帧
			camera.read(mat);
			// 识别人脸
			detectFace(mat);
			// 将mat转换成BufferedImage
			BufferedImage img = mat2BI(mat);
			// 将img转换成ImageIcon
			ImageIcon icon =new ImageIcon(img);
			// 将这帧绑定到label上输出到界面
			label.setIcon(icon);
		}
	}

}