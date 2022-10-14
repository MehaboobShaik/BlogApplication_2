package com.blogApp.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogApp.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String UploadImage(String path, MultipartFile file) throws IOException {
		 
		String fileName = file.getOriginalFilename();
		//image.png
		String filepath =path+File.separator+fileName;
		//filePath =src/main/resources/static/images\image.png
		File fileFolder = new File(path);
		if(!fileFolder.exists()) {
			fileFolder.mkdir();
		}
		Files.copy(file.getInputStream(),Paths.get(filepath));
		return fileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		
		 String filePath=path+File.separator+fileName;
		 InputStream fileInputStream = new FileInputStream(filePath);
		 System.out.println("inputStream:"+ fileInputStream);
		 return fileInputStream;
	}

}
