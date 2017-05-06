package com.imfbp.rz.service.rzprjcontrpub.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imfbp.rz.dao.rzpmtplan.RzPmtPlanDao;
import com.imfbp.rz.dao.rzpmtplancf.RzPmtPlanCfDao;
import com.imfbp.rz.dao.rzpmtplanlease.RzPmtPlanLeaseDao;
import com.imfbp.rz.dao.rzprjcontr.RzPrjcontrDao;
import com.imfbp.rz.dao.rzprjcontrcf.RzPrjcontrCfDao;
import com.imfbp.rz.dao.rzprjcontreqpt.RzPrjcontrEqptDao;
import com.imfbp.rz.dao.rzprjcontreqptmngr.RzPrjcontrEqptmngrDao;
import com.imfbp.rz.dao.rzprjcontrguar.RzPrjcontrGuarDao;
import com.imfbp.rz.dao.rzprjcontrguarb.RzPrjcontrGuarBDao;
import com.imfbp.rz.dao.rzprjcontrins.RzPrjcontrInsDao;
import com.imfbp.rz.dao.rzprjcontrinseqpt.RzPrjcontrInsEqptDao;
import com.imfbp.rz.dao.rzprjcontrlease.RzPrjcontrLeaseDao;
import com.imfbp.rz.dao.rzprjcontrlessee.RzPrjcontrLesseeDao;
import com.imfbp.rz.dao.rzprjcontrpur.RzPrjcontrPurDao;
import com.imfbp.rz.dao.rzprjcontrsupplier.RzPrjcontrSupplierDao;
import com.imfbp.rz.domain.rzcontrtally.RzContrTally;
import com.imfbp.rz.domain.rzpmtplan.RzPmtPlan;
import com.imfbp.rz.domain.rzpmtplancf.RzPmtPlanCf;
import com.imfbp.rz.domain.rzpmtplanlease.RzPmtPlanLease;
import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjcontrcf.RzPrjcontrCf;
import com.imfbp.rz.domain.rzprjcontrcf.query.RzPrjcontrCfQuery;
import com.imfbp.rz.domain.rzprjcontreqpt.RzPrjcontrEqpt;
import com.imfbp.rz.domain.rzprjcontreqpt.query.RzPrjcontrEqptQuery;
import com.imfbp.rz.domain.rzprjcontreqptmngr.RzPrjcontrEqptmngr;
import com.imfbp.rz.domain.rzprjcontrguar.RzPrjcontrGuar;
import com.imfbp.rz.domain.rzprjcontrguar.query.RzPrjcontrGuarQuery;
import com.imfbp.rz.domain.rzprjcontrguarb.RzPrjcontrGuarB;
import com.imfbp.rz.domain.rzprjcontrguarb.query.RzPrjcontrGuarBQuery;
import com.imfbp.rz.domain.rzprjcontrins.RzPrjcontrIns;
import com.imfbp.rz.domain.rzprjcontrins.query.RzPrjcontrInsQuery;
import com.imfbp.rz.domain.rzprjcontrinseqpt.RzPrjcontrInsEqpt;
import com.imfbp.rz.domain.rzprjcontrinseqpt.query.RzPrjcontrInsEqptQuery;
import com.imfbp.rz.domain.rzprjcontrlease.RzPrjcontrLease;
import com.imfbp.rz.domain.rzprjcontrlease.query.RzPrjcontrLeaseQuery;
import com.imfbp.rz.domain.rzprjcontrlessee.RzPrjcontrLessee;
import com.imfbp.rz.domain.rzprjcontrlessee.query.RzPrjcontrLesseeQuery;
import com.imfbp.rz.domain.rzprjcontrpur.RzPrjcontrPur;
import com.imfbp.rz.domain.rzprjcontrpur.query.RzPrjcontrPurQuery;
import com.imfbp.rz.domain.rzprjcontrsupplier.RzPrjcontrSupplier;
import com.imfbp.rz.domain.rzprjcontrsupplier.query.RzPrjcontrSupplierQuery;
import com.imfbp.rz.service.rzcontrtally.RzContrTallyService;
import com.imfbp.rz.service.rzprjcontrpub.RzPrjcontrPubService;
import com.imfbp.rz.util.DateUtil;
import com.imfbp.rz.util.PrimaryKeyIdWorker;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.utils.primarykey.PrimaryKeyUtil;

/**
 * 新增合同服务实现类
 * @author zhengjm5
 * @date 2016/12/15 16:31
 */

@Component("rzPrjcontrPubService")
public class RzPrjcontrPubServiceImpl implements RzPrjcontrPubService{

    //合同Dao
    private RzPrjcontrDao rzPrjcontrDao;
    //合同供应商Dao
    private RzPrjcontrSupplierDao rzPrjcontrSupplierDao;
    //合同供应商采购合同Dao
    private RzPrjcontrPurDao rzPrjcontrPurDao;
    //合同租赁物Dao
    private RzPrjcontrEqptDao rzPrjcontrEqptDao;
    //合同承租人Dao
    private RzPrjcontrLesseeDao rzPrjcontrLesseeDao;
    //合同现金流计划Dao
    private RzPrjcontrCfDao rzPrjcontrCfDao;
    //合同担保方主表Dao
    private RzPrjcontrGuarDao rzPrjcontrGuarDao;
    //合同担保方子表Dao
    private RzPrjcontrGuarBDao rzPrjcontrGuarBDao;
    //合同保险主表Dao
    private RzPrjcontrInsDao rzPrjcontrInsDao;
    //合同保险租赁物Dao
    private RzPrjcontrInsEqptDao rzPrjcontrInsEqptDao;
    //合同租金计划Dao
    private RzPrjcontrLeaseDao rzPrjcontrLeaseDao;

    //租赁物管理Dao
    private RzPrjcontrEqptmngrDao rzPrjcontrEqptmngrDao;

    //收付计划Dao
    private RzPmtPlanDao rzPmtPlanDao;
    //收付计划现金流计划Dao
    private RzPmtPlanCfDao rzPmtPlanCfDao;
    //收付计划租金计划Dao
    private RzPmtPlanLeaseDao rzPmtPlanLeaseDao;
    
