package com.accountingProject;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Font;
import java.awt.Font;
import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Button;


public class calcuEx extends JFrame implements ActionListener {
	JLabel label;           // 연산 결과창
	boolean state = false; // 화면에 표시된 number 핸들러
	double num1, num2; 
	double result;         // 연산 결과
	String func = "";     // 기능 연산자
	String nInput = "";   // 마지막에 누른 연산자 저장

	String btn[] = { "←", "C", "%", "x²", "7", "8", "9", "÷", "4", "5", "6", "×", "1", "2", "3", "-", ".", "0", "=",
	"+" };        // 버튼 안에 값 배열


	public calcuEx() {

		super("Calculator"); // title 지정
		super.setResizable(true); // 프레임의 크기를 사용자가 조절

		/*
		 * JFrame : 메인 프레임 JPanel : 보조 프레임 JLabel : 기능을 수행하는 컴포넌트
		 */

		// 결과 창 GUI
		label = new JLabel("0", JLabel.RIGHT); // ("첫화면 출력될 값", 위치)

		JPanel mainView = new JPanel();
		label.setFont(new Font("Serif", Font.BOLD, 50)); // Font 지정
		label.setBackground(Color.WHITE); // 결과창 배경색 지정
		label.setOpaque(true); // 배경색을 적용하기 위해 불투명 설정

		// 버튼 창 GUI
		JPanel btnView = new JPanel();

		btnView.setLayout(new GridLayout(5, 4, 2, 2)); // 행과 열로 구성된 레이아웃 설정 (row, cols, 간격, 간격)

		JButton button[] = new JButton[btn.length]; // 버튼 생성, 배열의 길이만큼 값을 가져옴

		for (int i = 0; i < btn.length; i++) {
			button[i] = new JButton(btn[i]);
			button[i].setFont(new Font("Serif", Font.BOLD, 25)); // Font 지정
			button[i].addActionListener(this); // 익명클래스로 버튼 이벤트 추가 ,이벤트 리스너의 객체이므로 this로 지정

			if (i == 0 || i == 1 || i == 18)
				button[i].setForeground(Color.RED); // 기능별 색 지정
			if (i == 2 || i == 3 || i == 7 || i == 11 || i == 15 || i == 19)
				button[i].setForeground(Color.BLUE);

			btnView.add(button[i]);

		}

		// 프레임 배치 및 설정
		mainView.setLayout(new BorderLayout()); // 동서남북 레이아웃 배치
		add(label, BorderLayout.CENTER); // 결과창 배치
		add(btnView, BorderLayout.SOUTH); // 버튼창 베치

		setBounds(100, 100, 300, 400); // 프레임의 크기 지정
		setDefaultCloseOperation(EXIT_ON_CLOSE); // X버튼을 누르면 닫히는 설정
		setVisible(true); // 프레임이 보이도록 설정

	}

	// 마우스 클릭에 의한 동작

