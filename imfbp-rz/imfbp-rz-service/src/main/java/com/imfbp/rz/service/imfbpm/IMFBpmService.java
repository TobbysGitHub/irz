package com.imfbp.rz.service.imfbpm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import yonyou.bpm.rest.BpmRest;
import yonyou.bpm.rest.BpmRests;
import yonyou.bpm.rest.HistoryService;
import yonyou.bpm.rest.RuntimeService;
import yonyou.bpm.rest.exception.RestException;
import yonyou.bpm.rest.param.BaseParam;
import yonyou.bpm.rest.request.AssignCheckParam;
import yonyou.bpm.rest.request.AssignInfo;
import yonyou.bpm.rest.request.AssignInfoItem;
import yonyou.bpm.rest.request.RestVariable;
import yonyou.bpm.rest.request.TaskActionTypeEnum;
import yonyou.bpm.rest.request.historic.HistoricTaskQueryParam;
import yonyou.bpm.rest.request.runtime.ProcessInstanceStartParam;
import yonyou.bpm.rest.request.task.TaskActionParam;
import yonyou.bpm.rest.request.task.TaskQueryParam;

import com.alibaba.dubbo.config.annotation.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.ifbp.boss.rpc.smallconditionItem.domain.SmallConditionItem;
import com.ifbp.boss.rpc.smallconditionItem.domain.query.SmallConditionItemQuery;
import com.imfbp.rz.domain.exception.BusinessException;
import com.imfbp.rz.domain.pub.BeanHelper;
import com.imfbp.rz.domain.pub.SuperHeadBean;
import com.imfbp.rz.enums.BillStatusEnum;
import com.imfbp.rz.pub.IRZConsts;
import com.imfbp.rz.pub.IMFConst;
import com.imfbp.rz.service.imfbpm.PropertyUtil;
import com.imfbp.rz.service.imfbpm.Task;
import com.imfbp.rz.service.message.MessageService;
import com.imfbp.rz.service.metadata.MetaDataService;
import com.imfbp.rz.util.ConvertUtil;
import com.imfbp.rz.util.DateUtil;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.exception.ResultException;
import com.platform.common.utils.json.JsonUtils;

/**
 * 
 * 流程 相关服务 2016年5月12日上午10:45:50
 *
 * 创建人：fulq
 * 
 * 
 * admin 442538b4-bad3-4f4f-8756-3e10a2f0ce31 flq *
 * 0b72183e-2083-11e6-b8e5-28d244b2647f hys 500e526a-17f9-11e6-91d6-28d244b2647f
 * qtds 9a35a0fd-17f9-11e6-91d6-28d244b2647f u1
 * a1803b90-17f9-11e6-91d6-28d244b2647f
 *
 * 完成主要功能:
 *
 */
@Service
@Component("IMFBpmService")
public class IMFBpmService {

	@Autowired
	private static MetaDataService metaDataService;

	@Autowired
	private MessageService messageService;