    @Autowired
    private RzContrTallyService rzContrTallyService;

    @Autowired
    private PrimaryKeyUtil primaryKeyUtil;
    //批量主键生成策略
    @Autowired
    private PrimaryKeyIdWorker primaryKeyIdWorker;

    @Override
    public RzPrjcontr createRzprjcontrByOld(RzPrjcontr oldRzPrjcontr) {
        RzPrjcontr newRzPrjcontr = new RzPrjcontr();
        try {
            BeanUtils.copyProperties(newRzPrjcontr, oldRzPrjcontr);
            //合同主键
            newRzPrjcontr.setPkPrjcontr(primaryKeyUtil.getPrimaryKey());
            //合同版本+0.1
            newRzPrjcontr.setContrVer(new BigDecimal(oldRzPrjcontr.getContrVer()).add(new BigDecimal("0.1")).toString());
            newRzPrjcontr.setTs(DateUtil.getTs());
            newRzPrjcontr.setFlowinstanceid("");
            //设置为最新版本
            newRzPrjcontr.setIsNew("Y");
            //插入合同信息
            rzPrjcontrDao.insertRzPrjcontr(newRzPrjcontr);
            //插入新合同供应商
            this.insertRzPrjcontrSupplierAndPur(oldRzPrjcontr, newRzPrjcontr);
            //插入新合同租赁物
            this.insertRzPrjcontrEqpt(oldRzPrjcontr, newRzPrjcontr);
            //插入新合同承租人
            this.insertRzPrjcontrLessee(oldRzPrjcontr, newRzPrjcontr);
            //插入新合同现金流计划
            this.insertRzPrjcontrCf(oldRzPrjcontr, newRzPrjcontr);
            //插入新合同担保方信息
            this.insertPrjcontrGuarAndGuarB(oldRzPrjcontr, newRzPrjcontr);
            //插入新合同保险信息
            this.insertRzPrjcontrInsAndEqpt(oldRzPrjcontr, newRzPrjcontr);
            //插入新合同租金计划信息
            this.insertRzPrjcontrLease(oldRzPrjcontr, newRzPrjcontr);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return newRzPrjcontr;
    }

    /**
     * 根据原合同主键查询原合同供应商
     * @author: zhengjm5
     * @Date: 2016-12-15 18:35:47
     * @param oldRzPrjcontr
     * @return
     */
    private List<RzPrjcontrSupplier> queryRzPrjcontrSupplierByOld(RzPrjcontr oldRzPrjcontr){
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
    private List<RzPrjcontrPur> queryRzPrjcontrPurByOld(RzPrjcontr oldRzPrjcontr){
        String pkPrjcontr = oldRzPrjcontr.getPkPrjcontr();
        RzPrjcontrPurQuery rzPrjcontrPurQuery = new RzPrjcontrPurQuery();
        rzPrjcontrPurQuery.setPkPrjcontr(pkPrjcontr);
        //查询采购合同
        List<RzPrjcontrPur> rzPrjcontrPurList = rzPrjcontrPurDao.getRzPrjcontrPurAll(rzPrjcontrPurQuery);
        return rzPrjcontrPurList;
    }

    /**
     * 插入新合同的供应商和采购合同
     * @author: zhengjm5
     * @Date: 2016-12-16 10:50:38
     * @param oldRzPrjcontr
     * @param newRzPrjcontr
     */
    private void insertRzPrjcontrSupplierAndPur(RzPrjcontr oldRzPrjcontr, RzPrjcontr newRzPrjcontr){
        //查询原合同的供应商信息
        List<RzPrjcontrSupplier> oldRzPrjcontrSupplierList = this.queryRzPrjcontrSupplierByOld(oldRzPrjcontr);
        if(ToolUtils.isEmptyCollection(oldRzPrjcontrSupplierList)){
            return;
        }
        //查询原合同的供应商采购合同信息
        List<RzPrjcontrPur> oldRzPrjcontrPurList = this.queryRzPrjcontrPurByOld(oldRzPrjcontr);
        //Map<"合同供应商主键"， List<采购合同>>
        Map<String, List<RzPrjcontrPur>> rzPrjcontrPurMap = new HashMap<String, List<RzPrjcontrPur>>();
        if(ToolUtils.isNotEmptyCollection(oldRzPrjcontrPurList)){
            for(RzPrjcontrPur oldRzPrjcontrPur : oldRzPrjcontrPurList){
                String oldPkPrjcontrSupplier = oldRzPrjcontrPur.getPkPrjcontrSupplier();
                if(rzPrjcontrPurMap.get(oldPkPrjcontrSupplier) != null){
                    List<RzPrjcontrPur> values = rzPrjcontrPurMap.get(oldPkPrjcontrSupplier);
                    values.add(oldRzPrjcontrPur);
                }else{
                    List<RzPrjcontrPur> values = new ArrayList<RzPrjcontrPur>();
                    values.add(oldRzPrjcontrPur);
                    rzPrjcontrPurMap.put(oldPkPrjcontrSupplier, values);
                }
            }
        }
        //新合同供应商
        List<RzPrjcontrSupplier> newRzPrjcontrSupplierList = new ArrayList<RzPrjcontrSupplier>();
        //新合同供应商采购合同
        List<RzPrjcontrPur> newRzPrjcontrPurList = new ArrayList<RzPrjcontrPur>();
        for(RzPrjcontrSupplier oldRzPrjcontrSupplier : oldRzPrjcontrSupplierList){
            RzPrjcontrSupplier newRzPrjcontrSupplier = new RzPrjcontrSupplier();
            try {
                BeanUtils.copyProperties(newRzPrjcontrSupplier, oldRzPrjcontrSupplier);
                //合同主键
                newRzPrjcontrSupplier.setPkPrjcontr(newRzPrjcontr.getPkPrjcontr());
                //合同供应商表主键
                newRzPrjcontrSupplier.setPkPrjcontrSupplier(primaryKeyIdWorker.getPrimaryKey());
            }catch (Exception ex){
                ex.printStackTrace();
                continue;
            }
            newRzPrjcontrSupplierList.add(newRzPrjcontrSupplier);
            if(ToolUtils.isNotEmptyCollection(rzPrjcontrPurMap.get(oldRzPrjcontrSupplier.getPkPrjcontrSupplier()))){
                List<RzPrjcontrPur> valueList = rzPrjcontrPurMap.get(oldRzPrjcontrSupplier.getPkPrjcontrSupplier());
                for(RzPrjcontrPur oldRzPrjcontrPur : valueList){
                    RzPrjcontrPur newRzPrjcontrPur = new RzPrjcontrPur();
                    try {
                        BeanUtils.copyProperties(newRzPrjcontrPur, oldRzPrjcontrPur);
                        //合同主键
                        newRzPrjcontrPur.setPkPrjcontr(newRzPrjcontr.getPkPrjcontr());
                        //合同供应商主键
                        newRzPrjcontrPur.setPkPrjcontrSupplier(newRzPrjcontrSupplier.getPkPrjcontrSupplier());
                        //合同供应商采购合同主键
                        newRzPrjcontrPur.setPkPrjcontrPur(primaryKeyIdWorker.getPrimaryKey());
                    }catch (Exception ex){
                        ex.printStackTrace();
                        continue;
                    }
                    newRzPrjcontrPurList.add(newRzPrjcontrPur);
                }
            }
        }
        //插入新合同供应商
        if(!newRzPrjcontrSupplierList.isEmpty()){
            rzPrjcontrSupplierDao.insertBatchRzPrjcontrSupplier(newRzPrjcontrSupplierList);
        }
        //插入新合同供应商采购合同
        if(!newRzPrjcontrPurList.isEmpty()){
            rzPrjcontrPurDao.insertBatchRzPrjcontrPur(newRzPrjcontrPurList);
        }
    }

    /**
     * 根据原合同查询租赁物信息
     * @author: zhengjm5
     * @Date: 2016-12-16 10:34:31
     * @param oldRzPrjcontr
     * @return
     */
    private List<RzPrjcontrEqpt> queryRzPrjcontrEqptByOld(RzPrjcontr oldRzPrjcontr){
        String pkPrjcontr = oldRzPrjcontr.getPkPrjcontr();
        RzPrjcontrEqptQuery rzPrjcontrEqptQuery = new RzPrjcontrEqptQuery();
        //合同主键
        rzPrjcontrEqptQuery.setPkPrjcontr(pkPrjcontr);
        List<RzPrjcontrEqpt> rzPrjcontrEqptList = rzPrjcontrEqptDao.getRzPrjcontrEqptAll(rzPrjcontrEqptQuery);
        return rzPrjcontrEqptList;
    }

    /**
     * 插入新合同租赁物
     * @author: zhengjm5
     * @Date: 2016-12-16 10:59:08
     * @param oldRzPrjcontr
     * @param newRzPrjcontr
     */
    private void insertRzPrjcontrEqpt(RzPrjcontr oldRzPrjcontr, RzPrjcontr newRzPrjcontr){
        //查询原合同的租赁物信息
        List<RzPrjcontrEqpt> oldRzPrjcontrEqptList = this.queryRzPrjcontrEqptByOld(oldRzPrjcontr);
        if(ToolUtils.isEmptyCollection(oldRzPrjcontrEqptList)){
            return;
        }
        List<RzPrjcontrEqpt> newRzPrjcontrEqptList = new ArrayList<RzPrjcontrEqpt>();
        for(RzPrjcontrEqpt oldRzPrjcontrEqpt : oldRzPrjcontrEqptList){
            RzPrjcontrEqpt newRzPrjcontrEqpt = new RzPrjcontrEqpt();
            try {
                BeanUtils.copyProperties(newRzPrjcontrEqpt, oldRzPrjcontrEqpt);
                //合同主键
                newRzPrjcontrEqpt.setPkPrjcontr(newRzPrjcontr.getPkPrjcontr());
                //合同租赁物主键
                newRzPrjcontrEqpt.setPkPrjcontrEqpt(primaryKeyIdWorker.getPrimaryKey());
            }catch (Exception ex){
                ex.printStackTrace();
                continue;
            }
            newRzPrjcontrEqptList.add(newRzPrjcontrEqpt);
        }
        if(!newRzPrjcontrEqptList.isEmpty()){
            rzPrjcontrEqptDao.insertBatchRzPrjcontrEqpt(newRzPrjcontrEqptList);
        }
    }

    /**
     * 根根据原合同查询承租人信息
     * @author: zhengjm5
     * @Date: 2016-12-16 11:03:31
     * @param oldRzPrjcontr
     * @return
     */
    private List<RzPrjcontrLessee> queryRzPrjcontrLesseeByOld(RzPrjcontr oldRzPrjcontr){
        String pkPrjcontr = oldRzPrjcontr.getPkPrjcontr();
        RzPrjcontrLesseeQuery rzPrjcontrLesseeQuery = new RzPrjcontrLesseeQuery();
        //合同主键
        rzPrjcontrLesseeQuery.setPkPrjcontr(pkPrjcontr);
        List<RzPrjcontrLessee> rzPrjcontrLesseeList = rzPrjcontrLesseeDao.getRzPrjcontrLesseeAll(rzPrjcontrLesseeQuery);
        return rzPrjcontrLesseeList;
    }

    /**
     * 插入新合同承租人
     * @author: zhengjm5
     * @Date: 2016-12-16 11:10:45
     * @param oldRzPrjcontr
     * @param newRzPrjcontr
     */
    private void insertRzPrjcontrLessee(RzPrjcontr oldRzPrjcontr, RzPrjcontr newRzPrjcontr){
        //查询原合同承租人信息
        List<RzPrjcontrLessee> oldRzPrjcontrLesseeList = this.queryRzPrjcontrLesseeByOld(oldRzPrjcontr);
        if(ToolUtils.isEmptyCollection(oldRzPrjcontrLesseeList)){
            return;
        }
        List<RzPrjcontrLessee> newRzPrjcontrLesseeList = new ArrayList<RzPrjcontrLessee>();
        for(RzPrjcontrLessee oldRzPrjcontrLessee : oldRzPrjcontrLesseeList){
            RzPrjcontrLessee newRzPrjcontrLessee = new RzPrjcontrLessee();
            try {
                BeanUtils.copyProperties(newRzPrjcontrLessee, oldRzPrjcontrLessee);
                //合同主键
                newRzPrjcontrLessee.setPkPrjcontr(newRzPrjcontr.getPkPrjcontr());
                //合同承租人主键
                newRzPrjcontrLessee.setPkPrjcontrLessee(primaryKeyIdWorker.getPrimaryKey());
            }catch (Exception ex){
                ex.printStackTrace();
                continue;
            }
            newRzPrjcontrLesseeList.add(newRzPrjcontrLessee);
        }
        if(!newRzPrjcontrLesseeList.isEmpty()){
            rzPrjcontrLesseeDao.insertBatchRzPrjcontrLessee(newRzPrjcontrLesseeList);
        }
    }

    /**
     * 查询原合同现金流计划
     * @author: zhengjm5
     * @Date: 2016-12-16 11:21:16
     * @param oldRzPrjcontr
     * @return
     */
    private List<RzPrjcontrCf> queryRzPrjcontrCf(RzPrjcontr oldRzPrjcontr){
        String pkPrjcontr = oldRzPrjcontr.getPkPrjcontr();
        RzPrjcontrCfQuery rzPrjcontrCfQuery = new RzPrjcontrCfQuery();
        //合同主键
        rzPrjcontrCfQuery.setPkPrjcontr(pkPrjcontr);
        List<RzPrjcontrCf> rzPrjcontrCfList = rzPrjcontrCfDao.getRzPrjcontrCfAll(rzPrjcontrCfQuery);
        return rzPrjcontrCfList;
    }

    /**
     * 插入新合同现金流计划
     * @author: zhengjm5
     * @Date: 2016-12-16 11:27:01
     * @param oldRzPrjcontr
     * @param newRzPrjcontr
     */
    private void insertRzPrjcontrCf(RzPrjcontr oldRzPrjcontr, RzPrjcontr newRzPrjcontr){
        //查询原合同现金流计划
        List<RzPrjcontrCf> oldRzPrjcontrCfList = this.queryRzPrjcontrCf(oldRzPrjcontr);
        if(ToolUtils.isEmptyCollection(oldRzPrjcontrCfList)){
            return;
        }
        List<RzPrjcontrCf> newRzPrjcontrCfList = new ArrayList<RzPrjcontrCf>();
        for(RzPrjcontrCf oldRzPrjcontrCf : oldRzPrjcontrCfList){
            RzPrjcontrCf newRzPrjcontrCf = new RzPrjcontrCf();
            try {
                BeanUtils.copyProperties(newRzPrjcontrCf, oldRzPrjcontrCf);
                //合同主键
                newRzPrjcontrCf.setPkPrjcontr(newRzPrjcontr.getPkPrjcontr());
                //合同现金流计划主键
                newRzPrjcontrCf.setPkPrjcontrCf(primaryKeyIdWorker.getPrimaryKey());
            }catch (Exception ex){
                ex.printStackTrace();
                continue;
            }
            newRzPrjcontrCfList.add(newRzPrjcontrCf);
        }
        if(!newRzPrjcontrCfList.isEmpty()){
            rzPrjcontrCfDao.insertBatchRzPrjcontrCf(newRzPrjcontrCfList);
        }
    }

    /**
     * 查询原合同担保方主表信息
     * @author: zhengjm5
     * @Date: 2016-12-16 13:27:35
     * @param oldRzPrjcontr
     * @return
     */
    private List<RzPrjcontrGuar> queryRzPrjcontrGuarOld(RzPrjcontr oldRzPrjcontr){
        String pkPrjcontr = oldRzPrjcontr.getPkPrjcontr();
        RzPrjcontrGuarQuery rzPrjcontrGuarQuery = new RzPrjcontrGuarQuery();
        //合同主键
        rzPrjcontrGuarQuery.setPkPrjcontr(pkPrjcontr);
        List<RzPrjcontrGuar> rzPrjcontrGuarList = rzPrjcontrGuarDao.getRzPrjcontrGuarAll(rzPrjcontrGuarQuery);
        return rzPrjcontrGuarList;
    }

    /**
     * 查询原合同担保方子表信息
     * @author: zhengjm5
     * @Date: 2016-12-16 13:48:24
     * @param oldRzPrjcontr
     * @return
     */
    private List<RzPrjcontrGuarB> queryRzPrjcontrGuarBOld(RzPrjcontr oldRzPrjcontr){
        String pkPrjcontr = oldRzPrjcontr.getPkPrjcontr();
        RzPrjcontrGuarBQuery rzPrjcontrGuarBQuery = new RzPrjcontrGuarBQuery();
        //合同主键
        rzPrjcontrGuarBQuery.setPkPrjcontr(pkPrjcontr);
        List<RzPrjcontrGuarB> rzPrjcontrGuarBList = rzPrjcontrGuarBDao.getRzPrjcontrGuarBAll(rzPrjcontrGuarBQuery);
        return rzPrjcontrGuarBList;
    }

    /**
     * 插入新合同担保方信息
     * @author: zhengjm5
     * @Date: 2016-12-16 14:06:43
     * @param oldRzPrjcontr
     * @param newRzPrjcontr
     */
    private void insertPrjcontrGuarAndGuarB(RzPrjcontr oldRzPrjcontr, RzPrjcontr newRzPrjcontr){
        List<RzPrjcontrGuar> oldRzPrjcontrGuarList = this.queryRzPrjcontrGuarOld(oldRzPrjcontr);
        if(ToolUtils.isEmptyCollection(oldRzPrjcontrGuarList)){
            return;
        }
        List<RzPrjcontrGuarB> oldRzPrjcontrGuarBList = this.queryRzPrjcontrGuarBOld(oldRzPrjcontr);
        //Map<"担保方主表主键", List<子表>>
        Map<String, List<RzPrjcontrGuarB>> rzPrjcontrGuarBMap = new HashMap<String, List<RzPrjcontrGuarB>>();
        if(ToolUtils.isNotEmptyCollection(oldRzPrjcontrGuarBList)){
            for(RzPrjcontrGuarB oldRzPrjcontrGuarB : oldRzPrjcontrGuarBList){
                String pkPrjcontrGuar = oldRzPrjcontrGuarB.getPkPrjcontrGuar();
                if(ToolUtils.isNotEmptyCollection(rzPrjcontrGuarBMap.get(pkPrjcontrGuar))){
                    List<RzPrjcontrGuarB> values = rzPrjcontrGuarBMap.get(pkPrjcontrGuar);
                    values.add(oldRzPrjcontrGuarB);
                }else{
                    List<RzPrjcontrGuarB> values = new ArrayList<RzPrjcontrGuarB>();
                    values.add(oldRzPrjcontrGuarB);
                    rzPrjcontrGuarBMap.put(pkPrjcontrGuar, values);
                }
            }
        }
        //新合同担保方主表
        List<RzPrjcontrGuar> newRzPrjcontrGuarList = new ArrayList<RzPrjcontrGuar>();
        //新合同担保方子表
        List<RzPrjcontrGuarB> newRzPrjcontrGuarBList = new ArrayList<RzPrjcontrGuarB>();
        for(RzPrjcontrGuar oldRzPrjcontrGuar : oldRzPrjcontrGuarList){
            RzPrjcontrGuar newRzPrjcontrGuar = new RzPrjcontrGuar();
            try {
                BeanUtils.copyProperties(newRzPrjcontrGuar, oldRzPrjcontrGuar);
                //合同主键
                newRzPrjcontrGuar.setPkPrjcontr(newRzPrjcontr.getPkPrjcontr());
                //合同担保方主表主键
                newRzPrjcontrGuar.setPkPrjcontrGuar(primaryKeyIdWorker.getPrimaryKey());
            }catch (Exception ex){
                ex.printStackTrace();
                continue;
            }
            newRzPrjcontrGuarList.add(newRzPrjcontrGuar);
            if(ToolUtils.isNotEmptyCollection(rzPrjcontrGuarBMap.get(oldRzPrjcontrGuar.getPkPrjcontrGuar()))){
                List<RzPrjcontrGuarB> valueList = rzPrjcontrGuarBMap.get(oldRzPrjcontrGuar.getPkPrjcontrGuar());
                for(RzPrjcontrGuarB oldRzPrjcontrGuarB : valueList){
                    RzPrjcontrGuarB newRzPrjcontrGuarB = new RzPrjcontrGuarB();
                    try {
                        BeanUtils.copyProperties(newRzPrjcontrGuarB, oldRzPrjcontrGuarB);
                        //合同主键
                        newRzPrjcontrGuarB.setPkPrjcontr(newRzPrjcontr.getPkPrjcontr());
                        //合同担保方主表主键
                        newRzPrjcontrGuarB.setPkPrjcontrGuar(newRzPrjcontrGuar.getPkPrjcontrGuar());
                        //合同担保方子表主键
                        newRzPrjcontrGuarB.setPkPrjcontrGuarB(primaryKeyIdWorker.getPrimaryKey());
                    }catch (Exception ex){
                        ex.printStackTrace();
                        continue;
                    }
                    newRzPrjcontrGuarBList.add(newRzPrjcontrGuarB);
                }
            }
        }
        //插入新合同担保方主表
        if(!newRzPrjcontrGuarList.isEmpty()){
            rzPrjcontrGuarDao.insertBatchRzPrjcontrGuar(newRzPrjcontrGuarList);
        }
        //插入新合同担保方子表
        if(!newRzPrjcontrGuarBList.isEmpty()){
            rzPrjcontrGuarBDao.insertBatchRzPrjcontrGuarB(newRzPrjcontrGuarBList);
        }
    }

    /**
     * 查询原合同保险主表信息
     * @author: zhengjm5
     * @Date: 2016-12-16 14:23:47
     * @param oldRzPrjcontr
     * @return
     */
    private List<RzPrjcontrIns> queryRzPrjcontrInsOld(RzPrjcontr oldRzPrjcontr){
        String pkPrjcontr = oldRzPrjcontr.getPkPrjcontr();
        RzPrjcontrInsQuery rzPrjcontrInsQuery = new RzPrjcontrInsQuery();
        //合同主键
        rzPrjcontrInsQuery.setPkPrjcontr(pkPrjcontr);
        List<RzPrjcontrIns> rzPrjcontrInsList = rzPrjcontrInsDao.getRzPrjcontrInsAll(rzPrjcontrInsQuery);
        return rzPrjcontrInsList;
    }

    /**
     * 查询原合同保险租赁物信息
     * @author: zhengjm5
     * @Date: 2016-12-16 14:39:01
     * @param oldRzPrjcontr
     * @return
     */
    private List<RzPrjcontrInsEqpt> queryRzPrjcontrInsEqptOld(RzPrjcontr oldRzPrjcontr){
        String pkPrjcontr = oldRzPrjcontr.getPkPrjcontr();
        RzPrjcontrInsEqptQuery rzPrjcontrInsEqptQuery = new RzPrjcontrInsEqptQuery();
        //合同主键
        rzPrjcontrInsEqptQuery.setPkPrjcontr(pkPrjcontr);
        List<RzPrjcontrInsEqpt> rzPrjcontrInsEqptList = rzPrjcontrInsEqptDao.getRzPrjcontrInsEqptAll(rzPrjcontrInsEqptQuery);
        return rzPrjcontrInsEqptList;
    }

    /**
     * 插入新合同的保险信息
     * @author: zhengjm5
     * @Date: 2016-12-16 15:23:34
     * @param oldRzPrjcontr
     * @param newRzPrjcontr
     */
    private void insertRzPrjcontrInsAndEqpt(RzPrjcontr oldRzPrjcontr, RzPrjcontr newRzPrjcontr){
        //查询合同保险主表信息
        List<RzPrjcontrIns> oldRzPrjcontrInsList = this.queryRzPrjcontrInsOld(oldRzPrjcontr);
        if(ToolUtils.isEmptyCollection(oldRzPrjcontrInsList)){
            return;
        }
        //查询合同保险租赁物信息
        List<RzPrjcontrInsEqpt> oldRzPrjcontrInsEqptList = this.queryRzPrjcontrInsEqptOld(oldRzPrjcontr);
        //Map<"保险主表主键", List<租赁物>>
        Map<String, List<RzPrjcontrInsEqpt>> rzPrjcontrInsEqptMap = new HashMap<String, List<RzPrjcontrInsEqpt>>();
        if(ToolUtils.isNotEmptyCollection(oldRzPrjcontrInsEqptList)){
            for(RzPrjcontrInsEqpt rzPrjcontrInsEqpt : oldRzPrjcontrInsEqptList){
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
        //新合同保险主表
        List<RzPrjcontrIns> newRzPrjcontrInsList = new ArrayList<RzPrjcontrIns>();
        //新合同保险租赁物子表
        List<RzPrjcontrInsEqpt> newRzPrjcontrInsEqptList = new ArrayList<RzPrjcontrInsEqpt>();
        for(RzPrjcontrIns oldRzPrjcontrIns : oldRzPrjcontrInsList){
            RzPrjcontrIns newRzPrjcontrIns = new RzPrjcontrIns();
            try {
                BeanUtils.copyProperties(newRzPrjcontrIns, oldRzPrjcontrIns);
                //合同主键
                newRzPrjcontrIns.setPkPrjcontr(newRzPrjcontr.getPkPrjcontr());
                //合同保险主表主键
                newRzPrjcontrIns.setPkPrjcontrIns(primaryKeyIdWorker.getPrimaryKey());
            }catch (Exception ex){
                ex.printStackTrace();
                continue;
            }
            newRzPrjcontrInsList.add(newRzPrjcontrIns);
            if(ToolUtils.isNotEmptyCollection(rzPrjcontrInsEqptMap.get(oldRzPrjcontrIns.getPkPrjcontrIns()))){
                List<RzPrjcontrInsEqpt> valueList = rzPrjcontrInsEqptMap.get(oldRzPrjcontrIns.getPkPrjcontrIns());
                for(RzPrjcontrInsEqpt oldRzPrjcontrInsEqpt : valueList){
                    RzPrjcontrInsEqpt newRzPrjcontrInsEqpt = new RzPrjcontrInsEqpt();
                    try {
                        BeanUtils.copyProperties(newRzPrjcontrInsEqpt, oldRzPrjcontrInsEqpt);
                        //合同主键
                        newRzPrjcontrInsEqpt.setPkPrjcontr(newRzPrjcontr.getPkPrjcontr());
                        //合同保险主表主键
                        newRzPrjcontrInsEqpt.setPkPrjcontrIns(newRzPrjcontrIns.getPkPrjcontrIns());
                        //合同保险租赁物子表主键
                        newRzPrjcontrInsEqpt.setPkPrjcontrInsEqpt(primaryKeyIdWorker.getPrimaryKey());
                    }catch (Exception ex){
                        ex.printStackTrace();
                        continue;
                    }
                    newRzPrjcontrInsEqptList.add(newRzPrjcontrInsEqpt);
                }
            }
        }
        if(!newRzPrjcontrInsList.isEmpty()){
            rzPrjcontrInsDao.insertBatchRzPrjcontrIns(newRzPrjcontrInsList);
        }
        if(!newRzPrjcontrInsEqptList.isEmpty()){
            rzPrjcontrInsEqptDao.insertBatchRzPrjcontrInsEqpt(newRzPrjcontrInsEqptList);
        }
    }

    /**
     * 查询原合同的租金计划信息
     * @author: zhengjm5
     * @Date: 2016-12-23 13:34:47
     * @param oldRzPrjcontr
     * @return
     */
    private List<RzPrjcontrLease> queryRzPrjcontrLease(RzPrjcontr oldRzPrjcontr){
        String pkPrjcontr = oldRzPrjcontr.getPkPrjcontr();
        RzPrjcontrLeaseQuery rzPrjcontrLeaseQuery = new RzPrjcontrLeaseQuery();
        //合同主键条件
        rzPrjcontrLeaseQuery.setPkPrjcontr(pkPrjcontr);
        List<RzPrjcontrLease> rzPrjcontrLeaseList = rzPrjcontrLeaseDao.getRzPrjcontrLeaseAll(rzPrjcontrLeaseQuery);
        return rzPrjcontrLeaseList;
    }

    /**
     * 插入新合同的租金计划
     * @author: zhengjm5
     * @Date: 2016-12-23 13:46:50
     * @param oldRzPrjcontr
     * @param newRzPrjcontr
     */
    private void insertRzPrjcontrLease(RzPrjcontr oldRzPrjcontr, RzPrjcontr newRzPrjcontr){
        //查询原合同的租金计划信息
        List<RzPrjcontrLease> oldRzPrjcontrLeaseList = this.queryRzPrjcontrLease(oldRzPrjcontr);
        if(ToolUtils.isEmptyCollection(oldRzPrjcontrLeaseList)){
            return;
        }
        List<RzPrjcontrLease> newRzPrjcontrLeaseList = new ArrayList<RzPrjcontrLease>();
        for(RzPrjcontrLease oldRzPrjcontrLease : oldRzPrjcontrLeaseList){
            RzPrjcontrLease newRzPrjcontrLease = new RzPrjcontrLease();
            try {
                BeanUtils.copyProperties(newRzPrjcontrLease, oldRzPrjcontrLease);
                //合同主键
                newRzPrjcontrLease.setPkPrjcontr(newRzPrjcontr.getPkPrjcontr());
                //合同租金计划主键
                newRzPrjcontrLease.setPkPricecalRent(primaryKeyIdWorker.getPrimaryKey());
            }catch (Exception ex){
                ex.printStackTrace();
                continue;
            }
            newRzPrjcontrLeaseList.add(newRzPrjcontrLease);
        }
        if(!newRzPrjcontrLeaseList.isEmpty()){
            rzPrjcontrLeaseDao.insertBatchRzPrjcontrLease(newRzPrjcontrLeaseList);
        }
    }

    @Override
    public List<RzPrjcontrEqptmngr> createRzPrjcontrEqptmngr(RzPrjcontr rzPrjcontr) {
        String pkPrjcontr = rzPrjcontr.getPkPrjcontr();
        //根据合同pk查询合同租赁物信息
        RzPrjcontrEqptQuery rzPrjcontrEqptQuery = new RzPrjcontrEqptQuery();
        rzPrjcontrEqptQuery.setPkPrjcontr(pkPrjcontr);
        List<RzPrjcontrEqpt> rzPrjcontrEqptList = rzPrjcontrEqptDao.getRzPrjcontrEqptAll(rzPrjcontrEqptQuery);
        if(ToolUtils.isEmptyCollection(rzPrjcontrEqptList)){
            return null;
        }
        List<RzPrjcontrEqptmngr> rzPrjcontrEqptmngrList = new ArrayList<RzPrjcontrEqptmngr>();
        for(RzPrjcontrEqpt rzPrjcontrEqpt : rzPrjcontrEqptList){
            RzPrjcontrEqptmngr rzPrjcontrEqptmngr = new RzPrjcontrEqptmngr();
            try {
                //填充合同信息
                BeanUtils.copyProperties(rzPrjcontrEqptmngr, rzPrjcontrEqpt);
                //填充租赁物信息
                BeanUtils.copyProperties(rzPrjcontrEqptmngr, rzPrjcontr);
                rzPrjcontrEqptmngr.setPkCustomer(rzPrjcontr.getPkCustomer());
                rzPrjcontrEqptmngr.setPkCustomerSupp(rzPrjcontrEqpt.getPkCustomer());
                //主键
                rzPrjcontrEqptmngr.setPkPrjcontrEqptmngr(primaryKeyIdWorker.getPrimaryKey());
                rzPrjcontrEqptmngr.setTs(DateUtil.getTs());
                rzPrjcontrEqptmngr.setDr(0);
            }catch (Exception ex){
                ex.printStackTrace();
                continue;
            }
            rzPrjcontrEqptmngrList.add(rzPrjcontrEqptmngr);
        }
        if(!rzPrjcontrEqptmngrList.isEmpty()){
            rzPrjcontrEqptmngrDao.insertBatchRzPrjcontrEqptmngr(rzPrjcontrEqptmngrList);
        }
        return rzPrjcontrEqptmngrList;
    }

    @Override
    public RzPmtPlan createRzPmtPlan(RzPrjcontr rzPrjcontr) {
        RzPmtPlan rzPmtPlan = this.createRzPmtPlanByPrjContr(rzPrjcontr);
        try{
            rzPmtPlanDao.insertRzPmtPlan(rzPmtPlan);
            //插入现金流计划
            this.createRzPmtPlanCfList(rzPrjcontr, rzPmtPlan);
            //插入租金计划
            this.createRzPmtPlanLeaseList(rzPrjcontr, rzPmtPlan);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return rzPmtPlan;
    }

    /**
     * 创建收付计划主表
     * @author: zhengjm5
     * @Date: 2016-12-23 13:27:01
     * @param rzPrjcontr
     * @return
     */
    private RzPmtPlan createRzPmtPlanByPrjContr(RzPrjcontr rzPrjcontr){
        RzPmtPlan rzPmtPlan = new RzPmtPlan();
        //主键
        rzPmtPlan.setPkPmtPlan(primaryKeyUtil.getPrimaryKey());
        //合同主键
        rzPmtPlan.setPkPrjcontr(rzPrjcontr.getPkPrjcontr());
        //评审单主键
        rzPmtPlan.setPkPrjreview(rzPrjcontr.getPkPrjreview());
        //立项申请主键
        rzPmtPlan.setPkPrjapply(rzPrjcontr.getPkPrjapply());
        //组织机构
        rzPmtPlan.setPkOrg(rzPrjcontr.getPkOrg());
        //部门
        rzPmtPlan.setPkDept(rzPrjcontr.getPkDept());
        //制单人
        rzPmtPlan.setBillmaker(rzPrjcontr.getBillmaker());
        //制单时间为合同审批时间
        rzPmtPlan.setBilldate(rzPrjcontr.getApprovedate().substring(0,10));
        //审批状态:自由态
        rzPmtPlan.setApprovestatus(0);
        //录入人
        rzPmtPlan.setOperator(rzPrjcontr.getOperator());
        //录入时间
        rzPmtPlan.setOperatordatetime(DateUtil.getTs());
        //dr
        rzPmtPlan.setDr(0);
        //ts
        rzPmtPlan.setTs(DateUtil.getTs());
        return rzPmtPlan;
    }

    /**
     * 插入收付计划现金流信息
     * @author: zhengjm5
     * @Date: 2016-12-23 14:02:44
     * @param rzPrjcontr
     * @param rzPmtPlan
     */
    private void createRzPmtPlanCfList(RzPrjcontr rzPrjcontr, RzPmtPlan rzPmtPlan){
        //查询合同现金流计划
        List<RzPrjcontrCf> rzPrjcontrCfList = this.queryRzPrjcontrCf(rzPrjcontr);
        if(ToolUtils.isEmptyCollection(rzPrjcontrCfList)){
            return;
        }
        List<RzPmtPlanCf> rzPmtPlanCfList = new ArrayList<RzPmtPlanCf>();
        for(RzPrjcontrCf rzPrjcontrCf : rzPrjcontrCfList){
            RzPmtPlanCf rzPmtPlanCf = new RzPmtPlanCf();
            try {
                BeanUtils.copyProperties(rzPmtPlanCf, rzPrjcontrCf);
                //收付计划主键
                rzPmtPlanCf.setPkPmtPlan(rzPmtPlan.getPkPmtPlan());
                //现金流主键
                rzPmtPlanCf.setPkPmtPlanCf(primaryKeyIdWorker.getPrimaryKey());
                //版本
                rzPmtPlanCf.setVer(new Double(1.0));
                //是否核销
                rzPmtPlanCf.setIsCheck("N");
            }catch (Exception ex){
                ex.printStackTrace();
                continue;
            }
            rzPmtPlanCfList.add(rzPmtPlanCf);
        }
        if(!rzPmtPlanCfList.isEmpty()){
            rzPmtPlanCfDao.insertBatchRzPmtPlanCf(rzPmtPlanCfList);
        }
    }

    /**
     * 插入收付计划租金计划信息
     * @author: zhengjm5
     * @Date: 2016-12-23 14:14:05
     * @param rzPrjcontr
     * @param rzPmtPlan
     */
    private void createRzPmtPlanLeaseList(RzPrjcontr rzPrjcontr, RzPmtPlan rzPmtPlan){
        //查询合同租金计划信息
        List<RzPrjcontrLease> rzPrjcontrLeaseList = this.queryRzPrjcontrLease(rzPrjcontr);
        if(ToolUtils.isEmptyCollection(rzPrjcontrLeaseList)){
            return;
        }
        List<RzPmtPlanLease> rzPmtPlanLeaseList = new ArrayList<RzPmtPlanLease>();
        for(RzPrjcontrLease rzPrjcontrLease : rzPrjcontrLeaseList){
            RzPmtPlanLease rzPmtPlanLease = new RzPmtPlanLease();
            try {
                BeanUtils.copyProperties(rzPmtPlanLease, rzPrjcontrLease);
                //收付计划主键
                rzPmtPlanLease.setPkPmtPlan(rzPmtPlan.getPkPmtPlan());
                //租金计划主键
                rzPmtPlanLease.setPkPmtPlanLease(primaryKeyIdWorker.getPrimaryKey());
                //版本
                rzPmtPlanLease.setVer(new Double(1.0));
                //是否核销
                rzPmtPlanLease.setIsCheck("N");
                //租赁利率
                rzPmtPlanLease.setLeaseRate(rzPrjcontrLease.getRentRate());
            }catch (Exception ex){
                ex.printStackTrace();
                continue;
            }
            rzPmtPlanLeaseList.add(rzPmtPlanLease);
        }
        if(!rzPmtPlanLeaseList.isEmpty()){
            rzPmtPlanLeaseDao.insertBatchRzPmtPlanLease(rzPmtPlanLeaseList);
        }
    }

    public void setRzPrjcontrSupplierDao(RzPrjcontrSupplierDao rzPrjcontrSupplierDao) {
        this.rzPrjcontrSupplierDao = rzPrjcontrSupplierDao;
    }

    public void setRzPrjcontrPurDao(RzPrjcontrPurDao rzPrjcontrPurDao) {
        this.rzPrjcontrPurDao = rzPrjcontrPurDao;
    }

    public void setRzPrjcontrEqptDao(RzPrjcontrEqptDao rzPrjcontrEqptDao) {
        this.rzPrjcontrEqptDao = rzPrjcontrEqptDao;
    }

    public void setRzPrjcontrLesseeDao(RzPrjcontrLesseeDao rzPrjcontrLesseeDao) {
        this.rzPrjcontrLesseeDao = rzPrjcontrLesseeDao;
    }

    public void setRzPrjcontrCfDao(RzPrjcontrCfDao rzPrjcontrCfDao) {
        this.rzPrjcontrCfDao = rzPrjcontrCfDao;
    }

    public void setRzPrjcontrDao(RzPrjcontrDao rzPrjcontrDao) {
        this.rzPrjcontrDao = rzPrjcontrDao;
    }

    public void setRzPrjcontrGuarDao(RzPrjcontrGuarDao rzPrjcontrGuarDao) {
        this.rzPrjcontrGuarDao = rzPrjcontrGuarDao;
    }

    public void setRzPrjcontrGuarBDao(RzPrjcontrGuarBDao rzPrjcontrGuarBDao) {
        this.rzPrjcontrGuarBDao = rzPrjcontrGuarBDao;
    }

    public void setRzPrjcontrInsDao(RzPrjcontrInsDao rzPrjcontrInsDao) {
        this.rzPrjcontrInsDao = rzPrjcontrInsDao;
    }

    public void setRzPrjcontrInsEqptDao(RzPrjcontrInsEqptDao rzPrjcontrInsEqptDao) {
        this.rzPrjcontrInsEqptDao = rzPrjcontrInsEqptDao;
    }

    public void setRzPrjcontrEqptmngrDao(RzPrjcontrEqptmngrDao rzPrjcontrEqptmngrDao) {
        this.rzPrjcontrEqptmngrDao = rzPrjcontrEqptmngrDao;
    }

    public void setRzPrjcontrLeaseDao(RzPrjcontrLeaseDao rzPrjcontrLeaseDao) {
        this.rzPrjcontrLeaseDao = rzPrjcontrLeaseDao;
    }

    public void setRzPmtPlanDao(RzPmtPlanDao rzPmtPlanDao) {
        this.rzPmtPlanDao = rzPmtPlanDao;
    }

    public void setRzPmtPlanCfDao(RzPmtPlanCfDao rzPmtPlanCfDao) {
        this.rzPmtPlanCfDao = rzPmtPlanCfDao;
    }

    public void setRzPmtPlanLeaseDao(RzPmtPlanLeaseDao rzPmtPlanLeaseDao) {
        this.rzPmtPlanLeaseDao = rzPmtPlanLeaseDao;
    }

	@Override
	public void createRzPrjcontrTally(RzPrjcontr rzPrjcontr ) {
		if(rzPrjcontr !=  null){
			RzContrTally rzContrTally = new RzContrTally();
			org.springframework.beans.BeanUtils.copyProperties(rzPrjcontr, rzContrTally);
			rzContrTallyService.addRzContrTally(rzContrTally);
		}
		
		
	}
}
