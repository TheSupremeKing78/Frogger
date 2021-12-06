package environment;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import graphicalElements.Element;
import util.Case;
import gameCommons.Game;
import java.awt.image.BufferedImage;

public class Lane {
	protected Game g;
	protected int ord;
	private int speed;
	protected ArrayList<Element>c;
	protected ArrayList<Car> cars;
	protected boolean leftToRight;
	protected double density;
	private int compt=0;
	private Random r = new Random();
	public Boolean isRiver;
	
	// TODO : Constructeur(s)
	public Lane (Game g2, int ord, double density, Boolean iR) {
		this.g = g2;
		this.ord = ord;
		this.speed = r.nextInt(3) + 2;
		this.cars = new ArrayList<Car>();
		this.leftToRight = r.nextBoolean();
		this.density = density;
		this.isRiver = iR;
		this.mayAddCar();
		for (int i = 1; i < g.width; i++) {
			this.mayAddCar();
			this.moveCars(true);
		}
		this.c = new ArrayList<Element>(0);
		if (!g.infini) {
			if (iR) {
				Element e;
				for (int i = 0; i < g.width; i++) {
					e = new Element(i, ord, g.image.waterP);
					this.c.add(e);
				}
				System.out.println(c);
			} else {
				Element e;
				for (int i = 0; i < g.width; i++) {
					e = new Element(i, ord, g.image.roadP);
					this.c.add(e);
				}
				System.out.println(c.size());
			}
		}
	}
	
	public void removeCars(){
		for(int i = 0; i<this.cars.size(); i++){
			if(!this.cars.get(i).inScreen()){
				this.cars.remove(this.cars.get(i));
			}
		}
	}
	
	private void moveCars(boolean b) {
		for (Car c : this.cars) {
			c.move(b);
		}
		this.removeCars();
	}
	
	
	public void update() {

		// TODO
		// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
		// d'horloge" �gal � leur vitesse
		// Notez que cette m�thode est appel�e � chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut �tre ajout�e
		
		this.compt++;
        if (this.compt <= this.speed) {
            this.moveCars(false);
        } else {
            this.moveCars(true);
            this.mayAddCar();
            this.compt = 0;
        }
	}
	
	public boolean isSafe(Case pos){
		if(!isRiver) {
			for(int i = 0; i<this.cars.size(); i++){
				if(this.cars.get(i).carOnCase(pos)){
				return false;
				}
			}
			return true;
		} else {
			for(int i = 0; i<this.cars.size(); i++){
				if(this.cars.get(i).carOnCase(pos)){
				return true;
				}
			}
			return false;
		}
	}
	


	
	// TODO : ajout de methodes
	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if ((isSafe(getFirstCase()) && isSafe(getBeforeFirstCase()) && !isRiver) || !isSafe(getFirstCase()) && !isSafe(getBeforeFirstCase()) && isRiver) {
			if (g.randomGen.nextDouble() < density) {
				if(isRiver) {
					cars.add(new Car(this.g, getBeforeFirstCase(), leftToRight, true));
				} else {
					cars.add(new Car(this.g, getBeforeFirstCase(), leftToRight, false));
				}
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(g.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(g.width, ord);
	}
	

	public ArrayList<Car> getCars(){
		return this.cars;
	}
	
	public void changeOrd(int n){
		this.ord = this.ord + n;
		for (Car car :cars) {
			car.changeCarOrd(n);
		}
	}



	public int getOrd() {
		return this.ord;
	}


}
