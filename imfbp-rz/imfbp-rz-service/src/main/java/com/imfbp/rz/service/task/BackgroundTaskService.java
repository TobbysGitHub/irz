package com.imfbp.rz.service.task;

import org.springframework.transaction.annotation.Transactional;

/**
 * 后台任务接口,需要执行后台任务时，实现该接口并修改对应配置文件即可
 * 
 * @author Administrator
 *
 */
public interface BackgroundTaskService {
	/**
	 * 后台任务执行方法
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Object executeTask(Object obj) throws Exception;

}
