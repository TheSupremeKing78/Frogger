package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import environment.Environment;
import environment.EnvironmentInf;
import frog.Frog;
import frog.FrogInf;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;
import util.SpriteBank;

public class Main {

	public static void main(String[] args) {

		//Caract�ristiques du jeu
		int width = 50;
		int height = 40;
		int tempo = 100;
		int minSpeedInTimerLoops = 3;
		double defaultDensity = 0.07;
		double defaultDensityRiver = 0.2;
		SpriteBank img = new SpriteBank();
		//Cr�ation de l'interface graphique
		final IFroggerGraphics graphic = new FroggerGraphic(width, height);
		//Cr�ation de la partie
		final Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity, defaultDensityRiver, img);
		if(!game.infini) {
			//Cr�ation et liason de la grenouille
			IFrog frog = new Frog(game);
			game.setFrog(frog);
			graphic.setFrog(frog);
			//Cr ́eation et liaison de l’environnement
			IEnvironment env = new Environment(game);
			game.setEnvironment(env);
		} else {
			//Création et liason de la grenouille
			IFrog frog = new FrogInf(game);
			game.setFrog(frog);
			graphic.setFrog(frog);
			//Création et liaison de l’environnement
			IEnvironment env = new EnvironmentInf(game);
			game.setEnvironment(env);
		}
		//Boucle principale : l'environnement s'acturalise tous les tempo milisecondes
		Timer timer = new Timer(tempo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.update();
				graphic.repaint();
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}
}
