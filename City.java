public class City {
	private int id;
    private int x;
    private int y;
    private boolean isVisited;
 
    public City(int id, int x, int y){ //Constructor method of city class.
    	this.id = id; 
    	this.x = x; 
    	this.y = y; 
    	this.isVisited = false;
    }

    public int distanceToCity(City city) { //This method computes the distance between 2 cities.
        int x = Math.abs(this.getX() - city.getX());
        int y = Math.abs(this.getY() - city.getY());
        int result = (int) Math.round((Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)))); //Rounds the result of sqrt.
        return result;
    }

	public int getX() { 
		return this.x;
	}
	public void setX(int x) {
		this.x=x;
	}
	
	public int getY() {
		return this.y;
	}
	public void setY(int y) {
		this.y=y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	
}