import javax.swing.*;

public class Task10 {

    public static void main(String[] args)
    {
        char[] englishAlphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                                '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', ' ' };
        String[] morseCode ={".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
                            "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-",
                            "..-", "...-", ".--", "-..-", "-.--", "--..", ".----", "..---", "...--",
                            "....-", ".....", "-....", "--...", "---..", "----.", "-----", "|"};
        String inputText, choice;

        choice = JOptionPane.showInputDialog("Enter 1 to convert English to Morse code or 2 to convert Morse code to English.");
        if(choice.equals("1")) {
            inputText = JOptionPane.showInputDialog("Enter a text:");
            for ( int i = 0; i < inputText.length(); i++ )
                for (int j = 0; j < morseCode.length; j++)
                    if (englishAlphabet[j] == inputText.toLowerCase().charAt(i))
                        System.out.print(morseCode[j] + " ");


        }
        else if(choice.equals("2")){
            inputText = JOptionPane.showInputDialog("Enter a Morse code (split chars with space key):");
            String[] output = inputText.split("\\s+");
            for(int i= 0; i< output.length; i++)
                for (int j = 0; j < englishAlphabet.length; j++)
                    if(output[i].equals(morseCode[j]))
                        System.out.print(englishAlphabet[j]);
        }
        else
            System.out.println("Wrong input!");

    }

}
