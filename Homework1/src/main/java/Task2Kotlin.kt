import javax.swing.*

object Task2Kotlin {
    @JvmStatic
    fun main(arg: Array<String>) {
        var result: Int
        val number: Int
        val choice: Int

        try {
            number = Integer.parseInt(JOptionPane.showInputDialog("Enter a number:"))
            choice = Integer.parseInt(JOptionPane.showInputDialog("Enter a 1 for sum or 2 for computing."))
            when (choice) {
                1 -> {
                    result = (1..number).sum()
                    println("Sum of numbers from 1 to $number is $result")
                }

                2 -> {
                    result = 1
                    for (i in 1..number)
                        result *= i
                    println("Computing of numbers from 1 to $number is $result")
                }

                else -> println("You didn't enter a right number for operation!\n")

            }

        } catch (e: NumberFormatException) {
            println("You didn't enter a whole number!\n")
        }

    }
}
