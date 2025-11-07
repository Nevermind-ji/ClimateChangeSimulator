package com.ccs.model;

public class Environment {
    private double rainfall;         // mm
    private double temperature;      // C
    private double waterAvailability; // 0..100
    private double pollutionLevel;   // 0..100
    private double seaLevel;         // cm relative
    private double projectionScore;  // 0..100

    public void reset(){
        rainfall = 120;
        temperature = 26;
        waterAvailability = 50;
        pollutionLevel = 20;
        seaLevel = 0;
        projectionScore = 60;
    }

    public Environment(){ reset(); }

    public double getRainfall(){ return rainfall; }
    public double getTemperature(){ return temperature; }
    public double getWaterAvailability(){ return waterAvailability; }
    public double getPollutionLevel(){ return pollutionLevel; }
    public double getSeaLevel(){ return seaLevel; }
    public double getProjectionScore(){ return projectionScore; }

    public void setRainfall(double v){ rainfall = v; }
    public void setTemperature(double v){ temperature = v; }
    public void setWaterAvailability(double v){ waterAvailability = v; }
    public void setPollutionLevel(double v){ pollutionLevel = v; }
    public void setSeaLevel(double v){ seaLevel = v; }
    public void setProjectionScore(double v){ projectionScore = v; }

    public String report(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Rainfall: %.1f mm\n", rainfall));
        sb.append(String.format("Avg Temperature: %.1f °C\n", temperature));
        sb.append(String.format("Water Availability: %.0f/100\n", waterAvailability));
        sb.append(String.format("Pollution Level: %.0f/100\n", pollutionLevel));
        sb.append(String.format("Sea Level Change: %.1f cm\n", seaLevel));
        sb.append(String.format("Projection Score: %.0f/100\n", projectionScore));
        return sb.toString();
    }
}
