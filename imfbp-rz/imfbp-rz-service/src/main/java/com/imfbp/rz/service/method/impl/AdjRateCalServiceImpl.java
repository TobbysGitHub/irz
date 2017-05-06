package com.imfbp.rz.service.method.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.imfbp.rz.domain.rzadjint.RzAdjInt;
import com.imfbp.rz.domain.rzadjintlease.RzAdjIntLease;
import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.pub.EumConst;
import com.imfbp.rz.service.method.AdjRateCalService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.utils.StringUtil;
import com.imfbp.rz.domain.exception.BusinessException;

@Component("adjRateCalService")
public class AdjRateCalServiceImpl implements AdjRateCalService {

	@Override
	public List<RzPricecalLease> calLease(RzAdjInt rzAdjInt, List<RzAdjIntLease> adjIntLeaseList)
			throws BusinessException {
		checkCaFactor(rzAdjInt);
		// 调息类型
		Integer intType = rzAdjInt.getIntType();
		List<RzPricecalLease> rzPlListNew = null;
		// 不需要调息
		if (intType == EumConst.AdjIntTypeEnum.NO.getIndex()) {
			rzPlListNew = calNoAdj(rzAdjInt, adjIntLeaseList);
		} else if (intType == EumConst.AdjIntTypeEnum.BASE.getIndex()) {
			// 随基准调息
			rzPlListNew = calAdjInt(rzAdjInt, adjIntLeaseList);
		} else if (intType == EumConst.AdjIntTypeEnum.NEXT_RENT.getIndex()) {
			rzPlListNew = calNextRentDateAdj(rzAdjInt, adjIntLeaseList);
		}
		return rzPlListNew;
	}

	/****
	 * 下一租金日调整
	 * 
	 * @param rzAdjInt
	 * @param adjIntLeaseList
	 * @return
	 */
	protected List<RzPricecalLease> calNextRentDateAdj(RzAdjInt rzAdjInt, List<RzAdjIntLease> adjIntLeaseList) {
		// 人行调息日期
		String pbcAdjIntDate = rzAdjInt.getPbcAdjIntDate();
		Double adjLeaseRate = rzAdjInt.getAdjLeaseRate();
		// 还款周期
		Integer reptcycle = rzAdjInt.getReptcycle();
		// 每年还款次数
		int everyYearNum = 12 / reptcycle;
		// 每期利率
		BigDecimal prdRate = BigDecimal.valueOf(adjLeaseRate / everyYearNum / 100).setScale(6,
				BigDecimal.ROUND_HALF_UP);

		int size = adjIntLeaseList.size();
		List<RzPricecalLease> rzPlListNew = new ArrayList<RzPricecalLease>();
		for (int i = 0; i < size; i++) {
			RzAdjIntLease rzAdjIntLeaseTmp = adjIntLeaseList.get(i);
			RzPricecalLease rzPricecalLease = new RzPricecalLease();
			BeanUtils.copyProperties(rzAdjIntLeaseTmp, rzPricecalLease);
			
			String isCheck = rzAdjIntLeaseTmp.getIsCheck();
			if (StringUtil.isEmpty(isCheck) || isCheck.equals(EumConst.N)) {
				// 支付日期
				String rptDate = rzAdjIntLeaseTmp.getRptDate();
				// 本金
				Double rptAmt = rzAdjIntLeaseTmp.getRptAmt();
				Double residualAmt = rzAdjIntLeaseTmp.getResidualAmt();
				
				//上一次剩余本金
				Double lastResidualAmt = ToolUtils.getDouNullAsZero(residualAmt) + ToolUtils.getDouNullAsZero(rptAmt);

				int resDay = DateUtil.compareDate(pbcAdjIntDate, rptDate);

				// 手续费
				Double payFee = rzAdjIntLeaseTmp.getPayFee();

				// 现金流出
				Double cfOut = ToolUtils.getDouNullAsZero(rzAdjIntLeaseTmp.getCfOut());

				// 如果调息日小于等于支付日期，开始调息
				if (resDay <= 0) {
					Double rptInt = BigDecimal.valueOf(lastResidualAmt).multiply(prdRate).setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
					Double rptRent = rptInt + rptAmt;
					Double cfIn = rptRent + ToolUtils.getDouNullAsZero(payFee);
					Double netCfIn = calNetCfIn(cfIn, cfOut);
					rzPricecalLease.setLeaseRate(adjLeaseRate);
					rzPricecalLease.setRptInt(rptInt);
					rzPricecalLease.setRptRent(rptRent);
					rzPricecalLease.setNetCfIn(netCfIn);
				}
			}
			rzPlListNew.add(rzPricecalLease);	
		}
		return rzPlListNew;
	}

