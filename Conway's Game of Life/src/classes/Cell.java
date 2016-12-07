package classes;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {

	private boolean alive, nextGen;
	private int x, y;

	public Cell(boolean alive, int x, int y) {
		this.x = x;
		this.y = y;
		this.alive = alive;
	}

	// Zustand Alive
	public boolean getAlive() {
		return alive;
	}

	// Zustand nextGen
	public boolean getnextGen() {
		return nextGen;
	}

	// Methode setAlive
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void setnextGen(boolean nextGen) {
		this.nextGen = nextGen;
	}

	// Zellenskin definieren
	public void paintCell(Graphics g) {
		if (alive) {
			g.setColor(Color.black);
		} else if (!alive)
			g.setColor(Color.gray);
		g.fillRect(x, y, 80, 80);

	}

}
