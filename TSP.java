import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TSP {
	
	static int totalDistance;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<City> cities = readFileAndCreateCities();
		int numOfCities = cities.size();
		int[][] distanceMatrix = createDistanceMatrix(cities, numOfCities);
		int[] path = tsp(numOfCities, distanceMatrix, cities);
		
		// Store console print stream.
		PrintStream ps_console = System.out;
		File file = new File("test-output-5.txt");
        FileOutputStream fos = new FileOutputStream(file);
        // Create new print stream for file.
        PrintStream ps = new PrintStream(fos);
        // Set file print stream.
        System.setOut(ps);
        
		printPath(path, numOfCities);

		System.setOut(ps_console);
		System.out.println("Finished.");
				
	}

	public static ArrayList<City> readFileAndCreateCities(){ // This method reads input file and creates cities arraylist.
		
		//System.out.println("Please enter the path."); // Asks user for input datapath.
		Scanner scanner = new Scanner(System.in); 
		String inputFile = scanner.nextLine(); // Gets data path.
		//String inputFile ="C:\\Users\\osman\\Desktop\\Algo3\\a\\test-input-3.txt";
		scanner.close(); // Closes scanner object.
		
		ArrayList<City> cities = new ArrayList<City>(); // Creates cities arraylist. 
		
		BufferedReader reader; // Creates buffered reader object.
		
		try {
			
			reader = new BufferedReader(new FileReader(inputFile)); // Initializes buffered reader.
			String line = reader.readLine(); // Reads first line.
			String[] splittedLine = new String[3]; // Creates an array object which holds line as different objects
			City city; // Creates city object.
			int id=0, x=0, y=0; // Initializes variables.
			
			while (line != null) { // Loop to read whole file.
				
				splittedLine = line.trim().split("\\s+"); // Split line by space.
				id = Integer.parseInt(splittedLine[0]); // assigns id.
				x = Integer.parseInt(splittedLine[1]); // assigns x coordinate.
				y = Integer.parseInt(splittedLine[2]); // assigs y coordinate.
				city = new City(id, x, y); // Construct city object for given id, x, and y values. 
				cities.add(city); // adds city to cities arraylist.
				
				// read next line
				line = reader.readLine(); // Reads new line.				
				
			}
			
			reader.close(); // closes buffered reader.
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// prints cities arraylist 
//		for(City c: cities) {
//			System.out.println(c.getId() + "    " + c.getX() + "    " + c.getY());
//		}
		
		return cities;
	}
		
	public static int[][] createDistanceMatrix(ArrayList<City> cities, int numOfCities){
		int[][] distanceMatrix = new int[numOfCities+1][numOfCities+1];
		int distance = 0;
		
		for(int i=0 ; i<numOfCities ; i++) {
			for(int j=i ; j<numOfCities ; j++) {
				if(i == j) { // means same city. its distance to itself is 0.
					distanceMatrix[i][j] = 0; 
				}
				else { 
					distance = cities.get(i).distanceToCity(cities.get(j)); // Calculates distance between city i to city j
					distanceMatrix[i][j] = distance; // fills adj matrix
					distanceMatrix[j][i] = distance; // fills adj matrix
				}	
			}
		}
		
		// prints adj matrix
//		for(int i=0 ; i<numOfCities ; i++) {
//			for(int j=0 ; j<numOfCities ; j++) {
//				System.out.print(distanceMatrix[i][j] + " ");
//			}
//			System.out.println("");
//		}
		
		return distanceMatrix;
	}
	
	public static int[] tsp(int numOfCities, int[][] distanceMatrix, ArrayList<City> cities) {
		int[] path = new int[numOfCities];

		path[0] = 0;
		int currentCityId = 0, distance = 0, total = 0, minDistance = Integer.MAX_VALUE, nextCityId = 0;
		cities.get(0).setVisited(true);
		
		for(int k=1; k<numOfCities; k++) {				
			for(int j=0; j<numOfCities; j++) {
				distance = distanceMatrix[currentCityId][j];
				if(distance != 0 && distance < minDistance) {
					if(!cities.get(j).isVisited()) {
						minDistance = distance;
						nextCityId = j;
					}
				}
			}
			
			total += minDistance;
			path[k] = nextCityId;
			cities.get(nextCityId).setVisited(true);
			currentCityId = nextCityId;
			
			minDistance = Integer.MAX_VALUE;
				
		}
		
		totalDistance = total + cities.get(path[numOfCities-1]).distanceToCity(cities.get(0));
		return path;
	}
	
	public static void printPath(int[] path, int numOfCities) {
		System.out.println(totalDistance);
		for(int i=0; i<numOfCities; i++) {
			System.out.println(path[i]);
		}
		System.out.print("");
	}
}
