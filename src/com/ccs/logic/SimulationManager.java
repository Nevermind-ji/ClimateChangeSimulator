package com.ccs.logic;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import com.ccs.model.*;

public class SimulationManager {
    private final World world;
    private final Environment env;
    private final UserInput input;
    private final ClimateAnalyzer analyzer;
    private final Consumer<Void> onTick;
    private Timer timer;

    public SimulationManager(World w, Environment e, UserInput in, ClimateAnalyzer a, Runnable onTick){
        this.world = w; this.env = e; this.input = in; this.analyzer = a;
        this.onTick = (v) -> onTick.run();
        timer = new Timer(700, new ActionListener(){
            public void actionPerformed(ActionEvent evt){ tick(); }
        });
    }

    public void setAuto(boolean running){
        if (running) timer.start();
        else timer.stop();
    }

    public void stop(){ timer.stop(); }

    public void tick(){
        analyzer.calculate(env, world, input);
        world.applyEnvironment(env, input);
        onTick.accept(null);
    }
}
