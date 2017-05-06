package com.imfbp.rz.controller;

import java.util.List;
import java.util.Map;

import com.platform.common.seculity.moduleinfo.Moduleinfo;
import com.platform.common.utils.tree.TreeNode;

/**
 * @author server
 *
 */
public class GenerateMenuTree {
	
	//带子的a标签，有图片
	private static String tagsA_sub = "<a href='javascript:void(0)' class='auto'>"
			+ "<span class = 'pull-right text-muted'>"
			+ "<i class='fa fa-fw fa-angle-right text'></i>"
			+ "<i class='fa fa-fw fa-angle-down text-active'></i>"
			+ "</span>"
			+ "<i class='glyphicon %s icon'></i>"
			+ "<span>%s</span></a>";
	//不带子的a标签，有图片
	private static String tagsA_nosub = "<a href='javascript:void(0)' class='auto'>"
			+ "<i class='glyphicon %s icon'></i>"
			+ "<span class='font-bold'>%s</span></a>";
	
	//没有模块的系统用，点击回到首页
	private static String tagsA_nosub_leaf = "<a href='javascript:void(0);' onclick='goHome()' class='auto'>"
			+ "<i class='glyphicon %s icon'></i>"
			+ "<span class='font-bold'>%s</span></a>";
	
	//带子的a标签，不带图片
	private static String tagsA_sub_nop = "<a href='javascript:void(0)' class='auto'>"
			+ "<span class = 'pull-right text-muted'>"
			+ "<i class='fa fa-fw fa-angle-right text'></i>"
			+ "<i class='fa fa-fw fa-angle-down text-active'></i>"
			+ "</span>"
			+ "<span class='font-bold'>%s</span></a>";
	//不带子的a标签，不带图片
	private static String tagsA_nosub_nop = "<a href='javascript:void(0)' class='auto'>"
			+ "<span class='font-bold'>%s</span></a>";
	
	//不带图片的叶子节点，a标签
	private static String tagsA_nop_leaf = "<a href='javascript:void(0)' class='auto' module_url='%s' module_value='%s'>"
			+ "<span>%s</span></a>";
	
	//带图片的叶子节点，a标签
	private static String tagsA_leaf = "<a href='javascript:void(0)' class='auto' module_url='%s' module_value='%s'>"
			+ "<i class='glyphicon %s icon'></i>"
			+ "<span class='font-bold'>%s</span></a>";
	
	private static String tagsLi_nosub = "<li>%s</li>";
	private static String tagsLi_sub = "<li>%s<ul class='nav nav-sub dk'>%s</ul></li>";
	
	
	/**
	 * 得到左侧系统模块菜单栏
	 * @return
	 */
	public String generateMenuTree(List<Map<String, Object>> systemList, Map<String, List<Moduleinfo>> sysModulesMap) {
		//先画根节点，是一个ul标签
		String root = "<ul class='nav'><li class='hidden-folded padder m-t m-b-sm text-muted text-xs'></li>%s</ul>";
		
		String systems = formatSystems(systemList, sysModulesMap);		
		String menu = String.format(root, systems);
		return menu;
	}
	
	/**
	 * 根据系统权限生成li标签
	 * @param systemList
	 * @param sysModulesMap
	 * @return
	 */
	private String formatSystems(List<Map<String, Object>> systemList, Map<String, List<Moduleinfo>> sysModulesMap) {
		String result = "";
		
		if(systemList.size() > 1){
			for(Map<String, Object> system : systemList){
				String systemName = system.get("system_name").toString();
				result += formatOneSystem(system, sysModulesMap.get(systemName));		
			}	
		}else{
			Map<String, Object> system = systemList.get(0);
			String systemName = system.get("system_name").toString();
			List<Moduleinfo> moduleinfos = sysModulesMap.get(systemName);
			TreeNode treeNode = getModuleTree(system, moduleinfos);
			result = formatModules(system.get("system_url").toString(), treeNode, true, false);
		}
		return result;
	}

	/**
	 * 将一个系统及其模块生成html代码
	 * @param system
	 * @param moduleinfos
	 * @return
	 */
	private String formatOneSystem(Map<String, Object> system, List<Moduleinfo> moduleinfos) {
		
		TreeNode treeNode = getModuleTree(system, moduleinfos);
		String result = "";
		String systemA = "";
		String systemName = system.get("system_name").toString();
		String systemIcon = system.get("system_icon_cls").toString();
		//如果系统有模块
		if(moduleinfos!=null && moduleinfos.size()>0){
			systemA = String.format(tagsA_sub, systemIcon, systemName);
			result = String.format(tagsLi_sub, systemA, formatModules(system.get("system_url").toString(), treeNode, false, true));
		}else{
			//如果系统没有模块，分两种情况，一个是系统本身即是叶子节点，这种情况，system中有module_url
			if(system.containsKey("Home")){
				systemA = String.format(tagsA_nosub_leaf, systemIcon, systemName);
				result = String.format(tagsLi_nosub, systemA);	
			}else{
				systemA = String.format(tagsA_nosub, systemIcon ,systemName);
				result = String.format(tagsLi_nosub, systemA);				
			}
		}
		return result;
	}


