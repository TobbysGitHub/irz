package com.loan.test;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.imfbp.loan.domain.xdpreloandoc.XdPreLoandoc;
import com.imfbp.loan.service.xdpreloandoc.XdPreLoandocService;

public class XdUnitTest extends TestCase {

	ClassPathXmlApplicationContext cap = null;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		cap = new ClassPathXmlApplicationContext(
				new String[] { "classpath:spring-util.xml" });

	}

	public void test() {
		xdPreLoanappService();
	}

	/**
	 * 尽职调查测试
	 */
	public void xdPreLoandocService() {
		XdPreLoandocService service = (XdPreLoandocService) cap
				.getBean("xdPreLoandocService");
		XdPreLoandoc xdPreLoandoc = new XdPreLoandoc();
		// xdPreLoandoc.setLoanappCode(loanappCode);
		// service.finishApproval(xdPreLoandoc);
	}

	/**
	 * 贷款申请表测试
	 */
	public void xdPreLoanappService() {
		// XdPreLoanappService service = (XdPreLoanappService) cap
		// .getBean("xdPreLoanappService");
		// XdPreLoanapp xdPreLoanapp = new XdPreLoanapp();
		// xdPreLoanapp.setMainCust("xd000000000000000000");
		// service.insertXdPreLoanapp(xdPreLoanapp);
		// // 测试获取所有客户参照数据
		// Object xdPer = service.getXdRefCustAll();
		// assertNotNull(xdPer);
		// // 测试根据客户ID获取客户数据
		// XdPreLoanappQuery xdPreLoanappQuery = new XdPreLoanappQuery();
		// xdPreLoanappQuery.setMainCust("xd000000000000000000");
		// Object obj = service.getXdCustById(xdPreLoanappQuery);
		// assertNotNull(obj);
	}
}
