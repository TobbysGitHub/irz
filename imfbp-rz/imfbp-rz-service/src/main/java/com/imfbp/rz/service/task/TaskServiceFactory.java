package com.imfbp.rz.service.task;

import java.util.Map;

public class TaskServiceFactory {

	private Map<String, BackgroundTaskService> taskServicesMap;

	public Map<String, BackgroundTaskService> getTaskServicesMap() {
		return taskServicesMap;
	}

	public void setTaskServicesMap(
			Map<String, BackgroundTaskService> taskServicesMap) {
		this.taskServicesMap = taskServicesMap;
	}

}
