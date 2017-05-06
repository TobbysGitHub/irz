package com.imfbp.rz.service.method;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.imfbp.rz.domain.rzpricecal.RzPricecal;
import com.imfbp.rz.domain.rzpricecalcf.RzPricecalCf;
import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.pub.EumConst.PlanPmtCategoryEnum;
import com.imfbp.rz.pub.EumConst.PlanPmtTypeEnum;
import com.imfbp.rz.service.commons.CalUtil;
import com.imfbp.rz.util.ToolUtils;
import com.imfbp.rz.domain.exception.BusinessException;

public abstract class AbstractRentMethodCal implements IRentMethodCal {
	
	@Override
	public List<RzPricecalCf> calCf(RzPricecal rzPricecal) throws BusinessException {
		RzPricecalCf rzPricecalCfFAmt = calFinancingAmount(rzPricecal);
		RzPricecalCf rzPricecalCfSf = calServiceFee(rzPricecal);
		RzPricecalCf rzPricecalCFCd = calCollectDeposit(rzPricecal);
		
		RzPricecalCf rzPricecalCFFirstPmt = calFirstPmt(rzPricecal);
		List<RzPricecalCf> list = new ArrayList<RzPricecalCf>();
		if (rzPricecalCfFAmt != null) {
			list.add(rzPricecalCfFAmt);
		}
		if (rzPricecalCfSf != null) {
			list.add(rzPricecalCfSf);
		}
		if (rzPricecalCFCd != null) {
			list.add(rzPricecalCFCd);
		}
		
		// 手续费支付方式： 0 一次性支付、1 随租金支付
		Integer feepayway = rzPricecal.getFeepayway();
		if (feepayway == null) {
			feepayway = 1;
		}
		if (feepayway != null && feepayway == 0) {
			RzPricecalCf rzPricecalCFFc = calFeeCharge(rzPricecal);
			if (rzPricecalCFFc != null) {
				list.add(rzPricecalCFFc);
			}
		}
		if (rzPricecalCFFirstPmt != null) {
			list.add(rzPricecalCFFirstPmt);
		}
		
		List<RzPricecalLease> rzPricecalLeaseList = calLease(rzPricecal);
		String contrEndDate = rzPricecal.getPlanleasedate();
		int count = 0;
		for (int i = 1; i < rzPricecalLeaseList.size(); i++) {
			RzPricecalLease rzPricecalLease = rzPricecalLeaseList.get(i);

			RzPricecalCf rzPricecalCf = new RzPricecalCf();
			rzPricecalCf.setPrd(String.valueOf(++count));
			rzPricecalCf.setPlanpmtdate(rzPricecalLease.getRptDate());
			rzPricecalCf.setSeqNo(rzPricecalLease.getSeqNo());
			rzPricecalCf.setPmtdur(rzPricecalLease.getPaydur());
			rzPricecalCf.setPlanpmttype(PlanPmtTypeEnum.RECEIVABLES.getIndex());
			rzPricecalCf.setPlanpmtcategory(PlanPmtCategoryEnum.RENT_COLLECTION.getIndex());
			rzPricecalCf.setDurdays(rzPricecalLease.getDurdays());
			rzPricecalCf.setLeaseRate(rzPricecalLease.getLeaseRate());
			rzPricecalCf.setReceivableAmt(rzPricecalLease.getRptAmt());
			rzPricecalCf.setReceivableInt(rzPricecalLease.getRptInt());
			rzPricecalCf.setReceivableFee(rzPricecalLease.getPayFee());

			// 应收租金
			double receivableRent = ToolUtils.getDouNullAsZero(rzPricecalLease.getRptRent());
			// 应收保证金
			Double depositamt = ToolUtils.getDouNullAsZero(rzPricecal.getDepositamt());
			// 应收手续费
			Double feeamt = ToolUtils.getDouNullAsZero(rzPricecal.getFeeamt());
			rzPricecalCf.setReceivableRent(rzPricecalLease.getRptRent());

			// 应收款项合计 = 应收租金 +应收保证金+应收手续费
			Double receivableTotal = receivableRent + depositamt + feeamt;
			rzPricecalCf.setReceivableTotal(receivableTotal);
			list.add(rzPricecalCf);
		}
		if (ToolUtils.isNotEmptyCollection(rzPricecalLeaseList)) {
			RzPricecalLease rzPricecalLeaseLast = rzPricecalLeaseList.get(rzPricecalLeaseList.size() - 1);
			contrEndDate = rzPricecalLeaseLast.getRptDate();
			count = rzPricecalLeaseLast.getSeqNo() + 1;
		} else {
			count = 5;
		}
		
		RzPricecalCf refundDeposit = calRefundDeposit(rzPricecal, contrEndDate, count);
		if (refundDeposit != null) {
			list.add(calRefundDeposit(rzPricecal, contrEndDate, count));
		}
		return list;
	}
	
