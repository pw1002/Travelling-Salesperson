public class City{
	int xPos, yPos;
	
	City(int x, int y){
		xPos = x;
		yPos = y;
	}
	
	//returns x-position of the city
	public int getX() {
		return xPos;
	}
	
	//returns y-position of the city
	public int getY() {
		return yPos;
	}
	
	//prints out the x and y position of the city in the console
	public String toString() {
		return "X: " + xPos + " Y: " + yPos;
	}
	
}
