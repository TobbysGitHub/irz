package com.imfbp.rz.service.rzprjcontrchg.impl;

import java.util.*;

import com.imfbp.rz.dao.rzprjcontr.RzPrjcontrDao;
import com.imfbp.rz.dao.rzprjcontrchgeqpt.RzPrjcontrChgEqptDao;
import com.imfbp.rz.dao.rzprjcontrchgguar.RzPrjcontrChgGuarDao;
import com.imfbp.rz.dao.rzprjcontrchgguarb.RzPrjcontrChgGuarBDao;
import com.imfbp.rz.dao.rzprjcontrchgins.RzPrjcontrChgInsDao;
import com.imfbp.rz.dao.rzprjcontrchginseqpt.RzPrjcontrChgInsEqptDao;
import com.imfbp.rz.dao.rzprjcontrchglessee.RzPrjcontrChgLesseeDao;
import com.imfbp.rz.dao.rzprjcontrchgpur.RzPrjcontrChgPurDao;
import com.imfbp.rz.dao.rzprjcontrchgsupplier.RzPrjcontrChgSupplierDao;
import com.imfbp.rz.dao.rzprjcontreqpt.RzPrjcontrEqptDao;
import com.imfbp.rz.dao.rzprjcontrguar.RzPrjcontrGuarDao;
import com.imfbp.rz.dao.rzprjcontrguarb.RzPrjcontrGuarBDao;
import com.imfbp.rz.dao.rzprjcontrins.RzPrjcontrInsDao;
import com.imfbp.rz.dao.rzprjcontrinseqpt.RzPrjcontrInsEqptDao;
import com.imfbp.rz.dao.rzprjcontrlessee.RzPrjcontrLesseeDao;
import com.imfbp.rz.dao.rzprjcontrpur.RzPrjcontrPurDao;
import com.imfbp.rz.dao.rzprjcontrsupplier.RzPrjcontrSupplierDao;
import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjcontr.query.RzPrjcontrQuery;
import com.imfbp.rz.domain.rzprjcontrchgeqpt.RzPrjcontrChgEqpt;
import com.imfbp.rz.domain.rzprjcontrchgguar.RzPrjcontrChgGuar;
import com.imfbp.rz.domain.rzprjcontrchgguarb.RzPrjcontrChgGuarB;
import com.imfbp.rz.domain.rzprjcontrchgins.RzPrjcontrChgIns;
import com.imfbp.rz.domain.rzprjcontrchginseqpt.RzPrjcontrChgInsEqpt;
import com.imfbp.rz.domain.rzprjcontrchglessee.RzPrjcontrChgLessee;
import com.imfbp.rz.domain.rzprjcontrchgpur.RzPrjcontrChgPur;
import com.imfbp.rz.domain.rzprjcontrchgsupplier.RzPrjcontrChgSupplier;
import com.imfbp.rz.domain.rzprjcontreqpt.RzPrjcontrEqpt;
import com.imfbp.rz.domain.rzprjcontreqpt.query.RzPrjcontrEqptQuery;
import com.imfbp.rz.domain.rzprjcontrguar.RzPrjcontrGuar;
import com.imfbp.rz.domain.rzprjcontrguar.query.RzPrjcontrGuarQuery;
import com.imfbp.rz.domain.rzprjcontrguarb.RzPrjcontrGuarB;
import com.imfbp.rz.domain.rzprjcontrguarb.query.RzPrjcontrGuarBQuery;
import com.imfbp.rz.domain.rzprjcontrins.RzPrjcontrIns;
import com.imfbp.rz.domain.rzprjcontrins.query.RzPrjcontrInsQuery;
import com.imfbp.rz.domain.rzprjcontrinseqpt.RzPrjcontrInsEqpt;
import com.imfbp.rz.domain.rzprjcontrinseqpt.query.RzPrjcontrInsEqptQuery;
import com.imfbp.rz.domain.rzprjcontrlessee.RzPrjcontrLessee;
import com.imfbp.rz.domain.rzprjcontrlessee.query.RzPrjcontrLesseeQuery;
import com.imfbp.rz.domain.rzprjcontrpur.RzPrjcontrPur;
import com.imfbp.rz.domain.rzprjcontrpur.query.RzPrjcontrPurQuery;
import com.imfbp.rz.domain.rzprjcontrsupplier.RzPrjcontrSupplier;
import com.imfbp.rz.domain.rzprjcontrsupplier.query.RzPrjcontrSupplierQuery;
import com.imfbp.rz.util.PrimaryKeyIdWorker;
import com.imfbp.rz.util.ToolUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.platform.common.utils.page.PaginatedList;
import com.platform.common.utils.page.impl.MysqlPaginatedArrayList;
import com.platform.common.utils.StringUtil;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;
import com.platform.common.web.result.GridResult;
import com.platform.common.web.result.Result;

import com.imfbp.rz.domain.rzprjcontrchg.RzPrjcontrChg;
import com.imfbp.rz.domain.rzprjcontrchg.query.RzPrjcontrChgQuery;
import com.imfbp.rz.dao.rzprjcontrchg.RzPrjcontrChgDao;
import com.imfbp.rz.service.rzprjcontrchg.RzPrjcontrChgService;
import com.imfbp.rz.util.DateUtil;





@Component("rzPrjcontrChgService")
public class RzPrjcontrChgServiceImpl implements RzPrjcontrChgService{


