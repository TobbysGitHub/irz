package com.imfbp.rz.service.template.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifbp.boss.rpc.metadata.domain.RpcMetadata;
import com.ifbp.boss.rpc.metadata.service.BossMetadataRpcService;
import com.imfbp.fastdfs.rpc.api.fastdfs.domain.RpcDfsFileInfo;
import com.imfbp.fastdfs.rpc.api.fastdfs.domain.query.RpcDfsFileInfoQuery;
import com.imfbp.fastdfs.rpc.api.fastdfs.service.IImfbpFastDFSRpcService;
import com.imfbp.rz.constant.RZConstants;
import com.imfbp.rz.dao.pubfileinfo.PubFileinfoDao;
import com.imfbp.rz.domain.pub.SuperBean;
import com.imfbp.rz.domain.pubfileinfo.PubFileinfo;
import com.imfbp.rz.domain.pubfileinfo.query.PubFileinfoQuery;
import com.imfbp.rz.domain.template.BaseSystemInfo;
import com.imfbp.rz.domain.template.NodeTemplateEntry;
import com.imfbp.rz.domain.template.NodeTemplateInfo;
import com.imfbp.rz.domain.template.TableColumnsEntry;
import com.imfbp.rz.domain.template.TableEntry;
import com.imfbp.rz.domain.template.TemplateDefEntry;
import com.imfbp.rz.domain.templatedef.TemplateDef;
import com.imfbp.rz.domain.templatedef.query.TemplateDefQuery;
import com.imfbp.rz.domain.templateinputdef.TemplateInputDef;
import com.imfbp.rz.domain.templateinputdef.query.TemplateInputDefQuery;
import com.imfbp.rz.domain.templatenodedef.TemplateNodeDef;
import com.imfbp.rz.domain.templatenodedef.query.TemplateNodeDefQuery;
import com.imfbp.rz.domain.templaterulesdef.TemplateRulesDef;
import com.imfbp.rz.domain.templaterulesdef.query.TemplateRulesDefQuery;
import com.imfbp.rz.domain.templateversion.TemplateVersion;
import com.imfbp.rz.enums.FileTypeEnum;
import com.imfbp.rz.enums.TemplateStatusEnum;
import com.imfbp.rz.service.billno.BillnoService;
import com.imfbp.rz.service.template.ITemplateService;
import com.imfbp.rz.service.template.nodetable.BasePublicService;
import com.imfbp.rz.service.templatedef.TemplateDefService;
import com.imfbp.rz.service.templateinputdef.TemplateInputDefService;
import com.imfbp.rz.service.templatenodedef.TemplateNodeDefService;
import com.imfbp.rz.service.templaterulesdef.TemplateRulesDefService;
import com.imfbp.rz.service.templateversion.TemplateVersionService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.Html2PDFUtils;
import com.imfbp.rz.util.Html2WordUtils;
import com.imfbp.rz.util.RZVersionUtils;
import com.imfbp.rz.util.RegularUtils;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

/**
 * @Title :模板服务接口实现
 * @Description :提供保存模板、模板预览、模板分配与规则定义等接口服务
 * @Company :yonyouFintech
 * @author :Xinggh
 * @date : 2016年11月25日 上午10:16:35
 */
@Component("iTemplateService")
public class TemplateServiceImpl implements ITemplateService {

	private final static Logger logger = Logger
			.getLogger(TemplateServiceImpl.class);

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;

	@Autowired
	private IImfbpFastDFSRpcService iImfbpFastDFSRpcService;

	@Autowired
	private TemplateDefService templateDefService;

	@Autowired
	private TemplateInputDefService templateInputDefService;

	@Autowired
	private TemplateRulesDefService templateRulesDefService;

	@Autowired
	private TemplateNodeDefService templateNodeDefService;

	@Autowired
	private TemplateVersionService templateVersionService;

	@Autowired
	private PubFileinfoDao pubFileinfoDao;

	@Autowired
	private BossMetadataRpcService bossMetadataRpcService;

	@Autowired
	private BasePublicService basePublicService;

	@Autowired
	private BillnoService billnoService;

	@Override
	public Result saveNodeTemplateData(NodeTemplateEntry nodeTemplateEntry,
			BaseSystemInfo baseSystemInfo) throws Exception {
		// TODO Auto-generated method stub
		Result result = new Result();
		result.setSuccess(false);
		checkNodeTemplate(nodeTemplateEntry);
		String nodeCode = nodeTemplateEntry.getNodeCode();
		List<String> addIds = nodeTemplateEntry.getAddTemplateIds();
		if (addIds != null && addIds.size() > 0) {
			List<TemplateNodeDef> addTemplateNodeDefs = new ArrayList<TemplateNodeDef>();
			for (int i = 0; i < addIds.size(); i++) {
				TemplateNodeDef tnd = new TemplateNodeDef();
				tnd.setNodeCode(nodeCode);
				tnd.setTemplateId(addIds.get(i));
				tnd.setCreatedate(DateUtil.getCurDateStr());
				tnd.setCreator(baseSystemInfo.getUserId());
				tnd.setTenantId(baseSystemInfo.getTenantId());
				tnd.setCreatedtime(DateUtil.getTs());
				// 设置默认已启用
				tnd.setUseStatus(TemplateStatusEnum.USED.getIndex());
				addTemplateNodeDefs.add(tnd);
			}
			// 持久化数据
			templateNodeDefService
					.insertBatchTemplateNodeDef(addTemplateNodeDefs);
			// 更新模板未已分配状态
			for (String templateId : addIds) {
				TemplateDef templateDef = new TemplateDef();
				templateDef.setId(templateId);
				templateDef.setTemplateStatus(TemplateStatusEnum.DISTRIBUTED
						.getIndex());
				templateDefService.updateTemplateDefById(templateDef);
			}
		}

		// 如果有必要可以返回当前节点的所有模板数据
		TemplateNodeDefQuery templateNodeDefQuery = new TemplateNodeDefQuery();
		templateNodeDefQuery.setNodeCode(nodeCode);
		List<TemplateNodeDef> templateNodeDefList = templateNodeDefService
				.getTemplateNodeDefAll(templateNodeDefQuery);
		result.addDefaultModel(RZConstants.LIST,
				getNodeTemplateInfoList(templateNodeDefList));
		result.setSuccess(true);
		result.setSuccessMessage("保存节点模板分配数据");
		return result;
	}

	@Override
	public Result delNodeTemplateData(List<String> nodeTemplateIds)
			throws Exception {
		// TODO Auto-generated method stub
		if (nodeTemplateIds == null || nodeTemplateIds.size() == 0) {
			throw new Exception("取消节点分配模板数据id集合为空");
		}
		StringBuffer batchId = new StringBuffer();
		for (int i = 0; i < nodeTemplateIds.size(); i++) {
			batchId.append(nodeTemplateIds.get(i)).append(
					RZConstants.POJO_BATCHID_SPLIT);
		}
		// templateNodeDefService.getTemplateNodeDefAll(templateNodeDefQuery);
		// 此处还需要判断是否将模板定义设置为已使用-代码暂时实现
		TemplateNodeDefQuery templateNodeDefQuery = new TemplateNodeDefQuery();
		templateNodeDefQuery.setBatchId(batchId.toString());
		// 持久化数据
		templateNodeDefService
				.deleteTemplateNodeDefByBatchId(templateNodeDefQuery);
		Result result = new Result();
		result.setSuccess(true);
		result.setSuccessMessage("取消分配成功");
		return result;
	}

	/**
	 * 校验节点模板分配数据合法性
	 * 
	 * @param nodeTemplateEntry
	 *            节点模板分配数据
	 * @throws Exception
	 */
	private void checkNodeTemplate(NodeTemplateEntry nodeTemplateEntry)
			throws Exception {
		if (nodeTemplateEntry == null) {
			throw new Exception("节点模板数据为空");
		}
		if (StringUtil.isEmpty(nodeTemplateEntry.getNodeCode())) {
			throw new Exception("节点编码为空");
		}
	}

	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * 
	 * @param batchIds
	 *            模板id集合查询条件
	 * @return
	 */
	@Override
	public Result deleteTemplateDefByBatchId(List<String> batchIds)
			throws Exception {
		// TODO
		if (batchIds == null || batchIds.size() == 0) {
			throw new Exception("根据id删除模板数据时删除条件不能为空");
		}
		// 校验模板是否已分配，如果已分配，就不能删除
		List<TemplateDef> templateDefList = templateDefService
				.getTemplateDefBybatchIds(batchIds);
		if (templateDefList == null || templateDefList.size() == 0) {
			throw new Exception("根据id删除数据时不存在任何需要删除的数据");
		}
		TemplateDefQuery templateDefQuery = new TemplateDefQuery();
		StringBuffer batchId = new StringBuffer();
		for (String id : batchIds) {
			batchId.append(id).append(RZConstants.POJO_BATCHID_SPLIT);
		}
		templateDefQuery.setBatchId(batchId.toString());
		// 如果删除模板，需要删除模板定义，模板文件
		Result delResult = templateDefService
				.deleteTemplateDefByBatchId(templateDefQuery);
		if (delResult != null && delResult.isSuccess()) {
			TemplateInputDefQuery templateInputDefQuery = new TemplateInputDefQuery();
			templateInputDefQuery.setBatchId(templateDefQuery.getBatchId());
			delResult = templateInputDefService
					.deleteInputDefByTemplateBatchId(templateInputDefQuery);
		}
		if (delResult == null || !delResult.isSuccess()) {
			String errorMessage = "根据id批量删除模板数据出现异常";
			if (delResult != null) {
				errorMessage += "，异常信息：" + delResult.getErrorMessage();
			}
			throw new Exception(errorMessage);
		}
		// 删除模板文件数据
		RpcDfsFileInfoQuery rpcDfsFileInfoQuery = new RpcDfsFileInfoQuery();
		Result rpcResult = null;
		for (TemplateDef td : templateDefList) {
			rpcDfsFileInfoQuery.setFileFastdfsId(td.getTemplateFileId());
			rpcResult = iImfbpFastDFSRpcService.deleteFile(rpcDfsFileInfoQuery);
			checkRpcResult(rpcResult);
		}
		delResult.setSuccess(true);
		return delResult;
	}

