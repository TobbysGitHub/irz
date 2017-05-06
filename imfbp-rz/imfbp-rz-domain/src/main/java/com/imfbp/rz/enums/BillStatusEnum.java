package com.imfbp.rz.enums;

public enum BillStatusEnum {
	FREE("自由",0),
	STARTED("提交",1),
	APPROVING("审批中",2),
	PASS("审批通过",3),
	NOPASS("审批不通过",4);
	
	private String value;
	
	private int index;
	
	private BillStatusEnum(String value, int index){
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
