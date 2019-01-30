public class EnnemiAPied extends Ennemi {

    private static float VITESSEAPIED = .04f;

    public EnnemiAPied(int nbLetter, float nPosX, float nPosY) {
        super(nbLetter, nPosX, nPosY);
    }

    public static float getVitesse() {
        return VITESSEAPIED;
    } 
    
    /**
     * Permet de déplacer l'ennemi ainsi que controler sa position par rapport au joueur
     * @param  joueur Entité d'un joueur
     * @param  delta 
     */
    public void moveLeft(Joueur joueur, int delta) {
    	if(this.getPosX() > joueur.getPosX() + 50)
    		this.setPosX(this.getPosX() - VITESSEAPIED * delta);
    }

}