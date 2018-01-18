public class Porte {
    private String nom;
    private String direction ;
    private char sens;
    
    public Porte(String nom,String direction,char sens){
        this.nom = nom;
        this.direction = direction;
        this.sens = sens;
        // f = forward , b = backyard , l = left ,r = right ,u = up ,d = down
    }
    
    String getDirection(){
        return this.nom;
    }
    char getSens(){
        return sens;
    }
    public String getNom() {
        return nom;
    }
    
    public String getdirection(){
        return direction;
    }
    
}
