package com.ccs.model.disaster;

import com.ccs.model.*;

public class Wildfire extends Disaster {
    @Override
    public void affect(Environment env, World world) {
        env.setPollutionLevel(Math.min(100, env.getPollutionLevel()+25));
        env.setTemperature(env.getTemperature()+1.5);
        env.setRainfall(Math.max(0, env.getRainfall()-10));
    }
}
