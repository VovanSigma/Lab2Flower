package ua.lab2.flower.model;

import java.util.Comparator;
import java.util.List;

public class FlowerSorter {

    // Сортування за назвою
    public static void sortByName(List<Flower> flowers) {
        flowers.sort(Comparator.comparing(Flower::getName));
    }

    // Сортування за середнім розміром
    public static void sortByAvgSize(List<Flower> flowers) {
        flowers.sort(Comparator.comparingInt(
                f -> f.getVisualParameters().getAvgSize()
        ));
    }
}

