import javax.swing.*

object Task10Kotlin {
    @JvmStatic
    fun main(args: Array<String>) {

        val englishAlphabet = charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', ' ')
        val morseCode = arrayOf(".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", "|")

        val inputText: String
        val choice: String = JOptionPane.showInputDialog("Enter 1 to convert English to Morse code or 2 to convert Morse code to English.")

        when (choice) {
            "1" -> {
                inputText = JOptionPane.showInputDialog("Enter a text:")
                for (i in 0 until inputText.length)
                    morseCode.indices
                            .filter { englishAlphabet[it] == inputText.toLowerCase()[i] }
                            .forEach { print(morseCode[it] + " ") }
            }
            "2" -> {
                inputText = JOptionPane.showInputDialog("Enter a Morse code (split chars with space key):")
                val output = inputText.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                for (anOutput in output)
                    englishAlphabet.indices
                            .filter { anOutput == morseCode[it] }
                            .forEach { print(englishAlphabet[it]) }
            }
            else -> println("Wrong input!")
        }

    }
}
