/* CHENAOUI Louiza mono5*/
import java.io.IOException;
public class TestSimulation{
    public static void main (String[] args){
        Simulation s1= new Simulation(4,5,4);
        s1.lancer(4);

        Simulation s2= new Simulation(5,5,5);
        s2.lancer(5);

        Simulation s3= new Simulation(10,4,6);
        s3.lancer(10);

        Simulation s4= new Simulation(10,10,10);
        s4.lancer(20);
        try{
            s1.simlog(4);
            s2.simlog(5);
        }catch (IOException i){
            System.out.println(" ERREUR "+i.getMessage());
        }
        
    }
}