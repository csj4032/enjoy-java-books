package com.genius.payroll.persistence;

import com.genius.payroll.domain.Employee;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PayrollDatabase {

	private static Map<Long, Employee> employees = new HashMap<>();
	private static Map<Long, Long> unionMembers = new HashMap<>();

	public static Employee getEmployee(long empId) {
		EntityManager entityManager = PersistenceManager.getEntityManagerFactory().createEntityManager();
		return entityManager.find(Employee.class, empId);
		//return employees.get(empId);
	}

	public static void addEmployee(long empId, Employee employee) {
		EntityManager entityManager = PersistenceManager.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
		entityManager.close();
		//PersistenceManager.shutdown();
		//employees.putIfAbsent(empId, employee);
	}

	public static void deleteEmployee(long empId) {
		employees.remove(empId);
	}

	public static void clear() {
		employees.clear();
		unionMembers.clear();
	}

	public static void addUnionMember(long memberId, Employee employee) {
		unionMembers.putIfAbsent(memberId, employee.getEmpId());
	}

	public static Employee getUnionMember(long memberId) {
		return employees.get(unionMembers.get(memberId));
	}

	public static void removeUnionMember(long memberId) {
		unionMembers.remove(memberId);
	}

	public static List<Long> getAllEmployeeIds() {
		return employees.keySet().stream().collect(Collectors.toList());
	}
}