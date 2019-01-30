public class Joueur {

    float posX;
    float posY;
    int life;

    String letterBuffer;

    /* CONSTRUCTEUR  */

    public Joueur(int nLife, float nPosX, float nPosY) {
        life = nLife;
        posX = nPosX;
        posY = nPosY;
        letterBuffer = "";
    }

    /* GETTERS & SETTERS  */

    public float getPosX() {
        return posX;
    }

    public void setPosX(float nPosX) {
        posX = nPosX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float nPosY) {
        posY = nPosY;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int nLife) {
        life = nLife;
    }
    
    public String getLetterBuffer() {
        return letterBuffer;
    }

    public void setLetterBuffer(String nLetterBuffer) {
    	letterBuffer = nLetterBuffer;
    }

    /* METHODS */
    
    /**
     * Permet de remettre la saisie du joueur à zero
     */
    public void cleanBuffer() {
    	letterBuffer = "";
    }

    /**
     * Décremente la vie du joueur
     */
    public void takeHit() {
        life--;
    }

    /**
     * Permet de savoir si un joueur est mort ou non
     * @return return true si il est mort sinon false
     */
    public boolean isDead() {
        return ((life > 0) ? false : true);
    }

    /**
     * Permet d'ajouter une nouvelle lettre à la saisie d'un joueur
     * @param  nLetter Nouvelle lettre
     */
    public void addLetterBuf(char nLetter) {
        letterBuffer = letterBuffer + nLetter;
    }
    
    /**
     * Permet de remettre le joueur à zéro
     * @param  nLife nombre de vie
     */
    public void clean(int nLife){
    	life = nLife;
        letterBuffer = "";
    }


}