import java.awt.Toolkit;
import java.awt.Dimension;

public final class GlobalData {
	
	private static final GlobalData instance = new GlobalData();
	private Dimension screenSize;
	
	private GlobalData(){
		Toolkit tk = Toolkit.getDefaultToolkit();
		screenSize = tk.getScreenSize();
	}
	
	public final Dimension getScreenSize(){
		return screenSize;
	}
	
	public static final GlobalData getInstance(){
		return instance;
	}
	
}
