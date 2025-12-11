package ua.lab2.flower.model;

public class GrowingTips {

    private int temperature;   // °C
    private boolean lighting;  // true потребує багато світла
    private int watering;      // полив, разів на тиждень

    public GrowingTips(int temperature, boolean lighting, int watering) {
        this.temperature = temperature;
        this.lighting = lighting;
        this.watering = watering;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isLighting() {
        return lighting;
    }

    public void setLighting(boolean lighting) {
        this.lighting = lighting;
    }

    public int getWatering() {
        return watering;
    }

    public void setWatering(int watering) {
        this.watering = watering;
    }

    @Override
    public String toString() {
        return "GrowingTips{" +
                "temperature=" + temperature +
                ", lighting=" + lighting +
                ", watering=" + watering +
                '}';
    }
}
