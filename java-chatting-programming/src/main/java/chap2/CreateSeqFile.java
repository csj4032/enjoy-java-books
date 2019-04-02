package chap2;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class CreateSeqFile extends Frame implements ActionListener {
	private TextField account, name, balance;
	private Button enter, done;
	private DataOutputStream output;

	public CreateSeqFile() {
		super("Seq File");
		try {
			output = new DataOutputStream(new FileOutputStream("client.txt"));
		} catch (IOException e) {
			System.err.println(e.toString());
			System.exit(1);
		}
		setSize(250, 130);
		setLayout(new GridLayout(4, 2));
		add(new Label("���¹�ȣ"));
		account = new TextField();
		add(account);
		add(new Label("�̸�"));
		name = new TextField(20);
		add(name);
		add(new Label("�ܰ�"));
		balance = new TextField(20);
		add(balance);
		enter = new Button("�Է�");
		enter.addActionListener(this);
		add(enter);
		done = new Button("����");
		done.addActionListener(this);
		add(done);
		setVisible(true);
	}

	public void addRecord() {
		int accountNo = 0;
		String d;
		if (!account.getText().equals("")) {
			try {
				accountNo = Integer.parseInt(account.getText());
				if (accountNo > 0) {
					output.writeInt(accountNo);
					output.writeUTF(name.getText());
					d = balance.getText();
					output.writeDouble(Double.valueOf(d));
				}
				account.setText("");
				name.setText("");
				balance.setText("");
			} catch (NumberFormatException nfe) {
				System.err.println("������ �Է��ؾ� �մϴ�.");
			} catch (IOException io) {
				System.err.println(io.toString());
				System.exit(1);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		addRecord();
		if (e.getSource() == done) {
			try {
				output.close();
			} catch (IOException io) {
				System.err.println(io.toString());
			}
			System.exit(0);
		}
	}

	public static void main(String args[]) {
		new CreateSeqFile();
	}
}