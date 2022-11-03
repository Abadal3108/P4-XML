package EX_02;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class App {
    private static final String FILENAME = "staff.xml";

    public static void main(String[] args) {
        // Iniciamos la clase que nos permite iniciar la "fábrica" de documentos XML
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            // Opcional, pero se recomienda para evitar ataques XXE (XML External Entities)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // Iniciamos DocumentBuilder para analizar un archivo XML
            DocumentBuilder db = dbf.newDocumentBuilder();

            // Abrimos el fichero y lo analizamos con db.parse
            Document doc = db.parse(("https://www.xataka.com/sitemap_index.xml"));

            // La normalización es opcional, pero recomendado para XML mal formateados
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Elemento raiz :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            // Obtenemos todos los staffs en una lista de nodos que podemos recorrer
            NodeList list = doc.getElementsByTagName("sitemap");

            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Obtener el atributo de identificación del staff
                    String id = element.getAttribute("sitemap");

                    // Obtener el texto de los diferentes elementos.
                    String loc = element.getElementsByTagName("loc").item(0).getTextContent();
                    System.out.println("First link : " + loc);


                    Document doc_2 = db.parse((loc));

                    // La normalización es opcional, pero recomendado para XML mal formateados
                    // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
                    doc_2.getDocumentElement().normalize();

                    System.out.println("Elemento raiz :" + doc_2.getDocumentElement().getNodeName());
                    System.out.println("------");

                    // Obtenemos todos los staffs en una lista de nodos que podemos recorrer
                    NodeList list_2 = doc_2.getElementsByTagName("url");

                    for (int temp_2 = 0; temp_2 < list_2.getLength(); temp_2++) {
                        Node node_2 = list_2.item(temp_2);
                        if (node_2.getNodeType() == node_2.ELEMENT_NODE) {
                            Element element_2 = (Element) node_2;

                            // Obtener el atributo de identificación del staff
                            String id_2 = element_2.getAttribute("loc");

                            // Obtener el texto de los diferentes elementos.
                            String loc_2;

                            if (element_2.getElementsByTagName("loc").item(0) == null) {

                            } else {
                                loc_2 = element_2.getElementsByTagName("loc").item(0).getTextContent();
                                System.out.println("First link_2 : " + loc_2);
                            }
                        }
                    }
                    System.out.println("------");

                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
