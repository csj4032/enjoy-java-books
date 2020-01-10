package com.genius.dudm.mapper;

import com.genius.dudm.domain.Department;
import com.genius.dudm.domain.DomainKey;
import com.genius.dudm.domain.Employee;
import com.genius.dudm.domain.EmployeeKey;
import com.genius.dudm.infrastructure.InstancePool;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class EmployeeMapper extends AbstractMapper<Employee> {

	private static final String SELECT_SQL = """
			SELECT
				E.NO AS EMPLOYEE_NO,
				E.NAME AS EMPLOYEE_NAME,
				E.POSITION,
				D.NO AS DEPARTMENT_NO,
				D.NAME AS DEPARTMENT_NAME,
				D.ADDRESS
			FROM
				EMPLOYEE AS E, DEPARTMENT AS D
			WHERE
				E.DEPARTMENT_NO = D.NO
			""";

	@Override
	protected String getFindByKey() {
		return SELECT_SQL + " AND E.NO = ?";
	}

	public List<Employee> findByDepartment(Department department) {
		return find(SELECT_SQL + " AND D.NO = ?", new Object[]{department.getNo()});
	}

	@Override
	public List<Employee> findAll() {
		return find(SELECT_SQL, null);
	}

	protected Employee load(ResultSet resultSet) throws SQLException {
		if(InstancePool.getInstancePool().containsInPool(getKey(resultSet))){
			log.info("Instance Pool");
			return (Employee) InstancePool.getInstancePool().getObjectFromPool(getKey(resultSet));
		}
		Department department = new Department(resultSet.getLong("DEPARTMENT_NO"), resultSet.getString("DEPARTMENT_NAME"), resultSet.getString("ADDRESS"));
		Employee employee = new Employee(resultSet.getLong("EMPLOYEE_NO"), resultSet.getString("EMPLOYEE_NAME"), resultSet.getString("POSITION"), department);
		InstancePool.getInstancePool().addObjectToPool(employee);
		return employee;
	}

	private DomainKey getKey(ResultSet resultSet) throws SQLException {
		return new EmployeeKey(resultSet.getLong("NO"));
	}
}