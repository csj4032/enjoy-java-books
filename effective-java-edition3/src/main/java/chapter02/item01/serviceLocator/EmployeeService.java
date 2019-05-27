package chapter02.item01.serviceLocator;

public class EmployeeService implements Service {

    @Override
    public String getServiceName() {
        return "Employee Service";
    }

    @Override
    public void execute() {
        System.out.println("Execute Employee Service...");
    }
}
