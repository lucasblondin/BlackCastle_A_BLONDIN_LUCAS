import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.paint.Color.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;

public class Action extends Application{
    MenuJeu jeu;

    
    public void start(Stage primaryStage) throws Exception {
              
        Pane root = new Pane();
        
       

        InputStream icon = Files.newInputStream(Paths.get("./img/icons.png"));
        Image icons = new Image(icon);
        icon.close();
        
       //imgView.setVisible(false);
               
        jeu = new MenuJeu();
        
        //g.getChildren().addAll(imgView,imgViewPont);
        root.getChildren().addAll(jeu);
        
        Scene scene = new Scene(root);
        
        primaryStage.getIcons().add(icons);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private static class GButton extends StackPane{
        private Text text;
        public String name;
        
        public String getNom(){
            return name;
        }
                
        public GButton(String name,int taille){
            this.name = name;
            text = new Text(this.name);
            text.setFont(text.getFont().font(taille));
            text.setFill(WHITE);
            
            setAlignment(Pos.CENTER_LEFT);
            getChildren().addAll(text);
            
            text.setOnMouseEntered(e -> {
                text.setOpacity(0.6);
            });
            text.setOnMouseExited(e -> {
                text.setOpacity(1);
            });
        }
        
        public GButton(String name,int taille,int decalageX){
            text = new Text(name);
            text.setFont(text.getFont().font(taille));
            text.setTranslateX(decalageX);
            text.setFill(WHITE);
            
            setAlignment(Pos.CENTER_LEFT);
            getChildren().addAll(text);
            
            text.setOnMouseEntered(e -> {
                text.setOpacity(0.6);
            });
            text.setOnMouseExited(e -> {
                text.setOpacity(1);
            });
        }
}
    private static class MenuJeu extends Parent {
        Chateau hante = new Chateau();
        Playeur moi;
        Item obj;
        Text objetTrouve2 = new Text();
        Text objetDescrip2 = new Text();
        Text objetInv = new Text();
        int inspection = 0;
        public MenuJeu() throws IOException{
    
    Media debut = new Media(new File("son/02NameEntry.mp3").toURI().toString());
    
    MediaPlayer musiqueDebut = new MediaPlayer(debut);
    musiqueDebut.setAutoPlay(true);
    musiqueDebut.setVolume(0.1);
    
    
    Media combat1 = new Media(new File("son/03_Slash.mp3").toURI().toString());
    MediaPlayer musiqueCombat1 = new MediaPlayer(combat1);
    musiqueCombat1.setVolume(0.3);
    
    Media combat2 = new Media(new File("son/07_New_Messiah.mp3").toURI().toString());
    MediaPlayer musiqueCombat2 = new MediaPlayer(combat2);
    musiqueCombat1.setVolume(0.3);
    
    Media retrouve = new Media(new File("son/07_ASmallPrayer.mp3").toURI().toString());
    MediaPlayer retrouvaille = new MediaPlayer(retrouve);
    
    
   
    
    ImageView imgView ;
    ImageView imgViewPont;
    ImageView imgViewEntree;
    ImageView imgViewBal;
    ImageView imgViewJardin;
    ImageView imgViewCuisine;
    ImageView imgViewDouve;
    ImageView imgViewArme;
    ImageView imgViewRempart;
    ImageView imgViewCellule;
    ImageView imgViewTrone;
    ImageView imgViewTourNord;
    ImageView imgViewTourSud;
    ImageView imgViewText;  
    //----------------------
    HBox afficheVie =  new HBox(20);
    Text afVie = new Text();
    
    
    afVie.setFill(RED);
    afVie.setFont(afVie.getFont().font(null,FontWeight.BOLD,25));
    
    afficheVie.setTranslateX(40);
    afficheVie.setTranslateY(370);
    afficheVie.setVisible(false);
    afficheVie.getChildren().addAll(afVie);
   //--------------------------------------------------Hero
   InputStream inHero = Files.newInputStream(Paths.get("./img/heroJeu.png"));
   Image imgHero = new Image(inHero);
   inHero.close();
   ImageView imgViewHero= new ImageView(imgHero);
   imgViewHero.setVisible(false);
   //----------------------------------------------------ENNEMIE
        InputStream inPrincesse = Files.newInputStream(Paths.get("./img/ennemie/princesse.png"));
        Image imgPrincesse = new Image(inPrincesse);
        inPrincesse.close();
        ImageView imgViewPrincesse= new ImageView(imgPrincesse);
        imgViewPrincesse.setVisible(false);
        
        InputStream inArmure = Files.newInputStream(Paths.get("./img/ennemie/armureVide.png"));
        Image imgArmure = new Image(inArmure);
        inArmure.close();
        ImageView imgViewArmure= new ImageView(imgArmure);
        imgViewArmure.setVisible(false);
        
        InputStream inNoble = Files.newInputStream(Paths.get("./img/ennemie/noble.png"));
        Image imgNoble = new Image(inNoble);
        inNoble.close();
        ImageView imgViewNoble= new ImageView(imgNoble);
        imgViewNoble.setVisible(false);
        
        InputStream inEnt = Files.newInputStream(Paths.get("./img/ennemie/ent.png"));
        Image imgEnt = new Image(inEnt);
        inEnt.close();
        ImageView imgViewEnt= new ImageView(imgEnt);
        imgViewEnt.setVisible(false);
        
        InputStream inRodeur = Files.newInputStream(Paths.get("./img/ennemie/rodeur.png"));
        Image imgRodeur = new Image(inRodeur);
        inRodeur.close();
        ImageView imgViewRodeur= new ImageView(imgRodeur);
        imgViewRodeur.setVisible(false);
        
        InputStream inCook = Files.newInputStream(Paths.get("./img/ennemie/cook.png"));
        Image imgCook = new Image(inCook);
        inCook.close();
        ImageView imgViewCook= new ImageView(imgCook);
        imgViewCook.setVisible(false);
        
        InputStream inGnome = Files.newInputStream(Paths.get("./img/ennemie/gnome.png"));
        Image imgGnome = new Image(inGnome);
        inGnome.close();
        ImageView imgViewGnome= new ImageView(imgGnome);
        imgViewGnome.setVisible(false);
        
        InputStream inChevalier = Files.newInputStream(Paths.get("./img/ennemie/armure.png"));
        Image imgChevalier = new Image(inChevalier);
        inChevalier.close();
        ImageView imgViewChevalier= new ImageView(imgChevalier);
        imgViewChevalier.setVisible(false);
        
        InputStream inArcher = Files.newInputStream(Paths.get("./img/ennemie/archer.png"));
        Image imgArcher = new Image(inArcher);
        inArcher.close();
        ImageView imgViewArcher= new ImageView(imgArcher);
        imgViewArcher.setVisible(false);
        
        InputStream inGardien = Files.newInputStream(Paths.get("./img/ennemie/gardien.png"));
        Image imgGardien = new Image(inGardien);
        inGardien.close();
        ImageView imgViewGardien= new ImageView(imgGardien);
        imgViewGardien.setVisible(false);
        
        InputStream inVampire = Files.newInputStream(Paths.get("./img/ennemie/vampire.png"));
        Image imgVampire = new Image(inVampire);
        inVampire.close();
        ImageView imgViewVampire = new ImageView(imgVampire);
        imgViewVampire.setVisible(false);
        
        InputStream inMageNord = Files.newInputStream(Paths.get("./img/ennemie/mageNord.png"));
        Image imgMageNord = new Image(inMageNord);
        inMageNord.close();
        ImageView imgViewMageNord = new ImageView(imgMageNord);
        imgViewMageNord.setVisible(false);
        
        InputStream inMageSud = Files.newInputStream(Paths.get("./img/ennemie/mageSud.png"));
        Image imgMageSud = new Image(inMageSud);
        inMageNord.close();
        ImageView imgViewMageSud = new ImageView(imgMageSud);
        imgViewMageSud.setVisible(false);
        
        VBox ennemiePvNom = new VBox();
        Text ennemiePv = new Text();
        ennemiePv.setFill(RED);
        ennemiePv.setFont(ennemiePv.getFont().font(30));
        ennemiePv.setTranslateX(200);
        ennemiePv.setTranslateY(20);
        ennemiePvNom.getChildren().add(ennemiePv);
       
        
        
        Pane imgEnnemy = new Pane();
        imgEnnemy.getChildren().addAll(
        imgViewArmure,imgViewNoble,imgViewEnt,imgViewRodeur,imgViewCook,
        imgViewGnome,imgViewChevalier,imgViewArcher,imgViewGardien,
        imgViewVampire,imgViewMageNord,imgViewMageSud,imgViewPrincesse);
        //----------------------------------------------------------------------
    
        InputStream is = Files.newInputStream(Paths.get("./img/menuSansTitre.jpg"));
        Image img = new Image(is);
        is.close();
             
        imgView = new ImageView(img);
        imgView.setFitHeight(600);
        imgView.setFitWidth(600);
        //-------------------------------------------------------------------BARTXT
        InputStream inText = Files.newInputStream(Paths.get("./img/imgMenu2.png"));
        Image imgText = new Image(inText);
        inText.close();
        
        
        imgViewText = new ImageView(imgText);
        imgViewText.setTranslateY(370);
        imgViewText.setVisible(false);
        //-------------------------------------------------------------------PONT
        InputStream inPont = Files.newInputStream(Paths.get("./img/piece/pont.png"));
        Image imgPont = new Image(inPont);
        inPont.close();
        
        
        imgViewPont = new ImageView(imgPont);
        imgViewPont.setFitHeight(600);
        imgViewPont.setFitWidth(600);
        imgViewPont.setVisible(false);
      //-------------------------------------------------------------------ENTREE
        InputStream inEntree = Files.newInputStream(Paths.get("./img/piece/entree.jpg"));
        Image imgEntree = new Image(inEntree);
        inEntree.close();
        
        
        imgViewEntree = new ImageView(imgEntree);
        imgViewEntree.setFitHeight(600);
        imgViewEntree.setFitWidth(600);
        imgViewEntree.setVisible(false);
      //-------------------------------------------------------------------BAL
        InputStream inBal = Files.newInputStream(Paths.get("./img/piece/bal.png"));
        Image imgBal = new Image(inBal);
        inEntree.close();
        
        
        imgViewBal = new ImageView(imgBal);
        imgViewBal.setFitHeight(600);
        imgViewBal.setFitWidth(600);
        imgViewBal.setVisible(false);
      //-------------------------------------------------------------------JARDIN
        InputStream inJardin = Files.newInputStream(Paths.get("./img/piece/jardin2.png"));
        Image imgJardin = new Image(inJardin);
        inJardin.close();
        
        
        imgViewJardin = new ImageView(imgJardin);
        imgViewJardin.setFitHeight(600);
        imgViewJardin.setFitWidth(600);
        imgViewJardin.setVisible(false);
      //-------------------------------------------------------------------CUISINE
        InputStream inCuisine = Files.newInputStream(Paths.get("./img/piece/cuisine.png"));
        Image imgCuisine = new Image(inCuisine);
        inCuisine.close();
        
        
        imgViewCuisine = new ImageView(imgCuisine);
        imgViewCuisine.setFitHeight(600);
        imgViewCuisine.setFitWidth(600);
        imgViewCuisine.setVisible(false);
      //-------------------------------------------------------------------EGOUT
        InputStream inDouve = Files.newInputStream(Paths.get("./img/piece/douve.png"));
        Image imgDouve = new Image(inDouve);
        inDouve.close();
        
        
        imgViewDouve = new ImageView(imgDouve);
        imgViewDouve.setFitHeight(600);
        imgViewDouve.setFitWidth(600);
        imgViewDouve.setVisible(false);
      //-------------------------------------------------------------------ARME
        InputStream inArme = Files.newInputStream(Paths.get("./img/piece/arme.png"));
        Image imgArme = new Image(inArme);
        inArme.close();
        
        
        imgViewArme = new ImageView(imgArme);
        imgViewArme.setFitHeight(600);
        imgViewArme.setFitWidth(600);
        imgViewArme.setVisible(false);
      //-------------------------------------------------------------------REMPART
       InputStream inRempart = Files.newInputStream(Paths.get("./img/piece/rempart.png"));
        Image imgRempart = new Image(inRempart);
        inRempart.close();
        
        
        imgViewRempart = new ImageView(imgRempart);
        imgViewRempart.setFitHeight(600);
        imgViewRempart.setFitWidth(600);
        imgViewRempart.setVisible(false);
      //-------------------------------------------------------------------CELLULE
        InputStream inCellule = Files.newInputStream(Paths.get("./img/piece/cachot.png"));
        Image imgCellule = new Image(inCellule);
        inCellule.close();
        
        
        imgViewCellule = new ImageView(imgCellule);
        imgViewCellule.setFitHeight(600);
        imgViewCellule.setFitWidth(600);
        imgViewCellule.setVisible(false);
      //-------------------------------------------------------------------TRONE
        InputStream inTrone = Files.newInputStream(Paths.get("./img/piece/trone.png"));
        Image imgTrone = new Image(inTrone);
        inTrone.close();
        
        
        imgViewTrone = new ImageView(imgTrone);
        imgViewTrone.setFitHeight(600);
        imgViewTrone.setFitWidth(600);
        imgViewTrone.setVisible(false);
      //-------------------------------------------------------------------TOURNORD
        InputStream inTourNord = Files.newInputStream(Paths.get("./img/piece/tourNord.png"));
        Image imgTourNord = new Image(inTourNord);
        inTourNord.close();
        
        
        imgViewTourNord = new ImageView(imgTourNord);
        imgViewTourNord.setFitHeight(600);
        imgViewTourNord.setFitWidth(600);
        imgViewTourNord.setVisible(false);
      //-------------------------------------------------------------------TOURSUD
        InputStream inTourSud = Files.newInputStream(Paths.get("./img/piece/tourSud.png"));
        Image imgTourSud = new Image(inTourSud);
        inTourSud.close();
        
        
        imgViewTourSud = new ImageView(imgTourSud);
        imgViewTourSud.setFitHeight(600);
        imgViewTourSud.setFitWidth(600);
        imgViewTourSud.setVisible(false);
      //------------------------------------------------------------------------
          Group groupImg = new Group();
          groupImg.getChildren().addAll(
                  imgView,imgViewPont,imgViewEntree,imgViewBal,imgViewJardin,
                  imgViewCuisine,imgViewDouve,imgViewRempart,imgViewCellule,
                  imgViewTrone,imgViewTourNord,imgViewTourSud,imgViewArme
                  );
            //MENU 0
      //------------------------------------------------------------------------

        InputStream title = Files.newInputStream(Paths.get("./img/titreMenuReduit.png"));
        Image mlg = new Image(title);
        title.close();
        
        ImageView mgv = new ImageView(mlg);
        //-----------------------------------------------MENU
        VBox menu0 = new VBox();
        VBox menu1 = new VBox(10);
        VBox menu2 = new VBox(40);
        VBox menu3 = new VBox();
        
        menu1.setVisible(false);
        menu2.setVisible(false);
        menu3.setVisible(false);
        
        //----------------------------------------------------FIN DU JEU GAGNANT
        Pane terminus = new Pane();
        VBox vter = new VBox();
        
        Text bienJouer = new Text("Bravo tu as récupéré ta bien\naimé saine et sauve");
        Text score = new Text();
        GButton finiQuitter = new GButton("quitter",30);
        
        bienJouer.setFill(WHITE);
        bienJouer.setFont(bienJouer.getFont().font(30));
        score.setFill(WHITE);
        score.setFont(score.getFont().font(30));
       
        vter.setTranslateX(100);
        vter.setTranslateY(200);
        
        vter.getChildren().addAll(bienJouer,score,finiQuitter);
        terminus.getChildren().addAll(vter);
        terminus.setVisible(false);
        
        finiQuitter.setOnMouseClicked(e->{
            System.exit(0);
        });
      //--------------------------------------------------------TROUVE LA PRINCESSE
      HBox tests= new HBox();
    Text trouvePrincesse = new Text(
    "Mon prince tu es venu ! \n\t\t\t ( clic ) ");
    
    trouvePrincesse.setOnMouseClicked(e-> {
    trouvePrincesse.setText(
        "Fuyions ! \n\t\t\t ( clic ) ");
               
               trouvePrincesse.setOnMouseClicked(ev-> {
                   musiqueDebut.play();
                   retrouvaille.stop();
                   menu3.setVisible(true);
                   imgEnnemy.setVisible(false);
                   imgViewPrincesse.setVisible(false);
                   trouvePrincesse.setVisible(false);
               }); 
    });   
    trouvePrincesse.setTranslateX(40);
    trouvePrincesse.setTranslateY(415);
    
    trouvePrincesse.setVisible(false);
    trouvePrincesse.setFill(WHITE);
    trouvePrincesse.setFont(trouvePrincesse.getFont().font(30));
    tests.getChildren().add(trouvePrincesse);
        //------------------------------------------------MENU COMBAT
        Pane Combat = new Pane();
        VBox combatAction = new VBox();
        
        GButton attaque = new GButton("attaquer ",30);
        GButton utilItem = new GButton("regarder inventaire",30);
        GButton fuir = new GButton("fuir",30);
        combatAction.setVisible(false);
        
        Text question = new Text();
        
        combatAction.setTranslateX(50);
        combatAction.setTranslateY(405);
        //--------------------------------------------- INVENTAIRE EN COMBAT
        VBox VbinventaireCombat = new VBox();
        VBox VbinventaireCombat2 = new VBox();
        HBox HbinventaireCombat = new HBox(15);  
        GButton exitInventaire = new GButton("quitter",30);
        
        exitInventaire.setOnMouseClicked(e->{
            HbinventaireCombat.setVisible(false);
            VbinventaireCombat.getChildren().clear();
            VbinventaireCombat2.getChildren().clear();
            combatAction.setVisible(true);
        });
        
        HbinventaireCombat.getChildren().addAll(VbinventaireCombat,VbinventaireCombat2);
        HbinventaireCombat.setTranslateX(30);
        HbinventaireCombat.setTranslateY(410);
        HbinventaireCombat.setVisible(false);
        //--------------------------------------------------TUER UN MONSTRE
        Pane lootP = new Pane();
        VBox loot = new VBox ();
        Text bravo = new Text("Bravo vous avez battu un ennemi");
        bravo.setFill(WHITE);
        bravo.setFont(bravo.getFont().font(25));

        
        Text secret = new Text();
        secret.setFill(WHITE);
        secret.setFont(secret.getFont().font(25));
        
        InputStream inLoot = Files.newInputStream(Paths.get("./img/decouvObjet.png"));
        Image imgLoot = new Image(inLoot);
        inLoot.close();
        ImageView imgViewLoot = new ImageView(imgLoot);
        
        InputStream inLootRetour = Files.newInputStream(Paths.get("./img/retour.png"));
        Image imgLootRetour = new Image(inLootRetour);
        inLootRetour.close();
        ImageView imgViewLootRetour = new ImageView(imgLootRetour);
        imgViewLootRetour.setTranslateY(340);
        imgViewLootRetour.setTranslateX(230);
        
        imgViewLootRetour.setOnMouseClicked(e -> {
            musiqueDebut.play();
            musiqueCombat1.stop();
            musiqueCombat2.stop();
            
            Combat.setVisible(false);
            lootP.setVisible(false);
            afficheVie.setVisible(true);
            menu3.setVisible(true);
            imgViewText.setVisible(true);
            ennemiePv.setVisible(false);
        });
        
        loot.setTranslateY(90);
        loot.setTranslateX(100);
        loot.getChildren().addAll(bravo,secret);
        
        lootP.setTranslateY(100);
        lootP.setVisible(false);
        lootP.getChildren().addAll(imgViewLoot,loot,imgViewLootRetour);
        //--------------------------------------------------GAME OVER 
        VBox gameOver = new VBox();
        Text fin = new Text("-GAME OVER-");
        GButton quitter = new GButton("Quitter",30,100);
        
        fin.setFill(WHITE);
        fin.setFont(fin.getFont().font(50));
        
        quitter.setOnMouseClicked(e -> {
            System.exit(0);
        });
        
        gameOver.setVisible(false);
        gameOver.setTranslateX(100);
        gameOver.setTranslateY(250);
        gameOver.getChildren().addAll(fin,quitter);
        //---------------------------------------------PHASE DE COMBAT
        VBox combatPhase = new VBox();
        HBox combatSub = new HBox();
        
        Text questionField = new Text();
        TextField reponseField = new TextField("votre réponse");
        Text errorInt = new Text("entrer un nombre !");
        Button combatSubmit = new Button("submit");
        
        
        questionField.setFill(WHITE);
        questionField.setFont(questionField.getFont().font(30));
        
        errorInt.setFill(RED);
        errorInt.setFont(errorInt.getFont().font(30));
        errorInt.setTranslateX(100);
        errorInt.setTranslateY(20);
        
        combatSub.setTranslateY(30);
        combatSub.setTranslateX(130);
        combatSub.getChildren().addAll(reponseField,combatSubmit);
        combatPhase.getChildren().addAll(questionField,combatSub,errorInt);
        combatPhase.setVisible(false);
        errorInt.setVisible(false);
        
        combatPhase.setTranslateX(50);
        combatPhase.setTranslateY(405);
        
        combatSubmit.setOnMouseClicked(e -> {
            boolean text = false;
           /* for (int i = 0 ; i < reponseField.getText().length();i++){
                if(reponseField.getText().charAt(i) < 48 || reponseField.getText().charAt(i) > 57){
                 errorInt.setVisible(true);
                }
            }*/
                     
                int test = 0;
                try {
                    test = Integer.parseInt(reponseField.getText());
                    text = true;
               
                    if(moi.getIciPièce().getEnnemie().getRep() == Integer.parseInt(reponseField.getText())){
                        errorInt.setVisible(false);
                        moi.getIciPièce().getEnnemie().getDamage(moi.getAttaque());
                        Media damageToEnnemie = new Media(new File("son/damageGiven.wav").toURI().toString());
                        MediaPlayer damageGiven = new MediaPlayer(damageToEnnemie);
                        damageGiven.play();
                        damageGiven.setVolume(3);
                        System.out.println(moi.getIciPièce().getEnnemie().vie);
                        //attaque ennemie
                        TranslateTransition Donnedegat = new TranslateTransition(Duration.millis(200),imgViewHero);
                        Donnedegat.setToX(200); 
                        Donnedegat.setToY(-70);
                        Donnedegat.play();
                        
                        Donnedegat.setOnFinished(ev -> {
                        TranslateTransition degat2 = new TranslateTransition(Duration.millis(200),imgViewHero); 
                        degat2.setToX(0);
                        degat2.setToY(0);
                        degat2.play();
                        });
                        
                        //ennemie prend un degat
                        TranslateTransition degat = new TranslateTransition(Duration.millis(100),imgEnnemy);
                        degat.setToX(10);   
                        degat.play();
                        
                        degat.setOnFinished(ev -> {
                        TranslateTransition degat2 = new TranslateTransition(Duration.millis(100),imgEnnemy); 
                        degat2.setToX(-10);
                        degat2.play();
                        
                        degat2.setOnFinished(evt -> {
                         TranslateTransition degat3 = new TranslateTransition(Duration.millis(100),imgEnnemy); 
                         degat3.setToX(10);
                         degat3.play(); 
                         
                         degat3.setOnFinished(evts -> {
                         TranslateTransition degat4 = new TranslateTransition(Duration.millis(100),imgEnnemy); 
                         degat4.setToX(-10);
                         degat4.play();
                        });
                        });
                        });
                        
                    }else{
                        errorInt.setVisible(false);
                        System.out.println(moi.vie);
                        moi.getDamage(moi.getIciPièce().getEnnemie().getAttaque() - moi.getShield());
                        afVie.setText(moi.nom + " " + moi.vie + " PV");
                        Media damageToMe= new Media(new File("son/damageTaken.wav").toURI().toString());
                        MediaPlayer damageTaken = new MediaPlayer(damageToMe);
    
                        damageTaken.play();
                       
                        //ennemie me donne des dégats
                        //attaque ennemie
                        TranslateTransition Prenddegat = new TranslateTransition(Duration.millis(200),imgEnnemy);
                        Prenddegat.setToX(-200); 
                        Prenddegat.setToY(70);
                        Prenddegat.play();
                        
                        Prenddegat.setOnFinished(ev -> {
                        TranslateTransition degat2 = new TranslateTransition(Duration.millis(200),imgEnnemy); 
                        degat2.setToX(0);
                        degat2.setToY(0);
                        degat2.play();
                        });
                        //je prend des dégats
                        TranslateTransition degat = new TranslateTransition(Duration.millis(100),imgEnnemy);
                        degat.setToX(10);   
                        degat.play();
                        
                        degat.setOnFinished(ev -> {
                        TranslateTransition degat2 = new TranslateTransition(Duration.millis(100),imgViewHero); 
                        degat2.setToX(-10);
                        degat2.play();
                        
                        degat2.setOnFinished(evt -> {
                         TranslateTransition degat3 = new TranslateTransition(Duration.millis(100),imgViewHero); 
                         degat3.setToX(10);
                         degat3.play(); 
                         
                        degat3.setOnFinished(evts -> {
                         TranslateTransition degat4 = new TranslateTransition(Duration.millis(100),imgViewHero); 
                         degat4.setToX(-10);
                         degat4.play();
                        });
                        });
                        });
                    }
                    if(moi.getIciPièce().getEnnemie().vie <= 0){
                        
                        for(int i = 0 ; i < imgEnnemy.getChildren().size();i++){
                            imgEnnemy.getChildren().get(i).setVisible(false);
                        }
                       moi.setVictime();
                       lootP.setVisible(true);
                       secret.setText("Indice : " + hante.getIndice());
                       imgEnnemy.setVisible(false);
                       ennemiePv.setVisible(false);
                       combatAction.setVisible(false);
                       imgViewText.setVisible(false);
                       combatPhase.setVisible(false);
                       afficheVie.setVisible(false);
                    }else if (moi.getVie() <= 0){
                       ennemiePv.setVisible(false);
                       gameOver.setVisible(true);
                       combatAction.setVisible(false);
                       imgViewText.setVisible(false);
                       combatPhase.setVisible(false);
                       afficheVie.setVisible(false);
                    }else{
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    combatPhase.setVisible(false);
                    combatAction.setVisible(true);             
                    }
                    
         ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
         ennemiePv.setVisible(true);
          } catch(NumberFormatException ev){
                    errorInt.setVisible(true);                   
                    TranslateTransition nombre = new TranslateTransition(Duration.millis(100),reponseField);
                        nombre.setToX(10);   
                        nombre.play();
                        
                        nombre.setOnFinished(ex -> {
                        TranslateTransition degat2 = new TranslateTransition(Duration.millis(100),reponseField); 
                        degat2.setToX(-10);
                        degat2.play();
                        
                        degat2.setOnFinished(evt -> {
                         TranslateTransition degat3 = new TranslateTransition(Duration.millis(100),reponseField); 
                         degat3.setToX(10);
                         degat3.play(); 
                         
                         degat3.setOnFinished(evts -> {
                         TranslateTransition degat4 = new TranslateTransition(Duration.millis(100),reponseField); 
                         degat4.setToX(-10);
                         degat4.play();
                        });
                        });
                        });
                }
        });
        //----------------------------------------------------------------------
        attaque.setOnMouseClicked(e -> {
        combatAction.setVisible(false);
        combatPhase.setVisible(true);    
        });
        
        utilItem.setOnMouseClicked(e -> {
            String test ="-";
            HbinventaireCombat.setVisible(true);
            combatAction.setVisible(false);
            int nb = 0;
            for(int i = 0 ; i < moi.objet.size();i++){             
                if(moi.objet.get(i).type().equals("potion")){
                String nom = moi.objet.get(i).getNom();
                GButton $nom = new GButton(moi.objet.get(i).getNom(),30);
                int heal = moi.objet.get(i).force;
                int idx = i;
                if(nb < 2){
                $nom.setOnMouseClicked(evt->{
                    moi.setVie(heal);
                    moi.objet.remove(idx);
                    VbinventaireCombat.getChildren().remove($nom);
                    afVie.setText(moi.getNom() + " " + moi.vie + " PV");
                });
                
                VbinventaireCombat.getChildren().add($nom);
                }else{
                $nom.setOnMouseClicked(evt->{
                    moi.setVie(heal);
                    moi.objet.remove(idx);
                    VbinventaireCombat2.getChildren().remove($nom);
                    afVie.setText(moi.getNom() + " " + moi.vie + " PV");
                });
                
                VbinventaireCombat2.getChildren().add($nom);   
                }
                nb++;
                }              
            }
            VbinventaireCombat2.getChildren().add(new GButton("-",30));
            VbinventaireCombat.getChildren().add(exitInventaire);
        });
        
        fuir.setOnMouseClicked(e -> {
           Media damageToEnnemie= new Media("file:///C:/Users/lucas/Downloads/damageGiven.wav");
           MediaPlayer damageGiven = new MediaPlayer(damageToEnnemie);
           damageGiven.play();
           musiqueCombat1.stop();
           musiqueCombat2.stop();
           musiqueDebut.play();
           ennemiePv.setVisible(false);
           moi.getDamage(moi.getIciPièce().getEnnemie().getAttaque());
           moi.getIciPièce().getEnnemie().getDamage(5000);
           for(int i = 0 ; i < imgEnnemy.getChildren().size();i++){
                      imgEnnemy.getChildren().get(i).setVisible(false);
                        }
          if(moi.vie <= 0){
            gameOver.setVisible(true);
            combatAction.setVisible(false);
            imgViewText.setVisible(false);
            combatPhase.setVisible(false);
            afficheVie.setVisible(false);
                        }else{           
           afVie.setText(moi.getNom() + " " + moi.vie + " PV");
           Combat.setVisible(false);
           imgEnnemy.setVisible(false);
           menu3.setVisible(true);
           imgViewText.setVisible(true);
          }
           
           
           TranslateTransition degat = new TranslateTransition(Duration.millis(100),imgViewHero);
                        degat.setToX(10);   
                        degat.play();
                        
                        degat.setOnFinished(ev -> {
                        TranslateTransition degat2 = new TranslateTransition(Duration.millis(100),imgViewHero); 
                        degat2.setToX(-10);
                        degat2.play();
                        
                        degat2.setOnFinished(evt -> {
                         TranslateTransition degat3 = new TranslateTransition(Duration.millis(100),imgViewHero); 
                         degat3.setToX(10);
                         degat3.play(); 
                         
                         degat3.setOnFinished(evts -> {
                         TranslateTransition degat4 = new TranslateTransition(Duration.millis(100),imgViewHero); 
                         degat4.setToX(-10);
                         degat4.play();
                        });
                        });
                        });
                        
        });
        
        combatAction.getChildren().addAll(attaque,utilItem,fuir);
        Combat.getChildren().addAll(combatAction,combatPhase,gameOver,HbinventaireCombat,lootP);

        //---------------------------------------------Piece
        //------------------------------------PONT
        VBox pont = new VBox(20);
        HBox PHaut = new HBox(150);
        HBox PBas = new HBox();
                       
        GButton sortie = new GButton("sortie",30);
        GButton entree = new GButton("porte d'entrée",30);
        GButton egout = new GButton("les égoux",30);
        
        PHaut.getChildren().addAll(entree,egout);
        PBas.getChildren().addAll(sortie);
        pont.getChildren().addAll(PHaut,PBas);
        pont.setVisible(false);
        
        pont.setTranslateX(50);
        pont.setTranslateY(420);
        
        sortie.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(12));
        
        if(!moi.getPrincesse()){
            bienJouer.setText("Tu t'es enfui ! ");
        }
        terminus.setVisible(true);
        score.setText("Tu as vaincu : " + moi.nbVictime() + " ennemie(s)");
        imgViewHero.setVisible(false);
        imgViewPont.setVisible(false);
        pont.setVisible(false);
        menu3.setVisible(false);
        imgViewText.setVisible(false);
        afficheVie.setVisible(false);
        });
        
