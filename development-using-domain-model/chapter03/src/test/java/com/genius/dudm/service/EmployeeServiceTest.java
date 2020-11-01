package com.genius.dudm.service;

import com.genius.dudm.domain.Department;
import com.genius.dudm.domain.Employee;
import com.genius.dudm.domain.EmployeeKey;
import com.genius.dudm.service.EmployeeService;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

@DisplayName("직원 관련 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceTest {

	@Test
	@Order(1)
	@DisplayName("전체 직원 조회 테스트")
	public void allEmployeeTest() {
		EmployeeService employee = new EmployeeService();
		List<Employee> employees = employee.findAllEmployee();
		Assertions.assertEquals(6, employees.size());
	}

	@Test
	@Order(2)
	@DisplayName("특정 부서 직원 조회 테스트")
	public void EmployeeByDepartmentTest() {
		EmployeeService employee = new EmployeeService();
		Department department = new Department(1, "경리과", "본사11층", null);
		List<Employee> employees = employee.findAllEmployeeByDepartment(department);
		Assertions.assertEquals(3, employees.size());
	}

	@Test
	@Order(3)
	@DisplayName("특정 직원 조회 테스트")
	public void EmployeeFindByIdTest() throws SQLException {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = employeeService.findById(new EmployeeKey(1l));
		Assertions.assertEquals(1, employee.getId());
		Assertions.assertEquals("가길동", employee.getName());
		Assertions.assertEquals("과장", employee.getPosition());
		employeeService.findById(new EmployeeKey(1l));
		employeeService.findById(new EmployeeKey(1l));
	}
}
