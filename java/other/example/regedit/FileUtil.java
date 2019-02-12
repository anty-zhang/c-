package example.regedit;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class FileUtil {
	public static List<User> getUser() throws IOException{
		List<User> list = new ArrayList<User>();
		File file = new File("./src/example/regedit/regedit.xml");
		System.out.println(file.getPath());
		if(!file.exists()) file.createNewFile(); //����ļ������ڣ��򴴽�
		System.out.println(file.exists());
		FileInputStream fis = new FileInputStream("./src/example/regedit/regedit.xml");
		InputStreamReader isr = new InputStreamReader(fis); //һ��һ�еĶ�
		BufferedReader br = new BufferedReader(isr);
		while(true){
			String temp = br.readLine();
			if(temp == null) break;     //�ļ������գ�ѭ������
			String[] mes = temp.split(",");
			if(mes.length!=3){          //���鲻Ϊ3����Ϊ���Ϸ���Ϣ
				continue;
			}
			User user = new User(mes[0],mes[1],mes[2]);
			list.add(user);
		}
		br.close(); //�رհ�װ��
		return list;
	}
	
	public static void setUser(String mes) throws IOException{
		FileOutputStream fos = new FileOutputStream("./src/example/regedit/regedit.xml",true);//true
		PrintStream ps = new PrintStream(fos);   //һ��һ�е�д
		ps.println(mes);
		ps.close();    //�رհ�װ��
	}
}
