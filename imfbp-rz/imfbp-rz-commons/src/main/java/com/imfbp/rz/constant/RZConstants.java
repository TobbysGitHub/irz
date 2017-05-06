package com.imfbp.rz.constant;

/**
 * @Title : 常量属性
 * @Description : 系统中使用的常量都会放在该类中，方便统一管理，包括数字、字符常量
 * @Company :yonyouFintech
 * @author :Xinggh
 * @date : 2016年11月25日 上午10:11:50
 */
public class RZConstants {

	/**
	 * \\$\\{.*?\\} 输入项匹配正则表达式
	 */
	// public final static String TEMPLATE_INPUT_REGULAR =
	// "\\[\\$\\#.*?\\#\\$\\]";
	public final static String TEMPLATE_INPUT_REGULAR = "\\$\\{.*?\\}";
	/**
	 * \\$\\{ 输入项匹配正则表达式首部
	 */
	// public final static String TEMPLATE_INPUT_REGULAR_BEFOR = "\\[\\$\\#";
	public final static String TEMPLATE_INPUT_REGULAR_BEFOR = "\\$\\{";
	/**
	 * \\} 输入项匹配正则表达式尾部
	 */
	// public final static String TEMPLATE_INPUT_REGULAR_END = "\\#\\$\\]";
	public final static String TEMPLATE_INPUT_REGULAR_END = "\\}";

	/**
	 * ${ 输入项首部
	 */
	// public final static String TEMPLATE_INPUT_BEFOR = "[$#";
	public final static String TEMPLATE_INPUT_BEFOR = "${";
	/**
	 * 输入项尾部
	 */
	// public final static String TEMPLATE_INPUT_END = "#$]";
	public final static String TEMPLATE_INPUT_END = "}";

	/**
	 * html html文件类型
	 */
	public final static String HTML_FILE_TYPE = "html";

	/**
	 * 模板预览时替换字符内容
	 */
	public final static String PREVIEW_REPLACE_CONTENT = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	/**
	 * htmlData html文件内容key
	 */
	public final static String HTML_FILE_DATA = "htmlData";
	/**
	 * htmlFileName 文件名称key
	 */
	public final static String HTML_FILE_NAME = "htmlFileName";
	/**
	 * '.' 文件名分隔符'.'
	 */
	public final static String FILE_SPLIT = ".";
	/**
	 * "\\." 字符串切分标识
	 */
	public final static String TEXT_STRING_SPLIT = "\\.";
	/**
	 * src ImageTag标签src属性标识
	 */
	public final static String IMAGETAG_SRC = "src";
	/**
	 * file:// ImageTag标签src属性值前缀
	 */
	public final static String IMAGETAG_SRC_FILE = "file://";
	/**
	 * "" 字段串空值
	 */
	public final static String EMPTY_CONTENT = "";
	/**
	 * " " 字段串空格
	 */
	public final static String SPACE_CONTENT = " ";
	/**
	 * html文件头文件部分
	 */
	public final static String HTML_FILE_HEAD = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" "
			+ "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> <html xmlns=\"http://www.w3.org/1999/xhtml\" "
			+ "xmlns:o=\"urn:schemas-microsoft-com:office:office\" "
			+ "xmlns:w=\"urn:schemas-microsoft-com:office:word\" "
			+ "xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\" "
			+ "xmlns=\"http://www.w3.org/TR/REC-html40\">  "
			+ "<head> "
			+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"
			+ "</head> " + "<body>";
	/**
	 * html文件文件尾部部分
	 */
	public final static String HTML_FILE_TAIL = " </body> </html>";

	/**
	 * tempName 模板临时文件名称-生成文件时使用
	 */
	public final static String TEMP_FILE_NAME = "tempName";
	/**
	 * doc文件类型
	 */
	public final static String FILE_TYPE_DOC = "doc";
	/**
	 * docx文件类型
	 */
	public final static String FILE_TYPE_DOCX = "docx";
	/**
	 * pdf文件类型
	 */
	public final static String FILE_TYPE_PDF = "pdf";
	/**
	 * fastdfscontent 分布式文件系统下载数据返回数据时取数key
	 */
	public final static String FASTDFS_CONTENT = "fastdfscontent";
	/**
	 * dfsFileInfo 分布式文件系统返回文件数据时取数key
	 */
	public final static String FASTDFS_FILEINFO = "dfsFileInfo";
	/**
	 * copy 复制模板时默认复制后模板文件名前缀
	 */
	public final static String TEMPLATE_FILE_NAME_HEAD = "copy";
	/**
	 * templateDefEntry 模板组合实体取数key
	 */
	public final static String TEMPLATE_DEF_ENTRY = "templateDefEntry";
	/**
	 * fileInfoList 附件文件信息列表
	 */
	public final static String FILE_INFO_LIST = "fileInfoList";

	/**
	 * fileURL 获取文件访问URL
	 */
	public final static String FILE_URL = "fileURL";

	/**
	 * V1.0 模板初始版本号
	 */
	public final static String TEMPLATE_INIT_VERSION = "V1.0";

	/**
	 * 5 模板预览时表体数据默认设置行数 5行
	 */
	public final static Integer TEMPLATE_TABLE_BODY_ROWS = 5;
	/**
	 * ',' 逗号
	 */
	public final static String POJO_BATCHID_SPLIT = ",";
	/**
	 * nodeTemplateInfoList 节点分配模板数据key
	 */
	public final static String NODE_TEMPLATE_DATA = "nodeTemplateInfoList";
	/**
	 * funMenuTree 功能节点数据key
	 */
	public final static String FUNCTION_MENU_TREE = "funMenuTree";
	/**
	 * tableData 元数据数据表信息key
	 */
	public final static String TABLE_DATA = "tableData";
	/**
	 * tableColumnsData 元数据数据表对应列信息key
	 */
	public final static String TABLE_COLUMNS_DATA = "tableColumnsData";

	public final static String YES = "Y";
	public final static String NO = "N";

	public final static String LIST = "list";
	public final static String BEAN = "bean";

	public final static String TABEL_LABEL_ZH = "label_zh";
	public final static String TABEL_LABEL_EN = "label_en";
	public final static String TABEL_COLUMNS = "columns";
	
	public final static String CHARSET_UTF_8="UTF-8";
	
}
