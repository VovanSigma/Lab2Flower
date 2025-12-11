package ua.lab2.flower.parser;

import ua.lab2.flower.model.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class FlowerStaxParser {

    public List<Flower> parse(String filePath) throws Exception {
        List<Flower> flowers = new ArrayList<>();

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader =
                factory.createXMLStreamReader(new FileInputStream(filePath));

        String currentElement = null;

        String id = null;
        String name = null;
        Soil soil = null;
        String origin = null;

        String stemColor = null;
        String leafColor = null;
        int avgSize = 0;

        int temperature = 0;
        boolean lighting = false;
        int watering = 0;

        Multiplying multiplying = null;

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    currentElement = reader.getLocalName();

                    if ("flower".equals(currentElement)) {
                        id = reader.getAttributeValue(null, "id");
                        // обнуляємо поля
                        name = null;
                        soil = null;
                        origin = null;
                        stemColor = null;
                        leafColor = null;
                        avgSize = 0;
                        temperature = 0;
                        lighting = false;
                        watering = 0;
                        multiplying = null;
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }

                    if ("name".equals(currentElement)) {
                        name = text;
                    } else if ("soil".equals(currentElement)) {
                        soil = Soil.valueOf(text);
                    } else if ("origin".equals(currentElement)) {
                        origin = text;
                    } else if ("stemColor".equals(currentElement)) {
                        stemColor = text;
                    } else if ("leafColor".equals(currentElement)) {
                        leafColor = text;
                    } else if ("avgSize".equals(currentElement)) {
                        avgSize = Integer.parseInt(text);
                    } else if ("temperature".equals(currentElement)) {
                        temperature = Integer.parseInt(text);
                    } else if ("lighting".equals(currentElement)) {
                        lighting = Boolean.parseBoolean(text);
                    } else if ("watering".equals(currentElement)) {
                        watering = Integer.parseInt(text);
                    } else if ("multiplying".equals(currentElement)) {
                        multiplying = Multiplying.valueOf(text);
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    String endName = reader.getLocalName();
                    if ("flower".equals(endName)) {
                        VisualParameters vp =
                                new VisualParameters(stemColor, leafColor, avgSize);
                        GrowingTips gt =
                                new GrowingTips(temperature, lighting, watering);

                        Flower flower = new Flower(
                                id, name, soil, origin, vp, gt, multiplying
                        );
                        flowers.add(flower);
                    }
                    currentElement = null;
                    break;
            }
        }

        reader.close();
        return flowers;
    }
}
