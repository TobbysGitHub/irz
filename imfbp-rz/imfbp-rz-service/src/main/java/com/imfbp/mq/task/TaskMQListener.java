package com.imfbp.mq.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.ifbp.boss.rpc.smalluser.domain.SmallUser;
import com.ifbp.message.rpc.smallmessage.domain.SmallMessage;
import com.ifbp.message.rpc.smallmessage.service.SmallMessageRpcService;
import com.imfbp.rz.pub.IRZConsts;
import com.imfbp.rz.service.task.BackgroundTaskService;
import com.imfbp.rz.service.task.TaskServiceFactory;
import com.imfbp.rz.util.DateUtil;
import com.platform.common.invocation.InvocationInfoProxy;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.mq.rabbitmq.RabbitMQProducer;

import net.sf.json.JSONObject;

public class TaskMQListener implements MessageListener {

	@Autowired
	private TaskServiceFactory taskServiceFactory;
	@Autowired
	private SmallMessageRpcService smallMessageRpcService;
	
	private RabbitMQProducer	rabbitMQProducer;

	private final static Logger logger = Logger.getLogger(TaskMQListener.class);

	@Override
	public void onMessage(Message message) {
		try {
			if (message != null) {
				logger.info("TaskMQListener 27 : " + message);
				String body = new String(message.getBody(), "UTF-8");
				if (!StringUtils.isEmpty(body)) {
					JSONObject jsonObj = JSONObject.fromObject(body);
					if (jsonObj != null) {
						JSONObject bodyMsg = (JSONObject) jsonObj
								.get("message");
						logger.info("TaskMQListener 36 : " + bodyMsg);
						if (bodyMsg != null) {
							// 任务类型taskType
							Object taskType = bodyMsg.get("taskType");
							if (taskType != null && taskServiceFactory
											.getTaskServicesMap() != null
									&& taskServiceFactory.getTaskServicesMap().containsKey(taskType)) {
								logger.info("taskServiceFactory.getXdTaskServicesMap():start" + bodyMsg);
								BackgroundTaskService taskService = taskServiceFactory.getTaskServicesMap().get(taskType);
								logger.info("taskServiceFactory.getXdTaskServicesMap():end" + bodyMsg);
								if(taskService!=null){
									String tenantid = (String) bodyMsg.get("tenantId");
									// rpc获取所有租户IT,for循环跑每一个租户的数据
									if(!StringUtil.isEmpty(tenantid)){
										InvocationInfoProxy.setTenantid(IRZConsts.DBPRE + tenantid.toLowerCase());
										Object messageNote = taskService.executeTask(null);
										//处理消息
										sendTaskMessage(bodyMsg, messageNote);
									} else {
										logger.error("tenantid is null" + bodyMsg);
									}
									
								}else{
									logger.error("xdTaskService is null" + bodyMsg);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("后台任务执行出现异常，异常信息为：" + e.getMessage(), e);
		}
	}
	
	private void sendTaskMessage(JSONObject bodyMsg,Object messageNote){
		String tenantid = (String) bodyMsg.get("tenantId");
		if(messageNote == null){
			logger.info("任务消息为空，不发送");
			return;
		}
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> smallList = (List<Map<String,Object>>)messageNote;
		if(smallList.size() <= 0 ){
			logger.info("任务消息为空，不发送");
			return;
		}
		
		StringBuffer messagecenter = new StringBuffer();
		StringBuffer mailusers = new StringBuffer();
		StringBuffer userphones = new StringBuffer();
		//需处理 消息发送人
		String messageType = bodyMsg.optString("taskMessageType");
		
		
		if(smallList.size() > 0){
			List<SmallMessage> messageList = new ArrayList<SmallMessage>();
			for (int i = 0; i < smallList.size(); i++) {
				SmallMessage message = new SmallMessage();
				message.setCreator("预警用户");
				message.setCreated(DateUtil.getTs());
				message.setContent((String)smallList.get(i).get("ERRORMSG"));
				message.setTenantid(tenantid);
				message.setUserids(((SmallUser)smallList.get(i).get("SENDUSER")).getId());
				if("2".equals(messageType)){
					message.setTitle("通知消息");
					message.setType(1);
				} else {
					message.setTitle("预警通知");
					message.setType(3);
				}
				message.setSystemid("4");
				messageList.add(message);
			}
			smallMessageRpcService.setMessage(messageList);
		}
		
		
		
//		if(mailusers.length() > 0){
//			Mail mail = new Mail();
//	        mail.setHost(PropertyUtil.getPropertyByKey("mail.host")); // 设置邮件服务器,如果不用163的,自己找找看相关的  
//	        mail.setSender(PropertyUtil.getPropertyByKey("mail.sender"));  
//	        mail.setReceiver(mailusers.substring(0, mailusers.length() - 1)); // 接收人  
//	        mail.setUsername(PropertyUtil.getPropertyByKey("mail.username")); // 登录账号,一般都是和邮箱名一样吧  
//	        mail.setPassword(PropertyUtil.getPropertyByKey("mail.password")); // 发件人邮箱的登录密码  
//	        mail.setTaskid(UUID.randomUUID().toString());
////	        mail.setReplyto("imfbp-msgreceipt-mail-exchange");
//	        mail.setSubject("预警通知");  
//	        String htmlMessage = "";
//	        for (int i = 0; i < messages.length; i++) {
//	        	htmlMessage += "<h1>"+messages[i]+"</h1>";
//	        }
//	        mail.setMessage(htmlMessage);  
//	        
//	        rabbitMQProducer.publishMsg("imfbp-bmsgsender-mail-exchange", JSONObject.fromObject(mail).toString());
//	        logger.debug("发送邮件消息到邮件服务器成功！");
//		}
		if(userphones.length() > 0 ){
			//发送短信
		}
		
	}
	
	
	public void setTaskServiceFactory(TaskServiceFactory taskServiceFactory) {
		this.taskServiceFactory = taskServiceFactory;
	}

	public void setSmallMessageRpcService(
			SmallMessageRpcService smallMessageRpcService) {
		this.smallMessageRpcService = smallMessageRpcService;
	}

	public void setRabbitMQProducer(RabbitMQProducer rabbitMQProducer) {
		this.rabbitMQProducer = rabbitMQProducer;
	}
}
