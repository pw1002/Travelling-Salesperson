import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TSP implements Runnable{

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new TSP());

	}

	private ControlPanel controlPanel;
	private DrawingPanel drawingPanel;
	private JFrame frame;

	@Override
	public void run() {
		//////////////////SetUp/////////////////////////////////////////////
		frame = new JFrame("Travelling Salesperson");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		controlPanel = new ControlPanel();
		frame.add(controlPanel.getPanel(), BorderLayout.LINE_START);

		drawingPanel = new DrawingPanel();
		frame.add(drawingPanel, BorderLayout.CENTER);

		//////////////////////ActionListener/////////////////////////////////
		controlPanel.getButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Stores the number of cities the user inputs
				int nOfc = controlPanel.getNumOfCities();
				//Creates an ArrayList which has a size same as the number of cities
				ArrayList<City> c = new ArrayList<City>(nOfc);

				//Clear the DrawingPanel each time the button is clicked (avoid overlap in drawings)
				drawingPanel.reset();				

				//Give random coordinates for each city
				for(int i = 0; i < nOfc; i++) {
					int randX = randomPosition();
					int randY = randomPosition();
					if(i > 0) {
						if(isRepeated(randX, randY, c)) {
							i--;
						}
						else {
							c.add(i, new City(randX,randY));
						}
					}
					else {
						c.add(i, new City(randX,randY));
					}
				}
				//Redraws the DrawingPanel given all the coordinates
				drawingPanel.redraw(c, nOfc);

				//Creating an ArrayList to store the cities using nearest neighbor method
				ArrayList<City> optimizedPath = new ArrayList<City>();
				City tempCity;
				optimizedPath.add(c.get(0));
				c.remove(0);

				//Moves nearest neighbor from the first city(c[0]) into ArrayList optimizedPath from ArrayList c
				for(int i = 0; i < nOfc; i++) {
					c.trimToSize();
					optimizedPath.trimToSize();
					tempCity = findShortestCity(optimizedPath.get(optimizedPath.size()-1), c);
					optimizedPath.add(tempCity);
					c.remove(tempCity);
				}

				//Draw the panel again with the cities as well as the lines which denotes the shortest path
				drawingPanel.drawLine(optimizedPath, nOfc);
			}
		});

		//////////////////////////////////////////////////////////////////////

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	//Generates and returns a random integer position for cities within the	DrawingPanel dimensions
	private int randomPosition() {
		return (int)(Math.random() * 396);
	}

	//Returns the distance between 2 cities
	private double getDistance(City a, City b) {
		double distance;

		double base = Math.abs(b.getX() - a.getX());
		double height = Math.abs(b.getY() - a.getY());
		double hypotenuse = Math.sqrt(base*base + height*height);

		distance = hypotenuse;
		return distance;
	}

	//Returns the city with in ArrayList c that is the shortest distance away from City startPoint
	private City findShortestCity(City startPoint, ArrayList<City> c) {
		double distance, shortestDistance = 0;
		City shortestCity = startPoint;

		for(int i = 0; i < c.size(); i++) {
			distance = getDistance(startPoint, c.get(i));
			if(shortestDistance == 0) {
				shortestDistance = distance;
				shortestCity = c.get(i);
			}
			else if(distance < shortestDistance){
				shortestDistance = distance;
				shortestCity = c.get(i);
			}
		}

		return shortestCity;
	}

	//returns true if the coordinates have been repeated, otherwise return false
	public boolean isRepeated(int x, int y, ArrayList<City> c) {
		for(int i = 0; i < c.size(); i++) {
			if(x == c.get(i).getX() && y == c.get(i).getY()) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}

}