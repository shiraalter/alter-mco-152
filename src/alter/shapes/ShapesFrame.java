package alter.shapes;

import javax.swing.*;
import java.awt.*;

public class ShapesFrame extends JFrame {

    private ShapeComponent shapeComponent;

    private JPanel panel;
    private JButton hamburger;
    private JButton watermelon;
    private JButton juice;
    private JButton hotDog;

    public ShapesFrame(){

        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("SHAPES");

        setLayout(new BorderLayout());
        shapeComponent = new ShapeComponent();
        add(shapeComponent, BorderLayout.CENTER);

        //add button panel
        panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Shape Buttons"));
        panel.setLayout(new GridLayout(4,1));

        //create and add buttons
        panel.add(hamburger = new JButton("Hamburger"));
        panel.add(watermelon = new JButton("Cookie"));
        panel.add(juice = new JButton("Juice Box"));
        panel.add(hotDog = new JButton("HotDog"));

        hamburger.addActionListener(actionEvent -> shapeComponent.setShape("Hamburger"));
        watermelon.addActionListener(actionEvent -> shapeComponent.setShape("Cookie"));
        juice.addActionListener(actionEvent -> shapeComponent.setShape("Juice"));
        hotDog.addActionListener(actionEvent -> shapeComponent.setShape("HotDog"));

        add(panel, BorderLayout.WEST);
        add(shapeComponent, BorderLayout.CENTER);


    }

    public static void main(String[] args)
    {
        new ShapesFrame().setVisible(true);
    }
}
