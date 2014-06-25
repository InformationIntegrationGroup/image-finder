package com.isi.image.io;

import java.io.File;
import java.util.ArrayList;

public class ImageReader {
	
	ImageValidator imgValidator;
	ArrayList<String> imgPathList;
	
	public ImageReader(){
		
		imgValidator = new ImageValidator();
		imgPathList = new ArrayList<String>();
	}
	
	public ArrayList<String> ReadImageFiles(File root){
		
		
		if(root.isFile() && imgValidator.validate(root.getName())){
			imgPathList.add(root.getAbsolutePath());
		}
		
		if(root.isDirectory()) {
			
			File[] fileList = root.listFiles();
			
			if(fileList.length > 0) {
				
				for(File file : fileList) {
					
					ReadImageFiles(file);
				}
			}
		}
		
		return imgPathList;
	}

}
