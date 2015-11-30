import java.awt.Dimension;

import javax.swing.JPanel;

import Controller.GlobalData;

class ResizeUtils {
	
	static void setPositionCenter(JPanel p, int x, int y){
		setPositionAnchor(p, 0.5, 0.5, x, y);
	}
	static void setPositionUpperRight(JPanel p, int x, int y){
		setPositionAnchor(p, 1., 0., x, y);
	}
	static void setPositionBottomLeft(JPanel p, int x, int y){
		setPositionAnchor(p, 0., 1., x, y);
	}
	
	static private void setPositionAnchor(JPanel p, double ax, double ay, int x, int y){
		Dimension s = p.getSize();
		p.setLocation(x-(int)(ax*s.width), y-(int)(ay*s.height));
	}
	
	static Dimension getBestFrameFor(Dimension d){
		return getBestFrameFor(d.width, d.height);
	}
	
	static Dimension getBestFrameFor(int width, int height){
		
		Dimension size = GlobalData.getInstance().getScreenSize();
		size = new Dimension(size.width*9/10, size.height*85/100);
		float sx = (float)(size.width) / width;
		float sy = (float)(size.height) / height;
		float s = sx > sy ? sy : sx;
		return new Dimension((int)(s*width), (int)(s*height));
	}
	
}
