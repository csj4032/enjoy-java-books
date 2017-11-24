package chapter02.item03.serializable;

import java.io.*;

public class SerializedSingleton implements Serializable {

	private static final long serailVersionUID = -7604766932017737115L;

	private SerializedSingleton() {
	}

	private static class SingletonHelper {
		private static final SerializedSingleton instance = new SerializedSingleton();
	}

	public static SerializedSingleton getInstance() {
		return SingletonHelper.instance;
	}

	protected Object readResolve() {
		return getInstance();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SerializedSingleton instanceOne = SerializedSingleton.getInstance();

		try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.ser"))) {
			out.writeObject(instanceOne);
			out.close();
		}

		SerializedSingleton instanceTwo;
		try (ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"))) {
			instanceTwo = (SerializedSingleton) in.readObject();
			in.close();
		}

		System.out.println("instanceOne hashCode=" + instanceOne.hashCode());
		System.out.println("instanceTwo hashCode=" + instanceTwo.hashCode());

	}
}
