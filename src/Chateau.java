import java.util.ArrayList;
import java.util.Random;

public class Chateau {
    ArrayList<Pièce> piece = new ArrayList<Pièce>();
    ArrayList<Indices> indice = new ArrayList<Indices>();
    
    Random rand = new Random();
    int endroit = rand.nextInt(4) + 8;
    int indiceId =0;
    
    public Chateau(){
        
        Indices celluleId = new Indices(
                "elle ce trouve dans un endroit sombre ",
                "dans les profondeurs de la terre",
                "derrière des barreaux");
        
        Indices troneId = new Indices(
                "elle est sur un pied d'estale",
                "parmis les rois elle ce trouve",
                "le centre du château");
        
        Indices tourNId = new Indices(
                "en hauteur tu dois chercher",
                "elle est près du sorcier blanc ",
                "elle ce trouve à l'inverse du plein soleil");
        
        Indices tourSId = new Indices(
                "en hauteur tu dois chercher",
                "elle est près du sorcier bleu",
                "elle se trouve plein soleil");
    
        indice.add(celluleId);
        indice.add(troneId);
        indice.add(tourNId);
        indice.add(tourSId);
        
        //sortie
        Pièce sortie = new Pièce("sortie",99);
        
        //rez de chaussé ( niveau 0 )
        Pièce pont = new Pièce("pont",0);
        Pièce entree = new Pièce("entree",0);
        Pièce bal = new Pièce("bal",0);
        Pièce jardin = new Pièce("jardin",0);
        Pièce cuisine = new Pièce("cuisine",0);
        
        
        //première étage ( niveau 1 )
        Pièce rempart = new Pièce ("rempart",1);
        Pièce trone = new Pièce ("trone",1);
        Pièce tourNord = new Pièce ("tourNord",1);
        Pièce tourSud = new Pièce ("tourSud",1);
        
        // étages inférieurs ( niveau -1 & niveau -2)
        Pièce arme = new Pièce ("arme",-1);
        Pièce egout = new Pièce("égout",-2);
        Pièce cellule = new Pièce ("cellule",-2);
        
        //0
        piece.add(pont);
        pont.porte.add(new Porte("salle d'entrée","entree",'f'));
        pont.porte.add(new Porte("égout","égout",'d'));
        pont.porte.add(new Porte("sortie","sortie",'b'));
        
        //1
        piece.add(entree);
        entree.porte.add(new Porte("porte central","bal",'f'));
        entree.porte.add(new Porte("escalier vers le haut","trône",'u'));
        entree.porte.add(new Porte("escalier vers le bas ","arme",'d'));
        entree.porte.add(new Porte("porte d'entrée","pont",'b'));
        
        //2
        piece.add(bal);
        bal.porte.add(new Porte("porte vers l'extérieur","jardin",'f'));
        bal.porte.add(new Porte("porte à droite","cuisine",'r'));
        bal.porte.add(new Porte("porte central","entree",'b'));
        
        //3
        piece.add(jardin);
        jardin.porte.add(new Porte("escalier vers le haut","rempart",'u'));
        jardin.porte.add(new Porte("porte vers le bal","bal",'b'));
        
        //4
        piece.add(cuisine);  
        cuisine.porte.add(new Porte("escalier vers le haut","trône",'u'));
        cuisine.porte.add(new Porte("escalier vers le bas ","arme",'d'));
        cuisine.porte.add(new Porte("porte de gauche","bal",'l'));
        
        //5
        piece.add(egout);
        egout.porte.add(new Porte("porte de cellule","cellule",'f'));
        egout.porte.add(new Porte("égout vers le pont","pont",'u'));
        
        //6
        piece.add(arme);
        arme.porte.add(new Porte("escalier vers l'entrée","entree",'u'));
        arme.porte.add(new Porte("escalier vers les remparts","rempart",'u'));
        arme.porte.add(new Porte("escalier vers le cachot","cellule",'d'));
        
        //7
        piece.add(rempart);
        rempart.porte.add(new Porte("chemin vers tour sud","tourSud",'l'));
        rempart.porte.add(new Porte("chemin vers tour nord","tourNord",'r'));
        rempart.porte.add(new Porte("escalier vers la salle d'arme","arme",'d'));
        rempart.porte.add(new Porte("porte vers la salle du trône","trone",'b'));
        
        //8
        piece.add(cellule);
        cellule.porte.add(new Porte("escalier vers salle d'arme","arme",'u'));
        cellule.porte.add(new Porte("passage dans les égouts","égout",'b'));
        
        //9
        piece.add(trone);
        trone.porte.add(new Porte("porte vers les remparts","rempart",'f'));
        trone.porte.add(new Porte("escalier vers la salle d'entrée","entree",'d'));
        trone.porte.add(new Porte("escalier vers la cuisine","cuisine",'d'));
        
          
        //10
        piece.add(tourNord);
        tourNord.porte.add(new Porte("sauter par la fenêtre","égout",'d'));
        tourNord.porte.add(new Porte("porte vers les remparts","rempart",'l'));
        
        //11
        piece.add(tourSud);
        tourSud.porte.add(new Porte("sauter par la fenêtre","égout",'d'));
        tourSud.porte.add(new Porte("porte vers les remparts","rempart",'r'));
        
        //12
        piece.add(sortie);
        
        piece.get(endroit).addPrincesse();
        
        //  System.out.println("la princesse ce trouve : " + piece.get(endroit).getNom());
        
    }
   
    
    String getOrdreDesPièces(){
        String res ="";
        for (Pièce p : piece) {
            res += piece.lastIndexOf(p) + " " +p.getNom();
		} return res;
    }
    