	/***
	 * 现金流入
	 * 
	 * @param rzPricecal
	 * @param pmt
	 * @param prdNum 期数
	 * @param feeamtEach 每期手续费
	 * 
	 * @return
	 */
	protected double cfIn(RzPricecal rzPricecal,Double pmt ,int prdNum,double feeamtEach) {
		Double cfInAmt = Double.valueOf(0);
		
		// 手续费支付方式： 0 一次性支付、1 随租金支付
		Integer feepayway = rzPricecal.getFeepayway();
		// 手续费金额
		Double feeamt =  ToolUtils.getDouNullAsZero(rzPricecal.getFeeamt());
		if (feepayway == null) {
			feepayway = 1;
		}
		// 如果不是期初，则取每期租金额
		if (prdNum != 0) {
			cfInAmt = pmt;
			// 手续费支付方式：1 随租金支付
			if (feepayway == 1) {
				cfInAmt = cfInAmt + feeamtEach;
			}
			return cfInAmt;
		} else {
			//期末支付(后付)： 保证金金额+ 如果：手续费支付方式 等于( 一次性支付) = 手续费金额 否则 =0 + 管理费+ 首付款金额+供应商折扣额
			//期初支付(先付)： 保证金金额+ 如果：手续费支付方式 等于( 一次性支付) = 手续费金额 否则 =0 + 管理费+ 首付款金额+供应商折扣额+每期租金
			Integer paymentway = rzPricecal.getPaymentway();
			
			//首付款金额
			Double firstpmtamt = ToolUtils.getDouNullAsZero(rzPricecal.getFirstpmtamt());
			
			// 客户保证金金额
			Double depositamt = rzPricecal.getDepositamt();
			if (depositamt == null) {
				depositamt = Double.valueOf(0);
			}

			cfInAmt = ToolUtils.getDouNullAsZero(depositamt);
			
			if (feepayway == 0) {
				cfInAmt = cfInAmt + feeamt;
			} 
			cfInAmt = cfInAmt + firstpmtamt;
			if (paymentway == 1) {
				cfInAmt = cfInAmt + pmt;
			}
		}
		return cfInAmt;
	}

	
	
	/****
	 * 现金流流出
	 * 
	 * @param rzPricecal
	 * @param leaseprdMonth 租赁期限月
	 * @param count 计算期数
	 * @return
	 */
	protected double cfOut(RzPricecal rzPricecal,int leaseprdMonth,int count) {
		Double cfOutAmt = Double.valueOf(0);
		if (count == 0) {
			// 租赁融资金额
			Double financeamt = ToolUtils.getDouNullAsZero(rzPricecal.getFinanceamt());
			// 服务费金额
			Double scamt = ToolUtils.getDouNullAsZero(rzPricecal.getScamt());
			// 期初：现金流流出 = 租赁融资金额 + 服务费金额
			cfOutAmt = financeamt + scamt;
		} else if (count == leaseprdMonth) {
			//最后一期：现金流流出 = 保证金金额 + 保证金金额*(租赁期限(年)*保证金存款利率
			// 保证金金额
			Double depositamt = ToolUtils.getDouNullAsZero(rzPricecal.getDepositamt());
			// TODO 未考虑保证金存款利率
			cfOutAmt = depositamt;
		}
		return cfOutAmt;
	}
	
	
	/****
	 * 
	 * 融资金额投放 =租赁融资额(应付融资金额) + 应付服务费
	 * 
	 * @param rzPricecal
	 * @return RzPricecalCf
	 */
	protected RzPricecalCf calFinancingAmount(RzPricecal rzPricecal) {
		// 融资金额投放 =租赁融资额(应付融资金额) + 应付服务费
		// 租赁融资额
		double financeamt = ToolUtils.getDouNullAsZero(rzPricecal.getFinanceamt());
		// 应付服务费
//		double scamt = ToolUtils.getDouNullAsZero(rzPricecal.getScamt());
		double payableAmt = financeamt ;
		RzPricecalCf rzPricecalCf = new RzPricecalCf();
		rzPricecalCf.setPrd("0");
		rzPricecalCf.setSeqNo(0);
		rzPricecalCf.setPlanpmtdate(rzPricecal.getLaunchdate());
		rzPricecalCf.setPmtdur(rzPricecal.getLaunchdate());
		rzPricecalCf.setPlanpmttype(PlanPmtTypeEnum.PAYMENT.getIndex());
		rzPricecalCf.setPlanpmtcategory(PlanPmtCategoryEnum.FINANCING_AMOUNT.getIndex());
		rzPricecalCf.setDurdays(1);
		rzPricecalCf.setPayableAmt(payableAmt);
		if (payableAmt == 0) {
			return null;
		}
		return rzPricecalCf;
	}

