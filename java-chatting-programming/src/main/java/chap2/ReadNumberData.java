package chap2;

import java.io.*;
public class ReadNumberData
{
   static FileInputStream fin;
   static DataInputStream dis;
   public static void main(String args[]){
      boolean bdata;
      double ddata;
      int number;
      try{
         fin = new FileInputStream("numberdata.txt");
         dis = new DataInputStream(fin);
         bdata = dis.readBoolean(); // �ο� ���� �д´�.
         System.out.println(bdata);
         ddata = dis.readDouble(); // �Ǽ� ���� �д´�.
         System.out.println(ddata);
         while(true){
            number = dis.readInt();
            System.out.print(number+" "); // �������� �а� ȭ�鿡 ����Ѵ�.
         }
      }catch(EOFException e){
         System.out.println("�����͸� ��� �о����ϴ�."); // ��������
      }catch(IOException e){ // ������ ����
         System.err.println(e);
      }
   }
}