package com.jrfcv.web.util;

import java.io.IOException;
import java.io.InputStream;

import org.cleverframe.fastdfs.model.StorePath;
import org.springframework.web.multipart.MultipartFile;

/**
 * 开发公司：北京金瑞帆科技有限公司
 *
 * 版权：北京金瑞帆科技有限公司
 * 
 * @Description: TODO()
 *
 * 区分　责任人　日期　　　　说明
 *
 * 创建　pj　2018年3月15日　
 *
 * @author pj
 *
 * @version 1.0, 2018年3月15日
*/
public interface FastDfsCoreMng {
	
	/**
	 * @Title: upload
	 * @Description: TODO(文件上传)
	 * @author: pj
	 * @date 2018年3月15日 上午10:23:32
	 * @param file
	 * @return
	 * @throws IOException
	 * @return: StorePath
	 */
	public StorePath upload(MultipartFile file) throws IOException;
	
	/**
	 * @Title: uploadImageAndCrtThumbImage
	 * @Description: TODO(文件上传及自动生成缩略图)
	 * @author: pj
	 * @date 2018年3月15日 上午10:23:34
	 * @param file
	 * @return
	 * @throws IOException
	 * @return: StorePath
	 */
	public StorePath uploadImageAndCrtThumbImage(MultipartFile file,Integer width,Integer height) throws IOException;
	
	/**
	 * @Title: uploadSlaveFile
	 * @Description: TODO(上传从文件,用于图片缩略图上传)
	 * @author: pj
	 * @date 2018年3月15日 上午10:09:17
	 * @param groupName 组名称
	 * @param masterFilename 主文件路径
	 * @param inputStream 从文件输入流
	 * @param fileSize 从文件大小
	 * @param prefixName 从文件前缀
	 * @param fileExtName 主文件扩展名
	 * @return: StorePath 文件存储路径
	 */
	public StorePath uploadSlaveFile(String groupName, String masterFilename, InputStream inputStream, long fileSize, String prefixName, String fileExtName);
	
	/**
	 * @Title: deleteFile
	 * @Description: TODO(删除文件)
	 * @author: pj
	 * @date 2018年3月15日 上午10:24:31
	 * @param groupName 组名称
	 * @param path 主文件路径
	 * @return
	 * @return: boolean
	 */
	public boolean deleteFile(String groupName, String path);
    
	/**
	 * @Title: deleteFile
	 * @Description: TODO(删除文件)
	 * @author: pj
	 * @date 2018年3月15日 上午10:24:55
	 * @param filePath 文件路径(groupName/path)
	 * @return
	 * @return: boolean
	 */
	public boolean deleteFile(String filePath); 
	
	/**
     * 下载整个文件
     * @param filePath 下载文件路径
     * @param groupName 组名称
     * @param path      主文件路径
     */
	public String downloadFile(String filePath,String groupName, String path);

    /**
     * 下载文件片段(断点续传)
     * @param filePath 下载文件路径
     * @param groupName  组名称
     * @param path       主文件路径
     * @param fileOffset 开始位置
     * @param fileSize   文件大小(经过测试好像这个参数值只能是“0”)
     */
	public String downloadFile(String filePath,String groupName, String path, long fileOffset, long fileSize);
	
	

}
