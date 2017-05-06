package com.imfbp.rz.service.ref;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.ref.RefBaseQuery;
import com.imfbp.rz.domain.ref.RefResult;
import com.platform.common.web.result.Result;

public interface PubRefDataService {

	public Result getRefDatasByQuery(RefBaseQuery refBaseQuery)
			throws Exception;

	public List<RefResult> getRefTranslateDatas(RefBaseQuery refBaseQuery,
			Map<String, String[]> values) throws Exception;
}
