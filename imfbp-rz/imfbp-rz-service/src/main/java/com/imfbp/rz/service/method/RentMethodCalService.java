package com.imfbp.rz.service.method;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.imfbp.rz.domain.exception.BusinessException;
import com.imfbp.rz.domain.rzpricecal.RzPricecal;
import com.imfbp.rz.domain.rzpricecalcf.RzPricecalCf;
import com.imfbp.rz.domain.rzpricecallease.RzPricecalLease;
import com.imfbp.rz.pub.EumConst;
import com.imfbp.rz.service.method.impl.EqualPrinMethodImpl;
import com.imfbp.rz.service.method.impl.EqualRentMethodImpl;
import com.imfbp.rz.service.method.impl.FreeMethodImpl;

/*****
 * 
 * 租金计算服务
 * 
 * @author Administrator
 *
 */
@Component("rentMethodCalService")
public class RentMethodCalService {

	private Map<Integer, AbstractRentMethodCal> priceMetdCalMap = new HashMap<Integer, AbstractRentMethodCal>();

	public List<RzPricecalLease> calLease(RzPricecal rzPricecal) throws BusinessException {
		Integer reptMethod = rzPricecal.getReptway();
		return getCalMethod(reptMethod).calLease(rzPricecal);
	}
	
	public List<RzPricecalLease> calLeaseChange(RzPricecal rzPricecal) throws BusinessException {
		Integer reptMethod = rzPricecal.getReptway();
		return getCalMethod(reptMethod).calLeaseChange(rzPricecal);
	}

	public List<RzPricecalCf> calCf(RzPricecal rzPricecal) throws BusinessException {
		Integer reptMethod = rzPricecal.getReptway();
		return getCalMethod(reptMethod).calCf(rzPricecal);
	}
	
	public Double calIRR(List<Double> netCfInList, RzPricecal rzPricecal) throws BusinessException {
		Integer reptMethod = rzPricecal.getReptway();
		Integer reptcycle = rzPricecal.getReptcycle();
		return getCalMethod(reptMethod).calIRR(netCfInList, reptcycle);
	}

	private AbstractRentMethodCal getCalMethod(Integer reptMethod) throws BusinessException {
		AbstractRentMethodCal rentMethodCal = priceMetdCalMap.get(reptMethod);
		
		if (rentMethodCal == null) {
			if (reptMethod == EumConst.RentMethodEnum.EQUAL_RENT.getIndex()) {
				AbstractRentMethodCal equalRentCal = new EqualRentMethodImpl();
				priceMetdCalMap.put(reptMethod, equalRentCal);
			} else if (reptMethod == EumConst.RentMethodEnum.EQUAL_PRIN.getIndex()) {
				AbstractRentMethodCal equalPrinCal = new EqualPrinMethodImpl();
				priceMetdCalMap.put(reptMethod, equalPrinCal);
			} else if (reptMethod == EumConst.RentMethodEnum.FREE.getIndex()) {
				AbstractRentMethodCal freeCal = new FreeMethodImpl();
				priceMetdCalMap.put(reptMethod, freeCal);
			}
		}
		rentMethodCal = priceMetdCalMap.get(reptMethod);
		if (reptMethod == null || rentMethodCal == null) {
			throw new BusinessException("未指定租赁还款方法！");
		}
		return rentMethodCal;
	}

}
