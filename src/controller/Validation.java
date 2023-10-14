package Controller;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Validation {

    private static final Scanner sc = new Scanner(System.in);

    public String inputString(String msg) {
       
        while (true) {
            
            System.out.println(msg);
            String input_raw = sc.nextLine();

            
            if (input_raw == null || input_raw.length() == 0) {
                
                System.err.println("Must input a string not empty !!!");
                System.out.println("Please enter again!");
                continue;
            }
            return input_raw;
        }
    }

    public String getString(String input) {
        while (true) {
            if (input.isEmpty()) {
                return "NULL";
            }
            return input.trim();
        }
    }

    public double getDouble(String input) {
        try {
            while (true) {
                double temp = Double.parseDouble(input);
                if (temp < 0) {
                    return 0;
                }
                return temp;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    
    public int checkInt(String msg, int min, int max) {
         
        while (true) {

            String input_raw = inputString(msg);

            try {              
                int input = Integer.parseInt(input_raw);
               
                if (input < min || input > max) {
                    System.err.println("Must input a number from " + min + " to " + max);
                    continue;

                }
                return input;
            } catch (NumberFormatException e) {
                System.err.println("Must enter a number");
                continue;
            }

        }
    }
        public double checkDouble(String msg) {
        
        while (true) {
            String input_raw = inputString(msg);

            try {
                double input = Double.parseDouble(input_raw);
                if (input <0 ) {
                    System.err.println("Must input a number >0");
                    continue;

                }
                return input;
            } catch (NumberFormatException e) {
                System.err.println("Must enter a number");
                continue;
            }

        }
    }

    public int checkINT(String input) {
        while (true) {
            if (Integer.parseInt(input) > 0) {
                return Integer.parseInt(input);
            } else {
                continue;
            }
        }
    }

}