import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Pièce {
   ArrayList<Porte> porte = new ArrayList<Porte>();
   private String nom;
   private int etage;
   private String [] visualisation;
   
   Scanner in = new Scanner(System.in);
   Random rand = new Random();
   private boolean princesse = false;
   private Item item;
   private Ennemie res;
   
   
    public Pièce(String nom,int etage){
            this.etage = etage;
            this.nom = nom;
        }
    
    public String getNom(){
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
   
    int getNiveau(){
        return this.etage;
    }
    
    int getNombreDePorte(){
        return porte.size();
    }
    
    boolean getPrincesse(){
        return princesse;
    }
    void addPrincesse(){
        princesse = true;
    }
    
    void addVisu(String [] v){
        visualisation = v;
    }
    
    String [] getVisu(){
        return visualisation;
    }
    
    int getNbPièce(){
        return porte.size() ;
    }
    
    Item creatItem(Playeur p){
    int alea = rand.nextInt(3);
        if(alea == 0){
            item = new Epee();
        }else if(alea == 1){
          if(p.potionEstAuMax()){
           alea = rand.nextInt(2);
           if(alea == 0){
              item = new Epee(); 
           }else {
              item = new Protection();  
           }
            }else{
           item = new Potion();                
            }
        }else{
            item = new Protection();
        }
        return item ;
    }
    
    
    Item creatItem(int i){
    int alea = rand.nextInt(3);
        if(alea == 0){
            item = new Epee(i);
        }else if(alea == 1){
            item = new Potion(i);
        }else{
            item = new Protection(i);
        }
        return item ;
    }

 boolean ennemie(){
     boolean result = false;
     if(rand.nextInt(20) > 10){
         result = true;
         Ennemie enemy = new Ennemie(this.getNom());
         res = enemy;        
     }
     return result ;
 }
 
 void Combat(String n){
     boolean fini = false;
     
     Ennemie enemy = new Ennemie(this.getNom());
     
 }
 
 boolean CombatFini(Playeur p){
    if(res.mort() || p.mort()){
        return true;
    }else {
        return false;
    }
 }
 
 int getQuestion(){
     return res.getRep();
 }
 
 Ennemie getEnnemie(){
     return res;
 }
  /*   
  void foundEnnemie(Playeur p,Chateau h) {
    if (rand.nextInt(20) > 10){
            Ennemie enemy = new Ennemie(this.getNom());
            System.out.println("un ennemie vient d'apparaitre !\n Ennemie : " + enemy.getNom()); 
            boolean fuite = false;
            int rep ;
                do{
                    System.out.println( enemy.getNom() + " à " + enemy.vie + " points de vie");
                    System.out.println("il vous reste " + p.getVie() + " points de vie");
                    System.out.println("\t 1. attaque\n\t 2. inventaire\n\t 3. fuir");
                    System.out.print("réponse : ");
                    rep = in.nextInt();
                
                     if(rep==1){
                         if(enemy.getRep()){
                        enemy.getDamage(p.getAttaque());
                         }else{
                        p.getDamage(enemy.getAttaque());
                         }
                    }else if(rep==2){
                        p.useItem(); 
                    }else if(rep==3){
                        fuite=true;
                        p.getDamage(enemy.getAttaque()/2);
                    }
                }while(!enemy.mort() && !p.mort() && !fuite);
                
                if(enemy.mort()){
                    if(h.indice.get(h.endroit - 8).getPossible()){
                      System.out.println(h.indice.get(h.endroit - 8).getIndice());
                    }
                this.creatItem(enemy.getDifficulté()).foundItem(p);     
                }

            
        }    
   }*/
}

