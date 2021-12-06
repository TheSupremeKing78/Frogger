package graphicalElements;

import javax.swing.*;

import gameCommons.IFrog;
import util.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;



public class FroggerGraphic extends JPanel implements IFroggerGraphics, KeyListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Element> elementsToDisplay;
	private ArrayList<Element> rivers;
	private ArrayList<Element>lines;
	private int pixelByCase = 16;
	private int width;
	private int height;
	private IFrog frog;
	private JFrame frame;
	public boolean inf;


	public FroggerGraphic(int width, int height) {
		this.width = width;
		this.height = height;
		elementsToDisplay = new ArrayList<Element>();
		rivers = new ArrayList<Element>();
		lines=new ArrayList<Element>();
		this.inf = true;


		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(width * pixelByCase, height * pixelByCase));

		JFrame frame = new JFrame("Frogger");
		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(this);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Element e;
		Element e2;
		Element e3;
		if (inf) {
			for (Element river : rivers) {
				e2 = river;
				g.drawImage(e2.img, pixelByCase * e2.absc, pixelByCase * (height - e2.ord - 1), this);
			}
			for (Element line : lines) {
				e3 = line;
				g.drawImage(e3.img, pixelByCase * e3.absc, pixelByCase * (height - e3.ord - 1), this);
			}
		}
		for (Element element : this.elementsToDisplay) {
			e = element;
			g.drawImage(e.img, pixelByCase * e.absc, pixelByCase * (height - e.ord - 1), this);
		}
		/*else {
			for (Element river : rivers) {
				e2 = river;
				if (!e2.river) {
					g.setColor(Color.blue);
					g.fillRect(pixelByCase * e2.absc, pixelByCase * (height - 1 - e2.ord), width * pixelByCase, pixelByCase - 1);
				}
			}*/

		/*for (int i = 0; i < elementsToDisplay.size(); i++) {
			e = elementsToDisplay.get(i);
			g.setColor(e.color);
			g.fillRect(pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), pixelByCase, pixelByCase - 1);
		}*/
		for (Element element : this.elementsToDisplay) {
			e = element;
			g.drawImage(e.img, pixelByCase * e.absc, pixelByCase * (height - e.ord - 1), this);
		}
	}







	public boolean isinf() { return this.inf ;}




	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			frog.move(Direction.up);
			break;
		case KeyEvent.VK_DOWN:
			frog.move(Direction.down);
			break;
		case KeyEvent.VK_LEFT:
			frog.move(Direction.left);
			break;
		case KeyEvent.VK_RIGHT:
			frog.move(Direction.right);
		}
	}

	public void clear() {
		this.elementsToDisplay.clear();
	}

	public void add(Element e) {
		this.elementsToDisplay.add(e);
	}
	
	public void addRivers(Element e) {
		this.rivers.add(e);
	}
	public void addLine(Element e){
		this.lines.add(e);
	}
	
	public void clearRivers() {
		this.rivers.clear();
	}
	public void clearLine(){
		this.lines.clear();
	}

	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	public void endGameScreen(String s) {
		frame.remove(this);
		JLabel label = new JLabel(s);
		label.setFont(new Font("Verdana", 1, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setSize(this.getSize());
		frame.getContentPane().add(label);
		frame.repaint();

	}

}
