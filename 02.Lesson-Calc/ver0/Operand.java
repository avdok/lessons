import java.util.Scanner;

public class Operand {
	
	double value = 0;	

	void getInput() {
                Scanner in = new Scanner(System.in);
                System.out.print("Введите число: ");
                value = in.nextDouble();
                
                System.out.println("Введено " + value);
                

	}
}
