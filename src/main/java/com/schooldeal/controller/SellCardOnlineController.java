package com.schooldeal.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.schooldeal.bean.InputObject;
import com.schooldeal.bean.OutputObject;
import com.schooldeal.service.GarbageService;
import com.schooldeal.util.PictureDisc;
import com.schooldeal.util.StringUtil;

@Controller
@RequestMapping("/GarbageController")
@SuppressWarnings("all")
public class SellCardOnlineController {

	private static Log logger = LogFactory.getLog(SellCardOnlineController.class);
	@Autowired
	private GarbageService garbageService;
	
	@ResponseBody
	@RequestMapping("/queryGarbage")
	public OutputObject querySvcNumList(HttpServletRequest request,HttpServletResponse response){
		OutputObject outputObject=new OutputObject();
		try {
			String reqString = request.getParameter("req_json");
			logger.error("/queryGarbage:"+reqString);
			InputObject reqObject1 = InputObject.stringToInputObject(reqString);
			Map params = reqObject1.getParams();
			if(StringUtil.isNull(String.valueOf(params.get("name")))){
				outputObject.setReturnCode("9999");
				outputObject.setReturnMessage("暂无数据");
				return outputObject;
			}
			outputObject = garbageService.queryGarbage(params);
		} catch (Exception e) {
			outputObject.setReturnCode("9999");
			outputObject.setReturnMessage("系统繁忙，请稍后重试");
		}
		
		return outputObject;
	}
	
	@ResponseBody
	@RequestMapping("/queryGarbageByPicture")
	public OutputObject queryGarbageByPicture(@RequestParam("file") MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		OutputObject outputObject=new OutputObject();
		try {
			InputStream in = file.getInputStream();
			System.out.println(file.getSize());
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024*1024];
			int n = 0;
		    while (-1 != (n = in.read(buffer))) {
		        output.write(buffer, 0, n);
		    }
		    output.close();
		    in.close();
			AipImageClassify client = PictureDisc.getclient();
			HashMap<String, String> options = new HashMap<String, String>();
			options.put("baike_num", "5");
			JSONObject res = client.advancedGeneral(buffer, new HashMap<String, String>());
	        System.out.println(res.toString(2));
	        outputObject = garbageService.queryGarbageByPicture(res);
		} catch (Exception e) {
			System.out.println(e);
			outputObject.setReturnCode("9999");
			outputObject.setReturnMessage("系统繁忙，请稍后重试");
		}
		
		return outputObject;
	}
	
	
}
