package ua.lab2.flower.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ua.lab2.flower.model.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FlowerDomParser {

    public List<Flower> parse(String filePath) throws Exception {
        List<Flower> flowers = new ArrayList<>();

        // Створюємо DOM-документ
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(filePath));
        document.getDocumentElement().normalize();

        // Знаходимо всі елементи <flower>
        NodeList flowerNodes = document.getElementsByTagName("flower");

        for (int i = 0; i < flowerNodes.getLength(); i++) {
            Element flowerElement = (Element) flowerNodes.item(i);

            String id = flowerElement.getAttribute("id");
            String name = getTextContent(flowerElement, "name");
            Soil soil = Soil.valueOf(getTextContent(flowerElement, "soil"));
            String origin = getTextContent(flowerElement, "origin");

            // visualParameters
            Element visualElement = (Element) flowerElement.getElementsByTagName("visualParameters").item(0);
            String stemColor = getTextContent(visualElement, "stemColor");
            String leafColor = getTextContent(visualElement, "leafColor");
            int avgSize = Integer.parseInt(getTextContent(visualElement, "avgSize"));
            VisualParameters visualParameters = new VisualParameters(stemColor, leafColor, avgSize);

            // growingTips
            Element tipsElement = (Element) flowerElement.getElementsByTagName("growingTips").item(0);
            int temperature = Integer.parseInt(getTextContent(tipsElement, "temperature"));
            boolean lighting = Boolean.parseBoolean(getTextContent(tipsElement, "lighting"));
            int watering = Integer.parseInt(getTextContent(tipsElement, "watering"));
            GrowingTips growingTips = new GrowingTips(temperature, lighting, watering);

            // multiplying
            Multiplying multiplying = Multiplying.valueOf(getTextContent(flowerElement, "multiplying"));

            Flower flower = new Flower(
                    id,
                    name,
                    soil,
                    origin,
                    visualParameters,
                    growingTips,
                    multiplying
            );

            flowers.add(flower);
        }

        return flowers;
    }

    private String getTextContent(Element parent, String tagName) {
        return parent.getElementsByTagName(tagName).item(0).getTextContent().trim();
    }
}