	private RzPrjcontrChgDao rzPrjcontrChgDao;
	private RzPrjcontrDao rzPrjcontrDao;
	//合同供应商Dao
	private RzPrjcontrSupplierDao rzPrjcontrSupplierDao;
	//合同供应商采购合同Dao
	private RzPrjcontrPurDao rzPrjcontrPurDao;
	//合同变更供应商Dao
	private RzPrjcontrChgSupplierDao rzPrjcontrChgSupplierDao;
	//合同变更采购合同Dao
	private RzPrjcontrChgPurDao rzPrjcontrChgPurDao;
	//合同承租人Dao
	private RzPrjcontrLesseeDao rzPrjcontrLesseeDao;
	//合同变更承租人Dao
	private RzPrjcontrChgLesseeDao rzPrjcontrChgLesseeDao;
	//合同租赁物Dao
	private RzPrjcontrEqptDao rzPrjcontrEqptDao;
	//合同变更租赁物Dao
	private RzPrjcontrChgEqptDao rzPrjcontrChgEqptDao;
	//合同担保方主表Dao
	private RzPrjcontrGuarDao rzPrjcontrGuarDao;
	//合同担保方子表Dao
	private RzPrjcontrGuarBDao rzPrjcontrGuarBDao;
	//合同变更担保方主表Dao
	private RzPrjcontrChgGuarDao rzPrjcontrChgGuarDao;
	//合同变更担保方子表Dao
	private RzPrjcontrChgGuarBDao rzPrjcontrChgGuarBDao;
	//合同保险主表Dao
	private RzPrjcontrInsDao rzPrjcontrInsDao;
	//合同保险租赁物Dao
	private RzPrjcontrInsEqptDao rzPrjcontrInsEqptDao;
	//合同变更保险主表Dao
	private RzPrjcontrChgInsDao rzPrjcontrChgInsDao;
	//合同变更租赁物Dao
	private RzPrjcontrChgInsEqptDao rzPrjcontrChgInsEqptDao;

	@Autowired
	private PrimaryKeyUtil primaryKeyUtil;
	//批量主键生成策略
	@Autowired
	private PrimaryKeyIdWorker primaryKeyIdWorker;

	/**
	 * 添加
	 * @param rzPrjcontrChg
	 * @return
	 */
	@Override
	public void insertRzPrjcontrChg(RzPrjcontrChg rzPrjcontrChg){
		String pk = primaryKeyUtil.getPrimaryKey();
		rzPrjcontrChg.setTs(DateUtil.getTs());
		rzPrjcontrChg.setDr(0);
		rzPrjcontrChg.setPkPrjcontrChg(pk);
		rzPrjcontrChgDao.insertRzPrjcontrChg(rzPrjcontrChg);
		RzPrjcontrQuery rzPrjcontrQuery = new RzPrjcontrQuery();
		rzPrjcontrQuery.setPkPrjcontr(rzPrjcontrChg.getPkPrjcontr());
		RzPrjcontr rzPrjcontr = rzPrjcontrDao.getRzPrjcontrById(rzPrjcontrQuery);
		//插入供应商和采购合同
		this.insertRzPrjcontrChgSupplierAndPur(rzPrjcontr, rzPrjcontrChg);
		//插入租赁物信息
		this.insertRzPrjcontrChgEqpt(rzPrjcontr, rzPrjcontrChg);
		//插入承租人
		this.insertRzPrjcontrChgLessee(rzPrjcontr, rzPrjcontrChg);
		//插入担保方信息
		this.insertPrjcontrGuarAndGuarB(rzPrjcontr, rzPrjcontrChg);
		//插入保险信息
		this.insertRzPrjcontrInsAndEqpt(rzPrjcontr, rzPrjcontrChg);
	}
	
	/**
	 * 批量添加
	 * @param List<rzPrjcontrChg>
	 * @return
	 */
	public void insertBatchRzPrjcontrChg(List<RzPrjcontrChg> rzPrjcontrChgList){
		if(rzPrjcontrChgList != null){
			for(int i=0;i<rzPrjcontrChgList.size();i++){
				rzPrjcontrChgList.get(i).setTs(DateUtil.getTs());
				rzPrjcontrChgList.get(i).setDr(0);
				String pk = primaryKeyUtil.getPrimaryKey();
				rzPrjcontrChgList.get(i).setPkPrjcontrChg(pk);
			}
			rzPrjcontrChgDao.insertBatchRzPrjcontrChg(rzPrjcontrChgList);
		}
	}

	/**
	 * 根据原合同主键查询原合同供应商
	 * @author: zhengjm5
	 * @Date: 2016-12-15 18:35:47
	 * @param oldRzPrjcontr
	 * @return
	 */
	private List<RzPrjcontrSupplier> queryRzPrjcontrSupplier(RzPrjcontr oldRzPrjcontr){
		String pkPrjcontr = oldRzPrjcontr.getPkPrjcontr();
		RzPrjcontrSupplierQuery rzPrjcontrSupplierQuery = new RzPrjcontrSupplierQuery();
		rzPrjcontrSupplierQuery.setPkPrjcontr(pkPrjcontr);
		//查询合同供应商
		List<RzPrjcontrSupplier> rzPrjcontrSupplierList = rzPrjcontrSupplierDao.getRzPrjcontrSupplierAll(rzPrjcontrSupplierQuery);
		return rzPrjcontrSupplierList;
	}

	/**
	 * 根据原合同查询原合同供应商的采购合同
	 * @author: zhengjm5
	 * @Date: 2016-12-15 18:50:32
	 * @param oldRzPrjcontr
	 * @return
	 */
	private List<RzPrjcontrPur> queryRzPrjcontrPur(RzPrjcontr oldRzPrjcontr){
		String pkPrjcontr = oldRzPrjcontr.getPkPrjcontr();
		RzPrjcontrPurQuery rzPrjcontrPurQuery = new RzPrjcontrPurQuery();
		rzPrjcontrPurQuery.setPkPrjcontr(pkPrjcontr);
		//查询采购合同
		List<RzPrjcontrPur> rzPrjcontrPurList = rzPrjcontrPurDao.getRzPrjcontrPurAll(rzPrjcontrPurQuery);
		return rzPrjcontrPurList;
	}

