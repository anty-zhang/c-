package day17.high;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;

public class TestJframe {
	public static void main(String[] args) {
		JFrame jf = new JFrame("TestJframe");
		JButton jb = new JButton(new ImageIcon("/home/tarena01/3.jpg","ok"));
		jf.setSize(300,200);//���ô�С
		jf.setLocation(300, 200);//������ʾλ��
		jf.add(jb);    //�Ѱ�ť���봰��
		jf.setVisible(true);//���ÿɼ�
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�ر��˳�
	}

}
