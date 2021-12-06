package frog;

import util.Case;
import gameCommons.Game;
import gameCommons.IFrog;
import util.Direction;

public class FrogInf extends Frog implements IFrog {
	private int frogUpLim;
	private int frogDownLim;


	public FrogInf(Game game) {
		super(game);
		this.frogDownLim = 3;
		this.frogUpLim = 6;

	}

	public int getDownLim(){
		return this.frogDownLim;
	}
	

	public int getUpLim(){
		return this.frogUpLim;
	}


	public boolean goingUp(){
		return this.p.ord>this.frogUpLim;
	}

	public boolean goingDown(){
		return this.p.ord<this.frogDownLim;
	}


	public void setOrd(int o){
		this.p = new Case(this.p.absc, o);
	}


	public Case getPosition() {
		return super.getPosition();
	}

	public Direction getDirection() {
		return super.getDirection();
	}

	public void move(Direction key) {
		switch(key) {
		case up:
			this.p = new Case(this.p.absc, this.p.ord+1);
			this.img = game.image.frogToUp;
			game.score += 1;
			break;
		case down:
			if(this.p.ord != 0){
				this.p = new Case(this.p.absc, this.p.ord-1);
				this.img = game.image.frogToDown;
				game.score -= 1;
			}
			break;
		case right:
			if(this.p.absc != this.game.width){
				this.p = new Case(this.p.absc+1, this.p.ord);
				this.img = game.image.frogToRight;
			}
			break;
		case left:
			if(this.p.absc != 0){
				this.p = new Case(this.p.absc-1, this.p.ord);
				this.img = game.image.frogToLeft;
			}
			break;
		default :
			break;
		}
		this.m = key;
		this.game.testLose();
	}
}
