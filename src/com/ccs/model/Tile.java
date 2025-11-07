package com.ccs.model;

import java.awt.Color;

public class Tile {
    private TileType type;
    private double fertility = 1.0; // affects forest/green strength
    private double water = 0.0;     // 0..1
    private double pollution = 0.0; // 0..1

    public Tile(TileType type) {
        this.type = type;
    }

    public TileType getType() { return type; }
    public void setType(TileType type) { this.type = type; }

    public void setFertility(double f){ this.fertility = Math.max(0, Math.min(2, f)); }
    public void setWater(double w){ this.water = Math.max(0, Math.min(1, w)); }
    public void setPollution(double p){ this.pollution = Math.max(0, Math.min(1, p)); }

    public Color getRenderColor(Environment env){
        switch(type){
            case MOUNTAIN:
                return new Color(139, 108, 66);
            case RIVER:
            case OCEAN:
                int b = (int)(180 + 60*Math.min(1, water + env.getWaterAvailability()/100.0));
                return new Color(40, 80, b);
            case BEACH:
                return new Color(230, 210, 120);
            case FOREST:
            case PLAIN:
            default:
                // greener with more fertility & water, yellower with dryness, gray with pollution
                double dryness = Math.max(0, 1 - (water*0.6 + env.getRainfall()/200.0));
                int g = (int)Math.max(60, Math.min(255, 80 + 140*fertility - 80*dryness));
                int r = (int)Math.max(40, Math.min(220, 90 + 80*dryness + pollution*60));
                int p = (int)Math.max(0, Math.min(90, pollution*90));
                return new Color(r, g, 40 + p/2);
        }
    }
}
