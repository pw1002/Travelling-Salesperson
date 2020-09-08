import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel{

	final private int sizeOfCity = 6;

	private static final long serialVersionUID = 1L;
	private int xPos, yPos;
	private ArrayList<Integer> allCoordinates = new ArrayList<Integer>();
	private ArrayList<City> shortestPath = new ArrayList<City>();
	private boolean line = false;

	public DrawingPanel() {
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(400,400));
	}

	//Adds the coordinates of the cities to the ArrayList allCoordinates and draws the dots(cities) in the ArrayList
	public void redraw(ArrayList<City> c, int nOfc) {				
		for(int i = 0; i < nOfc; i++) {
			xPos = c.get(i).getX();
			yPos = c.get(i).getY();
			allCoordinates.add(xPos);
			allCoordinates.add(yPos);
		}
		repaint();
	}

	//Adds cities to the ArrayList shortestPath and draws the lines in order of the cities in the ArrayList
	public void drawLine(ArrayList<City> c, int nOfc) {
		line = true;
		for(int i = 0; i < nOfc; i++) {
			shortestPath.add(c.get(i));
		}
		redraw(c, nOfc);
	}

	//resets the DrawingPanel by clearing the ArrayLists
	public void reset() {
		allCoordinates.clear();
		shortestPath.clear();
		repaint();
	}

	//Draws the lines and dots(cities) using the ArrayLists
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		for(int i = 0; i < allCoordinates.size()/2; i+=2) {
			g.fillRect(allCoordinates.get(i), allCoordinates.get(i+1), sizeOfCity, sizeOfCity);
			g.setColor(Color.BLACK);
		}
		if(line) {
			g.setColor(Color.BLACK);
			for(int i = 0; i < shortestPath.size()-1; i++) {
				g.drawLine(shortestPath.get(i).getX() + sizeOfCity/2, shortestPath.get(i).getY() + sizeOfCity/2,
						shortestPath.get(i+1).getX() + sizeOfCity/2, shortestPath.get(i+1).getY() + sizeOfCity/2);
			}
			line = false;
		}
	}

}
