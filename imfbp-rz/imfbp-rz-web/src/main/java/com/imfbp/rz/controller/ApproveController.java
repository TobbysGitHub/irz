package com.imfbp.rz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.imfbp.rz.controller.pub.BdBaseController;
import com.imfbp.rz.domain.exception.BusinessException;
import com.imfbp.rz.domain.pub.BeanHelper;
import com.imfbp.rz.domain.pub.SuperHeadBean;
import com.imfbp.rz.domain.pubnodevoinfo.PubNodevoinfo;
import com.imfbp.rz.domain.pubnodevoinfo.query.PubNodevoinfoQuery;
import com.imfbp.rz.enums.BillStatusEnum;
import com.imfbp.rz.pub.IMFConst;
import com.imfbp.rz.service.approve.ApproveService;
import com.imfbp.rz.service.imfbpm.IMFBpmService;
import com.imfbp.rz.service.imfbpm.Task;
import com.imfbp.rz.service.message.MessageService;
import com.imfbp.rz.service.pubnodevoinfo.PubNodevoinfoService;
import com.imfbp.rz.service.service.superbean.SuperBeanService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.ResultUtils;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.exception.ResultException;
import com.platform.common.web.result.Result;

import net.sf.json.JSONObject;
import yonyou.bpm.rest.request.AssignInfoItem;
import yonyou.bpm.rest.request.task.TaskQueryParam;

@RestController
public class ApproveController extends BdBaseController {

	private final static Logger logger = Logger.getLogger(ApproveController.class);

	private SuperBeanService superBeanService;

	private PubNodevoinfoService pubNodevoinfoService;

	private ApproveService approveService;

	private MessageService messageService;

	@RequestMapping(value = "imfbpm/startProcess", method = { RequestMethod.POST })
	public @ResponseBody Object startProcess(@RequestParam("datastr") String datastr,
			@RequestParam("nodecode") String nodecode, @RequestParam("assignitems") String assignnodestr,
			HttpServletRequest req, HttpServletResponse resp, ModelMap context) {

		try {
			SuperHeadBean headBean = getBean(datastr, nodecode);
			// 检查 非制单人不允许提交
			// if(!BeanHelper.getBillmaker(headBean).equals(getUserId())){
			// throw new BusinessException("非制单人不允许提交");
			// }

			StringBuffer checkuserids = new StringBuffer();
			StringBuffer taskids = new StringBuffer();

			if (StringUtil.isEmpty(assignnodestr)) {
				AssignInfoItem infoItem = IMFBpmService.assignCheck(headBean, getTenantId(), nodecode, getDeptId(),
						getUserId());

				if (infoItem != null) {
					Result result = new Result();
					result.setSuccess(true);
					result.addDefaultModel("assign", true);
					result.addDefaultModel("assignees", infoItem);
					return result;
				}
			}
			AssignInfoItem infoItems = null;
			if (!StringUtil.isEmpty(assignnodestr)) {
				JSONObject jsonobj = JSONObject.fromObject(assignnodestr);
				infoItems = (AssignInfoItem) JSONObject.toBean(jsonobj, AssignInfoItem.class);
			}

			// 第一次提交
			if (BeanHelper.getInstanceId(headBean) == null || "".equalsIgnoreCase(BeanHelper.getInstanceId(headBean)))
				headBean = IMFBpmService.startProcessByNodeCode(headBean, getTenantId(), nodecode, getDeptId(),
						getUserId(), checkuserids, taskids, infoItems);
			// 驳回到制单人后提交 实际是完成任务
			else {
				String auditResultPara = IMFConst.AGREE;
				String commentsPara = IMFConst.COMMENT_AGREE;

				TaskQueryParam parm = new TaskQueryParam();
				List<Task> tasklist = IMFBpmService.auditCheck(headBean, getTenantId(), getDeptId(), getUserId(),
						auditResultPara, parm, BeanHelper.getInstanceId(headBean));
				// 审批通过 及不通过
				headBean = IMFBpmService.auditTask(headBean, getTenantId(), getDeptId(), getUserId(), auditResultPara,
						commentsPara, true, checkuserids, taskids, null, infoItems, tasklist);
			}
			// 发送消息
			if (checkuserids.length() > 0) {
				String content = getUserRealName() + "提交单据:" + BeanHelper.getBillno(headBean) + ",请审批";
				messageService.insertWorkFlowMessage(getUserRealName(), content, checkuserids.toString(), nodecode,
						getTenantId(), taskids.toString(), headBean.getPrimaryKey());
			}
			// xinggh 方便移动端查看待审批数据开始
			else {
				// 此else判断如果当前不存在需要发消息的人，默认为无流程，无流程时需要，需要发一条消息给当前提交人，方便移动端查看待审批数据
				String content = getUserRealName() + "提交单据:" + BeanHelper.getBillno(headBean) + ",请审批";
				messageService.insertWorkFlowMessage(getUserRealName(), content, getUserId(), nodecode, getTenantId(),
						headBean.getPrimaryKey(), headBean.getPrimaryKey());
			}
			// xinggh 方便移动端查看待审批数据结束

			Result result = updateAduitInfo(headBean);
			result.setSuccessMessage(IMFConst.STARTSUCCESSMESSAGE);
			return result;
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			return ResultUtils.getExceptionResult("提交过程出现异常" + e.getMessage());
		}
	}

