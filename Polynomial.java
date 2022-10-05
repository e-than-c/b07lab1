
import java.io.FileReader;
import java.util.Scanner;
import java.io.*;



public class Polynomial 
{
	double[] coefficients;
	int [] exponents; //array of integers
	String variableName;
	//note that the list of coefficients and exponents must be the same length
	public Polynomial()
	{
		coefficients = new double[1];
		exponents = new int[1];
		variableName = "x";
		
		exponents[0] = 0;
		coefficients[0] = 0;
	}
	public Polynomial(double[] co_array, int[] exp_array)
	{
		//exponents = new int[1];
		//exponents[0] = 0;
		variableName = "x";
		//System.out.println("co: " + co_array.length + ", exp: " + exp_array.length);
		coefficients = new double[co_array.length];
		for (int i = 0; i < co_array.length; i++)
		{
			coefficients[i] = co_array[i];
		}
		
		exponents = new int[exp_array.length];
		for (int j = 0; j < exp_array.length; j++)
		{
			exponents[j] = exp_array[j];
		}
		
	}
	public int getMaxExponent(int [] exp)
	{
		int max = 0;
		for (int i = 0; i < exp.length; i++)
		{
			if (exp[i] > max)
			{
				max = exp[i];
			}
		}
		return max;
	}
	
	public Polynomial add(Polynomial equation)
	{
		int arr_len = Math.max(getMaxExponent(exponents), getMaxExponent(equation.exponents));
		double [] coeff = new double [arr_len+1];
		int [] exp = new int [arr_len+1];
		for (int i = 0; i < coefficients.length; i++)
		{
			coeff[exponents[i]] = coefficients[i];
			exp[exponents[i]] = exponents[i];
			
		}
		
		for (int j = 0; j < equation.coefficients.length; j++)
		{
			coeff[equation.exponents[j]] += equation.coefficients[j];
			exp[equation.exponents[j]] = equation.exponents[j];
		}
		
		Polynomial new_poly = new Polynomial(coeff, exp);

		return new_poly;
		
	}
	
	public double evaluate(double num)
	{
		//change, as now the polynomial powers are NOT in order, and are arranged according to exponents
		double total = 0.0;
		for (int i = 0; i < coefficients.length; i++)
		{
			total = total + coefficients[i] * Math.pow(num, exponents[i]);
		}
		return total;
	}
	
	
	public boolean hasRoot(double num)
	{//no change needed
		if (evaluate(num) == 0)
		{
			return true;
		}
		return false;
	}
	
	
	public Polynomial multiply(Polynomial equation)
	{
		int arr_len = getMaxExponent(exponents) + getMaxExponent(equation.exponents);

		double [] coeff = new double [arr_len+1];
		int [] exp = new int [arr_len+1];
		
		for (int i = 0; i < coefficients.length; i++)
		{
			for (int j = 0; j < equation.coefficients.length; j++)
			{
				coeff[exponents[i] + equation.exponents[j]] += coefficients[i] * equation.coefficients[j];
				exp[exponents[i] + equation.exponents[j]] = exponents[i] + equation.exponents[j];
			}
		}
		Polynomial new_poly = new Polynomial(coeff, exp);
		
		return new_poly;
	}
	public int getVariableSize(String input)
	{
		String tmp = input.replaceAll("[0-9+-]", "");
		return tmp.length()+1;
	}
	public String splitComponent(String input, boolean signFlag)
	{
		String sign = "";
		if (!signFlag) 
		{
			sign = "-";
		}
		return sign + input;
	}
	
	public double getCoefficient(String input)
	{
		String[] tmp = input.split(variableName);
		if (tmp.length > 0)
		{
			if ((tmp[0].length() > 0))
			{
				if (tmp[0].equals("-"))
				{
					return -1;
				}
				else
				{
					return Double.parseDouble(tmp[0]);
				}
			}
			else
			{
				return 1;
			}

		}
		else if (input.indexOf(variableName) >= 0)
		{
			return 1;
		}
		return 0;
	}
	
	public int getExponential(String input)
	{
		String[] tmp = input.split(variableName);
		if (tmp.length > 1)
		{
			return Integer.parseInt(tmp[1]);
		}
		else if (input.indexOf(variableName) >= 0)
		{
			return 1;
		}
		return 0;		
	}
	
	public Polynomial(File f)
	{
		String str = readFile(f);
	    variableName = "x";
	    int num = getVariableSize(str);
	    double[] coeArray = new double[num];
		int[] expArray = new int[num];

	    String tmp = str;
	    boolean signFlag = true;
	            
	    for (int i=0; i<num; i++)
	    {
	       	int positive = tmp.indexOf("+");
	       	int negative = tmp.indexOf("-");
	       	String component;
	       	if ((negative < 0) || (negative > positive))
	       	{
	        	if (positive >= 0)
	            {
	            	component = splitComponent(tmp.substring(0, positive), signFlag);  
	            	tmp = tmp.substring(positive+1);            			
	            }
	            else
	            {
	            	component = splitComponent(tmp, signFlag);
	            }
	            signFlag = true;
	        } 
	        else
	        {
	           	component = splitComponent(tmp.substring(0, negative), signFlag);
	           	tmp = tmp.substring(negative+1);
	           	signFlag = false;	            		
	        }
	        coeArray[i] = getCoefficient(component);
	        expArray[i] = getExponential(component);
	    }
	
	    int max = getMaxExponent(expArray);
		coefficients = new double[max+1];
		exponents = new int[max+1];
				
		for (int i=0; i<coeArray.length; i++)
		{
			exponents[expArray[i]] = expArray[i];
			coefficients[expArray[i]] = coeArray[i];
		}
	}
	
	
	public String readFile(File f)
	{
		String str = "";
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(f));
			str = br.readLine();
			br.close();
		}
	    catch (Exception e)
		{
	   		e.printStackTrace();
	    }
		return str;
	}
	
	public String convertToString()
	{
		String str = "";
		boolean firstFlag = true;
		for (int i=0; i<coefficients.length; i++)
		{
			if (coefficients[i] != 0)
			{
				int coeff =  (int)Math.round(coefficients[i]);
				String sign = "";
				if (coeff > 0 && !firstFlag)
				{
					sign = "+";
				}
				firstFlag = false;
				str += sign;
				
				if (coeff == -1)  
				{
					
					str += "-";
				}
				
				else if ((coeff != 1) || (coeff == 1 && exponents[i] == 0))
				{
					str += coeff;						
				}
				
 				if (exponents[i] > 0)
				{
 					str += variableName;
					if (exponents[i] > 1)
					{
						str += exponents[i];
					}
				}
			}
		}
		//System.out.println("saveToFile: str: " + str);
		return str;
	}
	
	public void saveToFile(String fileName)
	{
		String str = convertToString();
	    BufferedWriter output = null;
	        try
	        {
	        	File file = new File(fileName);
	        	output = new BufferedWriter(new FileWriter(file));
	        	output.write(str);
	        	output.close();
	        }
	        catch (Exception e)
	        {
	        	e.printStackTrace();
	        }
	}
}


	

