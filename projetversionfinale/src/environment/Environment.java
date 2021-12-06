package environment;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import graphicalElements.Element;

public class Environment implements IEnvironment {
    //TODO
    protected Game game;
    protected ArrayList<Lane> lanes;
    private final BufferedImage water;
    private final BufferedImage road;

    public Environment(Game g) {
        this.game = g;
        this.lanes = new ArrayList<>();
        lanes.add(new Lane(game, 0, 0, false));
        double d = g.defaultDensity;
        double dR = g.defaultDensityRiver;
        for (int i = 1; i < g.height - 1; i++) {
            int r = game.randomGen.nextInt(5);
            if (r == 1) {
                lanes.add(new Lane(game, i, dR, true));

            } else {
                lanes.add(new Lane(game, i, d, false));
            }
        }
        lanes.add(new Lane(game, g.height - 1, 0, false));
        this.road = game.image.roadP;
        this.water = game.image.waterP;
    }

    @Override
    public boolean isSafe(Case c) {
        return this.lanes.get(c.ord).isSafe(c);
    }

    @Override
    public boolean isWinningPosition(Case c) {
        if (c.ord == game.height - 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        this.addToGraphicsL();
        for (int i = 1; i < this.game.height - 1; i++) {
            this.lanes.get(i).update();

        }
    }

    public ArrayList<Lane> getLanes() {
        return this.lanes;
    }

    public void addToGraphicsL() {
        ArrayList<Lane> lanes = this.game.environment.getLanes();

        for (int i = 0; i < lanes.size(); i++) {
            Lane l = lanes.get(i);
            for (int j = 0; j < this.game.width; j++) {
                game.getGraphic().add(l.c.get(j));
                ;
            }
        }
    }
}



