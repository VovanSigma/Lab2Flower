package ua.lab2.flower.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.lab2.flower.model.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FlowerSaxParser {

    public List<Flower> parse(String filePath) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        FlowerHandler handler = new FlowerHandler();
        saxParser.parse(new File(filePath), handler);

        return handler.getFlowers();
    }

    private static class FlowerHandler extends DefaultHandler {

        private List<Flower> flowers = new ArrayList<>();

        private StringBuilder content = new StringBuilder();

        private String id;
        private String name;
        private Soil soil;
        private String origin;

        private String stemColor;
        private String leafColor;
        private int avgSize;

        private int temperature;
        private boolean lighting;
        private int watering;

        private Multiplying multiplying;

        private boolean inVisualParameters = false;
        private boolean inGrowingTips = false;

        public List<Flower> getFlowers() {
            return flowers;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
                throws SAXException {

            content.setLength(0); // очищаємо буфер тексту

            if ("flower".equals(qName)) {
                id = attributes.getValue("id");
            } else if ("visualParameters".equals(qName)) {
                inVisualParameters = true;
            } else if ("growingTips".equals(qName)) {
                inGrowingTips = true;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            content.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {

            String text = content.toString().trim();

            switch (qName) {
                case "name":
                    name = text;
                    break;
                case "soil":
                    soil = Soil.valueOf(text);
                    break;
                case "origin":
                    origin = text;
                    break;

                case "stemColor":
                    stemColor = text;
                    break;
                case "leafColor":
                    leafColor = text;
                    break;
                case "avgSize":
                    if (!text.isEmpty()) {
                        avgSize = Integer.parseInt(text);
                    }
                    break;

                case "temperature":
                    if (!text.isEmpty()) {
                        temperature = Integer.parseInt(text);
                    }
                    break;
                case "lighting":
                    if (!text.isEmpty()) {
                        lighting = Boolean.parseBoolean(text);
                    }
                    break;
                case "watering":
                    if (!text.isEmpty()) {
                        watering = Integer.parseInt(text);
                    }
                    break;

                case "multiplying":
                    multiplying = Multiplying.valueOf(text);
                    break;

                case "visualParameters":
                    inVisualParameters = false;
                    break;

                case "growingTips":
                    inGrowingTips = false;
                    break;

                case "flower":
                    VisualParameters vp =
                            new VisualParameters(stemColor, leafColor, avgSize);
                    GrowingTips gt =
                            new GrowingTips(temperature, lighting, watering);

                    Flower flower = new Flower(
                            id, name, soil, origin, vp, gt, multiplying
                    );
                    flowers.add(flower);
                    break;
            }
        }
    }
}
