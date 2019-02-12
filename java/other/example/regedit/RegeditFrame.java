package example.regedit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegeditFrame implements ActionListener{
	JLabel jl1 = new JLabel("ע����Ϣ");
	JTextField jtf1 = new JTextField(10);
	JTextField jtf2 = new JTextField(10);
	JTextField jtf3 = new JTextField(10);
	
	public void actionPerformed(ActionEvent ae){
		String comm = ae.getActionCommand();
		if("ע��".equals(comm)){
			String name = jtf1.getText();
			String password = jtf2.getText();
			if(password == null || !password.matches("^[a-zA-Z0-9_-]{6,8}$"))
				jl1.setText("����6-8λ������������");
			String email = jtf3.getText();
			Regedit regedit = new Regedit(name,password,email);
			try{
				String mes = regedit.regedit();
				jl1.setText(mes);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if("ȡ��".equals(comm)){
			jtf1.setText("");
			jtf2.setText("");
			jtf3.setText("");
			jtf1.requestFocus(); //���λ��������
		}
	}
	
	public RegeditFrame(){
		JFrame jf = new JFrame("�û�ע��");
		jf.setLayout(new GridLayout(5,1));
		JPanel[] jp = new JPanel[5];
		for(int i=0;i<jp.length;i++){
			jp[i] = new JPanel();
		}
		
		JLabel jl2 = new JLabel("�û�����");
		JLabel jl3 = new JLabel("��  �룺");
		JLabel jl4 = new JLabel("��  ����");
		JButton jb1 = new JButton("ע��");
		JButton jb2 = new JButton("ȡ��");
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jp[0].add(jl1);
		jp[1].add(jl2);
		jp[1].add(jtf1);
		jp[2].add(jl3);
		jp[2].add(jtf2);
		jp[3].add(jl4);
		jp[3].add(jtf3);
		jp[4].add(jb1);
		jp[4].add(jb2);
		for(int i = 0;i<jp.length;i++){
			jf.add(jp[i]);
		}
		
		jf.setSize(300,200);
		jf.setLocation(300,200);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new RegeditFrame();
	}
}
