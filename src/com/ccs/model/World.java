package com.ccs.model;

import java.util.Random;

public class World {
    private final int w, h;
    private final Tile[][] grid;
    private final Random rnd = new Random(1);

    public World(int w, int h) {
        this.w = w; this.h = h;
        grid = new Tile[h][w];
        generate();
    }

    public int getWidth(){ return w; }
    public int getHeight(){ return h; }

    public Tile get(int x, int y){ return grid[y][x]; }

    public void reset(){
        generate();
    }

    private void generate(){
        for(int y=0;y<h;y++){
            for(int x=0;x<w;x++){
                TileType type = TileType.PLAIN;
                // Mountains at top-left
                if (x < w/3 && y < h/3) type = TileType.MOUNTAIN;
                // River across the middle diagonally
                if (Math.abs(y - (x/2 + h/3)) <= 1) type = TileType.RIVER;
                // Ocean at far right
                if (x > w-3) type = TileType.OCEAN;
                // Beach near ocean
                if (x == w-4) type = TileType.BEACH;
                grid[y][x] = new Tile(type);
            }
        }
    }

    public void applyEnvironment(Environment e, UserInput u){
        for(int y=0;y<h;y++){
            for(int x=0;x<w;x++){
                Tile t = grid[y][x];
                // base water by tile type
                double water = (t.getType()==TileType.RIVER || t.getType()==TileType.OCEAN) ? 1.0 : 0.2;
                water += e.getRainfall()/300.0 - e.getTemperature()/200.0;
                t.setWater(water);

                // fertility better near river
                double fert = 1.0;
                if (t.getType()==TileType.PLAIN || t.getType()==TileType.FOREST) {
                    fert += 0.2 - e.getPollutionLevel()/200.0;
                }
                t.setFertility(fert);

                t.setPollution(Math.min(1.0, e.getPollutionLevel()/100.0));
            }
        }
    }
}