	/**
	 * 将一个模块树生成html代码
	 * @param treeNode
	 * @return
	 */
	private String formatModules(String systemUrl, TreeNode treeNode, boolean displayPic, boolean indent) {
		String result = "";
		String moduleLi = "";
		String moduleA = "";
		String moduleUrl = "";
		String moduleValue = "";
		if(treeNode != null){		
			List<TreeNode> nodeList = treeNode.getChildren();
			
			for(TreeNode node : nodeList){
				if(node.getChildren() != null && node.getChildren().size() > 0){
					if(displayPic){
						String moduleIcon = node.getIconCls();
						moduleA = String.format(tagsA_sub, moduleIcon, getModuleNodeText(node, indent));
					}else{
						moduleA = String.format(tagsA_sub_nop, getModuleNodeText(node, indent));
					}					
					moduleLi = String.format(tagsLi_sub, moduleA, formatModules(systemUrl,node, false, indent));
				}else{
					//叶子节点，每个叶子节点是一个li标签，里面只有a标签，叶子节点如果url和value不为空，则叶子节点有onclick				
					moduleUrl = node.getUrl().toString();
//					moduleValue = node.getNodeValue().toString();
					//外部链接修改
					if(node.getUrl().indexOf("http://")>-1){
						moduleUrl = node.getUrl().toString();
					}else{
						moduleUrl = systemUrl + node.getUrl().toString();
					}
					moduleValue = node.getNodeValue().toString();

					
					
					if(!moduleUrl.equals("")&&!moduleValue.equals("")){
						if(displayPic){
							String moduleIcon = node.getIconCls();
							moduleA = String.format(tagsA_leaf, moduleUrl, moduleValue, moduleIcon, getModuleNodeText(node, indent)); //叶子模块节点
						}else{
							moduleA = String.format(tagsA_nop_leaf, moduleUrl, moduleValue, getModuleNodeText(node, indent)); //叶子模块节点
						}
					}else{
						if(displayPic){
							String moduleIcon = node.getIconCls();
							moduleA = String.format(tagsA_nosub, moduleIcon, getModuleNodeText(node, indent));
						}else{
							moduleA = String.format(tagsA_nosub_nop, getModuleNodeText(node, indent));
						}
						
					}	
					moduleLi = String.format(tagsLi_nosub, moduleA);
				}
				result += moduleLi;
			}
		}	
		
		return result;
	}

	private Object getModuleNodeText(TreeNode node, boolean indent) {
//		String space = "&nbsp&nbsp&nbsp&nbsp&nbsp"; //五个空格，一个汉字的距离
		String space ="";
		String result = "";
		int i=1; 
		//如果首航缩进，则从第0级level开始缩进
		if(indent){
			i = 0;
		}
		for(;i< node.getNodeLevel(); i++){
			result = result + space;
		}
		return result + node.getText();
	}
	
	/**
	 * 根据系统和模块生成模块树
	 * @param system
	 * @param moduleinfos
	 * @return
	 */
	private TreeNode getModuleTree(Map<String, Object> system, List<Moduleinfo> moduleinfos) {
		TreeNode treeNode = new TreeNode();
		//treeNode是系统节点，node是模块节点
		treeNode.setText(system.get("system_name").toString());
		treeNode.setUrl(system.get("system_url").toString());
		if(moduleinfos != null && moduleinfos.size()>0){
			for(Moduleinfo moduleinfo:moduleinfos){
				TreeNode node = new TreeNode();
				node.setId(moduleinfo.getId());
				node.setIconCls(moduleinfo.getIconCls());
				node.setText(moduleinfo.getModuleName());
				node.setNodeValue(moduleinfo.getModuleValue());
				node.setNodeLevel(Integer.valueOf(moduleinfo.getLevels()));
				node.setNodePId(moduleinfo.getPid());
				node.setUrl(moduleinfo.getUrl());
				if(moduleinfo.getParam()==null){
					moduleinfo.setParam("");
				}
				node.setParam(moduleinfo.getParam()); 
				treeNode.add(node);
			}
		}
		treeNode = formateTreeNode(treeNode);
		return treeNode;
	}

	/**
	 * 生成树结构
	 * @param treeNode
	 * @return
	 */
	private TreeNode formateTreeNode(TreeNode treeNode) {
		TreeNode rootNode = new TreeNode();
		for (int i = 0; i < treeNode.getChildren().size(); i++) {
			TreeNode node = treeNode.getChildren().get(i);
			if(node.getNodeLevel()==1){
				//如果是一级模块，直接在根节点下
				rootNode.add(node);
			}else{
				//如果不是一级模块，则找到node的父节点，并且把node放到父节点之下，以后就不用关心这个节点了
				TreeNode pNode = getTreeNodeById(treeNode,node.getNodePId());
				if(pNode != null){
					pNode.add(node);
				}
			}
		}
		return rootNode;
	}
	
	/**
	 * 在list中寻找一个树节点的父节点
	 * @param treeNode
	 * @param id
	 * @return
	 */
	private TreeNode getTreeNodeById(TreeNode treeNode, String id) {
		List<TreeNode> children = treeNode.getChildren();
		for(TreeNode node:children){
			if(node.getId().trim().equals(id)){
				return node;
			}
		}
		return null;
	}

}
