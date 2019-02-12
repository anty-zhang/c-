package xml.sax;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class TestSAX {
	public static void main(String[] args) {
		TestSAX sax = new TestSAX();
		sax.parse("./src/xml/sax/student.xml");
	}
	
	/**
	 * �����ĵ�
	 * 
	 * @param fileName
	 *            XML�ļ�������
	 */
	public void parse(String filePath){
		try {
			//ͨ���Ķ�������Ĺ���������һ���Ķ�������
			XMLReader parser = XMLReaderFactory.createXMLReader();
			//��������ǰע�����ݹ�����
			parser.setContentHandler(new XMLContentHandler());
			//��ʼ����
			parser.parse(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
