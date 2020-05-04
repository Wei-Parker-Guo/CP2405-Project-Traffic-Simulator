package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//class representing menu entry point of the simulator
public class Menu extends JFrame implements ActionListener{
    //default button style
    static class default_button extends JButton{
        private Color hoverBackgroundColor = COLOR.ideal_dark_highlight;
        private Color pressedBackgroundColor = Color.cyan.darker();

        public default_button() {
            this(null);
        }

        public default_button(String text) {
            super(text);
            super.setContentAreaFilled(false);
            setFocusPainted(false);
            setBorder(null);
            setOpaque(false);
            setBorderPainted(false);
            setFont(FONT.NORMAL_TEXT);
            setBackground(COLOR.ideal_dark);
            setForeground(Color.WHITE);
            setHoverBackgroundColor(hoverBackgroundColor);
            setPressedBackgroundColor(pressedBackgroundColor);
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isPressed()) {
                g.setColor(pressedBackgroundColor);
            } else if (getModel().isRollover()) {
                g.setColor(hoverBackgroundColor);
            } else {
                g.setColor(getBackground());
            }
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }

        @Override
        public void setContentAreaFilled(boolean b) {
        }

        public Color getHoverBackgroundColor() {
            return hoverBackgroundColor;
        }

        public void setHoverBackgroundColor(Color hoverBackgroundColor) {
            this.hoverBackgroundColor = hoverBackgroundColor;
        }

        public Color getPressedBackgroundColor() {
            return pressedBackgroundColor;
        }

        public void setPressedBackgroundColor(Color pressedBackgroundColor) {
            this.pressedBackgroundColor = pressedBackgroundColor;
        }
    }


    //attributes
    private JFrame menu_frame;
    private int mode = 0; //mode: 0 - editing; 1 - simulating
    private JButton edit_button;
    private JButton sim_button;

    //entry point
    public void construct(){
        //setting up a fullscreen frame
        menu_frame = new JFrame("Traffic Simulator Menu");
        menu_frame.setUndecorated(true);
        menu_frame.setBackground(COLOR.totally_transparent);

        //setting up static elements
        JPanel main_panel = new JPanel();
        JPanel menu_panel = new JPanel(); //nested menu panel to limit size
        main_panel.setLayout(new BorderLayout());
        main_panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        main_panel.setBackground(COLOR.totally_transparent);
        menu_panel.setLayout(new GridLayout(0,1));
        menu_panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,30));
        main_panel.add(menu_panel, BorderLayout.LINE_START);

        //map panel
        JPanel placeholder = new JPanel();
        placeholder.setBackground(COLOR.totally_transparent);

        JPanel map_panel = new JPanel();
        map_panel.setBackground(COLOR.twenty_transparent);
        main_panel.add(map_panel, BorderLayout.CENTER);

        JLabel title_label = new JLabel("Traffic Simulator");
        title_label.setFont(FONT.TITLE);
        title_label.setForeground(Color.white);
        title_label.setHorizontalAlignment(JLabel.CENTER);
        title_label.setVerticalAlignment(JLabel.CENTER);

        menu_panel.add(title_label);
        menu_panel.setBackground(COLOR.totally_transparent);

        //setting up buttons

        //mode button
        edit_button = new default_button("Map Edit");
        sim_button = new default_button("Simulate");
        edit_button.addActionListener(this);
        edit_button.setActionCommand("edit");
        sim_button.addActionListener(this);
        sim_button.setActionCommand("sim");
        edit_button.setBackground(Color.orange);
        JPanel mode_button_container = new JPanel();
        mode_button_container.setLayout(new GridLayout(2,0));
        mode_button_container.add(edit_button);
        mode_button_container.add(sim_button);
        mode_button_container.setBackground(Color.gray);

        menu_panel.add(mode_button_container);

        menu_panel.add(placeholder); //separator

        //new button
        JButton new_button = new default_button("New");
        new_button.addActionListener(this);
        new_button.setActionCommand("new");

        menu_panel.add(new_button);

        //open button
        JButton open_button = new default_button("Open");
        open_button.addActionListener(this);
        open_button.setActionCommand("open");

        menu_panel.add(open_button);

        //save button
        JButton save_button = new default_button("Save");
        save_button.addActionListener(this);
        save_button.setActionCommand("save");

        menu_panel.add(save_button);

        //exit button
        JButton exit_button = new default_button("Exit");
        exit_button.addActionListener(this);
        exit_button.setActionCommand("exit");

        menu_panel.add(exit_button);

        menu_frame.add(main_panel);
        menu_frame.setSize(1920, 1080);
        menu_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menu_frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "exit":
                System.exit(0);
            case "edit":
                mode = 0;
                edit_button.setBackground(Color.orange);
                sim_button.setBackground(COLOR.ideal_dark);
                break;
            case "sim":
                mode = 1;
                sim_button.setBackground(Color.orange);
                edit_button.setBackground(COLOR.ideal_dark);
                break;
        }
    }
}