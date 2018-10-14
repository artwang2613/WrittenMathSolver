
import java.util.ArrayList;

public class mathLogic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		mathLogic math = new mathLogic();

		math.buildingArray();

		math.calculate();

	}

	// arthur's array received
	private ArrayList<String> arthursArray = new ArrayList<String>();

	private ArrayList<String> equation = new ArrayList<String>();

	mathLogic() {

	}

	public void buildingArray() {
		arthursArray.add("12");
		arthursArray.add("+");
		arthursArray.add("8");
		arthursArray.add("+");
		arthursArray.add("2");
		//arthursArray.add("*");
		//arthursArray.add("31");
		//arthursArray.add("*");
		//arthursArray.add("-2");
		equation.addAll(arthursArray); //create a new array list by copying the array given by the CNN
		System.out.println(equation);  //print out array list

	}

	public void calculate() {

		String ans = ""; //initialize string variable that will be the final answer of the problem
		

		// for loop that goes through the array list until it reaches the end of the list
		for (int index = 0; index < equation.size(); index++) {

			
			// if the index is less than 2 scan for these operations
			if (index < 2) {
				// if a plus sign is found in the array add the values before and after the operation
				if (equation.get(index) == "+") {  

					ans = String
							.valueOf(Float.valueOf(equation.get(index - 1)) + Float.valueOf(equation.get(index + 1)));
				}
				// if a subtraction sign is found in the array subtract the value after the operation from the value before the operation
				if (equation.get(index) == "-") {  

					ans = String
							.valueOf(Float.valueOf(equation.get(index - 1)) - Float.valueOf(equation.get(index + 1)));
				}
				  // if a multiplication sign is found in the array multiply the values before and after the operation 
				  if (equation.get(index) == "*") {
				  
				  ans = String .valueOf(Float.valueOf(equation.get(index - 1)) * Float.valueOf(equation.get(index + 1))); }
				  // if a division sign is found in the array divide the value before the operation by the value after the operation
				  if (equation.get(index) == "/") {
				  
				  ans = String .valueOf(Float.valueOf(equation.get(index - 1)) / Float.valueOf(equation.get(index + 1))); }
				 
			}
			// once the index is greater than 2 then scan for these operations.
			if (index > 2) {
				// if a plus sign is found in the array add the values before and after the operation
				if (equation.get(index) == "+") {

					ans = String.valueOf(Float.valueOf(ans) + Float.valueOf(equation.get(index + 1)));
				}
				// if a subtraction sign is found in the array subtract the value after the operation from the value before the operation
				if (equation.get(index) == "-") {

					ans = String.valueOf(Float.valueOf(ans) - Float.valueOf(equation.get(index + 1)));
				}
				  // if a multiplication sign is found in the array multiply the values before and after the operation 
				  if (equation.get(index) == "*") {
				  
				  ans = String.valueOf(Float.valueOf(ans) * Float.valueOf(equation.get(index + 1))); }
				  // if a division sign is found in the array divide the value before the operation by the value after the operation
				  if (equation.get(index) == "/") {
				  
				  ans = String.valueOf(Float.valueOf(ans) / Float.valueOf(equation.get(index + 1))); }
				 
			}
			
			

		}
		
		System.out.println("="); //print an "=" sign
		System.out.println(ans); //print the final answer of the problem
		
	}

}
