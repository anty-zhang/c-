package socket.chatroom;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/**
 * �ͻ��˵ĳ���,��ɿͻ�����Ļ��ˢ��
 * 
 * @Author zhang
 * @Date 2005-04-16
 * @version 1.0
 */
public class Client extends Thread implements ActionListener {
	private TextArea ta;

	private TextField tf;

	private Frame f;

	private Button b1;

	private Panel p;

	private String name;

	private Socket socket;

	private DataInputStream in;

	private DataOutputStream out;

	private String temp = ""; // ���ڱȽ����������Ƿ���ͬ

	private boolean flag = true; // �����ж��Ƿ���Ҫˢ�¿ͻ��˵�TEXTAREA��ʾ

	/**
	 * ����������¼�ͻ���NAME����ɽ���ĳ�ʼ����ͬʱ����INIT����
	 * 
	 * @param name
	 *            �ͻ�����
	 * @Author zhang
	 * @Date 2005-04-16
	 */
	public Client(String name) {
		// ��¼��½�ߵ�����
		this.name = name;
		f = new Frame("client frame");
		f.setLocation(300, 300);
		ta = new TextArea(20, 60);
		tf = new TextField("", 40);
		b1 = new Button("send");
		p = new Panel();
		ta.setEditable(false);
		p.add(tf);
		p.add(b1);
		f.add(p, BorderLayout.NORTH);
		f.add(ta, BorderLayout.CENTER);
		f.pack();
		f.show();
		init();
	}

	/**
	 * ���SOCKET�����Լ�ע�������ͬʱ�����߳�
	 * 
	 * @Author zhang
	 * @Date 2005-04-16
	 */
	private void init() {
		try {
			socket = new Socket("127.0.0.1", 2222);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			// ���͵�½�����֣�����ȡ����˵���Ϣ
			out.writeUTF("<1.1>");// ֪ͨ������,�����˵�½
			out.writeUTF(name);// �������˵��û���
			out.writeUTF("<2.1>");// �������������ȫ��������Ϣ
			ta.setText(in.readUTF());
		} catch (Exception ee) {
			// ����SOCKET�쳣���˳�
			ee.printStackTrace();
			System.exit(1);
		}
		// ע�����
		tf.addActionListener(this);
		b1.addActionListener(this);
		f.addWindowListener(new WindowAdapter() {
			// ��ر��Ժ���֪ͨ��������Ȼ�󽫿ͻ���ˢ����ʾ���̹߳ر�
			public void windowClosing(WindowEvent e) {
				try {
					out.writeUTF("<3.2>");// �����뿪
					flag = false;
					Thread.sleep(2000);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				System.exit(1);
			}
		});
		this.start();
	}

	/**
	 * ��������������Ҫ�ǽ��ͻ�˵�Ļ��ύ���������ˣ�ͬʱ���¿ͻ��˵���ʾ
	 * 
	 * @Author zhang
	 * @Date 2005-04-16
	 */
	public void actionPerformed(ActionEvent e) {
		// �ж��Ƿ���Ͼ仰��ͬ����ֹ�ûس�ˢ��
		if (tf.getText() != null && (!temp.equals(tf.getText()))) {
			temp = tf.getText();
			try {
				out.writeUTF("<2.2>");// ����ͻ�����˵��
				out.writeUTF(tf.getText());
				out.writeUTF("<2.1>");
				ta.setText(in.readUTF());
				// ������Ϣ�����TF��ͬʱTF��ù�꽹��
				tf.setText("");
				tf.requestFocus();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
	}

	/**
	 * �̵߳ķ����壬������ÿ��������¿ͻ��˵���ʾ
	 * 
	 * @Author zhang
	 * @Date 2005-04-16
	 */
	public void run() {
		// ��FLAG�жϿͻ����Ƿ���Ҫˢ����Ļ
		while (flag) {
			try {
				Thread.sleep(500);
				out.writeUTF("<2.1>");// Ҫ��ˢ����Ļ
				ta.setText(in.readUTF());
			} catch (Exception ee) {
				// �����쳣����������������⣬�ͻ�����ʾ���˳�
				ee.printStackTrace();
				ta.setText("���������ִ��󣬱�����3���رա�");
				try {
					Thread.sleep(3000);
				} catch (Exception ea) {
					ea.printStackTrace();
				}
				System.exit(1);
			}
		}
	}

}