	@Override
	public void actionPerformed(ActionEvent e) {

		String input = e.getActionCommand(); // 이벤트를 발생시킨 객체의 문자열을 가져와서 input에 넣음

		if (input.equals("+")) {
			num1 = num2;  
			func = "+";
			nInput = ""; // 마지막에 누른 연산자 저장

		} else if (input.equals("-")) {
			num1 = num2;
			func = "-";
			nInput = "";

		} else if (input.equals("×")) {
			num1 = num2;
			func = "×";
			nInput = "";

		} else if (input.equals("÷")) {
			num1 = num2;
			func = "÷";
			nInput = "";

		} else if (input.equals("%")) {
			num1 = num2;
			func = "%";
			nInput = "";
			result = num1 / 100;
			label.setText(String.valueOf(result)); //결과값을 문자열로 반환하여 결과창에 출력
		}

		else if (input.equals("x²")) {
			num1 = num2;
			func = "x²";
			nInput = "";
			result = num1 * num1;
			label.setText(String.valueOf(result));
			state = true;

		} else if (input.equals("C")) {  // Clear
			nInput = "";
			num2 = 0;
			num1 = 0;
			label.setText("0");

			// substring(start, end) - start부터 end 전까지 문자열 자르기
		} else if (input.equals("←")) {     // 왼쪽부터 순차적으로 지워지도록 함
			setBackSpace(getBackSpace().substring(0, getBackSpace().length() - 1));

			if (getBackSpace().length() < 1) {  // 더 이상 지울 숫자가 없으면, 0으로 clear
				nInput = "";
				num2 = 0;
				num1 = 0;
				label.setText("0");
			}

		} else if (input.equals("=")) {
			if (func.equals("+")) {
				result = num1 + num2;
				label.setText(String.valueOf(result)); //결과값을 문자열로 반환하여 결과창에 출력
				state = true; // 결과 값이 나온 후 다음 입력이 들어왔을 때 화면에 표시된 결과 값을 지운다.

			} else if (func.equals("-")) {
				result = num1 - num2;
				label.setText(String.valueOf(result));
				state = true;

			} else if (func.equals("×")) {
				result = num1 * num2;
				label.setText(String.valueOf(result));
				state = true;

			} else if (func.equals("÷")) {
				result = num1 / num2;
				label.setText(String.valueOf(result));
				state = true;

			}

		} else {
			if (state) {
				label.setText("0");
				state = false;
				num1 = 0;
				num2 = 0;
				nInput = "";
			}

			nInput += e.getActionCommand();
			label.setText(nInput);
			num2 = Double.parseDouble(nInput); //문자열에서 double형 변

		}

	}

	private void setBackSpace(String bs) {
		label.setText(bs);
	}

	private String getBackSpace() {
		return label.getText();
	}

	public static void main(String[] args) {
		new calcuEx();
	}
}

//
//private String[] str = {"7","8","9", "4", "5","6", "1", "2", "3","C", "0","="};
//private String[] str1 = {"÷","×","－","＋"};
//private Button[] bt = new Button[str.length];
//private Button[] bt1 = new Button[str1.length];
//
//private JLabel calta = new JLabel(" ",JLabel.RIGHT);
//private JLabel calta2 = new JLabel("0",JLabel.RIGHT);
//
//int first = 0;
//int second = 0;
//int result = 0;
//String recent = "";
//String func = "";
//boolean exist = true; // 현재 숫자가 저장됨?
//
//public Calculator() {
//	super("까미 계산기");	
//	Container c = getContentPane();
//	c.setLayout(new BorderLayout());
//	c.setBackground(Color.black);
//	
//	Panel p1 = new Panel(new GridLayout(4,3,5,5));
//	
//	for(int i = 0; i<str.length; i++) {
//		bt[i] = new Button(str[i]); // 배열지정
//		bt[i].setFont(new Font("궁서체", Font.BOLD,20)); // 폰트 지정
//		p1.add(bt[i]);
//		bt[i].setBackground(Color.white);
//		
//	}
//	for(int i = 0; i<str.length; i++) {
//		bt[i].addActionListener(this);
//	}
//	
//	Panel p2 = new Panel(new GridLayout(4,1,5,5));
//	
//	for(int i = 0; i<str1.length; i++) {
//		bt1[i] = new Button(str1[i]);
//		bt1[i].setFont(new Font("궁서체",Font.BOLD,30));
//		p2.add(bt1[i]);
//		bt1[i].addActionListener(this);
//	}
//	
//	calta.setFont(new Font("맑은 고딕", 0, 40));
//	calta.setBackground(Color.BLACK);
//	calta.setForeground(Color.WHITE);
//	calta2.setForeground(Color.WHITE);
//	calta2.setBackground(Color.BLACK);
//	
//	add("North", calta);
//	c.add("Center", p1);
//	c.add("East",p2);
//	setSize(400,400);
//	setLocation(900, 400);
//	setVisible(true);
//	
//}
//
//@Override
//public void actionPerformed(ActionEvent e) {
//	
//	/* 숫자 입력하고, 그걸 변수에 저장 / 사칙연산을 누르면 0으로 초기화, 전에 입력한 수를 first에 저장
//	 * = 를 누르면 first와 다시 입력한 수를 더해.
//	 * 
//	 */
//	
//	
//	Object obj = e.getSource();
////	
//	String input = e.getActionCommand();
//	
//	
//	
//}