	public void setMetaDataService(MetaDataService metaDataService) {
		IMFBpmService.metaDataService = metaDataService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	/**
	 * 获取我已经审批的单据号集合,在com.imfbp.loan.mobile.service.myapprove.impl.
	 * MobileMyApproveServiceImpl类中的getBillData()方法处调用
	 * 
	 * @param tenantCode
	 *            租户id
	 * @param currUserDept
	 *            机构id
	 * @param nodeCode
	 *            节点编码
	 * @param isFinished
	 *            是否审批结束
	 * @param currentUserId
	 *            用户id
	 * @param queryDateType 
	 * @param endQueryDate 
	 * @param beginQueryDate 
	 * @param size
	 *            每页显示数
	 * @param start
	 *            起始页数
	 * @return
	 * @throws Exception
	 */
	public static List<String> getMyHistoryTask(String tenantCode,
			String currUserDept, String nodeCode, String currentUserId, Integer queryDateType, String beginQueryDate, String endQueryDate) throws Exception {
		HistoryService ht = getBpmRest(tenantCode, currUserDept, currentUserId)
				.getHistoryService();// 历史服务
		HistoricTaskQueryParam historyParam = new HistoricTaskQueryParam();
		// 参数传过来 是否完成
		historyParam.setFinished(true);
		// 节点号 +用户id
		historyParam.setCategoryId(nodeCode + "#" + currentUserId);
		// 用户id
		historyParam.setTaskAssignee(currentUserId);
		historyParam.setReturnHistoricProcessInstance(true);
		historyParam.setOrder("desc");
		historyParam.setSort("endTime");
		
		String curdatestr = DateUtil.getCurDateStr();
		Date endDate  = null;
		switch (queryDateType) {
		case 0:
			// 当天
			historyParam.setTaskCompletedBefore(DateUtil.getDateByStr(curdatestr + " 23:59:59"));
			historyParam.setTaskCompletedAfter(DateUtil.getDateByStr(curdatestr + " 00:00:00"));
			break;
		case 1:
			// 本周
			historyParam.setTaskCompletedBefore(DateUtil.getDateByStr(curdatestr + " 23:59:59"));
			endDate = DateUtil.getDateByStr(DateUtil.getCurWeekBeginDate(curdatestr) + " 00:00:00");
			historyParam.setTaskCompletedAfter(endDate);
			break;
		case 2:
			// 本月
			historyParam.setTaskCompletedBefore(DateUtil.getDateByStr(curdatestr + " 23:59:59"));
			endDate = DateUtil.getDateByStr(DateUtil.getNextMonthFirstDay(curdatestr, 0) + " 00:00:00");
			historyParam.setTaskCompletedAfter(endDate);
			break;
		case 3:
			// 最近三个月
			historyParam.setTaskCompletedBefore(DateUtil.getDateByStr(curdatestr + " 23:59:59"));
			endDate = DateUtil.getDateByStr(DateUtil.dateAddMonth(curdatestr, -3) + " 00:00:00");
			historyParam.setTaskCompletedAfter(endDate);
			break;
		case 4:
			// 最近半年
			historyParam.setTaskCompletedBefore(DateUtil.getDateByStr(curdatestr + " 23:59:59"));
			endDate = DateUtil.getDateByStr(DateUtil.dateAddMonth(curdatestr, -6) + " 00:00:00");
			historyParam.setTaskCompletedAfter(endDate);
			break;
		case 5:
			// 选择区间
			historyParam.setTaskCompletedBefore(DateUtil.getDateByStr(endQueryDate + " 23:59:59"));
			historyParam.setTaskCompletedAfter(DateUtil.getDateByStr(beginQueryDate  + " 00:00:00"));
			break;
		}
		try{
			JsonNode jsonNode = (JsonNode) ht
					.getHistoricTaskInstances(historyParam);
		
			dealTaskComments(jsonNode);
			ArrayNode taskArrNode = (ArrayNode) jsonNode.get("data");
			List<String> billnoList = new ArrayList<String>();
			for (int i = 0; i < taskArrNode.size(); i++) {
				ObjectNode tempTaskNode = (ObjectNode) taskArrNode.get(i);
				if (!StringUtil
						.isEmpty(tempTaskNode.get("historicProcessInstance"))
						&& !StringUtil.isEmpty(tempTaskNode.get(
								"historicProcessInstance").get("businessKey"))) {
					billnoList.add(tempTaskNode.get("historicProcessInstance")
							.get("businessKey").toString());
				}
			}
			
			return billnoList;
		} catch (Exception e) {
			String restExcepMsg = e.getMessage();
			if (StringUtils.contains(restExcepMsg, IMFConst.NOTSETFLOWFLAG)) {
				return null;
			} else {
				throw new BusinessException("查询已审批表单出错:" + restExcepMsg);
			}
		}
	}

	public static AssignInfoItem assignCheck(SuperHeadBean headBean,
			String tenantCode, String nodeCode, String currentUserDept,
			String startUserId) throws Exception {
		try {
			String nodeCodeAndUserId = nodeCode + "#" + startUserId;
			AssignCheckParam assignCheckParam = new AssignCheckParam();
			assignCheckParam.setProcessDefinitionId(nodeCodeAndUserId);
			assignCheckParam.setActivityId("##initial");
			RuntimeService identityService = getBpmRest(tenantCode,
					currentUserDept, startUserId).getRuntimeService();

			ObjectNode assignCheckResult = (ObjectNode) identityService
					.assignCheck(assignCheckParam);
			AssignInfo assignInfo = JsonUtils.fromJson(
					assignCheckResult.get("assignInfo").toString(),
					AssignInfo.class);
			if (assignInfo != null) {
				// 获取参与人 获取后弹到前台 让客户选择
				AssignInfoItem[] infoItems = assignInfo.getAssignInfoItems();
				if (infoItems != null && infoItems.length > 0) {
					return infoItems[0];
				}
			}
		} catch (Exception e) {
			String restExcepMsg = e.getMessage();
			if (StringUtils.contains(restExcepMsg, IMFConst.NOTSETFLOWFLAG)) {
				return null;
			} else {
				throw new BusinessException("启动流程出错:" + restExcepMsg);
			}
		}
		return null;
	}

	public static AssignInfoItem auditAssignCheck(String tenantCode,
			String currentUserDept, String auditUserId, String auditResult,
			List<Task> taskList) throws Exception {
		if (taskList == null || taskList.size() <= 0) {
			return null;
		}
		Task currentTask = taskList.get(0);
		int agreeNum = 0;
		List<RestVariable> quryVariablelist = new ArrayList<RestVariable>(1);
		RestVariable pv = new RestVariable();
		pv.setName(IMFConst.AGREENUM);
		quryVariablelist.add(pv);
		// 同意数量
		RestVariable pv2 = new RestVariable();
		pv2.setName(IMFConst.CURRENTACTIVITY);
		quryVariablelist.add(pv2);
		ArrayNode queyrVariableValue = (ArrayNode) getBpmRest(tenantCode,
				currentUserDept, auditUserId).getTaskService()
				.getTaskVariableCollection(currentTask.getId(),
						IMFConst.GLOBALEVARIABLESCOPE, quryVariablelist);
		int dbAgreeNum = 0;
		String lastActivity = null;
		for (int i = 0; i < queyrVariableValue.size(); i++) {
			ObjectNode tempVariable = (ObjectNode) queyrVariableValue.get(i);
			if (IMFConst.AGREENUM.equalsIgnoreCase(tempVariable.get("name")
					.asText()))
				dbAgreeNum = tempVariable.get("value").asInt();
			else if (IMFConst.CURRENTACTIVITY.equalsIgnoreCase(tempVariable
					.get("name").asText()))
				lastActivity = tempVariable.get("value").asText();
		}
		if (IMFConst.AGREE.equalsIgnoreCase(auditResult)) {
			if (lastActivity.equalsIgnoreCase(currentTask
					.getTaskDefinitionKey()))
				agreeNum = dbAgreeNum + 1;
			else {
				agreeNum = 1;
			}
		} else {
			agreeNum = dbAgreeNum;

		}
		AssignCheckParam assignCheckParam = new AssignCheckParam();
		assignCheckParam.setTaskId(currentTask.getId());
		assignCheckParam.setExecutionId(auditResult + "#" + agreeNum + "#"
				+ dbAgreeNum);
		RuntimeService identityService = getBpmRest(tenantCode,
				currentUserDept, auditUserId).getRuntimeService();

		ObjectNode assignCheckResult = (ObjectNode) identityService
				.assignCheck(assignCheckParam);
		AssignInfo assignInfo = JsonUtils.fromJson(
				assignCheckResult.get("assignInfo").toString(),
				AssignInfo.class);
		if (assignInfo != null) {
			// 获取参与人 获取后弹到前台 让客户选择
			AssignInfoItem[] infoItems = assignInfo.getAssignInfoItems();
			if (infoItems != null && infoItems.length > 0) {
				return infoItems[0];
			}
		}

		return null;
	}

	/**
	 * 根据节点号 启动流程
	 * 
	 * @param headBean
	 * @param tenantCode
	 * @param nodeCode
	 * @param currentUserDept
	 * @param startUserId
	 * @param checkuserids
	 * @param taskids
	 * @param infoItems
	 * @return
	 * @throws Exception
	 */
	public static SuperHeadBean startProcessByNodeCode(SuperHeadBean headBean,
			String tenantCode, String nodeCode, String currentUserDept,
			String startUserId, StringBuffer checkuserids,
			StringBuffer taskids, AssignInfoItem infoItems) throws Exception {
		try {
			if (headBean == null || tenantCode == null || nodeCode == null
					|| currentUserDept == null || startUserId == null)
				throw new ResultException("传入参数不能为空");

			List<RestVariable> list = getStartVariable(headBean, tenantCode,
					nodeCode, startUserId);
			String billno = BeanHelper.getBillno(headBean);
			String nodeCodeAndUserId = nodeCode + "#" + startUserId;
			RuntimeService identityService = getBpmRest(tenantCode,
					currentUserDept, startUserId).getRuntimeService();
			ProcessInstanceStartParam startParam = new ProcessInstanceStartParam();
			startParam.setVariables(list);
			startParam.setProcessDefinitionId(nodeCodeAndUserId);
			startParam.setBusinessKey(billno);
			startParam.setIsNext(true);
			startParam.setAssignee(startUserId);
			// startParam.setMessage("同意启动");
			startParam.setReturnTaskParticipants(true);

			// 指定构造参数
			if (infoItems != null) {
				AssignInfo info = new AssignInfo();
				info.setAssignInfoItems(new AssignInfoItem[] { infoItems });
				startParam.setAssignInfo(info);
			}
			// 指派参数构造 end
			ObjectNode returnobj = (ObjectNode) identityService
					.startProcess(startParam);

			// 处理代办人
			if (returnobj != null && returnobj instanceof ObjectNode) {
				ObjectNode obj = (ObjectNode) returnobj;
				ArrayNode tempData = (ArrayNode) obj.get("tasks");
				if (tempData != null && tempData.size() > 0) {
					for (int i = 0; i < tempData.size(); i++) {
						Task task = JsonUtils.fromJson(tempData.get(i)
								.toString(), Task.class);
						checkuserids.append(task.getAssignee());
						checkuserids.append(",");
						taskids.append(task.getId());
						taskids.append(",");
					}
				}
				if (checkuserids.length() > 0) {
					checkuserids.deleteCharAt(checkuserids.length() - 1);
				}
				if (taskids.length() > 0) {
					taskids.deleteCharAt(taskids.length() - 1);
				}
			}

			BeanHelper.setInstanceId(headBean, returnobj.get("id").asText());
			BeanHelper.setApprovestatus(headBean,
					BillStatusEnum.STARTED.getIndex());
		} catch (Exception e) {
			String restExcepMsg = e.getMessage();
			if (StringUtils.contains(restExcepMsg, IMFConst.NOTSETFLOWFLAG)) {
				BeanHelper.setApprovestatus(headBean,
						BillStatusEnum.STARTED.getIndex());
			} else {
				throw new Exception("启动流程出错:" + restExcepMsg);
			}
		}
		return headBean;
	}

	/**
	 * 生成 启动流程的 变量
	 * 
	 * @param headBean
	 * @param tenantCode
	 * @param nodeCode
	 * @param startUserId
	 * @return
	 */
	private static List<RestVariable> getStartVariable(SuperHeadBean headBean,
			String tenantCode, String nodeCode, String startUserId)
			throws Exception {
		List<RestVariable> list = new ArrayList<RestVariable>(1);
		RestVariable pv1 = new RestVariable();
		pv1.setName(IMFConst.CURRENTACTIVITY);
		pv1.setScope(IMFConst.GLOBALEVARIABLESCOPE);
		pv1.setType("string");
		pv1.setValue("startActiviy");// 当前活动
		list.add(pv1);
		RestVariable pv2 = new RestVariable();
		pv2.setName(IMFConst.AGREENUM);
		pv2.setScope(IMFConst.GLOBALEVARIABLESCOPE);
		pv2.setType("integer");// double string integer
		pv2.setValue(0);
		list.add(pv2);

		// ///////////////////////////////////////////////////////////////////////////////////
		// 根据租户+节点号 查询 业务单据条件表 循环设置 放到 list 当成任务的变量 数字的 value 不要加 引号 默认 不加
		SmallConditionItemQuery smallConditionItemQuery = new SmallConditionItemQuery();
		smallConditionItemQuery.setFunCode(nodeCode);
		List<SmallConditionItem> smallConditionItemLists = metaDataService
				.getBossConditionItemByCondition(smallConditionItemQuery);
		if (smallConditionItemLists != null
				&& smallConditionItemLists.size() > 0) {
			Object obj = null;
			for (SmallConditionItem smallConditionItem : smallConditionItemLists) {
				RestVariable restVariable = new RestVariable();
				String itemCode = smallConditionItem.getItemCode();
				String fielditemCode = ConvertUtil
						.firstLowerCamelCase(itemCode);
				if (!StringUtils.isEmpty(itemCode)) {
					restVariable.setName(itemCode);
					restVariable.setScope(IMFConst.GLOBALEVARIABLESCOPE);
					obj = BeanHelper.getProperty(headBean, fielditemCode);
					String fieldType = BeanHelper.getFieldType(headBean,
							fielditemCode);
					if (!StringUtils.isEmpty(fieldType)
							&& fieldType.equals(java.lang.Integer.class
									.getName())) {
						restVariable.setType(IRZConsts.INTEGER_TYPE);
					} else if (!StringUtils.isEmpty(fieldType)
							&& fieldType.equals(java.lang.Double.class
									.getName())) {
						restVariable.setType(IRZConsts.DOUBLE_TYPE);
					} else {
						restVariable.setType(IRZConsts.STRING_TYPE);
					}
					restVariable.setValue(obj);
					list.add(restVariable);
				}
			}
		}
		//

		return list;

	}

	@SuppressWarnings("unused")
	public static SuperHeadBean callback(SuperHeadBean headBean,
			String tenantCode, String currentUserDept, String startUserId)
			throws Exception {

		if (headBean == null
				|| BeanHelper.getApprovestatus(headBean).intValue() != BillStatusEnum.STARTED
						.getIndex()) {
			throw new BusinessException("收回过程出错:不允许收回非提交态单据!");
		}

		String flowinstanceid = BeanHelper.getInstanceId(headBean);
		try {
			Object withDrawReturn = getBpmRest(tenantCode, currentUserDept,
					startUserId).getRuntimeService().deleteProcessInstance(flowinstanceid);
			
			
		} catch (RestException e) {
			throw new BusinessException("收回过程出错:" + e.getMessage());
		}
		return headBean;
	}

	/**
	 * 审批任务
	 * 
	 * @param headBean
	 * @param tenantCode
	 * @param currentUserDept
	 * @param auditUserId
	 * @param auditResult
	 * @param comments
	 * @param checkuserids
	 * @param taskids
	 * @param currtaskid
	 * @param infoItems
	 * @param tasklist2
	 * @throws Exception
	 */
	public static SuperHeadBean auditTask(SuperHeadBean headBean,
			String tenantCode, String currentUserDept, String auditUserId,
			String auditResult, String comments, boolean haveRejectedFlag,
			StringBuffer checkuserids, StringBuffer taskids,
			StringBuffer currtaskid, AssignInfoItem infoItems,
			List<Task> tasklist) throws Exception {
		try {
			// 所有参赛校验

			String processInstanceId = BeanHelper.getInstanceId(headBean);
			int billStatus = BeanHelper.getApprovestatus(headBean);
			// 未找到审批流程实例,并且单据状态为提交状态，实现业务单据状态修改
			if (StringUtils.isEmpty(processInstanceId)
					&& billStatus == BillStatusEnum.STARTED.getIndex()) {
				if (IMFConst.AGREE.equalsIgnoreCase(auditResult)) {
					BeanHelper.setApprovestatus(headBean,
							BillStatusEnum.PASS.getIndex());
				} else {
					BeanHelper.setApprovestatus(headBean,
							BillStatusEnum.NOPASS.getIndex());
				}
				BeanHelper.setApproveid(headBean, auditUserId);
				BeanHelper.setApprovenote(headBean, comments);
				BeanHelper.setApprovedate(headBean, DateUtil.getTs());
				return headBean;
			}

			Task currentTask = tasklist.get(0);
			String taskid = currentTask.getId();
			int agreeNum = 0;

			List<RestVariable> quryVariablelist = new ArrayList<RestVariable>(1);
			RestVariable pv = new RestVariable();
			pv.setName(IMFConst.AGREENUM);
			quryVariablelist.add(pv);
			// 同意数量
			RestVariable pv2 = new RestVariable();
			pv2.setName(IMFConst.CURRENTACTIVITY);
			quryVariablelist.add(pv2);
			ArrayNode queyrVariableValue = (ArrayNode) getBpmRest(tenantCode,
					currentUserDept, auditUserId).getTaskService()
					.getTaskVariableCollection(taskid,
							IMFConst.GLOBALEVARIABLESCOPE, quryVariablelist);
			int dbAgreeNum = 0;
			String lastActivity = null;
			for (int i = 0; i < queyrVariableValue.size(); i++) {
				ObjectNode tempVariable = (ObjectNode) queyrVariableValue
						.get(i);
				if (IMFConst.AGREENUM.equalsIgnoreCase(tempVariable.get("name")
						.asText()))
					dbAgreeNum = tempVariable.get("value").asInt();
				else if (IMFConst.CURRENTACTIVITY.equalsIgnoreCase(tempVariable
						.get("name").asText()))
					lastActivity = tempVariable.get("value").asText();
			}
			BeanHelper.setApprovenote(headBean, IMFConst.COMMENT_AGREE
					+ comments);
			if (IMFConst.AGREE.equalsIgnoreCase(auditResult)) {
				if (lastActivity.equalsIgnoreCase(currentTask
						.getTaskDefinitionKey()))
					agreeNum = dbAgreeNum + 1;
				else {
					agreeNum = 1;
				}
				comments = IMFConst.COMMENT_AGREE_FLAG + IMFConst.COMMENT_AGREE
						+ comments;
			} else {
				agreeNum = dbAgreeNum;
				comments = IMFConst.COMMENT_DISAGREE_FLAG
						+ IMFConst.COMMENT_DISAGREE + comments;

			}
			lastActivity = currentTask.getTaskDefinitionKey();
			List<RestVariable> list = getAuditVariable(auditResult, agreeNum,
					lastActivity);
			TaskActionParam taskActionParam = new TaskActionParam();
			taskActionParam.setTaskVariables(list);
			taskActionParam.setAction(TaskActionTypeEnum.complete);
			taskActionParam.setReturnHistoricProcessInstance(true);
			taskActionParam.setMessage(comments);
			taskActionParam.setReturnTaskParticipants(true);
			taskActionParam.setReturnTasks(true);
			taskActionParam.setReturnHistoricActivityInstances(true);

			// 指定构造参数
			if (infoItems != null) {
				AssignInfo info = new AssignInfo();
				info.setAssignInfoItems(new AssignInfoItem[] { infoItems });
				taskActionParam.setAssignInfo(info);
			}
			// 指定构造参数 end
			ObjectNode returnobj = (ObjectNode) getBpmRest(tenantCode,
					currentUserDept, auditUserId).getTaskService().actionTask(
					tasklist.get(0).getId(), taskActionParam);

			// 处理代办人
			if (returnobj != null && returnobj instanceof ObjectNode) {
				ObjectNode obj = (ObjectNode) returnobj;

				if (currtaskid != null) {
					currtaskid.append(obj.get("taskId").asText());
				}

				ObjectNode tempData = (ObjectNode) obj
						.get("historicProcessInstance");

				if (!(tempData.get("tasks") instanceof NullNode)) {
					ArrayNode taskNodes = (ArrayNode) tempData.get("tasks");
					if (taskNodes != null && taskNodes.size() > 0) {
						for (int i = 0; i < taskNodes.size(); i++) {
							Task task = JsonUtils.fromJson(taskNodes.get(i)
									.toString(), Task.class);
							checkuserids.append(task.getAssignee());
							checkuserids.append(",");
							taskids.append(task.getId());
							taskids.append(",");
						}
					}
					if (checkuserids.length() > 0) {
						checkuserids.deleteCharAt(checkuserids.length() - 1);
					}
					if (taskids.length() > 0) {
						taskids.deleteCharAt(taskids.length() - 1);
					}
				}

			}

			// 处理删除

			// 驳回后 再次提交
			if (haveRejectedFlag)
				BeanHelper.setApprovestatus(headBean,
						BillStatusEnum.STARTED.getIndex());
			else {
				String processInsatnceEndTime = returnobj
						.get("historicProcessInstance").get("endTime")
						.textValue();
				// 流程没有结束
				if (processInsatnceEndTime == null)
					BeanHelper.setApprovestatus(headBean,
							BillStatusEnum.APPROVING.getIndex());
				else {
					// 流程没有结束
					if (IMFConst.AGREE.equalsIgnoreCase(auditResult))
						BeanHelper.setApprovestatus(headBean,
								BillStatusEnum.PASS.getIndex());
					else
						BeanHelper.setApprovestatus(headBean,
								BillStatusEnum.NOPASS.getIndex());
				}
			}
			BeanHelper.setApproveid(headBean, auditUserId);
			BeanHelper.setApprovedate(headBean, DateUtil.getTs());
			return headBean;
		} catch (Exception e) {
			throw new Exception("审批过程出错:" + e.getMessage());
		}
	}

	public static List<Task> auditCheck(SuperHeadBean headBean,
			String tenantCode, String currentUserDept, String auditUserId,
			String auditResult, TaskQueryParam parm, String processInstanceId)
			throws RestException {
		if (headBean == null || tenantCode == null || currentUserDept == null
				| auditUserId == null || tenantCode == null
				|| auditResult == null)
			throw new ResultException("传入参数不能为空");

		if (StringUtil.isEmpty(processInstanceId)) {
			return null;
		}

		String billno = BeanHelper.getBillno(headBean);
		parm.setProcessInstanceBusinessKey(billno);
		parm.setProcessInstanceId(processInstanceId);
		ObjectNode objectNode = (ObjectNode) getBpmRest(tenantCode,
				currentUserDept, auditUserId).getTaskService().queryTasksToDo(
				auditUserId, parm);
		List<Task> tasklist = new ArrayList<Task>();
		if (objectNode != null && objectNode instanceof ObjectNode) {
			ObjectNode obj = (ObjectNode) objectNode;
			ArrayNode tempData = (ArrayNode) obj.get("data");
			if (tempData != null && tempData.size() > 0) {
				for (int i = 0; i < tempData.size(); i++) {
					tasklist.add(JsonUtils.fromJson(tempData.get(i).toString(),
							Task.class));
				}
			}
		}
		if (tasklist.size() == 0)
			throw new ResultException("没有审批任务。");
		return tasklist;
	}

	/**
	 * 生成审批时的变量
	 * 
	 * @param audit_resulst
	 * @param agreeNum
	 * @param currentactivity
	 * @return
	 */
	private static List<RestVariable> getAuditVariable(String audit_resulst,
			int agreeNum, String currentactivity) {
		List<RestVariable> list = new ArrayList<RestVariable>(1);
		RestVariable pv = new RestVariable();
		pv.setName(IMFConst.AUDITRESULST);
		pv.setValue(audit_resulst);
		list.add(pv);
		// 同意数量
		RestVariable pv2 = new RestVariable();
		pv2.setName(IMFConst.AGREENUM);
		pv2.setType("integer");
		pv2.setValue(agreeNum);
		list.add(pv2);
		// 当前活动变量
		RestVariable pv3 = new RestVariable();
		pv3.setName(IMFConst.CURRENTACTIVITY);
		pv3.setScope(IMFConst.GLOBALEVARIABLESCOPE);
		pv3.setType("string");
		pv3.setValue(currentactivity);
		list.add(pv3);
		return list;

	}

	/**
	 * 驳回到制单环节
	 * 
	 * @param headBean
	 * @param tenantCode
	 * @param currentUserDept
	 * @param auditUserId
	 * @param auditResult
	 * @param comments
	 * @param checkuserids
	 * @throws Exception
	 */
	public static SuperHeadBean rejectToBillMaker(SuperHeadBean headBean,
			String tenantCode, String currentUserDept, String auditUserId,
			String comments, StringBuffer currtaskid) throws Exception {
		try {
			String assignUser = BeanHelper.getBillmaker(headBean);
			// 测试数据 根据具体业务处理
			// assignUser = "0b72183e-2083-11e6-b8e5-28d244b2647f";
			// auditUserId = "0b72183e-2083-11e6-b8e5-28d244b2647f";
			// tenantCode = "xd_code";
			// currentUserDept = "30b584a6-17f4-11e6-91d6-28d244b2647f";

			if (headBean == null || tenantCode == null
					|| currentUserDept == null | auditUserId == null
					|| tenantCode == null)
				throw new ResultException("传入参数不能为空");

			String processInstanceId = BeanHelper.getInstanceId(headBean);
			int billStatus = BeanHelper.getApprovestatus(headBean);
			// 获取当前任务id开始 xinggh
			Task task = getBpmTask(headBean, tenantCode, currentUserDept,
					auditUserId);
			if (task != null) {
				currtaskid.append(task.getId());
			}
			// 获取当前任务id结束 xinggh
			if (StringUtils.isEmpty(processInstanceId)
					&& (billStatus == BillStatusEnum.STARTED.getIndex() || billStatus == BillStatusEnum.APPROVING
							.getIndex())) {

			} else {
				// 需要查询所有的 变量 循环设置下
				@SuppressWarnings("unused")
				Object returnobj = getBpmRest(tenantCode, currentUserDept,
						assignUser).getRuntimeService()
						.rejectToInitialActivity(
								BeanHelper.getInstanceId(headBean), "驳回到制单环节");
				// xinggh注释开始
				// 处理代办人
				// if (returnobj != null && returnobj instanceof ObjectNode) {
				// ObjectNode obj = (ObjectNode) returnobj;
				//
				// if (currtaskid != null) {
				// currtaskid.append(obj.get("id").asText());
				// }
				// }
				// xinggh注释结束
			}

			BeanHelper.setApprovestatus(headBean,
					BillStatusEnum.FREE.getIndex());
			BeanHelper.setApproveid(headBean, auditUserId);
			BeanHelper.setApprovenote(headBean, comments);
			BeanHelper.setApprovedate(headBean, DateUtil.getTs());
			return headBean;
		} catch (Exception e) {
			throw new Exception("驳回制单人出错:" + e.getMessage());
		}
	}

	/**
	 * 获取实例 上该用户已经做过的任务 用于弃审使用
	 * 
	 * @param headBean
	 * @param tenantCode
	 * @param currentUserDept
	 * @param auditUserId
	 * @param currDeptId
	 * @param auditResult
	 * @param comments
	 */
	public static JsonNode queryUserHaveDoneTask(String processInstanceId,
			String billId, String tenantCode, String auditUserId,
			String currDeptId) {
		try {
			if (processInstanceId == null || billId == null
					|| tenantCode == null || auditUserId == null)
				throw new ResultException("传入参数不能为空");

			HistoricTaskQueryParam paramHistoricTaskQueryParam = new HistoricTaskQueryParam();
			paramHistoricTaskQueryParam.setProcessInstanceId(processInstanceId);
			paramHistoricTaskQueryParam.setFinished(true);
			paramHistoricTaskQueryParam.setReturnTaskComment(true);
			paramHistoricTaskQueryParam.setTaskAssignee(auditUserId);
			paramHistoricTaskQueryParam.setSort("endTime");
			paramHistoricTaskQueryParam.setOrder("desc");

			JsonNode jsonNode = (ObjectNode) getBpmRest(tenantCode, currDeptId,
					auditUserId).getHistoryService().getHistoricTaskInstances(
					paramHistoricTaskQueryParam);

			dealTaskComments(jsonNode);
			return jsonNode.get("data");
		}
		// 异常为处理
		catch (Exception e) {
			throw new ResultException("查询用户完成任务出错:" + e.getMessage());
		}
	}
	
	/**
	 * 获取实例 上该用户待做的任务，弃审后发消息使用
	 * 
	 * @param headBean
	 * @param tenantCode
	 * @param currentUserDept
	 * @param auditUserId
	 * @param currDeptId
	 * @param auditResult
	 * @param comments
	 */
	public static String queryUserPendingTask(String processInstanceId,
			String billId, String tenantCode, String auditUserId,
			String currDeptId) {
		try {
			if (processInstanceId == null || billId == null
					|| tenantCode == null || auditUserId == null)
				throw new ResultException("传入参数不能为空");

			HistoricTaskQueryParam paramHistoricTaskQueryParam = new HistoricTaskQueryParam();
			paramHistoricTaskQueryParam.setProcessInstanceId(processInstanceId);
			paramHistoricTaskQueryParam.setFinished(false);
			paramHistoricTaskQueryParam.setReturnTaskComment(true);
			paramHistoricTaskQueryParam.setTaskAssignee(auditUserId);
			paramHistoricTaskQueryParam.setSort("startTime");
			paramHistoricTaskQueryParam.setOrder("desc");

			JsonNode jsonNode = (ObjectNode) getBpmRest(tenantCode, currDeptId,
					auditUserId).getHistoryService().getHistoricTaskInstances(
					paramHistoricTaskQueryParam);
			String taskId = "";
			dealTaskComments(jsonNode);
			JsonNode jsondata = jsonNode.get("data");
			if(jsondata != null && jsondata instanceof ArrayNode){
				ArrayNode aryNode = (ArrayNode) jsondata;
				if(aryNode.size() > 0){
					JsonNode firstnode = aryNode.get(0);
					
					taskId = firstnode.get("id").asText();
				} else {
					throw new BusinessException("弃审后，当前操作人没有待审批的任务");
				}
			} else {
				throw new BusinessException("弃审后，当前操作人没有待审批的任务");
			}
			return taskId;
		}
		// 异常为处理
		catch (Exception e) {
			throw new ResultException("查询用户待审批任务出错:" + e.getMessage());
		}
	}

	/**
	 * 弃审任务
	 * 
	 * @param taskid
	 * @param tenantCode
	 * @param currentUserDept
	 * @param currentUserId
	 * @param checkuserids
	 * @param taskids
	 * @throws Exception
	 */
	public static void withDrawTask(String taskid, String tenantCode,
			String currentUserDept, String currentUserId) throws Exception {
		try {
			// currentUserId = "0b72183e-2083-11e6-b8e5-28d244b2647f";
			// currentUserId = "6b06c0ed-214a-11e6-8daf-28d244b2647f";
			// tenantCode = "xd_code";
			// currentUserDept = "30b584a6-17f4-11e6-91d6-28d244b2647f";
			// 所有参赛校验
			if (taskid == null || tenantCode == null || currentUserDept == null
					|| currentUserId == null)
				throw new ResultException("传入参数不能为空");
			HistoricTaskQueryParam paramHistoricTaskQueryParam = new HistoricTaskQueryParam();
			paramHistoricTaskQueryParam.setFinished(true);
			paramHistoricTaskQueryParam.setTaskId(taskid);
			paramHistoricTaskQueryParam.setReturnTaskComment(true);
			paramHistoricTaskQueryParam.setIncludeProcessVariables(true);
			paramHistoricTaskQueryParam.setReturnHistoricProcessInstance(true);
			JsonNode jsonNode = (ObjectNode) getBpmRest(tenantCode,
					currentUserDept, currentUserId).getHistoryService()
					.getHistoricTaskInstances(paramHistoricTaskQueryParam);
			String taskComments = jsonNode.get("data").get(0)
					.get("taskComments").get(0).get("message").asText();
			ArrayNode varablesArrNode = (ArrayNode) jsonNode.get("data").get(0)
					.get("variables");
			int agreeNum = 0;
			for (int i = 0; i < varablesArrNode.size(); i++) {
				ObjectNode varablesNode = (ObjectNode) varablesArrNode.get(i);
				String name = varablesNode.get("name").asText();
				if (IMFConst.AGREENUM.equals(name)) {
					agreeNum = varablesNode.get("value").asInt();
					break;
				}
			}
			if (agreeNum > 0 && taskComments.contains(IMFConst.COMMENT_AGREE)) {
				agreeNum = agreeNum - 1;
			}
			List<RestVariable> list = new ArrayList<RestVariable>(1);
			RestVariable pv2 = new RestVariable();
			pv2.setName(IMFConst.AGREENUM);
			pv2.setType("integer");
			pv2.setValue(agreeNum);
			list.add(pv2);
			// 添加 变量
			Object withDrawReturn = getBpmRest(tenantCode, currentUserDept,
					currentUserId).getTaskService().withdrawTaskWithVariables(
					taskid, list);

			// 处理代办人
			if (withDrawReturn != null && withDrawReturn instanceof Boolean) {
				
			}
		}
		// 异常为处理
		catch (Exception e) {
			throw new Exception("弃审出错:" + e.getMessage());
		}

	}

	public static String queryDeletedTasks(String flowinstanceid,
			String tenantCode, String currentUserId, String currUserDept,
			String currTimeStr) {
		try {
			// currentUserId = "0b72183e-2083-11e6-b8e5-28d244b2647f";
			// tenantCode = "xd_code";
			if (flowinstanceid == null || tenantCode == null
					|| currentUserId == null)
				throw new ResultException("传入参数不能为空");
			HistoryService ht = getBpmRest(tenantCode, currUserDept,
					currentUserId).getHistoryService();// 历史服务
			HistoricTaskQueryParam historyParam = new HistoricTaskQueryParam();
			historyParam.setProcessInstanceId(flowinstanceid);
			historyParam.setReturnTaskComment(true);
			historyParam.setReturnParticipants(true);

			historyParam.setSort("startTime");
			historyParam.setOrder("asc");
			JsonNode jsonNode = (JsonNode) ht
					.getHistoricTaskInstances(historyParam);

			ArrayNode taskArrNode = (ArrayNode) jsonNode.get("data");
			StringBuffer deletedTaskIds = new StringBuffer();
			for (int i = 0; i < taskArrNode.size(); i++) {
				ObjectNode tempTaskNode = (ObjectNode) taskArrNode.get(i);

				// 在JSON中过滤删除的历史任务,在界面不展示
				String deleteReason = tempTaskNode.get("deleteReason").asText(
						"");
				if (deleteReason.equals("deleted")) {
					//modfiy by tuxl on 2016-09-07 case : 由于服务器时间不一致导致，bpm服务器时间早于小贷服务器时间,无法使用时间比较  start
//					String endTime = tempTaskNode.get("endTime").asText();
//					if (!StringUtil.isEmpty(endTime)) {
//						DateFormat df = new SimpleDateFormat(
//								"yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
//						SimpleDateFormat format = new SimpleDateFormat(
//					"yyyy-MM-dd HH:mm:ss");
//						String deleteTime = format.format(df.parse(endTime));
//
//						if (currTimeStr.compareTo(deleteTime) <= 0) {
							deletedTaskIds.append(
									tempTaskNode.get("id").asText("")).append(
									",");
//						}
//					}
					//modfiy by tuxl on 2016-09-07 case : 由于服务器时间不一致导致，bpm服务器时间早于小贷服务器时间,无法使用时间比较  end
				}
			}
			if (deletedTaskIds.length() > 0) {
				deletedTaskIds.deleteCharAt(deletedTaskIds.length() - 1);
			}
			return deletedTaskIds.toString();
		} catch (Exception e) {
			throw new ResultException("查询任务历史出错");
		}
	}

	/**
	 * 查询实例的历史任务
	 * 
	 * @param flowinstanceid
	 * @param tenantCode
	 * @param currentUserDept
	 * @param currentUserId
	 * @param currUserDept
	 * @return
	 */

	public static JsonNode queryInstanceAllHistoryTaskRecordList(
			String flowinstanceid, String tenantCode, String currentUserId,
			String currUserDept) {
		try {
			// currentUserId = "0b72183e-2083-11e6-b8e5-28d244b2647f";
			// tenantCode = "xd_code";
			if (flowinstanceid == null || tenantCode == null
					|| currentUserId == null)
				throw new ResultException("传入参数不能为空");
			HistoryService ht = getBpmRest(tenantCode, currUserDept,
					currentUserId).getHistoryService();// 历史服务
			HistoricTaskQueryParam historyParam = new HistoricTaskQueryParam();
			historyParam.setProcessInstanceId(flowinstanceid);
			historyParam.setReturnTaskComment(true);
			historyParam.setReturnParticipants(true);
			historyParam.setReturnHistoricProcessInstance(true);

			historyParam.setSort("startTime");
			historyParam.setOrder("asc");
			JsonNode jsonNode = (JsonNode) ht
					.getHistoricTaskInstances(historyParam);
			dealTaskComments(jsonNode);
			return jsonNode.get("data");
		} catch (Exception e) {
			throw new ResultException("查询任务历史出错");
		}
	}

	private static void dealTaskComments(JsonNode jsonNode) {
		if(jsonNode instanceof NullNode){
			return;
		}
		
		ArrayNode taskArrNode = (ArrayNode) jsonNode.get("data");
		for (int i = 0; i < taskArrNode.size(); i++) {
			ObjectNode tempTaskNode = (ObjectNode) taskArrNode.get(i);

			// 在JSON中过滤删除的历史任务,在界面不展示
			String deleteReason = tempTaskNode.get("deleteReason").asText("");
			if (deleteReason.equals("deleted") || deleteReason.equals("ACTIVITI_DELETED")) {
				taskArrNode.remove(i);
				--i;
			} else {
				if(!tempTaskNode.has("assigneeParticipant") || tempTaskNode
						.get("assigneeParticipant") instanceof NullNode){
					continue;
				}
				ObjectNode tempAssignArrayNode = (ObjectNode) tempTaskNode
						.get("assigneeParticipant");

				if (tempAssignArrayNode.size() > 0) {
					((ObjectNode) taskArrNode.get(i)).set("assignee",
							tempAssignArrayNode.get("name"));
				}

				if (tempTaskNode.get("taskComments") instanceof NullNode) {
					((ObjectNode) taskArrNode.get(i)).set("taskComments", null);
					continue;
				}

				ArrayNode tempArrayNode = (ArrayNode) tempTaskNode
						.get("taskComments");
				if (tempArrayNode.size() == 0) {
					((ObjectNode) taskArrNode.get(i)).set("taskComments", null);
				} else {
					String comments = tempArrayNode.get(0).get("message")
							.asText().replace(IMFConst.COMMENT_AGREE_FLAG, "");
					comments = comments.replace(IMFConst.COMMENT_DISAGREE_FLAG,
							"");
					((ObjectNode) taskArrNode.get(i)).set("taskComments",
							new TextNode(comments));
				}
			}
		}
	}

	/**
	 * 获取 流程图 json
	 * 
	 * @param processDefinitionId
	 * @param flowinstanceid
	 * @param tenantCode
	 * @param currentUserId
	 * @param currUserDept
	 * @return
	 */

	public static ObjectNode getProcessInstanceDiagramJson(
			String flowinstanceid, String tenantCode, String currentUserId,
			String currUserDept) {
		// currentUserId = "0b72183e-2083-11e6-b8e5-28d244b2647f";
		// tenantCode = "xd_code";
		if (flowinstanceid == null || tenantCode == null
				|| currentUserId == null)
			throw new ResultException("传入参数不能为空");
		RuntimeService runtimeSer = getBpmRest(tenantCode, currUserDept,
				currentUserId).getRuntimeService();// 历史服务
		ObjectNode img = null;
		try {
			img = (ObjectNode) runtimeSer.getProcessInstanceDiagramJson(null,
					flowinstanceid);
		} catch (Exception e) {
			throw new ResultException("查询流程图出错");
		}
		return img;
	}

	/**
	 * 获取 流程图 加亮
	 * 
	 * @param flowinstanceid
	 * @param tenantCode
	 * @param currentUserId
	 * @param currDeptid
	 * @return
	 */
	public static ObjectNode getHighlightsProcessInstance(
			String flowinstanceid, String tenantCode, String currentUserId,
			String currDeptid) {
		// currentUserId = "0b72183e-2083-11e6-b8e5-28d244b2647f";
		// tenantCode = "xd_code";
		if (flowinstanceid == null || tenantCode == null
				|| currentUserId == null)
			throw new ResultException("传入参数不能为空");
		RuntimeService runtimeSer = getBpmRest(tenantCode, currDeptid,
				currentUserId).getRuntimeService();
		ObjectNode img = null;
		try {
			img = (ObjectNode) runtimeSer
					.getHighlightsProcessInstance(flowinstanceid);
		} catch (Exception e) {
			throw new ResultException("查询流程图出错");
		}
		return img;
	}

	/**
	 * 获取rest服务
	 * 
	 * @param tenantCode
	 * @return
	 */
	private static BpmRest getBpmRest(String tenantCode,
			String currentUserDept, String currentUserId) {
		try {
			BaseParam baseParam = new BaseParam();

			// 测试
			if (tenantCode == null || currentUserDept == null
					|| currentUserId == null)
				throw new ResultException("传入参数不能为空");
			baseParam
					.setServer(PropertyUtil.getPropertyByKey("bpmrest.server"));
			baseParam.setClientToken(PropertyUtil
					.getPropertyByKey("bpmrest.token"));
			// 租户编码
			baseParam.setTenant(tenantCode);
			baseParam.setOrg(currentUserDept);
			baseParam.setOperatorID(currentUserId);
			BpmRest rest = BpmRests.getBpmRest(baseParam);
			return rest;
		} catch (Exception e) {
			throw new ResultException("调用rest服务时,获取调用参数出错.");
		}
	}

	/**
	 * 查询当前审批任务--xinggh
	 * 
	 * @param headBean
	 * @param tenantCode
	 * @param currentUserDept
	 * @param auditUserId
	 * @return
	 * @throws RestException
	 */
	public static Task getBpmTask(SuperHeadBean headBean, String tenantCode,
			String currentUserDept, String auditUserId) throws RestException {
		TaskQueryParam parm = new TaskQueryParam();
		String processInstanceId = BeanHelper.getInstanceId(headBean);
		String billno = BeanHelper.getBillno(headBean);
		parm.setProcessInstanceBusinessKey(billno);
		parm.setProcessInstanceId(processInstanceId);
		ObjectNode objectNode = (ObjectNode) getBpmRest(tenantCode,
				currentUserDept, auditUserId).getTaskService().queryTasksToDo(
				auditUserId, parm);
		List<Task> tasklist = new ArrayList<Task>();
		if (objectNode != null && objectNode instanceof ObjectNode) {
			ObjectNode obj = (ObjectNode) objectNode;
			ArrayNode tempData = (ArrayNode) obj.get("data");
			if (tempData != null && tempData.size() > 0) {
				for (int i = 0; i < tempData.size(); i++) {
					tasklist.add(JsonUtils.fromJson(tempData.get(i).toString(),
							Task.class));
				}
			}
		}
		if (tasklist.size() > 0) {
			return tasklist.get(0);
		} else {
			return null;
		}
	}
}
