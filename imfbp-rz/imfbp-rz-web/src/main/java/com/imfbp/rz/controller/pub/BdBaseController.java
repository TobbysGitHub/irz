package com.imfbp.rz.controller.pub;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.imfbp.rz.domain.pub.BeanHelper;
import com.imfbp.rz.domain.pub.SuperBean;
import com.imfbp.rz.domain.pub.SuperHeadBean;
import com.imfbp.rz.enums.BillStatusEnum;
import com.imfbp.rz.pub.IDFSConst;
import com.imfbp.rz.pub.IRZBeanItemsConsts;
import com.imfbp.rz.pub.IRZConsts;
import com.imfbp.rz.service.billno.BillnoService;
import com.imfbp.rz.service.imfbpm.PropertyUtil;
import com.imfbp.rz.util.DateUtil;
import com.platform.common.spring.mvc.controller.BaseController;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.exception.ResultException;
import com.platform.common.utils.query.BaseQuery;

public class BdBaseController extends BaseController {

	private static String START_HOURS = " 00:00:00";

	private static String END_HOURS = " 23:59:59";

	private BillnoService bdBillnoService;

	/**
	 * 得到时间段的开始时间和结束时间
	 * 
	 * @return
	 */
	protected Map<String, String> getStartAndEndDate(String timeBunket) {
		if (!"".equals(timeBunket) && timeBunket != null) {

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(date);
			String startDate = "";
			String endDate = "";
			Map<String, String> result = new HashMap<String, String>();
			try {
				if (("yesterday").equals(timeBunket)) {
					startDate = DateUtil.dateAddDays(today, -1);
					endDate = DateUtil.dateAddDays(today, -1);
				} else if (("thisweek").equals(timeBunket)) {
					startDate = DateUtil.getCurWeekBeginDate(today);
					endDate = today + END_HOURS;
				} else if (("thismonth").equals(timeBunket)) {
					startDate = DateUtil.getNextMonthFirstDay(today, 0);
					endDate = DateUtil.getNextMonthLastDay(today, 0);
				}
				startDate += START_HOURS;
				endDate += END_HOURS;
			} catch (Exception e) {
				e.printStackTrace();
			}
			result.put("startDate", startDate);
			result.put("endDate", endDate);
			return result;
		}
		return null;
	}

	protected void attachModuleValueAndId(ModelAndView mv, HttpServletRequest req) {
		String moduleValue = req.getParameter("moduleValue");
		mv.addObject("moduleValue", moduleValue);
		String id = req.getParameter("billid");
		if (!StringUtil.isEmpty(id)) {
			mv.addObject("id", id);
		} else {
			mv.addObject("id", "");
		}
		mv.addObject("idfsdomain", PropertyUtil.getPropertyByKey(IDFSConst.IDFS_DOMAIN_NAME));
	}

	/**
	 * 将小贷系统的枚举附加的视图中
	 * 
	 * @param mv
	 */
	/*
	 * protected void attachEnum(ModelAndView mv) {
	 * mv.addObject("insuranceEnums", EnumToJsonStringBuilder.enumToJson());
	 * attachLoginInfo(mv); }
	 */

	/**
	 * 将登录组织及用户信息附加到视图中
	 * 
	 * @param mv
	 */
	protected void attachLoginInfo(ModelAndView mv) {
		mv.addObject(IRZConsts.ORG_VALUE, getOrgId());
		mv.addObject(IRZConsts.DEPT_VALUE, getDeptId());
		mv.addObject(IRZConsts.USER_VALUE, getUserId());
	}

	public void beforeQuery(BaseQuery basequery) {
	}

	public void bodybeforeinsert(SuperBean bean) {
		if (bean != null && StringUtil.isEmpty(bean.getPrimaryKey())) {
			bean.setAttribute("tenantId", getTenantId());
		}
	}

