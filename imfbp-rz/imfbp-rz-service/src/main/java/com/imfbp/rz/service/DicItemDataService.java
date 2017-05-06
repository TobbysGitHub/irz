package com.imfbp.rz.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("dicItemDataService")
public class DicItemDataService {
	/**
	 * 险类
	 */
	private String dicInsuranceType;
	/**
	 * 人员岗位
	 */
	private String dicPersonnelJob;
	/**
	 * 人员级别
	 */
	private String dicPersonnelLevel;

	public String getDicInsuranceType() {
		return dicInsuranceType;
	}

	public void setDicInsuranceType(String dicInsuranceType) {
		this.dicInsuranceType = dicInsuranceType;
	}

	public String getDicPersonnelJob() {
		return dicPersonnelJob;
	}

	public void setDicPersonnelJob(String dicPersonnelJob) {
		this.dicPersonnelJob = dicPersonnelJob;
	}

	public String getDicPersonnelLevel() {
		return dicPersonnelLevel;
	}

	public void setDicPersonnelLevel(String dicPersonnelLevel) {
		this.dicPersonnelLevel = dicPersonnelLevel;
	}

}
