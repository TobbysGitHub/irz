package com.imfbp.rz.service.template;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.pub.SuperBean;
import com.imfbp.rz.domain.template.BaseSystemInfo;
import com.imfbp.rz.domain.template.NodeTemplateEntry;
import com.imfbp.rz.domain.template.TemplateDefEntry;
import com.imfbp.rz.domain.templatedef.TemplateDef;
import com.imfbp.rz.domain.templatedef.query.TemplateDefQuery;
import com.imfbp.rz.domain.templatenodedef.query.TemplateNodeDefQuery;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

/**
 * 模板服务接口
 * 
 * @author Xinggh
 * @version 2016-11-24
 *
 */
public interface ITemplateService {

	/**
	 * 保存模板数据
	 * 
	 * @param templateDefEntry
	 *            模板定义组合实体-包含模板数据、模板输入项数据、模板内容
	 * @return result.get(RZConstants.TEMPLATE_DEF_ENTRY)获取模板定义组合实体
	 * @throws Exception
	 */
	public Result saveTemplatData(TemplateDefEntry templateDefEntry)
			throws Exception;

	/**
	 * 根据模板id获取模板数据
	 * 
	 * @param templateId
	 *            模板id
	 * @return result.get(RZConstants.TEMPLATE_DEF_ENTRY)获取模板定义组合实体
	 * @throws Exception
	 */
	public Result getTemplateDataById(String templateId) throws Exception;

	/**
	 * 模板预览
	 * 
	 * @param htmlData
	 *            模板html数据
	 * @return Result.get(RZConstants.HTML_FILE_DATA)获取需要显示的文件数据
	 * @throws Exception
	 */
	public Result previewTemplatData(String htmlData) throws Exception;

	/**
	 * 导出模板数据
	 * 
	 * @param templateId
	 *            模板id
	 * @param exportType
	 *            导出文件类型( fileType生成的模板类型,默认doc,支持doc
	 *            、docx、pdf具体可以参考枚举类FileTypeEnum)
	 * @return map.get(RZConstants.HTML_FILE_DATA)获取文件二进制数据；map.get(RZConstants
	 *         .HTML_FILE_NAME)获取文件名
	 * @throws Exception
	 */
	public Map<String, Object> exportTemplateData(String templateId,
			String exportType) throws Exception;

	/**
	 * 复制模板
	 * 
	 * @param templateId
	 *            模板id
	 * @param templateName
	 *            模板复制后的名称
	 * @param baseSystemInfo
	 *            复制文件时的公共信息
	 * @return result.get(RZConstants.TEMPLATE_DEF_ENTRY)获取模板定义组合实体
	 * @throws Exception
	 */
	public Result copyTemplateData(String templateId, String templateName,
			BaseSystemInfo baseSystemInfo) throws Exception;

	/**
	 * 单据文档预览
	 * 
	 * @param nodeCode
	 *            功能节点编码
	 * @param billDataMap
	 *            单据数据,如果一个单据是主子表，则使用：billDataMap.put(t1,list);t1代表表名，
	 *            list代表对应的数据集合（主表数据该集合只有一个元素）,
	 * @param nodeTemplateId
	 *            节点分配模板id
	 * @return Result.get(RZConstants.HTML_FILE_DATA)获取需要显示的文件数据
	 * @throws Exception
	 */
	public Result previewBillDoc(String nodeCode,
			Map<String, List<SuperBean>> billDataMap, String nodeTemplateId)
			throws Exception;

	/**
	 * 生成单据对应的文档附件
	 * 
	 * @param nodeCode
	 *            功能节点编码
	 * @param billId
	 *            单据id
	 * @param mainBillDataMap
	 *            单据主表数据
	 * @param bodyBillDataMap
	 *            单据表体数据
	 * @param nodeTemplateId
	 *            节点分配模板id,如果nodeTemplateId为空，则默认生成功能节点编码nodeCode下该单据所有模板的文档附件
	 * @param baseSystemInfo
	 *            Controller层的公共信息，租户id，系统编码，人员id，文件类型(
	 *            fileType生成的文档类型,默认pdf,支持doc 、docx、pdf具体可以参考枚举类FileTypeEnum)
	 * @return
	 * @throws Exception
	 */
	public Result createBillDoc(String nodeCode, String billId,
			Map<String, Object> mainBillDataMap,
			Map<String, List<? extends SuperBean>> bodyBillDataMap,
			String nodeTemplateId, BaseSystemInfo baseSystemInfo)
			throws Exception;

	/**
	 * 导出单据文档
	 * 
	 * @param billFileId
	 *            单据文档文件id
	 * @param exportType
	 *            导出类型默认pdf可选doc
	 * @return result.get(RZConstants.HTML_FILE_DATA)获取文档byte[]类型文件内容;
	 *         result.get(RZConstants.HTML_FILE_NAME)获取文档文件名称
	 * @throws Exception
	 */
	public Result exportBillDataDoc(String billFileId, String exportType)
			throws Exception;

	/**
	 * 获取模板文件访问URL
	 * 
	 * @param templateFileId
	 *            文件服务器上模板文件id
	 * @return result.get(RZConstants.FILE_URL)获取文件访问URL
	 * @throws Exception
	 */
	public Result getFileUrl(String templateFileId) throws Exception;

	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * 
	 * @param batchIds
	 *            模板id集合
	 * @return
	 */
	Result deleteTemplateDefByBatchId(List<String> batchIds) throws Exception;

	public Result saveNodeTemplateData(NodeTemplateEntry nodeTemplateEntry,
			BaseSystemInfo baseSystemInfo) throws Exception;

	public Result delNodeTemplateData(List<String> nodeTemplateIds)
			throws Exception;

	/**
	 * 获取指定节点分配的模板信息
	 * 
	 * @param nodeCode
	 *            功能节点编码
	 * @param tenantId
	 *            租户id
	 * @return 
	 *         result.get(RZConstants.NODE_TEMPLATE_DATA)获取List<NodeTemplateInfo>
	 *         类型结果集数据
	 * @throws Exception
	 */
	public Result getNodeTemplateData(String nodeCode, String tenantId)
			throws Exception;

	/**
	 * 更新节点分配的模板启用状态
	 * 
	 * @param nodeTemplateId
	 *            节点模板id
	 * @param useStatus
	 *            使用状态
	 * @return
	 * @throws Exception
	 */
	public Result updateNodeTemplateStatus(String nodeTemplateId,
			Integer useStatus) throws Exception;

	/**
	 * 获取指定节点元数据
	 * 
	 * @param sysId
	 *            系统id
	 * @param nodeCode
	 *            功能节点编码
	 * @return
	 * @throws Exception
	 */
	public Result getMetadata(String sysId, String nodeCode) throws Exception;

	/**
	 * 更新条件查询当前节点未分配的模板信息
	 * 
	 * @param templateDefQuery
	 * @return
	 * @throws Exception
	 */
	public GridResult<TemplateDef> getNoDisTemplateDefByPage(
			TemplateDefQuery templateDefQuery) throws Exception;

	/**
	 * 根据节点模板id查询模业务板规则数据
	 * 
	 * @param templateNodeDefQuery
	 * @return
	 * @throws Exception
	 */
	public Result getRulesDataByTemplateNodeId(
			TemplateNodeDefQuery templateNodeDefQuery) throws Exception;

	public Result getTemplatBpaData(String templateId, String htmlData)
			throws Exception;

	public Result getPublicMetadata(String sysId) throws Exception;

	public Result updateTemplateDefStatus(String templateId,
			Integer templateStatus) throws Exception;

}
