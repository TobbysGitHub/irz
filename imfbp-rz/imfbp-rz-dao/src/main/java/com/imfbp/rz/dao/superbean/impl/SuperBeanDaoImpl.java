package com.imfbp.rz.dao.superbean.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.imfbp.rz.dao.superbean.SuperBeanDao;
import com.imfbp.rz.domain.pub.BeanHelper;
import com.imfbp.rz.domain.pub.SuperHeadBean;
import com.imfbp.rz.util.ToolUtils;

public class SuperBeanDaoImpl  extends SqlSessionDaoSupport implements SuperBeanDao {

	@Override
	public boolean updateHeadBean(SuperHeadBean headbean) throws Exception {
		
		
		String sql = "update " + headbean.getTableName() + " set ";
		
			sql += headbean.approveStatusFieldName() + " = " + BeanHelper.getApprovestatus(headbean)+",";
			sql += headbean.instanceIdFieldName() + " = '" + (BeanHelper.getInstanceId(headbean) == null ? "": BeanHelper.getInstanceId(headbean)) + "',";
			sql += headbean.approverFieldName() + " = '" + (BeanHelper.getApproveid(headbean) == null ? "" :  BeanHelper.getApproveid(headbean))+"',";
			sql += headbean.approvedateFieldName() + " = '" + (BeanHelper.getApprovedate(headbean)  == null ? "" :  BeanHelper.getApprovedate(headbean))+ "',";
			sql += headbean.approveNoteFieldName() + " = '" + (BeanHelper.getApprovenote(headbean)  == null ? "" :  BeanHelper.getApprovenote(headbean))+ "',";
//			修改人
			sql += headbean.modifierFieldName() + " = '" + (BeanHelper.getModifier(headbean)  == null ? "" :  BeanHelper.getModifier(headbean))+ "',";
//			修改时间
			sql += headbean.modifitimeFieldName() + " = '" + (BeanHelper.getModifitime(headbean)  == null ? "" :  BeanHelper.getModifitime(headbean))+ "',";
		if(sql.endsWith(",")){
			sql = sql.substring(0,sql.length()-1);
			String pkFieldName = ToolUtils.humpToLine(headbean.getPkFieldName());
			sql += " where " + pkFieldName + " = '" + headbean.getPrimaryKey() + "'";
			
			return this.getSqlSession().update("superBean.updateAuditInfo", sql) > 0;
		}
		
		return false;
	}

}
