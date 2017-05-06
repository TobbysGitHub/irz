package com.imfbp.rz.dao.ref;

import java.util.List;

public interface RefDao {

	public List<Object> getRefDataValueBySql(String sql);

	public Integer getRefDataCountbySql(String sql);
}