	/****
	 * 服务费支出
	 * 
	 * @param rzPricecal
	 */
	protected RzPricecalCf calServiceFee(RzPricecal rzPricecal) {
		RzPricecalCf rzPricecalCf = new RzPricecalCf();
		Double scamt = ToolUtils.getDouNullAsZero(rzPricecal.getScamt());
		rzPricecalCf.setPrd("0");
		rzPricecalCf.setSeqNo(1);
		rzPricecalCf.setPlanpmtdate(rzPricecal.getLaunchdate());
		rzPricecalCf.setPmtdur(rzPricecal.getLaunchdate());
		rzPricecalCf.setPlanpmttype(PlanPmtTypeEnum.PAYMENT.getIndex());
		rzPricecalCf.setPlanpmtcategory(PlanPmtCategoryEnum.SERVICE_FEE.getIndex());
		rzPricecalCf.setDurdays(1);
		Double sctaxrate = rzPricecal.getScpct();
		rzPricecalCf.setLeaseRate(sctaxrate);
		rzPricecalCf.setPayableAmt(scamt);
		if (scamt == 0) {
			return null;
		}
		return rzPricecalCf;
	}
	
	/****
	 * 
	 * 收取保证金
	 * 
	 * @param rzPricecal
	 * @return
	 */
	protected RzPricecalCf calCollectDeposit(RzPricecal rzPricecal) {
		RzPricecalCf rzPricecalCf = new RzPricecalCf();
		Double depositamt = ToolUtils.getDouNullAsZero(rzPricecal.getDepositamt());
		rzPricecalCf.setPrd("0");
		rzPricecalCf.setSeqNo(2);
		rzPricecalCf.setPlanpmtdate(rzPricecal.getLaunchdate());
		rzPricecalCf.setPmtdur(rzPricecal.getLaunchdate());
		rzPricecalCf.setPlanpmttype(PlanPmtTypeEnum.RECEIVABLES.getIndex());
		rzPricecalCf.setPlanpmtcategory(PlanPmtCategoryEnum.COLLECT_DEPOSIT.getIndex());
		rzPricecalCf.setDurdays(1);
		Double depositpct = ToolUtils.getDouNullAsZero(rzPricecal.getDepositpct());
		rzPricecalCf.setLeaseRate(depositpct);
		rzPricecalCf.setReceivableTotal(depositamt);
		if (depositamt == 0) {
			return null;
		}
		return rzPricecalCf;
	}
	
	
	/****
	 * 
	 * 手续费收取
	 * 
	 * @param rzPricecal
	 * @return
	 */
	protected RzPricecalCf calFeeCharge(RzPricecal rzPricecal) {
		RzPricecalCf rzPricecalCf = new RzPricecalCf();
		Double feeamt = ToolUtils.getDouNullAsZero(rzPricecal.getFeeamt());
		rzPricecalCf.setPrd("0");
		rzPricecalCf.setSeqNo(3);
		rzPricecalCf.setPlanpmtdate(rzPricecal.getLaunchdate());
		rzPricecalCf.setPmtdur(rzPricecal.getLaunchdate());
		rzPricecalCf.setPlanpmttype(PlanPmtTypeEnum.RECEIVABLES.getIndex());
		rzPricecalCf.setPlanpmtcategory(PlanPmtCategoryEnum.FEE_CHARGE.getIndex());
		rzPricecalCf.setDurdays(1);
		Double feepct = ToolUtils.getDouNullAsZero((rzPricecal.getFeepct()));
		rzPricecalCf.setLeaseRate(feepct);
		rzPricecalCf.setReceivableFee(feeamt);
		rzPricecalCf.setReceivableTotal(feeamt);
		if (feeamt == 0) {
			return null;
		}
		return rzPricecalCf;
	}
	
