package chap2;

import java.io.*;

public class ReadNumberData {
	static FileInputStream fin;
	static PipedInputStream pis;
	static ByteArrayInputStream bais;
	static DataInputStream dis;
	static BufferedInputStream bif;
	static LineNumberInputStream lnis;

	static String path = ReadNumberData.class.getClassLoader().getResource("chap2/numberdata.txt").getPath();

	public static void main(String args[]) {
		boolean bdata;
		double ddata;
		int number;
		try {
			fin = new FileInputStream(path);
			bais = new ByteArrayInputStream(new byte[10]);

			dis = new DataInputStream(fin);
			bif = new BufferedInputStream(fin);

//			int c;
//			while ((c = fin.read()) != -1) {
//				System.out.print((char) c);
//			}

			String ch;
			while ((ch = dis.readLine()) != null) {
				System.out.print(ch);
			}

			lnis = new LineNumberInputStream(fin);

			bdata = dis.readBoolean();
			ddata = dis.readDouble();
			number = dis.readInt();


			InputStreamReader isr_fin = new InputStreamReader(fin);
			InputStreamReader isr_bif = new InputStreamReader(bif);
			InputStreamReader isr_dis = new InputStreamReader(dis);
			InputStreamReader isr_bais = new InputStreamReader(bais);

			FileReader fr = new FileReader(path);

			BufferedReader br_isr_bif = new BufferedReader(isr_bif);
			BufferedReader br_isr_dis = new BufferedReader(isr_dis);
			BufferedReader br_isr_fr = new BufferedReader(fr);
			BufferedReader br_isr_bais = new BufferedReader(fr);

			br_isr_bif.readLine();
			br_isr_dis.readLine();
			br_isr_fr.readLine();
			br_isr_bais.readLine();

			while (true) {
				number = dis.readInt();
				System.out.print(number + " ");
			}
		} catch (EOFException e) {
			System.out.println("입출력 에러 발생!!!!");
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}