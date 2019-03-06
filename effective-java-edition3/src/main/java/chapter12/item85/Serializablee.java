package chapter12.item85;

import java.io.*;

public class Serializablee {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Genius genius = new Genius("A", 1);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("genius.dat"));
		objectOutputStream.writeObject(genius);

		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("genius.dat"));
		Genius geniuss = (Genius) objectInputStream.readObject();
	}
}