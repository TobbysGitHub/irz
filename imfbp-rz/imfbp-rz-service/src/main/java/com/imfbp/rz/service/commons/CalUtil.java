package com.imfbp.rz.service.commons;

import java.math.BigDecimal;
import java.util.List;

/***
 * 计算工具
 * 
 * @author Administrator
 *
 */
public class CalUtil {

	private final static double MINDIF = 0.0001;

	private final static int LOOPNUM = 2000;

	private static double npv(List<Double> cashList, double r) {
		double npv = 0;
		int size = cashList.size();
		for (int i = 0; i < size; i++) {
			Double val = cashList.get(i);
			if (val == null) {
				val = BigDecimal.ZERO.doubleValue();
			}
			npv += val / Math.pow(1 + r, i);
		}
		return npv;
	}

	/***
	 * IRR内部收益率计算
	 * 
	 * @param cashList
	 * @return double
	 */
	public static double irr(List<Double> cashList) {
		if (cashList != null && !cashList.isEmpty()) {
			double irr = 0;
			double r1 = 0.01;
			double r2 = 0.09;
			double npv1 = npv(cashList, r1);
			double npv2 = npv(cashList, r2);
			int count = 0;
			while (Math.abs(npv2) > MINDIF & count < LOOPNUM) {
				irr = r2 - npv2 * (r2 - r1) / (npv2 - npv1);
				r1 = r2;
				r2 = irr;
				//System.out.println(irr);
				npv1 = npv2;
				npv2 = npv(cashList, r2);
				count++;
			}
			if (Math.abs(npv2) < MINDIF & count <= LOOPNUM) {
				return BigDecimal.valueOf(irr).multiply(BigDecimal.valueOf(100)).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
			} else {
				return Double.NaN;
			}
		} else {
			return BigDecimal.ZERO.doubleValue();
		}
	}

	/****
	 * 基于固定利率及等额分期付款方式，返回贷款的每期付款额
	 * 
	 * @param rate 每期付款利率
	 * @param nPer 付款总期数
	 * @param pv   也称本金
	 * @param fv   可选、未来现值
	 * @param type 0 或省略 期末、 1 期初 
	 * @return  每期付款额
	 */
	public static double pmt(double rate, int nPer, double amt, double fv, int type) {
		double payAmt = 0;
		double p = Math.pow((rate + 1), nPer);
		payAmt = (rate * (amt * p - fv)) / ((rate * type + 1) * (p - 1));
		return payAmt;
	}

	public static void main(String[] args) {
		double amt = 5000000;
//		double fv = 100000;
		double rate = 0.06 / 4;
		//=PMT(0.015,5,3431547.79,0,0)
		double pmt = CalUtil.pmt(0.015, 5, 3431547.79, 0, 0) ;
		System.out.println(pmt);
		/*int i=1;
		double pmt1 =0;
		while (i<=13) {
			pmt1 =  BigDecimal.valueOf(amt * rate).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
			amt = amt - (pmt-pmt1);
			System.out.print("第" + i + "期，应还利息：" + pmt1);
			
			System.out.print(" 应还本金" + (amt-pmt*i) );
			
			System.out.print(" 应还租金" + pmt + "剩余本金：" + amt);
			System.out.println("");
			i++;
		}*/
		/*System.out.println(CalUtil.pmt(rate, 12, amt, 0, 0) + " " + CalUtil.pmt(rate, 12, amt, fv, 0));
		System.out.println(CalUtil.pmt(rate, 12, amt, 0, 1) + " " + CalUtil.pmt(rate, 12, amt, fv, 1));*/
	}

}
