package socket.chatroom;

import java.net.*;
import java.io.*;

public class Client_socket extends Thread {
	private DataInputStream in;

	private DataOutputStream out;

	private String clientname;

	private History history_info;

	public Client_socket(Socket tt, History history_info) {
		this.history_info = history_info;
		// ���server_socket����ϵ
		try {
			InputStream is = tt.getInputStream();
			in = new DataInputStream(is);

			OutputStream ops = tt.getOutputStream();
			out = new DataOutputStream(ops);

		} catch (Exception e) {
			System.out.println(1);
			System.out.println(e);
		}

	}

	public void run() {
		String ww = "";
		try {
			while (true) {
				ww = in.readUTF();
				// �ͻ���ע��
				if (ww.equals("<1.1>")) {
					clientname = in.readUTF();
					history_info.setWord("server: ��ӭ" + clientname + "����������");
				}
				// client Ҫ��õ����е���Ϣ
				else if (ww.equals("<2.1>")) {
					out.writeUTF(history_info.getWord());
				}
				// clientҪ����
				else if (ww.equals("<2.2>")) {
					history_info.setWord(clientname + "˵��" + in.readUTF());
				}
				// client�뿪
				else if (ww.equals("<3.2>")) {
					history_info.setWord(clientname + "�뿪�������ҡ�");
				}
			}
		} catch (Exception e) {
			System.out.println(2);
			System.out.println(e);
		}
	}

};