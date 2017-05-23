package leak.classloader;

public interface IExample {

	String message();

	int plusPlus();

	int counter();

	IExample copy(IExample example);

	ILeak leak();
}
