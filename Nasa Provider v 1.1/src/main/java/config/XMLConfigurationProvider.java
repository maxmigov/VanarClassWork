package config;

import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class XMLConfigurationProvider {

    public static String getValue(String keyName, String fileName) throws IOException {

        ClassLoader classLoader = XMLConfigurationProvider.class.getClassLoader();
        String value = null;
        try (InputStream input = classLoader.getResourceAsStream(fileName)){
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(input);
            doc.getDocumentElement().normalize();
            Node node = doc.getElementsByTagName(keyName).item(0);
            Element elementValue = (Element) node;
            value = elementValue.getAttribute("value");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return value;
    }

}