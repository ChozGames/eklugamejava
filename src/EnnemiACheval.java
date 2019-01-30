public class EnnemiACheval extends Ennemi{

    private static float VITESSEACHEVAL = .06f;

    public EnnemiACheval(int nbLetter, float nPosX, float nPosY) {
        super(nbLetter, nPosX, nPosY);
    }

    public static float getVitesse() {
        return VITESSEACHEVAL;
    } 
    
    /**
     * Permet de déplacer l'ennemi ainsi que controler sa position par rapport au joueur
     * @param  joueur Entité d'un joueur
     * @param  delta 
     */
    public void moveLeft(Joueur joueur, int delta) {
    	if(this.getPosX() > joueur.getPosX() + 50)
    		this.setPosX(this.getPosX() - VITESSEACHEVAL * delta);
    }

}