	/**
	 * 插入合同变更供应商和采购合同
	 * @author: zhengjm5
	 * @Date: 2016-12-28 13:40:29
	 * @param rzPrjcontr
	 * @param rzPrjcontrChg
	 */
	private void insertRzPrjcontrChgSupplierAndPur(RzPrjcontr rzPrjcontr, RzPrjcontrChg rzPrjcontrChg){
		//查询原合同的供应商信息
		List<RzPrjcontrSupplier> rzPrjcontrSupplierList = this.queryRzPrjcontrSupplier(rzPrjcontr);
		if(ToolUtils.isEmptyCollection(rzPrjcontrSupplierList)){
			return;
		}
		//查询原合同的供应商采购合同信息
		List<RzPrjcontrPur> rzPrjcontrPurList = this.queryRzPrjcontrPur(rzPrjcontr);
		//Map<"合同供应商主键"， List<采购合同>>
		Map<String, List<RzPrjcontrPur>> rzPrjcontrPurMap = new HashMap<String, List<RzPrjcontrPur>>();
		if(ToolUtils.isNotEmptyCollection(rzPrjcontrPurList)){
			for(RzPrjcontrPur rzPrjcontrPur : rzPrjcontrPurList){
				String pkPrjcontrSupplier = rzPrjcontrPur.getPkPrjcontrSupplier();
				if(rzPrjcontrPurMap.get(pkPrjcontrSupplier) != null){
					List<RzPrjcontrPur> values = rzPrjcontrPurMap.get(pkPrjcontrSupplier);
					values.add(rzPrjcontrPur);
				}else{
					List<RzPrjcontrPur> values = new ArrayList<RzPrjcontrPur>();
					values.add(rzPrjcontrPur);
					rzPrjcontrPurMap.put(pkPrjcontrSupplier, values);
				}
			}
		}
		//合同变更供应商
		List<RzPrjcontrChgSupplier> rzPrjcontrChgSupplierList = new ArrayList<RzPrjcontrChgSupplier>();
		//合同变更供应商采购合同
		List<RzPrjcontrChgPur> rzPrjcontrChgPurList = new ArrayList<RzPrjcontrChgPur>();
		for(RzPrjcontrSupplier rzPrjcontrSupplier : rzPrjcontrSupplierList){
			RzPrjcontrChgSupplier rzPrjcontrChgSupplier = new RzPrjcontrChgSupplier();
			try {
				BeanUtils.copyProperties(rzPrjcontrChgSupplier, rzPrjcontrSupplier);
				//合同变更主键
				rzPrjcontrChgSupplier.setPkPrjcontrChg(rzPrjcontrChg.getPkPrjcontrChg());
				//合同变更供应商表主键
				rzPrjcontrChgSupplier.setPkPrjcontrChgSupplier(primaryKeyIdWorker.getPrimaryKey());
			}catch (Exception ex){
				ex.printStackTrace();
				continue;
			}
			rzPrjcontrChgSupplierList.add(rzPrjcontrChgSupplier);
			if(ToolUtils.isNotEmptyCollection(rzPrjcontrPurMap.get(rzPrjcontrSupplier.getPkPrjcontrSupplier()))){
				List<RzPrjcontrPur> valueList = rzPrjcontrPurMap.get(rzPrjcontrSupplier.getPkPrjcontrSupplier());
				for(RzPrjcontrPur rzPrjcontrPur : valueList){
					RzPrjcontrChgPur rzPrjcontrChgPur = new RzPrjcontrChgPur();
					try {
						BeanUtils.copyProperties(rzPrjcontrChgPur, rzPrjcontrPur);
						//合同变更主键
						rzPrjcontrChgPur.setPkPrjcontrChg(rzPrjcontrChg.getPkPrjcontrChg());
						//合同变更供应商主键
						rzPrjcontrChgPur.setPkPrjcontrChgSupplier(rzPrjcontrChgSupplier.getPkPrjcontrChgSupplier());
						//合同变更供应商采购合同主键
						rzPrjcontrChgPur.setPkPrjcontrChgPur(primaryKeyIdWorker.getPrimaryKey());
					}catch (Exception ex){
						ex.printStackTrace();
						continue;
					}
					rzPrjcontrChgPurList.add(rzPrjcontrChgPur);
				}
			}
			if(!rzPrjcontrChgSupplierList.isEmpty()){
				rzPrjcontrChgSupplierDao.insertBatchRzPrjcontrChgSupplier(rzPrjcontrChgSupplierList);
			}
			if(!rzPrjcontrChgPurList.isEmpty()){
				rzPrjcontrChgPurDao.insertBatchRzPrjcontrChgPur(rzPrjcontrChgPurList);
			}
		}
	}

	/**
	 * 查询合同租赁物信息
	 * @author: zhengjm5
	 * @Date: 2016-12-28 14:04:49
	 * @param rzPrjcontr
	 * @return
	 */
	private List<RzPrjcontrEqpt> queryRzPrjcontrEqpt(RzPrjcontr rzPrjcontr){
		String pkPrjcontr = rzPrjcontr.getPkPrjcontr();
		RzPrjcontrEqptQuery rzPrjcontrEqptQuery = new RzPrjcontrEqptQuery();
		//合同主键
		rzPrjcontrEqptQuery.setPkPrjcontr(pkPrjcontr);
		List<RzPrjcontrEqpt> rzPrjcontrEqptList = rzPrjcontrEqptDao.getRzPrjcontrEqptAll(rzPrjcontrEqptQuery);
		return rzPrjcontrEqptList;
	}

