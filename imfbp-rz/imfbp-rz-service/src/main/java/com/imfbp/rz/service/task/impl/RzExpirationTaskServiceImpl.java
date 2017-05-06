package com.imfbp.rz.service.task.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ifbp.boss.rpc.smalluser.domain.SmallUser;
import com.ifbp.boss.rpc.smalluser.domain.query.SmallUserQuery;
import com.ifbp.boss.rpc.smalluser.service.BossUserRpcService;
import com.imfbp.rz.dao.rzoverdatemsg.RzOverdateMsgDao;
import com.imfbp.rz.domain.msgvo.RzOverDateMsgVo;
import com.imfbp.rz.service.task.BackgroundTaskService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.ToolUtils;

/**
 * @ClassName: RzExpirationTaskServiceImpl
 * @Description: 到期通知类提醒
 * @author: qinhuimin
 * @date 2016年12月22日 下午1:01:27
 *
 */

public class RzExpirationTaskServiceImpl implements BackgroundTaskService {
	
	private static final String SEND_USER = "SENDUSER";
	private static final String ERROR = "ERRORMSG";
	
	private RzOverdateMsgDao rzOverdateMsgDao;
	@Autowired
	private BossUserRpcService bossUserRpcService;
	
	@Override
	public Object executeTask(Object obj) throws Exception {
		String currdate = DateUtil.getCurDateStr();
		List<RzOverDateMsgVo> list = rzOverdateMsgDao.getRzOverDateMsg();

		if (ToolUtils.isNotEmptyCollection(list)) {
			
			StringBuffer personIds = new StringBuffer();
			for (int i = 0; i < list.size(); i++) {
				RzOverDateMsgVo rzOverDateMsg = list.get(i);
				personIds.append("'");
				personIds.append(rzOverDateMsg.getPkUserManager());
				personIds.append("'");
				personIds.append(",");
			}
			// 获取人员翻译
			SmallUserQuery smallUserQuery = new SmallUserQuery();
			smallUserQuery.setIds(personIds.toString().substring(0,personIds.length()-1));
			List<SmallUser> userLists = bossUserRpcService
					.getBossUserByIds(smallUserQuery);

			HashMap<String, SmallUser> personMap = new HashMap<String, SmallUser>();
			for (SmallUser user:userLists) {
				personMap.put(user.getId(), user);
			}

			// 提前提醒
			List<RzOverDateMsgVo> expirationList = new ArrayList<RzOverDateMsgVo>();
			for (RzOverDateMsgVo vo : list) {
				if (vo.getRptdate().equals(currdate)) {
					expirationList.add(vo);
				}
			}
			if (expirationList.size() > 0) {
				List<Map<String,Object>> errMessList = new ArrayList<Map<String,Object>>();
				for (int i = 0; i < expirationList.size(); i++) {
					RzOverDateMsgVo vo = expirationList.get(i);
					Map<String,Object> map = new HashMap<String,Object>();
					String errMess = "收款提醒通知：尊敬的" + personMap.get(vo.getPkUserManager()).getUserRealName() + "，您好！合同：" + vo.getContrName()
							+ "编号为：" + vo.getContrcode() + " ：" + vo.getNum() + "，将于" + vo.getRptdate()
							+ "到期。";
					map.put(SEND_USER, personMap.get(vo.getPkUserManager()));
					map.put(ERROR, errMess);
					errMessList.add(map);
				}
				return errMessList;
			}
		}
		return null;
	}

	public RzOverdateMsgDao getRzOverdateMsgDao() {
		return rzOverdateMsgDao;
	}

	public void setRzOverdateMsgDao(RzOverdateMsgDao rzOverdateMsgDao) {
		this.rzOverdateMsgDao = rzOverdateMsgDao;
	}
	
}