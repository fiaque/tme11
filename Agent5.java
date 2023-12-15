/*CHENAOUI Louiza mono5*/
import java.util.ArrayList;




public class Agent5{
    private int posX;
    private int posY;
    private ArrayList<Joyaux> sac;
    private Grille grille;
    private int nbCont=0;

    public Agent5(Grille grille){
        this.posX= (int)((Math.random()*grille.nbLignes) % grille.nbLignes);
        this.posY= (int)((Math.random()*grille.nbLignes) % grille.nbColonnes);
        this.grille=grille;
        this.sac= new ArrayList<Joyaux>();
    }
    public String toString(){
        return ("L'agent est a la position ("+posX+","+posY+")");
    }
    public int getX(){
        return this.posX;}
    public int getY(){
        return this.posY;
    }

    public void seDeplacer(int newx, int newy) throws CoordonneesIncorrectesException, CaseNonPleineException{
        if (! grille.sontValides(newx, newy)){
            throw new CoordonneesIncorrectesException("Les coordonnées fournies ne sont pas sur la grille");
        }
        this.posX=newx; 
        this.posY=newy;
        //System.out.println("L'agent se déplace vers la case ("+ newx+" , "+newy+")");
        if (! grille.caseEstVide(newx,newy)){
            Contenu obj= grille.getCase(newx,newy);
            if ( obj instanceof Joyaux){
                sac.add((Joyaux) obj);
                this.nbCont++;
                grille.videCase(newx,newy);
            
                //System.out.println("Il y avait "+obj.toString()+ "sur la case, l'agent les collecte");
            }
            if (obj instanceof Guardien){
		        sac.clear();
                //System.out.println("L'agent a renconté un gardien, il a perdu le contenu de son sac!");
	    }
        }
	}
    public void seDeplacer(int newx, int newy, int f) throws CoordonneesIncorrectesException, CaseNonPleineException{
	if (! grille.sontValides(newx, newy)){
            throw new CoordonneesIncorrectesException("Les coordonnées fournies ne sont pas sur la grille");
    }else{
        this.posX=newx; 
        this.posY=newy;
        //System.out.println("L'agent se déplace vers la case ("+ newx+" , "+newy+")");

        if (! grille.caseEstVide(newx,newy)){
            Contenu obj= grille.getCase(newx,newy);
            if ( obj instanceof Joyaux){
                sac.add((Joyaux) obj);
                this.nbCont++;
                grille.videCase(newx,newy);
            
                System.out.println("Il y avait "+obj.toString()+ "sur la case, l'agent les collecte");
            }
            if (obj instanceof Guardien){
                Guardien g= (Guardien)obj;
                if (g.getPv() <= f){
                    grille.videCase(newx,newy);

                    //System.out.println("L'agent a renconté un gardien moins fort que lui, le guardien est vaincu!");
                }else{
			        sac.clear();
                    
                    //System.out.println("L'agent a renconté un gardien plus for que lui, il a perdu le contenu de son sac!");
                }
            }
        }
    }
    }
    public int fortune(){
        int fort=0;
        for (int i=0; i<sac.size();i++){
		    Joyaux item=sac.get(i);
		    fort+= item.getPrix();
        }
        return fort;
    }
    public String contenuSac(){
        if(sac.isEmpty()){ return "le sac est vide"; }

	    String res="Le sac de l'agent contient\n";
	    for (int i=0; i<sac.size(); i++){
		    Joyaux item= sac.get(i);
		    res+= ("\t" +item.toString()+"\n");
	    }
	    return res;
    }


}
