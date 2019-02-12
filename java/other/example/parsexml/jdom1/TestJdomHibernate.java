package xml.jdom1;
import org.jdom.input.SAXBuilder;
import org.jdom.Document;
import org.jdom.Element;
import java.util.List;

//Jdom����xml�ļ���һ��ʵ�ַ�ʽ����jdom.jar
public class TestJdomHibernate {
	public static void main(String[] args) {
		try{
			SAXBuilder sax = new SAXBuilder();
			Document doc = sax.build("./day26/xml/jdom/hibernate.cfg.xml");
			Element el = doc.getRootElement();
			List<Element> listRoot = el.getChildren("session-factory");
			for(Element list:listRoot){
				List<Element> list1 = list.getChildren("property");
				for(Element listChild:list1){
					String name = listChild.getAttributeValue("name");
					//String text = listChild.getChildTextTrim("property");
					String text = listChild.getTextTrim();   //�����ǽ������������
					System.out.println("name="+name+",text="+text);
				}
				
				System.out.println("-----------------");
				List<Element> list2 = list.getChildren("mapping");
				for(Element listChild:list2){
					String resource = listChild.getAttributeValue("resource");
					String text = listChild.getTextTrim();
					System.out.println("resource="+resource+",text="+text);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
