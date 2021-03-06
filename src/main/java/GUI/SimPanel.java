package GUI;

import TrafficSim.Block;
import TrafficSim.Road;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

public class SimPanel extends JPanel implements ActionListener {

    private JPanel content_panel;
    public String status = "stop";
    public Canvas canvas = new Canvas();

    //city attributes
    public Hashtable<Point, Block> map = new Hashtable<>(); //map of the road structure
    public Hashtable<Point, Road> road_map = new Hashtable<>(); //map of road used to spawn cars

    //UI constructor
    public void construct(){
        //configure layout
        setLayout(new BorderLayout());
        JPanel bottom_panel = new JPanel(new GridLayout(1,0));
        bottom_panel.setBackground(COLOR.totally_transparent);
        setBackground(COLOR.totally_transparent);

        //play button
        default_button play_button = new default_button("Play");
        play_button.addActionListener(this);
        play_button.setActionCommand("play");

        bottom_panel.add(play_button);

        //pause button
        default_button pause_button = new default_button("Pause");
        pause_button.addActionListener(this);
        pause_button.setActionCommand("pause");

        bottom_panel.add(pause_button);

        //stop button
        default_button stop_button = new default_button("Stop");
        stop_button.addActionListener(this);
        stop_button.setActionCommand("stop");

        bottom_panel.add(stop_button);

        //content panel
        content_panel = new JPanel(new BorderLayout());
        content_panel.setOpaque(false);

        //canvas
        canvas.mode = 1;
        content_panel.add(canvas);

        //add to main panel
        add(content_panel, BorderLayout.CENTER);
        add(bottom_panel, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch(cmd){
            case "play":
                status = "play";
                break;
            case "pause":
                break;
            case "stop":
                status = "stop";
                break;
        }
    }

    //method to get road blocks to map
    public void road_to_map(){
        for(ArrayList<Point> road : canvas.saved_roads.keySet()){
            Point direction = canvas.saved_roads.get(road);
            for(Point pos : road) {
                Road new_road = new Road(direction,pos,3);

                //add to road_map
                road_map.put(pos, new_road);
                //add to map
                map.put(pos, new_road);
            }
        }
    }
}
