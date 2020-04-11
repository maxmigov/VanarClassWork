package tests;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class NASADataProviderTest {
    @Test
    void checkConnection() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String urlFromXML = null;
        String apiKeyFromXML = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("src/main/resources/test.xml");
            doc.getDocumentElement().normalize();

            NodeList nodeList1 = doc.getElementsByTagName("resources");
            for (int i = 0; i < nodeList1.getLength(); i++) {
                Node node = nodeList1.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    apiKeyFromXML = eElement.getElementsByTagName("apikey").item(0).getTextContent();
                    System.out.println("Parsed the first var from XML: ENDPOINT\n----->");
                }
            }

            NodeList nodeList2 = doc.getElementsByTagName("resources");
            for (int i = 0; i < nodeList2.getLength(); i++) {
                Node node = nodeList2.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    urlFromXML = eElement.getElementsByTagName("endpoint").item(0).getTextContent();
                    System.out.println("Parsed the second var from XML: APIKEY\n----->");
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // *** 1. connect to NASA API

        URL oracle = null;
        try {
            oracle = new URL(urlFromXML + apiKeyFromXML);
        } catch (MalformedURLException e) {
            fail("No internet connection or wrong URL address");
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(oracle.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connection established\n----->");

        // *** 2. read data

        String stringData = "";
        String inputLine = null;
        while (true) {
            try {
                if (!((inputLine = in.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            stringData += inputLine;
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Read data from URL\n----->");
        // *** 3. parse JSON

        JSONObject data = new JSONObject(stringData);
        System.out.println("Start to parse data from JSON\n----->");

        Integer d = data.getJSONObject("page")
                .getInt("size");
        System.out.println("Page size of JSON: " + d +"\n----->");
        System.out.println("JSON file parsed");

    }

}

/*
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i) instanceof Element) {
                    System.out.println(((Element) nodeList.item(i)).getTagName());
                }
            }
            System.out.println("--------------------");

             */
            /*
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i) instanceof Element) {
                    String value = "";
                    if (!nodeList.item(i).getTextContent().trim().isEmpty() && !((Text)nodeList.item(i).getFirstChild()).getData().trim().isEmpty() && !((Text)nodeList.item(i).getFirstChild()).getData().trim().equals("\n")){
                        Text text = (Text) nodeList.item(i).getFirstChild();
                        value += "=" + text.getData().trim();
                    }
                    System.out.println(nodeList.item(i).getNodeName() + value);
                }
            }

             */

/*public void getKey() {
	 try {
	 ClassLoader classLoader = getClass().getClassLoader();
	 File file = new File(classLoader.getResource("xml_data/token.xml").getPath());
	 DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
	 DocumentBuilder builder = factory.newDocumentBuilder();
	 Document doc = builder.parse(file);
//	 System.out.println(doc.getDocumentElement().getNodeName());
	 NodeList nList = doc.getElementsByTagName("keys");
	 	 for (int i = 0; i<=nList.getLength();i++) {
		 Node nNode = nList.item(i);
//		 System.out.println(nNode.getNodeName());
		 if (nNode.getNodeType()==Node.ELEMENT_NODE) {
			 Element eElement = (Element) nNode;
			String finKey = eElement.getElementsByTagName("key").item(0).getTextContent();
			 System.out.println(finKey);
		 }
	 }
	 }
	 catch(Exception e) {}
 }
*/