	/**
	 * 插入合同变更租赁物信息
	 * @author: zhengjm5
	 * @Date: 2016-12-28 14:05:16
	 * @param rzPrjcontr
	 * @param rzPrjcontrChg
	 */
	private void insertRzPrjcontrChgEqpt(RzPrjcontr rzPrjcontr, RzPrjcontrChg rzPrjcontrChg){
		//查询合同租赁物信息
		List<RzPrjcontrEqpt> rzPrjcontrEqptList = this.queryRzPrjcontrEqpt(rzPrjcontr);
		if(ToolUtils.isEmptyCollection(rzPrjcontrEqptList)){
			return;
		}
		List<RzPrjcontrChgEqpt> rzPrjcontrChgEqptList = new ArrayList<RzPrjcontrChgEqpt>();
		for(RzPrjcontrEqpt rzPrjcontrEqpt : rzPrjcontrEqptList){
			RzPrjcontrChgEqpt rzPrjcontrChgEqpt = new RzPrjcontrChgEqpt();
			try {
				BeanUtils.copyProperties(rzPrjcontrChgEqpt, rzPrjcontrEqpt);
				//合同变更主键
				rzPrjcontrChgEqpt.setPkPrjcontrChg(rzPrjcontrChg.getPkPrjcontrChg());
				//合同租赁物主键
				rzPrjcontrChgEqpt.setPkPrjcontrChgEqpt(primaryKeyIdWorker.getPrimaryKey());
			}catch (Exception ex){
				ex.printStackTrace();
				continue;
			}
			rzPrjcontrChgEqptList.add(rzPrjcontrChgEqpt);
		}
		if(!rzPrjcontrChgEqptList.isEmpty()){
			rzPrjcontrChgEqptDao.insertBatchRzPrjcontrChgEqpt(rzPrjcontrChgEqptList);
		}
	}

	/**
	 * 查询合同承租人
	 * @author: zhengjm5
	 * @Date: 2016-12-28 14:35:51
	 * @param rzPrjcontr
	 * @return
	 */
	private List<RzPrjcontrLessee> queryRzPrjcontrLessee(RzPrjcontr rzPrjcontr){
		String pkPrjcontr = rzPrjcontr.getPkPrjcontr();
		RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery = new RzPrjcontrLesseeQuery();
		//合同主键
		rzPrjcontrLesseeQuery.setPkPrjcontr(pkPrjcontr);
		List<RzPrjcontrLessee> rzPrjcontrLesseeList = rzPrjcontrLesseeDao.getRzPrjcontrLesseeAll(rzPrjcontrLesseeQuery);
		return rzPrjcontrLesseeList;
	}

	/**
	 * 插入合同变更承租人
	 * @author: zhengjm5
	 * @Date: 2016-12-28 14:49:25
	 * @param rzPrjcontr
	 * @param rzPrjcontrChg
	 */
	private void insertRzPrjcontrChgLessee(RzPrjcontr rzPrjcontr, RzPrjcontrChg rzPrjcontrChg){
		//查询合同承租人信息
		List<RzPrjcontrLessee> rzPrjcontrLesseeList = this.queryRzPrjcontrLessee(rzPrjcontr);
		if(ToolUtils.isEmptyCollection(rzPrjcontrLesseeList)){
			return;
		}
		List<RzPrjcontrChgLessee> rzPrjcontrChgLesseeList = new ArrayList<RzPrjcontrChgLessee>();
		for(RzPrjcontrLessee rzPrjcontrLessee : rzPrjcontrLesseeList){
			RzPrjcontrChgLessee rzPrjcontrChgLessee = new RzPrjcontrChgLessee();
			try {
				BeanUtils.copyProperties(rzPrjcontrChgLessee, rzPrjcontrLessee);
				//合同变更主键
				rzPrjcontrChgLessee.setPkPrjcontrChg(rzPrjcontrChg.getPkPrjcontrChg());
				//合同变更承租人主键
				rzPrjcontrChgLessee.setPkPrjcontrChgLessee(primaryKeyIdWorker.getPrimaryKey());
			}catch (Exception ex){
				ex.printStackTrace();
				continue;
			}
			rzPrjcontrChgLesseeList.add(rzPrjcontrChgLessee);
		}
		if(!rzPrjcontrChgLesseeList.isEmpty()){
			rzPrjcontrChgLesseeDao.insertBatchRzPrjcontrChgLessee(rzPrjcontrChgLesseeList);
		}
	}

	/**
	 * 查询合同担保方主表
	 * @author: zhengjm5
	 * @Date: 2016-12-28 14:56:38
	 * @param rzPrjcontr
	 * @return
	 */
	private List<RzPrjcontrGuar> queryRzPrjcontrGuar(RzPrjcontr rzPrjcontr){
		String pkPrjcontr = rzPrjcontr.getPkPrjcontr();
		RzPrjcontrGuarQuery rzPrjcontrGuarQuery = new RzPrjcontrGuarQuery();
		//合同主键
		rzPrjcontrGuarQuery.setPkPrjcontr(pkPrjcontr);
		List<RzPrjcontrGuar> rzPrjcontrGuarList = rzPrjcontrGuarDao.getRzPrjcontrGuarAll(rzPrjcontrGuarQuery);
		return rzPrjcontrGuarList;
	}