	/****
	 * 
	 * 首付款收取
	 * 
	 * @param rzPricecal
	 * @return
	 */
	protected RzPricecalCf calFirstPmt(RzPricecal rzPricecal) {
		RzPricecalCf rzPricecalCf = new RzPricecalCf();
		Double firstpmtamt = ToolUtils.getDouNullAsZero(rzPricecal.getFirstpmtamt());
		rzPricecalCf.setPrd("0");
		rzPricecalCf.setSeqNo(4);
		rzPricecalCf.setPlanpmtdate(rzPricecal.getLaunchdate());
		rzPricecalCf.setPmtdur(rzPricecal.getLaunchdate());
		rzPricecalCf.setPlanpmttype(PlanPmtTypeEnum.RECEIVABLES.getIndex());
		rzPricecalCf.setPlanpmtcategory(PlanPmtCategoryEnum.FIRST_PMT.getIndex());
		rzPricecalCf.setDurdays(1);
		Double firstpmtpct = ToolUtils.getDouNullAsZero(rzPricecal.getFirstpmtpct());
		rzPricecalCf.setLeaseRate(firstpmtpct);
		rzPricecalCf.setReceivableFee(firstpmtamt);
		rzPricecalCf.setReceivableTotal(firstpmtamt);
		if (firstpmtamt == 0) {
			return null;
		}
		return rzPricecalCf;
	}
	
	
	/****
	 * 
	 * 合同到期，退还保证金
	 * 
	 * @param rzPricecal
	 * @return
	 */
	protected RzPricecalCf calRefundDeposit(RzPricecal rzPricecal,String contrEndDate,int seqNo) {
		RzPricecalCf rzPricecalCf = new RzPricecalCf();
		Double depositamt = ToolUtils.getDouNullAsZero(rzPricecal.getDepositamt());
		rzPricecalCf.setPrd("合同到期");
		rzPricecalCf.setSeqNo(seqNo);
		rzPricecalCf.setPlanpmtdate(contrEndDate);
		rzPricecalCf.setPmtdur(contrEndDate);
		rzPricecalCf.setPlanpmttype(PlanPmtTypeEnum.PAYMENT.getIndex());
		rzPricecalCf.setPlanpmtcategory(PlanPmtCategoryEnum.REFUND_DEPOSIT.getIndex());
		rzPricecalCf.setDurdays(1);
		Double depositpct = ToolUtils.getDouNullAsZero(rzPricecal.getDepositpct());
		rzPricecalCf.setLeaseRate(depositpct);
		rzPricecalCf.setPayableAmt(depositamt);
		
		if (depositamt == 0) {
			return null;
		}
		return rzPricecalCf;
	}
	
	
	
	/****
	 * 计算因素检查
	 * 
	 * @param rzPmtPlan
	 * @throws BusinessException
	 */
	protected void checkCaFactor(RzPricecal rzPricecal) throws BusinessException {
		// 项目金额
		Double financeamt = rzPricecal.getFinanceamt();
		// 租赁利率
		Double pricerate = rzPricecal.getPricerate();
		// 租赁期限
		Integer leaseprd = rzPricecal.getLeaseprd();
		// 还款周期
		Integer reptcycle = rzPricecal.getReptcycle();

		// 租金支付方式 0 期末支付、1 期初支付
		Integer paymentway = rzPricecal.getPaymentway();

		if (financeamt == null || financeamt <= 0) {
			throw new BusinessException("请录入正确的，融资金额！");
		}

		if (pricerate == null || pricerate <= 0) {
			throw new BusinessException("请录入正确的，租赁利率！");
		}

		if (leaseprd == null || leaseprd <= 0) {
			throw new BusinessException("请录入正确的，租赁期限！");
		}

		if (reptcycle == null || reptcycle <= 0) {
			throw new BusinessException("请录入正确的，还款周期！");
		}

		if (paymentway == null) {
			throw new BusinessException("请录入，租金支付方式！");
		}

	}
	

	/*****
	 * IRR预期收益率计算
	 * 
	 * @param netCfInList
	 *            净利息收益List
	 * @param reptcycle
	 *            每年还款次数
	 * @return
	 */
	public Double calIRR(List<Double> netCfInList, Integer reptcycle) throws BusinessException {
		if (reptcycle == null) {
			throw new BusinessException("每年还款次数,不能为空！");
		}
		// 每年还款次数
		int everyYearNum = 12 / reptcycle;
		Double irr = Math.pow(CalUtil.irr(netCfInList)/100 + 1, everyYearNum) - 1;
		
		Double irrHalfUp = BigDecimal.valueOf(irr).multiply(BigDecimal.valueOf(100)).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
		return irrHalfUp;
	}
	
	public static void main(String args[]){
		double s = Math.pow(0.017206+1,4) ;
		System.out.println(s-1);
	}

}
