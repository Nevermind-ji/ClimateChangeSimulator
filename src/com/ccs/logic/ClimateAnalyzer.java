package com.ccs.logic;

import com.ccs.model.*;

public class ClimateAnalyzer {

    public void calculate(Environment env, World world, UserInput in){
        // Simple, explainable formulas (bounded)
        double rainfall = clamp(60 + in.getForestCover()*0.8 - in.getIndustries()*0.3 + rnd( -5, 5), 0, 400);
        double temperature = clamp(20 + in.getIndustries()*0.25 - in.getForestCover()*0.1 + in.getPopulation()*0.03,  -10, 55);
        double pollution = clamp(10 + in.getIndustries()*0.9 + in.getPopulation()*0.2 - in.getSustainability()*0.8, 0, 100);
        double waterAvail = clamp(40 + rainfall*0.2 - temperature*0.5 - pollution*0.2 + in.getSustainability()*0.5, 0, 100);
        double seaLevel = clamp((temperature - 25)*0.3 + pollution*0.02, -5, 50);

        env.setRainfall(rainfall);
        env.setTemperature(temperature);
        env.setPollutionLevel(pollution);
        env.setWaterAvailability(waterAvail);
        env.setSeaLevel(seaLevel);

        // Projection score: higher is better
        double score = 100 - (temperature-22>0 ? (temperature-22)*2 : 0) - pollution*0.5 + (rainfall>80 ? 10: -10) + in.getSustainability()*0.4;
        score = clamp(score, 0, 100);
        env.setProjectionScore(score);

        // Apply disaster if any
        if (in.getEvent()!=null){
            in.getEvent().affect(env, world);
        }
    }

    private double clamp(double v, double lo, double hi){
        return Math.max(lo, Math.min(hi, v));
    }

    private double rnd(double a, double b){
        return a + Math.random()*(b-a);
    }
}