	/**
	 * 查询合同担保方子表
	 * @author: zhengjm5
	 * @Date: 2016-12-28 14:58:39
	 * @param rzPrjcontr
	 * @return
	 */
	private List<RzPrjcontrGuarB> queryRzPrjcontrGuarB(RzPrjcontr rzPrjcontr){
		String pkPrjcontr = rzPrjcontr.getPkPrjcontr();
		RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery = new RzPrjcontrGuarBQuery();
		//合同主键
		rzPrjcontrGuarBQuery.setPkPrjcontr(pkPrjcontr);
		List<RzPrjcontrGuarB> rzPrjcontrGuarBList = rzPrjcontrGuarBDao.getRzPrjcontrGuarBAll(rzPrjcontrGuarBQuery);
		return rzPrjcontrGuarBList;
	}

	/**
	 * 插入合同变更担保方信息
	 * @author: zhengjm5
	 * @Date: 2016-12-28 15:38:49
	 * @param rzPrjcontr
	 * @param rzPrjcontrChg
	 */
	private void insertPrjcontrGuarAndGuarB(RzPrjcontr rzPrjcontr, RzPrjcontrChg rzPrjcontrChg){
		//合同担保方主表
		List<RzPrjcontrGuar> rzPrjcontrGuarList = this.queryRzPrjcontrGuar(rzPrjcontr);
		if(ToolUtils.isEmptyCollection(rzPrjcontrGuarList)){
			return;
		}
		//合同担保方子表
		List<RzPrjcontrGuarB> rzPrjcontrGuarBList = this.queryRzPrjcontrGuarB(rzPrjcontr);
		//Map<"担保方主表主键", List<子表>>
		Map<String, List<RzPrjcontrGuarB>> rzPrjcontrGuarBMap = new HashMap<String, List<RzPrjcontrGuarB>>();
		if(ToolUtils.isNotEmptyCollection(rzPrjcontrGuarBList)){
			for(RzPrjcontrGuarB rzPrjcontrGuarB : rzPrjcontrGuarBList){
				String pkPrjcontrGuar = rzPrjcontrGuarB.getPkPrjcontrGuar();
				if(ToolUtils.isNotEmptyCollection(rzPrjcontrGuarBMap.get(pkPrjcontrGuar))){
					List<RzPrjcontrGuarB> values = rzPrjcontrGuarBMap.get(pkPrjcontrGuar);
					values.add(rzPrjcontrGuarB);
				}else{
					List<RzPrjcontrGuarB> values = new ArrayList<RzPrjcontrGuarB>();
					values.add(rzPrjcontrGuarB);
					rzPrjcontrGuarBMap.put(pkPrjcontrGuar, values);
				}
			}
		}
		//合同变更担保方主表
		List<RzPrjcontrChgGuar> rzPrjcontrChgGuarList = new ArrayList<RzPrjcontrChgGuar>();
		//合同变更担保方子表
		List<RzPrjcontrChgGuarB> rzPrjcontrChgGuarBList = new ArrayList<RzPrjcontrChgGuarB>();
		for(RzPrjcontrGuar rzPrjcontrGuar : rzPrjcontrGuarList){
			RzPrjcontrChgGuar rzPrjcontrChgGuar = new RzPrjcontrChgGuar();
			try {
				BeanUtils.copyProperties(rzPrjcontrChgGuar, rzPrjcontrGuar);
				//合同变更主键
				rzPrjcontrChgGuar.setPkPrjcontrChg(rzPrjcontrChg.getPkPrjcontrChg());
				//合同担保方主表主键
				rzPrjcontrChgGuar.setPkPrjcontrChgGuar(primaryKeyIdWorker.getPrimaryKey());
			}catch (Exception ex){
				ex.printStackTrace();
				continue;
			}
			rzPrjcontrChgGuarList.add(rzPrjcontrChgGuar);
			if(ToolUtils.isNotEmptyCollection(rzPrjcontrGuarBMap.get(rzPrjcontrGuar.getPkPrjcontrGuar()))){
				List<RzPrjcontrGuarB> valueList = rzPrjcontrGuarBMap.get(rzPrjcontrGuar.getPkPrjcontrGuar());
				for(RzPrjcontrGuarB rzPrjcontrGuarB : valueList){
					RzPrjcontrChgGuarB rzPrjcontrChgGuarB = new RzPrjcontrChgGuarB();
					try {
						BeanUtils.copyProperties(rzPrjcontrChgGuarB, rzPrjcontrGuarB);
						//合同变更主键
						rzPrjcontrChgGuarB.setPkPrjcontrChg(rzPrjcontrChg.getPkPrjcontrChg());
						//合同变更担保方主表主键
						rzPrjcontrChgGuarB.setPkPrjcontrChgGuar(rzPrjcontrChgGuar.getPkPrjcontrChgGuar());
						//合同变更担保方子表主键
						rzPrjcontrChgGuarB.setPkPrjcontrChgGuarB(primaryKeyIdWorker.getPrimaryKey());
					}catch (Exception ex){
						ex.printStackTrace();
						continue;
					}
					rzPrjcontrChgGuarBList.add(rzPrjcontrChgGuarB);
				}
			}
			//插入合同变更担保方主表
			if(!rzPrjcontrChgGuarList.isEmpty()){
				rzPrjcontrChgGuarDao.insertBatchRzPrjcontrChgGuar(rzPrjcontrChgGuarList);
			}
			//插入合同变更担保方子表
			if(!rzPrjcontrChgGuarBList.isEmpty()){
				rzPrjcontrChgGuarBDao.insertBatchRzPrjcontrChgGuarB(rzPrjcontrChgGuarBList);
			}
		}
	}

