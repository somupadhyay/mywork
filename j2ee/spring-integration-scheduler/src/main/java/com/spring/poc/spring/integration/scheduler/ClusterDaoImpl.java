package com.spring.poc.spring.integration.scheduler;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class ClusterDaoImpl extends JdbcDaoSupport implements ClusterDao {

	public boolean checkClusterStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	public void upsertClusterStatus() {
		// TODO Auto-generated method stub

	}

	public List<Map<String, Object>> ListAllClusterTableEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> getClusterStatusByCName(String cName) {
		String sql = "";
		getJdbcTemplate().queryForList(sql);
		return getJdbcTemplate().queryForList(sql);
	}

}
