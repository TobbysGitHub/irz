package com.imfbp.rz.domain.rzpricecaleqpt.query;

import java.io.Serializable;
import com.platform.common.utils.query.BaseQuery;

public class RzPricecalEqptQuery extends BaseQuery implements Serializable{

	private static final long serialVersionUID = 1L;

	//主键
	private String pkPricecalEqpt;
	//主键
	private String pkPricecal;
	//主键
	private String pkEqpt;
	//数量
	private Integer num;
	//价格
	private Double price;
	

	public void setPkPricecalEqpt(String pkPricecalEqpt){
		this.pkPricecalEqpt =  pkPricecalEqpt;
	}
	
	public String getPkPricecalEqpt(){
		return pkPricecalEqpt;
	}

	public void setPkPricecal(String pkPricecal){
		this.pkPricecal =  pkPricecal;
	}
	
	public String getPkPricecal(){
		return pkPricecal;
	}

	public void setPkEqpt(String pkEqpt){
		this.pkEqpt =  pkEqpt;
	}
	
	public String getPkEqpt(){
		return pkEqpt;
	}

	public void setNum(Integer num){
		this.num =  num;
	}
	
	public Integer getNum(){
		return num;
	}

	public void setPrice(Double price){
		this.price =  price;
	}
	
	public Double getPrice(){
		return price;
	}
}




 

