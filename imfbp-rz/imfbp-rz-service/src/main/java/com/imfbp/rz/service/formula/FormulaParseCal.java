package com.imfbp.rz.service.formula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imfbp.brefdata.rpc.reference.domain.RpcDataQuery;
import com.imfbp.brefdata.rpc.reference.service.ReferenceRpcService;
import com.imfbp.rz.dao.pubquery.PubQueryBySqlDao;
import com.imfbp.rz.dao.tmplFormula.TmplFormulaDao;
import com.imfbp.rz.domain.pub.SuperBean;
import com.imfbp.rz.domain.pub.SuperHeadBean;
import com.imfbp.rz.domain.template.BaseSystemInfo;
import com.imfbp.rz.domain.tmpl.TmplFormula;
import com.imfbp.rz.service.ref.impl.DefaultRpcServiceImpl;
import com.imfbp.rz.service.template.impl.TemplateServiceImpl;
import com.imfbp.rz.util.ToolUtils;
import com.platform.common.utils.StringUtil;

import nc.bs.pub.formulaparse.FormulaParse;
import nc.vo.pub.SuperVO;
import net.sf.json.JSONObject;

/*****
 * 
 * 公式解析
 * 
 * @author Administrator
 *
 */
@Component("formulaParseCal")
public class FormulaParseCal {
	

	private final static Logger logger = Logger.getLogger(FormulaParseCal.class);

	@Autowired
	private TmplFormulaDao tmplFormulaDao;
	
	@Autowired
	private PubQueryBySqlDao pubQueryBySqlDao;
	
	@Autowired
	private TemplateServiceImpl templateServiceImpl;

	@Autowired
	private ReferenceRpcService referenceRpcService;// 公共参照接口

	private final static Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");

