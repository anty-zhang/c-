package day17.high;
/*
��ҵ��
 1 дһ�������ֵ���Ϸ��Ҫ��
    �㿪ʼ��ť����1-100���������Ȼ������֣��ı���/��ť����������ʾ�´��ˣ�JLabel����С����ʾ��С�ˣ�������ʾ�µĴ�����
   �ı���õ�/�����������ݷ�����getText/setText��
   ������ť��1��JLabel��1���ı���

  2 ����дһ�¼����������ÿ�������ʵ�֣���
 */
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessNumber implements ActionListener{
	JLabel jl = new JLabel("��������һ���������");
	JTextField jtf = new JTextField(10);
	Random r = new Random();
	int number;
	int count = 1;
	public void actionPerformed(ActionEvent ae){
		String m = ae.getActionCommand();
		if("Random".equals(m)){
			jl.setText("����������������");
			number = r.nextInt(100);
		}else if("Guess".equals(m)){
			if("".equals(jtf.getText())|| null == jtf.getText()){
				jl.setText("����û��ѡ��һ���������");
			}else{
				int num = Integer.parseInt(jtf.getText());
				if(num==number && number!=0){
					jl.setText("������������������"+number+"!������"+count+"��");
				}else if(num>number && number!=0){
					jl.setText("���ˣ�������");
				}else{
					jl.setText("С��,������");
				}
				count++;
			}
			jtf.setText("");
			jtf.requestFocus();//�ı������
		}
	}
	
	public GuessNumber(){
		JFrame jf = new JFrame("GuessNumber");
		jf.setLayout(new GridLayout(2,1));
		JPanel jp1 = new JPanel();
		jp1.add(jl);
		JButton jb1 = new JButton("Random");
		jb1.addActionListener(this);
		jp1.add(jb1);
		jf.add(jp1);
		
		JPanel jp2 = new JPanel();
		jp2.add(jtf);
		
		JButton jb2=new JButton("Guess");
		jb2.addActionListener(this);
		//jtf.addActionListener(this);//�س�����
		jp2.add(jb2);
		
		jf.add(jp2);
		jf.setSize(400,300);
		//jf.pack();//��jvm�Զ����ڴ��ڴ�С
		jf.setLocation(300,200);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new GuessNumber();
	}
}
