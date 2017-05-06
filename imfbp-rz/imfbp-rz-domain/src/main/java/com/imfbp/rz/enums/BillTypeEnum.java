package com.imfbp.rz.enums;

public enum BillTypeEnum {
	VEHICLE("个车",0),
	TEAMVEHICLE("团车",1),
	TARGET("财产险",2),
	LIFE("寿险",3);
	
	private String value;
	
	private int index;
	
	private BillTypeEnum(String value, int index){
		this.value = value;
		this.index = index;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public int getIndex(){
		return this.index;
	}
	public String toString(){
		return "{pk:'"+this.index+"',name:'"+this.value+"',value:'"+this.index+"'}";
	}
}
