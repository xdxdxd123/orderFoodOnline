package com.xidong.orderFoodOnline.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
static public  String   fileSave(MultipartFile file) throws IllegalStateException, IOException {
	String filePath=null;
	File fileTemp=null;
	String fileName=null;
	if(file!=null&&!file.isEmpty()) {
		 StringBuilder filePrefix=new StringBuilder( "d:/picture/");
		 DateFormat  df=new SimpleDateFormat("yyyy-MM-dd");
		 File file_=new  File(filePrefix.append(df.format(new Date())).toString());
		 if(!file_.exists()) {
			 file_.mkdirs();
		 }
		 fileName=file.getOriginalFilename();
		 int index= fileName.indexOf('.');
		String  suffix= fileName.substring(index);
		String fileSuffix="/"+UUIDUtil.getUUID()+suffix;
		  filePath=filePrefix+fileSuffix;
		 fileTemp= new File(filePath);
		 file.transferTo(fileTemp);
	}
	return filePath;
}
}
