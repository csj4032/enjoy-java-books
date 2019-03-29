package com.genius;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;

public class Calculator extends JFrame {

	private Container contentPane;
	private JPanel leftPanel;
	private JPanel centerPanel;
	private JPanel buttonPanel;
	private JTextField input1TextField;
	private JTextField input2TextField;
	private JLabel answerLabel;
	private JButton plusButton;
	private JButton minusButton;

	public Calculator() throws HeadlessException {
		super("Simple Calculator");

		contentPane = this.getContentPane();
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(3, 1));
		leftPanel.add(new JLabel("Input 1:  "));
		leftPanel.add(new JLabel("Input 2:  "));
		leftPanel.add(new JLabel("Answer:  "));
		contentPane.add(leftPanel, BorderLayout.WEST);

		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(3, 1));
		input1TextField = new JTextField(10);
		input2TextField = new JTextField(10);
		answerLabel = new JLabel();
		centerPanel.add(input1TextField);
		centerPanel.add(input2TextField);
		centerPanel.add(answerLabel);
		contentPane.add(centerPanel, BorderLayout.CENTER);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 1));
		plusButton = new JButton("+");
		minusButton = new JButton("-");
		buttonPanel.add(plusButton);
		buttonPanel.add(minusButton);
		contentPane.add(buttonPanel, BorderLayout.EAST);

		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		this.setSize(250, 100);
		this.setVisible(true);

		ActionListener actionListener = (e) -> {
			BigDecimal d1 = new BigDecimal(input1TextField.getText());
			BigDecimal d2 = new BigDecimal(input2TextField.getText());
			if (e.getSource() == plusButton) {
				answerLabel.setText("" + (d1.add(d2)));
			} else {
				answerLabel.setText("" + (d1.subtract(d2)));
			}
		};

		plusButton.addActionListener(actionListener);
		minusButton.addActionListener(actionListener);

		WindowListener windowListener = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Calculator.this.dispose();
				System.exit(0);
			}
		};

		this.addWindowListener(windowListener);
	}

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
	}
}
