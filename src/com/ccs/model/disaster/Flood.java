package com.ccs.model.disaster;

import com.ccs.model.*;

public class Flood extends Disaster {
    @Override
    public void affect(Environment env, World world) {
        env.setWaterAvailability(Math.min(100, env.getWaterAvailability()+20));
        env.setPollutionLevel(Math.min(100, env.getPollutionLevel()+5));
        env.setRainfall(env.getRainfall()+30);
    }
}
