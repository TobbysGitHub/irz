package com.imfbp.rz.domain.ref;

import java.util.List;
import java.util.Map;

public class RefLevelResultData {

	private String id;
	private String nodeId;
	private String text;
	private Map<String, Object> data;
	private List<RefLevelResultData> nodes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public List<RefLevelResultData> getNodes() {
		return nodes;
	}

	public void setNodes(List<RefLevelResultData> nodes) {
		this.nodes = nodes;
	}

}
