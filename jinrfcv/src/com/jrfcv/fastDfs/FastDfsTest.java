package com.jrfcv.fastDfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.cleverframe.fastdfs.client.DefaultStorageClient;
import org.cleverframe.fastdfs.client.DefaultTrackerClient;
import org.cleverframe.fastdfs.client.StorageClient;
import org.cleverframe.fastdfs.client.TrackerClient;
import org.cleverframe.fastdfs.conn.DefaultCommandExecutor;
import org.cleverframe.fastdfs.model.StorageNode;
import org.cleverframe.fastdfs.model.StorageNodeInfo;
import org.cleverframe.fastdfs.model.StorePath;
import org.cleverframe.fastdfs.pool.ConnectionPool;
import org.cleverframe.fastdfs.pool.PooledConnectionFactory;
import org.cleverframe.fastdfs.protocol.storage.callback.DownloadFileWriter;

public class FastDfsTest {
	
	public static void upload() throws IOException{
		// 连接创建工厂（用户新建连接）
		PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(500, 500);
		// 连接池配置
		GenericKeyedObjectPoolConfig conf = new GenericKeyedObjectPoolConfig();
		conf.setMaxTotal(200);
		conf.setMaxTotalPerKey(200);
		conf.setMaxIdlePerKey(100);
		// 连接池
		ConnectionPool connectionPool = new ConnectionPool(pooledConnectionFactory, conf);
		Set<String> trackerSet = new HashSet<String>();
		trackerSet.add("192.168.10.128:22122");
		// 命令执行器
		DefaultCommandExecutor commandExecutor = new DefaultCommandExecutor(trackerSet, connectionPool);
		// Tracker客户端
		TrackerClient trackerClient = new DefaultTrackerClient(commandExecutor);
		// Storage客户端
		StorageClient storageClient = new DefaultStorageClient(commandExecutor, trackerClient);

		// Tracker客户端 - 获取Storage服务器节点信息
		StorageNode storageNode = trackerClient.getStorageNode("group1");

		// 获取文件信息
		StorageNodeInfo storageNodeInfo = trackerClient.getFetchStorage("group1", "M00/00/00/wKg4i1gxz_6AIPPsAAiCQSk77jI661.png");

		// 上传文件
		File file = new File("F:\\123456.txt");
		FileInputStream fileInputStream = FileUtils.openInputStream(file);
		StorePath storePath = storageClient.uploadFile("group1", fileInputStream, file.length(), "txt");

		// 下载文件
		DownloadFileWriter downloadFileWriter = new DownloadFileWriter("F:\\123.xlsx");
		String filePath = storageClient.downloadFile("group1", "M00/00/00/wKgKgFg02TaAY3mTADCUhuWQdRc53.xlsx", downloadFileWriter);

		// ... 更多操作

		// 关闭连接池
		connectionPool.close();
	}
	
	public static void main(String[] args) {
		
	}

}
