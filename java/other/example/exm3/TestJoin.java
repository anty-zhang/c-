package day19.high;

public class TestJoin implements Runnable{
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
			try{
				Thread.sleep(400);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		TestJoin tj1 = new TestJoin();
		Thread t1= new Thread(tj1);
		
		//t1.join();  //û��Ч��
		t1.start();
		
		TestJoin tj2 = new TestJoin();
		Thread t2= new Thread(tj2);
		//t1.join();  //t1ִ�����ִ��t2��main�Ľ���
		t2.start();
		
		t1.join();   //t1��t2֮���н���,Ȼ���ִ��main
		for(int i=0;i<10;i++){
			System.out.println("main:"+i);
			try{
				Thread.sleep(500);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
