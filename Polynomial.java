
public class Polynomial {
	double[] coefficients;
	public Polynomial()
	{
		coefficients = new double[1];
		coefficients[0] = 0;
	}
	public Polynomial(double[] array)
	{
		coefficients = new double[array.length];
		for (int i = 0; i < array.length; i++)
		{
			coefficients[i] = array[i];
		}
	}
	
	public Polynomial add(Polynomial equation)
	{
		//method adds two polynomials together
		//handle cases where the polynomials are different length
		//always return the longer array, with the shorter one added on

		if (coefficients.length < equation.coefficients.length)
		{
			Polynomial new_array = new Polynomial(equation.coefficients);
			//Polynomial new_array = new Polynomial(new double[coefficients.length]);
			for (int i = 0; i < equation.coefficients.length; i++)
			{
				new_array.coefficients[i] = equation.coefficients[i];
			}
			//if coefficients is longer than equation, add coefficients to equation
			for (int j = 0; j < coefficients.length; j++)
			{
				new_array.coefficients[j] = equation.coefficients[j] + coefficients[j];
			}
			return new_array;
		}
		
		else //when the polynomials are of equal length or coefficients is larger
			//when coefficients >= equation.coefficients
			//need to define a new variable of type polynomial, as cannot return polynomial
		{
			Polynomial new_array = new Polynomial(coefficients);
			//Polynomial new_array = new Polynomial(new double[coefficients.length]);
			for (int i = 0; i < coefficients.length; i++)
			{
				new_array.coefficients[i] = coefficients[i];
			}
			for (int j = 0; j < equation.coefficients.length; j++)
			{
				new_array.coefficients[j] = new_array.coefficients[j] + equation.coefficients[j];
			}
			return new_array;
		}
	}
	
	public double evaluate(double num)
	{
		double total = 0.0;
		for (int i = 0; i < coefficients.length; i++)
		{
			total = total + coefficients[i] * Math.pow(num, i);
		}
		return total;
	}
	
	
	public boolean hasRoot(double num)
	{
		
		if (evaluate(num) == 0)
		{
			return true;
		}
		return false;
	}
}
	

