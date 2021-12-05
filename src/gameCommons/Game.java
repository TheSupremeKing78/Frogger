package gameCommons;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;
import util.Case;
import util.SpriteBank;
import frog.Frog;
import gameCommons.IFrog;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;
	public final double defaultDensityRiver;
	public boolean infini;
	public int score;
	public double comp;
	public SpriteBank image;

	// Lien aux objets utilis�s
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;

	/**
	 * @param graphic             l'interface graphique
	 * @param width               largeur en cases
	 * @param height              hauteur en cases
	 * @param minSpeedInTimerLoop Vitesse minimale, en nombre de tour de timer avant d�placement
	 * @param defaultDensity      densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity, double defaultDensityRiver, SpriteBank image) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		this.defaultDensityRiver = defaultDensityRiver;
		//CHANGE
		this.infini = true;
		this.score = 0;
		this.image = image;
	}

	/**
	 * Lie l'objet frog � la partie
	 *
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	public IFrog getFrog() {
		return this.frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 *
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un �cran de fin appropri� si tel
	 * est le cas
	 *
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		Case position= this.frog.getPosition();
		if (this.environment.isSafe(position) == false){
			if (this.infini==false) {
				this.graphic.endGameScreen("You failed, try again ! \n Your time : " + (int)comp + "s" );
				return true;
			}
			else{
				this.graphic.endGameScreen("You failed, try again ! \n Score : " + this.score + "\n Your time :"+ (int)comp + "s");
				return true;

			}
		}
		return false;
	}

	/**
	 * Teste si la partie est gagnee et lance un �cran de fin appropri� si tel
	 * est le cas
	 *
	 * @return true si la partie est gagn�e
	 */
	public boolean testWin() {
		Case position = frog.getPosition();
		if (this.environment.isWinningPosition(position)) {
			this.graphic.endGameScreen("Congratulation :) !!!\n Your time : " + (int)comp + "s" );
			return true;
		}
		return false;
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {
		if (!testLose()) {
			graphic.clear();
			environment.update();
			frog.addToGraphics();
			testLose();
			testWin();
			if (testLose() == false) {
				comp += 0.1;
			}

		}

	}
}
