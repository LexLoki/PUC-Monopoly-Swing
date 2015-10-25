package sorteReves;
import javax.swing.ImageIcon;

public class SorteOuReves 
{
	public int efeito;
	public Boolean passeprisao = false;
	public Boolean irpartida = false;
	public Boolean irprisao = false;
	public Boolean recebeOutros = false;
	public ImageIcon imagem;
	
	public SorteOuReves(int i)
	{
		efeito = i;
	}
	
	public void setEfeito (int i)
	{
		efeito = i;
	}
	
	public void setPasse ()
	{
		passeprisao = true;
	}
	
	public void setIrPrisao()
	{
		irprisao = true;
	}
	
	public void setPartida()
	{
		irpartida = true;
	}
	
	public void setRecebeOutros()
	{
		recebeOutros = true;
	}
	
	public void setImagem (String path)
	{
		imagem = new ImageIcon(path);
	}
	
}
