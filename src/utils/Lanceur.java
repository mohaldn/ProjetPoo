package src.utils;
import java.util.Scanner;

import src.gameobject.Joueur;
import src.gameobject.Plateau;
import src.gameobject.ObjDominos.TuileDominos;

public class Lanceur {

    public static void joueurTuile(Plateau p, Joueur j){
        Scanner askX = new Scanner(System.in);
        Scanner askY = new Scanner(System.in);
        System.out.println("Quelle position x ? ");
        int posX=askX.nextInt();
        System.out.println("Quelle position y ? ");
        int posY=askY.nextInt();
        if(!(j.placerTuiles(p, posY, posX))){
            Scanner otherMove = new Scanner(System.in);
            System.out.println("Placement incorrect ! Replacer(r), tourner (t) ou deffausser(d)  ");
            String choice = otherMove.nextLine();
            if(choice.equals("r")){
                joueurTuile(p, j);
            } 
            else if(choice.equals("t")){

            }
        }
        
    }
    public static void tourner(Plateau p, Joueur j){
        ((TuileDominos)j.getTuileCourante()).tourneTuile();
        System.out.println(j.getTuileCourante());
        Scanner retourne = new Scanner(System.in);
        System.out.println("Tourner une fois de plus : oui (o) ou non (n)");
        String s=retourne.nextLine();
        if(s.equals("n")){
            joueurTuile(p, j);
        }
        else{
            tourner(p, j);
        }
    }
    public static void defausser(Plateau p, Joueur j){
        p.defausser(j.getTuileCourante());
        j.setTuileCourante(null);
    }
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        Scanner myName =new Scanner(System.in);
        System.out.println("Nombre de joueur:");
        int nbJoueur = myObj.nextInt();
        Joueur[] joueurs = new Joueur[nbJoueur];
        int nb=0;
        for (int i = 0; i < nbJoueur; i++) {
            System.out.println("Joueur(1) ou IA(0) :");
            int bot = myObj.nextInt();
            if (bot == 0) {
                joueurs[i] = new Joueur(true, "Robot "+ nb, null);
                nb++;
            } else {
                System.out.println("Choisir un pseudo : ");
                String nom = myName.nextLine();
                joueurs[i] = new Joueur(false, nom, null);
            }
        }
        System.out.println("Nombre de tuile : ");
        Scanner nbtuilesca = new Scanner(System.in);
        int nbtuile = myObj.nextInt();

        Plateau p = new Plateau(nbtuile, false);
        int i=0;
        Scanner myObj2 = new Scanner(System.in);

        while (p.resteTuiles()) {
             if(joueurs[i].isBot()){
                System.out.println("Au tour du joueur "+(i+1)+" \""+joueurs[i].getNom() +"\""+"\n Score : "+ "\nScore : " +joueurs[i].getScore());
                joueurs[i].piocher(p);
                defausser(p, joueurs[i]);
             }
             else {
                joueurs[i].setTuileCourante(p.piocher());
                System.out.println(p);
                System.out.println("Au tour du joueur "+(i+1)+" \""+joueurs[i].getNom() +"\""+"\nScore : " +joueurs[i].getScore());
                System.out.println("Tuile piochée : ");
                System.out.println(joueurs[i].getTuileCourante());
                System.out.println("Tourner(t), Jouer(j) ou défausser(d):");
                String tour = myObj2.nextLine();
                if(tour.equals("d")){
                    defausser(p, joueurs[i]);
                }
                else if(tour.equals("j")){
                    joueurTuile(p, joueurs[i]);
                }
                else{
                    tourner(p, joueurs[i]);
                }
                }
                if(i>=joueurs.length-1){i=0;}
                else {i++;}
            }

        for (int k = 0; k < joueurs.length - 1; k++)  {
                int index = k;  
                for (int j = i + 1; j < joueurs.length; j++){
                    if (joueurs[j].getScore() < joueurs[index].getScore()){ 
                        index = j;
                    }
                }
        
                Joueur temp = joueurs[index];
                joueurs[index] = joueurs[i]; 
                joueurs[i] = temp;
        }
        System.out.println("Classement :" );
        for (int k = joueurs.length-1; k >= 0; k--)  {
            System.out.println((joueurs.length-k)+" : "+ joueurs[k].getNom()+ ", Score : "+ joueurs[k].getScore());
        }
    }
        

        // Numerical input
    }
