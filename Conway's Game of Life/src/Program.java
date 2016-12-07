import java.awt.*;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import classes.WindowClosingAdapter;
import classes.Cell;
import Interfaces.MyInterface;

public class Program extends JFrame implements Runnable, MouseListener, KeyListener {

	// Array neue Zelle definieren
	private Cell[][] cell = new Cell[80][80];

	// Game-Start mit Leertaste
	private boolean start = false;

	// Programm Titel
	public Program(String title) {
		super(title);

		// Zellen durch Mausklick initialisieren
		initCells();
		this.addMouseListener(this);
		this.setFocusable(true);
		this.addKeyListener(this);
		addMouseListener(this);

	}

	// Neue Zellen
	public void initCells() {
		for (int x = 0; x < 80; x++) {
			for (int y = 0; y < 80; y++) {
				cell[x][y] = new Cell(false, x * 20, y * 20);
			}
		}
	}

	// Allgemeine Paint-Befehle
	public void paint(Graphics g) {
		super.paintComponents(g);

		// Hintergrund
		g.setColor(Color.gray);
		g.fillRect(0, 0, 1000, 1000);

		// Zelle (selbe Schleife wie Initialisierung)
		for (int x = 0; x < 80; x++) {
			for (int y = 0; y < 80; y++) {
				// Zelle zeichnen
				cell[x][y].paintCell(g);
			}
		}

		// Rastermuster
		g.setColor(Color.BLACK);
		for (int x = 0; x < 80; x++) {
			g.drawLine(20 * x, 0, 20 * x, 1000);

			for (int y = 0; y < 80; y++) {
				g.drawLine(0, 20 * y, 1000, 20 * y);

			}
		}
	}

	// Main Programm
	public static void main(String[] args) {

		// New Game
		Program game = new Program("Das Spiel des Lebens!");
		// Fenster
		game.addWindowListener(new WindowClosingAdapter(true));
		game.setSize(1000, 1000);
		game.setVisible(true);
		game.setLocation(330, 10);
		// Thread starten = In-Programm-Ausführung
		new Thread(game).start();
	}

	// Methode getNeighbours
	public void getNeighbours() {
		for (int x = 0; x < 80; x++) {
			for (int y = 0; y < 80; y++) {

				// Prüfen lebender Zellen
				int count = 0;
				if (cell[x][y].getAlive()) {
					if (cell[x - 1][y].getAlive()) {
						count++;
					}
					if (cell[x + 1][y].getAlive()) {
						count++;
					}
					if (cell[x][y - 1].getAlive()) {
						count++;
					}
					if (cell[x][y + 1].getAlive()) {
						count++;
					}
					if (cell[x - 1][y - 1].getAlive()) {
						count++;
					}
					if (cell[x + 1][y + 1].getAlive()) {
						count++;
					}
					if (cell[x - 1][y + 1].getAlive()) {
						count++;
					}
					if (cell[x + 1][y - 1].getAlive()) {
						count++;
					}

					// Spielbedingungen

					// Einsamkeit
					if (count < 2) {
						cell[x][y].setnextGen(false);
					}
					// Weiterleben
					if (count == 2 || count == 3) {
						cell[x][y].setnextGen(true);
					}
					// Überbevölkerung
					if (count > 3) {
						cell[x][y].setnextGen(false);
					}
				}
				// Prüfen toter Zellen
				int count2 = 0;
				if (x >= 1 && y >= 1 && x <= 78 && y <= 78) {
					if (!cell[x][y].getAlive()) {
						if (cell[x - 1][y].getAlive()) {
							count2++;
						}
						if (cell[x + 1][y].getAlive()) {
							count2++;
						}
						if (cell[x][y - 1].getAlive()) {
							count2++;
						}
						if (cell[x][y + 1].getAlive()) {
							count2++;
						}
						if (cell[x - 1][y - 1].getAlive()) {
							count2++;
						}
						if (cell[x + 1][y + 1].getAlive()) {
							count2++;
						}
						if (cell[x - 1][y + 1].getAlive()) {
							count2++;
						}
						if (cell[x + 1][y - 1].getAlive()) {
							count2++;
						}
						if (count2 == 3) {
							cell[x][y].setnextGen(true);
						}
					}
				}
			}
		}
	}

	// Automatisch eingefügt durch "Implements Runnable"
	@Override
	public void run() {
		while (true) {

			// CODE
			if (start) {
				System.out.println("hello");
				getNeighbours();
				for (int x = 0; x < 80; x++) {
					for (int y = 0; y < 80; y++) {
						cell[x][y].setAlive(cell[x][y].getnextGen());
					}
				}
			}
			// Nach Code-Durchlauf: Repaint
			repaint();

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	// Automatisch eingefügt durch "Mouse Listener"
	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent me) {
		int x = me.getX() / 20;
		int y = me.getY() / 20;
		cell[x][y].setAlive(true);
		System.out.println("X: " + x + " Y: " + y);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void keyPressed(KeyEvent k) {
		if (k.getKeyCode() == 32) {
			start = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
