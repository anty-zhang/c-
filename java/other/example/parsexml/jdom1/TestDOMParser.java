package xml.jdom1;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

//Jdom����xml�ļ��ĵڶ��з�ʽ����sun�ṩ��API
public class TestDOMParser {
	public static void main(String[] args) {
		TestDOMParser tp = new TestDOMParser();
		tp.parseXMLFile("./day26/xml/jdom/student.xml");
	}

	public void parseXMLFile(String fileName) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			// �����ļ�������
			Document doc = db.parse(fileName);
			// ���Documet,��xml��������ƥ��
			// Document doc = parser.getDocument();

			// ��ø��ڵ�StudentInfo
			Element elmtInfo = doc.getDocumentElement();

			// �õ�����student�ڵ�,�ڵ㼯��
			NodeList nlStudent = elmtInfo.getElementsByTagName("student");

			System.out.println("XML�ļ���ʼ����");

			// ѭ�����ÿһ��ѧ���ɼ�

			for (int i = 0; i < nlStudent.getLength(); i++) {

				// ��ǰstudentԪ��
				Element elmtStudent = (Element) nlStudent.item(i);
				// Name/sex/lesson�ڵ��嵥
				NodeList nlCurrent = elmtStudent.getElementsByTagName("name");
				System.out.println("����:"
						+ nlCurrent.item(0).getFirstChild().getNodeValue());

				nlCurrent = elmtStudent.getElementsByTagName("sex");
				System.out.println("�Ա�:"
						+ nlCurrent.item(0).getFirstChild().getNodeValue());
				// ȡ��Lesson�ڵ�,����һ��,��Ҫѭ��
				nlCurrent = elmtStudent.getElementsByTagName("lesson");

				for (int j = 0; j < nlCurrent.getLength(); j++) {
					// Lesson���Ԫ�صĶ�Ӧ
					Element elmtLesson = (Element) nlCurrent.item(j);
					NodeList nlLesson = elmtLesson
							.getElementsByTagName("lessonName");
					System.out.print(nlLesson.item(0).getFirstChild()
							.getNodeValue());
					System.out.print(":");
					nlLesson = elmtLesson.getElementsByTagName("lessonScore");
					System.out.print(nlLesson.item(0).getFirstChild()
							.getNodeValue());
					System.out.println();
				}

				System.out.println("------------------------------------");
			}

			System.out.println("XML�ļ���������");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
