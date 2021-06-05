import java.util.Scanner;



public class Action {

	double preResult = 0;
	String operation = "";	

	boolean stop = false;
	
	boolean stopped() {
		return stop;
	}

	void getInput() {
		Scanner in = new Scanner(System.in);
		System.out.print("Введите действие (+-*/=cCeE): ");
		String act = in.next();
		
		switch (act) {
			case ("e"):
				stop = true;
				System.out.println("Выход");
				break;
			case ("c"):
				preResult = 0;
				operation = "";
				break;
			case ("+"):
				operation = "+";
				break;
			case ("-"):
				operation = "-";
				break;
			case ("*"):
				operation = "*";
				break;
			case ("/"):
				operation = "/";
				break;

			default:
				System.out.println("Некорректное действие");
				break;
		}
		
	}

	void execute(Operand op) {
		switch (operation) {
			case ("+"):
				preResult = preResult + op.value;
				break;
			case ("-"):
				preResult = preResult - op.value;
				break;
			case ("*"):
				preResult = preResult * op.value;
				break;
			case ("/"):
				preResult = preResult / op.value;
				break;
			default:
				break;
		}
		operation = "";
		if (operation == "=") {
			System.out.println("Результат " + preResult);
			preResult = 0;
		}
	}
}
 
