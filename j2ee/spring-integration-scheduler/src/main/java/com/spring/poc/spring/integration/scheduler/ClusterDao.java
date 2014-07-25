/**
 * 
 */
package com.spring.poc.spring.integration.scheduler;

import java.util.List;
import java.util.Map;

/**
 * @author Somnath.Upadhyay
 *
 */
public interface ClusterDao {

	public boolean checkClusterStatus();
	
	public void upsertClusterStatus();
	
	public List<Map<String, Object>> ListAllClusterTableEntry();
	
	public List<Map<String, Object>> getClusterStatusByCName(String cName);
}