	/**
	 * 保存模板数据
	 * 
	 * @param templateDefEntry
	 *            模板定义组合实体-包含模板数据、模板输入项数据、模板内容
	 * @return result.get(RZConstants.TEMPLATE_DEF_ENTRY)获取模板定义组合实体
	 * @throws Exception
	 */
	@Override
	public Result saveTemplatData(TemplateDefEntry templateDefEntry)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("\n执行保存模板数据服务开始\n");
		Result result = new Result();
		result.setSuccess(false);
		// 设置模板编号
		templateDefEntry.getTemplateDef().setTemplateCode(
				billnoService.getBillno(null));
		checkNullData(templateDefEntry);
		checkTemplateInput(templateDefEntry);
		// 如果是修改，需要删除分布式文件服务器上的模板文件重新上传
		if (!StringUtil.isEmpty(templateDefEntry.getTemplateDef().getId())
				&& !StringUtil.isEmpty(templateDefEntry.getTemplateDef()
						.getTemplateFileId())) {
			RpcDfsFileInfoQuery rpcDfsFileInfoQuery = new RpcDfsFileInfoQuery();
			rpcDfsFileInfoQuery.setFileFastdfsId(templateDefEntry
					.getTemplateDef().getTemplateFileId());
			Result deleteResult = iImfbpFastDFSRpcService
					.deleteFile(rpcDfsFileInfoQuery);
			checkRpcResult(deleteResult);
		}
		RpcDfsFileInfo rpcDfsFileInfo = new RpcDfsFileInfo();
		// 文件名=模板名+"."+文件后缀
		String fileName = templateDefEntry.getTemplateDef().getTemplateName()
				+ RZConstants.FILE_SPLIT + RZConstants.HTML_FILE_TYPE;
		checkTemplateName(templateDefEntry.getTemplateDef().getTemplateName());
		// 模板名称都是以html结尾
		// templateDefEntry.getTemplateDef().setTemplateName(fileName);
		rpcDfsFileInfo.setFileName(fileName);
		rpcDfsFileInfo.setFileSize((long) templateDefEntry.getHtmlContent()
				.getBytes(RZConstants.CHARSET_UTF_8).length);
		rpcDfsFileInfo.setFileType(RZConstants.HTML_FILE_TYPE);
		String htmlData = templateDefEntry.getHtmlContent();
		htmlData = RZConstants.HTML_FILE_HEAD + htmlData;
		htmlData += RZConstants.HTML_FILE_TAIL;
		// byte[] content =
		// templateDefEntry.getHtmlContent().getBytes(RZConstants.CHARSET_UTF_8);
		byte[] content = htmlData.getBytes(RZConstants.CHARSET_UTF_8);
		// 上传文件
		Result uploadResult = iImfbpFastDFSRpcService.uploadFile(
				rpcDfsFileInfo, content);
		checkRpcResult(uploadResult);
		Object obj = uploadResult.get(RZConstants.FASTDFS_FILEINFO);
		if (obj != null) {
			RpcDfsFileInfo dfsFileInfo = (RpcDfsFileInfo) obj;
			templateDefEntry.getTemplateDef().setTemplateFileId(
					dfsFileInfo.getFileFastdfsId());
			// boolean isUpdate = false;
			if (!StringUtil.isEmpty(templateDefEntry.getTemplateDef().getId())) {
				// isUpdate = true;
				// 如果是更新，需要判断是否已分配，如果已分配，需要改变当前版本号，将历史版本号进行持久化
				if (templateDefEntry.getTemplateDef().getTemplateStatus() != null
						&& templateDefEntry.getTemplateDef()
								.getTemplateStatus() == TemplateStatusEnum.DISTRIBUTED
								.getIndex()) {
					String version = templateDefEntry.getTemplateDef()
							.getTemplateVersion();
					if (StringUtil.isEmpty(version)) {
						throw new Exception("当前模板数据不存在版本号");
					}

					if (StringUtil.isEmpty(templateDefEntry.getTemplateDef()
							.getTemplateFileId())) {
						throw new Exception("当前模板数据不存在模板文件");
					}

					templateDefEntry.getTemplateDef().setTemplateVersion(
							RZVersionUtils.getNextVersion(version));
					TemplateVersion templateVersion = new TemplateVersion();
					templateVersion.setTemplateCode(templateDefEntry
							.getTemplateDef().getTemplateCode());
					templateVersion.setTemplateVersion(version);
					templateVersion.setTemplateFileId(templateDefEntry
							.getTemplateDef().getTemplateFileId());
					templateVersionService.insertOrUpdate(templateVersion);
				}
			} else {
				// isUpdate = false;
				// 如果是新增的话需要设置初始模板状态与模板版本号
				templateDefEntry.getTemplateDef().setTemplateStatus(
						TemplateStatusEnum.USED.getIndex());
				templateDefEntry.getTemplateDef().setTemplateVersion(
						RZConstants.TEMPLATE_INIT_VERSION);

			}
			// 保存数据到本地数据库
			templateDefService
					.insertOrUpdate(templateDefEntry.getTemplateDef());
			// 先删除已经存在的指标数据，再重新插入所有数据
			TemplateInputDefQuery templateInputDefQuery = new TemplateInputDefQuery();
			templateInputDefQuery.setTemplateId(templateDefEntry
					.getTemplateDef().getId());
			templateInputDefService
					.deleteTemplateInputDefByCondition(templateInputDefQuery);
			List<TemplateInputDef> templateInputDefs = templateDefEntry
					.getTemplateInputDefLists();
			if (templateInputDefs != null && templateInputDefs.size() > 0) {
				List<TemplateInputDef> insertLists = new ArrayList<TemplateInputDef>();
				// List<TemplateInputDef> updateLists = new
				// ArrayList<TemplateInputDef>();
				for (TemplateInputDef templateInputDef : templateInputDefs) {
					templateInputDef.setTemplateId(templateDefEntry
							.getTemplateDef().getId());
					templateInputDef.setId(null);
					insertLists.add(templateInputDef);
					// if (isUpdate
					// && !StringUtil.isEmpty(templateInputDef.getId())) {
					// updateLists.add(templateInputDef);
					// } else {
					// templateInputDef.setTs(DateUtil.getTs());
					// templateInputDef.setDr(0);
					// insertLists.add(templateInputDef);
					// }
				}
				if (insertLists.size() > 0) {
					// 插入数据库
					templateInputDefService
							.insertTemplateInputDefBatch(insertLists);
				}
				// if (updateLists.size() > 0) {
				// // 更新数据库
				// templateInputDefService
				// .updateBatchTemplateInputDefById(updateLists);
				// }
			}
		} else {
			throw new Exception("保存模板数据时，调用rpc未获取到模板文件信息");
		}
		result.addDefaultModel(RZConstants.TEMPLATE_DEF_ENTRY, templateDefEntry);
		result.setSuccess(true);
		result.setSuccessMessage("模板保存成功");
		logger.info("\n执行保存模板数据服务结束\n");
		return result;
	}

	/**
	 * 根据模板id获取模板数据
	 * 
	 * @param templateId
	 *            模板id
	 * @return result.get(RZConstants.TEMPLATE_DEF_ENTRY)获取模板定义组合实体
	 * @throws Exception
	 */
	@Override
	public Result getTemplateDataById(String templateId) throws Exception {
		// TODO Auto-generated method stub
		logger.info("执行根据模板id获取模板数据服务开始");
		if (StringUtil.isEmpty(templateId)) {
			throw new Exception("根据模板id获取模板数据时参数模板文件id[templateId]不能为空");
		}
		// 调用文件服务rpc获取访问URL
		Result result = new Result();
		result.setSuccess(false);
		// 查询模板数据相关元数据
		TemplateDefQuery templateDefQuery = new TemplateDefQuery();
		templateDefQuery.setId(templateId);
		TemplateDef templateDef = templateDefService
				.getTemplateDefById(templateDefQuery);
		if (templateDef == null
				|| StringUtil.isEmpty(templateDef.getTemplateFileId())) {
			throw new Exception("查找的模板信息不存在");
		}
		TemplateInputDefQuery templateInputDefQuery = new TemplateInputDefQuery();
		templateInputDefQuery.setTemplateId(templateId);
		List<TemplateInputDef> templateInputDefList = templateInputDefService
				.getTemplateInputDefAll(templateInputDefQuery);
		// 拿到文件模板内容数据
		RpcDfsFileInfoQuery rpcDfsFileInfoQuery = new RpcDfsFileInfoQuery();
		rpcDfsFileInfoQuery.setFileFastdfsId(templateDef.getTemplateFileId());
		Result downResult = iImfbpFastDFSRpcService
				.downloanFile(rpcDfsFileInfoQuery);
		if (downResult == null
				|| !downResult.isSuccess()
				|| StringUtil.isEmpty(downResult
						.get(RZConstants.FASTDFS_CONTENT))) {
			throw new Exception("获取模板文件时服务器中不存在该模板文件");
		}
		byte[] content = (byte[]) downResult.get(RZConstants.FASTDFS_CONTENT);
		// 组装数据，返回数据
		TemplateDefEntry templateDefEntry = new TemplateDefEntry();
		templateDefEntry.setTemplateDef(templateDef);
		templateDefEntry.setTemplateInputDefLists(templateInputDefList);
		templateDefEntry.setHtmlContent(new String(content,
				RZConstants.CHARSET_UTF_8));
		result.setSuccess(true);
		result.addDefaultModel(RZConstants.TEMPLATE_DEF_ENTRY, templateDefEntry);
		logger.info("执行根据模板id获取模板数据服务结束");
		return result;
	}

	/**
	 * 处理模板节点数据
	 * 
	 * @param node
	 *            需要处理的节点
	 */
	private void dealBillHtmlData(Node node,
			final Map<String, Object> billDataMap,
			Map<String, List<? extends SuperBean>> bodyBillDataMap) {
		if (node == null) {
			return;
		}
		if (node instanceof TextNode) {
			String nodeText = node.getText();
			List<String> inputItems = RegularUtils.getPatternMatcher(nodeText,
					RZConstants.TEMPLATE_INPUT_REGULAR);
			if (inputItems != null && inputItems.size() > 0) {
				for (String inputItem : inputItems) {
					String itemCode = inputItem.replaceAll(
							RZConstants.TEMPLATE_INPUT_REGULAR_BEFOR,
							RZConstants.EMPTY_CONTENT).replaceAll(
							RZConstants.TEMPLATE_INPUT_REGULAR_END,
							RZConstants.EMPTY_CONTENT);
					String regex = RZConstants.TEMPLATE_INPUT_REGULAR_BEFOR
							+ itemCode + RZConstants.TEMPLATE_INPUT_REGULAR_END;
					if (billDataMap.get(itemCode) != null) {
						node.setText(nodeText.replaceAll(regex, billDataMap
								.get(itemCode).toString()));
					} else {
						node.setText(nodeText.replaceAll(regex,
								RZConstants.SPACE_CONTENT));
					}
				}
			}
		} else if (node instanceof TableTag) {
			TableTag tag = (TableTag) node;
			TableRow[] rows = tag.getRows();
			if (rows != null && rows.length >= 0) {
				NodeList rowNodeList = new NodeList();
				for (int rowIndex = 0; rowIndex < rows.length; rowIndex++) {
					// 行文件值
					String rowText = rows[rowIndex].toPlainTextString()
							.toString().trim();
					List<String> rowTexts = RegularUtils.getPatternMatcher(
							rowText, RZConstants.TEMPLATE_INPUT_REGULAR);
					if (rowTexts != null && rowTexts.size() > 0) {
						boolean isTableBody = false;
						String tableCode = null;
						TableColumn[] tableColumns = rows[rowIndex]
								.getColumns();
						if (tableColumns != null && tableColumns.length > 0) {
							for (int i = 0; i < tableColumns.length; i++) {
								String tdText = tableColumns[i]
										.toPlainTextString();
								tdText = tdText != null ? tdText.toString()
										.trim() : RZConstants.EMPTY_CONTENT;
								List<String> tdTexts = RegularUtils
										.getPatternMatcher(
												tdText,
												RZConstants.TEMPLATE_INPUT_REGULAR);
								if (tdTexts != null && tdTexts.size() > 0) {
									for (String tdTempText : tdTexts) {
										String[] tdTextArray = tdTempText
												.split(RZConstants.TEXT_STRING_SPLIT);
										// 表体字段形式:${body.tableName.colName}
										if (tdTextArray != null
												&& tdTextArray.length == 2) {
											isTableBody = true;
											break;
										}
									}

								}
							}
						}
						if (isTableBody) {
							// 属于表体数据
							List<? extends SuperBean> bodyDataList = bodyBillDataMap
									.get(tableCode);
							if (bodyDataList != null && bodyDataList.size() > 0) {
								// 设置bodyDataList.size()行数据
								for (int i = 0; i < bodyDataList.size(); i++) {
									rowNodeList.add(rows[rowIndex]);
								}
							}
						} else {
							rowNodeList.add(rows[rowIndex]);
						}
					} else {
						rowNodeList.add(rows[rowIndex]);
					}
				}
				// 设置所有数据行数
				tag.setChildren(rowNodeList);
				rows = tag.getRows();
				// 表体行数
				int bodyRows = 0;
				// 设置数据值
				for (int j = 0; j < rows.length; j++) {
					TableRow tr = rows[j];
					// 找到需要替换的表格行，将制定字段替换掉
					TableColumn[] td = tr.getColumns();
					String newData = RZConstants.SPACE_CONTENT;
					boolean isHaveBody = false;
					for (int k = 0; k < td.length; k++) {
						String inputItem = td[k].toPlainTextString();
						inputItem = inputItem != null ? inputItem.toString()
								.trim() : RZConstants.EMPTY_CONTENT;
						inputItem = inputItem.replaceAll(
								RZConstants.SPACE_CONTENT,
								RZConstants.EMPTY_CONTENT);
						List<String> tdTexts = RegularUtils.getPatternMatcher(
								inputItem, RZConstants.TEMPLATE_INPUT_REGULAR);
						if (tdTexts != null && tdTexts.size() > 0) {
							String itemCode = inputItem.replaceAll(
									RZConstants.TEMPLATE_INPUT_REGULAR_BEFOR,
									RZConstants.EMPTY_CONTENT).replaceAll(
									RZConstants.TEMPLATE_INPUT_REGULAR_END,
									RZConstants.EMPTY_CONTENT);
							String[] tdItemCodeArray = itemCode
									.split(RZConstants.TEXT_STRING_SPLIT);
							if (tdItemCodeArray != null
									&& tdItemCodeArray.length == 2) {
								// 表格数据
								List<? extends SuperBean> bodyDataList = bodyBillDataMap
										.get(tdItemCodeArray[0]);
								if (bodyDataList != null
										&& bodyDataList.size() > 0) {
									SuperBean superBean = bodyDataList
											.get(bodyRows);
									if (superBean != null) {
										Object obj = superBean
												.getAttribute(tdItemCodeArray[1]);
										if (obj != null) {
											newData = obj.toString();
										}
									}
								}
								isHaveBody = true;
							} else {
								// 非表格数据
								Object obj = billDataMap.get(itemCode);
								if (obj != null) {
									newData = obj.toString();
								}
							}
							// NodeList tds = td[k].getChildren();
							// if (tds != null && tds.size() > 0) {
							// for (int h = 0; h < tds.size(); h++) {
							// setTableTdValue(tds.elementAt(h),
							// inputItem, newData);
							// }
							// }

							List<String> rowTexts = RegularUtils
									.getPatternMatcher(inputItem,
											RZConstants.TEMPLATE_INPUT_REGULAR);
							if (rowTexts != null && rowTexts.size() > 0) {
								NodeList tds = td[k].getChildren();
								if (tds != null && tds.size() > 0) {
									for (int h = 0; h < rowTexts.size(); h++) {
										setTableTdValue(td[k], rowTexts.get(h),
												newData);
									}
								}
							}
						}
					}
					if (isHaveBody) {
						bodyRows++;
						isHaveBody = false;
					}
				}
			}
		} else {
			// 递归处理
			NodeList childrenNodes = node.getChildren();
			if (childrenNodes != null && childrenNodes.size() > 0) {
				for (int i = 0; i < childrenNodes.size(); i++) {
					dealBillHtmlData(childrenNodes.elementAt(i), billDataMap,
							bodyBillDataMap);
				}
			}
		}
	}

	/**
	 * 处理模板节点数据
	 * 
	 * @param node
	 *            需要处理的节点
	 */
	private void dealTemplateData(Node node) {
		if (node == null) {
			return;
		}
		if (node instanceof TextNode) {
			String nodeText = node.getText();
			List<String> inputItems = RegularUtils.getPatternMatcher(nodeText,
					RZConstants.TEMPLATE_INPUT_REGULAR);
			if (inputItems != null && inputItems.size() > 0) {
				for (String inputItem : inputItems) {
					String number = inputItem.replaceAll(
							RZConstants.TEMPLATE_INPUT_REGULAR_BEFOR,
							RZConstants.EMPTY_CONTENT).replaceAll(
							RZConstants.TEMPLATE_INPUT_REGULAR_END,
							RZConstants.EMPTY_CONTENT);
					String regex = RZConstants.TEMPLATE_INPUT_REGULAR_BEFOR
							+ number + RZConstants.TEMPLATE_INPUT_REGULAR_END;
					node.setText(nodeText.replaceAll(regex,
							RZConstants.PREVIEW_REPLACE_CONTENT));
				}
			}
		} else if (node instanceof TableTag) {
			TableTag tag = (TableTag) node;
			TableRow[] rows = tag.getRows();
			if (rows != null && rows.length >= 0) {
				NodeList rowNodeList = new NodeList();
				for (int rowIndex = 0; rowIndex < rows.length; rowIndex++) {
					// 行文件值
					String rowText = rows[rowIndex].toPlainTextString()
							.toString().trim();
					List<String> rowTexts = RegularUtils.getPatternMatcher(
							rowText, RZConstants.TEMPLATE_INPUT_REGULAR);
					if (rowTexts != null && rowTexts.size() > 0) {
						boolean isTableBody = false;
						TableColumn[] tableColumns = rows[rowIndex]
								.getColumns();
						if (tableColumns != null && tableColumns.length > 0) {
							for (int i = 0; i < tableColumns.length; i++) {
								String tdText = tableColumns[i]
										.toPlainTextString();
								tdText = tdText != null ? tdText.toString()
										.trim() : RZConstants.EMPTY_CONTENT;
								List<String> tdTexts = RegularUtils
										.getPatternMatcher(
												tdText,
												RZConstants.TEMPLATE_INPUT_REGULAR);
								if (tdTexts != null && tdTexts.size() > 0) {
									for (String tdTempText : tdTexts) {
										String[] tdTextArray = tdTempText
												.split(RZConstants.TEXT_STRING_SPLIT);
										// 表体字段形式:${body.tableName.colName}
										if (tdTextArray != null
												&& tdTextArray.length == 2) {
											isTableBody = true;
											break;
										}
									}

								}
							}
						}
						if (isTableBody) {
							// 属于表体数据
							// 设置默认5行数据
							for (int i = 0; i < RZConstants.TEMPLATE_TABLE_BODY_ROWS; i++) {
								rowNodeList.add(rows[rowIndex]);
							}
						} else {
							rowNodeList.add(rows[rowIndex]);
						}
					} else {
						rowNodeList.add(rows[rowIndex]);
					}
				}
				// 设置所有数据行数
				tag.setChildren(rowNodeList);
				rows = tag.getRows();
				// 设置数据值
				for (int j = 0; j < rows.length; j++) {
					TableRow tr = rows[j];
					// 找到需要替换的表格行，将制定字段替换掉
					TableColumn[] td = tr.getColumns();
					for (int k = 0; k < td.length; k++) {
						String inputItem = td[k].toPlainTextString();
						inputItem = inputItem != null ? inputItem.toString()
								.trim() : RZConstants.EMPTY_CONTENT;
						inputItem = inputItem.replaceAll(
								RZConstants.SPACE_CONTENT,
								RZConstants.EMPTY_CONTENT);
						List<String> rowTexts = RegularUtils.getPatternMatcher(
								inputItem, RZConstants.TEMPLATE_INPUT_REGULAR);
						if (rowTexts != null && rowTexts.size() > 0) {
							NodeList tds = td[k].getChildren();
							if (tds != null && tds.size() > 0) {
								for (int h = 0; h < rowTexts.size(); h++) {
									setTableTdValue(td[k], rowTexts.get(h),
											RZConstants.PREVIEW_REPLACE_CONTENT);
								}
							}
						}

					}
				}
			}
		} else {
			// 递归处理
			NodeList childrenNodes = node.getChildren();
			if (childrenNodes != null && childrenNodes.size() > 0) {
				for (int i = 0; i < childrenNodes.size(); i++) {
					dealTemplateData(childrenNodes.elementAt(i));
				}
			}
		}
	}

	/**
	 * 设置表格单元格值
	 * 
	 * @param node
	 *            单元格节点node
	 * @param oldData
	 *            被替换的数据
	 * @param newData
	 *            用于替换的数据
	 */
	private void setTableTdValue(Node node, String oldData, String newData) {
		String nodeText = node.toPlainTextString();
		nodeText = nodeText != null ? nodeText.toString().trim()
				: RZConstants.EMPTY_CONTENT;
		nodeText = nodeText.replaceAll(RZConstants.SPACE_CONTENT,
				RZConstants.EMPTY_CONTENT);
		if (node instanceof TextNode && nodeText.indexOf(oldData) >= 0) {
			String targetValue = oldData.replaceAll(
					RZConstants.TEMPLATE_INPUT_REGULAR_BEFOR, "").replaceAll(
					RZConstants.TEMPLATE_INPUT_REGULAR_END, "");
			targetValue = RZConstants.TEMPLATE_INPUT_REGULAR_BEFOR
					+ targetValue + RZConstants.TEMPLATE_INPUT_REGULAR_END;
			node.setText(nodeText.replaceAll(targetValue, newData));
		} else {
			NodeList nls = node.getChildren();
			if (nls != null && nls.size() > 0) {
				for (int i = 0; i < nls.size(); i++) {
					setTableTdValue(nls.elementAt(i), oldData, newData);
				}
			}
		}
	}

	/**
	 * 模板预览-根据模板数据将指标替换为特殊值
	 * 
	 * @param htmlData
	 *            模板html数据
	 * @return Result.get(RZConstants.HTML_FILE_DATA)获取需要显示的文件数据
	 * @throws Exception
	 */
	@Override
	public Result previewTemplatData(String htmlData) throws Exception {
		// TODO Auto-generated method stub
		logger.info("\n执行预览模板服务开始\n");
		if (StringUtil.isEmpty(htmlData)) {
			throw new Exception("模板预览时，参数htmlData不能为空");
		}
		// htmlData = RZConstants.HTML_FILE_HEAD + htmlData;
		// htmlData += RZConstants.HTML_FILE_TAIL;
		// 替换掉需要显示为特殊字符的字段
		Result result = new Result();
		result.setSuccess(false);
		Parser parser = Parser
				.createParser(htmlData, RZConstants.CHARSET_UTF_8);
		// 拿到所有节点，根据节点去判断
		NodeList allNodeList = parser.parse(null);
		if (allNodeList != null && allNodeList.size() > 0) {
			for (int i = 0; i < allNodeList.size(); i++) {
				dealTemplateData(allNodeList.elementAt(i));
			}
		}
		// result.addDefaultModel(RZConstants.HTML_FILE_DATA, new
		// String(allNodeList.toHtml().getBytes(RZConstants.CHARSET_UTF_8)));
		result.addDefaultModel(RZConstants.HTML_FILE_DATA, allNodeList.toHtml());
		result.setSuccess(true);
		logger.info("\n执行预览模板服务结束\n");
		return result;
	}

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
	@Override
	public Result previewBillDoc(String nodeCode,
			final Map<String, List<SuperBean>> billDataMap,
			String nodeTemplateId) throws Exception {
		// TODO Auto-generated method stub
		logger.info("\n执行预览单据文档服务开始\n");
		Result result = new Result();
		result.setSuccess(false);
		// String htmlData = getBillHtmlData(nodeCode, billDataMap,
		// nodeTemplateId);
		String htmlData = null;
		result.addDefaultModel(RZConstants.HTML_FILE_DATA, htmlData);
		// 返回显示
		result.setSuccess(true);
		logger.info("\n执行预览单据文档服务结束\n");
		return result;
	}

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
	@Override
	public Result createBillDoc(String nodeCode, String billId,
			Map<String, Object> mainBillDataMap,
			Map<String, List<? extends SuperBean>> bodyBillDataMap,
			String nodeTemplateId, BaseSystemInfo baseSystemInfo)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("\n执行创建单据文档附件服务开始\n");
		Result result = new Result();
		result.setSuccess(false);
		if (StringUtil.isEmpty(billId)) {
			throw new Exception("参数单据id[billId]不能为空");
		}
		if (StringUtil.isEmpty(nodeTemplateId)) {
			throw new Exception("参数节点分配模板[nodeTemplateId]不能为空");
		}
		if (baseSystemInfo == null) {
			throw new Exception("参数文件公共信息[baseSystemInfo]不能为空");
		}
		if (mainBillDataMap == null) {
			mainBillDataMap = new HashMap<String, Object>();
		}
		if (bodyBillDataMap == null) {
			bodyBillDataMap = new HashMap<String, List<? extends SuperBean>>();
		}
		if (baseSystemInfo.getFileType() == null
				|| "".equals(baseSystemInfo.getFileType())) {
			baseSystemInfo.setFileType(FileTypeEnum.PDF.getValue());
		}
		if (!(baseSystemInfo.getFileType().equals(FileTypeEnum.PDF.getValue()) || baseSystemInfo
				.getFileType().equals(FileTypeEnum.DOC.getValue()))) {
			baseSystemInfo.setFileType(FileTypeEnum.PDF.getValue());
		}
		// 根据功能节点编码和模板定义id查询模板分配数据
		TemplateNodeDefQuery templateNodeDefQuery = new TemplateNodeDefQuery();
		templateNodeDefQuery.setId(nodeTemplateId);
		TemplateNodeDef templateNodeDef = templateNodeDefService
				.getTemplateNodeDefById(templateNodeDefQuery);
		if (templateNodeDef == null) {
			throw new Exception("未找到知道的节点分配模板数据");
		}
		// 拿到模板信息
		TemplateDefQuery templateDefQuery = new TemplateDefQuery();
		templateDefQuery.setId(templateNodeDef.getTemplateId());
		TemplateDef templateDef = templateDefService
				.getTemplateDefById(templateDefQuery);
		if (templateDef == null) {
			throw new Exception("该单据不存在任何模板信息");
		}

		// 获取html格式数据
		String htmlData = getBillHtmlData(nodeCode, mainBillDataMap,
				bodyBillDataMap, nodeTemplateId);
		// 根据格式生成文档并存放到文件服务器
		RpcDfsFileInfo rpcDfsFileInfo = new RpcDfsFileInfo();
		String templateName = templateDef.getTemplateName();
		String fileHtmlName = templateName + RZConstants.FILE_SPLIT
				+ RZConstants.HTML_FILE_TYPE;
		// checkTemplateName(fileName);
		rpcDfsFileInfo.setFileName(fileHtmlName);
		rpcDfsFileInfo.setFileSize((long) htmlData
				.getBytes(RZConstants.CHARSET_UTF_8).length);
		rpcDfsFileInfo.setFileType(RZConstants.HTML_FILE_TYPE);
		byte[] fileBtyeData = htmlData.getBytes(RZConstants.CHARSET_UTF_8);
		Result uploadResult = iImfbpFastDFSRpcService.uploadFile(
				rpcDfsFileInfo, fileBtyeData);
		if (uploadResult != null && uploadResult.isSuccess()) {
			rpcDfsFileInfo = (RpcDfsFileInfo) uploadResult
					.get(RZConstants.FASTDFS_FILEINFO);
			PubFileinfo fileinfo = new PubFileinfo();
			if (rpcDfsFileInfo != null) {
				fileinfo.setFileFastdfsGroup(rpcDfsFileInfo
						.getFileFastdfsGroup());
				fileinfo.setFileFastdfsId(rpcDfsFileInfo.getFileFastdfsId());
			}
			fileinfo.setPkBill(billId);
			// 文件名称
			fileinfo.setFilename(templateName + RZConstants.FILE_SPLIT
					+ baseSystemInfo.getFileType());
			// 文档类型--导出类型
			fileinfo.setDoctype(baseSystemInfo.getFileType());
			// 文件类型--2:原件
			fileinfo.setFiletype("2");
			// 文件描述固定为html，后续在下载导出时会使用
			fileinfo.setDescription(RZConstants.HTML_FILE_TYPE);
			// 文件来源-2：自动生成
			fileinfo.setFilesrc("2");
			fileinfo.setUploader(baseSystemInfo.getUserId());
			fileinfo.setUploadtime(DateUtil.getTs());
			fileinfo.setFilelength((long) htmlData
					.getBytes(RZConstants.CHARSET_UTF_8).length);
			fileinfo.setTenantId(baseSystemInfo.getTenantId());
			fileinfo.setSystemCode(baseSystemInfo.getSystemCode());
			// 将数据存放自己的数据库中
			fileinfo.setPkFile(primaryKeyUtil.getPrimaryKey());
			// 存储文件对照关系
			pubFileinfoDao.insertPubFileinfo(fileinfo);
			result.addDefaultModel("fileinfo", fileinfo);
		} else {
			// 存在异常，提示为什么出现异常
			throw new Exception("上传单据文档附件到文件服务器出现异常:"
					+ uploadResult.getErrorMessage());
		}

		result.setSuccess(true);
		logger.info("\n执行创建单据文档附件服务结束\n");
		return result;
	}

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
	@Override
	public Result exportBillDataDoc(String billFileId, String exportType)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("\n执行导出单据文档服务开始\n");
		Result result = new Result();
		result.setSuccess(false);
		if (StringUtil.isEmpty(billFileId)) {
			throw new Exception("导出单据文档时参数单据文档文件id[billDocFileId]不能为空");
		}
		// 拿到文件信息
		PubFileinfoQuery pubFileinfoQuery = new PubFileinfoQuery();
		pubFileinfoQuery.setPkFile(billFileId);
		PubFileinfo pubFileinfo = pubFileinfoDao
				.getPubFileinfoById(pubFileinfoQuery);
		if (pubFileinfo == null
				|| StringUtil.isEmpty(pubFileinfo.getFileFastdfsId())) {
			throw new Exception("导出单据文件时不存在该文档文件信息");
		}
		// 拿到指定文档文件内容
		RpcDfsFileInfoQuery rpcDfsFileInfoQuery = new RpcDfsFileInfoQuery();
		rpcDfsFileInfoQuery.setFileFastdfsId(pubFileinfo.getFileFastdfsId());
		Result downResult = iImfbpFastDFSRpcService
				.downloanFile(rpcDfsFileInfoQuery);
		if (downResult == null
				|| !downResult.isSuccess()
				|| StringUtil.isEmpty(downResult
						.get(RZConstants.FASTDFS_CONTENT))) {
			throw new Exception("导出单据文件时服务器中不存在该文档文件");
		}
		String htmlData = new String(
				(byte[]) downResult.get(RZConstants.FASTDFS_CONTENT));
		// 转换到指定文档内容
		byte[] fileBtyeData = null;
		if (StringUtil.isEmpty(exportType)) {
			exportType = FileTypeEnum.PDF.getValue();
		}
		if (exportType.equals(FileTypeEnum.PDF.getValue())) {
			fileBtyeData = Html2PDFUtils.html2PDF(htmlData);
		} else if (exportType.equals(FileTypeEnum.DOC.getValue())) {
			fileBtyeData = Html2WordUtils.html2WordDoc(htmlData);
		}
		// 返回结果
		result.setSuccess(true);
		result.addDefaultModel(RZConstants.HTML_FILE_DATA, fileBtyeData);
		result.addDefaultModel(RZConstants.HTML_FILE_NAME,
				pubFileinfo.getFilename());
		logger.info("\n执行导出单据文档服务结束\n");
		return result;
	}

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
	@Override
	public Map<String, Object> exportTemplateData(String templateId,
			String exportType) throws Exception {
		// TODO Auto-generated method stub
		logger.info("\n执行导出模板服务开始\n");
		if (StringUtil.isEmpty(templateId)) {
			throw new Exception("导出模板文件时参数模板id[templateId]不能为空");
		}
		if (StringUtil.isEmpty(exportType)) {
			exportType = FileTypeEnum.DOC.getValue();
		}
		// 根据模板id找到模板数据文件
		TemplateDefQuery templateDefQuery = new TemplateDefQuery();
		templateDefQuery.setId(templateId);
		TemplateDef templateDef = templateDefService
				.getTemplateDefById(templateDefQuery);
		if (templateDef == null
				|| StringUtil.isEmpty(templateDef.getTemplateFileId())) {
			throw new Exception("导出模板文件时不存在指定模板文件数据");
		}
		// 拿到模板数据文件
		RpcDfsFileInfoQuery rpcDfsFileInfoQuery = new RpcDfsFileInfoQuery();
		rpcDfsFileInfoQuery.setFileFastdfsId(templateDef.getTemplateFileId());
		Result downResult = iImfbpFastDFSRpcService
				.downloanFile(rpcDfsFileInfoQuery);
		checkRpcResult(downResult);
		if (StringUtil.isEmpty(downResult.get(RZConstants.FASTDFS_CONTENT))) {
			throw new Exception("导出模板文件时服务器中不存在该模板文件");
		}
		Map<String, Object> resultMap = new HashMap<String, Object>(2);
		// Object obj = downResult.get(RZConstants.FASTDFS_CONTENT);
		// String htmlData = new String((byte[]) obj);
		resultMap.put(RZConstants.HTML_FILE_DATA,
				downResult.get(RZConstants.FASTDFS_CONTENT));
		resultMap.put(RZConstants.HTML_FILE_NAME, templateDef.getTemplateName()
				+ RZConstants.FILE_SPLIT + RZConstants.FILE_TYPE_DOC);
		logger.info("\n执行导出模板服务结束\n");
		return resultMap;
	}

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
	@Override
	public Result copyTemplateData(String templateId, String templateName,
			BaseSystemInfo baseSystemInfo) throws Exception {
		// TODO Auto-generated method stub
		logger.info("\n执行复制模板服务开始\n");
		if (StringUtil.isEmpty(templateId)) {
			throw new Exception("复制模板时参数模板id[templateId]不能为空");
		}
		Result result = new Result();
		result.setSuccess(false);
		// 根据模板id找到模板文件元数据、输入项指标信息、模板文件信息
		TemplateDefQuery templateDefQuery = new TemplateDefQuery();
		templateDefQuery.setId(templateId);
		TemplateDef templateDef = templateDefService
				.getTemplateDefById(templateDefQuery);
		if (templateDef == null) {
			throw new Exception("复制模板时不存在指定模板文件数据");
		}
		// 拿到模板数据文件
		RpcDfsFileInfoQuery rpcDfsFileInfoQuery = new RpcDfsFileInfoQuery();
		rpcDfsFileInfoQuery.setFileFastdfsId(templateDef.getTemplateFileId());
		Result downResult = iImfbpFastDFSRpcService
				.downloanFile(rpcDfsFileInfoQuery);
		if (downResult == null
				|| !downResult.isSuccess()
				|| StringUtil.isEmpty(downResult
						.get(RZConstants.FASTDFS_CONTENT))) {
			throw new Exception("导出模板文件时服务器中不存在该模板文件");
		}
		byte[] templateData = (byte[]) downResult
				.get(RZConstants.FASTDFS_CONTENT);
		// 重新上传一份文件到分布式文件服务器
		RpcDfsFileInfo rpcDfsFileInfo = new RpcDfsFileInfo();
		if (StringUtil.isEmpty(templateName)) {
			templateName = templateDef.getTemplateName();
		}
		checkTemplateName(templateName);
		templateDef.setTemplateName(templateName);
		String rpcFileName = templateName;
		rpcFileName += RZConstants.FILE_SPLIT;
		rpcFileName += RZConstants.HTML_FILE_TYPE;
		rpcDfsFileInfo.setFileName(rpcFileName);
		rpcDfsFileInfo.setFileSize((long) templateData.length);
		rpcDfsFileInfo.setFileType(RZConstants.HTML_FILE_TYPE);
		Result uploadResult = iImfbpFastDFSRpcService.uploadFile(
				rpcDfsFileInfo, templateData);
		checkRpcResult(uploadResult);
		Object obj = uploadResult.get(RZConstants.FASTDFS_FILEINFO);
		if (obj != null) {
			RpcDfsFileInfo dfsFileInfo = (RpcDfsFileInfo) obj;
			templateDef.setId(null);
			templateDef.setTemplateFileId(dfsFileInfo.getFileFastdfsId());
		} else {
			throw new Exception("复制模板数据时，调用rpc未获取到模板文件信息");
		}
		templateDef.setTemplateCode(billnoService.getBillno(null));
		// 改变主键id后将模板文件元信息、输入项指标信息入库
		templateDefService.insertOrUpdate(templateDef);
		TemplateInputDefQuery templateInputDefQuery = new TemplateInputDefQuery();
		templateInputDefQuery.setTemplateId(templateId);
		List<TemplateInputDef> templateInputDefList = templateInputDefService
				.getTemplateInputDefAll(templateInputDefQuery);
		if (templateInputDefList != null && templateInputDefList.size() > 0) {
			for (int index = 0; index < templateInputDefList.size(); index++) {
				templateInputDefList.get(index).setId(null);
				templateInputDefList.get(index).setTemplateId(
						templateDef.getId());
			}
			templateInputDefService
					.insertTemplateInputDefBatch(templateInputDefList);
		}
		// result.addDefaultModel(RZTableNames.TEMPLATE_DEF, templateDef);
		// 组装数据，返回数据
		TemplateDefEntry templateDefEntry = new TemplateDefEntry();
		templateDefEntry.setTemplateDef(templateDef);
		templateDefEntry.setTemplateInputDefLists(templateInputDefList);
		templateDefEntry.setHtmlContent(new String(templateData,
				RZConstants.CHARSET_UTF_8));
		result.addDefaultModel(RZConstants.TEMPLATE_DEF_ENTRY, templateDefEntry);
		result.setSuccess(true);
		logger.info("\n执行复制模板服务结束\n");
		return result;
	}

	/**
	 * 获取模板文件访问URL
	 * 
	 * @param templateFileId
	 *            文件服务器上模板文件id
	 * @return result.get(RZConstants.FILE_URL)获取文件访问URL
	 * @throws Exception
	 */
	@Override
	public Result getFileUrl(String templateFileId) throws Exception {
		// TODO Auto-generated method stub
		logger.info("执行获取模板文件访问URL服务开始");
		if (StringUtil.isEmpty(templateFileId)) {
			throw new Exception("获取文件访问URL时参数模板文件id[templateFileId]不能为空");
		}
		// 调用文件服务rpc获取访问URL
		Result result = new Result();
		result.setSuccess(false);
		RpcDfsFileInfoQuery rpcDfsFileInfoQuery = new RpcDfsFileInfoQuery();
		rpcDfsFileInfoQuery.setFileFastdfsId(templateFileId);
		Result urlResult = iImfbpFastDFSRpcService
				.getFileTokenURLAndFileName(rpcDfsFileInfoQuery);
		checkRpcResult(urlResult);
		Object obj = urlResult.get(RZConstants.FILE_URL);
		if (obj == null) {
			throw new Exception("文件服务器中不存在该模板文件访问URL");
		}
		result.setSuccess(true);
		logger.info("执行获取模板文件访问URL服务结束");
		return result;
	}

	/**
	 * 获取单据文档内容
	 * 
	 * @param nodeCode
	 *            功能节点编码
	 * @param billDataMap
	 *            单据数据,如果一个单据是主子表，则使用：billDataMap.put(t1,list);t1代表表名，
	 *            list代表对应的数据集合（主表数据该集合只有一个元素）
	 * @param nodeTemplateId
	 *            节点分配模板id
	 * @return String类型html文件内容
	 * @throws Exception
	 */
	private String getBillHtmlData(String nodeCode,
			final Map<String, Object> billDataMap,
			Map<String, List<? extends SuperBean>> bodyBillDataMap,
			String nodeTemplateId) throws Exception {
		if (StringUtil.isEmpty(nodeCode)) {
			throw new Exception("参数功能节点编码[nodeCode]不能为空");
		}
		if (StringUtil.isEmpty(nodeCode)) {
			throw new Exception("参数单据数据[billDataMap]不能为空");
		}
		if (StringUtil.isEmpty(nodeCode)) {
			throw new Exception("参数节点分配模板id[nodeTemplateId]不能为空");
		}
		TemplateNodeDefQuery templateNodeDefQuery = new TemplateNodeDefQuery();
		templateNodeDefQuery.setId(nodeTemplateId);
		TemplateNodeDef templateNodeDef = templateNodeDefService
				.getTemplateNodeDefById(templateNodeDefQuery);
		if (templateNodeDef == null) {
			throw new Exception("不存在指定节点分配模板信息");
		}
		// 获取到模板业务规则数据
		TemplateRulesDefQuery templateRulesDefQuery = new TemplateRulesDefQuery();
		templateRulesDefQuery.setTemplateNodeId(nodeTemplateId);
		List<TemplateRulesDef> templateRulesDefLists = templateRulesDefService
				.getTemplateRulesDefAll(templateRulesDefQuery);
		if (templateRulesDefLists == null || templateRulesDefLists.size() == 0) {
			throw new Exception("该节点不存任何模板业务规则数据");
		}
		final Map<String, TemplateRulesDef> templateRulesDefMap = new HashMap<String, TemplateRulesDef>();
		for (TemplateRulesDef trd : templateRulesDefLists) {
			templateRulesDefMap.put(trd.getInputItemId(), trd);
		}

		// 拿到模板信息
		TemplateDefQuery templateDefQuery = new TemplateDefQuery();
		templateDefQuery.setId(templateNodeDef.getTemplateId());
		TemplateDef templateDef = templateDefService
				.getTemplateDefById(templateDefQuery);
		if (templateDef == null
				|| StringUtil.isEmpty(templateDef.getTemplateFileId())) {
			throw new Exception("该单据不存在任何模板信息");
		}

		// 拿到模板文件信息
		RpcDfsFileInfoQuery rpcDfsFileInfoQuery = new RpcDfsFileInfoQuery();
		rpcDfsFileInfoQuery.setFileFastdfsId(templateDef.getTemplateFileId());
		Result downResult = iImfbpFastDFSRpcService
				.downloanFile(rpcDfsFileInfoQuery);
		if (downResult == null
				|| !downResult.isSuccess()
				|| StringUtil.isEmpty(downResult
						.get(RZConstants.FASTDFS_CONTENT))) {
			throw new Exception("服务器中不存在该模板文件");
		}
		Object obj = downResult.get(RZConstants.FASTDFS_CONTENT);
		String htmlData = new String((byte[]) obj, RZConstants.CHARSET_UTF_8);
		Parser parser = Parser
				.createParser(htmlData, RZConstants.CHARSET_UTF_8);
		// 拿到所有节点，根据节点去判断
		NodeList allNodeList = parser.parse(null);
		if (allNodeList != null && allNodeList.size() > 0) {
			for (int i = 0; i < allNodeList.size(); i++) {
				dealBillHtmlData(allNodeList.elementAt(i), billDataMap,
						bodyBillDataMap);
			}
		}
		// // 根据模板业务规则与单据数据生成对应的html内容
		// Parser parser = Parser.createParser(htmlData, "GBK");
		// NodeList allNodes = parser.extractAllNodesThatMatch(new NodeFilter()
		// {
		// public boolean accept(Node node) {
		// if (node instanceof TextNode) {
		// String nodeText = node.getText();
		// if (!StringUtil.isEmpty(nodeText)) {
		// List<String> inputItems = RegularUtils
		// .getPatternMatcher(nodeText,
		// RZConstants.TEMPLATE_INPUT_REGULAR);
		// if (inputItems != null && inputItems.size() > 0) {
		// for (String inputItem : inputItems) {
		// String number = inputItem
		// .replaceAll(
		// RZConstants.TEMPLATE_INPUT_REGULAR_BEFOR,
		// "")
		// .replaceAll(
		// RZConstants.TEMPLATE_INPUT_REGULAR_END,
		// "");
		// String regex = RZConstants.TEMPLATE_INPUT_BEFOR
		// + number
		// + RZConstants.TEMPLATE_INPUT_END;
		// // node.setText(nodeText.replaceAll(regex,
		// // RZConstants.PREVIEW_REPLACE_CONTENT));
		// TemplateRulesDef templateRulesDef = templateRulesDefMap
		// .get(number);
		// if (templateRulesDef != null) {
		// // List<SuperBean> dataBeans = billDataMap
		// // .get(templateRulesDef
		// // .getTableName());
		// List<SuperBean> dataBeans = null;
		// String itemName = templateRulesDef
		// .getItemName();
		// if (dataBeans != null
		// && dataBeans.size() > 0) {
		// Object replaceText = dataBeans.get(0)
		// .getAttribute(itemName);
		// if (!StringUtil.isEmpty(replaceText)) {
		// // 替换内容
		// node.setText(nodeText.replaceAll(
		// regex,
		// replaceText.toString()));
		// }
		// }
		// }
		// }
		// }
		// } else {
		// node.setText(RZConstants.SPACE_CONTENT);
		// }
		// } else if (node instanceof TableTag) {
		// // String nodeText = node.getText();
		// // System.out.println(nodeText);
		// TableTag tag = (TableTag) node;
		// TableRow[] rows = tag.getRows();
		// // 第一列为表头，第二列为内容
		// if (rows != null && rows.length == 2) {
		// // rows
		// String tdText = rows[1].getColumns()[0]
		// .toPlainTextString();
		// String number = tdText.replaceAll(
		// RZConstants.TEMPLATE_INPUT_REGULAR_BEFOR, "")
		// .replaceAll(
		// RZConstants.TEMPLATE_INPUT_REGULAR_END,
		// "");
		// String regex = RZConstants.TEMPLATE_INPUT_BEFOR
		// + number + RZConstants.TEMPLATE_INPUT_END;
		// TemplateRulesDef templateRulesDef = templateRulesDefMap
		// .get(number);
		// if (templateRulesDef != null) {
		// NodeList trChildren = rows[1].getChildren();
		// // TableColumn[] td = rows[1].getColumns();
		// // List<SuperBean> dataBeans = billDataMap
		// // .get(templateRulesDef.getTableName());
		// List<SuperBean> dataBeans = null;
		// NodeList nl = new NodeList();
		// if (trChildren != null && trChildren.size() == 1
		// && dataBeans != null
		// && dataBeans.size() == 0) {
		// for (int i = 0; i < dataBeans.size(); i++) {
		// nl.add(trChildren);
		// }
		// }
		// rows[1].setChildren(nl);
		// // NodeList nodeChildren = rows[1].getChildren();
		// // String itemName = templateRulesDef.getItemName();
		// if (dataBeans != null && dataBeans.size() > 0) {
		// for (int dataIndex = 0; dataIndex < dataBeans
		// .size(); dataIndex++) {
		// TableRow tr = rows[dataIndex];
		// TableColumn[] td = tr.getColumns();
		// for (int tdIndex = 0; tdIndex < td.length; tdIndex++) {
		// String inputItem = td[tdIndex]
		// .toPlainTextString();
		// number = inputItem
		// .replaceAll(
		// RZConstants.TEMPLATE_INPUT_REGULAR_BEFOR,
		// "")
		// .replaceAll(
		// RZConstants.TEMPLATE_INPUT_REGULAR_END,
		// "");
		// regex = RZConstants.TEMPLATE_INPUT_BEFOR
		// + number
		// + RZConstants.TEMPLATE_INPUT_END;
		// templateRulesDef = templateRulesDefMap
		// .get(number);
		// Object replaceText = dataBeans.get(
		// dataIndex).getAttribute(
		// templateRulesDef.getItemName());
		// if (!StringUtil.isEmpty(replaceText)) {
		// // 替换内容
		// td[tdIndex].setText(inputItem
		// .replaceAll(regex,
		// replaceText
		// .toString()));
		// } else {
		// td[tdIndex]
		// .setText(RZConstants.SPACE_CONTENT);
		// }
		// }
		// }
		// }
		// }
		//
		// }
		// } else if (node instanceof ImageTag) {
		// String src = ((ImageTag) node)
		// .getAttribute(RZConstants.IMAGETAG_SRC);
		// if (!StringUtil.isEmpty(src)) {
		// src = src.replaceAll(RZConstants.IMAGETAG_SRC_FILE,
		// RZConstants.EMPTY_CONTENT);
		// ((ImageTag) node).setAttribute(
		// RZConstants.IMAGETAG_SRC, src);
		// }
		// }
		// return true;
		// }
		// });
		// System.out.println("\n\n\n htmlData is \n" + allNodes.toHtml()
		// + "\n\n\n");
		// return allNodes.toHtml();
		return allNodeList.toHtml();
	}

	/**
	 * 校验模板名称长度是否太长不能超过20个汉字所在位置。使用UTF-8编码，不能超过60字节
	 * 
	 * @param templateName
	 *            字符串
	 */
	private void checkTemplateName(String templateName) throws Exception {
		if (!StringUtil.isEmpty(templateName)) {
			if (templateName.getBytes(RZConstants.CHARSET_UTF_8).length > 57) {
				throw new Exception("模板名称最长为19个汉字");
			}
		}
	}

	/**
	 * 校验结果，给出指定异常
	 * 
	 * @param rpcResult
	 *            结果集对象
	 * @throws Exception
	 */
	private void checkRpcResult(Result rpcResult) throws Exception {
		if (rpcResult == null) {
			throw new Exception("调用分布式文件服务rpc返回结果集为空");
		}
		if (rpcResult != null && !rpcResult.isSuccess()) {
			throw new Exception("调用分布式文件服务rpc出现异常,异常信息:"
					+ rpcResult.getErrorMessage());
		}
	}

	/**
	 * 校验模板输入项与模板内容定义的输入项是否一致
	 * 
	 * @param templateDefEntry
	 *            模板定义实体-包含模板数据、模板输入项数据、模板内容
	 * @throws Exception
	 */
	private void checkTemplateInput(TemplateDefEntry templateDefEntry)
			throws Exception {
		String htmlContent = templateDefEntry.getHtmlContent();
		// List<String> inputItems = RegularUtils.getPatternMatcher(htmlContent,
		// RZConstants.TEMPLATE_INPUT_REGULAR);
		List<String> inputItems = getAllInputItemByHtmlData(htmlContent);
		if (inputItems != null && inputItems.size() > 0) {
			Set<String> inputItemSet = new HashSet<String>();
			for (String inputIntem : inputItems) {
				inputItemSet.add(inputIntem);
			}
			List<TemplateInputDef> realInputItems = templateDefEntry
					.getTemplateInputDefLists();
			if (realInputItems == null
					|| inputItems.size() != realInputItems.size()) {
				throw new Exception("模板保存时，模板内容定义的输入项与实际填写输入项个数不一致");
			}
			Set<String> realInputItemSet = new HashSet<String>();
			for (TemplateInputDef templateInputDef : realInputItems) {
				realInputItemSet.add(RZConstants.TEMPLATE_INPUT_BEFOR
						+ templateInputDef.getItemCode()
						+ RZConstants.TEMPLATE_INPUT_END);
			}
			if (!inputItemSet.containsAll(realInputItemSet)
					&& !realInputItemSet.containsAll(inputItemSet)) {
				throw new Exception("模板保存时，模板内容定义的输入项与实际填写输入项编码存在不一致");
			}
		}
	}

	/**
	 * 校验数据必填项是否为空
	 * 
	 * @param templateDefEntry
	 *            模板定义实体-包含模板数据、模板输入项数据、模板内容
	 * @throws Exception
	 */
	private void checkNullData(TemplateDefEntry templateDefEntry)
			throws Exception {
		if (templateDefEntry == null) {
			throw new Exception("保存模板时，模板定义对象不能为空");
		}
		if (templateDefEntry.getTemplateDef() == null) {
			throw new Exception("保存模板时，模板数据不能为空");
		}
		if (StringUtil.isEmpty(templateDefEntry.getHtmlContent())) {
			throw new Exception("保存模板时，模板内容不能为空");
		}
		if (StringUtil.isEmpty(templateDefEntry.getTemplateDef()
				.getTemplateCode())) {
			throw new Exception("保存模板时，模板编码不能为空");
		}
		if (StringUtil.isEmpty(templateDefEntry.getTemplateDef()
				.getTemplateName())) {
			throw new Exception("保存模板时，模板名称不能为空");
		}
		List<TemplateInputDef> lists = templateDefEntry
				.getTemplateInputDefLists();
		if (lists != null) {
			for (TemplateInputDef templateInputDef : lists) {
				if (StringUtil.isEmpty(templateInputDef.getItemCode())) {
					throw new Exception("保存模板时，模板输入项编码不能为空");
				}
				if (StringUtil.isEmpty(templateInputDef.getItemName())) {
					throw new Exception("保存模板时，模板输入项名称不能为空");
				}
			}
		}
	}

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
	@Override
	public Result getNodeTemplateData(String nodeCode, String tenantId)
			throws Exception {
		// TODO Auto-generated method stub
		// 校验节点编码、租户id是否为空
		if (StringUtil.isEmpty(nodeCode)) {
			throw new Exception("节点编码为空");
		}
		if (StringUtil.isEmpty(tenantId)) {
			throw new Exception("租户id为空");
		}
		Result result = new Result(false);
		// 获取节点分配的模板信息
		TemplateNodeDefQuery templateNodeDefQuery = new TemplateNodeDefQuery();
		templateNodeDefQuery.setNodeCode(nodeCode);
		templateNodeDefQuery.setTenantId(tenantId);
		List<TemplateNodeDef> templateNodeDefList = templateNodeDefService
				.getTemplateNodeDefAll(templateNodeDefQuery);
		result.addDefaultModel(RZConstants.LIST,
				getNodeTemplateInfoList(templateNodeDefList));
		// 返回结果
		result.setSuccessMessage("获取节点分配的模板信息成功");
		result.setSuccess(true);
		return result;
	}

	private List<NodeTemplateInfo> getNodeTemplateInfoList(
			List<TemplateNodeDef> templateNodeDefList) throws Exception {
		// 组装需要返回的信息 NodeTemplateInfo
		if (templateNodeDefList != null && templateNodeDefList.size() > 0) {
			List<String> batchIds = new ArrayList<String>(
					templateNodeDefList.size());
			Map<String, TemplateNodeDef> templateNodeDefMap = new HashMap<String, TemplateNodeDef>();
			for (TemplateNodeDef tnd : templateNodeDefList) {
				batchIds.add(tnd.getTemplateId());
				templateNodeDefMap.put(tnd.getTemplateId(), tnd);
			}
			List<TemplateDef> templateDefList = templateDefService
					.getTemplateDefBybatchIds(batchIds);
			if (templateDefList != null && templateDefList.size() > 0) {
				// 组装数据
				List<NodeTemplateInfo> nodeTemplateInfoList = new ArrayList<NodeTemplateInfo>(
						templateDefList.size());
				for (TemplateDef td : templateDefList) {
					NodeTemplateInfo ntinfo = new NodeTemplateInfo();
					// 设置主键--节点模板分配主键
					ntinfo.setId(templateNodeDefMap.get(td.getId()).getId());
					// 设置启用状态
					ntinfo.setUseStatus(templateNodeDefMap.get(td.getId())
							.getUseStatus());
					// 设置分配日期
					ntinfo.setCreatedate(templateNodeDefMap.get(td.getId())
							.getCreatedate());
					// 设置模板id
					ntinfo.setTenantId(td.getId());
					// 设置模板文件id
					ntinfo.setTemplateFileId(td.getTemplateFileId());
					// 设置模板编码
					ntinfo.setTemplateCode(td.getTemplateCode());
					// 设置模板名称
					ntinfo.setTemplateName(td.getTemplateName());
					// 设置模板版本号
					ntinfo.setTemplateVersion(td.getTemplateVersion());
					nodeTemplateInfoList.add(ntinfo);
				}
				return nodeTemplateInfoList;
			}
		}
		return null;
	}

	@Override
	public Result updateNodeTemplateStatus(String nodeTemplateId,
			Integer useStatus) throws Exception {
		// TODO Auto-generated method stub
		// 校验参数[节点分配模板id、是否启用状态]是否为空
		if (StringUtil.isEmpty(nodeTemplateId)) {
			throw new Exception("节点分配模板id为空");
		}
		if (StringUtil.isEmpty(useStatus)) {
			// 如果启用状态为空，默认为启用
			useStatus = TemplateStatusEnum.USED.getIndex();
		}
		if (!(useStatus == TemplateStatusEnum.USED.getIndex() || useStatus == TemplateStatusEnum.UNUSED
				.getIndex())) {
			throw new Exception("启用停用状态只能为0或者1，0代表停用，1代表启用");
		}
		TemplateNodeDef templateNodeDef = new TemplateNodeDef();
		templateNodeDef.setId(nodeTemplateId);
		templateNodeDef.setUseStatus(useStatus);
		templateNodeDefService.updateTemplateNodeDefById(templateNodeDef);
		Result result = new Result(true);
		result.setSuccessMessage("更新节点模板分配数据启用停用状态成功");
		return result;
	}

	@Override
	public Result updateTemplateDefStatus(String templateId,
			Integer templateStatus) throws Exception {
		// TODO Auto-generated method stub
		if (StringUtil.isEmpty(templateId)) {
			throw new Exception("模板id为空");
		}
		if (StringUtil.isEmpty(templateStatus)) {
			// 如果启用状态为空，默认为启用
			templateStatus = TemplateStatusEnum.USED.getIndex();
		}
		if (!(templateStatus != TemplateStatusEnum.USED.getIndex() || templateStatus != TemplateStatusEnum.UNUSED
				.getIndex())) {
			throw new Exception("启用停用状态只能为0或者1，0代表停用，1代表启用");
		}
		TemplateDef templateDef = new TemplateDef();
		templateDef.setId(templateId);
		templateDef.setTemplateStatus(templateStatus);
		templateDefService.updateTemplateDefById(templateDef);
		Result result = new Result(true);
		result.setSuccessMessage("更新模板数据启用停用状态成功");
		return result;
	}

	@Override
	public Result getMetadata(String sysId, String nodeCode) throws Exception {
		// TODO Auto-generated method stub
		Result result = new Result(true);
		String[] tableCodes = basePublicService
				.getTableCodesByNodeCode(nodeCode);
		if (tableCodes != null && tableCodes.length > 0) {
			Map<String, JSONObject> metadataMap = bossMetadataRpcService
					.getBossMetadata4Extract(sysId, tableCodes);
			if (metadataMap != null) {
				// result.addDefaultModel(RZConstants.META_DATA, metadataMap);
				Object[] obj = getTableMetaDataByJSON(metadataMap);
				if (obj != null && obj.length == 2) {
					result.addDefaultModel(RZConstants.TABLE_DATA, obj[0]);
					result.addDefaultModel(RZConstants.TABLE_COLUMNS_DATA,
							obj[1]);
				}
			}
		}
		result.setSuccessMessage("获取元数据成功");
		return result;
	}

	@SuppressWarnings("unchecked")
	private Object[] getTableMetaDataByJSON(Map<String, JSONObject> metadataMap) {
		if (metadataMap == null || metadataMap.size() == 0) {
			return null;
		}
		List<TableEntry> tableEntries = new ArrayList<TableEntry>();
		Map<String, List<TableColumnsEntry>> tableColumnsEntryMap = new HashMap<String, List<TableColumnsEntry>>();
		JSONObject value = null;
		Object columnsMap = null;
		Map<String, JSONObject> rpcMetadataMap = null;
		for (Entry<String, JSONObject> jSONObjectEntry : metadataMap.entrySet()) {
			value = jSONObjectEntry.getValue();
			if (value != null) {
				TableEntry tableEntry = new TableEntry();
				tableEntry.setTableCode(value.get(RZConstants.TABEL_LABEL_EN)
						.toString());
				if (!StringUtil.isEmpty(value.get(RZConstants.TABEL_LABEL_ZH))) {
					tableEntry.setTableName(value.get(
							RZConstants.TABEL_LABEL_ZH).toString());
				} else {
					tableEntry.setTableName(value.get(
							RZConstants.TABEL_LABEL_EN).toString());
				}
				tableEntries.add(tableEntry);
				columnsMap = value.get(RZConstants.TABEL_COLUMNS);
				if (columnsMap != null && columnsMap instanceof Map) {
					List<TableColumnsEntry> tableColumnsEntryList = new ArrayList<TableColumnsEntry>();
					rpcMetadataMap = (Map<String, JSONObject>) columnsMap;
					for (Entry<String, JSONObject> jsonObjectEntry : rpcMetadataMap
							.entrySet()) {
						JSONObject jsonObject = jsonObjectEntry.getValue();
						if (jsonObject != null && !jsonObject.isEmpty()) {
							RpcMetadata rpcMetadata = (RpcMetadata) JSONObject
									.toBean(jsonObject, RpcMetadata.class);
							TableColumnsEntry tableColumnsEntry = new TableColumnsEntry();
							tableColumnsEntry.setColCode(rpcMetadata
									.getColumnLableEn());
							if (StringUtil.isEmpty(rpcMetadata
									.getColumnLableZh())) {
								tableColumnsEntry.setColName(rpcMetadata
										.getColumnLableEn());
							} else {
								tableColumnsEntry.setColName(rpcMetadata
										.getColumnLableZh());
							}
							tableColumnsEntryList.add(tableColumnsEntry);
						}
					}
					tableColumnsEntryMap.put(
							value.get(RZConstants.TABEL_LABEL_EN).toString(),
							tableColumnsEntryList);
				}
			}
		}
		return new Object[] { tableEntries, tableColumnsEntryMap };
	}

	public IImfbpFastDFSRpcService getiImfbpFastDFSRpcService() {
		return iImfbpFastDFSRpcService;
	}

	public void setiImfbpFastDFSRpcService(
			IImfbpFastDFSRpcService iImfbpFastDFSRpcService) {
		this.iImfbpFastDFSRpcService = iImfbpFastDFSRpcService;
	}

	public TemplateDefService getTemplateDefService() {
		return templateDefService;
	}

	public void setTemplateDefService(TemplateDefService templateDefService) {
		this.templateDefService = templateDefService;
	}

	public TemplateInputDefService getTemplateInputDefService() {
		return templateInputDefService;
	}

	public void setTemplateInputDefService(
			TemplateInputDefService templateInputDefService) {
		this.templateInputDefService = templateInputDefService;
	}

	public TemplateRulesDefService getTemplateRulesDefService() {
		return templateRulesDefService;
	}

	public void setTemplateRulesDefService(
			TemplateRulesDefService templateRulesDefService) {
		this.templateRulesDefService = templateRulesDefService;
	}

	public TemplateNodeDefService getTemplateNodeDefService() {
		return templateNodeDefService;
	}

	public void setTemplateNodeDefService(
			TemplateNodeDefService templateNodeDefService) {
		this.templateNodeDefService = templateNodeDefService;
	}

	public PrimaryKeyUtil getPrimaryKeyUtil() {
		return primaryKeyUtil;
	}

	public void setPrimaryKeyUtil(PrimaryKeyUtil primaryKeyUtil) {
		this.primaryKeyUtil = primaryKeyUtil;
	}

	public TemplateVersionService getTemplateVersionService() {
		return templateVersionService;
	}

	public void setTemplateVersionService(
			TemplateVersionService templateVersionService) {
		this.templateVersionService = templateVersionService;
	}

	public PubFileinfoDao getPubFileinfoDao() {
		return pubFileinfoDao;
	}

	public void setPubFileinfoDao(PubFileinfoDao pubFileinfoDao) {
		this.pubFileinfoDao = pubFileinfoDao;
	}

	public BossMetadataRpcService getBossMetadataRpcService() {
		return bossMetadataRpcService;
	}

	public void setBossMetadataRpcService(
			BossMetadataRpcService bossMetadataRpcService) {
		this.bossMetadataRpcService = bossMetadataRpcService;
	}

	public BasePublicService getBasePublicService() {
		return basePublicService;
	}

	public void setBasePublicService(BasePublicService basePublicService) {
		this.basePublicService = basePublicService;
	}

	public BillnoService getBillnoService() {
		return billnoService;
	}

	public void setBillnoService(BillnoService billnoService) {
		this.billnoService = billnoService;
	}

	@Override
	public GridResult<TemplateDef> getNoDisTemplateDefByPage(
			TemplateDefQuery templateDefQuery) throws Exception {
		// TODO Auto-generated method stub
		if (templateDefQuery == null) {
			throw new Exception("查询条件参数为空");
		}
		if (StringUtil.isEmpty(templateDefQuery.getNodeCode())) {
			throw new Exception("功能节点编码不能为空");
		}
		TemplateNodeDefQuery templateNodeDefQuery = new TemplateNodeDefQuery();
		templateNodeDefQuery.setNodeCode(templateDefQuery.getNodeCode());
		List<TemplateNodeDef> templateNodeDefList = templateNodeDefService
				.getTemplateNodeDefAll(templateNodeDefQuery);
		if (templateNodeDefList != null && templateNodeDefList.size() > 0) {
			List<String> templateIds = new ArrayList<String>();
			for (TemplateNodeDef templateNodeDef : templateNodeDefList) {
				templateIds.add(templateNodeDef.getTemplateId());
			}
			templateDefQuery.setBatchIds(templateIds);
		}
		templateDefQuery.setIsDistriTemp(RZConstants.YES);
		// Result result = new Result(true);
		GridResult<TemplateDef> list = templateDefService
				.getTemplateDefByPage(templateDefQuery);
		// if (list != null) {
		// result.addDefaultModel("list", list);
		// }
		// result.setSuccessMessage("获取未分配模板成功");
		// return result;
		return list;
	}

	@Override
	public Result getRulesDataByTemplateNodeId(
			TemplateNodeDefQuery templateNodeDefQuery) throws Exception {
		// TODO Auto-generated method stub
		if (templateNodeDefQuery == null) {
			throw new Exception("查询节点指定模板业务规则数据时参数不能为空");
		}
		if (StringUtil.isEmpty(templateNodeDefQuery.getId())) {
			throw new Exception("节点分配模板数据id为空");
		}
		// 查询当前节点分配的节点模板数据
		TemplateNodeDef templateNodeDef = templateNodeDefService
				.getTemplateNodeDefById(templateNodeDefQuery);
		if (templateNodeDef == null) {
			throw new Exception("不存在节点分配的指定节点模板数据，可能数据已经被更新");
		}
		if (StringUtil.isEmpty(templateNodeDef.getTemplateId())) {
			throw new Exception("节点模板数据中不存在模板id信息");
		}
		// 查询当前节点分配模板配置的业务规则数据
		TemplateRulesDefQuery templateRulesDefQuery = new TemplateRulesDefQuery();
		templateRulesDefQuery.setTemplateNodeId(templateNodeDefQuery.getId());
		List<TemplateRulesDef> templateRulesDefList = templateRulesDefService
				.getTemplateRulesDefAll(templateRulesDefQuery);
		// 查询模板实际配置的指标数据
		TemplateInputDefQuery templateInputDefQuery = new TemplateInputDefQuery();
		templateInputDefQuery.setTemplateId(templateNodeDef.getTemplateId());
		List<TemplateInputDef> templateInputDefList = templateInputDefService
				.getTemplateInputDefAll(templateInputDefQuery);
		if (templateRulesDefList == null) {
			templateRulesDefList = new ArrayList<TemplateRulesDef>();
		}
		Map<String, TemplateRulesDef> templateRulesDefMap = new HashMap<String, TemplateRulesDef>();
		for (TemplateRulesDef templateRulesDef : templateRulesDefList) {
			templateRulesDefMap.put(templateRulesDef.getInputItemId(),
					templateRulesDef);
		}
		if (templateInputDefList != null && templateInputDefList.size() > 0) {
			for (TemplateInputDef templateInputDef : templateInputDefList) {
				if (templateRulesDefMap.containsKey(templateInputDef.getId())) {
					templateRulesDefMap.get(templateInputDef.getId())
							.setItemBpaCode(templateInputDef.getItemCode());
					templateRulesDefMap.get(templateInputDef.getId())
							.setItemBpaName(templateInputDef.getItemName());
				} else {
					TemplateRulesDef templateRulesDef = new TemplateRulesDef();
					templateRulesDef.setInputItemId(templateInputDef.getId());
					templateRulesDef.setTemplateNodeId(templateNodeDefQuery
							.getId());
					templateRulesDef.setItemBpaCode(templateInputDef
							.getItemCode());
					templateRulesDef.setItemBpaName(templateInputDef
							.getItemName());
					templateRulesDefMap.put(templateInputDef.getId(),
							templateRulesDef);
				}
			}
		}
		Collection<TemplateRulesDef> list = templateRulesDefMap.values();
		Result result = new Result(true);
		result.addDefaultModel(RZConstants.LIST, list);
		result.setSuccessMessage("获取业务规则数据成功");
		return result;
	}

	/**
	 * 校验模板中是否存在多个相同指标
	 * 
	 * @param htmlData
	 */
	private List<String> getAllInputItemByHtmlData(String htmlData)
			throws Exception {
		if (!StringUtil.isEmpty(htmlData)) {
			List<String> allItemList = new ArrayList<String>();
			Parser parser = Parser.createParser(htmlData,
					RZConstants.CHARSET_UTF_8);
			// 拿到所有节点，根据节点去判断
			NodeList allNodeList = parser.parse(null);
			if (allNodeList != null && allNodeList.size() > 0) {
				Node node = null;
				String nodeText = null;
				for (int i = 0; i < allNodeList.size(); i++) {
					node = allNodeList.elementAt(i);
					if (node != null) {
						nodeText = node.toPlainTextString();
						List<String> inputItems = RegularUtils
								.getPatternMatcher(nodeText,
										RZConstants.TEMPLATE_INPUT_REGULAR);
						if (inputItems != null && inputItems.size() > 0) {
							for (String item : inputItems) {
								if (allItemList.contains(item)) {
									throw new Exception("模板文件中不能存在相同的指标编码["
											+ item.substring(2,
													item.length() - 1) + "]");
								}
							}
							allItemList.addAll(inputItems);
						}
					}
				}
			}
			return allItemList;
		} else {
			return null;
		}
	}

	@Override
	public Result getTemplatBpaData(String templateId, String htmlData)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("\n执行获取模板指标数据服务开始\n");
		if (StringUtil.isEmpty(htmlData)) {
			throw new Exception("模板预览时，参数htmlData不能为空");
		}
		Result result = new Result(true);
		Map<String, TemplateInputDef> templateInputDefMap = new HashMap<String, TemplateInputDef>();
		if (!StringUtil.isEmpty(templateId)) {
			TemplateInputDefQuery templateInputDefQuery = new TemplateInputDefQuery();
			templateInputDefQuery.setTemplateId(templateId);
			List<TemplateInputDef> realTemplateInputDefs = templateInputDefService
					.getTemplateInputDefAll(templateInputDefQuery);
			if (realTemplateInputDefs != null
					&& realTemplateInputDefs.size() > 0) {
				for (TemplateInputDef templateInputDef : realTemplateInputDefs) {
					templateInputDefMap.put(templateInputDef.getItemCode(),
							templateInputDef);
				}
			}
		}
		List<String> inputItems = getAllInputItemByHtmlData(htmlData);
		List<TemplateInputDef> templateInputDefList = new ArrayList<TemplateInputDef>();
		if (inputItems != null && inputItems.size() > 0) {
			for (String inputItem : inputItems) {
				inputItem = inputItem.substring(2, inputItem.length() - 1);
				TemplateInputDef templateInputDef = new TemplateInputDef();
				templateInputDef.setItemCode(inputItem);
				if (templateInputDefMap.containsKey(inputItem)) {
					templateInputDef.setItemName(templateInputDefMap.get(
							inputItem).getItemName());
				} else {
					templateInputDef.setItemName(inputItem);
				}
				templateInputDefList.add(templateInputDef);
			}
		}

		result.addDefaultModel(RZConstants.LIST, templateInputDefList);
		logger.info("\n执行获取模板指标数据服务结束\n");
		return result;
	}

	@Override
	public Result getPublicMetadata(String sysId) throws Exception {
		// TODO Auto-generated method stub
		Result result = new Result(true);
		List<String> publicTableCodes = basePublicService
				.getPublicMetadataList();
		if (publicTableCodes != null && publicTableCodes.size() > 0) {
			Map<String, JSONObject> metaDataMap = bossMetadataRpcService
					.getBossMetadata4Extract(sysId,
							publicTableCodes.toArray(new String[0]));
			if (metaDataMap != null) {
				// result.addDefaultModel(RZConstants.META_DATA, metadataMap);
				Object[] obj = getTableMetaDataByJSON(metaDataMap);
				if (obj != null && obj.length == 2) {
					result.addDefaultModel(RZConstants.TABLE_DATA, obj[0]);
					result.addDefaultModel(RZConstants.TABLE_COLUMNS_DATA,
							obj[1]);
				}
			}
		}
		result.setSuccessMessage("获取公共数据元数据成功");
		return result;
	}

}
