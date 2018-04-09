import javax.swing.*;

public class Task1 {

    public static void main(String arg[])
    {
        int sum = 0, number;

            try {
                number = Integer.parseInt(JOptionPane.showInputDialog("Enter a number:"));
                for (int i = 1; i <= number; i++)
                    sum = sum + i;
                System.out.println("Sum of numbers from 1 to " + number + " is " + sum);
            } catch (NumberFormatException e) {
                System.out.println("You didn't enter a whole number!\n");
            }



    }

}
