package com.imfbp.rz.service;

import com.ifbp.boss.rpc.smalluser.domain.query.SmallUserQuery;
import com.ifbp.message.rpc.smallmessage.domain.SmallMessage;
import com.ifbp.message.rpc.smallmessage.domain.query.SmallMessageQuery;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;


public interface DefaultService {
	
	public GridResult<SmallMessage>  getMessageByPage(SmallMessageQuery smallMessageQuery);
	

	public boolean setMessageReadMark(String userId, Long messageId);

	
	public Integer getUnReadMessCount(SmallMessageQuery smallMessageQuery);
	/**
	 * 得到头像url
	 * @param smallUserQuery
	 * @return
	 */
	public Result getImageUrl(SmallUserQuery smallUserQuery);
	
	/**获取用户皮肤**/
	public Result getUserSkin(SmallUserQuery smallUserQuery);
}
