package chap2;

import java.io.*;
public class BufferedStreamCopier
{
   public static void main(String args[]){
      try{
         copy(System.in, System.out);
      }catch(IOException e){System.err.println(e);}
   }
   public static void copy(InputStream in, OutputStream out) throws IOException{
      synchronized(in){
         synchronized(out){
            BufferedInputStream bin = new BufferedInputStream(in);
            BufferedOutputStream bout = new BufferedOutputStream(out);
            while(true){
               int data = bin.read(); // ���ۿ� �ִ� �����͸� �д´�.
               if(data==-1) break; // CTRL-Z �Է��� -1�� ��ȯ�Ѵ�.
               bout.write(data); // ���ۿ� �����͸� �����Ѵ�.
            }
            bout.flush(); // ���ۿ� �ִ� ��� �����͸� ��Ʈ������ �����Ѵ�.
         }
      }
   }
}