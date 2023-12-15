/* CHENAOUI Louiza, mono5*/
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class Simulation{
    private Grille grille;
    private Agent5 agent;
    private Contenu[] tab;
    private static int nbSim;
    private final int id;
    private static int nbfile=0;

    public Simulation(int nbLignes, int nbColonnes,int m){
        /* on suppose que m est inferieur au nombre de cases*/
        nbSim++;
        this.id=nbSim;
        this.grille= new Grille(nbLignes, nbColonnes);
        this.agent=new Agent5(grille);
        this.tab= new Contenu[m];
        for (int j=0; j<m; j++){
            try{
                double p=Math.random();
                int x= (int)(Math.random()*nbLignes);
                int y=(int)(Math.random()*nbColonnes);
                if( grille.caseEstVide(x,y)){
                    if(p<=0.5){
                    tab[j]=new Guardien();
                    grille.setCase(x,y, tab[j]);
                    System.out.println("On a placé un guardien a la position ("+x+" , "+y+")");
                    }else{
                    tab[j]=new Joyaux((int)(Math.random()*5+1));
                    grille.setCase(x,y, tab[j]);
                    System.out.println("On a placé "+tab[j].toString()+" à la position ("+x+" , "+y+")");
                }
                }
                
            }catch(CoordonneesIncorrectesException co){
                System.out.println("Le placement a échoué"+co.getMessage());
                j--;
            }
            
        }

    }
    public void lancer(int nbEtapes){
        System.out.println("Début de la simulation "+id+",\nInformations sur la grille"+this.grille);
        grille.affiche(2);
        System.out.println(agent.toString());
        System.out.println(agent.contenuSac());
        for (int i=0; i<nbEtapes; i++){    
            System.out.println("Etape "+i+":");            
            int dx= (int)(Math.random()*3)-1;
            int dy= (int)(Math.random()*3)-1;
            try{

                double p= Math.random();
                if(p<=0.3){
                    agent.seDeplacer(agent.getX()+dx, agent.getY()+dy, (int)(Math.random()*91+10));
                    System.out.println(agent+"\n"+agent.contenuSac());
                }else{
                    agent.seDeplacer(agent.getX()+dx, agent.getY()+dy);
                    System.out.println(agent+"\n"+agent.contenuSac());
                }
            }catch( CoordonneesIncorrectesException co){
                System.out.println("L'agent n'a pas pu se deplacer pour cette étape"+co.getMessage());
                System.out.println("("+(agent.getX()+dx)+","+(agent.getY()+dy)+")");
            }catch(CaseNonPleineException ca){
                System.out.println("La case était vide.");
            }
            System.out.println("______");
        
        }
        System.out.println("C'est la fin de la simulation");
        grille.affiche(2);
        System.out.println(agent.contenuSac());
        System.out.println("La fortune de l'agent sélève à "+agent.fortune());


    }

    public void simlog(int nbEtapes) throws IOException{
        //écrit la simulation dans un fichier plutot que de l'afficher sur le terminal//
        File sortie= new File("sim"+nbfile+".txt");
        nbfile++;
        FileOutputStream out = null;
        DataOutputStream ostream= null ;
        try{
            out = new FileOutputStream (sortie, true) ;
            ostream = new DataOutputStream (out) ;
            ostream.writeBytes("CHENAOUI Louiza, mono5\n");
            ostream.writeBytes("Début de la simulation "+id+",\nInformations sur la grille"+this.grille+"\n");
            ostream.writeBytes(agent.toString()+"\n");
            ostream.writeBytes(agent.contenuSac()+"\n");
            for (int i=0; i<nbEtapes; i++){       
                ostream.writeBytes("Etape "+i+":\n");         
                int dx= (int)(Math.random()*3)-1;
                int dy= (int)(Math.random()*3)-1;
                try{

                    double p= Math.random();
                    if(p<=0.3){
                        agent.seDeplacer(agent.getX()+dx, agent.getY()+dy, (int)(Math.random()*91+10));
                    }else{
                        agent.seDeplacer(agent.getX()+dx, agent.getY()+dy);
                    }
                    ostream.writeBytes("L'agent s'est déplacé vers la case ("+agent.getX()+","+agent.getY()+")\n");
                    ostream.writeBytes(agent.contenuSac()+"\n");
                }catch( CoordonneesIncorrectesException co){
                    ostream.writeBytes("L'agent n'a pas pu se deplacer pour cette étape"+co.getMessage()+"\n");
                    ostream.writeBytes("("+(agent.getX()+dx)+","+(agent.getY()+dy)+")\n");
                }catch(CaseNonPleineException ca){
                    ostream.writeBytes("La case était vide.\n");
                }
                ostream.writeBytes("____\n");

            }
            ostream.writeBytes("C'est la fin de la simulation\n");
            ostream.writeBytes(agent.contenuSac()+"\n");
            ostream.writeBytes("La fortune de l'agent s'éleve à: "+agent.fortune()+"\n");
            }catch (IOException i){
                System.out.println("Erreur"+i.getMessage()+"\n");
            }finally{
                if(ostream!= null){
                    ostream.close();
                }
            }
        


    }
}