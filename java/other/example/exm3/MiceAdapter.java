package day19.high;
//������ԶԸ������޸�
public class MiceAdapter extends Mice{
	private Meat meat;
	public MiceAdapter(Meat meat){
		this.meat=meat;
	}
	@Override
	public void sellMice(){
		meat.sellMeat();
	}
	
	public static void main(String[] args) {
		Mice m = new Mice();
		m.sellMice();
		Meat mt = new Meat();
		Mice ma =new MiceAdapter(mt);//ǿ����
		ma.sellMice();
	}
}
