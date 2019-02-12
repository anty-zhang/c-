package xml.jdom1;

import java.io.FileOutputStream;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
//Jdom����xml�ļ���һ��ʵ�ַ�ʽ����jdom.jar
public class TestJdom {
	public static void main(String[] args) {
		try {
			//1.��SAXBuilder��xml�ļ����ص��ڴ�
			SAXBuilder sax = new SAXBuilder();
			//2.��Document��ü��ص��ڴ��е�xml�ļ��Ķ���
			Document doc = sax.build("./src/xml/jdom1/student.xml");
			//3.��ø��ڵ�
			Element el = doc.getRootElement();
			//4.��ø��ڵ�
			List<Element> childList = el.getChildren("student");
			for(Element student:childList){
				//5.��getChildTextTrim("")��ñ�ǵ�����
				System.out.println("Student name--->"+student.getChildTextTrim("name"));
				System.out.println("Student sex--->"+student.getChildTextTrim("sex"));
				
				//��ӡ����ֵ
				System.out.println("student id ---- >"+student.getAttributeValue("id"));
				
				//�޸�����ֵ
				student.setAttribute("id", "10");
				
				
				//ȡ�ڵ㣬���޸�����
				Element ele = student.getChild("name");
				ele.setText("zhangqiang");
				
				List<Element> studentChildList = student.getChildren("lesson");
				
				for(Element lession:studentChildList){
					System.out.println("lessonName--->"+lession.getChildTextTrim("lessonName"));
					System.out.println("lessonScore--->"+lession.getChildTextTrim("lessonScore"));
				}
				System.out.println();
			}
			
			//����޸ĺ�Ľ��
			XMLOutputter out = new XMLOutputter();
			out.output(doc, new FileOutputStream("./src/xml/jdom1/student.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
