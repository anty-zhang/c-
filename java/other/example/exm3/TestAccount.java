package day19.high;

public class TestAccount implements Runnable {
	private double money = 2000;

	Object obj = new Object();
	public void run() {
		//synchronized (this) {
		synchronized (obj) {  //ͬ�����ƣ�ʹ��ͬһ���󼴿ɣ�
			double temp = money;//ȡ����
			temp = temp - 800;  //ȡǮ
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			money = temp;   //д�ص����ݿ�
		}
	}

	public static void main(String[] args) {
		TestAccount ta = new TestAccount();
		Thread t1 = new Thread(ta);
		t1.start();

		Thread t2 = new Thread(ta);
		t2.start();
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(ta.money);
	}
}
