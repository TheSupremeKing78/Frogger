package environment;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import frog.FrogInf;
import gameCommons.Game;
import gameCommons.IEnvironment;
import graphicalElements.Element;
import util.Case;

public class EnvironmentInf extends Environment implements IEnvironment {

	private int decalage;

	public EnvironmentInf(Game g) {
		super(g);
		this.game = g;
		this.lanes = new ArrayList<Lane>();
		lanes.add(new Lane(game, 0, 0, false));
		double d = g.defaultDensity;
		double dR = g.defaultDensityRiver;
		decalage = 0;
		for (int i = 1; i < g.height + 1; i++) {
			int r = game.randomGen.nextInt(5);
			if (r == 1) {
				lanes.add(new Lane(game, i, dR, true));
			} else {
				lanes.add(new Lane(game, i, d, false));
			}
		}
	}

	public boolean isSafe(Case p) {
		Lane current = this.lanes.get(p.ord + decalage);
		if (current.isRiver) {
			for (Car c : current.cars) {
				for (int lr = 0; lr < c.length; lr++) {
					if (c.leftPosition.absc + lr == p.absc) {
						return true;
					}
				}
			}
			return false;
		} else {
			for (Car c : current.cars) {
				for (int lr = 0; lr < c.length; lr++) {
					if (c.leftPosition.absc + lr == p.absc) {
						return false;
					}
				}
			}
			return true;
		}
	}


	public void update() {
		if (((FrogInf) this.game.getFrog()).goingUp()) {

			((FrogInf) this.game.getFrog()).setOrd(this.game.getFrog().getPosition().ord - 1);

			for (int l = 0; l < this.lanes.size(); l++) {
				this.lanes.get(l).changeOrd(-1);
			}
			decalage++;
			int r = game.randomGen.nextInt(5);
			if (r == 1) {
				this.lanes.add(new Lane(this.game, this.game.height, this.game.defaultDensityRiver, true));
			} else {
				this.lanes.add(new Lane(this.game, this.game.height, this.game.defaultDensity, false));
			}

		}
		if (((FrogInf) this.game.getFrog()).goingDown()) {
			if (this.lanes.get(((FrogInf) this.game.getFrog()).getDownLim()).getOrd() < ((FrogInf) this.game.getFrog()).getDownLim()) {
				for (int l = 0; l < this.lanes.size(); l++) {
					this.lanes.get(l).changeOrd(1);
				}
				((FrogInf) this.game.getFrog()).setOrd(this.game.getFrog().getPosition().ord + 1);
				decalage--;
			}
		}
		game.getGraphic().clearRivers();
		game.getGraphic().clearLine();
		for (Lane lane : this.lanes) {
			lane.update();
		}
		this.addRivers();
		this.addLine();
	}

	private void addRivers() {
		for (Lane a : this.lanes) {
			int oL = a.getOrd();
			if (a.isRiver) {
				Element e;
				for (int i = 0; i < game.width; i++) {
					e = new Element(i, oL, game.image.waterP);
					game.getGraphic().addRivers(e);
				}
			}
		}
	}

	private void addLine() {
		for (int k = 0; k < this.lanes.size(); k++) {
			Lane a = this.lanes.get(k);
			int oL = this.lanes.get(k).getOrd();
			if (!this.lanes.get(k).isRiver) {
				Element e;
				for (int i = 0; i < game.width; i++) {
					if (oL == 0) {
						e = new Element(i, oL, game.image.dirtP);
						game.getGraphic().addLine(e);
					} else {
						e = new Element(i, oL, game.image.roadP);
						game.getGraphic().addLine(e);
					}
				}
			}
		}
	}
}



