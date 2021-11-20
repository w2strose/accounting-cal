package com.accountingProject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class Calculator extends JFrame implements ActionListener{

	// 계산기 숫자(p1에 사용)
	String btstr[] = {"7","8","9","÷", "4", "5","6","×", 
			"1", "2", "3","-","C", "0","=","+"};
	JButton jbt[] = new JButton[btstr.length];
	
	JTextField jf;
	JTextArea ja;
	int first;
	int second;
	int result;
	String oper;
	String last="";
	String last2="";
	
	
	public Calculator() {
	
		super("까미계산기");

		JPanel jfbp = new JPanel(new BorderLayout());
		jf = new JTextField();
		jf.setEditable(false);
		jf.setBackground(Color.white);
		jf.setHorizontalAlignment(JTextField.RIGHT);
		jf.setFont(new Font("굴림체",Font.BOLD,50));
		jf.setBounds(8,10,270,70);
		
		JPanel btnpanel = new JPanel(new GridLayout(4,4,10,10));
		btnpanel.setBackground(Color.LIGHT_GRAY);
		btnpanel.setBounds(8,9,270,235);
		
		for(int i = 0; i<btstr.length; i++) {
			jbt[i] = new JButton(btstr[i]);
			jbt[i].setFont(new Font("굴림체",Font.BOLD,20));
			if(i>=0 && i<=2 || 4<= i && i<= 6 || 8<=i && i<=10 || i==13 ) {
				jbt[i].setBackground(Color.white);
			}
			btnpanel.add(jbt[i]);
			jbt[i].addActionListener(this);
		}
		
		ja = new JTextArea(5,10);
		ja.setEditable(false);
		ja.setBackground(Color.white);
		
		jfbp.add("North",jf);
		jfbp.add("Center",btnpanel);
		add(jfbp);
		add("East",ja);
		
		setSize(400,400);
		setLocation(400,400);
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String call = e.getActionCommand();
		
		if(call.equals("C")) {
			jf.setText("");
			first = 0;
			second = 0;
			
		}else if(call.equals("=")) {
			last2 = jf.getText() + e.getActionCommand();
			last2 = last2.substring(0,last2.length()-1);
			second = Integer.parseInt(last2);
			
			
			switch(oper){
			case "add": jf.setText(first+second+ "");
						result = first+second;
						ja.append("   "+ result + "\n");
				break;
			case "sub": jf.setText(first-second+"");
						result = first-second;
						ja.append("   "+ result + "\n");
				break;
			case "mul": jf.setText(first*second+"");
						result = first*second;
						ja.append("   "+ result + "\n");
				break;
			case "div": jf.setText(first/second+"");
						result = first/second;
						ja.append("   "+ result + "\n");
				break;
			}
		}else if(call.equals("+")){
			last = jf.getText()+ e.getActionCommand();
			last = last.substring(0,last.length()-1);
			first = Integer.parseInt(last);
			jf.setText("");
			oper = "add";
		}else if(call.equals("-")){
			last = jf.getText()+ e.getActionCommand();
			last = last.substring(0,last.length()-1);
			first = Integer.parseInt(last);
			jf.setText("");
			oper = "sub";
		}else if(call.equals("÷")){
			last = jf.getText()+ e.getActionCommand();
			last = last.substring(0,last.length()-1);
			first = Integer.parseInt(last);
			jf.setText("");
			oper = "div";
		}else if(call.equals("×")){
			last = jf.getText()+ e.getActionCommand();
			last = last.substring(0,last.length()-1);
			first = Integer.parseInt(last);
			jf.setText("");
			oper = "mul";
		}
		else {
			jf.setText(jf.getText()+ e.getActionCommand());
		}
	
	}
}
