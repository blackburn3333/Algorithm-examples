import java.util.Arrays;
class SlectionSort{
    public static void main(String[] args){
        //array for sort
        int arrayForSort[] = {10, 30, 20, 45, 85, 90, 75};
        System.out.println("------------------------------------------------------");
        System.out.println("|                    Selection Sort                  |");
        System.out.println("------------------------------------------------------");
        System.out.println("               <- Before Sorting Array ->             ");
        //print array before sort
        for (int i = 0; i < arrayForSort.length; i++) {
            System.out.print(arrayForSort[i] + " ");
        }
        System.out.println("\n------------------------------------------------------");
        //call selection array sort method
        SelectionArraysort(arrayForSort);
        System.out.println("               <- After Sorting Array ->              ");
        //print array after sort
        for (int i = 0; i < arrayForSort.length; i++) {
            System.out.print(arrayForSort[i] + " ");
        }
        System.out.println("\n------------------------------------------------------");
    }

    public static void SelectionArraySort(int[] data){
        //read array elements and sort
        for(int i = 0; i < array.length - 1; i++){
            //assign i as array index
            int index = i;
            //loop for compare values
            for(int x = i + 1;x < array.length; x++){
                //comairing array[x] < array[index] if array[x] less than array[index] assign index as x
                if(array[x] < array[index]){
                    //assign value of x to index if x is less
                    index = x; // lowest index
                }
            }
            //swap values
            //assign array[index] value to smallNum varible.
            int smallNum = array[index];
            //assign array[i] value to array[index]
            array[index] = array[i];
            //assign smallNum value to array[i]
            array[i] = smallNum; //assign small number to current element
        }
    }
}