	/**
	 * 通过节点号与前台json字符串获取bean
	 * 
	 * @param datastr
	 * @param nodecode
	 * @return
	 * @throws Exception
	 */
	private SuperHeadBean getBean(String datastr, String nodecode) throws Exception {
		PubNodevoinfoQuery query = new PubNodevoinfoQuery();
		query.setNodecode(nodecode);
		PubNodevoinfo voinfo = pubNodevoinfoService.getPubNodevoinfoByFuncode(query);
		if (voinfo == null) {
			throw new ResultException("没有单据注册信息！请检查pub_nodevoinfo表");
		}
		JSONObject jsonobject = JSONObject.fromObject(datastr);

		SuperHeadBean headBean = (SuperHeadBean) JSONObject.toBean(jsonobject, Class.forName(voinfo.getVoclassname()));
		return headBean;
	}

	// 审批
	@RequestMapping(value = "imfbpm/auditTask", method = { RequestMethod.POST })
	public Result auditTask(@RequestParam("datastr") String datastr, @RequestParam("nodecode") String nodecode,
			@RequestParam("assignitems") String assignnodestr, HttpServletRequest req, HttpServletResponse resp,
			ModelMap context) {
		try {
			SuperHeadBean headBean = getBean(datastr, nodecode);
			String auditResultPara = req.getParameter("auditResultPara");
			String commentsPara = req.getParameter("commentsPara");
			StringBuffer checkuserids = new StringBuffer();
			StringBuffer taskids = new StringBuffer();
			StringBuffer currtaskid = new StringBuffer();

			String currtime = DateUtil.getTs();

			TaskQueryParam parm = new TaskQueryParam();
			List<Task> tasklist = IMFBpmService.auditCheck(headBean, getTenantId(), getDeptId(), getUserId(),
					auditResultPara, parm, BeanHelper.getInstanceId(headBean));
			// 审批通过 及不通过
			if (IMFConst.AGREE.equalsIgnoreCase(auditResultPara)
					|| IMFConst.DISAGREE.equalsIgnoreCase(auditResultPara)) {

				if (StringUtil.isEmpty(assignnodestr)) {
					AssignInfoItem infoItem = IMFBpmService.auditAssignCheck(getTenantId(), getDeptId(), getUserId(),
							auditResultPara, tasklist);

					if (infoItem != null) {
						Result result = new Result();
						result.setSuccess(true);
						result.addDefaultModel("assign", true);
						result.addDefaultModel("assignees", infoItem);
						return result;
					}
				}
				AssignInfoItem infoItems = null;
				if (!StringUtil.isEmpty(assignnodestr)) {
					JSONObject jsonobj = JSONObject.fromObject(assignnodestr);
					infoItems = (AssignInfoItem) JSONObject.toBean(jsonobj, AssignInfoItem.class);
				}

				headBean = IMFBpmService.auditTask(headBean, getTenantId(), getDeptId(), getUserId(), auditResultPara,
						commentsPara, false, checkuserids, taskids, currtaskid, infoItems, tasklist);
				// 发送消息
				if (checkuserids.length() > 0) {
					String aduitmess = "";
					if (IMFConst.AGREE.equalsIgnoreCase(auditResultPara)) {
						aduitmess = "审批通过";
					} else if (IMFConst.DISAGREE.equalsIgnoreCase(auditResultPara)) {
						aduitmess = "审批不通过";
					}

					String content = getUserRealName() + aduitmess + "单据:" + BeanHelper.getBillno(headBean) + ",请审批";
					messageService.insertWorkFlowMessage(getUserRealName(), content, checkuserids.toString(), nodecode,
							getTenantId(), taskids.toString(), headBean.getPrimaryKey());
				}
			} else
			// 驳回到制单人
			if (IMFConst.REJECT.equalsIgnoreCase(auditResultPara)) {
				headBean = IMFBpmService.rejectToBillMaker(headBean, getTenantId(), getDeptId(), getUserId(),
						commentsPara, currtaskid);

				String content = getUserRealName() + "驳回单据:" + BeanHelper.getBillno(headBean) + ",请修改单据";
				messageService.insertWorkFlowMessage(getUserRealName(), content, BeanHelper.getBillmaker(headBean),
						nodecode, getTenantId(), taskids.toString(), headBean.getPrimaryKey());
			}

			if (!StringUtil.isEmpty(currtaskid)) {
				messageService.updateApproveFlag(currtaskid.toString());
			} else {
				// 如果不存在任务，则默认任务id为任务id
				messageService.updateApproveFlag(headBean.getPrimaryKey());
			}
			if (!StringUtil.isEmpty(BeanHelper.getInstanceId(headBean))) {
				String deleteTaskids = IMFBpmService.queryDeletedTasks(BeanHelper.getInstanceId(headBean),
						getTenantId(), getUserId(), getDeptId(), currtime);

				if (!StringUtil.isEmpty(deleteTaskids)) {
					messageService.deleteWorkFlowMessage(deleteTaskids);
				}
			} // 将消息删除 xinggh
			else {
				messageService.deleteWorkFlowMessage(headBean.getPrimaryKey());
			}
			// 将消息删除 xinggh

			Result result = updateAduitInfo(headBean);

			// 处理审批后事件
			if (BillStatusEnum.PASS.getIndex() == BeanHelper.getApprovestatus(headBean).intValue()) {
				// 审批通过
				approveService.afterApprovePass(nodecode, headBean);
			}
			result.setSuccessMessage(IMFConst.AUDITSUCCESSMESSAGE);
			return result;
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			return ResultUtils.getExceptionResult("审批过程出现异常：" + e.getMessage());
		}

	}