        entree.setOnMouseClicked((MouseEvent e) -> {
        moi.changeDePièce(hante.piece.get(1));
        imgViewEntree.setVisible(true);
        imgViewPont.setVisible(false);
        pont.setVisible(false);
                        
        if(moi.getIciPièce().ennemie()){
            
                    musiqueDebut.pause();
                    musiqueCombat1.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());                  
                    imgEnnemy.setVisible(true);
                    imgViewArmure.setVisible(true);                    
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        egout.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(5));  
        imgViewDouve.setVisible(true);
        imgViewPont.setVisible(false);
        pont.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat1.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewGnome.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        //------------------------------------ENTREE
        VBox entreeC = new VBox(20);
        HBox EHaut = new HBox(215);
        HBox EBas = new HBox(130);
                       
        GButton pontlevie = new GButton("le pont",30);
        GButton bal = new GButton("salle de bal",30);
        GButton trone = new GButton("salle du trône",30);
        GButton arme = new GButton("salle d'arme",30);
        
        EHaut.getChildren().addAll(pontlevie,bal);
        EBas.getChildren().addAll(trone,arme);
        entreeC.getChildren().addAll(EHaut,EBas);
        entreeC.setVisible(false);
        
        entreeC.setTranslateX(50);
        entreeC.setTranslateY(420);
        
        pontlevie.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(0));
        imgViewPont.setVisible(true);
        imgViewEntree.setVisible(false);
        entreeC.setVisible(false);
        menu3.setVisible(true);
        });
        
        bal.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(2));  
        entreeC.setVisible(false);
        imgViewEntree.setVisible(false);
        imgViewBal.setVisible(true);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat1.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewNoble.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        trone.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(9)); 
        imgViewTrone.setVisible(true);
        imgViewEntree.setVisible(false);
        entreeC.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat2.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewVampire.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        arme.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(6)); 
        imgViewArme.setVisible(true);
        imgViewEntree.setVisible(false);
        entreeC.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat2.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewChevalier.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        //------------------------------------------------
        //------------------------------------BAL
        VBox balB = new VBox(20);
        HBox BHaut = new HBox(190);
        HBox BBas = new HBox(30);
                       
        GButton balEntree = new GButton("salle d'entree",30);
        GButton balJardin = new GButton("le jardin",30);
        GButton balCuisine = new GButton("la cuisine",30); 
        
        BHaut.getChildren().addAll(balEntree,balJardin);
        BBas.getChildren().addAll(balCuisine);
        balB.getChildren().addAll(BHaut,BBas);
        balB.setVisible(false);
        
        balB.setTranslateX(50);
        balB.setTranslateY(420);
        
        balEntree.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(1));
         if(moi.getIciPièce().ennemie()){
                    imgViewArmure.setVisible(true);
                }
        imgViewBal.setVisible(false);
        imgViewEntree.setVisible(true);
        balB.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat1.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewArmure.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        balJardin.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(3));  
        imgViewBal.setVisible(false);
        imgViewJardin.setVisible(true);
        balB.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat1.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewEnt.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        balCuisine.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(4));  
        imgViewCuisine.setVisible(true);
        imgViewBal.setVisible(false);
        balB.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat1.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewCook.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        //-----------------------------------------
        //------------------------------------JARDIN
        VBox jardinJ = new VBox(20);
        HBox JHaut = new HBox(30);
        HBox JBas = new HBox(30);
                       
        GButton Jbal = new GButton("salle de bal",30);
        GButton Jr = new GButton("monter sur les remparts",30);
        
        JHaut.getChildren().addAll(Jbal);
        JBas.getChildren().addAll(Jr);
        jardinJ.getChildren().addAll(JHaut,JBas);
        jardinJ.setVisible(false);
        
        jardinJ.setTranslateX(100);
        jardinJ.setTranslateY(420);
        
        Jbal.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(2));
        imgViewJardin.setVisible(false);
        imgViewBal.setVisible(true);
        jardinJ.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat2.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewNoble.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        Jr.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(7));  
        imgViewRempart.setVisible(true);
        imgViewJardin.setVisible(false);
        jardinJ.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat1.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewArcher.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });

        //-----------------------------------------
        //------------------------------------CUISINE
        VBox cuisineC = new VBox(20);
        HBox CHaut = new HBox(30);
        HBox CBas = new HBox(30);
                       
        GButton Cbal = new GButton("salle de bal",30);
        //GButton Crempart = new GButton("monter sur les remparts",30);
        GButton Ctrone = new GButton("monter dans la salle du trône",30);
        
        CHaut.getChildren().addAll(Cbal);
        CBas.getChildren().addAll(Ctrone);
        cuisineC.getChildren().addAll(CHaut,CBas);
        cuisineC.setVisible(false);
        
        cuisineC.setTranslateX(100);
        cuisineC.setTranslateY(420);
        
        Cbal.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(2));
        imgViewBal.setVisible(true);
        imgViewCuisine.setVisible(false);
        cuisineC.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat2.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewNoble.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        /*Crempart.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(7)); 
        imgViewRempart.setVisible(true);
        imgViewCuisine.setVisible(false);
        cuisineC.setVisible(false);
        menu3.setVisible(true);
        });*/
        
        Ctrone.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(9));
        imgViewTrone.setVisible(true);
        imgViewCuisine.setVisible(false);
        cuisineC.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat1.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewVampire.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });

        //-----------------------------------------
        //------------------------------------DOUVE
        VBox egoutE = new VBox(20);
        HBox Ehaut = new HBox(30);
        HBox Ebas = new HBox(30);
                       
        GButton Epont = new GButton("monter sur le pont",30);
        GButton Ecellule = new GButton("entrée dans la cellule",30);
        
        Ehaut.getChildren().addAll(Epont);
        Ebas.getChildren().addAll(Ecellule);
        egoutE.getChildren().addAll(Ehaut,Ebas);
        egoutE.setVisible(false);
        
        egoutE.setTranslateX(100);
        egoutE.setTranslateY(420);
        
        Epont.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(0));
        imgViewPont.setVisible(true);
        imgViewDouve.setVisible(false);
        egoutE.setVisible(false);
            menu3.setVisible(true);
        });
        
        Ecellule.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(8));  
        imgViewCellule.setVisible(true);
        imgViewDouve.setVisible(false);
        egoutE.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat2.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewGardien.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });

        //-----------------------------------------
        //------------------------------------ARME
        VBox armeA = new VBox(20);
        HBox AHaut = new HBox(50);
        HBox ABas = new HBox(30);
                       
        GButton Aentree = new GButton("aller à l'entree",30);
        GButton Arempart = new GButton("monter sur les remparts",30);
        GButton Acellule = new GButton("descendre dans la prison",30);
        
        AHaut.getChildren().addAll(Aentree,Arempart);
        ABas.getChildren().addAll(Acellule);
        armeA.getChildren().addAll(AHaut,ABas);
        armeA.setVisible(false);
        
        armeA.setTranslateX(50);
        armeA.setTranslateY(420);
        
        Aentree.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(1));
        imgViewEntree.setVisible(true);
        imgViewArme.setVisible(false);
        armeA.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat1.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewArmure.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        Arempart.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(7));
        imgViewRempart.setVisible(true);
        imgViewArme.setVisible(false);
        armeA.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat2.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewArcher.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        Acellule.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(8));  
        imgViewCellule.setVisible(true);
        imgViewArme.setVisible(false);
        armeA.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat1.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewGardien.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });

        //-----------------------------------------
        //------------------------------------REMPART
        VBox rempartT = new VBox(20);
        HBox THaut = new HBox(30);
        HBox TBas = new HBox(30);
                       
        GButton  Tarme= new GButton("salle d'arme",30);
        GButton  Tjardin= new GButton("aller dans le jardin",30);
        GButton Ttn = new GButton("aller à la tour Nord",30);
        GButton Tts = new GButton("aller à la tour Sud",30);        
        
        THaut.getChildren().addAll(Tarme,Tjardin);
        TBas.getChildren().addAll(Ttn,Tts);
        rempartT.getChildren().addAll(THaut,TBas);
        rempartT.setVisible(false);
        
        rempartT.setTranslateX(50);
        rempartT.setTranslateY(420);
        
        Tarme.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(6));
        imgViewArme.setVisible(true);
        imgViewRempart.setVisible(false);
        rempartT.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat2.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewChevalier.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        Tjardin.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(3));  
        imgViewJardin.setVisible(true);
        imgViewRempart.setVisible(false);
        rempartT.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat1.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewEnt.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        Ttn.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(10));
        imgViewTourNord.setVisible(true);
        imgViewRempart.setVisible(false);
        rempartT.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat2.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewMageNord.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        Tts.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(11));  
        imgViewTourSud.setVisible(true);
        imgViewRempart.setVisible(false);
        rempartT.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat1.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewMageSud.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });

        //-----------------------------------------
        //------------------------------------CELLULE
        VBox celluleC = new VBox(20);
        HBox Chaut = new HBox(30);
        HBox Cbas = new HBox(30);
                       
        GButton Carme = new GButton("aller dans la salle d'arme",30);
        GButton Cdouve = new GButton("aller dans les douves",30);
        
        Chaut.getChildren().addAll(Carme);
        Cbas.getChildren().addAll(Cdouve);
        celluleC.getChildren().addAll(Chaut,Cbas);
        celluleC.setVisible(false);
        
        celluleC.setTranslateX(100);
        celluleC.setTranslateY(420);
        
        Carme.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(6));
        imgViewArme.setVisible(true);
        imgViewCellule.setVisible(false);
        celluleC.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat1.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewChevalier.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        Cdouve.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(5));
        imgViewDouve.setVisible(true);
        imgViewCellule.setVisible(false);
        celluleC.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat2.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewGnome.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });

        //-----------------------------------------
        //------------------------------------TRONE
        VBox troneR = new VBox(20);
        HBox RHaut = new HBox(30);
        HBox RBas = new HBox(30);
                       
        GButton Rentree = new GButton("salle d'entree",30);
        GButton Rcuisine = new GButton("les cuisines",30);
        GButton RtourN = new GButton("tour Nord",30);
        GButton RtourS = new GButton("tour Sud",30);
        
        RHaut.getChildren().addAll(Rentree,Rcuisine);
        RBas.getChildren().addAll(RtourN,RtourS);
        troneR.getChildren().addAll(RHaut,RBas);
        troneR.setVisible(false);
        
        troneR.setTranslateX(50);
        troneR.setTranslateY(420);
        
        Rentree.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(1));
        imgViewEntree.setVisible(true);
        imgViewTrone.setVisible(false);
        troneR.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat2.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewArmure.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        Rcuisine.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(4));  
        imgViewCuisine.setVisible(true);
        imgViewTrone.setVisible(false);
        troneR.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat1.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewCook.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        RtourN.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(10));
        imgViewTourNord.setVisible(true);
        imgViewTrone.setVisible(false);
        troneR.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat2.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewMageNord.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        RtourS.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(11));  
        imgViewTourSud.setVisible(true);
        imgViewTrone.setVisible(false);
        troneR.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat2.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewMageSud.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });

        //-----------------------------------------
        //------------------------------------TOURNORD
        VBox  tourNordN = new VBox(20);
        HBox NHaut = new HBox(30);
        HBox NBas = new HBox(30);
                       
        GButton Nrempart = new GButton("aller sur les remparts",30);
        GButton Ndouve = new GButton("sauter par la fenêtre",30);
        GButton Ntrone = new GButton("Salle du trône",30);
        
        NHaut.getChildren().addAll(Nrempart,Ntrone);
        NBas.getChildren().addAll(Ndouve);
        tourNordN.getChildren().addAll(NHaut,NBas);
        tourNordN.setVisible(false);
        
        tourNordN.setTranslateX(50);
        tourNordN.setTranslateY(420);
        
        Nrempart.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(7));
        imgViewRempart.setVisible(true);
        imgViewTourNord.setVisible(false);
        tourNordN.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat2.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewArcher.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        Ndouve.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(5));  
        imgViewDouve.setVisible(true);
        imgViewTourNord.setVisible(false);
        tourNordN.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat1.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewGnome.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        Ntrone.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(9));  
        imgViewTrone.setVisible(true);
        imgViewTourNord.setVisible(false);
        tourNordN.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat2.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewVampire.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });

        //-----------------------------------------
        //------------------------------------TOURSUD
        VBox  tourSudS= new VBox(20);
        HBox SHaut = new HBox(30);
        HBox SBas = new HBox(30);
                       
        GButton Srempart = new GButton("aller sur les remparts",30);
        GButton Sdouve = new GButton("sauter par la fenêtre",30);
        GButton Strone = new GButton("Salle du trône",30);
        
        SHaut.getChildren().addAll(Srempart,Strone);
        SBas.getChildren().addAll(Sdouve);
        tourSudS.getChildren().addAll(SHaut,SBas);
        tourSudS.setVisible(false);
        
        tourSudS.setTranslateX(50);
        tourSudS.setTranslateY(420);
        
        Srempart.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(7));
        imgViewRempart.setVisible(true);
        imgViewTourSud.setVisible(false);
        tourSudS.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat1.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewArcher.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        Sdouve.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(5));  
        imgViewDouve.setVisible(true);
        imgViewTourSud.setVisible(false);
        tourSudS.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat2.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewGnome.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        
        Strone.setOnMouseClicked(e -> {
        moi.changeDePièce(hante.piece.get(9));  
        imgViewTrone.setVisible(true);
        imgViewTourSud.setVisible(false);
        tourSudS.setVisible(false);
        
        if(moi.getIciPièce().ennemie()){
                    musiqueDebut.pause();
                    musiqueCombat2.play();
                    ennemiePv.setVisible(true);
                    ennemiePv.setText(moi.getIciPièce().getEnnemie().getNom() + " " + moi.getIciPièce().getEnnemie().vie);
                    questionField.setText(moi.getIciPièce().getEnnemie().getQuestion());
                    imgEnnemy.setVisible(true);
                    imgViewVampire.setVisible(true);
                    Combat.setVisible(true);
                    combatAction.setVisible(true);
                    //menu3.setVisible(false);
        }else {
            menu3.setVisible(true);
        }
        });
        //-----------------------------------------
        VBox panelMenu0 = new VBox(40);
        GButton btJouer = new GButton("Jouer",70);
        GButton btExit = new GButton("Exit",30,60);
        
        panelMenu0.getChildren().addAll(btJouer,btExit);
        menu0.getChildren().addAll(mgv,panelMenu0);
        menu0.setTranslateY(50);
        
        panelMenu0.setTranslateX(230);
        panelMenu0.setTranslateY(80);
        
        btExit.setOnMouseClicked(e -> {
            System.exit(0);
        });
        // MENU0 VERS MENU1
        btJouer.setOnMouseClicked(e -> {
            //System.out.println(hante.getPiece());
            FadeTransition fade = new FadeTransition(Duration.seconds(0.5),menu0);
            fade.setFromValue(1);
            fade.setToValue(0);
            
            fade.setOnFinished(event -> { 
                menu0.setVisible(false);
                FadeTransition aparait = new FadeTransition(Duration.seconds(0.1),menu1);
                aparait.setFromValue(0);
                aparait.setToValue(1);
            
                aparait.setOnFinished(evenement -> { 
                menu1.setVisible(true);
                
                    });
                aparait.play();
                    });
            fade.play();
        });
      //------------------------------------------------------------------------ 
      
            //MENU1
      //------------------------------------------------------------------------
        TextField textNom = new TextField("choisir un nom");
        Text textPseudo = new Text("Pseudo");
        GButton btValider = new GButton("Valider",50);
        GButton btRetour = new GButton("Retour",30,30);
        
        textPseudo.setFill(WHITE);
        
        
        menu1.setTranslateX(230);
        menu1.setTranslateY(300);
        // MENU1 VERS MENU0
        btRetour.setOnMouseClicked(e -> {
            FadeTransition fade = new FadeTransition(Duration.seconds(0.5),menu1);
            fade.setFromValue(1);
            fade.setToValue(0);
            
            fade.setOnFinished(event -> { 
                menu1.setVisible(false);
                FadeTransition aparait = new FadeTransition(Duration.seconds(0.1),menu0);
                aparait.setFromValue(0);
                aparait.setToValue(1);
            
                aparait.setOnFinished(evenement -> { 
                menu0.setVisible(true);
                
                    });
                aparait.play();
                    });
            fade.play();
        });
        //DEBUT DU JEU + MENU1 VERS MENU2
        btValider.setOnMouseClicked(e -> {
            moi = new Playeur(textNom.getText(),hante.piece.get(0));
            System.out.println(moi.nom);
            
            afVie.setText(moi.nom + " " + moi.vie + " PV");
            
            
            FadeTransition fade = new FadeTransition(Duration.seconds(0.5),menu1);
            fade.setFromValue(1);
            fade.setToValue(0);
            
            fade.setOnFinished(event -> { 
                menu1.setVisible(false);
                FadeTransition aparait = new FadeTransition(Duration.seconds(0.1),menu2);
                aparait.setFromValue(0);
                aparait.setToValue(1);
            
                aparait.setOnFinished(evenement -> { 
                menu2.setVisible(true);
                
                    });
                aparait.play();
                    });
            fade.play();
        });
        
        menu1.getChildren().addAll(textPseudo,textNom,btValider,btRetour);
      //------------------------------------------------------------------------

            //MENU2
      //------------------------------------------------------------------------
        VBox vbTextSuivant = new VBox();
        GButton btSuivant = new GButton("Suivant",30);
        Text presentation = new Text(""
                + "La princesse du royaume c'est faite capturer\n"
                + "Vous êtes le dernière espoire\n"
                + "retrouver la princesse coûte que coûte\n"
                + "Vous trouverez des indices en faisant\nparler les monstres\n"
                + "Bonne chance\n"
                + " Roi Dagobert\n");
        
        presentation.setFill(WHITE);
        presentation.setFont(presentation.getFont().font(30));
        vbTextSuivant.setTranslateX(10);
        vbTextSuivant.setTranslateY(100);
        vbTextSuivant.getChildren().addAll(presentation,btSuivant);
       
        
        btSuivant.setOnMouseClicked(e -> {
            
            FadeTransition fade = new FadeTransition(Duration.seconds(0.5),menu2);
            fade.setFromValue(1);
            fade.setToValue(0);
            
            fade.setOnFinished(event -> { 
                menu2.setVisible(false);
                FadeTransition aparait = new FadeTransition(Duration.seconds(0.1),menu3);
                aparait.setFromValue(0);
                aparait.setToValue(1);
            
                aparait.setOnFinished(evenement -> { 
                menu3.setVisible(true);
                imgViewHero.setVisible(true);
                afficheVie.setVisible(true);
                imgViewPont.setVisible(true);
                imgViewText.setVisible(true);
                    });
                aparait.play();
                    });
            fade.play();
        });
        menu2.getChildren().addAll(vbTextSuivant);
      //------------------------------------------------------------------------      
        //MENU3
     //-------------------------------------------------------------------------
        VBox choixLigne = new VBox(10);
        HBox choixColone1 = new HBox(20);
        HBox choixColone2 = new HBox(20);
        VBox deux = new VBox();
        
        InputStream is2 = Files.newInputStream(Paths.get("./img/piece/pont.png"));
        Image img2 = new Image(is2);
        //root.getChildren().add(new ImageView(img2));
        is2.close();
        
        
        GButton btInspecter = new GButton(" examiner la pièce",30);
        GButton btChangerDePiece = new GButton(" Changer de pièce",30);
        choixColone1.getChildren().addAll(btInspecter,btChangerDePiece);
        
        GButton btInventaire = new GButton(" inventaire",30);
        GButton btMap = new GButton("             regarder la map",30);
        choixColone2.getChildren().addAll(btInventaire,btMap);
        


        btChangerDePiece.setOnMouseClicked(e -> {
            inspection = 0;
            menu3.setVisible(false);
            System.out.println(moi.getIciString());
            if(moi.getIciString().equals("pont")){
               pont.setVisible(true); 
            }else if(moi.getIciString().equals("entree")){               
            entreeC.setVisible(true);
        }else if(moi.getIciString().equals("bal")){
            balB.setVisible(true);
        }else if(moi.getIciString().equals("jardin")){
            jardinJ.setVisible(true);
        }else if(moi.getIciString().equals("cuisine")){
            cuisineC.setVisible(true);
        }else if(moi.getIciString().equals("égout")){
            egoutE.setVisible(true);
        }else if(moi.getIciString().equals("arme")){
            armeA.setVisible(true);
        }else if(moi.getIciString().equals("rempart")){
            rempartT.setVisible(true);            
        }else if(moi.getIciString().equals("cellule")){
            celluleC.setVisible(true);            
        }else if(moi.getIciString().equals("trone")){
            troneR.setVisible(true);            
        }else if(moi.getIciString().equals("tourNord")){
            tourNordN.setVisible(true);        
        }else if(moi.getIciString().equals("tourSud")){
            tourSudS.setVisible(true);
        }else if(moi.getIciString().equals("sortie")){
                System.out.println("Fin");
        }
        });
        /*
        if( 1 > deux.getChildren().size() ){
           deux.getChildren().get(1).setOnMouseClicked(e -> {
               menu3.setVisible(true);
               
           });
        }else if(2 > deux.getChildren().size() ){
            
        }else if(3 > deux.getChildren().size() ){
            
        }else if(4 > deux.getChildren().size() ){
            
        }*/
        //faire action sur deux.
     /*   
        btChangerDePiece.setOnMouseClicked(e -> {
            HBox haut = new HBox(10);
            HBox bas = new HBox(10);
                       
            deux.getChildren().addAll(haut,bas);
            
            int count = 0;
            System.out.println("piece");
           // moi.changeDePiece(hante);  
           // moi.getIciPièce().foundEnnemie(moi,hante);
           
           
           for(int i = 0 ; i < moi.getIciPièce().getNombreDePorte();i++){
               String nom = moi.getIciPièce().porte.get(i).getNom();
               GButton $nom = new GButton(moi.getIciPièce().porte.get(i).getNom(),30);
               
               if(i < 2){
                 haut.getChildren().add($nom);
               }else{
                 bas.getChildren().add($nom);  
               }
           }
           
           
           deux.setTranslateX(35);
           deux.setTranslateY(420);
           
           menu3.setVisible(false);
           getChildren().add(deux);
        });*/
      //---------------------------------------------------------------INVENTAIRE  
        VBox inventaireBox = new VBox();
        HBox boutonInv = new HBox();
        VBox inventaireVB = new VBox();
        /*
        objetInv.setVisible(false);
        objetInv.setFill(WHITE);
        objetInv.setFont(objetInv.getFont().font(30));
         */
        
        inventaireBox.getChildren().add(inventaireVB);
        //inventaireBox.getChildren().add(objetInv);
        inventaireBox.setTranslateX(150);
        inventaireBox.setTranslateY(150);
            
        InputStream inInventaire = Files.newInputStream(Paths.get("./img/inventaire.png"));
        Image imgInventaire = new Image(inInventaire);
        inInventaire.close();
        ImageView imgViewInventaire = new ImageView(imgInventaire);
        imgViewInventaire.setVisible(false);
        
        InputStream inRetour = Files.newInputStream(Paths.get("./img/retour.png"));
        Image imgRetour = new Image(inRetour);
        inRetour.close();
        ImageView imgViewRetour = new ImageView(imgRetour);
        boutonInv.setTranslateX(200);
        boutonInv.setTranslateY(500);
        boutonInv.getChildren().add(imgViewRetour);
        boutonInv.setVisible(false);
        
        imgViewRetour.setOnMouseClicked(e -> {
            //objetInv.setVisible(false);
            inventaireVB.getChildren().clear();
            inventaireVB.setVisible(false);
            boutonInv.setVisible(false);
            imgViewInventaire.setVisible(false);
            menu3.setVisible(true);
            imgViewText.setVisible(true);  
        });
        
        btInventaire.setOnMouseClicked(e -> {
            System.out.println("inventaire");
            //moi.useItem();
            objetInv.setText(moi.ItemX());
            objetInv.setVisible(true);      
            
            for(int i = 0 ; i < moi.objet.size();i++){
                String nom = moi.objet.get(i).getNom();
                GButton $nom = new GButton(moi.objet.get(i).getNom(),30);
                int heal = moi.objet.get(i).force;
                int idx = i;
                if(moi.objet.get(i).type().equals("potion")){            
                $nom.setOnMouseClicked(evt->{
                    moi.setVie(heal);
                    moi.objet.remove(idx);
                    inventaireVB.getChildren().remove($nom);
                    afVie.setText(moi.getNom() + " " + moi.vie + " PV");
                });                
                
                }
                inventaireVB.getChildren().add($nom);
            }
            System.out.println(inventaireVB.getChildren().size());
            inventaireVB.setVisible(true);
            boutonInv.setVisible(true);
            imgViewInventaire.setVisible(true);
            menu3.setVisible(false);
            imgViewText.setVisible(false);
        });
             //----------------------------------------------------------INSPECTION
        VBox inspect = new VBox();
        VBox possibilite = new VBox();
        HBox choixInspection = new HBox(70);
        inspect.setVisible(false);
        
        InputStream inTrouve = Files.newInputStream(Paths.get("./img/decouvObjet.png"));
        Image imgTrouve = new Image(inTrouve);
        inTrouve.close();
        ImageView imgViewTrouve = new ImageView(imgTrouve);
        imgViewTrouve.setTranslateX(0);
        imgViewTrouve.setTranslateY(100);
        imgViewTrouve.setVisible(false);
        
        Text trouve = new Text("vous avez trouvé un objet !");
        trouve.setFill(WHITE);
        trouve.setFont(trouve.getFont().font(30));
        
        InputStream inPrendre = Files.newInputStream(Paths.get("./img/boutonPrendre.png"));
        Image imgPrendre = new Image(inPrendre);
        inPrendre.close();
        ImageView imgViewPrendre = new ImageView(imgPrendre);
        
        imgViewPrendre.setOnMouseClicked( e -> {
            if(moi.dejaVuB(obj) && !obj.type().equals("potion")){
                
                moi.force -= moi.objet.get(moi.dejaVuIndex(obj)).force;
                moi.objet.remove(moi.objet.get(moi.dejaVuIndex(obj)));               
            }
                moi.addItem(obj); 
                if(obj.type().equals("epee")){
                    moi.force += obj.force;
                }         
                menu3.setVisible(true);
                imgViewText.setVisible(true);
                inspect.setVisible(false); 
                imgViewTrouve.setVisible(false);    
        });
        
        InputStream inLaisser = Files.newInputStream(Paths.get("./img/BoutonLaisser.png"));
        Image imgLaisser = new Image(inLaisser);
        inLaisser.close();
        ImageView imgViewLaisser = new ImageView(imgLaisser);
        
        imgViewLaisser.setOnMouseClicked( e -> {
                menu3.setVisible(true);
                imgViewText.setVisible(true);
                inspect.setVisible(false); 
                imgViewTrouve.setVisible(false);    

        });
        
        inspect.setTranslateX(70);
        inspect.setTranslateY(200);
        choixInspection.setTranslateY(130);
        choixInspection.setTranslateX(30);
        choixInspection.getChildren().addAll(imgViewPrendre,imgViewLaisser);
        inspect.getChildren().addAll(trouve,possibilite,objetTrouve2,objetDescrip2,choixInspection);
      //------------------------------------------------------------------------
      
        btInspecter.setOnMouseClicked(e -> {
            
            System.out.println("inspecter");
            if(moi.getIciPièce().getPrincesse()){
                musiqueDebut.stop();
                retrouvaille.play();
                System.out.println("tu as trouvé la princesse ! ");
                imgEnnemy.setVisible(true);
                imgViewPrincesse.setVisible(true);
                trouvePrincesse.setVisible(true);
                menu3.setVisible(false);
                
                moi.addPrincesse();
            }else{
                if (inspection == 3){
                    
                }else {
                inspection++;
                obj = moi.getIciPièce().creatItem(moi);
                
                if(moi.dejaVuB(obj) && !obj.type().equals("potion")){

            objetTrouve2.setText("vous ne pouvez en garder qu'une" );
            objetDescrip2.setText("(trouvé) " + obj.getNom() + " force : "+ obj.force +  "\n" + moi.getItemChoisie(obj.type()));;
            
            }else {
                objetTrouve2.setText("objet : " + obj.getNom());
                objetTrouve2.setFill(WHITE);
                objetTrouve2.setFont(objetTrouve2.getFont().font(30));
                
                objetDescrip2.setText("cette objet a une puissance de : " + obj.force);
                objetDescrip2.setFill(WHITE);
                objetDescrip2.setFont(objetDescrip2.getFont().font(30));
            }
                
                menu3.setVisible(false);
                imgViewText.setVisible(false);
                inspect.setVisible(true); 
                imgViewTrouve.setVisible(true);
            //moi.getIciPièce().creatItem().foundItem(moi);
            }
            }
        });
        //------------------------------------------------------------------MAP
        
        Pane mapPane = new Pane();
        
        InputStream inMap0 = Files.newInputStream(Paths.get("./img/map0.png"));
        Image imgMap0 = new Image(inMap0);
        inMap0.close();
        ImageView imgViewMap0 = new ImageView(imgMap0);
        imgViewMap0.setVisible(false);
        
        InputStream inMapMoins1 = Files.newInputStream(Paths.get("./img/-1niveau.png"));
        Image imgMapMoins1 = new Image(inMapMoins1);
        inMapMoins1.close();
        ImageView imgViewMapMoins1 = new ImageView(imgMapMoins1);
        imgViewMapMoins1.setVisible(false);
        
        
        InputStream inMap1 = Files.newInputStream(Paths.get("./img/1erEtage.png"));
        Image imgMap1 = new Image(inMap1);
        inMap1.close();
        ImageView imgViewMap1 = new ImageView(imgMap1);
        imgViewMap1.setVisible(false);
        
        InputStream inIci = Files.newInputStream(Paths.get("./img/ici.png"));
        Image imgIci = new Image(inIci);
        inIci.close();
        ImageView imgViewIci = new ImageView(imgIci);
        
        mapPane.getChildren().addAll(imgViewMap0,imgViewMap1,imgViewMapMoins1,imgViewIci);
        mapPane.setVisible(false);
        
        imgViewMap0.setOnMouseClicked(e -> {
            imgViewMap0.setVisible(false);
            mapPane.setVisible(false);
            afficheVie.setVisible(true);
            menu3.setVisible(true);
            imgViewText.setVisible(true);
        });
        
        imgViewMap1.setOnMouseClicked(e -> {
            imgViewMap1.setVisible(false);
            mapPane.setVisible(false);
            afficheVie.setVisible(true);
            menu3.setVisible(true);
            imgViewText.setVisible(true);
        });
        
        imgViewMapMoins1.setOnMouseClicked(e -> {
            imgViewMapMoins1.setVisible(false);
            mapPane.setVisible(false);
            afficheVie.setVisible(true);
            menu3.setVisible(true);
            imgViewText.setVisible(true);
        });
        
        btMap.setOnMouseClicked(e -> {
            if(moi.getIciPièce().getNiveau() == 0){
                imgViewMap0.setVisible(true);
                System.out.println(moi.getIciPièce().getNiveau());
            if(moi.getIciString().equals("pont")){
                imgViewIci.setTranslateX(200);
                imgViewIci.setTranslateY(520);
            }else if(moi.getIciString().equals("entree")){
                imgViewIci.setTranslateX(200);
                imgViewIci.setTranslateY(370);
            }else if(moi.getIciString().equals("bal")){
                imgViewIci.setTranslateX(200);
                imgViewIci.setTranslateY(270);
            }else if(moi.getIciString().equals("jardin")){
                imgViewIci.setTranslateX(200);
                imgViewIci.setTranslateY(130);
            }else  if(moi.getIciString().equals("cuisine")){
                imgViewIci.setTranslateX(350);
                imgViewIci.setTranslateY(270);
              }
            }else if(moi.getIciPièce().getNiveau() == 1){
                imgViewMap1.setVisible(true);
                if(moi.getIciString().equals("tourNord")){
                   imgViewIci.setTranslateX(170);
                   imgViewIci.setTranslateY(130);
                }else if(moi.getIciString().equals("trone")){
                   imgViewIci.setTranslateX(170);
                   imgViewIci.setTranslateY(210);
                }else if(moi.getIciString().equals("rempart")){
                   imgViewIci.setTranslateX(320);
                   imgViewIci.setTranslateY(150); 
                }else{               
                  imgViewIci.setTranslateX(165);
                  imgViewIci.setTranslateY(430);  
                }
            }else{
                imgViewMapMoins1.setVisible(true);
                if(moi.getIciString().equals("cellule")){            
                  imgViewIci.setTranslateX(250);
                  imgViewIci.setTranslateY(420);  
                }else if(moi.getIciString().equals("arme")){
                  imgViewIci.setTranslateX(250);
                  imgViewIci.setTranslateY(220); 
                }else {
                  imgViewIci.setTranslateX(250);
                  imgViewIci.setTranslateY(500);  
                }
            }
            System.out.println("Map");
            mapPane.setVisible(true);
            afficheVie.setVisible(false);
            menu3.setVisible(false);
            imgViewText.setVisible(false);
        });
        choixLigne.getChildren().addAll(choixColone1,choixColone2);
        choixLigne.setTranslateX(35);
        choixLigne.setTranslateY(420);
        menu3.getChildren().addAll(choixLigne);
     //-------------------------------------------------------------------------
     //-------------------------------------------------------------------------
        getChildren().addAll(
                groupImg,imgEnnemy,imgViewHero,imgViewText,afficheVie,tests,
                menu0,menu1,menu2,menu3,imgViewTrouve,Combat,inspect,imgViewInventaire,inventaireBox,boutonInv
                ,mapPane,pont,entreeC,balB,jardinJ,cuisineC,egoutE,armeA,rempartT
                ,celluleC,troneR,tourNordN,tourSudS,ennemiePvNom,terminus);
        
        }
        
       /*public void foundEnnemie(Playeur p,Chateau h) {
            Random rand = new Random();
            
    if (rand.nextInt(20) > 10){
            Ennemie enemy = new Ennemie(moi.getIciPièce().getNom());
            System.out.println("un ennemie vient d'apparaitre !\n Ennemie : " + enemy.getNom()); 
            boolean fuite = false;
            int rep ;
                do{
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
              //  this.creatItem(enemy.getDifficulté()).foundItem(p);     
                }

            
        }    
   }*/
    }
    public static void main(String[] args){
        launch(args);
    }
    
}