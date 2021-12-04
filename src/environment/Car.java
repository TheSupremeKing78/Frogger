package environment;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import frog.Frog;
import util.Case;
import util.Direction;
import gameCommons.Game;
import graphicalElements.Element;
import util.SpriteBank;

public class Car {
	private Game game;
	protected Case leftPosition;
	protected boolean leftToRight;
	protected int length;
	protected boolean isLog;
	//private final Color colorLtR = Color.BLACK;
	//private final Color colorRtL = Color.RED;
	//private final Color log = Color.ORANGE;
	private final BufferedImage carLtR1;
	private final BufferedImage carRtL1;
	private final BufferedImage carLtR2;
	private final BufferedImage carRtL2;
	private final BufferedImage log;

	//TODO Constructeur(s)
	public Car (Game g, Case lp, boolean lr, boolean iL){
		this.game = g;
		this.leftPosition = lp;
		this.leftToRight = lr;
		this.isLog = iL;
		this.length = game.randomGen.nextInt(2)+1;
		if(isLog) {
			this.length = game.randomGen.nextInt(2)+2;
		}
		this.carLtR1 = game.image.carSize1toRight;
		this.carRtL1 = game.image.carSize1toLeft;
		this.carLtR2 = game.image.carSize2toRight;
		this.carRtL2 = game.image.carSize2toLeft;
		this.log = game.image.logP;
	}
	//TODO : ajout de methodes
	public void move(boolean b){
		if(b) {
			Case c;
			Case pC = leftPosition;
			Frog f = (Frog) this.game.getFrog();
            Case pF = f.getPosition();
			if(this.leftToRight) {
				c = new Case(this.leftPosition.absc + 1, this.leftPosition.ord);
			} else {
				c = new Case(this.leftPosition.absc - 1, this.leftPosition.ord);
			} 
			if(isLog) {
				for(int i = 0; i<this.length; i++) {
					if((pF.ord == pC.ord && pF.absc == pC.absc+i)) {
						Direction d;
						if(leftToRight) {d = Direction.right;}
						else {d = Direction.left;}
						f.move(d);
						game.setFrog(f);
					}
				}
			}
			this.leftPosition = c;
            
		} 
		this.addToGraphics();
	}
	
	public boolean carOnCase(Case pos) {
		for(int i=0; i<this.length; i++){
			if(this.leftPosition.absc + i == pos.absc){
				return true;
			}
		}
		return false;
	}
	
	public boolean inScreen() {
		if(this.leftPosition.absc + this.length < 0 || this.leftPosition.absc > this.game.width){
			return false;
		}
		return true;
	}

	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		BufferedImage img = game.image.dirtP;
		if(this.isLog) {
			img = log;
		}else if (this.leftToRight) {
			if (this.length == 1) {
				img = carLtR1;
			} else if (this.length == 2) {
				img = carLtR2;
			}
		} else {
			if (this.length == 1) {
				img = carRtL1;
			} else if(this.length == 2){
				img = carRtL2;
			}
		}
		for (int i = 0; i < length; i++) {
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, img));
		}
	}

	public void changeCarOrd(int n) {
		Case c = new Case(this.leftPosition.absc, this.leftPosition.ord + n);
		this.leftPosition= c;
		
	}
	public ArrayList<Case> getCases() {
		ArrayList<Case> tab = new ArrayList<Case>();
		for(int i = 0; i<this.length; i++){
			Case a = new Case(this.leftPosition.absc+i , this.leftPosition.ord);
			tab.add(a);
		}
		return tab;
	}

}
