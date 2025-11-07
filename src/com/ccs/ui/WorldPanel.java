package com.ccs.ui;

import javax.swing.*;
import java.awt.*;
import com.ccs.model.*;

public class WorldPanel extends JPanel {
    private final World world;
    private final Environment env;
    private static final int TILE = 24;

    public WorldPanel(World world, Environment env) {
        this.world = world;
        this.env = env;
        setPreferredSize(new Dimension(world.getWidth()*TILE, world.getHeight()*TILE));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < world.getHeight(); y++) {
            for (int x = 0; x < world.getWidth(); x++) {
                Tile t = world.get(x, y);
                g.setColor(t.getRenderColor(env));
                g.fillRect(x*TILE, y*TILE, TILE, TILE);
                g.setColor(new Color(0,0,0,40));
                g.drawRect(x*TILE, y*TILE, TILE, TILE);
            }
        }

        // Legend
        g.setColor(Color.WHITE);
        g.drawString("Legend: Green=Forest/Plain  Blue=Water  Brown=Mountain  Yellow=Dry", 10, getHeight()-10);
    }
}
