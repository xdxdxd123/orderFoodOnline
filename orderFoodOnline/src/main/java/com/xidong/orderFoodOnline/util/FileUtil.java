package com.xidong.orderFoodOnline.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
static public  String   fileSave(MultipartFile file) {
	String filePath=null;
	File fileTemp=null;
	String fileName=null;
	if(file!=null&&!file.isEmpty()) {
		 StringBuilder filePrefix=new StringBuilder( "d:/picture/");
		 DateFormat  df=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		 File file_=new  File(filePrefix.append(df.format(new Date())).toString());
		 if(!file_.exists()) {
			 file_.mkdirs();
		 }
		 fileName=file.getOriginalFilename();
		 int index= fileName.indexOf('.');
		String  suffix= fileName.substring(index);
		  filePath=filePrefix+"/"+UUIDUtil.getUUID()+suffix;
		 fileTemp= new File(filePath);
	}
	return filePath;
}
}
