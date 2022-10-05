import java.io.File;

public class Driver { 
	 public static void main(String [] args) { 
	  Polynomial p = new Polynomial(); 
	  //System.out.println(p.evaluate(3)); 
	  
	  double [] c1 = {1, -4};
	  int[] e1 = {1, 2};
	  Polynomial p1 = new Polynomial(c1, e1); 
	  
	  double [] c2 = {0, -2, 6, 0};
	  int[] e2 = {1,8, 3, 2};
	  Polynomial p2 = new Polynomial(c2, e2); 
	  
	 // Polynomial s = p1.add(p2);
	 // Polynomial x = p1.multiply(p2);
	  
	 // p1.convertToString();
	 // String b = p2.convertToString();
	  Polynomial p3 = new Polynomial(new File("C:\\Users\\encha\\OneDrive\\Desktop\\CS files\\abc.txt"));
	  p3.saveToFile("C:\\Users\\encha\\OneDrive\\Desktop\\CS files\\result.txt");
	  
	  // System.out.println("Printing the coefficients of p3: \n");
	  // for (int i = 0; i < p3.coefficients.length; i++)
	  // {
		//   System.out.println("" + p3.coefficients[i]);
	  // }

	 } 
	} 