	/****
	 * 不调息
	 * 
	 * @param rzAdjInt
	 * @param adjIntLeaseList
	 * @return
	 */
	protected List<RzPricecalLease> calNoAdj(RzAdjInt rzAdjInt, List<RzAdjIntLease> adjIntLeaseList) {

		int size = adjIntLeaseList.size();
		List<RzPricecalLease> rzPlListNew = new ArrayList<RzPricecalLease>();
		for (int i = 0; i < size; i++) {
			RzAdjIntLease rzAdjIntLeaseTmp = adjIntLeaseList.get(i);
			RzPricecalLease rzPricecalLease = new RzPricecalLease();
			BeanUtils.copyProperties(rzAdjIntLeaseTmp, rzPricecalLease);
			rzPlListNew.add(rzPricecalLease);
		}
		return rzPlListNew;
	}

	/****
	 * 随基准调息
	 * 
	 * @param rzAdjInt
	 * @param adjIntLeaseList
	 * @return
	 */
	protected List<RzPricecalLease> calAdjInt(RzAdjInt rzAdjInt, List<RzAdjIntLease> adjIntLeaseList) {
		String adjIntDate = rzAdjInt.getAdjIntDate();
		int yearDays = Integer.valueOf(rzAdjInt.getYeardays());

		Double adjLeaseRate = rzAdjInt.getAdjLeaseRate();

		// 还款周期
		Integer reptcycle = rzAdjInt.getReptcycle();

		// 每年还款次数
		int everyYearNum = 12 / reptcycle;

		// 每期利率
		BigDecimal prdRate = BigDecimal.valueOf(adjLeaseRate / everyYearNum / 100).setScale(6,
				BigDecimal.ROUND_HALF_UP);

		// 调整利率转换为日利率
		Double adjLeaseRateDay = adjLeaseRate / yearDays / 100;

		int size = adjIntLeaseList.size();

		List<RzPricecalLease> rzPlListNew = new ArrayList<RzPricecalLease>();

		for (int i = 0; i < size; i++) {
			RzAdjIntLease rzAdjIntLeaseTmp = adjIntLeaseList.get(i);
			String isCheck = rzAdjIntLeaseTmp.getIsCheck();

			RzPricecalLease rzPricecalLease = new RzPricecalLease();
			BeanUtils.copyProperties(rzAdjIntLeaseTmp, rzPricecalLease);

			if (isCheck.equals(EumConst.N)) {
				String[] payDur = rzAdjIntLeaseTmp.getPaydur().split("~");
				// 支付日期
				String rptDate = rzAdjIntLeaseTmp.getRptDate();

				// 本金
				Double rptAmt = rzAdjIntLeaseTmp.getRptAmt();
				Double residualAmt = rzAdjIntLeaseTmp.getResidualAmt();
				
				//上一次剩余本金
				Double lastResidualAmt = ToolUtils.getDouNullAsZero(residualAmt) + ToolUtils.getDouNullAsZero(rptAmt);

				int resDay = DateUtil.compareDate(adjIntDate, rptDate);

				// 手续费
				Double payFee = rzAdjIntLeaseTmp.getPayFee();

				// 现金流出
				Double cfOut = ToolUtils.getDouNullAsZero(rzAdjIntLeaseTmp.getCfOut());

				// 如果调息日大于支付日期，则采用分段计算利息
				if (resDay == 1) {
					String startDate = payDur[0];
					try {
						// 利率调整之前计算
						int beforDays = DateUtil.daysBetween(startDate, adjIntDate) - 1;
						// 转换日率
						Double leaseRate = rzAdjIntLeaseTmp.getLeaseRate() / yearDays / 100;

						RzPricecalLease rzPricecalLeaseBefor = new RzPricecalLease();
						BeanUtils.copyProperties(rzAdjIntLeaseTmp, rzPricecalLeaseBefor);
						String paydurBefor = startDate + "~" + adjIntDate;
						rzPricecalLeaseBefor.setDurdays(beforDays);
						rzPricecalLeaseBefor.setPaydur(paydurBefor);

						Double rptInt = BigDecimal.valueOf(lastResidualAmt)
								.multiply(BigDecimal.valueOf(leaseRate).multiply(BigDecimal.valueOf(beforDays)))
								.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						rzPricecalLeaseBefor.setRptInt(rptInt);
						rzPlListNew.add(rzPricecalLeaseBefor);

						// 利率调整之后计算
						int afterDays = DateUtil.daysBetween(startDate, rptDate);

						RzPricecalLease rzPricecalLeaseAfter = new RzPricecalLease();
						BeanUtils.copyProperties(rzAdjIntLeaseTmp, rzPricecalLeaseAfter);
						String paydurAfter = startDate + "~" + adjIntDate;
						rzPricecalLeaseAfter.setDurdays(afterDays);
						rzPricecalLeaseAfter.setPaydur(paydurAfter);
						rzPricecalLeaseAfter.setLeaseRate(adjLeaseRate);

						Double rptIntAfter = BigDecimal.valueOf(lastResidualAmt)
								.multiply(BigDecimal.valueOf(adjLeaseRateDay).multiply(BigDecimal.valueOf(afterDays)))
								.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						rzPricecalLeaseAfter.setRptInt(rptIntAfter);

						rzPlListNew.add(rzPricecalLeaseAfter);

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					Double rptInt = BigDecimal.valueOf(lastResidualAmt).multiply(prdRate).setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
					Double rptRent = rptInt + rptAmt;
					Double cfIn = rptRent + ToolUtils.getDouNullAsZero(payFee);
					Double netCfIn = calNetCfIn(cfIn, cfOut);
					rzPricecalLease.setLeaseRate(adjLeaseRate);
					rzPricecalLease.setRptInt(rptInt);
					rzPricecalLease.setRptRent(rptRent);
					rzPricecalLease.setNetCfIn(netCfIn);
				}
			}
			rzPlListNew.add(rzPricecalLease);
		}
		return rzPlListNew;
	}

	/****
	 * 计算因素检查
	 * 
	 * @param rzPmtPlan
	 * @throws BusinessException
	 */
	protected void checkCaFactor(RzAdjInt rzAdjInt) throws BusinessException {

		Integer intType = rzAdjInt.getIntType();
		if (intType == null) {
			throw new BusinessException("调息类型，不能为空！");
		}

		String adjIntDate = rzAdjInt.getAdjIntDate();
		if (adjIntDate == null) {
			throw new BusinessException("调息日期，不能为空！");
		}
		if (StringUtil.isEmpty(rzAdjInt.getYeardays())) {
			throw new BusinessException("年化天数，不能为空！");
		}

		Double adjLeaseRate = rzAdjInt.getAdjLeaseRate();
		if (StringUtil.isEmpty(adjLeaseRate)) {
			throw new BusinessException("调整利率，不能为空！");
		}

		// 还款周期
		Integer reptcycle = rzAdjInt.getReptcycle();
		if (reptcycle == null) {
			throw new BusinessException("还款周期，不能为空！");
		}
	}

	protected Double calNetCfIn(Double cfIn, Double cfOut) {
		return cfIn - cfOut;
	}

}
