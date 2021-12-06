package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import graphicalElements.Element;
import util.Case;
import util.Direction;
import util.SpriteBank;

import java.awt.image.BufferedImage;

public class Frog implements IFrog {
	protected Case p;
	protected Game game;
	protected Direction m;
	public BufferedImage img;

	public Frog(Game game) {
		this.game = game;
		p = new Case(game.width / 2, 0);
		this.m = Direction.up;
		this.img =game.image.frogToUp ;

	}

	public Case getPosition() {
		return p;
	}

	public Direction getDirection() {
		return m;
	}

	public void move(Direction d) {
		if (d == Direction.up && p.ord + 1 < game.height) {
			p = new Case(p.absc, p.ord + 1);
			game.score += 1;
			this.img = game.image.frogToUp;
		} else if (d == Direction.down && p.ord > 0) {
			p = new Case(p.absc, p.ord - 1);
			game.score -= 1;
			this.img = game.image.frogToDown;
		} else if (d == Direction.left && p.absc > 0) {
			p = new Case(p.absc - 1, p.ord);
			this.img = game.image.frogToLeft;
		} else if (d == Direction.right && p.absc < game.width - 1) {
			p = new Case(p.absc + 1, p.ord);
			this.img = game.image.frogToRight;
		}

		this.m = d;
	}

	public void addToGraphics() {
		game.getGraphic()
				.add(new Element(this.getPosition(), this.img));
	}
}

