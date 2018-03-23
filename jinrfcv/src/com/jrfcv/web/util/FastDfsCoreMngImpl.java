package com.jrfcv.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.cleverframe.fastdfs.client.StorageClient;
import org.cleverframe.fastdfs.client.TrackerClient;
import org.cleverframe.fastdfs.model.MateData;
import org.cleverframe.fastdfs.model.StorePath;
import org.cleverframe.fastdfs.protocol.storage.callback.DownloadFileWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

public class FastDfsCoreMngImpl implements FastDfsCoreMng {
	
	
//	@Resource(name="fastDfsStorageClient")
	StorageClient storageClient;
	
//	@Resource(name="fastDfsTrackerClient")
	TrackerClient trackerClient;
	
	@Override
	public StorePath upload(MultipartFile file) throws IOException {
		Set<MateData> data = new HashSet<MateData>();
		// 获取文件后缀名 
	    String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
		StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, data);
		System.out.println(storePath.getPath());
		return storePath;
	}

	@Override
	public StorePath uploadImageAndCrtThumbImage(MultipartFile file,Integer width,Integer height)
			throws IOException {
		Set<MateData> data = new HashSet<MateData>();
		// 获取文件后缀名 
	    String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
	    StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), ext, data,width,height);
	    System.out.println(storePath.getPath());
	    return storePath;
	}

	@Override
	public StorePath uploadSlaveFile(String groupName, String masterFilename,
			InputStream inputStream, long fileSize, String prefixName,
			String fileExtName) {
		 StorePath storePath = storageClient.uploadSlaveFile(groupName, masterFilename, inputStream, fileSize, prefixName, fileExtName);
		return storePath;
	}

	@Override
	public boolean deleteFile(String groupName, String path) {
		return storageClient.deleteFile(groupName, path);
	}

	@Override
	public boolean deleteFile(String filePath) {
		return storageClient.deleteFile(filePath);
	}

	@Override
	public String downloadFile(String filePath, String groupName, String path) {
		DownloadFileWriter downloadFileWriter = new DownloadFileWriter(filePath);
		return storageClient.downloadFile(groupName, path, downloadFileWriter);
	}

	@Override
	public String downloadFile(String filePath, String groupName, String path,
			long fileOffset, long fileSize) {
		DownloadFileWriter downloadFileWriter = new DownloadFileWriter(filePath);
		return storageClient.downloadFile(groupName, path, fileOffset, fileSize, downloadFileWriter);
	}

}
