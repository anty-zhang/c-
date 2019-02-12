package day16;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Regedit {
	public static void regedit() {
		Scanner sc = new Scanner(System.in);
		System.out.println("input name,password,email;");
		String name = sc.next();
		String password = sc.next();
		String email = sc.next();

		String temp = Regedit.getFileInput(); //
		if (temp.endsWith(";")) {
			temp = temp.substring(0, temp.length() - 1);
		}

		if (temp == null || "".equals(temp)) { //
			User user = new User(name, password, email);
			Regedit.setFileOutput(user);
		} else {
			List<User> list = new ArrayList<User>(); //
			String[] s = temp.split(";");
			for (String ss : s) {
				String[] u = ss.split(",");
				User user = new User(u[0], u[1], u[2]); //
				list.add(user); //
			}

			boolean flag = false;
			for (User u : list) {
				if (u.getName().equals(name)) { //
					flag = true;
					System.out.println("�û������ظ�");
					break;
				}
			}

			if (!flag) {
				User user = new User(name, password, email);
				Regedit.setFileOutput(user);
				System.out.println("regedit sueccess!");
			}
		}
	}

	public static String getFileInput() {
		String temp = "";
		try {
			FileInputStream fis = new FileInputStream(
					"/home/tarena01/workspace/corejava/day16/regedit.txt");
			byte[] b = new byte[1024];
			while (true) {
				int num = fis.read(b);
				if (num == -1) {
					break;
				}
				temp = temp + new String(b, 0, num);
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	public static void setFileOutput(User user) {
		try {
			FileOutputStream fos = new FileOutputStream(
					"/home/tarena01/workspace/corejava/day16/regedit.txt", true);
			fos.write(user.toString().getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		Regedit.regedit();
	}
}

/*
 * ģ���û�ע��Ĺ��� Ҫ���û�ע����Ϣ���û��������룯email��ͨ�� Scanner¼�룬���һ���û����Ƿ���ڣ������ڣ����û�д���ļ�
 * ����FileInputStream����һ���ļ����õ������û�����ϢList��User����ѭ���Ƚ��û����� ע�⣺�Ƚ����ļ������������쳣
 * ��д��ʱ���趨���������׷�ӷ�ʽ��
 */