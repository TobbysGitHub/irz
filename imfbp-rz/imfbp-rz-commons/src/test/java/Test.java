import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	private static double MINDIF = 0.0001;

	private static int LOOPNUM = 2000;

	/*public static void main(String[] args) {
		List<Double> list = new ArrayList<Double>();
		list.add(-4687500.00);
		list.add(458399.96);
		list.add(458399.96);
		list.add(458399.96);
		list.add(458399.96);
		list.add(458399.96);
		list.add(458399.96);
		list.add(458399.96);
		list.add(458399.96);
		list.add(458399.96);
		list.add(458399.96);
		list.add(458399.96);
		list.add(158399.96);
		list.add(null);
		System.out.println(Test.irr(list) * 100 * 4);
	}*/

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
				System.out.println(irr);
				npv1 = npv2;
				npv2 = npv(cashList, r2);
				count++;
			}

			if (Math.abs(npv2) < MINDIF & count <= LOOPNUM) {
				return BigDecimal.valueOf(irr).setScale(8, BigDecimal.ROUND_HALF_UP).doubleValue();
			} else {
				return Double.NaN;
			}
		} else {
			return BigDecimal.ZERO.doubleValue();
		}
	}
	
	
	/**
	 * 计算月供
	 * 
	 * @param rate
	 *            年利率 年利率除以12就是月利率
	 * @param term
	 *            贷款期数，单位月
	 * @param financeAmount
	 *            贷款金额
	 * @return
	 */
	private static double PMT(double rate, double term, double financeAmount) {
		double v = (1 + (rate / 4));
		double t = (-(term / 4) * 4);
		double result = (financeAmount * (rate / 12)) / (1 - Math.pow(v, t));
		return result;
	}
	
	public static double PMT1(double yearlyInterestRate, int totalNumberOfMonths, double loanAmount) {
		double rate = (double) yearlyInterestRate / 100 / 4;
		double denominator = Math.pow((1 + rate), totalNumberOfMonths) - 1;
		return (rate + (rate / denominator)) * loanAmount;
	}
	
	public static double PMT2(double ir, double np, double pv, double fv ) {
		 /*
		 ir - interest rate per month
		 np - number of periods (months)
		 pv - present value
		 fv - future value (residual value)
		 type - 0 or 1 need to implement that
		 */
		/*double  pmt = ( ir * ( pv * Math.pow ( (ir+1), np ) + fv ) ) / ( ( ir + 1 ) * ( Math.pow ( (ir+1), np) -1 ) );
		 return pmt;*/
		/*先付：租金=P*I*(1+I)n-1/((1+I)n-1)；

				后付：租金=P*I*(1+I)n/((1+I)n-1)；

				细心一看可以发现，后付=先付*（1+I），没错，就是这样的。

				其中P为本金，I为期利率，n为租赁其次*/
		double rate = (double) 6 / 100 / 4;
		
	return 5000000*rate*Math.pow(1+rate,11)/(Math.pow(1+rate,12)-1) ;	
	
	}
	
	/*Rate  必需。贷款利率。
	Nper  必需。该项贷款的付款总数。
	Pv  必需。现值，或一系列未来付款的当前值的累积和，也称为本金。
	Fv  可选。未来值，或在最后一次付款后希望得到的现金余额，如果省略 fv，则假设其值为 0（零），也就是一笔贷款的未来值为 0。
	Type  可选。数字 0（零）或 1，用以指示各期的付款时间是在期初还是期末*/
	/****
	 * 
	 * @param yearRate
	 * @param nPer
	 * @param amt
	 * @param fv
	 * @param type 0 期末支付后付 1 先付
	 * @return
	 */
	public static double pmt3(double yearRate,int nPer, double amt,double fv,int type){
		double rate = yearRate / 4;
		double payAmt = 0;
		double p = Math.pow ( (rate+1), nPer );
		
		payAmt = (rate * (amt * p - fv)) / ((rate * type + 1) * (p - 1));
		/*if (type == 0) {
			payAmt = rate * (amt * Math.pow(1 + rate, nPer) - 100000) / (Math.pow(1 + rate, 12) - 1);
		} else {
			payAmt = rate * (amt * Math.pow(1 + rate, nPer - 1)-100000 ) / (Math.pow(1 + rate, 12) - 1);
		}*/
		return payAmt;
	}
	
	public static double pmt4(double yearRate, double nPer, double amt, double future_value, double type){
	    /*if(yearRate != 0.0){*/
	        // Interest rate exists
			double rate = yearRate / 4;
			double q = Math.pow(1 + rate, nPer);

			
			//return  ((amt - future_value) * rate / (1 - Math.pow((1 + rate), -nPer)));
			 return ( rate * ( amt * Math.pow ( (rate+1), nPer ) - future_value ) ) / ( ( rate * type + 1 ) * ( Math.pow ( (rate+1), nPer) -1 ) );
//			return rate * (amt * Math.pow((rate + 1), nPer) + future_value) / (rate * type + 1)* (Math.pow((rate + 1), nPer) - 1);
//			return (rate * (future_value + (q * amt))) / ((q - 1) * (1 + nPer * (type)));
	    	 
//	        return rate * (future_value + (q * amt)) / (( q-1) );

	  /*  } else if(nPer != 0.0){
	        // No interest rate, but number of payments exists
	        return -(future_value + amt) / nPer;
	    }*/

	/*    return 0;*/
	}

	public static void main(String[] args) {
		double amt = 5000000;
		double fv = 10;
		System.out.println(Test.pmt3(0.06, 12, amt, 0, 0) + " " + Test.pmt3(0.06, 12, amt, fv, 0));
		System.out.println(Test.pmt3(0.06, 12, amt, 0, 1) + " " + Test.pmt3(0.06, 12, amt, fv, 1));
	/*	System.out.println(Test.pmt4(0.06, 12, amt, 0, 1));*/
		/*System.out.println(Test.pmt3(0.06, 12, amt, 0, 0));
		System.out.println(Test.pmt3(0.06, 12, amt, 0, 1));*/
		//458399.96453114727
		//451625.5808188643
	}
	
}