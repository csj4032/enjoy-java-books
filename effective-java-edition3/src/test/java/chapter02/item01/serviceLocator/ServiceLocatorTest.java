package chapter02.item01.serviceLocator;

import org.junit.jupiter.api.Test;

class ServiceLocatorTest {

    @Test
    public void serviceLocator() {
        Service service = ServiceLocator.getService("EmployeeService");
        service.execute();

        service = ServiceLocator.getService("CustomerService");
        service.execute();
    }
}