package com.imfbp.rz.pub;

public class EumConst {

	public final static String Y = "Y";

	public final static String N = "N";
	
	
	
	
	/****
	 * 调息类型：0 不调息、1 随基准调息、2 下一收租日调息
	 * 
	 * @author Administrator
	 *
	 */
	public static enum AdjIntTypeEnum {
		/** 不调息 **/
		NO("不调息", 0),

		/** 随基准调息 **/
		BASE("随基准调息", 1),

		/** 随基准调息 **/
		NEXT_RENT("下一收租日调息", 2);

		private String value;

		private int index;

		private AdjIntTypeEnum(String value, int index) {
			this.value = value;
			this.index = index;
		}

		public String getValue() {
			return this.value;
		}

		public int getIndex() {
			return this.index;
		}

		public static String getName(int index) {
			for (AdjIntTypeEnum adjIntTypeEnum : AdjIntTypeEnum.values()) {
				if (adjIntTypeEnum.getIndex() == index) {
					return adjIntTypeEnum.getValue();
				}
			}
			return "---";
		}

		public String toString() {
			return "{name:'" + this.value + "',value:'" + this.index + "'}";
		}

	}

	/*****
	 * 
	 * 计划收付款类别
	 * <p>
	 * 0、融资金额投放 1、服务费支出 2、收取保证金 3、手续费收取 4、首付款收取 5、租金收取 6、退还保证金
	 * 
	 * @author Administrator
	 *
	 */
	public static enum PlanPmtCategoryEnum {
		/** 融资金额投放 **/
		FINANCING_AMOUNT("融资金额投放", 0),

		/** 服务费支出 **/
		SERVICE_FEE("服务费支出", 1),

		/** 收取保证金 **/
		COLLECT_DEPOSIT("收取保证金", 2),

		/** 手续费收取 **/
		FEE_CHARGE("手续费收取", 3),

		/** 首付款收取 **/
		FIRST_PMT("首付款收取", 4),

		/** 租金收取 **/
		RENT_COLLECTION("租金收取", 5),

		/** 退还保证金 **/
		REFUND_DEPOSIT("退还保证金", 6);

		private String value;

		private int index;

		private PlanPmtCategoryEnum(String value, int index) {
			this.value = value;
			this.index = index;
		}

		public String getValue() {
			return this.value;
		}

		public int getIndex() {
			return this.index;
		}

		public static String getName(int index) {
			for (PlanPmtCategoryEnum planPmtCategoryEnum : PlanPmtCategoryEnum.values()) {
				if (planPmtCategoryEnum.getIndex() == index) {
					return planPmtCategoryEnum.getValue();
				}
			}
			return "---";
		}

		public String toString() {
			return "{name:'" + this.value + "',value:'" + this.index + "'}";
		}

	}

	/*****
	 * 
	 * 计划收付类型
	 * <p>
	 * 
	 * 0、付款 1、收款
	 * 
	 * @author Administrator
	 *
	 */
	public static enum PlanPmtTypeEnum {
		/** 付款 **/
		PAYMENT("付款", 0),

		/** 收款 **/
		RECEIVABLES("收款", 1);

		private String value;

		private int index;

		private PlanPmtTypeEnum(String value, int index) {
			this.value = value;
			this.index = index;
		}

		public String getValue() {
			return this.value;
		}

		public int getIndex() {
			return this.index;
		}

		public static String getName(int index) {
			for (PlanPmtTypeEnum planPmtTypeEnum : PlanPmtTypeEnum.values()) {
				if (planPmtTypeEnum.getIndex() == index) {
					return planPmtTypeEnum.getValue();
				}
			}
			return "---";
		}

		public String toString() {
			return "{name:'" + this.value + "',value:'" + this.index + "'}";
		}

	}

	/*****
	 * 
	 * 项目合同流转状态
	 * <p>
	 * 
	 * 项目状态：报价单生成、立项中、项目评审中、合同签订中、项目已起租、项目租后管理中、项目结清、项目合同作废
	 * 
	 * @author Administrator
	 *
	 */
	public static enum PrjStateEnum {
		/** 生成报价单 **/
		PRICE_NO_GEN("生成报价单", 0),

		/** 立项中 **/
		PRJ_APPLY("立项中", 1),

		/** 项目评审中 **/
		PRJ_REVIEW("项目评审中", 2),

		/** 合同签订中 **/
		PRJ_CONTR("合同签订中", 3),

		/** 项目已起租 **/
		RENT_START("项目已起租", 4),

		/** 项目租后管理中 **/
		RENT_MGT("项目租后管理中", 5),

		/** 项目结清 **/
		PRJ_END("项目结清", 6),

		/** 项目合同作废 **/
		PRJ_CANCEL("项目合同作废", 7);

		private String value;

		private int index;

		private PrjStateEnum(String value, int index) {
			this.value = value;
			this.index = index;
		}

		public String getValue() {
			return this.value;
		}

		public int getIndex() {
			return this.index;
		}

		public static String getName(int index) {
			for (PrjStateEnum prjStateEnum : PrjStateEnum.values()) {
				if (prjStateEnum.getIndex() == index) {
					return prjStateEnum.getValue();
				}
			}
			return "---";
		}

		public String toString() {
			return "{name:'" + this.value + "',value:'" + this.index + "'}";
		}

	}

	/****
	 * 租金计算方法
	 * 
	 * @author Administrator
	 *
	 */
	public static enum RentMethodEnum {
		/** 等额租金法 **/
		EQUAL_RENT("等额租金法", 0),

		/** 等额本金法 **/
		EQUAL_PRIN("等额本金法", 1),

		/** 自由还款法 **/
		FREE("自由还款", 2);

		private String value;

		private int index;

		private RentMethodEnum(String value, int index) {
			this.value = value;
			this.index = index;
		}

		public String getValue() {
			return this.value;
		}

		public int getIndex() {
			return this.index;
		}

		public static String getName(int index) {
			for (RentMethodEnum rentMethodEnum : RentMethodEnum.values()) {
				if (rentMethodEnum.getIndex() == index) {
					return rentMethodEnum.getValue();
				}
			}
			return "---";
		}

		public String toString() {
			return "{name:'" + this.value + "',value:'" + this.index + "'}";
		}

	}
	
	

}
