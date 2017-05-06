package com.imfbp.rz.service.task.impl;

import com.imfbp.rz.service.task.BackgroundTaskService;

public class DefaultTaskServiceImpl implements BackgroundTaskService {

	@Override
	public Object executeTask(Object obj) throws Exception {
		System.out.println("\n\n\n\n后台任务接口实现：DefaultXdTaskServiceImpl\n\n\n\n");
		return null;
	}

}
