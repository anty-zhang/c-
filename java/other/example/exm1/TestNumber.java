package day17.high;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class TestNumber implements ActionListener{//static   //1ʵ�ֽӿ�
	JLabel jl = new JLabel("welcome !!");                      //��������
	private int num = 0;
	public void actionPerformed(ActionEvent ae){               //��д����
		String mes = ae.getActionCommand();
		if("+".equals(mes))
			num++;
		else if("-".equals(mes))
			num--;
		jl.setText("you click "+num+" times!");
	}
	
	public TestNumber(){                                     //��ֲ����
		JFrame jf = new JFrame();
		jf.setLayout(new FlowLayout());
		jf.add(jl);
		JButton jb1 = new JButton("+");
		jb1.addActionListener(this);    //this                 //ע�����
		jf.add(jb1);
		JButton jb2 = new JButton("-");
		jb2.addActionListener(this);
		jf.add(jb2);
		jf.setSize(300,200);
		jf.setLocation(300,200);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new TestNumber();
	}

}