	// 弃审
	@RequestMapping(value = "imfbpm/queryUserHaveDoneTask", method = { RequestMethod.POST })
	public JsonNode queryUserHaveDoneTask(@RequestParam("datastr") String datastr,
			@RequestParam("nodecode") String nodecode, String taskId, HttpServletRequest req, HttpServletResponse resp,
			ModelMap context) {
		try {
			SuperHeadBean headbean = getBean(datastr, nodecode);
			return IMFBpmService.queryUserHaveDoneTask(BeanHelper.getInstanceId(headbean), headbean.getPrimaryKey(),
					getTenantId(), getUserId(), getDeptId());
		} catch (Exception e) {
			throw new ResultException(e.getMessage());
		}
	}

	@RequestMapping(value = "imfbpm/withDrawTaskDirect", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Object withDrawTaskDirect(@RequestParam("datastr") String datastr,
			@RequestParam("nodecode") String nodecode, HttpServletRequest req, HttpServletResponse resp,
			ModelMap context) {
		Result result = new Result();
		try {
			SuperHeadBean headbean = getBean(datastr, nodecode);

			approveService.withDrawPassBill(nodecode, headbean);
			// 非审批流，弃审变为自由态
			BeanHelper.setApprovestatus(headbean, BillStatusEnum.FREE.getIndex());
			BeanHelper.setApproveid(headbean, null);
			BeanHelper.setApprovenote(headbean, null);
			BeanHelper.setApprovedate(headbean, null);

			updateAduitInfo(headbean);
			result.setSuccess(true);
			result.addDefaultModel("datas", headbean);
			result.setSuccessMessage(IMFConst.WITHDRAWSUCCESSMESSAGE);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			return ResultUtils.getExceptionResult(e.getMessage());

		}
		return result;
	}

	// 弃审
	@RequestMapping(value = "imfbpm/withDrawTask", method = { RequestMethod.POST })
	public @ResponseBody Object withDrawTask(@RequestParam("datastr") String datastr,
			@RequestParam("nodecode") String nodecode, HttpServletRequest req, HttpServletResponse resp,
			ModelMap context) {
		try {
			String taskId = "";

			String currtime = DateUtil.getTs();

			SuperHeadBean headbean = getBean(datastr, nodecode);
			// 先查询历史
			JsonNode jsondata = IMFBpmService.queryUserHaveDoneTask(BeanHelper.getInstanceId(headbean),
					headbean.getPrimaryKey(), getTenantId(), getUserId(), getDeptId());
			if (jsondata != null && jsondata instanceof ArrayNode) {
				ArrayNode aryNode = (ArrayNode) jsondata;
				if (aryNode.size() > 0) {
					JsonNode firstnode = aryNode.get(0);

					taskId = firstnode.get("id").asText();
				} else {
					throw new BusinessException("没有可弃审的任务");
				}
			} else {
				throw new BusinessException("没有可弃审的任务");
			}

			IMFBpmService.withDrawTask(taskId, getTenantId(), getDeptId(), getUserId());

			String newtaskid = IMFBpmService.queryUserPendingTask(BeanHelper.getInstanceId(headbean),
					headbean.getPrimaryKey(), getTenantId(), getUserId(), getDeptId());
			// 发送消息
			String content = getUserRealName() + "弃审单据:" + BeanHelper.getBillno(headbean) + ",请审批";
			messageService.insertWorkFlowMessage(getUserRealName(), content, getUserId(), nodecode, getTenantId(),
					newtaskid, headbean.getPrimaryKey());

			String deleteTaskids = IMFBpmService.queryDeletedTasks(BeanHelper.getInstanceId(headbean), getTenantId(),
					getUserId(), getDeptId(), currtime);

			if (!StringUtil.isEmpty(deleteTaskids)) {
				messageService.deleteWorkFlowMessage(deleteTaskids);
			}

			Result result = updateAduitInfo(headbean);
			result.setSuccessMessage(IMFConst.WITHDRAWSUCCESSMESSAGE);
			return result;
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			return ResultUtils.getExceptionResult(e.getMessage());
		}
	}

	@RequestMapping(value = "imfbpm/callBack", method = { RequestMethod.POST })
	public @ResponseBody Object callBack(@RequestParam("datastr") String datastr,
			@RequestParam("nodecode") String nodecode, String taskId, HttpServletRequest req, HttpServletResponse resp,
			ModelMap context) {
		try {
			SuperHeadBean headBean = getBean(datastr, nodecode);
			// if(!BeanHelper.getBillmaker(headBean).equals(getUserId())){
			// throw new BusinessException("非制单人不允许收回");
			// }
			String flowinstanceid = BeanHelper.getInstanceId(headBean);
			if (!StringUtil.isEmpty(flowinstanceid)) {
				IMFBpmService.callback(headBean, getTenantId(), getDeptId(), getUserId());
			}
			if (!StringUtil.isEmpty(BeanHelper.getInstanceId(headBean))) {
				String deleteTaskids = IMFBpmService.queryDeletedTasks(BeanHelper.getInstanceId(headBean),
						getTenantId(), getUserId(), getDeptId(), "");

				if (!StringUtil.isEmpty(deleteTaskids)) {
					messageService.deleteWorkFlowMessage(deleteTaskids);
				}
			} else {
				messageService.deleteWorkFlowMessage(headBean.getPrimaryKey());
			}

			BeanHelper.setApprovestatus(headBean, BillStatusEnum.FREE.getIndex());
			BeanHelper.setInstanceId(headBean, "");

			Result result = updateAduitInfo(headBean);
			result.setSuccessMessage(IMFConst.WITHDRAWSUCCESSMESSAGE);
			return result;
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			return ResultUtils.getExceptionResult(e.getMessage());
		}
	}

	private Result updateAduitInfo(SuperHeadBean headBean) {
		Result result = null;
		try {
			// result = xdPreLoanappService.insertOrUpdate(xdPreLoanapp);

			beforeUpdate(headBean);
			result = superBeanService.updateSuperHeaderBean(headBean);

		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			return ResultUtils.getExceptionResult(e.getMessage());

		}
		return result;
	}

	// fulq 审批相关代码 end

	@RequestMapping(value = "imfbpm/processInstancediagram", method = { RequestMethod.POST, RequestMethod.GET })
	protected void getProcessInstanceDiagramJson(String processInstanceId, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			// processInstanceId = "f983c319-1d80-11e6-b107-184f32029040";
			ObjectNode img = IMFBpmService.getProcessInstanceDiagramJson(processInstanceId, getTenantId(), getUserId(),
					getDeptId());
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().print(img);
		} catch (Throwable t) {
			logger.debug(t.getMessage(), t);
			throw new ResultException("查询流程图出错");
		}
	}

