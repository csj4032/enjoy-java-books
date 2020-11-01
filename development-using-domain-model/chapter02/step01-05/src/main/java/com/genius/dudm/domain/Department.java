package com.genius.dudm.domain;

import com.genius.dudm.service.EmployeeService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jooq.lambda.Unchecked;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class Department extends DomainObject {
	private Long id;
	private String name;
	private String address;

	public Department(Long id, String name, String address) {
		super(new DepartmentKey(id));
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public boolean move(int limitsEmployeeCount, String newAddress) throws Exception {
		EmployeeService employeeService = new EmployeeService();
		List<Employee> employees = employeeService.findEmployeeByDepartment(this);
		if (employees.size() < limitsEmployeeCount + 1) {
			this.setAddress(newAddress);
			employees.stream().forEach(Unchecked.consumer(e -> e.getDepartment().setAddress(newAddress)));
			employeeService.printForMove(employees);
			return true;
		}
		return false;
	}
}