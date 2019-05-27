package chapter02.item01.serviceLocator;

public class CustomerService implements Service {

    @Override
    public String getServiceName() {
        return "Customer Service";
    }

    @Override
    public void execute() {
        System.out.println("Execute Customer Service...");
    }
}
