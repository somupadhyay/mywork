/**
 * 
 */
package org.sb.jersey.poc.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.sb.jersey.poc.domain.Employee;
import org.springframework.stereotype.Service;

/**
 * @author Somnath.Upadhyay
 *
 */
@Service
public class EmployeeService {

	private static Map<String, Employee> EMP_DB = new HashMap<>();

	public Collection<Employee> getAllEmployees() {
		return EMP_DB.values();
	}

	public Employee getEmployee(String eid) {
		return EMP_DB.get(eid);
	}

	public String addEmployee(Employee employee) {
		String eid = UUID.randomUUID().toString();
		employee.setEid(eid);
		EMP_DB.put(eid, employee);
		return eid;
	}

	public void updateEmployee(String eid, Employee employee) {
		employee.setEid(eid);
		EMP_DB.put(eid, employee);
	}

	public void deleteEmployee(String eid) {
		EMP_DB.remove(eid);
	}

}
