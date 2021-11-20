package com.accountingProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class Interface extends JFrame implements ActionListener {
	// 까미 이미지 
	private ImageIcon img = new ImageIcon("src/img/kami2.jpg");
	
	// 수입,지출,금액, Memo label 
	JLabel in = new JLabel("수입",JLabel.LEFT);
	JLabel ex = new JLabel("지출",JLabel.LEFT);
	JLabel memo = new JLabel("Memo",JLabel.LEFT);
	JLabel memo1 = new JLabel("Memo",JLabel.LEFT);
	JLabel price = new JLabel("금액");
	String[] items1 = {"월급","용돈","재난지원금","기타수입","투자수익"};
	String[] items2 = {"식비","문화생활","교통비","주거비","경조사비"};
	JComboBox combo1 = new JComboBox(items1);
	JComboBox combo2 = new JComboBox(items2);
	JTextArea ja1 = new JTextArea(1,10);
	JTextArea ja3 = new JTextArea(1,20);
	JTextArea ja2 = new JTextArea(1,10);
	JTextArea ja4 = new JTextArea(1,20);
	
	// 파일저장시 필요한 FileChooser,TextArea
	JFileChooser fc;
	JTextArea log;
	// 버튼 
	private JButton income = new JButton("Income");
	private JButton expense = new JButton("Expense");
	
	// 텍스트 area
	private JTextArea ta1 = new JTextArea(10,8);
	private JScrollPane js1 = new JScrollPane(ta1);
	private JTextArea ta2 = new JTextArea(10,10);
	private JScrollPane js2 = new JScrollPane(ta2);
	
	// 잔액 라벨
	
	JLabel l = new JLabel("현재 잔액 : ", JLabel.CENTER);
	int a;
	int sum;
	// 메뉴바 내용물
	JMenu mnuselect = new JMenu("기능");
	JMenuItem mnusave = new JMenuItem("저장하기");
	JMenuItem mnuload = new JMenuItem("불러오기");
	JMenuItem mnunow = new JMenuItem("현재내역");
	
	
	JMenu mnuselect1 = new JMenu("계산기");
	JMenuItem play = new JMenuItem("계산기 실행");
	
	JMenu mnuselect2 = new JMenu("도움말");
	JMenuItem info = new JMenuItem("정보");
	
	public Interface() { // 생성자 시작
	
		// Title
		super("까미 가계부");
		// 레이아웃설정
		setLayout(new GridLayout(1,2));
		
		// FileChooser 객체 생성 *** 
		fc = new JFileChooser();
		log = new JTextArea(5,20); // 텍스트에어리어 길이지정
		
		// 그림, 입력창, 버튼 시작<왼쪽>
		Panel p1 = new Panel(new BorderLayout());
		
		// 이미지추가[North]
		Image im = img.getImage();
		Image im2 = im.getScaledInstance(250, 190, Image.SCALE_DEFAULT);
		ImageIcon img2 = new ImageIcon(im2);
		JLabel img = new JLabel(img2);
		p1.add("North",img); // 이미지추가 끝
		
		// 입력창추가[가운데]
		Panel input = new Panel(new GridLayout(4,1));
		Panel f1 = new Panel(new FlowLayout());
		f1.add(in); f1.add(combo1); f1.add(price); f1.add(ja1);
		ja1.setBorder(BorderFactory.createLineBorder(Color.black));
		input.add(f1);
		Panel f2 = new Panel(new FlowLayout());
		f2.add(memo); f2.add(ja3);
		ja3.setBorder(BorderFactory.createLineBorder(Color.black));
		input.add(f2);
		Panel f3 = new Panel(new FlowLayout());
		f3.add(ex); f3.add(combo2); f3.add(price); f3.add(ja2);
		ja2.setBorder(BorderFactory.createLineBorder(Color.black));
		input.add(f3);
		Panel f4 = new Panel(new FlowLayout());
		f4.add(memo1); f4.add(ja4);
		ja4.setBorder(BorderFactory.createLineBorder(Color.black));
		input.add(f4);
		
		p1.add("Center",input);
		// 입력창 끝
		
		//버튼추가[South]
		Panel Buttons = new Panel(new FlowLayout()); //버튼패널
		Buttons.add(income);
		Buttons.add(expense);
		p1.add("South",Buttons);// 버튼추가
		
		add("West",p1);
		p1.setBackground(Color.orange);
		// 그림, 입력창, 버튼 <왼쪽>끝

		
		// 텍스트area, 잔액출력 <오른쪽>
		Panel p2 = new Panel(new BorderLayout());
		
		Panel half = new Panel(new GridLayout(1,2,5,5));// 텍스트area 패널
		half.add(js1);
		half.add(js2);
		p2.add("Center",half); 
		
		Panel lp = new Panel();
		lp.add(l);
		
		p2.add("South",lp); // 잔액라벨 추가
		
		add("East",p2);
		p2.setBackground(Color.orange);
		// 텍스트 area, 잔액출력 <오른쪽>끝
	
		
		// 메뉴바 시작
		JMenuBar mb = new JMenuBar();
		
		mnuselect.add(mnusave);
		mnuselect.add(mnuload);
		mnuselect.add(mnunow);
		mnuselect1.add(play);
		mnuselect2.add(info);
		
		mb.add(mnuselect); mb.add(mnuselect1); mb.add(mnuselect2);
		setJMenuBar(mb);
		
		// 메뉴바 끝
		
		
		setSize(700, 410);
		setLocation(600, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		info.addActionListener(this);
		income.addActionListener(this);
		expense.addActionListener(this);
		mnuload.addActionListener(this);
		mnusave.addActionListener(this);
		mnunow.addActionListener(this);
		play.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == mnuload) {
			int returnVal = fc.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				log.setText(file.getName()+"\n");
				InterInside ii = new InterInside();
				JTextArea ta = new JTextArea();

				try {
					BufferedReader br = new BufferedReader(new FileReader(file));	
					String line = "";
					String s = "";
					while((s = br.readLine()) != null) {
						line+=s + "\n";
					}
					ta.setText(line);
					ii.add(ta);
				}catch(FileNotFoundException fe) {
					fe.printStackTrace();
				}catch(IOException io) {
					io.printStackTrace();
				}
			}
		}
		if(e.getSource() == mnusave) {
			int returnVal = fc.showSaveDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				log.setText(file.getName()+"\n");
				FileWriter writer = null;
				try {
					// 파일에 한문자 단위로 기록할 수 있는 FileWriter 객체 생성
					writer = new FileWriter(file);

					String str = "▲수입▲" +"\n\n" + ta1.getText() + "\n\n"
							+"▼지출▼" + "\n\n" +ta2.getText() + "\n\n" 
							+"■현재잔액■" + "\n\n" + l.getText();

					writer.write(str);
					writer.flush();
				}catch(FileNotFoundException fe) {
					fe.printStackTrace();
				}catch(IOException ii) {
					ii.printStackTrace();
				}finally {
					try {
						if(writer != null) writer.close();
					}catch(IOException ee) {
						ee.printStackTrace();
					}
				}	
			}

		}
		else if(e.getSource() == mnunow) {
			InterInside ii = new InterInside();
			TextArea ta = new TextArea();

			ta.setText("▲수입▲" +"\n\n" + ta1.getText() + "\n\n"
					+"▼지출▼" + "\n\n" +ta2.getText() + "\n\n" 
					+"■현재잔액■" + "\n\n" + l.getText());

			ii.add(ta);

		}

		if(e.getSource() == income) { // income눌렀을떄
			String b = combo1.getSelectedItem().toString();
			String imsi = ja1.getText();
			String imsi2 = ja3.getText();
			
			ta1.append(b + "         " +
					imsi + "원   \n" + imsi2 + "\n"+"\n");
			a = Integer.parseInt(imsi);
			
			sum += a;
			l.setText("현재 잔액 : " + sum);
			combo1.setSelectedItem("월급");
			ja1.setText("");
			ja3.setText("");
			
		}

		if(e.getSource() == expense) { // expense 눌렀을때
			String b2 = combo2.getSelectedItem().toString();
			String imsi = ja2.getText();
			String imsi2 = ja4.getText();
			ta2.append(b2 + "         " +
					imsi + "원   \n" + imsi2 + "\n"+"\n");
			a = Integer.parseInt(imsi);
			sum -=a;
			l.setText("현재 잔액 : " + sum);
			
			combo2.setSelectedItem("식비");
			ja2.setText("");
			ja4.setText("");
		}
		
		if(e.getSource() == play) {
			Calculator cal = new Calculator();
			
		}
		
		if(e.getSource()==info) { // 도움말 클릭시
			JOptionPane.showMessageDialog(
					this, "이규봉, 홍민기 2인1조 가계부 세미 프로젝트 "
							+ "결과물 입니다"
							, "[정보]",JOptionPane.INFORMATION_MESSAGE); 
		}

	}

	public static void main(String[] args) {
		
		new Interface();
	}

}
