package com.genius.dudm;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("직원 관련 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeTest {

	@Test
	@Order(1)
	@DisplayName("직원 생성 테스트")
	public void newEmployeeTest() {
		Employee employee = new Employee();
		assertNotNull(employee);
	}

	@Test
	@Order(2)
	@DisplayName("직원 부서 확인 테스트 ")
	public void isDepartment() {
		Department department = new Department();
		Employee employee = new Employee();
		employee.setDepartment(department);
		assertTrue(employee.isBelongsTo(department));
	}

	@Test
	@Order(3)
	@DisplayName("모든 직원 구하기")
	public void findEmployee() {
		EmployeeService employeeService = new EmployeeService();
		List<Employee> all = employeeService.findAllEmployee();
		assertEquals(5, all.size());
	}

	@Test
	@Order(4)
	@DisplayName("특정 부서 직원 구하기")
	public void findEmployeeByDepartment() {
		Department department = new Department(1, "총무과");
		EmployeeService employeeService = new EmployeeService();
		List<Employee> all = employeeService.findAllEmployeeByDepartment(department);
		assertEquals(4, all.size());
	}
}