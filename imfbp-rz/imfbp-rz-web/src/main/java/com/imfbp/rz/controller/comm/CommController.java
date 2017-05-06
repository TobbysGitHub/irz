package com.imfbp.rz.controller.comm;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ifbp.boss.rpc.dic.service.DicItemRpcService;
import com.imfbp.rz.controller.pub.PubBaseContrl;
import com.platform.common.web.result.Result;

@RestController
public class CommController extends PubBaseContrl {

	private static final Logger logger = Logger.getLogger(CommController.class);

	@Autowired
	public DicItemRpcService dicItemRpcClient;

	// 根据核算账簿，获取所有的会计科目体系
	@RequestMapping(value = "rzComm/getDicItemData", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Result getDicItemData(HttpServletRequest req) {

		Result result = new Result();
		// 设置调用失败
		result.setSuccess(false);
		try {
			result.addDefaultModel("data", dicItemRpcClient.getDicItem("DIC_SEX"));
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
