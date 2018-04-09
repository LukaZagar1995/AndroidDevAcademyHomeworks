public class Task4 {
    public static void main(String arg[])
    {
        int counter = 0, i = 2018;
        do {
            if (i % 4 == 0) {
                    System.out.println(i);
                    counter++;
                }
            i++;
        } while (counter < 20);
    }
}
