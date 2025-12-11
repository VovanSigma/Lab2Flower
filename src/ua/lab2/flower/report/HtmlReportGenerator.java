package ua.lab2.flower.report;

import ua.lab2.flower.model.Flower;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlReportGenerator {

    public static void generate(String fileName, List<Flower> flowers) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("<html><head><title>Flowers Report</title></head><body>");
            writer.write("<h1>Flowers Report</h1>");
            writer.write("<table border='1' cellpadding='5' cellspacing='0'>");

            writer.write("<tr><th>ID</th><th>Name</th><th>Soil</th><th>Origin</th>" +
                    "<th>Stem Color</th><th>Leaf Color</th><th>Avg Size</th>" +
                    "<th>Temperature</th><th>Lighting</th><th>Watering</th><th>Multiplying</th></tr>");

            for (Flower f : flowers) {
                writer.write("<tr>");
                writer.write("<td>" + f.getId() + "</td>");
                writer.write("<td>" + f.getName() + "</td>");
                writer.write("<td>" + f.getSoil() + "</td>");
                writer.write("<td>" + f.getOrigin() + "</td>");
                writer.write("<td>" + f.getVisualParameters().getStemColor() + "</td>");
                writer.write("<td>" + f.getVisualParameters().getLeafColor() + "</td>");
                writer.write("<td>" + f.getVisualParameters().getAvgSize() + "</td>");
                writer.write("<td>" + f.getGrowingTips().getTemperature() + "</td>");
                writer.write("<td>" + f.getGrowingTips().isLighting() + "</td>");
                writer.write("<td>" + f.getGrowingTips().getWatering() + "</td>");
                writer.write("<td>" + f.getMultiplying() + "</td>");
                writer.write("</tr>");
            }

            writer.write("</table></body></html>");

            System.out.println("HTML report created: " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
