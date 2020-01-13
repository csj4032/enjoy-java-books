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
				E.ID AS EMPLOYEE_ID,
				E.NAME AS EMPLOYEE_NAME,
				E.POSITION,
				D.ID AS DEPARTMENT_ID,
				D.NAME AS DEPARTMENT_NAME,
				D.ADDRESS
			FROM
				EMPLOYEE AS E, DEPARTMENT AS D
			WHERE
				E.DEPARTMENT_ID = D.ID
			""";

	@Override
	protected String getFindAllSql() {
		return SELECT_SQL;
	}

	@Override
	protected String getFindByKeySql() {
		return SELECT_SQL + " AND E.ID = ?";
	}

	public List<Employee> findByDepartment(Department department) {
		return find(SELECT_SQL + " AND D.ID = ?", new Object[]{department.getId()});
	}

	@Override
	public List<Employee> findAll() {
		return find(SELECT_SQL, null);
	}

	protected Employee load(ResultSet resultSet) throws SQLException {
		if (InstancePool.getInstancePool().containsInPool(getKey(resultSet))) {
			log.info("Instance Pool");
			return InstancePool.getInstancePool().getObjectFromPool(getKey(resultSet));
		}
		//Department department = new Department(resultSet.getLong("DEPARTMENT_ID"), resultSet.getString("DEPARTMENT_NAME"), resultSet.getString("ADDRESS"));
		Employee employee = new Employee(resultSet.getLong("EMPLOYEE_ID"), resultSet.getString("EMPLOYEE_NAME"), resultSet.getString("POSITION"));
		InstancePool.getInstancePool().addObjectToPool(employee);
		return employee;
	}

	@Override
	protected Employee doLoad(ResultSet resultSet) throws Exception {
		DepartmentMapper departmentMapper = new DepartmentMapper();
		return new Employee(resultSet.getLong("EMPLOYEE_ID"), resultSet.getString("EMPLOYEE_NAME"), resultSet.getString("POSITION"));
	}

	public DomainKey getKey(ResultSet resultSet) throws SQLException {
		return new EmployeeKey(resultSet.getLong("E.ID"));
	}
}