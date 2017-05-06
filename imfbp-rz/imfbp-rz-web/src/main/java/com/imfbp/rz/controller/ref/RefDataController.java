package com.imfbp.rz.controller.ref;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.imfbp.rz.controller.pub.BdBaseController;
import com.imfbp.rz.domain.ref.RefBaseQuery;
import com.imfbp.rz.domain.ref.RefResult;
import com.imfbp.rz.service.ref.impl.DefaultPubRefDataServiceImpl;
import com.imfbp.rz.util.ResultUtils;
import com.platform.common.seculity.annotation.AccessSeculity;

@RestController
public class RefDataController extends BdBaseController {

	@Autowired
	private DefaultPubRefDataServiceImpl defaultPubRefDataServiceImpl;

	private final static Logger logger = Logger
			.getLogger(RefDataController.class);

	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "refData/getRefDatas", method = { RequestMethod.POST })
	public Object getRefDatas(RefBaseQuery refBaseQuery,
			HttpServletRequest req, HttpServletResponse resp) {
		try {
			if (refBaseQuery == null) {
				refBaseQuery = new RefBaseQuery();
			}
			refBaseQuery.setIsTranslate(false);
			refBaseQuery.setOrgId(getOrgId());
			refBaseQuery.setTenantId(getTenantId());
			RefResult result = defaultPubRefDataServiceImpl
					.getRefDatasByQuery(refBaseQuery);
			if (result != null) {
				result.setSuccess(true);
				result.setSuccessMessage("查询数据成功！");
			}
			return result;
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			StringWriter writer = new StringWriter();
			e.printStackTrace(new PrintWriter(writer));
			return ResultUtils
					.getExceptionResult(writer.toString());
		}
	}

	/**
	 * 获取参照翻译数据
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@AccessSeculity(code = "PERMITVALUE_PERMITVALUE")
	@RequestMapping(value = "refData/getRefTranslateDatas", method = { RequestMethod.POST })
	public List<RefResult> getRefTranslateDatas(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			Map<String, String[]> values = req.getParameterMap();
			RefBaseQuery refBaseQuery = new RefBaseQuery();
			refBaseQuery.setOrgId(getOrgId());
			refBaseQuery.setTenantId(getTenantId());
			List<RefResult> resultLists = defaultPubRefDataServiceImpl
					.getRefTranslateDatas(refBaseQuery, values);
			return resultLists;
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			List<RefResult> resultLists = new ArrayList<RefResult>();
			return resultLists;
		}
	}

	public DefaultPubRefDataServiceImpl getDefaultPubRefDataServiceImpl() {
		return defaultPubRefDataServiceImpl;
	}

	public void setDefaultPubRefDataServiceImpl(
			DefaultPubRefDataServiceImpl defaultPubRefDataServiceImpl) {
		this.defaultPubRefDataServiceImpl = defaultPubRefDataServiceImpl;
	}

}
