package com.imfbp.rz.service.rzprjreview;

import java.lang.reflect.InvocationTargetException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.imfbp.rz.dao.rzpricecal.RzPricecalDao;
import com.imfbp.rz.dao.rzprjcontr.RzPrjcontrDao;
import com.imfbp.rz.domain.rzpricecal.RzPricecal;
import com.imfbp.rz.domain.rzpricecal.query.RzPricecalQuery;
import com.imfbp.rz.domain.rzprjcontr.query.RzPrjcontrQuery;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.DoubleUtils;
import com.imfbp.rz.util.ToolUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.imfbp.rz.dao.rzprjapply.RzPrjapplyDao;
import com.imfbp.rz.domain.pub.SuperHeadBean;
import com.imfbp.rz.domain.rzprjapply.RzPrjapply;
import com.imfbp.rz.domain.rzprjapply.query.RzPrjapplyQuery;
import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjreview.RzPrjreview;
import com.imfbp.rz.domain.rzprjreviewlessee.RzPrjreviewLessee;
import com.imfbp.rz.domain.rzprjreviewlessee.query.RzPrjreviewLesseeQuery;
import com.imfbp.rz.handler.approvehandler.ApproveHandler;
import com.imfbp.rz.service.rzprjcontr.RzPrjcontrService;
import com.imfbp.rz.service.rzprjreviewlessee.RzPrjreviewLesseeService;

	
	/**
	 * 项目审批
	 * @author wanghaox
	 * @date 2016/12/23 15:41
	 */
	public class RzPrjreviewApproveHandler extends ApproveHandler{

	    @Autowired
	    private RzPrjcontrService rzPrjcontrService;
	    @Autowired
	    private RzPrjreviewLesseeService rzPrjreviewLesseeService;
	    @Autowired
	    private RzPrjapplyDao rzPrjapplyDao;
		@Autowired
		private RzPrjcontrDao rzPrjcontrDao;
		@Autowired
		private RzPricecalDao rzPricecalDao;
        
        
	    @Override
	    public void afterApprovePass(SuperHeadBean headBean) throws Exception {
	    	RzPrjreview rzPrjreview = (RzPrjreview)headBean;
	        RzPrjcontr rzPrjcontr=new RzPrjcontr();
	        RzPrjapplyQuery rzPrjapplyQuery=new RzPrjapplyQuery();
	        rzPrjapplyQuery.setPkPrjapply(rzPrjreview.getPkPrjapply());
	        RzPrjapply rzPrjapply= rzPrjapplyDao.getRzPrjapplyById(rzPrjapplyQuery);
			//查询引用该项目的所有已审批通过的合同，获取总合同金额，
			RzPrjcontrQuery rzPrjcontrQuery = new RzPrjcontrQuery();
			rzPrjcontrQuery.setPkPrjapply(rzPrjreview.getPkPrjapply());
			rzPrjcontrQuery.setApprovestatus(3);
			List<RzPrjcontr> rzPrjcontrList = rzPrjcontrDao.getRzPrjcontrAll(rzPrjcontrQuery);
			Double sumContrAmt = new Double(0);
			if(ToolUtils.isNotEmptyCollection(rzPrjcontrList)){
				for(RzPrjcontr item : rzPrjcontrList){
					sumContrAmt += DoubleUtils.getDoubleNullAsZero(item.getContrAmt());
				}
			}
			RzPricecalQuery rzPricecalQuery = new RzPricecalQuery();
			rzPricecalQuery.setPkPricecal(rzPrjreview.getPkPricecal());
			RzPricecal rzPricecal = rzPricecalDao.getRzPricecalById(rzPricecalQuery);
			try {
				BeanUtils.copyProperties(rzPrjcontr, rzPrjreview);
				rzPrjcontr.setApproveid("");
				rzPrjcontr.setFlowinstanceid(null);
				rzPrjcontr.setApproveid(null);
				rzPrjcontr.setApprovestatus(0);
				rzPrjcontr.setApprovedate("");
				rzPrjcontr.setApprovenote("");
				rzPrjcontr.setIsMainLessee("Y");
				rzPrjcontr.setBilldate(null);
				rzPrjcontr.setTs(DateUtil.getCurDateStr());
				rzPrjcontr.setContrStatus(0);
				rzPrjcontr.setPrjCode(rzPrjapply.getPrjCode());
				rzPrjcontr.setPrjName(rzPrjapply.getPrjName());
				RzPrjreviewLesseeQuery rzPrjreviewLesseeQuery=new RzPrjreviewLesseeQuery();
				rzPrjreviewLesseeQuery.setPkPrjreview(rzPrjreview.getPkPrjreview());
				if(rzPrjreviewLesseeService.getRzPrjreviewLesseeAll(rzPrjreviewLesseeQuery).size()>0){
					RzPrjreviewLessee rzPrjreviewLessee =rzPrjreviewLesseeService.getRzPrjreviewLesseeAll(rzPrjreviewLesseeQuery).get(0);
					rzPrjcontr.setPkCustomerLessee(rzPrjreviewLessee.getPkCustomer());//承租人编码
					rzPrjcontr.setIsMainLessee("Y");//是主承租人
				}
				Format format = new SimpleDateFormat("yyyy");
				rzPrjcontr.setContrYear(format.format(new Date()));//设置合同年份
				rzPrjcontr.setPlanSignedDate(rzPrjreview.getLaunchdate());
				rzPrjcontr.setStartLeaseDate(rzPrjreview.getLaunchdate());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date dat = null;
				dat = df.parse(rzPrjreview.getLaunchdate());
				Calendar c = Calendar.getInstance();
				c.setTime(dat);
				c.add(Calendar.MONTH,rzPrjreview.getLeaseprd());
				String endLeaseDate = df.format(c.getTime());
				rzPrjcontr.setEndLeaseDate(endLeaseDate);
				Double balance = DoubleUtils.getDoubleNullAsZero(rzPrjcontr.getItemamt()) - DoubleUtils.getDoubleNullAsZero(sumContrAmt);
				if(balance >= 0){
					rzPrjcontr.setContrAmt(balance);
					rzPrjcontr.setItembal(balance);
				}else{
					rzPrjcontr.setContrAmt(new Double(0));
					rzPrjcontr.setItembal(new Double(0));
				}
				//报价单号
				rzPrjcontr.setPriceno(rzPricecal.getPriceno());
				rzPrjcontrService.insertRzPrjcontr(rzPrjcontr, getTenantId());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
	    }

	    @Override
	    public void withDrawPassBill(SuperHeadBean headBean) throws Exception {

	    }
	}   