	/**
	 * 查询合同保险主表信息
	 * @author: zhengjm5
	 * @Date: 2016-12-28 15:44:33
	 * @param rzPrjcontr
	 * @return
	 */
	private List<RzPrjcontrIns> queryRzPrjcontrIns(RzPrjcontr rzPrjcontr){
		String pkPrjcontr = rzPrjcontr.getPkPrjcontr();
		RzPrjcontrInsQuery rzPrjcontrInsQuery = new RzPrjcontrInsQuery();
		//合同主键
		rzPrjcontrInsQuery.setPkPrjcontr(pkPrjcontr);
		List<RzPrjcontrIns> rzPrjcontrInsList = rzPrjcontrInsDao.getRzPrjcontrInsAll(rzPrjcontrInsQuery);
		return rzPrjcontrInsList;
	}

	/**
	 * 查询合同保险租赁物信息
	 * @author: zhengjm5
	 * @Date: 2016-12-28 15:45:50
	 * @param rzPrjcontr
	 * @return
	 */
	private List<RzPrjcontrInsEqpt> queryRzPrjcontrInsEqpt(RzPrjcontr rzPrjcontr){
		String pkPrjcontr = rzPrjcontr.getPkPrjcontr();
		RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery = new RzPrjcontrInsEqptQuery();
		//合同主键
		rzPrjcontrInsEqptQuery.setPkPrjcontr(pkPrjcontr);
		List<RzPrjcontrInsEqpt> rzPrjcontrInsEqptList = rzPrjcontrInsEqptDao.getRzPrjcontrInsEqptAll(rzPrjcontrInsEqptQuery);
		return rzPrjcontrInsEqptList;
	}