	@RequestMapping(value = "imfbpm/getHighlightsProcessInstance", method = { RequestMethod.POST, RequestMethod.GET })
	protected void getHighlightsProcessInstance(String processInstanceId, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			// processInstanceId = "f983c319-1d80-11e6-b107-184f32029040";
			ObjectNode img = IMFBpmService.getHighlightsProcessInstance(processInstanceId, getTenantId(), getUserId(),
					getDeptId());
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().print(img);
		} catch (Throwable t) {
			logger.debug(t.getMessage(), t);
			throw new ResultException("查询流程图出错");
		}
	}

	@RequestMapping(value = "imfbpm/queryInstanceAllHistoryTaskRecordList", method = { RequestMethod.POST,
			RequestMethod.GET })
	public JsonNode queryInstanceAllHistoryTaskRecordList(String processInstanceId, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			// processInstanceId = "f983c319-1d80-11e6-b107-184f32029040";
			return IMFBpmService.queryInstanceAllHistoryTaskRecordList(processInstanceId, getTenantId(), getUserId(),
					getDeptId());
		} catch (Throwable t) {
			logger.debug(t.getMessage(), t);
			throw new ResultException("查询流程图出错");
		}
	}

	public void setSuperBeanService(SuperBeanService superBeanService) {
		this.superBeanService = superBeanService;
	}

	public void setPubNodevoinfoService(PubNodevoinfoService pubNodevoinfoService) {
		this.pubNodevoinfoService = pubNodevoinfoService;
	}

	public void setApproveService(ApproveService approveService) {
		this.approveService = approveService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

}
