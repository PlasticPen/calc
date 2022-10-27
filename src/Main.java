
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter math expression:");
            try {
            String rawInput = scan.nextLine();
            System.out.println(calc(rawInput));
            } catch (Exception e) {
                return;
            }
        }
    }
    public static String calc(String input)throws FormatInputException{

        Expression expr = new Expression();
        expr.parser(input);
        int temp = switch (expr.getSign()) {
            case ('+') -> (expr.getNumbers()[0] + expr.getNumbers()[1]);
            case ('-') -> (expr.getNumbers()[0] - expr.getNumbers()[1]);
            case ('*') -> (expr.getNumbers()[0] * expr.getNumbers()[1]);
            case ('/') -> (expr.getNumbers()[0] / expr.getNumbers()[1]);
            default -> 0;
        };

        if (expr.getNumSystem()[0] == 'R' && expr.getNumSystem()[1] == 'R')
            return(expr.fromArabicToRoman(temp));
        return (String.valueOf(temp));
    }

}



