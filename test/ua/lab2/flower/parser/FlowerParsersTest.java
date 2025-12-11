package ua.lab2.flower.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.lab2.flower.model.Flower;
import ua.lab2.flower.model.FlowerSorter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlowerParsersTest {

    private static final String XML_PATH = "greenhouse.xml";
    private static final String XSD_PATH = "greenhouse.xsd";

    private FlowerDomParser domParser;
    private FlowerSaxParser saxParser;
    private FlowerStaxParser staxParser;

    @BeforeEach
    void setUp() {
        domParser = new FlowerDomParser();
        saxParser = new FlowerSaxParser();
        staxParser = new FlowerStaxParser();
    }

    @Test
    void xmlShouldBeValidAgainstXsd() {
        boolean valid = XmlValidator.validate(XML_PATH, XSD_PATH);
        assertTrue(valid, "XML має бути валідним згідно XSD");
    }

    @Test
    void domParserShouldReturnThreeFlowersAndFirstIsRose() throws Exception {
        List<Flower> flowers = domParser.parse(XML_PATH);

        assertEquals(3, flowers.size(), "Має бути 3 квітки");
        Flower first = flowers.get(0);
        assertEquals("f1", first.getId());
        assertEquals("Rose", first.getName());
    }

    @Test
    void saxParserShouldReturnSameNumberOfFlowersAsDom() throws Exception {
        List<Flower> domFlowers = domParser.parse(XML_PATH);
        List<Flower> saxFlowers = saxParser.parse(XML_PATH);

        assertEquals(domFlowers.size(), saxFlowers.size(),
                "SAX має повернути стільки ж квіток, як DOM");
    }

    @Test
    void staxParserShouldReturnSameNumberOfFlowersAsDom() throws Exception {
        List<Flower> domFlowers = domParser.parse(XML_PATH);
        List<Flower> staxFlowers = staxParser.parse(XML_PATH);

        assertEquals(domFlowers.size(), staxFlowers.size(),
                "StAX має повернути стільки ж квіток, як DOM");
    }

    @Test
    void sorterShouldSortByName() throws Exception {
        List<Flower> flowers = domParser.parse(XML_PATH);
        FlowerSorter.sortByName(flowers);

        assertEquals("Orchid", flowers.get(0).getName());
        assertEquals("Rose",   flowers.get(1).getName());
        assertEquals("Tulip",  flowers.get(2).getName());
    }
}
