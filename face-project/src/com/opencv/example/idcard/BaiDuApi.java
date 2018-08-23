package com.opencv.example.idcard;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class BaiDuApi {
	public static String ApiorcText(String filePath){
	    String APP_ID = "11664131";
	    String API_KEY = "btr3f9hWElXq5fmepbVatIyE";
	    String SECRET_KEY = "aHWv6ZXkcIg7z9GMB19vi2Chog38fLfa";
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");
        //options.put("access_token",AuthService.getAuth());
        
        // 调用接口
        //String path = "images/part2/2.jpg";
        JSONObject res = client.basicGeneral(filePath, options);
        return res.toString(2);
	}
	
	public static void main(String[] args) {
		System.out.println(ApiorcText("images/part2/3.jpg"));
	}
}
