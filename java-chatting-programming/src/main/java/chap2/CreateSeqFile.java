package chap2;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
public class CreateSeqFile extends Frame implements ActionListener
{
   private TextField account, name, balance;
   private Button enter, done; 
   private DataOutputStream output;  // ���� ��Ʈ�� ��ü
   public CreateSeqFile() {
      super( "�������� ����" );
      try {
         output = new DataOutputStream(new FileOutputStream("client.txt"));
      }catch ( IOException e ) {
         System.err.println(e.toString());
         System.exit(1);
      }
      setSize(250, 130);
      setLayout( new GridLayout( 4, 2 ));
      add( new Label("���¹�ȣ"));
      account = new TextField(); // ���¹�ȣ �Է� �ʵ�
      add(account);
      add(new Label("�̸�"));
      name = new TextField( 20 ); // �̸� �Է� �ʵ�
      add(name);      
      add(new Label("�ܰ�"));
      balance = new TextField( 20 ); // �ܰ� �Է� �ʵ�
      add(balance);
      enter = new Button("�Է�"); // �Էµ� �����͸� �����ϴ� ��ư.
      enter.addActionListener(this); // �̺�Ʈ�� ����
      add(enter);
      done = new Button("����"); // �Է��� �����ϴ� ��ư.
      done.addActionListener( this ); // �̺�Ʈ�� ����
      add(done);
      setVisible( true );  
   }
   public void addRecord() {
      int accountNo = 0;
      String d;
      if(!account.getText().equals("")){ // ���¹�ȣ�� �Է��� üũ
         try{
            accountNo = Integer.parseInt(account.getText());
            if(accountNo > 0){
               output.writeInt(accountNo); // ���¹�ȣ�� ������ ���Ͽ� �����Ѵ�.
               output.writeUTF(name.getText() ); // �̸��� ���ڿ��� �����Ѵ�.
               d=balance.getText(); // �ܰ� ���ڿ��� ����
               output.writeDouble(Double.valueOf(d)); // �ܰ� �Ǽ��� �����Ѵ�.
            }
            account.setText( "" ); // �ؽ�Ʈ �ʵ带 ����
            name.setText( "" );
            balance.setText( "" );
         }catch (NumberFormatException nfe) {
            System.err.println("������ �Է��ؾ� �մϴ�." );
         }catch (IOException io) {
            System.err.println(io.toString());
            System.exit( 1 );
         }
      }
   }
   public void actionPerformed(ActionEvent e) {
      addRecord(); // �Էµ� �����͸� ���Ͽ� �����Ѵ�.
      if (e.getSource() == done) {
         try {
            output.close(); // ������ �ݴ´�.
         }catch(IOException io) {
            System.err.println(io.toString());
         }
         System.exit( 0 ); // ���α׷��� �����Ѵ�.
      }
   }
   public static void main( String args[] ){
      new CreateSeqFile();
   }
}