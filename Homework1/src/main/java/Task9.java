public class Task9 {
    public static void main(String[] args)
    {
        int matrix[][] = { { 4, 5, 6, 8 },
                { 1, 2, 3, 1 },
                { 7, 8, 9, 4 },
                { 1, 8, 7, 5 },
                { 1, 8, 7, 5 },
                { 1, 8, 7, 5 } };
        for (int[] aMatrix : matrix) {
            for (int j = 0; j < matrix[0].length; j++)
                System.out.print(aMatrix[j] + " ");
            System.out.println();
        }
    }
}
