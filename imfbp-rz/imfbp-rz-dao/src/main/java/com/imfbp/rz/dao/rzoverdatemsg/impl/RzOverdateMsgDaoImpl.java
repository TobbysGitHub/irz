package com.imfbp.rz.dao.rzoverdatemsg.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.imfbp.rz.dao.rzoverdatemsg.RzOverdateMsgDao;
import com.imfbp.rz.domain.msgvo.RzOverDateMsgVo;

public class RzOverdateMsgDaoImpl extends SqlSessionDaoSupport implements RzOverdateMsgDao {

	@Override
	public List<RzOverDateMsgVo> getRzOverDateMsg() {
		return this.getSqlSession().selectList("RzOverDateMsg.getRzOverDateMsg");	
	}
}
