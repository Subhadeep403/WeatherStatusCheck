	package Test;
	
    import java.util.Scanner;
	import io.restassured.RestAssured;
	import io.restassured.path.json.JsonPath;
	import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;
/**
 * 
 * @author subhadeep
 *
 */
public class WeatherCheck {

	
		    // Replace with the actual API base URL
		    private static final String API_BASE_URL = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";
		    static	RequestSpecification httpRequest = RestAssured.given();
			static int m=-1;
	    	static Response response = httpRequest.get(API_BASE_URL);
	         
	    	// First get the JsonPath object instance from the Response interface
	    	static JsonPath jsonPathEvaluator = response.jsonPath();
		    public static void main(String[] args) {
		        Scanner scanner = new Scanner(System.in);
		        int option;

		        do {
		            printMenu();
		            option = getUserInput(scanner);

		            switch (option) {
		                case 1:
		                    getAndPrintWeatherData(scanner);
		                    break;
		                case 2:
		                    getAndPrintWindSpeed(scanner);
		                    break;
		                case 3:
		                    getAndPrintPressure(scanner);
		                    break;
		                case 0:
		                    System.out.println("Exiting the program.");
		                    break;
		                default:
		                    System.out.println("Invalid option. Please select a valid option.");
		                    break;
		            }
		        } while (option != 0);

		        scanner.close();
		    }

		    private static void printMenu() {
		        System.out.println("1. Get weather");
		        System.out.println("2. Get Wind Speed");
		        System.out.println("3. Get Pressure");
		        System.out.println("0. Exit");
		    }
		    private static int getUserInput(Scanner scanner) {
		        try {
		            return Integer.parseInt(scanner.nextLine());
		        } catch (NumberFormatException e) {
		            System.out.println("Invalid input. Please enter a valid option.");
		            return -1;
		        }
		    }
		    private static void getAndPrintWeatherData(Scanner scanner) {
		        System.out.print("Enter the date (YYYY-MM-DD HH:MM:SS): ");
		        String date = scanner.nextLine();
		        String weatherData = getWeatherData(date);
		        if (weatherData != null) {
		            System.out.println("Temperature : " + weatherData );
		        } else {
		            System.out.println("Weather data not available for the specified date.");
		        }
		    }

		    private static void getAndPrintWindSpeed(Scanner scanner) {
		        System.out.print("Enter the date (YYYY-MM-DD HH:MM:SS): ");
		        String date = scanner.nextLine();
		        String windSpeed = getWindSpeed(date);
		        if (windSpeed != null) {
		            System.out.println("Wind Speed : " + windSpeed );
		        } else {
		            System.out.println("Weather data not available for the specified date.");
		        }
		    }

		    private static void getAndPrintPressure(Scanner scanner) {
		        System.out.print("Enter the date (YYYY-MM-DD HH:MM:SS): ");
		        String date = scanner.nextLine();
		        String pressure = getPressure(date);
		        if (pressure != null) {
		            System.out.println("Pressure : " + pressure );
		        } else {
		            System.out.println("Weather data not available for the specified date.");
		        }
		    }

		    // Replace with actual methods to make API calls and parse the JSON response
		    private static String getWeatherData(String date) {
		    	String temp = null ;
		    	float tem;
		    	if(dateCheck(date)>=0) {
		    	// JsonPath object to get a String value of the node
		    	try{
		    		 tem= jsonPathEvaluator.get("list["+ m +"].main.temp");
		    	}
		    	catch (Exception e) {
					// TODO: handle exception
		    		tem= jsonPathEvaluator.getInt("list["+ m +"].main.temp");
				}
		    	//float to String
		    	 temp  = Float.toString(tem);
		    	}
		    	 else
	    			 System.out.println("Please enter valid Date and Time!");
	    		 
	    		 
		        return temp;
		    }

		    private static String getWindSpeed(String date) {
		    	String wind = null ;
		    	float win;
		    	if(dateCheck(date)>=0) {
		    		try {
		    		 	 win= jsonPathEvaluator.get("list["+ m +"].wind.speed");
		    		}
		    		catch (Exception e) {
						// TODO: handle exception
		    			 win= jsonPathEvaluator.getInt("list["+ m +"].wind.speed");
					}
		    	// JsonPath object to get a String value of the node
		    
		    	//float to String
		    	 wind  = Float.toString(win);
		    	}
		    	 else
	    			 System.out.println("Please enter valid Date and Time!");
		        return wind;
		    }

		    private static String getPressure(String date) {
		    	String pressure = null ;
		    	float pre;
		    	if(dateCheck(date)>=0) {
		    	// JsonPath object to get a String value of the node
		    	try {
		    		pre= jsonPathEvaluator.get("list["+ m +"].main.pressure");
		    	}
		    	catch (Exception e) {
					// TODO: handle exception
		    		pre= jsonPathEvaluator.getInt("list["+ m +"].main.pressure");
				}
		    	//float to String
		    	pressure  = Float.toString(pre);
		    	}
		    	 else
	    			 System.out.println("Please enter valid Date and Time!");
		        return pressure;
		    }
		    
		    //It use to check the date is valid or not
		    public static int dateCheck(String date) {
		    	int k=-1;
		    	int count = jsonPathEvaluator.getInt("cnt");
		    
		    	for(int i=0;i<count;i++) {
		    		
		    		 String sysdate = jsonPathEvaluator.getString("list["+i+"].dt_txt");
		    		 
		    		 if(date.equals(sysdate)) {
		    		    m=i;
		    			k=i;
		    		 }
		    			 
		    		}
		    	 if (k==-1) {
		    		 m=-1;
		    	 }
		    	
				return m;
		    	
		    }
		    
		    
		    
		}




