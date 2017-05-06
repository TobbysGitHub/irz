package com.imfbp.rz.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import com.imfbp.rz.constant.RZConstants;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.platform.common.utils.StringUtil;

/**
 * @Title : Html2PDFUtil
 * @Description : 将html文件转换为PDF文件工具类
 * @Company :yonyouFintech
 * @author :Xinggh
 * @date : 2016年12月1日 上午11:00:37
 */
public final class Html2PDFUtils {

	private final static Logger logger = Logger.getLogger(Html2PDFUtils.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// String htmlFile = "d:/testhtmlPdf1111.html";
		String pdfFile = "d:/testhtmlPdf1111.pdf";

		long times = System.currentTimeMillis();
		System.out.println(" --------------- > start...");
		// buildPDF(htmlFile, pdfFile);
		readFile(pdfFile);
		System.out.println(" --------------- > end. use time(ms):"
				+ (System.currentTimeMillis() - times));
	}

	public final static void buildPDF(String htmlFilePath, String pdfFilePath) {
		try {
			FileInputStream ins = new FileInputStream(new File(htmlFilePath));
			InputStreamReader isr = new InputStreamReader(ins,
					RZConstants.CHARSET_UTF_8);
			Document document = new Document(PageSize.A4);
			// document.setMargins(0, 0, 0, 0);
			// pdf method
			FileOutputStream outputStream = new FileOutputStream(pdfFilePath);
			PdfWriter pdfwriter = PdfWriter.getInstance(document, outputStream);
			pdfwriter.setViewerPreferences(PdfWriter.HideToolbar);
			document.open();
			// XMLWorkerHelper.getInstance().parseXHtml(pdfwriter, document,
			// isr);
			HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
			htmlContext.charSet(Charset.forName(RZConstants.CHARSET_UTF_8));
			htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
			// final String dir = request.getSession().getServletContext()
			// .getRealPath("/");
			// 如果html中图片引用使用的是相对路径，需要设置图片的全路劲
			// htmlContext.setImageProvider(new AbstractImageProvider() {
			// public String getImageRootPath() {
			// return "c:/"; // web realpath for images.
			// }
			// });
			// htmlContext.setLinkProvider(new LinkProvider() {
			// public String getLinkRoot() {
			// return "D:/";
			// }
			// });
			CSSResolver cssResolver = XMLWorkerHelper.getInstance()
					.getDefaultCssResolver(true);
			Pipeline<?> pipeline = new CssResolverPipeline(cssResolver,
					new HtmlPipeline(htmlContext, new PdfWriterPipeline(
							document, pdfwriter)));
			XMLWorker worker = new XMLWorker(pipeline, true);
			XMLParser p = new XMLParser();
			p.addListener(worker);
			p.parse(isr);
			p.flush();
			document.close();
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("将HTML格式内容转换为PDF文件内容出现异常，异常信息：" + e.getMessage());
		}
	}

	/**
	 * 将Html文件转换为PDF文件，返回文件内容二进制值
	 * 
	 * @param htmlData
	 *            html文件内容
	 * @return
	 */
	public final static byte[] html2PDF(String htmlData) {
		FileOutputStream outputStream = null;
		InputStream ins = null;
		InputStreamReader isr = null;
		try {
			if (StringUtil.isEmpty(htmlData)) {
				return null;
			} else {
				byte[] buf = htmlData.getBytes(RZConstants.CHARSET_UTF_8);
				ins = new ByteArrayInputStream(buf);
				isr = new InputStreamReader(ins, "UTF-8");
				Document document = new Document(PageSize.A4);

				String resourcePath = Html2PDFUtils.class.getClassLoader()
						.getResource("").getPath();
				// 生成的零时文件
				String pdfFilePath = resourcePath + "/"
						+ RZConstants.TEMP_FILE_NAME + RZConstants.FILE_SPLIT
						+ RZConstants.FILE_TYPE_PDF;
				File file = new File(pdfFilePath);
				// if (file.isFile()) {
				// file.delete();
				// }
				outputStream = new FileOutputStream(file);
				// outputStream = new FileOutputStream(pdfFilePath);
				PdfWriter pdfwriter = PdfWriter.getInstance(document,
						outputStream);
				pdfwriter.setViewerPreferences(PdfWriter.HideToolbar);
				document.open();
				HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
				htmlContext.charSet(Charset.forName(RZConstants.CHARSET_UTF_8));
				htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
				// final String dir = request.getSession().getServletContext()
				// .getRealPath("/");
				// 如果html中图片引用使用的是相对路径，需要设置图片的全路劲
				// htmlContext.setImageProvider(new AbstractImageProvider() {
				// public String getImageRootPath() {
				// return "c:/"; // web realpath for images.
				// }
				// });
				// htmlContext.setLinkProvider(new LinkProvider() {
				// public String getLinkRoot() {
				// return "D:/";
				// }
				// });
				CSSResolver cssResolver = XMLWorkerHelper.getInstance()
						.getDefaultCssResolver(true);
				Pipeline<?> pipeline = new CssResolverPipeline(cssResolver,
						new HtmlPipeline(htmlContext, new PdfWriterPipeline(
								document, pdfwriter)));
				XMLWorker worker = new XMLWorker(pipeline, true);
				XMLParser p = new XMLParser();
				p.addListener(worker);
				p.parse(isr);
				p.flush();
//				byte[] byteData2= pdfwriter.getDirectContent().getInternalBuffer().getBuffer();
				 
//				byte[] byteData3 = pdfwriter.getDirectContent().toPdf(pdfwriter.getDirectContent().getPdfWriter());
				document.close();
				// 获取文件内容
				byte[] byteData = FileUtils.toByteArray(pdfFilePath);
				// FileOutputStream fos = new
				// FileOutputStream("d:/testhtmlPdf.pdf");
				// fos.write(byteData);
				// fos.close();
				// 删除原文件
				file.delete();
				// 返回文件信息
				return byteData;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("将HTML格式内容转换为PDF文件内容出现异常，异常信息：" + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
				if (ins != null) {
					ins.close();
				}
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e) {
				logger.error("将HTML格式内容转换为PDF文件内容出现异常，异常信息：" + e.getMessage());
			}
		}
		return null;
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
		new String(buffer.toString().getBytes("UTF-8"));
		byte[] byteData = FileUtils.file2Byte(docFilePath);
		FileOutputStream fos = new FileOutputStream("d:/testhtmlPdf.pdf");
		fos.write(byteData);
		fos.close();
		new String(byteData, "UTF-8");
		byteData = FileUtils.toByteArray2(docFilePath);
		new String(byteData, "UTF-8");
		return buffer.toString();
	}

}
