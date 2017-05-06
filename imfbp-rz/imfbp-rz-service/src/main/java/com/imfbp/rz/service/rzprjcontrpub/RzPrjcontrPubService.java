package com.imfbp.rz.service.rzprjcontrpub;

import com.imfbp.rz.domain.rzpmtplan.RzPmtPlan;
import com.imfbp.rz.domain.rzprjcontr.RzPrjcontr;
import com.imfbp.rz.domain.rzprjcontreqptmngr.RzPrjcontrEqptmngr;

import java.util.List;

/**
 * 合同公共服务
 * @author zhengjm5
 * @date 2016/12/15 16:22
 */
public interface RzPrjcontrPubService {

    /**
     * 通过已存在的合同创建新合同(包括创建合同的所有子表，并提高合同版本)
     * @author: zhengjm5
     * @Date: 2016-12-15 16:25:25
     * @param oldRzPrjcontr
     * @return
     */
    public RzPrjcontr createRzprjcontrByOld(RzPrjcontr oldRzPrjcontr);

    /**
     * 通过合同信息创建租赁物管理信息
     * @author: zhengjm5
     * @Date: 2016-12-19 11:23:13
     * @param rzPrjcontr
     * @return
     */
    public List<RzPrjcontrEqptmngr> createRzPrjcontrEqptmngr(RzPrjcontr rzPrjcontr);

    /**
     * 在合同审批结束后创建收付计划表
     * @author: zhengjm5
     * @Date: 2016-12-23 10:51:48
     * @param rzPrjcontr
     * @return
     */
    public RzPmtPlan createRzPmtPlan(RzPrjcontr rzPrjcontr);
    
    
    /**
     * 在合同审批结束后创建台账数据
     * @author: zhengjm5
     * @Date: 2016-12-23 10:51:48
     * @param rzPrjcontr
     * @return
     */
    public void  createRzPrjcontrTally(RzPrjcontr rzPrjcontr);
}
