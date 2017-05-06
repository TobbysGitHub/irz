package com.imfbp.rz.dao.rzassetriskclass.impl;

import java.util.List;
import java.util.Map;

import com.imfbp.rz.domain.rzassetriskclass.RzAssetRiskclass;
import com.imfbp.rz.domain.rzassetriskclass.query.RzAssetRiskclassQuery;
import com.imfbp.rz.dao.rzassetriskclass.RzAssetRiskclassDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class RzAssetRiskclassDaoImpl extends SqlSessionDaoSupport implements RzAssetRiskclassDao{

	/**
	 * 添加
	 * @param rzAssetRiskclass
	 * @return
	 */
	@Override
	public void insertRzAssetRiskclass(RzAssetRiskclass rzAssetRiskclass){
		this.getSqlSession().insert("rzAssetRiskclass.insertRzAssetRiskclass", rzAssetRiskclass);
	}
	
	/**
	 * 批量添加
	 * @param List<rzAssetRiskclass>
	 * @return
	 */
	public void insertBatchRzAssetRiskclass(List<RzAssetRiskclass> rzAssetRiskclassList){
		this.getSqlSession().insert("rzAssetRiskclass.insertBatchRzAssetRiskclass", rzAssetRiskclassList);
	}
	
	/**
	 * 删除 (真正删除数据库数据)
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	@Override
	public boolean deleteRzAssetRiskclassById(RzAssetRiskclassQuery rzAssetRiskclassQuery){
		return this.getSqlSession().delete("rzAssetRiskclass.deleteRzAssetRiskclassById", rzAssetRiskclassQuery)>0;
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	@Override
	public boolean deleteRzAssetRiskclassByCondition(RzAssetRiskclassQuery rzAssetRiskclassQuery){
		return this.getSqlSession().delete("rzAssetRiskclass.deleteRzAssetRiskclassByCondition", rzAssetRiskclassQuery)>0;
	}
	
	/**
	 * 根据主键批量删除  (真正删除数据库数据)
	 * @param data
	 * @return
	 */
	@Override
	public boolean deleteRzAssetRiskclassByBatchId(Map<String,Object> data){
		return this.getSqlSession().delete("rzAssetRiskclass.deleteRzAssetRiskclassByBatchId", data)>0;
	}
    
	/**
	 * 逻辑删除 (修改数据库数据为删除状态)
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzAssetRiskclassById(RzAssetRiskclassQuery rzAssetRiskclassQuery){
		return this.getSqlSession().update("rzAssetRiskclass.logicDeleteRzAssetRiskclassById", rzAssetRiskclassQuery)>0;
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzAssetRiskclassByCondition(RzAssetRiskclassQuery rzAssetRiskclassQuery){
		return this.getSqlSession().update("rzAssetRiskclass.logicDeleteRzAssetRiskclassByCondition", rzAssetRiskclassQuery)>0;
	}
	
	/**
	 * 根据主键批量逻辑删除 (修改数据库数据为删除状态)
	 * @param data
	 * @return
	 */
	@Override
	public boolean logicDeleteRzAssetRiskclassByBatchId(Map<String,Object> data){
		return this.getSqlSession().update("rzAssetRiskclass.logicDeleteRzAssetRiskclassByBatchId", data)>0;
	}
	
	/**
	 * 修改
	 * @param rzAssetRiskclassQuery
	 * @return
	 */	
	@Override
	public boolean updateRzAssetRiskclassById(RzAssetRiskclass rzAssetRiskclass){
		return this.getSqlSession().update("rzAssetRiskclass.updateRzAssetRiskclassById", rzAssetRiskclass)>0;
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzAssetRiskclassList
	 * @return
	 */
	public boolean updateRzAssetRiskclassByBatchId(List<RzAssetRiskclass> rzAssetRiskclassList){
		return this.getSqlSession().update("rzAssetRiskclass.updateRzAssetRiskclassByBatchId", rzAssetRiskclassList)>0;
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzAssetRiskclassByCondition(Map<String,Object> data){
		return this.getSqlSession().update("rzAssetRiskclass.updateRzAssetRiskclassByCondition", data)>0;
	}
	
	/**
	 * 根据id查询
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	@Override
	public RzAssetRiskclass getRzAssetRiskclassById(RzAssetRiskclassQuery rzAssetRiskclassQuery){
		return this.getSqlSession().selectOne("rzAssetRiskclass.getRzAssetRiskclassById",rzAssetRiskclassQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzAssetRiskclassQuery
	 * @return
	 */
	@Override
	public List<RzAssetRiskclass> getRzAssetRiskclassAll(RzAssetRiskclassQuery rzAssetRiskclassQuery){
		return this.getSqlSession().selectList("rzAssetRiskclass.getRzAssetRiskclassAll",rzAssetRiskclassQuery);
	}
	
	/**
	 * 分页查询
	 * @param permitOperation
	 * @return
	 */
	@Override
	public List<RzAssetRiskclass> getRzAssetRiskclassByPage(RzAssetRiskclassQuery rzAssetRiskclassQuery){
		return this.getSqlSession().selectList("rzAssetRiskclass.getRzAssetRiskclassByPage",rzAssetRiskclassQuery);
	}
	
	/**
	 * 分页查询查询总数
	 * @param permitOperation
	 * @return
	 */
	@Override
	public Integer getRzAssetRiskclassByPageCount(RzAssetRiskclassQuery rzAssetRiskclassQuery){
		return this.getSqlSession().selectOne("rzAssetRiskclass.getRzAssetRiskclassByPageCount",rzAssetRiskclassQuery);
	}
}