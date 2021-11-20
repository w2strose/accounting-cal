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
	// ��� �̹��� 
	private ImageIcon img = new ImageIcon("src/img/kami2.jpg");
	
	// ����,����,�ݾ�, Memo label 
	JLabel in = new JLabel("����",JLabel.LEFT);
	JLabel ex = new JLabel("����",JLabel.LEFT);
	JLabel memo = new JLabel("Memo",JLabel.LEFT);
	JLabel memo1 = new JLabel("Memo",JLabel.LEFT);
	JLabel price = new JLabel("�ݾ�");
	String[] items1 = {"����","�뵷","�糭������","��Ÿ����","���ڼ���"};
	String[] items2 = {"�ĺ�","��ȭ��Ȱ","�����","�ְź�","�������"};
	JComboBox combo1 = new JComboBox(items1);
	JComboBox combo2 = new JComboBox(items2);
	JTextArea ja1 = new JTextArea(1,10);
	JTextArea ja3 = new JTextArea(1,20);
	JTextArea ja2 = new JTextArea(1,10);
	JTextArea ja4 = new JTextArea(1,20);
	
	// ��������� �ʿ��� FileChooser,TextArea
	JFileChooser fc;
	JTextArea log;
	// ��ư 
	private JButton income = new JButton("Income");
	private JButton expense = new JButton("Expense");
	
	// �ؽ�Ʈ area
	private JTextArea ta1 = new JTextArea(10,8);
	private JScrollPane js1 = new JScrollPane(ta1);
	private JTextArea ta2 = new JTextArea(10,10);
	private JScrollPane js2 = new JScrollPane(ta2);
	
	// �ܾ� ��
	
	JLabel l = new JLabel("���� �ܾ� : ", JLabel.CENTER);
	int a;
	int sum;
	// �޴��� ���빰
	JMenu mnuselect = new JMenu("���");
	JMenuItem mnusave = new JMenuItem("�����ϱ�");
	JMenuItem mnuload = new JMenuItem("�ҷ�����");
	JMenuItem mnunow = new JMenuItem("���系��");
	
	
	JMenu mnuselect1 = new JMenu("����");
	JMenuItem play = new JMenuItem("���� ����");
	
	JMenu mnuselect2 = new JMenu("����");
	JMenuItem info = new JMenuItem("����");
	
	public Interface() { // ������ ����
	
		// Title
		super("��� �����");
		// ���̾ƿ�����
		setLayout(new GridLayout(1,2));
		
		// FileChooser ��ü ���� *** 
		fc = new JFileChooser();
		log = new JTextArea(5,20); // �ؽ�Ʈ����� ��������
		
		// �׸�, �Է�â, ��ư ����<����>
		Panel p1 = new Panel(new BorderLayout());
		
		// �̹����߰�[North]
		Image im = img.getImage();
		Image im2 = im.getScaledInstance(250, 190, Image.SCALE_DEFAULT);
		ImageIcon img2 = new ImageIcon(im2);
		JLabel img = new JLabel(img2);
		p1.add("North",img); // �̹����߰� ��
		
		// �Է�â�߰�[���]
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
		// �Է�â ��
		
		//��ư�߰�[South]
		Panel Buttons = new Panel(new FlowLayout()); //��ư�г�
		Buttons.add(income);
		Buttons.add(expense);
		p1.add("South",Buttons);// ��ư�߰�
		
		add("West",p1);
		p1.setBackground(Color.orange);
		// �׸�, �Է�â, ��ư <����>��

		
		// �ؽ�Ʈarea, �ܾ���� <������>
		Panel p2 = new Panel(new BorderLayout());
		
		Panel half = new Panel(new GridLayout(1,2,5,5));// �ؽ�Ʈarea �г�
		half.add(js1);
		half.add(js2);
		p2.add("Center",half); 
		
		Panel lp = new Panel();
		lp.add(l);
		
		p2.add("South",lp); // �ܾ׶� �߰�
		
		add("East",p2);
		p2.setBackground(Color.orange);
		// �ؽ�Ʈ area, �ܾ���� <������>��
	
		
		// �޴��� ����
		JMenuBar mb = new JMenuBar();
		
		mnuselect.add(mnusave);
		mnuselect.add(mnuload);
		mnuselect.add(mnunow);
		mnuselect1.add(play);
		mnuselect2.add(info);
		
		mb.add(mnuselect); mb.add(mnuselect1); mb.add(mnuselect2);
		setJMenuBar(mb);
		
		// �޴��� ��
		
		
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
					// ���Ͽ� �ѹ��� ������ ����� �� �ִ� FileWriter ��ü ����
					writer = new FileWriter(file);

					String str = "����ԡ�" +"\n\n" + ta1.getText() + "\n\n"
							+"�������" + "\n\n" +ta2.getText() + "\n\n" 
							+"�������ܾס�" + "\n\n" + l.getText();

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

			ta.setText("����ԡ�" +"\n\n" + ta1.getText() + "\n\n"
					+"�������" + "\n\n" +ta2.getText() + "\n\n" 
					+"�������ܾס�" + "\n\n" + l.getText());

			ii.add(ta);

		}

		if(e.getSource() == income) { // income��������
			String b = combo1.getSelectedItem().toString();
			String imsi = ja1.getText();
			String imsi2 = ja3.getText();
			
			ta1.append(b + "         " +
					imsi + "��   \n" + imsi2 + "\n"+"\n");
			a = Integer.parseInt(imsi);
			
			sum += a;
			l.setText("���� �ܾ� : " + sum);
			combo1.setSelectedItem("����");
			ja1.setText("");
			ja3.setText("");
			
		}

		if(e.getSource() == expense) { // expense ��������
			String b2 = combo2.getSelectedItem().toString();
			String imsi = ja2.getText();
			String imsi2 = ja4.getText();
			ta2.append(b2 + "         " +
					imsi + "��   \n" + imsi2 + "\n"+"\n");
			a = Integer.parseInt(imsi);
			sum -=a;
			l.setText("���� �ܾ� : " + sum);
			
			combo2.setSelectedItem("�ĺ�");
			ja2.setText("");
			ja4.setText("");
		}
		
		if(e.getSource() == play) {
			Calculator cal = new Calculator();
			
		}
		
		if(e.getSource()==info) { // ���� Ŭ����
			JOptionPane.showMessageDialog(
					this, "�̱Ժ�, ȫ�α� 2��1�� ����� ���� ������Ʈ "
							+ "����� �Դϴ�"
							, "[����]",JOptionPane.INFORMATION_MESSAGE); 
		}

	}

	public static void main(String[] args) {
		
		new Interface();
	}

}
