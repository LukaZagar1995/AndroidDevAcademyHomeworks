public class Task5 {
    public static void main(String arg[])
    {
        int[] largeArray = {1,2,3,4,4,5,9,1,2};
        System.out.println(largestNumber(largeArray));
    }

    private static int largestNumber(int array[])
    {
        int max = array[0];
        for (int anArray : array)
            if (max < anArray)
                max = anArray;
        return max;
    }
    
}
