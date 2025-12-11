package ua.lab2.flower.model;

public class VisualParameters {

    private String stemColor;
    private String leafColor;
    private int avgSize; // см

    public VisualParameters(String stemColor, String leafColor, int avgSize) {
        this.stemColor = stemColor;
        this.leafColor = leafColor;
        this.avgSize = avgSize;
    }

    public String getStemColor() {
        return stemColor;
    }

    public void setStemColor(String stemColor) {
        this.stemColor = stemColor;
    }

    public String getLeafColor() {
        return leafColor;
    }

    public void setLeafColor(String leafColor) {
        this.leafColor = leafColor;
    }

    public int getAvgSize() {
        return avgSize;
    }

    public void setAvgSize(int avgSize) {
        this.avgSize = avgSize;
    }

    @Override
    public String toString() {
        return "VisualParameters{" +
                "stemColor='" + stemColor + '\'' +
                ", leafColor='" + leafColor + '\'' +
                ", avgSize=" + avgSize +
                '}';
    }
}

