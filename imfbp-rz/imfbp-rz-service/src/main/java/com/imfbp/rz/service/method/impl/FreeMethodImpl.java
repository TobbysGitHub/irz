package com.imfbp.rz.service.method.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.imfbp.rz.domain.rzpricecal.RzPricecal;
import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.service.method.AbstractRentMethodCal;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.utils.StringUtil;
import com.imfbp.rz.domain.exception.BusinessException;

/****
 * 自由还款法,默认采用等额租金法计算期初还款计划
 * 
 * @author Administrator
 *
 */
public class FreeMethodImpl extends AbstractRentMethodCal {

	@Override
	public List<RzPricecalLease> calLease(RzPricecal rzPricecal) throws BusinessException {
		EqualRentMethodImpl equalRentMethodImpl = new EqualRentMethodImpl();
		return equalRentMethodImpl.calLease(rzPricecal);
	}

	
	/*******
	 * 
	 * 自由还款租金计划表修改
	 * 
	 * @param rzPricecal
	 * @param rzPricecalLeaseList
	 * @return
	 * @throws BusinessException
	 */
	public List<RzPricecalLease> updateLease(RzPricecal rzPricecal, List<RzPricecalLease> rzPricecalLeaseList)
			throws BusinessException {
		
		 List<RzPricecalLease> addRzPLList = new ArrayList<RzPricecalLease>();
		 
		Double financeamt = ToolUtils.getDouNullAsZero(rzPricecal.getFinanceamt());
		if (rzPricecal != null && ToolUtils.isNotEmptyCollection(rzPricecalLeaseList)) {
			int size = rzPricecalLeaseList.size();
			Double sumRptAmt = Double.valueOf("0");
			// 总共期数-首付款
			int prd = size - 1;
			
			// 租赁期限月
			Integer leaseprd = rzPricecal.getLeaseprd();
			
			// 租赁利率(%)
			Double pricerate = rzPricecal.getPricerate();
		
			// 还款周期
			BigDecimal reptcycle = BigDecimal.valueOf(leaseprd).divide(BigDecimal.valueOf(prd), 8, BigDecimal.ROUND_HALF_UP);

			// 每期利率
			BigDecimal prdRate = BigDecimal.valueOf(pricerate).divide(reptcycle, 8, BigDecimal.ROUND_HALF_UP)
					.divide(BigDecimal.valueOf(100), 8, BigDecimal.ROUND_HALF_UP);
			
			// 间隔天数
			int days = reptcycle.multiply(BigDecimal.valueOf(30)).intValue();
			
			// 计划投放日期
			String launchdate = rzPricecal.getLaunchdate();
			// 计划收租日期
			String planleasedate = rzPricecal.getPlanleasedate();
			planleasedate = launchdate.substring(0, 7) + "-" + planleasedate;
			
			
			// 租金支付方式 0 期末支付、1 期初支付
			Integer paymentway = rzPricecal.getPaymentway();
			
			//首付款金额
			Double firstpmtamt = ToolUtils.getDouNullAsZero(rzPricecal.getFirstpmtamt());
			
			// 剩余本金
			BigDecimal residualAmt = BigDecimal.valueOf(financeamt);
			String nextDate = "";
			String lastDate = planleasedate;
			// 应还款日期
			String rptDate = planleasedate;
			
			// 手续费金额
			Double feeamt =  ToolUtils.getDouNullAsZero(rzPricecal.getFeeamt());
			Double feeamtEach = feeamt / prd;
			
			double cfIn = cfIn(rzPricecal, 0.0, 0, feeamtEach);
			double cfOut = cfOut(rzPricecal, prd, 0);
			double netCfIn = cfIn - cfOut;
			
			// 首次应还款租金
			Double firstRentAmt = Double.valueOf("0");
			if (paymentway == 0) {
				firstRentAmt = firstpmtamt;
			} else {
				// 首次应还款租金 =首付款金额+每期租金(pmt)
				firstRentAmt = firstpmtamt ;
				/*// 剩余本金 = 融资金额-首付款金额
				residualAmt = residualAmt.subtract(BigDecimal.valueOf(firstpmtamt));*/
			}

			// 手续费支付方式： 0 一次性支付、1 随租金支付
			Integer feepayway = rzPricecal.getFeepayway();
			
			//起租日
			RzPricecalLease rzPricecalLeaseTmp = new RzPricecalLease();
			rzPricecalLeaseTmp.setNum("起租日");
			rzPricecalLeaseTmp.setRptDate(rptDate);
			rzPricecalLeaseTmp.setPaydur(rptDate);
			rzPricecalLeaseTmp.setRptRent(firstRentAmt);
			rzPricecalLeaseTmp.setResidualAmt(residualAmt.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			rzPricecalLeaseTmp.setRptTotal(firstRentAmt);
			rzPricecalLeaseTmp.setCfIn(cfIn);
			rzPricecalLeaseTmp.setCfOut(cfOut);
			rzPricecalLeaseTmp.setNetCfIn(netCfIn);
			rzPricecalLeaseTmp.setSeqNo(10);
			
			addRzPLList.add(rzPricecalLeaseTmp);
			
			int count = 0;
			for (int i = 0; i < prd; i++) {
				count = count + 1;
				RzPricecalLease rzPricecalLeaseTMP = rzPricecalLeaseList.get(i);
				sumRptAmt = sumRptAmt + ToolUtils.getDouNullAsZero(rzPricecalLeaseTMP.getRptAmt());

				// 支付利息
				BigDecimal payInt = residualAmt.multiply(prdRate);
				// 支付本金
				BigDecimal payPrin = BigDecimal.valueOf(ToolUtils.getDouNullAsZero(rzPricecalLeaseTMP.getRptAmt()));
				
				// 剩余本金
				residualAmt = (residualAmt.subtract(payPrin));
				
				if (StringUtil.isEmpty(nextDate)) {
					nextDate = DateUtil.dateAddDays(planleasedate, days);
				} else {
					lastDate = nextDate;
					nextDate = DateUtil.dateAddMonth(nextDate, days);
					rptDate = nextDate;
				}
				String paydur = lastDate + "~" + nextDate;

				if (feepayway != null && feepayway == 1) {
					rzPricecalLeaseTMP.setPayFee(feeamtEach);
				}

				rzPricecalLeaseTMP.setNum("第" + count + "期");
				rzPricecalLeaseTMP.setRptDate(rptDate);
				rzPricecalLeaseTMP.setPaydur(paydur);
				rzPricecalLeaseTMP.setDurdays(days);
				rzPricecalLeaseTMP.setLeaseRate(pricerate);
				rzPricecalLeaseTMP.setRptAmt(payPrin.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				rzPricecalLeaseTMP.setRptInt(payInt.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

				rzPricecalLeaseTMP.setResidualAmt(residualAmt.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

				cfIn = cfIn(rzPricecal, rzPricecalLeaseTMP.getRptRent(), count, feeamtEach);
				cfOut = cfOut(rzPricecal, prd, count);
				netCfIn = cfIn - cfOut;

				rzPricecalLeaseTMP.setCfIn(cfIn);
				rzPricecalLeaseTMP.setCfOut(cfOut);
				rzPricecalLeaseTMP.setNetCfIn(netCfIn);
				rzPricecalLeaseTMP.setSeqNo(10 + count);

				addRzPLList.add(rzPricecalLeaseTMP);

			}
			if (financeamt != sumRptAmt) {
				throw new BusinessException("租金计划表中的【应还款金额 不等于 融资金额】！");
			}
		} else {
			throw new BusinessException("请录入对应的租金计划表数据！");
		}

		return addRzPLList;
	}

	@Override
	public List<RzPricecalLease> calLeaseChange(RzPricecal rzPricecal) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
