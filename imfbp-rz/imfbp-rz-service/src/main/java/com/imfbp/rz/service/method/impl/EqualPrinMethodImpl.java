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
 * 等额本金还款法<br>
 * 融资租金/还款期限
 * 
 * @author Administrator
 *
 */
public class EqualPrinMethodImpl extends AbstractRentMethodCal {

	@Override
	public List<RzPricecalLease> calLease(RzPricecal rzPricecal) throws BusinessException {
		checkCaFactor(rzPricecal);

		List<RzPricecalLease> rzPricecalLeaseList = new ArrayList<RzPricecalLease>();

		// 融资金额
		Double financeamt = rzPricecal.getFinanceamt();
		// 租赁利率(%)
		Double pricerate = rzPricecal.getPricerate();
		// 租赁期限年
		Integer leaseprd = rzPricecal.getLeaseprd();
		// 还款周期
		Integer reptcycle = rzPricecal.getReptcycle();

		// 租金支付方式 0 期末支付、1 期初支付
		Integer paymentway = rzPricecal.getPaymentway();

		String launchdate = rzPricecal.getLaunchdate();
		// 计划收租日期
		String planleasedate = rzPricecal.getPlanleasedate();
		planleasedate = launchdate.substring(0, 7) + "-" + planleasedate;

		// 首付款金额
		Double firstpmtamt = ToolUtils.getDouNullAsZero(rzPricecal.getFirstpmtamt());

		// 每年还款次数
		int everyYearNum = 12 / reptcycle;

		// 租赁期限月
		Integer leaseprdMonth = leaseprd / reptcycle;

		// 每期利率
		BigDecimal prdRate = BigDecimal.valueOf(pricerate / everyYearNum / 100).setScale(6, BigDecimal.ROUND_HALF_UP);

		// 每期支付本金
		BigDecimal payPrin = (BigDecimal.valueOf(financeamt).divide(BigDecimal.valueOf(leaseprdMonth), 4,BigDecimal.ROUND_HALF_UP));

		// 剩余本金
		BigDecimal residualAmt = BigDecimal.valueOf(financeamt);
		
		String nextDate = "";
		String lastDate = planleasedate;
		// 应还款日期
		String rptDate = planleasedate;

		// 首次应还款租金
		Double firstRentAmt = Double.valueOf("0");
		if (paymentway == 0) {
			firstRentAmt = firstpmtamt;
		} else {
			// 首次应还款租金 =首付款金额+首期租金=本金+首期利息
			firstRentAmt = firstpmtamt + (payPrin.add(residualAmt.multiply(prdRate)).doubleValue());
			// 剩余本金 = 融资金额-首付款金额
			// residualAmt =
			// residualAmt.subtract(BigDecimal.valueOf(firstpmtamt));
		}

		// 起租日
		RzPricecalLease rzPricecalLeaseTmp = new RzPricecalLease();
		rzPricecalLeaseTmp.setNum("起租日");
		rzPricecalLeaseTmp.setRptDate(rptDate);
		rzPricecalLeaseTmp.setPaydur(rptDate);
		rzPricecalLeaseTmp.setRptRent(firstRentAmt);
		rzPricecalLeaseTmp.setResidualAmt(residualAmt.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		rzPricecalLeaseTmp.setRptTotal(firstRentAmt);
		
		// 手续费支付方式： 0 一次性支付、1 随租金支付
		Integer feepayway = rzPricecal.getFeepayway();
		// 手续费金额
		Double feeamt = ToolUtils.getDouNullAsZero(rzPricecal.getFeeamt());
		double feeamtEach = feeamt / leaseprdMonth;

		double cfIn = cfIn(rzPricecal, rzPricecalLeaseTmp.getRptRent(), 0, feeamtEach);
		double cfOut = cfOut(rzPricecal, leaseprdMonth, 0);
		double netCfIn = cfIn - cfOut;
		
		rzPricecalLeaseTmp.setCfIn(cfIn);
		rzPricecalLeaseTmp.setCfOut(cfOut);
		rzPricecalLeaseTmp.setNetCfIn(netCfIn);
		rzPricecalLeaseTmp.setSeqNo(10);
		
		rzPricecalLeaseList.add(rzPricecalLeaseTmp);

//		System.out.println("首付款租金=" + firstRentAmt + " 现金流入：" + cfIn + "  现金流流出：" + cfOut + "净流入：" + netCfIn);

		int count = 0;
		for (int i = 0; i < leaseprdMonth; i++) {
			RzPricecalLease rzPricecalLease = new RzPricecalLease();
			count = i + 1;
			// 支付利息
			BigDecimal payInt = residualAmt.multiply(prdRate);
			// 租金 = 支付利息+支付本金
			BigDecimal rptRen = payInt.add(payPrin);
			rzPricecalLease.setRptRent(rptRen.doubleValue());

			if (feepayway != null && feepayway == 1) {
				rzPricecalLease.setPayFee(feeamtEach);
			}
			rzPricecalLease.setRptTotal(rzPricecalLease.getRptRent() + ToolUtils.getDouNullAsZero(rzPricecalLease.getPayFee()));

			// 剩余本金
			residualAmt = (residualAmt.subtract(payPrin));

			if (StringUtil.isEmpty(nextDate)) {
				nextDate = DateUtil.dateAddMonth(planleasedate, reptcycle);
			} else {
				lastDate = nextDate;
				nextDate = DateUtil.dateAddMonth(nextDate, reptcycle);
				rptDate = nextDate;
			}

			String paydur = lastDate + "~" + nextDate;

			rzPricecalLease.setNum("第" + count + "期");
			rzPricecalLease.setRptDate(rptDate);
			rzPricecalLease.setPaydur(paydur);
			rzPricecalLease.setDurdays(30 * reptcycle);
			rzPricecalLease.setLeaseRate(pricerate);
			rzPricecalLease.setRptAmt(payPrin.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			rzPricecalLease.setRptInt(payInt.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

			rzPricecalLease.setResidualAmt(residualAmt.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			rzPricecalLease.setSeqNo(10 + count);
			rzPricecalLeaseList.add(rzPricecalLease);
		/*	System.out.print("第" + (i + 1) + "期：");
			System.out.print("计划支付日期:" + rzPricecalLease.getRptDate());
			System.out.print("收付期间:" + rzPricecalLease.getPaydur());
			System.out.print("应还款利息：" + rzPricecalLease.getRptInt() + " 应还本金 :" + rzPricecalLease.getRptAmt() + " 剩余本金："
					+ rzPricecalLease.getResidualAmt());*/

			cfIn = cfIn(rzPricecal, rzPricecalLease.getRptRent(), count, feeamtEach);
			cfOut = cfOut(rzPricecal, leaseprdMonth, count);
			netCfIn = cfIn - cfOut;

			rzPricecalLease.setCfIn(cfIn);
			rzPricecalLease.setCfOut(cfOut);
			rzPricecalLease.setNetCfIn(netCfIn);

			System.out.println("现金流入：" + cfIn + "  现金流流出：" + cfOut + "净流入：" + netCfIn);
		}

		return rzPricecalLeaseList;

	}

	public static void main(String args[]) {
		RzPricecal rzPricecal = new RzPricecal();
		rzPricecal.setItemamt(5000000.00);
		rzPricecal.setFinanceamt(5000000.00);
		rzPricecal.setPricerate(6.0);
		rzPricecal.setLeaseprd(36);
		rzPricecal.setReptcycle(3);
		rzPricecal.setPaymentway(1);

		rzPricecal.setFeepayway(0);

		rzPricecal.setFeeamt(rzPricecal.getFinanceamt() * 2.25 / 100);
		rzPricecal.setDepositamt(rzPricecal.getFinanceamt() * 6 / 100);
		rzPricecal.setScamt(rzPricecal.getFinanceamt() * 2 / 100);

		// rzPricecal.setFinanceamt(rzPricecal.getItemamt());
		// rzPricecal.setFirstpmtamt(500000.00);
		rzPricecal.setLaunchdate("2016-11-17");
		EqualPrinMethodImpl a = new EqualPrinMethodImpl();
		try {
			a.calLease(rzPricecal);
			// a.calCf(rzPricecal);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * RzPricecal rzPricecal = new RzPricecal();
		 * rzPricecal.setItemamt(3431547.79);
		 * rzPricecal.setFinanceamt(3431547.79); rzPricecal.setPricerate(6.0);
		 * rzPricecal.setLeaseprd(5); rzPricecal.setReptcycle(3);
		 * rzPricecal.setPaymentway(0);
		 * 
		 * rzPricecal.setFeepayway(0);
		 * 
		 * rzPricecal.setFeeamt(rzPricecal.getItemamt()*2.25/100);
		 * rzPricecal.setDepositamt(rzPricecal.getItemamt()*6/100);
		 * rzPricecal.setScamt(rzPricecal.getItemamt()*2/100);
		 * 
		 * rzPricecal.setFinanceamt(rzPricecal.getItemamt());
		 * rzPricecal.setFirstpmtamt(500000.00);
		 * rzPricecal.setLaunchdate("2017-11-17"); EqualRentMethodImpl a = new
		 * EqualRentMethodImpl(); try { a.calLeaseChange(rzPricecal);
		 * //a.calCf(rzPricecal); } catch (BusinessException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		// System.out.println(new BigDecimal(1.2435).setScale(2,
		// BigDecimal.ROUND_UP));

	}


	@Override
	public List<RzPricecalLease> calLeaseChange(RzPricecal rzPricecal) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
