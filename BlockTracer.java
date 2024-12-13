/**
 * Represents a tracer class that keeps track of all the blocks using stack.
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.time.Duration;
import java.time.Instant;

public class BlockTracer {
    public static void main(String[] args) {
        Instant start = Instant.now();

        Scanner scanner = new Scanner(System.in);
        
        // Comment out from here
        //System.out.print("Enter C program filename: ");
        //String fileName = scanner.nextLine();
        //System.out.println();
        // To here

        Stack<Block> stackTrace = new Stack<>(); 
        FileInputStream fis;
        String data_old;
        try {
            fis = new FileInputStream("sample4.c"); // Change filename to say sample4.c

            InputStreamReader inStream = new InputStreamReader(fis);
            BufferedReader stdin = new BufferedReader(inStream);
            while ((data_old = stdin.readLine()) != null){
                String[] new_data = stringCleaner(data_old);
                for (String data: new_data){
                    if (data.isEmpty()) continue;
                    if (data.charAt(0) == '{'){

                        stackTrace.push(new Block());

                    } else if (data.startsWith("int")){

                        String[] dataNew = data.split(" ");
                        for (String token : dataNew){
                            if (token.equals("int") || token.isEmpty()) continue;
                            String[] sub_token = token.split("="); 
                            int num;
                            if (sub_token.length == 2) num = Integer.parseInt(sub_token[1]);
                            else num = 0;
                            Variable var = new Variable(sub_token[0],num);
                            stackTrace.peek().addVar(var);
                        }

                    } else if (data.contains("/*$print")){
                        String intName = data.substring(9, data.length()-2);
                        
                        if (intName.equals("LOCAL")){
                            String LOCAL = stackTrace.peek().toString();
                            if (!LOCAL.isEmpty()){
                                System.out.println("Variable Name  Initial Value");
                                System.out.println(LOCAL + "\n");
                            } else {
                                System.out.println("No local variables to print.\n");
                            }
                            continue;
                        }

                        Stack<Block> tempStack = new Stack<>(); 
                        boolean valF = false;
                        Variable var = new Variable();
                        
                        while (stackTrace.size() > 0){
                            var = stackTrace.peek().findVal(intName);
                            if (var == null){
                                tempStack.push(stackTrace.pop());
                                continue;
                            }
                            valF = true;
                            break;
                        }
                        while (tempStack.size() > 0){
                            stackTrace.push(tempStack.pop());
                        }
                        if (valF){
                            System.out.println("Variable Name  Initial Value");
                            System.out.println(var.toString() + "\n");
                        } else {
                            System.out.println("Variable not found: " + intName + "\n");
                        }

                    } else if (data.charAt(0) == '}') {

                        stackTrace.pop();

                    }
                }
            }
            stdin.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file you entered was not found. Try again.");
        } catch (IOException e) {
            System.out.println("Internal IOException Error Occured. Try again.");
        }  
        scanner.close(); 

        // Print execution time
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Execution time: " + duration.toMillis() + " milliseconds");
    }

    // A universal function that is used to reformat the input line of C code into neatly formatted array, each containing one logical component of the line.
    public static String[] stringCleaner(String b){
        String[] parts = b.split("(?<=[;\\{\\}]|(?<=\\*\\/))|(?=[\\{\\}])");
        for (int i = 0; i < parts.length; i++){
            parts[i] = parts[i].trim().strip().replace(";", "");
            if (parts[i].startsWith("int")){
                parts[i] = parts[i].replace(" = ", "=").replace(",", "");
            }

            String var = parts[i];
            Boolean isTrue = var.startsWith("int") || var.startsWith("/*$print ") || var.startsWith("{") || var.startsWith("}");

            if (!isTrue){
                parts[i] = "";
            }
        }
        return parts;
    }
}