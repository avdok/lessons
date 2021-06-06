import java.util.Scanner;
 
public class Calc {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        double num1, num2, result;
        char operation; 
        
        num1 = getOperand();

	while (true) {
            operation = getOperation();
            if (operation == 'e' || operation == 'E') {
                break;
            }
            if (operation == 'c' || operation == 'C') {
                num1 = getOperand();
                continue;
            }
            if (operation == '=') {
                System.out.println("Результат = " + num1);
                result = num1;
                continue;
            }

            num2 = getOperand();
            result = calc(num1, num2, operation);
            System.out.println("промежуточный результат " + num1 + " " + operation + " " + num2 + " = " + result);
            num1 = result;
            
        }
    }
 
    public static double getOperand(){
        System.out.println("Введите число:");
        double num;
        if(scanner.hasNextDouble()){
            num = scanner.nextDouble();
        } else {
            System.out.println("Вы допустили ошибку при вводе числа. Попробуйте еще раз.");
            scanner.next();//рекурсия
            num = getOperand();
        }
        return num;
    }
 
    public static char getOperation(){
        System.out.println("Введите операцию:");
        char operation;
        if(scanner.hasNext()){
            operation = scanner.next().charAt(0);
        } else {
            System.out.println("Вы допустили ошибку при вводе операции. Попробуйте еще раз.");
            scanner.next();//рекурсия
            operation = getOperation();
        }
        return operation;
    }
 
    public static double calc(double num1, double num2, char operation){
        double result = 0;
        switch (operation){
            case '+':
                result = num1+num2;
                break;
            case '-':
                result = num1-num2;
                break;
            case '*':
                result = num1*num2;
                break;
            case '/':
                try {
                    result = num1/num2;
                    if (result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY) {
                        throw new ArithmeticException();
                    }
                }
                catch (ArithmeticException ae) {
                    System.out.println("Деление на ноль!");
                }
                break;
            default:
                System.out.println("Операция не распознана. Повторите ввод.");
                result = calc(num1, num2, getOperation());
        }
        return result;
    }
}
