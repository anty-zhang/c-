package day17.high;
/*
д�������Ľ��棬һ���ı���20����ť
BACKSPACE��CE��C��E��
7��8��9����
4��5��6����
1��2��3����
0����������������
 */
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class Comput {
	public static void main(String[] args) {
		JFrame jf = new JFrame("Comput");
		JTextField jtf = new JTextField(15);
		jtf.setEditable(false); //���ܱ༭
		jf.add(jtf,BorderLayout.NORTH);
		
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(5,4));
		
		String[] str = {"BACK","CE","C","+",	"7","8","9","-", "4","5","6","*","1","2","3","/", "0","+/-",".","="};
		//JButton[] jb = new JButton[20];
		JButton[] jb = new JButton[str.length];
		for(int i=0;i<str.length;i++){
			jb[i] = new JButton(str[i]);
			jp.add(jb[i]);
		}
		jf.add(jp,BorderLayout.CENTER);
		
		jf.setResizable(false);   //������ק
		
		jf.setSize(300,200);
		jf.setLocation(300,200);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
