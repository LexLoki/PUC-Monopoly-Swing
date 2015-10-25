import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;

public final class GlobalData {
	
	private static final GlobalData instance = new GlobalData();
	private Dimension screenSize;
	private Color[] playerColor;
	private File[] territoriesFile;
	private File[] sorteRevesFile;
	
	private GlobalData(){
		Toolkit tk = Toolkit.getDefaultToolkit();
		screenSize = tk.getScreenSize();
		
		//would be nice to get this data from a txt file or something
		//CHECK LATER
		playerColor = new Color[]{Color.blue, Color.black, Color.red, Color.green, Color.yellow};
		territoriesFile = GlobalData.listOfFiles("assets/territorios");
		sorteRevesFile = GlobalData.listOfFiles("assets/sorteReves");
	}
	
	public File[] getSpaceFiles(){
		return this.territoriesFile;
	}
	
	private static File[] listOfFiles(String path){
		System.out.println("here");
		File folder = new File(path);
		System.out.println(folder);
		File[] listOfFiles = folder.listFiles();
		System.out.println(listOfFiles);
		System.out.println(listOfFiles.length);
		List<File> lf = new ArrayList<File>(Arrays.asList(listOfFiles));
		lf.remove(0);
		listOfFiles = (File[]) lf.toArray(new File[0]);
		    for (int i = 0; i < listOfFiles.length; i++) {
		      System.out.println("Read: " + listOfFiles[i].getName());
		    }
		return listOfFiles;
	}
	
	public final Dimension getScreenSize(){
		return screenSize;
	}
	
	public static final GlobalData getInstance(){
		return instance;
	}
	
}
