package com.genius.dudm.mapper;

import com.genius.dudm.domain.Department;
import com.genius.dudm.domain.DepartmentKey;
import com.genius.dudm.domain.DomainKey;
import com.genius.dudm.infrastructure.InstancePool;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentLoader {

	private DepartmentMapper departmentMapper;
	private EmployeeMapper employeeMapper;
	protected Map<DepartmentKey, Department> inProgress = new HashMap();
	protected List<DomainKey> resultKeys = new ArrayList<>();

	public DepartmentLoader(DepartmentMapper departmentMapper, EmployeeMapper employeeMapper) {
		this.departmentMapper = departmentMapper;
		this.employeeMapper = employeeMapper;
	}

	public List<Department> load(ResultSet resultSet) throws Exception {
		while (resultSet.next()) {
			loadRow(resultSet);
		}
		addAllNewObjectToPool();
		return makeResult();
	}

	private void addAllNewObjectToPool() {
		inProgress.keySet().forEach(key -> {
			if (!InstancePool.getInstancePool().containsInPool(key)) {
				InstancePool.getInstancePool().addObjectToPool(inProgress.get(key));
			}
		});
	}

	private void loadRow(ResultSet resultSet) throws Exception {
		DepartmentKey key = departmentMapper.getKey(resultSet);
		if (!resultKeys.contains(key)) resultKeys.add(key);
		if (!InstancePool.getInstancePool().containsInPool(key)) {
			if (!inProgress.containsKey(key)) {
				inProgress.put(key, departmentMapper.doLoad(resultSet));
			}
		} else {
			inProgress.put(key, InstancePool.getInstancePool().getObjectFromPool(key));
		}

		Department department = inProgress.get(key);
		System.out.println(department);
		department.addEmployee(employeeMapper.load(resultSet));
	}

	private List<Department> makeResult() {
		List<Department> result = new ArrayList<>();
		resultKeys.forEach(e -> result.add(InstancePool.getInstancePool().getObjectFromPool(e)));
		return result;
	}
}
