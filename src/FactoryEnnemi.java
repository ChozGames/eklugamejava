import java.util.Random;


public abstract class FactoryEnnemi {
	
	public static IEnnemi build(int nbLetter, int posY) {
		Random rand = new Random();
		int z = rand.nextInt(2);
		
		/* Génération aléatoire du type d'ennemi */
		if (z==0)
			return new EnnemiAPied(nbLetter,500,posY);
		else
			return new EnnemiACheval(nbLetter,500,posY);
	}
}
