package com.imfbp.rz.controller.pub;

import com.imfbp.rz.pub.IRZConsts;
import com.platform.common.spring.mvc.controller.BaseController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


public class PubBaseContrl extends BaseController {

    /**
     * 将登录组织及用户信息附加到视图中
     *
     * @param mv
     */
    protected void attachLoginInfo(ModelAndView mv) {
        mv.addObject(IRZConsts.ORG_VALUE, getOrgId());
        mv.addObject(IRZConsts.DEPT_VALUE, getDeptId());
        mv.addObject(IRZConsts.USER_VALUE, getUserId());
    }

    /**
     * 将模块值添加在mv中，前台通过"${moduleValue}"获取
     * @author: zhengjm5
     * @Date: 2016-12-21 14:02:59
     * @param mv
     * @param req
     */
    protected void attachModuleValue(ModelAndView mv, HttpServletRequest req){
        String moduleValue = req.getParameter("moduleValue");
        mv.addObject("moduleValue", moduleValue);
    }
}
