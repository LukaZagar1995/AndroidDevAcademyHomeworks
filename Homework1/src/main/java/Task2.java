import javax.swing.*;

public class Task2 {
    public static void main(String arg[])
    {
        int result, number, choice;

            try {
                number = Integer.parseInt(JOptionPane.showInputDialog("Enter a number:"));
                choice = Integer.parseInt(JOptionPane.showInputDialog("Enter a 1 for sum or 2 for computing."));
                if(choice == 1) {
                    result = 0;
                    for (int i = 1; i <= number; i++)
                        result = result + i;
                    System.out.println("Sum of numbers from 1 to " + number + " is " + result);
                }
                else if(choice == 2)
                {
                    result = 1;
                    for (int i = 1; i <= number; i++)
                        result = result * i;
                    System.out.println("Computing of numbers from 1 to " + number + " is " + result);
                }
                else
                    System.out.println("You didn't enter a right number for operation!\n");

            } catch (NumberFormatException e) {
                System.out.println("You didn't enter a whole number!\n");
            }

    }
}
