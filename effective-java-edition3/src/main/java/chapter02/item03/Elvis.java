package chapter02.item03;

import java.io.*;

public class Elvis implements Serializable {

	private static final Elvis INSTANCE = new Elvis();
	private String singleton;

	private Elvis() {
		this.singleton = "yes";
	}

	public static Elvis getInstance() {
		return INSTANCE;
	}

	public void leaveTheBuilding() {
	}

	private Object readResolve() {
		// '진짜' Elvis를 반환하고, 가짜 Elvis는 가비지 컬렉터에 맡긴다.
		return INSTANCE;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Elvis elvis = Elvis.getInstance();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("elvis.dat"));
		objectOutputStream.writeObject(elvis);

		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("elvis.dat"));
		Elvis elviss = (Elvis) objectInputStream.readObject();
		elvis.singleton = "no";
		System.out.printf(elviss.singleton);
	}
}