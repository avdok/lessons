import java.util.Scanner;



public class Action {

	double preResult = 0;
	
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
				break;
			case ("+"):
				//preResult = p
				break;
			default:
				System.out.println("Некорректное действие");
				break;
		}
		
	}

	void execute(Operand op) {
		System.out.println("Action!");
	}
}
