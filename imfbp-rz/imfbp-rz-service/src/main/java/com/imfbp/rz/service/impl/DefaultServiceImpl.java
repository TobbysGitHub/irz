package com.imfbp.rz.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifbp.boss.rpc.smalltenantinformation.domain.SmallTenantInformation;
import com.ifbp.boss.rpc.smalltenantinformation.domain.query.SmallTenantInformationQuery;
import com.ifbp.boss.rpc.smalltenantinformation.service.BossTenantInformationRpcService;
import com.ifbp.boss.rpc.smalluser.domain.SmallUser;
import com.ifbp.boss.rpc.smalluser.domain.query.SmallUserQuery;
import com.ifbp.boss.rpc.smalluser.service.BossUserRpcService;
import com.ifbp.message.rpc.smallmessage.domain.SmallMessage;
import com.ifbp.message.rpc.smallmessage.domain.query.SmallMessageQuery;
import com.ifbp.message.rpc.smallmessage.service.SmallMessageRpcService;
import com.imfbp.fastdfs.rpc.api.fastdfs.domain.query.RpcDfsFileInfoQuery;
import com.imfbp.fastdfs.rpc.api.fastdfs.service.IImfbpFastDFSRpcService;
import com.imfbp.rz.service.DefaultService;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

@Component("defaultService")
public class DefaultServiceImpl implements DefaultService{
	
	
	@Autowired
	private SmallMessageRpcService smallMessageRpcClient;
	@Autowired
	private BossUserRpcService userRpcClient;
	
	private BossTenantInformationRpcService tenantInfoService;
	@Autowired
	private IImfbpFastDFSRpcService fastDFSRpcClient;
	
	public GridResult<SmallMessage> getMessageByPage(SmallMessageQuery smallMessageQuery){
		
		int total = smallMessageRpcClient.getMessageTotalcount(smallMessageQuery);
		PaginatedList<SmallMessage> messagePageList = new MysqlPaginatedArrayList<SmallMessage>(
				smallMessageQuery, total);
		List<SmallMessage> smallMessages = smallMessageRpcClient.getMessageByPage(smallMessageQuery);
		messagePageList.addAll(smallMessages);
		GridResult<SmallMessage> result = new GridResult<SmallMessage>(
				messagePageList);
		return result;		
	}
	
	@Override
	public Result getUserSkin(SmallUserQuery smallUserQuery) {
		Result result = new Result();
		result.setSuccess(false);
		SmallUser user = null;
		List<SmallUser> userList = userRpcClient.getBossUserByCondition(smallUserQuery);
		
		if(userList != null && userList.size() > 0){
			user = userList.get(0);
			if(user != null){
				result.addDefaultModel("userskin", user.getUserSkinColor());
				result.setSuccess(true);
			} else {
				result.setErrorMessage("没找到用户信息");
			}
		} else {
			result.setErrorMessage("没找到用户信息");
		}
		
		
		return result;
	}
	
	/**
	 * 得到头像url
	 * @param smallUserQuery
	 * @return
	 */
	public Result getImageUrl(SmallUserQuery smallUserQuery){
		Result result = new Result();
		SmallUser user = null;
		List<SmallUser> userList = userRpcClient.getBossUserByCondition(smallUserQuery);
		
		if(userList != null && userList.size() > 0){
			user = userList.get(0);
			result.addDefaultModel("userskin", user.getUserSkinColor());
			RpcDfsFileInfoQuery rpcDfsFileInfoQuery = new RpcDfsFileInfoQuery();
			if (StringUtil.isNotEmpty(user.getFileFastdfsId())) {
				try {
					 rpcDfsFileInfoQuery.setFileFastdfsId(user.getFileFastdfsId());
					 Result result1 = fastDFSRpcClient.getFileTokenURL(rpcDfsFileInfoQuery);
					 result.addDefaultModel("userheadimageurl", result1.get("fastdfsfileurl"));
				} catch (Exception e) {
					result.setErrorMessage(e.getMessage());
					e.printStackTrace();
					Logger.getLogger(getClass()).error(e.getMessage(),e);
				}	
			}
		}
		
		SmallTenantInformationQuery infoQuery = new SmallTenantInformationQuery();
		infoQuery.setTenantId(user.getTenantId());
		
		List<SmallTenantInformation> tenantInfoList = tenantInfoService.getBossTenantInformationByCondition(infoQuery);
		
		if(tenantInfoList != null && tenantInfoList.size() > 0){
			SmallTenantInformation tenateInfo = tenantInfoList.get(0);
			
			tenateInfo.getFileFastdfsId();
			RpcDfsFileInfoQuery rpcDfsFileInfoQuery = new RpcDfsFileInfoQuery();
			if (StringUtil.isNotEmpty(tenateInfo.getFileFastdfsId())) {
				try {
					 rpcDfsFileInfoQuery.setFileFastdfsId(tenateInfo.getFileFastdfsId());
					 Result result2 = fastDFSRpcClient.getFileTokenURL(rpcDfsFileInfoQuery);
					 result.addDefaultModel("logoimageurl", result2.get("fastdfsfileurl"));
				} catch (Exception e) {
					result.setErrorMessage(e.getMessage());
					e.printStackTrace();
					Logger.getLogger(getClass()).error(e.getMessage(),e);
				}	
			}
		}
		result.setSuccess(true);
		
		
		return result;
	}

	@Override
	public boolean setMessageReadMark(String userId, Long messageId) {
		return smallMessageRpcClient.setMessageReadMark(userId, messageId);
	}


	@Override
	public Integer getUnReadMessCount(SmallMessageQuery smallMessageQuery) {
		return smallMessageRpcClient.getMessageTotalcount(smallMessageQuery);
	}
	
	
	public void setTenantInfoService(
			BossTenantInformationRpcService tenantInfoService) {
		this.tenantInfoService = tenantInfoService;
	}

}
