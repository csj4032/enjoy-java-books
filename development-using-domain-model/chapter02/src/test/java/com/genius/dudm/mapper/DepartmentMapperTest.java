package com.genius.dudm.mapper;

import com.genius.dudm.domain.Department;
import com.genius.dudm.domain.DepartmentKey;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("직원 관련 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentMapperTest {

	@Test
	@Order(1)
	@DisplayName("특정 부서 검색")
	public void testFindById() {
		DepartmentMapper departmentMapper = new DepartmentMapper();
		Department department = departmentMapper.findByKey(new DepartmentKey(1l));
		assertNotNull(department, "부서 존재 여부");
		assertEquals("경리과", department.getName(), "부서명 확인");
	}

	@Test
	@Order(2)
	@DisplayName("부서 전체 확인")
	public void testFindAll() {
		DepartmentMapper departmentMapper = new DepartmentMapper();
		List<Department> departments = departmentMapper.findAll();
		assertEquals(3, departments.size());
	}
}