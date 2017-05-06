package com.imfbp.rz.pub;

/**
 * DFS常量接口，存放所有静态变量
 * 
 * @author Xinggh
 *
 */
public interface IDFSConst {

	/**
	 * FastDFS文件标识
	 */
	public static final String FASTDFS_FILEFASTDFSID = "filefastdfsid";
	/**
	 * FastDFS文件分组标识
	 */
	public static final String FASTDFS_FILEFASTDFSGROUP = "filefastdfsgroup";
	/**
	 * FastDFS文件内容
	 */
	public static final String FASTDFS_CONTENT = "fastdfscontent";
	/**
	 * FastDFS文件访问URL
	 */
	public static final String FASTDFS_FILE_URL = "fastdfsfileurl";
	/**
	 * 分布式文件系统访问域名
	 */
	public static final String IDFS_DOMAIN_NAME = "idfs.domain.name";
	/**
	 * 上传文件线程数
	 */
	public static final String UPLOAD_FILE_THREAD_NUM = "uploan.file.thread.num";
	/**
	 * 默认上传文件线程数
	 */
	public static final Integer DEFAULT_UPLOAD_FILE_THREAD_NUM = 8;
	/**
	 * 文件信息key
	 */
	public static final String DFS_FILE_INFO = "dfsFileInfo";

	/**
	 * 文件名
	 */
	public static final String DFS_FILE_NAME = "dfsFileName";

}
