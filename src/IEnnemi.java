
public interface IEnnemi {
	public void moveLeft(Joueur joueur, int delta);
	public float getPosX();
	public float getPosY();
	public void setPosX(float nPosX);
	public void setPosY(float nPosY);
	public String getLetters();
	public void setLetters(String nLetters);
	public float getPosYLetter();
}
