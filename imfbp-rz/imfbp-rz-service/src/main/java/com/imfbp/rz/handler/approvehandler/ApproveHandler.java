package com.imfbp.rz.handler.approvehandler;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.imfbp.rz.domain.pub.SuperHeadBean;
import com.imfbp.rz.enums.BillStatusEnum;
import com.imfbp.rz.pub.IRZBeanItemsConsts;
import com.imfbp.rz.service.billno.BillnoService;
import com.imfbp.rz.util.DateUtil;
import com.platform.common.utils.login.enums.LoginEnum;

public abstract class ApproveHandler {
	
	@Autowired
	private BillnoService bdBillnoService;

	@Transactional
	public abstract void afterApprovePass(SuperHeadBean headBean) throws Exception;
	
	@Transactional
	public abstract void withDrawPassBill(SuperHeadBean headBean) throws Exception;
	/**
	 * 插入数据前操作
	 * 
	 * @param superHeadBean
	 * @throws Exception 
	 */
	public void beforeInsert(SuperHeadBean superHeadBean,String nodeCode)
			throws Exception {
		// 设置机构
		if(superHeadBean.getAttribute(IRZBeanItemsConsts.PKORG) == null 
				|| "".equals(superHeadBean.getAttribute(IRZBeanItemsConsts.PKORG).toString())){
			superHeadBean.setAttribute(IRZBeanItemsConsts.PKORG, this.getOrgId());
		}
		// 设置部门
		if(superHeadBean.getAttribute(IRZBeanItemsConsts.PKDEPT) == null 
				|| "".equals(superHeadBean.getAttribute(IRZBeanItemsConsts.PKDEPT).toString())){
			superHeadBean
					.setAttribute(IRZBeanItemsConsts.PKDEPT, this.getOrgId());
		}
		// 设置创建人
		superHeadBean.setAttribute(IRZBeanItemsConsts.OPERATOR,
				this.getUserId());
		// 设置创建时间
		superHeadBean.setAttribute(IRZBeanItemsConsts.OPERATIONTIME,
				DateUtil.getTs());
		// 设置制单人
		superHeadBean.setAttribute(IRZBeanItemsConsts.BILLMAKER,
				this.getUserId());
		// 设置制单日期
		superHeadBean.setAttribute(IRZBeanItemsConsts.BILLDATE,
				DateUtil.getCurDateStr());
		// 设置单据状态为自由状态
		superHeadBean.setAttribute(IRZBeanItemsConsts.APPROVESTATUS,
				BillStatusEnum.FREE.getIndex());
		// 设置单据状态为自由状态
		String billNo = bdBillnoService.getBillno(nodeCode);
		// 设置单据号
		superHeadBean.setAttribute(IRZBeanItemsConsts.BILLNO, billNo);
	}
	
	
	public void setBdBillnoService(BillnoService bdBillnoService) {
		this.bdBillnoService = bdBillnoService;
	}

	public String getOrgId(){		 
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpSession session = requestAttributes.getRequest().getSession();
		Object object = session.getAttribute(LoginEnum.LONGIN_ORG_ID.getloginInfo());
		String orgId=null;
		if(object !=null){
			orgId=(String) object;
		}
		return orgId;
	}
	
	public String getUserId() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpSession session = requestAttributes.getRequest().getSession();
		Object object = session.getAttribute(LoginEnum.LONGIN_USER_ID.getloginInfo());
		String userId=null;
		if (object != null) {
			userId=(String) object;
		}
		return userId;
	}

	public String getTenantId() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpSession session = requestAttributes.getRequest().getSession();
		Object object = session.getAttribute(LoginEnum.LONGIN_TENANT_ID.getloginInfo());
		String userId=null;
		if (object != null) {
			userId=(String) object;
		}
		return userId;
	}
}
