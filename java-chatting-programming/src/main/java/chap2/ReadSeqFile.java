package chap2;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
public class ReadSeqFile extends Frame implements ActionListener
{
   private TextField account, name, balance;
   private Button next, done; 
   private DataInputStream input;  // ���� �Է� ��Ʈ�� ��ü
   public ReadSeqFile() {
      super( "�������� ����");
      try {
         input = new DataInputStream(new FileInputStream("client.txt"));
      }catch ( IOException e ) {
         System.err.println(e.toString());
         System.exit(1);
      }      
      setSize(250, 130);
      setLayout( new GridLayout( 4, 2 ));
      add( new Label("���¹�ȣ"));
      account = new TextField(); // ���¹�ȣ�� �д� �ʵ�.
      account.setEditable(false); // ������ �Է��� ������Ų��.
      add(account);
      add(new Label("�̸�"));
      name = new TextField( 20 ); // �̸��� �д� �ʵ�.
      name.setEditable(false); // �������� �Է��� ������Ű��.
      add(name);
      add(new Label("�ܰ�"));
      balance = new TextField( 20 ); // �ܰ� �д� �ʵ�.
      balance.setEditable(false); // �������� �Է��� ������Ų��.
      add(balance);
      next = new Button("���"); // ���Ϸκ��� �����͸� �д� ��ư
      next.addActionListener(this);
      add(next);      
      done = new Button("����"); // ���α׷��� �����ϴ� ��ư
      done.addActionListener( this );
      add(done);       
      setVisible(true);  
   }
   public void actionPerformed(ActionEvent e){
      if (e.getSource() == next)
         readRecord(); // �����͸� �� ���ڵ徿 �д� �޼ҵ�
      else
         closeFile();
   }
   public void readRecord(){
      int accountNo;
      double d;
      String namedata;
      try{
         accountNo = input.readInt(); // �������� ���¹�ȣ�� �д´�.
         namedata = input.readUTF(); // ���ڿ��� �̸��� �д´�.
         d = input.readDouble(); // �Ǽ����� �ܰ� �д´�.
// �о�帰 �����͸� ���õ� �ؽ�Ʈ �ʵ忡 ����Ѵ�.
         account.setText(String.valueOf(accountNo));
         name.setText(namedata);
         balance.setText(String.valueOf(d));
      }catch(EOFException eof){
         closeFile();
      }catch (IOException io) {
         System.err.println(io.toString());
         System.exit(1);
      }
   }
   private void closeFile(){ // ��Ʈ���� �ݰ� ���α׷��� �����Ѵ�.
      try{
         input.close();
         System.exit(0);
      }catch(IOException io){
         System.err.println(io.toString());
         System.exit(1);
      }
   }
   public static void main( String args[] ){
      new ReadSeqFile();
   }
}