	/**
	 * 插入数据前操作
	 * 
	 * @param superHeadBean
	 * @throws Exception
	 */
	public void beforeInsert(SuperBean superHeadBean) throws Exception {
		// 设置机构
		Object obj = BeanHelper.getProperty(superHeadBean, IRZBeanItemsConsts.PKORG);
		if (StringUtil.isEmpty(obj)) {
			superHeadBean.setAttribute(IRZBeanItemsConsts.PKORG, this.getOrgId());
		}
		// 租户id
		superHeadBean.setAttribute(IRZBeanItemsConsts.TENANTID, this.getTenantId());

		// // 设置部门
		obj = BeanHelper.getProperty(superHeadBean, IRZBeanItemsConsts.PKDEPT);
		if (StringUtil.isEmpty(obj)) {
			superHeadBean.setAttribute(IRZBeanItemsConsts.PKDEPT, this.getDeptId());
		}
		// 设置创建人
		superHeadBean.setAttribute(IRZBeanItemsConsts.OPERATOR, this.getUserId());
		// 设置创建时间
		superHeadBean.setAttribute(IRZBeanItemsConsts.OPERATIONTIME, DateUtil.getTs());
	}

	/**
	 * 插入数据前操作
	 * 
	 * @param superHeadBean
	 * @throws Exception
	 */
	public void beforeInsert(SuperHeadBean superHeadBean, String nodeCode) throws Exception {
		// 设置机构
		Object obj = BeanHelper.getProperty(superHeadBean, IRZBeanItemsConsts.PKORG);
		if (StringUtil.isEmpty(obj)) {
			superHeadBean.setAttribute(IRZBeanItemsConsts.PKORG, this.getOrgId());
		}
		// 租户id
		superHeadBean.setAttribute(IRZBeanItemsConsts.TENANTID, this.getTenantId());
		// // 设置部门
		obj = BeanHelper.getProperty(superHeadBean, IRZBeanItemsConsts.PKDEPT);
		if (StringUtil.isEmpty(obj)) {
			superHeadBean.setAttribute(IRZBeanItemsConsts.PKDEPT, this.getDeptId());
		}
		// 设置创建人
		superHeadBean.setAttribute(IRZBeanItemsConsts.OPERATOR, this.getUserId());
		// 设置创建时间
		superHeadBean.setAttribute(IRZBeanItemsConsts.OPERATIONTIME, DateUtil.getTs());
		// // 设置制单人
		superHeadBean.setAttribute(IRZBeanItemsConsts.BILLMAKER, this.getUserId());
		// // 设置制单日期
		superHeadBean.setAttribute(IRZBeanItemsConsts.BILLDATE, DateUtil.getCurDateStr());
		// // 设置单据状态为自由状态
		superHeadBean.setAttribute(IRZBeanItemsConsts.APPROVESTATUS, BillStatusEnum.FREE.getIndex());
		String billNo = bdBillnoService.getBillno(nodeCode);
		// 设置单据号
		superHeadBean.setAttribute(IRZBeanItemsConsts.BILLNO, billNo);
		// 设置单据id
		// superHeadBean.setAttribute(IInsuranceBeanItemsConsts.BILLID, billNo);
	}

	/**
	 * 更新数据前操作
	 * 
	 * @param superHeadBean
	 * @throws ResultException
	 */
	public void beforeUpdate(SuperHeadBean superHeadBean) throws ResultException {
		// 设置修改人
		superHeadBean.setAttribute(IRZBeanItemsConsts.MODIFIER, this.getUserId());
		// 设置修改时间
		superHeadBean.setAttribute(IRZBeanItemsConsts.MODIFITIME, DateUtil.getTs());
	}

	/**
	 * 更新数据前操作
	 * 
	 * @param superHeadBean
	 * @throws ResultException
	 */
	public void beforeUpdate(SuperBean superHeadBean) throws ResultException {
		// 设置修改人
		superHeadBean.setAttribute(IRZBeanItemsConsts.MODIFIER, this.getUserId());
		// 设置修改时间
		superHeadBean.setAttribute(IRZBeanItemsConsts.MODIFITIME, DateUtil.getTs());
	}

	/**
	 * 获取编码
	 * 
	 * @param moduleValue
	 * @return
	 */
	public String getBillCode(String moduleValue) {
		try {
			return bdBillnoService.getBillno(moduleValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存数据后
	 * 
	 * @param superHeadBean
	 * @throws ResultException
	 */
	public void afterInsertOrUpdate(SuperHeadBean superHeadBean) throws ResultException {
	}

	public void setBdBillnoService(BillnoService bdBillnoService) {
		this.bdBillnoService = bdBillnoService;
	}
}
