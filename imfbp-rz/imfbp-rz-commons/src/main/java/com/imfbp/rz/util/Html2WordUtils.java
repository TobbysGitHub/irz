package com.imfbp.rz.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.imfbp.rz.constant.RZConstants;
import com.platform.common.utils.StringUtil;

/**
 * @Title : html2Doc
 * @Description : html2Doc工具类，主要转换为2003格式的Word文档
 * @Company :yonyouFintech
 * @author :Xinggh
 * @date : 2016年12月1日 上午11:05:25
 */
public final class Html2WordUtils {
	/**
	 * 
	 * @param htmlFile
	 *            htmlFIle文件路径
	 * @param docFile
	 *            需要生成的doc文件路径
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public final static boolean writeWordFile(String htmlFile, String docFile)
			throws Exception {
		boolean flag = false;
		ByteArrayInputStream bais = null;
		FileOutputStream fos = null;
		try {
			if (!"".equals(docFile)) {
				File fileDir = new File(docFile);

				String content = readFile(htmlFile);
				byte b[] = content.getBytes();
				bais = new ByteArrayInputStream(b);
				POIFSFileSystem poifs = new POIFSFileSystem();
				DirectoryEntry directory = poifs.getRoot();
				DocumentEntry documentEntry = directory.createDocument(
						"WordDocument", bais);
				fos = new FileOutputStream(docFile);
				poifs.writeFilesystem(fos);
				bais.close();
				fos.close();
			}

		} catch (IOException e) {
			throw e;
		} finally {
			if (fos != null)
				fos.close();
			if (bais != null)
				bais.close();
		}
		return flag;
	}

	/**
	 * 读取html文件到字符串
	 * 
	 * @param docFilePath
	 *            docFilePath文件路径
	 * @return
	 * @throws Exception
	 */
	public static String readFile(String docFilePath) throws Exception {
		StringBuffer buffer = new StringBuffer("");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(docFilePath));
			buffer = new StringBuffer();
			while (br.ready())
				buffer.append((char) br.read());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				br.close();
		}
		new String(buffer.toString().getBytes(), "UTF-8");
		return buffer.toString();
	}

	/**
	 * 将html格式文件内容转换为word格式(.doc)文件内容
	 * 
	 * @param htmlData
	 *            html格式文件内容
	 * @return
	 * @throws Exception
	 */
	public final static byte[] html2WordDoc(String htmlData) throws Exception {
		if (StringUtil.isEmpty(htmlData)) {
			return null;
		} else {
			ByteArrayInputStream bais = null;
			FileOutputStream fos = null;
			try {
				// 生成的零时文件
				String docFilePath = RZConstants.TEMP_FILE_NAME
						+ RZConstants.FILE_SPLIT + RZConstants.FILE_TYPE_DOC;
//				docFilePath = "D:\\testhtmlPdf1111.doc";
				// htmlData = RZConstants.HTML_FILE_HEAD + htmlData;
				// htmlData += RZConstants.HTML_FILE_TAIL;
				byte b[] = htmlData.getBytes();
				bais = new ByteArrayInputStream(b);
				POIFSFileSystem poifs = new POIFSFileSystem();
				DirectoryEntry directory = poifs.getRoot();
				// DocumentEntry documentEntry = directory.createDocument(
				// "WordDocument", bais);
				directory.createDocument("WordDocument", bais);
				fos = new FileOutputStream(docFilePath);
				poifs.writeFilesystem(fos);
				bais.close();
				fos.close();
				// 获取文件内容
				byte[] byteData = FileUtils.file2Byte(docFilePath);
				// 删除原文件
				new File(docFilePath).delete();
				// 返回文件信息
				return byteData;
			} catch (IOException e) {
				throw e;
			} finally {
				if (fos != null)
					fos.close();
				if (bais != null)
					bais.close();
			}
		}
	}

	/**
	 * 将html格式文件内容转换为word格式(.doc)文件内容
	 * 
	 * @param htmlDataByte
	 *            byte[]格式文件内容
	 * @return
	 * @throws Exception
	 */
	public final static byte[] htmlArrayByte2WordDoc(byte[] htmlDataByte)
			throws Exception {
		if (htmlDataByte == null || htmlDataByte.length == 0) {
			return null;
		} else {
			ByteArrayInputStream bais = null;
			FileOutputStream fos = null;
			try {
				// 生成的零时文件
				String docFilePath = RZConstants.TEMP_FILE_NAME
						+ RZConstants.FILE_SPLIT + RZConstants.FILE_TYPE_DOC;
//				docFilePath = "D:\\testhtmlPdf1111.doc";
				bais = new ByteArrayInputStream(htmlDataByte);
				POIFSFileSystem poifs = new POIFSFileSystem();
				DirectoryEntry directory = poifs.getRoot();
				// DocumentEntry documentEntry = directory.createDocument(
				// "WordDocument", bais);
				directory.createDocument("WordDocument", bais);
				fos = new FileOutputStream(docFilePath);
				poifs.writeFilesystem(fos);
				bais.close();
				fos.close();
				// 获取文件内容
				// byte[] byteData = FileUtils.file2Byte(docFilePath);
				// 删除原文件
				 new File(docFilePath).delete();
				// 返回文件信息
				return htmlDataByte;
			} catch (IOException e) {
				throw e;
			} finally {
				if (fos != null)
					fos.close();
				if (bais != null)
					bais.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		String htmlFile = "D:\\testhtmlPdf1111.html";
		String docFile = "D:\\testhtmlPdf1111.doc";
		writeWordFile(htmlFile, docFile);
	}
}