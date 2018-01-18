/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lucas
 */
public class Indices {
        String[] tab ;
        int count = 0;
            public Indices(String un , String deux ,String trois){
                tab = new String[]{un,deux,trois};
            }
            
            String getIndice(){
                String res;
                if (count <= 2){
                   res = tab[count];
                    count++;
                }else{
                    res = "il n'y a plus d'indice";
                }
                return res ;             
            }
            
            boolean getPossible(){
                boolean res = true;
                if(count > 2){
                    res = false;
                }
                return res ;
            }
            
            String getDisplayIndice(){
                String res = "";
                if(count == 0){
                   res = "\t vous n'avez pas encore d'indices";
                }else{
                    int id = 0;
                    for(int i = count-1; i >= 0;i--){
                        id++;
                    res += "\t " + id + tab[i];
                    }
                } return res;
            }
}
