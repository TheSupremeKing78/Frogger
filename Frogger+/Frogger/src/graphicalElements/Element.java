package graphicalElements;

import java.awt.image.BufferedImage;

import util.Case;


public class Element extends Case {
        public BufferedImage img;

    public Element(int absc, int ord, BufferedImage img) {
        super(absc, ord);
        this.img = img;
    }
    public Element(Case c, BufferedImage img) {
        super(c.absc, c.ord);
        this.img = img;
    }
}
