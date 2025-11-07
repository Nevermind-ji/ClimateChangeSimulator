package com.ccs.ui;

import javax.swing.*;
import java.awt.*;
import com.ccs.model.*;
import com.ccs.logic.*;
import com.ccs.model.disaster.*;

public class MainFrame extends JFrame {
    private final World world;
    private final Environment environment;
    private final UserInput userInput;
    private final ClimateAnalyzer analyzer;
    private final SimulationManager sim;
    private final WorldPanel worldPanel;
    private final JTextArea output;
    private final JComboBox<String> disasterBox;
    private final JSlider popSlider, forestSlider, indSlider, sustainSlider;
    private final JCheckBox autoPlay;

    public MainFrame() {
        super("Climate Change Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);

        world = new World(30, 20);
        environment = new Environment();
        userInput = new UserInput();
        analyzer = new ClimateAnalyzer();
        sim = new SimulationManager(world, environment, userInput, analyzer, this::refresh);

        worldPanel = new WorldPanel(world, environment);
        output = new JTextArea(6, 50);
        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);

        // Controls
        popSlider = slider(0, 100, 40, "Population");
        forestSlider = slider(0, 100, 60, "Forest Cover");
        indSlider = slider(0, 100, 30, "Industries");
        sustainSlider = slider(0, 100, 50, "Sustainability");
        disasterBox = new JComboBox<>(new String[]{"None","Flood","Drought","Wildfire"});
        JButton tickBtn = new JButton("Run Tick");
        JButton resetBtn = new JButton("Reset");
        autoPlay = new JCheckBox("Auto Play");
        autoPlay.addActionListener(e -> sim.setAuto(autoPlay.isSelected()));

        tickBtn.addActionListener(e -> sim.tick());
        resetBtn.addActionListener(e -> {
            sim.stop();
            autoPlay.setSelected(false);
            world.reset();
            environment.reset();
            refresh();
        });

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        left.add(labeled(popSlider, "Population"));
        left.add(labeled(forestSlider, "Forest Cover"));
        left.add(labeled(indSlider, "Industries"));
        left.add(labeled(sustainSlider, "Sustainability"));
        left.add(new JLabel("Event:"));
        left.add(disasterBox);
        left.add(Box.createVerticalStrut(10));
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.add(tickBtn);
        buttons.add(resetBtn);
        buttons.add(autoPlay);
        left.add(buttons);

        JPanel right = new JPanel(new BorderLayout());
        right.add(worldPanel, BorderLayout.CENTER);
        right.add(new JScrollPane(output), BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(left, BorderLayout.WEST);
        add(right, BorderLayout.CENTER);

        refresh();
    }

    private JPanel labeled(JSlider s, String name) {
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel(name), BorderLayout.NORTH);
        p.add(s, BorderLayout.CENTER);
        return p;
    }

    private JSlider slider(int min, int max, int val, String name) {
        JSlider s = new JSlider(min, max, val);
        s.setMajorTickSpacing(25);
        s.setMinorTickSpacing(5);
        s.setPaintTicks(true);
        s.setPaintLabels(true);
        s.addChangeListener(e -> refresh());
        return s;
    }

    private Disaster selectedDisaster() {
        String sel = (String) disasterBox.getSelectedItem();
        switch (sel) {
            case "Flood": return new Flood();
            case "Drought": return new Drought();
            case "Wildfire": return new Wildfire();
            default: return null;
        }
    }

    public void refresh() {
        userInput.setPopulation(popSlider.getValue());
        userInput.setForestCover(forestSlider.getValue());
        userInput.setIndustries(indSlider.getValue());
        userInput.setSustainability(sustainSlider.getValue());
        userInput.setEvent(selectedDisaster());

        analyzer.calculate(environment, world, userInput);
        world.applyEnvironment(environment, userInput);

        output.setText(environment.report());
        worldPanel.repaint();
    }
}
