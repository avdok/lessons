public class Calc {

	public static void main(String args[]) {

		Operand op1 = new Operand();
		Operand op2 = new Operand();
		Action act =  new Action();

		while (true)
		{
			op1.getInput();
			act.getInput();
			if (act.stopped()) break;
			act.execute(op1);
		}


	}
}
