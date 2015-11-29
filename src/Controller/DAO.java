package Controller;
import java.io.File;
import java.util.ArrayList;

import Model.SorteRevesModel;
import Model.TerritoryModel;
import Model.TerritoryModel.TerritoryType;
import Model.CompanyModel;
import Model.EffectModel;
import Model.BoardUnit;

public class DAO {
	
	private static final String territoriesPath = "assets/territorios";
	private static final String sorteRevesPath = "assets/sorteReves";
	
	//NOT WORKING NOW
	/*
	static PlaceModel[] getTerritories(){
		ArrayList<PlaceModel> places = new ArrayList<PlaceModel>();
		String[] files = DAO.listOfFilesName(territoriesPath);
		for(int i=0; i<files.length;i++){
			//places.add(new PlaceModel(files[i], i, "TesteName", 200, 
			//		50, 50, taxes));
		}
		return (PlaceModel[])places.toArray(new PlaceModel[0]);
	}
	*/
	
	public static BoardUnit[] getBoardSpaces(){
		BoardUnit[] p = new BoardUnit[]{
			addN(0),
			addT("curicica.jpg",1,"Curicica",220,200,200,
					new int[]{28,150,450,1000,1200,1400},
					TerritoryType.orange),
			addS(2),
			addC("empresa3.jpg",3,"Maquinas de caca-niquel",200,50,100),
			addT("leme.jpg",4,"Leme",300,200,200,
					new int[]{26,130,390,900,1100,1275},
					TerritoryType.red),
			addT("vilarcarioca.jpg",5,"Vilar carioca",220,150,150,
					new int[]{18,90,250,700,875,1050},
					TerritoryType.yellow),
			addS(6),
			addC("empresa2.jpg",7,"Botijao de gas",150,40,75),
			addT("morro18.jpg",8,"Morro do 18",140,100,100,
					new int[]{10,50,150,450,625,750},
					TerritoryType.purple),
			addN(9),
			addT("guapore.jpg",10,"Guapore",60,50,50,
					new int[]{4,20,60,180,320,450},
					TerritoryType.blue),
			addT("tanque.jpg",11,"Tanque",180,100,100,
					new int[]{14,70,200,550,750,950},
					TerritoryType.orange),
			addT("botafogo.jpg",12,"Botafogo",300,200,200,
					new int[]{26,130,390,900,1100,1275},
					TerritoryType.red),
			addS(13),
			addT("batan.jpg",14,"Batan",260,150,150,
					new int[]{22,110,330,800,975,1150},
					TerritoryType.green),
			addC("empresa6.jpg",15,"Transporte alternativo",200,50,100),
			addT("barbante.jpg",16,"Barbante",220,150,150,
					new int[]{18,90,250,700,875,1050},
					TerritoryType.yellow),
			addE(17, "Propinas e extorsoes", 200),
			addN(18),
			addC("empresa5.jpg",19,"Seguranca patrimonial",200,50,100),
			addT("gardeniaazul.jpg",20,"Gardenia azul",180,100,100,
					new int[]{14,70,200,550,750,950},
					TerritoryType.orange),
			addT("caixaagua.jpg",21,"Caixa dagua",140,100,100,
					new int[]{10,50,150,450,625,750},
					TerritoryType.purple),
			addT("kelsons.jpg",22,"Kelsons",120,50,50,
					new int[]{8,40,100,300,450,600},
					TerritoryType.green),
			addS(23),
			addC("empresa4.jpg",24,"Servico de moto-taxi",200,50,100),
			addT("quitungo.jpg",25,"Quitungo",60,50,50,
					new int[]{4,20,60,180,320,450},
					TerritoryType.blue),
			addT("riodaspedras.jpg",26,"Rio das pedras",260,150,150,
					new int[]{22,110,330,800,975,1150},
					TerritoryType.red),
			addN(27),
			addT("fuba.jpg",28,"Fuba",160,100,100,
					new int[]{12,60,180,500,700,900},
					TerritoryType.purple),
			addS(29),
			addT("carobinha.jpg",30,"Carobinha",240,150,150,
					new int[]{20,100,300,750,925,1100},
					TerritoryType.yellow),
			addC("empresa1.jpg",31,"Sinal de tv a gato",150,40,75),
			addT("fumace.jpg",32,"Fumace",100,50,50,
					new int[]{6,30,90,270,400,500},
					TerritoryType.green),
			addS(33),
			addE(34, "Custos de campanha", -200),
			addT("cidadealta.jpg",35,"Cidade alta",100,50,50,
					new int[]{6,30,90,270,400,500},
					TerritoryType.blue),
		};
		return p;
	}
	
	//static sorteRevesModel[] getSorteReves(){}
	
	private static TerritoryModel addT(String fileName, int id, String name,
			int buyValue, int houseValue, int hotelValue, int[] taxes, TerritoryType tp){
		return new TerritoryModel(territoriesPath+"/"+fileName,id,name,buyValue,houseValue,hotelValue,taxes,tp);
	}
	private static CompanyModel addC(String fileName, int id, String name, int buyValue,
			int multiplier, int hipoteca){
		return new CompanyModel(territoriesPath+"/"+fileName,id,name,buyValue,multiplier,hipoteca);
	}
	private static SorteRevesModel addS(int id){
		return new SorteRevesModel(id);
	}
	private static BoardUnit addN(int id){
		return new BoardUnit("Nothing", id);
	}
	private static EffectModel addE(int id, String name, int value){
		return new EffectModel(name, id, value);
	}
	
	private static String[] listOfFilesName(String path){
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> ls = new ArrayList<String>();
		//listOfFiles = (File[]) lf.toArray(new File[0]);
		    for (int i = 1; i < listOfFiles.length; i++) {
		      ls.add(path+"/"+listOfFiles[i].getName());
		    }
		return (String[])ls.toArray(new String[0]);
	}
}
