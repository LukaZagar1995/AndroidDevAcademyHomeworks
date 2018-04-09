public class Task5 {
    public static void main(String arg[])
    {
        int[] array = {1,2,3,4,4,5,9,1,2};
        System.out.println("Largest number in array is: " + largestNumber(array));
        array = oddPositions(array);
        for (int anArray : array) System.out.println(anArray);
    }

    private static int largestNumber(int array[])
    {
        int max = array[0];
        for (int anArray : array)
            if (max < anArray)
                max = anArray;
        return max;
    }

    private static int[] reverseArray(int array[])
    {
        int temp[] = new int[array.length];
        for(int i = 0; i< array.length; i++)
            temp[i]=array[array.length - 1 - i];
        return temp;
    }

    private static int[] oddPositions(int array[])
    {
        int temp[] = new int[array.length/2], counter=0;
        for(int i = 0; i< array.length; i++)
            if (i % 2 == 1) {
                temp[counter] = array[i];
                counter ++;
            }
        return temp;
    }

    
}
