package com.genius.dudm.service;

import com.genius.dudm.domain.Department;
import com.genius.dudm.domain.DepartmentKey;
import com.genius.dudm.domain.Employee;
import com.genius.dudm.domain.EmployeeKey;
import com.genius.dudm.infrastructure.DatabaseManager;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("직원 관련 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentServiceTest {

	@BeforeAll
	public static void setUp() throws SQLException {

		DatabaseManager.execute("TRUNCATE EMPLOYEE");
		DatabaseManager.execute("TRUNCATE DEPARTMENT");

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
	@DisplayName("객체는 유일해야 한다.")
	public void testMoveDepartment() throws Exception {
		DepartmentService departmentService = new DepartmentService();
		List<Department> moveDepartments = departmentService.moveDepartment();
		assertEquals(1, moveDepartments.size(), "이동한 부서의 수는 1");
		assertEquals("총무과", moveDepartments.get(0).getName(), "이동한 부서명");
	}

	@Test
	@Order(2)
	@DisplayName("Dept 주소와 Emp 내부의 Dept 주소 모두 변경")
	public void testSetAddress() throws Exception {
		EmployeeService employeeService = new EmployeeService();
		final Employee employee = employeeService.findEmployeeByKey(new EmployeeKey(1L));
		DepartmentService departmentService = new DepartmentService();
		Department department = departmentService.findDepartmentByKey(employee.getDepartment().getKey());
		department.setAddress("별관3층");
		assertEquals("별관3층", employee.getDepartment().getAddress(), "부서주소 수정 후 소속된 사원의 부서 주소");
	}
}