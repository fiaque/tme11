/* CHENAOUI Louiza mono5*/
public class Guardien extends Contenu{
	private int pv;
	public Guardien (){
		super("Guardien", 1);
		this.pv= (int)(Math.random()*20);
	}
	public int getPv(){
		return this.pv;
	}

}