    int getPièceParNiveau(Playeur p){
        int count=0;
        for(Pièce pc : piece){
            if(pc.getNiveau() == p.getIciPièce().getNiveau()){
                count++;
            }
        }return count ;
    }
    
    String getMap(Playeur j){
        String[][] tab;
        String res="";
        /*
                    tab[0][0] = piece.get(0).getVisu();
                    tab[0][1] = piece.get(1).getVisu();
                    tab[0][2] = piece.get(2).getVisu();
                    tab[0][3] = piece.get(3).getVisu();
                    tab[1][2] = piece.get(4).getVisu();
                    
                    for(int i = 3 ; i >= 0;i--){
                        for(int y = 0 ; y < 2;y++){
                        res += tab[y][i];
                        }res +="\n";
                    }*/
                    for(int i = 0 ; i < j.getIciPièce().getVisu().length;i++){
                        res += j.getIciPièce().getVisu()[i] + "\n";
                    }
        return res;
    }
    
    void setMap(Playeur p){
        String [] res = new String[6];
        int count=0;
        for(Pièce pc : piece){
                //if(p.getIciPièce().getNom().equals(pc.getNom())){
              //       res += "|" + pc.getNom() + "|\n";
             //   }
               for(Porte porte : pc.porte){             
                   if(porte.getSens() == 'f'){
                       res[count] = "||===|F|===||";
                   }else if(porte.getSens() == 'u'){
                       if(pc.porte.lastIndexOf(porte) == 0){
                        res[count] = "||=========||\n|| up      ||";  
                       }else if(pc.porte.lastIndexOf(porte) == pc.porte.size()-1){
                        res[count] = "|| up      ||\n||=========||";   
                       }else{
                       res[count] = "|| up      ||";
                       }
                   }else if(porte.getSens() == 'd'){
                       if(pc.porte.lastIndexOf(porte) == 0){
                        res[count] = "||=========||\n|| down    ||";  
                       }else if(pc.porte.lastIndexOf(porte) == pc.porte.size()-1){
                        res[count] = "|| down    ||\n||=========||";
                        }else{
                       res[count] = "|| down    ||";                       
                       }
                   }else if(porte.getSens() == 'r'){
                       if(pc.porte.lastIndexOf(porte) == 0){
                        res[count] = "||=========||\n||          =";  
                       }else if(pc.porte.lastIndexOf(porte) == pc.porte.size()-1){
                       res[count] = "||          =\n||=========||";  
                       }else{
                       res[count] = "||          =";                       
                       }
                   }else if(porte.getSens() == 'l'){
                       if(pc.porte.lastIndexOf(porte) == 0){
                        res[count] = "||=========||\n=          ||";  
                       }else if(pc.porte.lastIndexOf(porte) == pc.porte.size()-1){
                        res[count] = "=          ||\n||=========||";    
                       }else{
                       res[count] = "=          ||";                       
                       }                      
                   }else if(porte.getSens() == 'b'){
                      res[count]= "||===|B|===||"; 
                   }else{
                      res[count] = "?????????????\n\n";   
                   }
                   if(count == pc.porte.size()-1){
                       pc.addVisu(res);
                       
                       count = 0;
                   }else{
                   count++;
                   }
                }
        }        
    }
    
    int getIndiceArrayPiece(Pièce p){
        int res = 0;
        for(Pièce pi : piece){
            if(pi.getNom().equals(p.getNom())){
                return res;
            } res++;
        }
        return res;
    }
    
    String getPiece(){
      String res = "";
      res += ("Printing list of Rooms:\n");
        for (Pièce p : piece) {
            res +=" " + piece.indexOf(p) + " " + p.getNom() + " niveau " + p.getNiveau() + " avec " + p.getNbPièce() + " passages\n";
        } return res ;
    }
    
    String getIndice(){
          return this.indice.get(this.endroit-8).getIndice();  
    }
}
