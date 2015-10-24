import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.*;


public class MyFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyFrame(){
		
		super("My Title");
		Dimension screenSize = GlobalData.getInstance().getScreenSize();
		Dimension mySize = new Dimension(screenSize.width/2,screenSize.height/2);
		setBounds((screenSize.width-mySize.width)/2, (screenSize.height-mySize.height)/2, mySize.width, mySize.height);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		prepareBarGraph(new Dimension(mySize.width/2, mySize.height/2));
	}
	
	private void prepareImg(){
		//JTextField t = JTextField();
	}
	
	private void prepareBarGraph(Dimension size){
		JPanel graph = new JPanel();
		graph.setBounds(prepareBounds(size,this.getSize()));
		graph.setBackground(Color.white);
		graph.setVisible(true);
		//this.add(graph);
		this.getContentPane().add(graph);
		int[] values = {4,2,7,30,10,20};
		String[] names = {"A", "B", "C","A", "B", "C"};
		float div = (float)size.height/getBigger(values);
		float n = (float)values.length;
		float width = size.width/((3*n-1)/2);
		for(int i=0; i<n; i++){
			JPanel p = prepareBar(names[i], values[i], i, width, div, size.height);
			graph.add(p);
		}
	}
	
	private Rectangle prepareBounds(Dimension size, Dimension withSize){
		return new Rectangle((withSize.width-size.width)/2,(withSize.height-size.height)/2,size.width,size.height);
	}
	
	private JPanel prepareBar(String name, int value, int index, float width, float div, int totalH){
		JPanel p = new JPanel();
		int myH = (int)(value*div);
		p.setBounds((int)(index*(width*1.5)), totalH-myH, (int)width, myH);
		p.setBackground(Color.black);
		
		return p;
		
	}
	
	private int getBigger(int[] vals){
		int big=-1;
		for(int n:vals)
			if(n>big)
				big=n;
		return big;
	}
	
}