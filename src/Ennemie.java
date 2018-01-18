public class Ennemie extends Etre {
    int niveau;
    int resultat;
    public Ennemie(String p){
        if(p.equals("entree")){
          this.nom = "armure vide";
          this.force = 50;
          this.vie = 300;
        }else if(p.equals("bal")){
          this.nom = "mort Vivant";
          this.force = 50;
          this.vie = 200; 
        }else if(p.equals("jardin")){
          this.nom = "Ent";
          this.force = 30;
          this.vie = 70;   
        }else if(p.equals("cuisine")){
          this.nom = "cochon zombie";
          this.force = 150;
          this.vie = 100;  
        }else if(p.equals("trone")){
         this.nom = "vampire";
          this.force = 300;
          this.vie = 200;   
        }else if(p.equals("arme")){
          this.nom = "chevalier noir";
          this.force = 100;
          this.vie = 100;  
        }else if(p.equals("cellule")){
          this.nom = "squelette";
          this.force = 50;
          this.vie = 75;  
        }else if(p.equals("égout")){
          this.nom = "gnome";
          this.force = 50;
          this.vie = 50;  
        }else if(p.equals("tourSud")){
          this.nom = "sorcier bleu";
          this.force = 200;
          this.vie = 50;  
        }else if(p.equals("tourNord")){
          this.nom = "sorcier blanc";
          this.force = 200;
          this.vie = 50;  
        }else if(p.equals("cuisine")){
          this.nom = "Chef Troll";
          this.force = 200;
          this.vie = 50;  
        }else {
          this.nom = "archer zombie";
          this.force = 50;
          this.vie = 100;     
        }
    }
    
    
    String getQuestion(){
        int r = this.rand.nextInt(3);
        String res = "";
        if(r == 0){
            int x = this.rand.nextInt(15)+1;
            int y = this.rand.nextInt(10)+1;
            int z = this.rand.nextInt(10)+1;
            niveau = 50;
            resultat = ((x*y)/z)+x;
            res = "\t calcule : (" + x + " X " + y + ") / " + z + " + " + x;
        }else if(r == 1){
            int x = this.rand.nextInt(15);
            int y = this.rand.nextInt(10);
            int z = this.rand.nextInt(10);
            niveau = 30;
            resultat = (x*z)+z-x;
            res = "\t calcule : " + x + " X " + z + " + " + z + " - " + x;
        }else{
            int x = this.rand.nextInt(15);
            int y = this.rand.nextInt(10);
            int z = this.rand.nextInt(5);
            niveau = 10;
            resultat = x+y-z+y-x;
            res ="\t calcule : " + x + " + " + y + " - " + z + " + " + y + " - " + x;
        } return res ;
    }
    int getRep(){
        return resultat;
    }
    
    int getDifficulté(){
        return niveau;
    }
}
