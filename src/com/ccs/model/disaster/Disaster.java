package com.ccs.model.disaster;

import com.ccs.model.Environment;
import com.ccs.model.World;

public abstract class Disaster {
    public abstract void affect(Environment env, World world);
    public String name(){ return getClass().getSimpleName(); }
}
