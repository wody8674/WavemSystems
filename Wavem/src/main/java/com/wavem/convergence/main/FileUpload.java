package com.wavem.convergence.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wavem.convergence.HomeController;

@Controller
public class FileUpload {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/fileUpload.do")
	public ModelAndView fileUpload(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView();
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "X-File-Name");
	 
		if (!"OPTIONS".equals(request.getMethod().toUpperCase())) {
			String fileName = request.getHeader("X-File-Name");
			
			System.out.println(fileName);
	  
			String ext = fileName.substring(fileName.lastIndexOf("."));
	  		//String uploadFileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + ext;
	  		String uploadFileName = fileName.substring(0, fileName.lastIndexOf(".")) + ext;
	  		
	  		System.out.println(uploadFileName);
	  		
	  		File uploadDir = new File("D:\\fileupload");
	  		
	  		System.out.println("D:\\fileupload");
	  		
	  		if(!uploadDir.exists()){
				uploadDir.mkdir();
			}
	  		
			File uploadFile = new File(uploadDir, uploadFileName);
	 
			InputStream in = null;
			OutputStream outFile = null;
			try {
				in = request.getInputStream();
				outFile = new FileOutputStream(uploadFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			byte[] buf = new byte[1024*2];
			int size = 0;
			
			try {
				while((size=in.read(buf)) != -1){
					outFile.write(buf, 0, size);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				outFile.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  
			String fileUrl ="D:\\fileupload" + uploadFileName;
			fileUrl = "<a href='"+fileUrl+"'>"+fileName+"</a>";  

			mav.addObject("message", fileUrl);
			mav.setViewName("viewData");
						
		}
		return mav;
	}
}
