package ua.lab2.flower.app;

import ua.lab2.flower.model.Flower;
import ua.lab2.flower.parser.*;
import ua.lab2.flower.model.FlowerSorter;
import ua.lab2.flower.report.HtmlReportGenerator;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        String xmlPath = "greenhouse.xml";
        String xsdPath = "greenhouse.xsd";

        // Валідація
        boolean valid = XmlValidator.validate(xmlPath, xsdPath);
        System.out.println("XML is valid: " + valid);
        if (!valid) {
            System.out.println("Завершення програми через невалідний XML.");
            return;
        }

        try {
            System.out.println("\n DOM parser ");
            FlowerDomParser domParser = new FlowerDomParser();
            List<Flower> domFlowers = domParser.parse(xmlPath);
            domFlowers.forEach(System.out::println);

            System.out.println("\n DOM parser (sorted by name)");
            FlowerSorter.sortByName(domFlowers);
            domFlowers.forEach(System.out::println);

            System.out.println("\nDOM parser (sorted by avgSize) ");
            FlowerSorter.sortByAvgSize(domFlowers);
            domFlowers.forEach(System.out::println);

            // Генерація HTML-звіту з DOM-списку
            HtmlReportGenerator.generate("flowers.html", domFlowers);
            System.out.println("\nHTML report generated:flowers.html");



            System.out.println("\nSAX parser ");
            FlowerSaxParser saxParser = new FlowerSaxParser();
            List<Flower> saxFlowers = saxParser.parse(xmlPath);
            saxFlowers.forEach(System.out::println);

            System.out.println("\nStAX parser ");
            FlowerStaxParser staxParser = new FlowerStaxParser();
            List<Flower> staxFlowers = staxParser.parse(xmlPath);
            staxFlowers.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



