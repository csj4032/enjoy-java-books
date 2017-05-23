package leak.classloader;

public class Leak implements ILeak {

	private ILeak leak;

	public Leak(ILeak leak) {
		this.leak = leak;
	}
}
