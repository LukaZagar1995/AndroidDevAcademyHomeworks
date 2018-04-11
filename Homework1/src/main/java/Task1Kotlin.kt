import javax.swing.*

object Task1Kotlin {
    @JvmStatic
    fun main(arg: Array<String>) {
        var sum = 0
        val number: Int

        try {
            number = Integer.parseInt(JOptionPane.showInputDialog("Enter a number:"))
            for (i in 1..number)
                sum += i
            println("Sum of numbers from 1 to $number is $sum")
        } catch (e: NumberFormatException) {
            println("You didn't enter a whole number!\n")
        }

    }
}
