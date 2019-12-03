/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficlight;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TrafficLight extends JFrame implements ActionListener {
    private static JButton btn1 = null;
    private static JButton btn2 = null;
    private static JButton btn3 = null;
    
    private static TrafficSignal green = new TrafficSignal(Color.green);
    private static TrafficSignal yellow = new TrafficSignal(Color.yellow);
    private static TrafficSignal red = new TrafficSignal(Color.red);
    public final static int ONE_SECOND = 1000;
    public JSlider sliderGreen = new JSlider(0, 10, 0);
    public JLabel greenTimer = new JLabel();
  
    public JSlider sliderYellow = new JSlider(0, 10, 0);
    public JLabel yellowTimer = new JLabel();
  
    public JSlider sliderRed = new JSlider(0, 10, 0);
    public JLabel redTimer = new JLabel();
    
    public boolean isGreen = false;
       public boolean isYellow = false;
          public boolean isRed = false;
      int secondsPassed =0;
    
    Timer myTimer = new Timer();
    
    TimerTask task = new TimerTask(){
        public void run(){
            secondsPassed++;
            System.out.println("seconds passed: " + secondsPassed);
            
            if (secondsPassed == sliderRed.getValue() && isRed==true){
            yellow.turnOn(false);            
            green.turnOn(true);
            red.turnOn(false);
            isRed=false;
            isGreen=true;
             secondsPassed=0;
            }
            else if (secondsPassed == sliderGreen.getValue() && isGreen==true){
            yellow.turnOn(true);            
            green.turnOn(false);
            red.turnOn(false);
            isGreen=false;
            isYellow=true;
            
         secondsPassed=0;
          
            }
            else if (secondsPassed == sliderYellow.getValue() && isYellow==true){
                
                yellow.turnOn(false);            
                green.turnOn(false);
                red.turnOn(true);
                isYellow=false;
                isRed=true;
          secondsPassed =0;
            }
          //  else if (secondsPassed ==10){
          //    secondsPassed =0;
                
            //}
         
        }
    };
    
    public void start() {
        myTimer.scheduleAtFixedRate(task, 1000, 1000);
    }
    public TrafficLight(){
        super("Traffic Light");
        getContentPane().setLayout(new GridLayout(2, 1));
        btn1 = new JButton("Start");
       // btn2 = new JButton("Yellow");
        btn3 = new JButton("Stop");
        btn1.addActionListener(this);
      //  btn2.addActionListener(this);
        btn3.addActionListener(this);        
        
        green.turnOn(false);
        yellow.turnOn(false);
        red.turnOn(false);
        
        JPanel lights = new JPanel( new FlowLayout() );
        lights.add( green );
        lights.add( yellow );
        lights.add( red );
        JPanel btnPane = new JPanel(new FlowLayout());
        btnPane.add(btn1);
     
        btnPane.add(btn3);
        sliderGreen.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent ce) {
         // System.out.println("greenLabel" + sliderGreen.getValue() + "yellowLabel" + sliderYellow.getValue() + "redLable" + sliderRed.getValue());
            repaint();
         }
      });
        sliderYellow.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent ce) {
          //System.out.println("greenLabel" + sliderGreen.getValue() + "yellowLabel" + sliderYellow.getValue() + "redLable" + sliderRed.getValue());
            repaint();
         }
      });
        sliderRed.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent ce) {
          //System.out.println("greenLabel" + sliderGreen.getValue() + "yellowLabel" + sliderYellow.getValue() + "redLable" + sliderRed.getValue());
            repaint();
         }
      });
        sliderGreen.setMajorTickSpacing(10);
        sliderGreen.setMinorTickSpacing(1);
        sliderGreen.setPaintTicks(true);
        sliderGreen.setPaintLabels(true);
        btnPane.add(sliderGreen); 
        
   
        
        sliderYellow.setMajorTickSpacing(10);
        sliderYellow.setMinorTickSpacing(1);
        sliderYellow.setPaintTicks(true);
        sliderYellow.setPaintLabels(true);
        btnPane.add(sliderYellow); 
        
   
        
       sliderRed.setMajorTickSpacing(10);
        sliderRed.setMinorTickSpacing(1);
        sliderRed.setPaintTicks(true);
        sliderRed.setPaintLabels(true);
        btnPane.add(sliderRed); 
        
      
        btnPane.add(greenTimer);
          btnPane.add(yellowTimer);
            btnPane.add(redTimer);
        getContentPane().add(lights);
        getContentPane().add(btnPane);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);            
    }

    
    
    public static void main(String[] args){
        TrafficLight tl = new TrafficLight();        
        tl.setVisible(true);
    }
  
   
    
    public void actionPerformed(ActionEvent e){        
        if (e.getSource() == btn1){
            green.turnOn(true);            
           // yellow.turnOn(false);
           // red.turnOn(false);
           isGreen=true;
          start();
         System.out.println("greenLabel" + sliderGreen.getValue() + "yellowLabel" + sliderYellow.getValue() + "redLable" + sliderRed.getValue());
        } else if (e.getSource() == btn3){
           System.exit(0);
        }
        
    }

    
   
}    
    
class TrafficSignal extends JPanel {
    
    Color on;
    int radius = 75;
    int border = 10;
    boolean active;
    
    TrafficSignal(Color color){
        on = color;
        active = true;
    }
    
    public void turnOn(boolean a) {
        active = a;
        
        //set repaint thread timer from slider 
        repaint();        
    }
    
    public Dimension getPreferredSize(){
        int size = (radius+border)*2;
        return new Dimension( size, size );
    }
    
    public void paintComponent(Graphics g){
       g.setColor( Color.black );
        g.fillRect(0,0,getWidth(),getHeight());
     
        if (active){
            
            g.setColor( on );
        } else {
            
            g.setColor( on.darker().darker().darker().darker().darker() );
       }
        g.fillOval( border,border,2*radius,2*radius );

    }
}
