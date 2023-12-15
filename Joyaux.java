/* CHENAOUI Louiza, mono5*/
public class Joyaux extends Contenu{
	private final int prix;

	public Joyaux (int qte) {
		super ("Joyaux",qte);
		this.prix=(int)((Math.random()*5000)+1);
	}
	public int getPrix(){
		return this.prix;
	}
	public String toString(){
		return (this.getQuantite()+ " Joyaux, de valeur "+ this.prix);
	}
	

}

