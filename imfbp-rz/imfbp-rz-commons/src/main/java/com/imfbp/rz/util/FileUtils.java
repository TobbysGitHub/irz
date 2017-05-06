package com.imfbp.rz.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import com.platform.common.utils.StringUtil;


/**
 * @Title : 文件操作工具类
 * @Description : 文件操作工具类
 * @Company :yonyouFintech
 * @author :Xinggh
 * @date : 2016年12月1日 上午11:31:17
 */
public final class FileUtils {
	/**
	 * 将文本文件中的内容读入到buffer中
	 * 
	 * @param buffer
	 *            buffer
	 * @param filePath
	 *            文件路径
	 * @throws IOException
	 *             异常
	 */
	public final static void readToBuffer(StringBuffer buffer, String filePath)
			throws IOException {
		if (StringUtil.isEmpty(filePath)) {
			return;
		}
		if (buffer == null) {
			buffer = new StringBuffer();
		}
		InputStream is = new FileInputStream(filePath);
		String line; // 用来保存每行读取的内容
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		line = reader.readLine(); // 读取第一行
		while (line != null) { // 如果 line 为空说明读完了
			buffer.append(line); // 将读到的内容添加到 buffer 中
			buffer.append("\n"); // 添加换行符
			line = reader.readLine(); // 读取下一行
		}
		reader.close();
		is.close();
	}

	/**
	 * 读取文本文件内容
	 * 
	 * @param filePath
	 *            文件所在路径
	 * @return 文本内容
	 * @throws IOException
	 *             异常
	 */
	public final static String readFile(String filePath) throws IOException {
		StringBuffer sb = new StringBuffer();
		readToBuffer(sb, filePath);
		return sb.toString();
	}

	@SuppressWarnings("resource")
	public final byte[] getContent(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length
				&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		fi.close();
		return buffer;
	}

	/**
	 * the traditional io way
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public final static byte[] toByteArray(String filename) throws IOException {

		File f = new File(filename);
		if (!f.exists()) {
			throw new FileNotFoundException(filename);
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}

	/**
	 * NIO way
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public final static byte[] toByteArray2(String filename) throws IOException {

		File f = new File(filename);
		if (!f.exists()) {
			throw new FileNotFoundException(filename);
		}

		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
				// System.out.println("reading");
			}
			return byteBuffer.array();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public final static byte[] file2Byte(String filename) throws IOException {

		FileChannel fc = null;
		try {
			fc = new RandomAccessFile(filename, "r").getChannel();
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,
					fc.size()).load();
			System.out.println(byteBuffer.isLoaded());
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				// System.out.println("remain");
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		String filename = "d:/testhtmlPdf1111.html";
		System.out.println(new String(file2Byte(filename)));
	}
}
