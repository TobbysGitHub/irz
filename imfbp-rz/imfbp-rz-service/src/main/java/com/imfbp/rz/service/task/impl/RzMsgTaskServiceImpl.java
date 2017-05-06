package com.imfbp.rz.service.task.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;

import com.ifbp.boss.rpc.smallpersonnelinformation.service.BossPersonnelInformationRpcService;
import com.ifbp.boss.rpc.smalluser.domain.SmallUser;
import com.ifbp.boss.rpc.smalluser.domain.query.SmallUserQuery;
import com.ifbp.boss.rpc.smalluser.service.BossUserRpcService;
import com.imfbp.rz.dao.rzbaseparam.RzBaseParamDao;
import com.imfbp.rz.dao.rzoverdatemsg.RzOverdateMsgDao;
import com.imfbp.rz.domain.msgvo.RzOverDateMsgVo;
import com.imfbp.rz.domain.rzbaseparam.RzBaseParam;
import com.imfbp.rz.domain.rzbaseparam.query.RzBaseParamQuery;
import com.imfbp.rz.service.task.BackgroundTaskService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.ToolUtils;

/**
 * @ClassName: RzMsgTaskServiceImpl
 * @Description: 预警类提醒
 * @author: qinhuimin
 * @date 2016年12月22日 下午1:01:06
 *
 */
public class RzMsgTaskServiceImpl implements BackgroundTaskService {
	@Autowired
	private RzOverdateMsgDao rzOverdateMsgDao;
	@Autowired
	private BossPersonnelInformationRpcService bossPersonService;
	@Autowired
	private RzBaseParamDao rzBaseParamDao;
	
	private static final String SEND_USER = "SENDUSER";
	private static final String ERROR = "ERRORMSG";
	
	
	@Autowired
	private BossUserRpcService bossUserRpcService;
	@Override 
	public Object executeTask(Object obj) throws Exception {
		// 批量删除逾期罚息主表
		List<RzBaseParam> rzBaseParamList = rzBaseParamDao.getRzBaseParamAll(new RzBaseParamQuery());

		if (ToolUtils.isNotEmptyCollection(rzBaseParamList)) {
			int advancedays = rzBaseParamList.get(0).getAdvNoticeDays();
			int rpmtdays = rzBaseParamList.get(0).getRpmtTerm();
			// 当前时间
			String currdate = DateUtil.getCurDateStr();
			// 提前提醒时间
			String advancedate = DateUtil.dateAddDays(currdate, +advancedays);
			// 逾期提醒时间
			String rpmtdate = DateUtil.dateAddDays(currdate, -rpmtdays);

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
				// 获取人员翻译
				List<SmallUser> userLists = bossUserRpcService
						.getBossUserByIds(smallUserQuery);

				HashMap<String, SmallUser> personMap = new HashMap<String, SmallUser>();
				for (SmallUser user:userLists) {
					personMap.put(user.getId(), user);
				}

				// 提前提醒
				List<RzOverDateMsgVo> advanceList = new ArrayList<RzOverDateMsgVo>();
				List<RzOverDateMsgVo> rpmtList = new ArrayList<RzOverDateMsgVo>();
				for (RzOverDateMsgVo vo : list) {
					if (vo.getRptdate().equals(advancedate)) {
						advanceList.add(vo);
					} else if (vo.getRptdate().equals(rpmtdate)) {
						rpmtList.add(vo);
					}
				}
				//有预警类提醒信息
				if (advanceList.size() + rpmtList.size() > 0) {
					List<Map<String,Object>> errMessList = new ArrayList<Map<String,Object>>();
					for (int i = 0; i < advanceList.size(); i++) {
						Map<String,Object> map = new HashMap<String,Object>();
						RzOverDateMsgVo vo = advanceList.get(i);
						String errMess = "收款提醒通知：尊敬的" + personMap.get(vo.getPkUserManager()).getUserRealName() + "，您好！合同："
								+ vo.getContrName() + "编号为：" + vo.getContrcode() + " ， " + vo.getNum() + "：将于"
								+ vo.getRptdate() + "到期，请及时还款。";
						map.put(ERROR, errMess);
						map.put(SEND_USER,personMap.get(vo.getPkUserManager()));
						errMessList.add(map);
					}
					for (int i = 0; i < rpmtList.size(); i++) {
						RzOverDateMsgVo vo = rpmtList.get(i);
						Map<String,Object> map = new HashMap<String,Object>();
						String errMess = "逾期提醒通知：尊敬的" + personMap.get(vo.getPkUserManager()).getUserRealName() + "，您好！合同："
								+ vo.getContrName() + "编号为：" + vo.getContrcode() + " ：" + vo.getNum() + "，于"
								+ vo.getRptdate() + "到期,现已逾期" + rpmtdays + "天，请及时还款。";
						map.put(SEND_USER,personMap.get(vo.getPkUserManager()));
						map.put(ERROR,errMess);
						errMessList.add(map);
					}
					return errMessList;
				}
			}
		}
		return null;
	}
}