import lib.util.Interpreter;

import java.util.Scanner;

public class MUA {
    public static void main(String[] args) {
        if (args.length == 0) {
            Interpreter interpreter = new Interpreter(true);
            while (true) {
                interpreter.next(true, new Scanner(System.in));
            }
        }
        else {
            Interpreter interpreter = new Interpreter(false);
            interpreter.runFile(args[0]);
        }
    }
}
