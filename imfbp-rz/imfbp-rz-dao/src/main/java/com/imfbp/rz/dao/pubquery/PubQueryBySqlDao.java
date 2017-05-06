package com.imfbp.rz.dao.pubquery;

import java.util.List;
import java.util.Map;

public interface PubQueryBySqlDao {

	public List<Map<String, Object>> getRefDataValueBySql(String sql);
}
