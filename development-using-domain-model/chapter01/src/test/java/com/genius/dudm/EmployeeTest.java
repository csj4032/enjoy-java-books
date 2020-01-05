package com.genius.dudm;

import org.junit.jupiter.api.*;

@DisplayName("직원 관련 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeTest {

	@Test
	@Order(1)
	@DisplayName("직원 생성 테스트")
	public void newEmployeeTest() {
		Employee employee = new Employee();
		Assertions.assertNotNull(employee);
	}

	@Test
	@Order(2)
	@DisplayName("직원 부서 확인 테스트 ")
	public void isDepartment() {
		Department department = new Department();
		Employee employee = new Employee();
		employee.setDepartment(department);
		Assertions.assertTrue(employee.isBelongsTo(department));
	}
}