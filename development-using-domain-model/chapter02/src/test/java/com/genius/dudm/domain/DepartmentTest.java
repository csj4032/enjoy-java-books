package com.genius.dudm.domain;

import com.genius.dudm.service.DepartmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("부서 도메인 테스트")
public class DepartmentTest {

	@Test
	@Order(1)
	@DisplayName("총무과 이동")
	public void moveTest() {
		Department department = new Department(2, "총무과", "본사11");
		department.move(2, "본사13");
	}

	@Test
	@Order(2)
	@DisplayName("전체 부서 중 인원 2명 이하 이동")
	public void moveDepartmentTest() {
		DepartmentService departmentService = new DepartmentService();
		departmentService.moveDepartment();
	}
}