	public void formulaParse(SuperHeadBean superVO, String nodeCode,BaseSystemInfo baseSystemInfo) {
		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("nodeCode", nodeCode);
		List<TmplFormula> tmplFormulaList = tmplFormulaDao.getTmplFormulaByNodeCode(mapWhere);

		//单据主键
		String billPk = superVO.getPrimaryKey();
//		Object  object  = superVO.getAttribute("");
	
		
		if (ToolUtils.isNotEmptyCollection(tmplFormulaList)) {

			// 将公式模板进行模板分组
			Map<String, List<TmplFormula>> formulaMap = templeGroup(tmplFormulaList);
			
			Map<String, Map<String, Object>> pubMapCache = new HashMap<String, Map<String, Object>>();
			
			
			
			
			for (Map.Entry<String, List<TmplFormula>> entry : formulaMap.entrySet()) {
				
				// 表头指标取数
				Map<String, Object> mainBillDataMap = new HashMap<String, Object>();
				// 表体指标取数
				Map<String, List<? extends SuperBean>> bodyBillDataMap = new HashMap<String, List<? extends SuperBean>>();

				String nodeTemplateId = entry.getKey();
				List<TmplFormula> tmplFormulaListTmp = entry.getValue();
				int size = tmplFormulaListTmp.size();
				
				Map<String, List<String>> itemCodeMap = new HashMap<String, List<String>>();
				
				//子表
				Map<String, Map<String, String>> childMap = new HashMap<String, Map<String, String>>();

				for (int i = 0; i < size; i++) {
					TmplFormula tmplFormula = tmplFormulaListTmp.get(i);
					String formula = tmplFormula.getFormula();
					
					//指标编码
					String itemCode = tmplFormula.getItemCode();
					// 按照指标编码分割，区分主子表
					String[] itemCodeSplit = itemCode.split(".");
					if (itemCodeSplit.length > 1) {
						// 主子表情况 t.a t.b
						//进行子表分组,暂时只支持一个子表场景
						String key = itemCodeSplit[0];
						if (childMap.get(key) == null) {
							Map<String, String> mapChildTmp = new HashMap<String, String>();
							mapChildTmp.put(itemCodeSplit[1], formula);
							childMap.put(key, mapChildTmp);
						} else {
							childMap.get(key).put(itemCodeSplit[1], formula);
						}
						
						
					} else {
						
						if (StringUtil.isNotEmpty(formula)) {
							// 外部公式
							if (formula.indexOf("getPubValue") > -1) {
								mainBillDataMap.put(itemCode,getPubValue(formula, superVO, baseSystemInfo.getTenantId(), pubMapCache));
							} else {
								Matcher matcher = pattern.matcher(formula);
								String newStr = formula;
								mainBillDataMap.put(itemCode, getColValue(newStr, superVO));
							}

						}
					}
				}
				
				if (!childMap.isEmpty()) {
					// t a,b,c
					
					for (Map.Entry<String, Map<String, String>> entryChild : childMap.entrySet()) {
						//key 子节点表编码 val = 公式
						Map<String, String> mapChild = entryChild.getValue();
						//将公式分组
						for (Map.Entry<String, String> mapFormulaChild : mapChild.entrySet()) {
							String formula = mapFormulaChild.getValue();
							Matcher matcher = pattern.matcher(formula);
							String  newStr = null;
							if (matcher.find()) {
								newStr = matcher.group();
							}
							String arr[] = newStr.split(",");
							if (arr.length != 4) {
								logger.error("getColValue(" + formula + ") 公式解析错误，请检查公式配置！");
							} else {
								String  tableName = arr[0];
//								String  
								
							}
							
						}
						
						Object returnVal = null;
						
						
					}

				}
				
				
				try {
					
					templateServiceImpl.createBillDoc(nodeCode, billPk, mainBillDataMap, bodyBillDataMap , nodeTemplateId,
							baseSystemInfo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	protected Object getColValue(String formula,SuperHeadBean superVO) {
		Matcher matcher = pattern.matcher(formula);
		String newStr = formula;
		Object returnVal = null;
		if (matcher.find()) {
			newStr = matcher.group();
		}
		String arr[] = newStr.split(",");
		if (arr.length != 4) {
			logger.error("getColValue(" + formula + ") 公式解析错误，请检查公式配置！" );
		} else {
			String selectCol = ToolUtils.camelCaseName(arr[1].replace(arr[0] + ".", ""));
			returnVal = superVO.getAttribute(selectCol);

			if (returnVal == null) {
				// 去掉表名前缀
				String valText = arr[3].replace(arr[0] + ".", "");
				String attrValWhere = ToolUtils.camelCaseName(valText);
				String sql = "select " + arr[1] + " from " + arr[0] + " as " + arr[0] + " where " + arr[2] + "='"
						+ attrValWhere + "'";
				List<Map<String, Object>> list = pubQueryBySqlDao.getRefDataValueBySql(sql);
				if (ToolUtils.isNotEmptyCollection(list)) {
					returnVal= list.get(0);
				}
			}
		}
		return returnVal;
	}
	
	
	/*******
	 * 
	 * 外部参照公式
	 * 
	 * @param formula
	 * @param superVO
	 * @param tenantId
	 * @param pubMapCache 公式缓存 Map<参照编码, Map<元数据字段, 公式值>>
	 * @return
	 */
	protected Object getPubValue(String formula, SuperHeadBean superVO, String tenantId,
			Map<String, Map<String, Object>> pubMapCache) {
		Object returnVal = null;
		
		Matcher matcher = pattern.matcher(formula);
		String newStr = formula;
		if (matcher.find()) {
			newStr = matcher.group();
		}
		String arr[] = newStr.split(",");
		if (arr.length != 4) {
			logger.error("getPubValue(" + formula + ") 公式解析错误，请检查公式配置！" );
		} else {
			// getPubValue(参照编码,显示字段,查询字段,查询值)
			String pubRefCode = arr[0];

			// 去掉表名前缀
			String pubRefShowName = arr[1].replace(pubRefCode + ".", "");

			// 查询条件字段
			String pubRefWhereCol = arr[2].replace(pubRefCode + ".", "");

			// 查询条件值
			String pubRefWhereColVal = arr[3].replace(pubRefCode + ".", "");
			// 业务单据获取条件值
			String attrRefWhereColVal = null;
			try {
				attrRefWhereColVal = BeanUtils.getProperty(superVO, ToolUtils.camelCaseName(pubRefWhereColVal));
			} catch (Exception e) {
				logger.error("getPubValue(" + formula + ")获取属性值错误：" + ToolUtils.camelCaseName(pubRefWhereColVal) + " " + e.getMessage());
			}

			// 缓存key = 参照编码 + 查询值
			String cacheKey = pubRefCode + attrRefWhereColVal;

			if (pubMapCache.get(cacheKey) != null) {
				returnVal = pubMapCache.get(cacheKey).get(pubRefShowName);
			} else {

				JSONObject json = new JSONObject();
				// RPC条件组装
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("keyword", RpcDataQuery.KeyWord.EQ);
				map.put("value", attrRefWhereColVal);
				json.element(pubRefWhereCol, JSONObject.fromObject(map));

				List<JSONObject> conditions = new ArrayList<JSONObject>();
				conditions.add(json);

				RpcDataQuery query = new RpcDataQuery();
				query.setLimit(1);
				query.setConditions(conditions);
				List<JSONObject> dataList = referenceRpcService.getDataByReferences(tenantId, pubRefCode, query);

				if (ToolUtils.isEmptyCollection(dataList)) {
					logger.error("getPubValue(" + formula + ") 接口没有返回数据，请检查公式配置！");
				} else {
					for (int i = 0; i < dataList.size(); i++) {
						Map<String, Object> cacheValMap = new HashMap<String, Object>();
						Set<String> set = dataList.get(i).keySet();
						Iterator<String> it = set.iterator();
						while (it.hasNext()) {
							// 元数据key
							String key = it.next();
							Object objVal = dataList.get(i).get(key);
							cacheValMap.put(key, objVal);
						}
						// 将查询之后的公式数据放于缓存
						pubMapCache.put(cacheKey, cacheValMap);
					}
					returnVal = pubMapCache.get(cacheKey).get(pubRefShowName);
				}
			}
		}

		return returnVal;
	}
	
	/*****
	 * 将公式模板进行模板分组
	 * 
	 * @param tmplFormulaList
	 * @return
	 */
	protected Map<String, List<TmplFormula>>  templeGroup(List<TmplFormula> tmplFormulaList) {
		Map<String, List<TmplFormula>> formulaMap = new HashMap<String, List<TmplFormula>>();
		for (int i = 0; i < tmplFormulaList.size(); i++) {
			TmplFormula tmplFormula = tmplFormulaList.get(i);
			String templateId = tmplFormula.getTemplateDefId();
			if (formulaMap.get(templateId) == null) {
				List<TmplFormula> tmplFormulaListTmp = new ArrayList<TmplFormula>();
				tmplFormulaListTmp.add(tmplFormula);
				formulaMap.put(templateId, tmplFormulaListTmp);
			} else {
				formulaMap.get(templateId).add(tmplFormula);
			}
		}
		return formulaMap;
	}
	
	public static void main(String args[]){
		FormulaParse f = new FormulaParse();
//		String result = f.getValue();
		String a = "getColValue(rz_prjcontr,rz_prjcontr.pk_prjapply,rz_prjcontr.pk_prjapplypkfield,pkvalue)";
		f.setExpress(a);
		f.addVariable("rz_prjcontr.pk_prjapply", "pk_prjapply");
		
		Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
		Matcher matcher = pattern.matcher(a);
		String newStr = a;
		if (matcher.find()) {
			newStr = matcher.group();
		}
		String arr[] = newStr.split(",");
		
		if (arr.length != 4) {

		} else {
			StringBuffer sql = new StringBuffer("select ");
			String val = arr[3].replace(arr[0] + ".", "");
			String s = "select " + arr[1] + " from " + arr[0] + " as " + arr[0] + " where " + arr[2] + "='" + val
					+ "'";
		}
	
		
		  
	}
	
	
	/** 
     * 使用正则表达式提取中括号中的内容 
     * @param msg 
     * @return  
     */  
    public static List<String> extractMessageByRegular(String msg){  
          
        List<String> list=new ArrayList<String>();  
        Pattern p = Pattern.compile("(\\[[^\\]]*\\])");  
        Matcher m = p.matcher(msg);  
        while(m.find()){  
            list.add(m.group().substring(1, m.group().length()-1));  
        }  
        return list;  
    }  

}
