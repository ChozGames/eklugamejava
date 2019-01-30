import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

public class Run extends BasicGame{
	private static int newLettreTime = 10;
	private static int ecartNewEnnemi = 2;
	
	private static int nbLife = 5;
	
	private static int hScreenSize = 720;
	private static int vScreenSize = 534;
		
	private GameContainer container;
	private Image ennemiSprite;
	private Image joueurSprite;
	private Image heartSprite;
	private Image background;
	
	private Joueur joueur;
	
	private int time;
	
	private int timeGenerateEnnemi;
	private boolean canGenerate;
	
	private int nbLetter;
	
	List<IEnnemi> ennemies;
	
	private boolean isStart;
	
	public static void main(String[] args) {

    	System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "lib/natives"), LWJGLUtil.getPlatformName()).getAbsolutePath());

        /* Code du jeu */
        AppGameContainer screen = null;
        
        try {
            screen = new AppGameContainer(new Run(), hScreenSize, vScreenSize, false);
            screen.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }

    public Run() {
        super("Slick Game Test");
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException
    {
    	/* Initialisation des variables globals au jeu */
    	this.nbLetter = 1;
    	this.time = 0;
    	this.timeGenerateEnnemi = 3;
    	this.canGenerate = true;
    	this.isStart = false;
    	
    	this.container = gc;
    	container.setShowFPS(false);

    	/* Récupération des images du jeu */
    	this.ennemiSprite = new Image("sprites/ennemi.png");
    	this.joueurSprite = new Image("sprites/joueur.png");
    	this.heartSprite = new Image("sprites/heart.png");
    	this.background = new Image("sprites/bg_cb.png");
    	
    	/* Initialisation de la liste des joueur qui va accueillir */
    	ennemies = new ArrayList<IEnnemi>();
    	
    	/* Instantiation du joueur */
    	this.joueur = new Joueur(nbLife, 20, 100);
    }
    
    public void render(GameContainer gmc, Graphics gr) throws SlickException
    {
    	this.background.draw(0,0);
    	
    	/* Gestion de l'affichage des différents menu [Jeu, Fin, Accueil] */
    	if (joueur.getLife() > 0 && isStart) {
    		
    		/* Affichage des élements contituant un joueur */
	    	 this.joueurSprite.drawCentered(this.joueur.getPosX() + 20,(vScreenSize/2));
	    	 this.heartSprite.drawCentered(this.joueur.getPosX()+10,(vScreenSize/2) - 40);
	    	 gr.drawString(Integer.toString(joueur.getLife()), this.joueur.getPosX() + 20, (vScreenSize/2) - 50);
	    	 
	    	 /* Affichage des ennemies */
	    	 for(IEnnemi e : ennemies) {
	    		 this.ennemiSprite.draw(e.getPosX(),e.getPosY());
	    		 gr.drawString(e.getLetters(),e.getPosX(), e.getPosYLetter());
	    	 }
	    	 
	    	 /* Affichage des lettres saisies */
	    	 gr.drawString(joueur.getLetterBuffer(), 200, 10);
	    	 
	    	 /* Affichage du temps */
	    	 gr.drawString(Integer.toString(this.time/1000), 400, 10);
	    	 
    	} else if (joueur.getLife() < 1 && isStart){
    		gr.drawString("Vous avez Perdu", 300, 200);
    	} else {
    		gr.drawString("Appuyer sur entrer pour commencer", 300, 200);
    	}
    }
    
    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
    	/* On n'effectue pas de modification tant que le jeu n'est pas démarré */
    	if (joueur.getLife() < 1 || !isStart)
    		return;
    		
    	this.time += delta;
    	
    	/* Augmentation du nombre de lettre par ennemi */
    	if((this.time/1000) > this.nbLetter * newLettreTime)
    		this.nbLetter++;
    	
    	/* Génération aléatoire d'un ennemi */
    	if(this.canGenerate) {
    		Random rand = new Random();
    		int n = rand.nextInt(vScreenSize - 100) + 50;
    		
    		/* On recupere un ennemi depuis la fabrique */
    		IEnnemi ennemiAdded = FactoryEnnemi.build(this.nbLetter, n);
    		
    		ennemies.add(ennemiAdded);
    		
    		this.canGenerate = false;
    		this.timeGenerateEnnemi = 0;
    	} else {
    		
    		/* Vérification de l'interval entre chaque apparition 
    		 * Définie si oui ou non on peu générer un nouveau ennemi*/
    		this.timeGenerateEnnemi += delta;
    		if((this.timeGenerateEnnemi/1000) >= ecartNewEnnemi)
    			this.canGenerate = true;
    	}
    		
    	/* Gestion du déplacement des ennemies */
    	for(IEnnemi e : ennemies) {
    		e.moveLeft(this.joueur, delta);
    	}

    	/* Gestion du contenu de la saisie du joueur */
    	if(!LetterService.checkBuffer(joueur.getLetterBuffer(), ennemies))
    		joueur.cleanBuffer();
    	
    	/* Gestion de la suppression (tué) d'un ennemi */
    	for(IEnnemi e : ennemies) {
    		if(LetterService.isKill(joueur.getLetterBuffer(), e)) {
    			ennemies.remove(e);
    			break;
    		}
    		
    		if(e.getPosX() < joueur.getPosX() + 50) {
    			this.joueur.takeHit();
    			ennemies.remove(e);
    			break;
    		}
    			
    	}
    		
    }
    
    @Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			container.exit();
		}
		
		/* Récupération des lettres saisie par le joueur */
		if (isStart) {
			if(Ennemi.LETTRES.indexOf(c) >= 0) {
				joueur.addLetterBuf(c);
			}
		}
		
		if (!isStart) {
			if(Input.KEY_SPACE == key)
				this.isStart = true;
		}
		
		/* Gestion de la relance d'une nouvelle partie */
		if (joueur.getLife() < 1 && isStart) {
			if(Input.KEY_SPACE == key) {
				ennemies.clear();
				this.nbLetter = 1;
		    	this.time = 0;
		    	this.timeGenerateEnnemi = 3;
		    	this.canGenerate = true;
		    	this.isStart = false;
		    	this.joueur.clean(nbLife);
			}
		}

	}


    
}