	/**
	 * 插入合同变更保险信息
	 * @author: zhengjm5
	 * @Date: 2016-12-28 16:02:37
	 * @param rzPrjcontr
	 * @param rzPrjcontrChg
	 */
	private void insertRzPrjcontrInsAndEqpt(RzPrjcontr rzPrjcontr, RzPrjcontrChg rzPrjcontrChg){
		//查询合同保险主表信息
		List<RzPrjcontrIns> rzPrjcontrInsList = this.queryRzPrjcontrIns(rzPrjcontr);
		if(ToolUtils.isEmptyCollection(rzPrjcontrInsList)){
			return;
		}
		//查询合同保险租赁物信息
		List<RzPrjcontrInsEqpt> rzPrjcontrInsEqptList = this.queryRzPrjcontrInsEqpt(rzPrjcontr);
		//Map<"保险主表主键", List<租赁物>>
		Map<String, List<RzPrjcontrInsEqpt>> rzPrjcontrInsEqptMap = new HashMap<String, List<RzPrjcontrInsEqpt>>();
		if(ToolUtils.isNotEmptyCollection(rzPrjcontrInsEqptList)){
			for(RzPrjcontrInsEqpt rzPrjcontrInsEqpt : rzPrjcontrInsEqptList){
				String pkPrjcontrIns = rzPrjcontrInsEqpt.getPkPrjcontrIns();
				if(rzPrjcontrInsEqptMap.get(pkPrjcontrIns) != null){
					List<RzPrjcontrInsEqpt> value = rzPrjcontrInsEqptMap.get(pkPrjcontrIns);
					value.add(rzPrjcontrInsEqpt);
				}else{
					List<RzPrjcontrInsEqpt> value = new ArrayList<RzPrjcontrInsEqpt>();
					value.add(rzPrjcontrInsEqpt);
					rzPrjcontrInsEqptMap.put(pkPrjcontrIns, value);
				}
			}
		}
		//合同变更保险主表信息
		List<RzPrjcontrChgIns> rzPrjcontrChgInsList = new ArrayList<RzPrjcontrChgIns>();
		//合同变更保险租赁物信息
		List<RzPrjcontrChgInsEqpt> rzPrjcontrChgInsEqptList = new ArrayList<RzPrjcontrChgInsEqpt>();
		for(RzPrjcontrIns rzPrjcontrIns : rzPrjcontrInsList){
			RzPrjcontrChgIns rzPrjcontrChgIns = new RzPrjcontrChgIns();
			try {
				BeanUtils.copyProperties(rzPrjcontrChgIns, rzPrjcontrIns);
				//合同变更主键
				rzPrjcontrChgIns.setPkPrjcontrChg(rzPrjcontrChg.getPkPrjcontrChg());
				//合同变更保险主表主键
				rzPrjcontrChgIns.setPkPrjcontrChgIns(primaryKeyIdWorker.getPrimaryKey());
			}catch (Exception ex){
				ex.printStackTrace();
				continue;
			}
			rzPrjcontrChgInsList.add(rzPrjcontrChgIns);
			if(ToolUtils.isNotEmptyCollection(rzPrjcontrInsEqptMap.get(rzPrjcontrIns.getPkPrjcontrIns()))){
				List<RzPrjcontrInsEqpt> valueList = rzPrjcontrInsEqptMap.get(rzPrjcontrIns.getPkPrjcontrIns());
				for(RzPrjcontrInsEqpt rzPrjcontrInsEqpt : valueList){
					RzPrjcontrChgInsEqpt rzPrjcontrChgInsEqpt = new RzPrjcontrChgInsEqpt();
					try {
						BeanUtils.copyProperties(rzPrjcontrChgInsEqpt, rzPrjcontrInsEqpt);
						//合同变更主键
						rzPrjcontrChgInsEqpt.setPkPrjcontrChg(rzPrjcontrChg.getPkPrjcontrChg());
						//合同变更保险主表主键
						rzPrjcontrChgInsEqpt.setPkPrjcontrChgIns(rzPrjcontrChgIns.getPkPrjcontrChgIns());
						//合同保险租赁物子表主键
						rzPrjcontrChgInsEqpt.setPkPrjcontrChgInsEqpt(primaryKeyIdWorker.getPrimaryKey());
					}catch (Exception ex){
						ex.printStackTrace();
						continue;
					}
					rzPrjcontrChgInsEqptList.add(rzPrjcontrChgInsEqpt);
				}
			}
		}
		if(!rzPrjcontrChgInsList.isEmpty()){
			rzPrjcontrChgInsDao.insertBatchRzPrjcontrChgIns(rzPrjcontrChgInsList);
		}
		if(!rzPrjcontrChgInsEqptList.isEmpty()){
			rzPrjcontrChgInsEqptDao.insertBatchRzPrjcontrChgInsEqpt(rzPrjcontrChgInsEqptList);
		}
	}
	/**
	 * 根据Id删除 (真正删除数据库数据)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgById(RzPrjcontrChgQuery rzPrjcontrChgQuery){
		return rzPrjcontrChgDao.deleteRzPrjcontrChgById(rzPrjcontrChgQuery);	
	}
	
	/**
	 * 根据条件删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	@Override
	public boolean deleteRzPrjcontrChgByCondition(RzPrjcontrChgQuery rzPrjcontrChgQuery){
		return rzPrjcontrChgDao.deleteRzPrjcontrChgByCondition(rzPrjcontrChgQuery);	
	}
	
	/**
	 * 根据id批量删除 (真正删除数据库数据)
	 * @param rzPrjcontrChgQuery
	 * @return
	 */	
	@Override
	public Result deleteRzPrjcontrChgByBatchId(RzPrjcontrChgQuery rzPrjcontrChgQuery) {
		Result result = new Result(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrChgQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			boolean flat = rzPrjcontrChgDao.deleteRzPrjcontrChgByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 根据Id逻辑删除 (修改数据库数据为删除状态)
	 * @param id
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjcontrChgById(RzPrjcontrChgQuery rzPrjcontrChgQuery){
		return rzPrjcontrChgDao.logicDeleteRzPrjcontrChgById(rzPrjcontrChgQuery);	
	}
	
	/**
	 * 根据条件逻辑删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	@Override
	public boolean logicDeleteRzPrjcontrChgByCondition(RzPrjcontrChgQuery rzPrjcontrChgQuery){
		return rzPrjcontrChgDao.logicDeleteRzPrjcontrChgByCondition(rzPrjcontrChgQuery);	
	}
	
	/**
	 * 根据id逻辑批量删除 (修改数据库数据为删除状态)
	 * @param rzPrjcontrChgQuery
	 * @return
	 */	
	@Override
	public Result logicDeleteRzPrjcontrChgByBatchId(RzPrjcontrChgQuery rzPrjcontrChgQuery) {
		Result result = new Result();
		result.setSuccess(false);
		try {
			Map<String, Object> data = new Hashtable<String, Object>();
			//TODO 如果是多主键修要修改，如果不是删除就可以
			String [] batchIdArr = rzPrjcontrChgQuery.getBatchId().split(",");
			data.put("batchId1",batchIdArr);
			data.put("batchId2",batchIdArr);
			boolean flat = rzPrjcontrChgDao.logicDeleteRzPrjcontrChgByBatchId(data);	
			result.setSuccess(flat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 添加或修改
	 * @param rzPrjcontrChg
	 * @return
	 */	
	@Override
	public Result insertOrUpdate(RzPrjcontrChg rzPrjcontrChg) {
		//设置调用失败
		Result result = new Result(false);
		try {
			if(rzPrjcontrChg!=null){
				if(StringUtil.isNotEmpty(rzPrjcontrChg.getPkPrjcontrChg())){
					updateRzPrjcontrChgById(rzPrjcontrChg);
				}else{
					insertRzPrjcontrChg(rzPrjcontrChg);
				}
				//如果没有异常设置成功
				result.setSuccess(true);
			} else {
				result.setErrorMessage("数据对象不能为空");
			}
			//设置service返回的对象，可以是多个。 result.addDefaultModel("key", obj)
			result.addDefaultModel(rzPrjcontrChg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据Id修改
	 * @param rzPrjcontrChg
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgById(RzPrjcontrChg rzPrjcontrChg){
		return rzPrjcontrChgDao.updateRzPrjcontrChgById(rzPrjcontrChg);
	}
	
	/**
	 * 根据条件修改
	 * @param data
	 * @return
	 */
	@Override
	public boolean updateRzPrjcontrChgByCondition(RzPrjcontrChgQuery record,RzPrjcontrChgQuery parameter){
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("record",record);
		data.put("parameter",parameter);
		return rzPrjcontrChgDao.updateRzPrjcontrChgByCondition(data);
	}
	
	/**
	 * 根据Id批量修改
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	public Result updateRzPrjcontrChgByBatchId(List<RzPrjcontrChg> rzPrjcontrChgList){
		Result result = new Result(false);
		try {
			boolean flag = rzPrjcontrChgDao.updateRzPrjcontrChgByBatchId(rzPrjcontrChgList);
			result.setSuccess(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id查询
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	@Override
	public RzPrjcontrChg getRzPrjcontrChgById(RzPrjcontrChgQuery rzPrjcontrChgQuery){
		return rzPrjcontrChgDao.getRzPrjcontrChgById(rzPrjcontrChgQuery);
	}
	
	/**
	 * 查询所有
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	@Override
	public List<RzPrjcontrChg> getRzPrjcontrChgAll(RzPrjcontrChgQuery rzPrjcontrChgQuery){
		return rzPrjcontrChgDao.getRzPrjcontrChgAll(rzPrjcontrChgQuery);
	}
	
	/**
	 * 分页查询
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	@Override
	public GridResult<RzPrjcontrChg> getRzPrjcontrChgByPage(RzPrjcontrChgQuery rzPrjcontrChgQuery){
		//如果排序的字段是空或者空字符串
		if(rzPrjcontrChgQuery!=null&&StringUtils.isBlank(rzPrjcontrChgQuery.getSort())){
			rzPrjcontrChgQuery.setSort("pk_prjcontr_chg");
			rzPrjcontrChgQuery.setOrder("desc");;
		}
		int total = rzPrjcontrChgDao.getRzPrjcontrChgByPageCount(rzPrjcontrChgQuery);
		PaginatedList<RzPrjcontrChg> rzPrjcontrChgPageList = new MysqlPaginatedArrayList<RzPrjcontrChg>(rzPrjcontrChgQuery,total);
		List<RzPrjcontrChg> rzPrjcontrChgList = rzPrjcontrChgDao.getRzPrjcontrChgByPage(rzPrjcontrChgQuery);
		rzPrjcontrChgPageList.addAll(rzPrjcontrChgList);
		GridResult<RzPrjcontrChg> result = new GridResult<RzPrjcontrChg>(rzPrjcontrChgPageList);
		return result;
	}
	
	/**
	 * 分页查询查询总数
	 * @param rzPrjcontrChgQuery
	 * @return
	 */
	@Override
	public int getRzPrjcontrChgByPageCount(RzPrjcontrChgQuery rzPrjcontrChgQuery){
		return rzPrjcontrChgDao.getRzPrjcontrChgByPageCount(rzPrjcontrChgQuery);
	}

	public void setRzPrjcontrChgDao(RzPrjcontrChgDao  rzPrjcontrChgDao){
		this.rzPrjcontrChgDao = rzPrjcontrChgDao;
	}

	public void setRzPrjcontrSupplierDao(RzPrjcontrSupplierDao rzPrjcontrSupplierDao) {
		this.rzPrjcontrSupplierDao = rzPrjcontrSupplierDao;
	}

	public void setRzPrjcontrPurDao(RzPrjcontrPurDao rzPrjcontrPurDao) {
		this.rzPrjcontrPurDao = rzPrjcontrPurDao;
	}

	public void setRzPrjcontrChgSupplierDao(RzPrjcontrChgSupplierDao rzPrjcontrChgSupplierDao) {
		this.rzPrjcontrChgSupplierDao = rzPrjcontrChgSupplierDao;
	}

	public void setRzPrjcontrChgPurDao(RzPrjcontrChgPurDao rzPrjcontrChgPurDao) {
		this.rzPrjcontrChgPurDao = rzPrjcontrChgPurDao;
	}

	public void setRzPrjcontrDao(RzPrjcontrDao rzPrjcontrDao) {
		this.rzPrjcontrDao = rzPrjcontrDao;
	}

	public void setRzPrjcontrEqptDao(RzPrjcontrEqptDao rzPrjcontrEqptDao) {
		this.rzPrjcontrEqptDao = rzPrjcontrEqptDao;
	}

	public void setRzPrjcontrChgEqptDao(RzPrjcontrChgEqptDao rzPrjcontrChgEqptDao) {
		this.rzPrjcontrChgEqptDao = rzPrjcontrChgEqptDao;
	}

	public void setRzPrjcontrLesseeDao(RzPrjcontrLesseeDao rzPrjcontrLesseeDao) {
		this.rzPrjcontrLesseeDao = rzPrjcontrLesseeDao;
	}

	public void setRzPrjcontrChgLesseeDao(RzPrjcontrChgLesseeDao rzPrjcontrChgLesseeDao) {
		this.rzPrjcontrChgLesseeDao = rzPrjcontrChgLesseeDao;
	}

	public void setRzPrjcontrGuarDao(RzPrjcontrGuarDao rzPrjcontrGuarDao) {
		this.rzPrjcontrGuarDao = rzPrjcontrGuarDao;
	}

	public void setRzPrjcontrGuarBDao(RzPrjcontrGuarBDao rzPrjcontrGuarBDao) {
		this.rzPrjcontrGuarBDao = rzPrjcontrGuarBDao;
	}

	public void setRzPrjcontrChgGuarDao(RzPrjcontrChgGuarDao rzPrjcontrChgGuarDao) {
		this.rzPrjcontrChgGuarDao = rzPrjcontrChgGuarDao;
	}

	public void setRzPrjcontrChgGuarBDao(RzPrjcontrChgGuarBDao rzPrjcontrChgGuarBDao) {
		this.rzPrjcontrChgGuarBDao = rzPrjcontrChgGuarBDao;
	}

	public void setRzPrjcontrInsDao(RzPrjcontrInsDao rzPrjcontrInsDao) {
		this.rzPrjcontrInsDao = rzPrjcontrInsDao;
	}

	public void setRzPrjcontrInsEqptDao(RzPrjcontrInsEqptDao rzPrjcontrInsEqptDao) {
		this.rzPrjcontrInsEqptDao = rzPrjcontrInsEqptDao;
	}

	public void setRzPrjcontrChgInsDao(RzPrjcontrChgInsDao rzPrjcontrChgInsDao) {
		this.rzPrjcontrChgInsDao = rzPrjcontrChgInsDao;
	}

	public void setRzPrjcontrChgInsEqptDao(RzPrjcontrChgInsEqptDao rzPrjcontrChgInsEqptDao) {
		this.rzPrjcontrChgInsEqptDao = rzPrjcontrChgInsEqptDao;
	}
}