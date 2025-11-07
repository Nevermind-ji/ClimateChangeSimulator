package com.ccs.model.disaster;

import com.ccs.model.*;

public class Drought extends Disaster {
    @Override
    public void affect(Environment env, World world) {
        env.setRainfall(Math.max(0, env.getRainfall()-40));
        env.setWaterAvailability(Math.max(0, env.getWaterAvailability()-30));
        env.setTemperature(env.getTemperature()+2);
    }
}
