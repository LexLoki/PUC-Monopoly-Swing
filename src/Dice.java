import java.awt.*;
import javax.swing.*;

public class Dice extends JComponent {
	
	private static final int SPOT_DIAM = 9;  
    
    
    private int _faceValue;    
    
    public Dice(Dimension size) {
    	this.setSize(size);
        //setPreferredSize(size);//new Dimension(60,60));
        roll();
    }
    
   
    public int roll() {
        int val = (int)(6*Math.random() + 1);   // Range 1-6
        setValue(val);
        return val;
    }
    
    
    public int getValue() {
        return _faceValue;
    }
    
   
    public void setValue(int spots) {
        _faceValue = spots;
        repaint();   
    }
    
    
    @Override public void paintComponent(Graphics g) {
        int w = getWidth();  
        int h = getHeight();
        
        
        Graphics2D g2 = (Graphics2D)g;  
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
       
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, w, h);
        g2.setColor(Color.BLACK);
        
        g2.drawRect(0, 0, w-1, h-1);  
        
        switch (_faceValue) {
            case 1:
                drawSpot(g2, w/2, h/2);
                break;
            case 3:
                drawSpot(g2, w/2, h/2);
                
            case 2:
                drawSpot(g2, w/4, h/4);
                drawSpot(g2, 3*w/4, 3*h/4);
                break;
            case 5:
                drawSpot(g2, w/2, h/2);
                
            case 4:
                drawSpot(g2, w/4, h/4);
                drawSpot(g2, 3*w/4, 3*h/4);
                drawSpot(g2, 3*w/4, h/4);
                drawSpot(g2, w/4, 3*h/4);
                break;
            case 6:
                drawSpot(g2, w/4, h/4);
                drawSpot(g2, 3*w/4, 3*h/4);
                drawSpot(g2, 3*w/4, h/4);
                drawSpot(g2, w/4, 3*h/4);
                drawSpot(g2, w/4, h/2);
                drawSpot(g2, 3*w/4, h/2);
                break;
        }
    }
    
    
    private void drawSpot(Graphics2D g2, int x, int y) {
        g2.fillOval(x-SPOT_DIAM/2, y-SPOT_DIAM/2, SPOT_DIAM, SPOT_DIAM);
    }
}

