package com.ccs.model;

import com.ccs.model.disaster.Disaster;

public class UserInput {
    private int population;    // 0..100
    private int forestCover;   // 0..100
    private int industries;    // 0..100
    private int sustainability;// 0..100
    private Disaster event;    // nullable

    public int getPopulation(){ return population; }
    public int getForestCover(){ return forestCover; }
    public int getIndustries(){ return industries; }
    public int getSustainability(){ return sustainability; }
    public Disaster getEvent(){ return event; }

    public void setPopulation(int v){ population = v; }
    public void setForestCover(int v){ forestCover = v; }
    public void setIndustries(int v){ industries = v; }
    public void setSustainability(int v){ sustainability = v; }
    public void setEvent(Disaster d){ event = d; }
}
