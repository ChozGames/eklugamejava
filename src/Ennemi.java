import java.util.Random;

public abstract class Ennemi implements IEnnemi  {

    final static String LETTRES = "abcdefghijklmnopqrstuvwxyz";

    private String letters;
    private float posX;
    private float posY;

    /* CONSTRUCTEUR  */

    public Ennemi(int nbLetter, float nPosX, float nPosY) {
        letters = getRandomLetters(nbLetter);
        
        posX = nPosX;
        posY = nPosY;
    }

    /* GETTERS & SETTERS  */

    public String getLetters() {
        return letters;
    }

    public void setLetters(String nLetters) {
        letters = nLetters;
    }
    
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

    /* METHODS  */
    
    /**
     * Permet de générer une chaine de lettre aléatoirement
     * @param  nbLettre Nombre de lettre Ã  mettre dans la chaine
     * @return          Chaine de lettre généré aléatoirement
     */
    public String getRandomLetters(int nbLettre) {
        String randomLetters = "";
        Random rand = new Random();

        for(int i = 0; i < nbLettre; i++) {
            int n = rand.nextInt(LETTRES.length());
            randomLetters = randomLetters + LETTRES.charAt(n);
        }

        return randomLetters;
    }

    /**
     * Permet de récupérer la position Y des lettres d'un ennemi
     * @return Retourne une position
     */
    public float getPosYLetter(){
    	return (posY - 30);
    }

}