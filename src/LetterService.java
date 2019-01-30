import java.util.List;

public abstract class LetterService {

    /**
     * Permet de savoir si le buffer de saisie correspond 
     * àau moin un des noms des ennemies 
     * @param  buffer  La chaine saisie par le joueur
     * @param  ennemis La liste des ennemies
     * @return         Retourne True si un ennemi correspond sinon false
     */
    public static boolean checkBuffer(String buffer, List<IEnnemi> ennemis) {

        for (IEnnemi e : ennemis) {
            if(buffer.equals(e.getLetters().substring(0,buffer.length())))
                return true;
        }

        return false;
    }

    /**
     * Permet de savoir si un ennemi peut être tuer ou non
     * @param  buffer La chaine saisie par le joueur
     * @param  ennemi L'ennemi qui est vérifié
     * @return        Retourne True si l'ennemi peut être tuer sinon False
     */
    public static boolean isKill(String buffer, IEnnemi ennemi) {
        return (buffer.equals(ennemi.getLetters()) ? true : false);
    } 

}