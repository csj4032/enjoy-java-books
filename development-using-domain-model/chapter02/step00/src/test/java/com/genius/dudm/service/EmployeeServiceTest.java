package com.genius.dudm.service;

import com.genius.dudm.domain.Department;
import com.genius.dudm.domain.Employee;
import com.genius.dudm.infrastructure.DatabaseManager;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@DisplayName("직원 관련 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceTest {

	@BeforeAll
	public static void setUp() throws SQLException {
		DatabaseManager.execute("INSERT INTO DEPARTMENT VALUES ('1', '경리과', '본사12층')");
		DatabaseManager.execute("INSERT INTO DEPARTMENT VALUES ('2', '총무과', '본사11층')");

		DatabaseManager.execute("INSERT INTO EMPLOYEE VALUES ('1', '홍길동', '과장','1')");
		DatabaseManager.execute("INSERT INTO EMPLOYEE VALUES ('2', '박문수', '과장','1')");
		DatabaseManager.execute("INSERT INTO EMPLOYEE VALUES ('3', '이순신', '과장','1')");
		DatabaseManager.execute("INSERT INTO EMPLOYEE VALUES ('4', '강감찬', '과장','2')");
		DatabaseManager.execute("INSERT INTO EMPLOYEE VALUES ('5', '장길산', '과장','2')");
	}

	@Test
	@Order(1)
	@DisplayName("전체 직원 조회 테스트")
	public void testFindAllEmployee() throws SQLException {
		EmployeeService employee = new EmployeeService();
		List<Employee> employees = employee.findAllEmployee();
		Assertions.assertEquals(5, employees.size(), "전체 사원수는 5명");
	}

	@Test
	@Order(2)
	@DisplayName("특정 부서 직원 조회 테스트 : STEP1 에서 해결 해보자")
	public void testFindAllEmployeeByDepartment() throws SQLException {
		EmployeeService employee = new EmployeeService();
		List<Employee> employees = employee.findEmployeeByDepartment(new Department(1L, "경리과", "서울"));
		Assertions.assertEquals(3, employees.size(), "경리과 전체 사원수는 5명");
	}

	@AfterAll
	public static void tearDown() throws SQLException {
		DatabaseManager.execute("TRUNCATE DEPARTMENT");
		DatabaseManager.execute("TRUNCATE EMPLOYEE");
	}
}
