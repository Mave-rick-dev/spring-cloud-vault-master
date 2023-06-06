package com.maverick.dao.impl;

import com.maverick.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {

	@Autowired
	DataSource dataSource;

	@Value("${example.password}")
	private String password;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public List<Employee> getAllEmployees() {
		String sql = "SELECT * FROM employee";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		logger.info("password fetched using field injection: "+password);

		List<Employee> result = new ArrayList<Employee>();
		for (Map<String, Object> row : rows) {
			Employee emp = new Employee();
			emp.setEmpId((String) row.get("empId"));
			emp.setEmpName((String) row.get("empName"));
			result.add(emp);
		}

		return result;
	}
}