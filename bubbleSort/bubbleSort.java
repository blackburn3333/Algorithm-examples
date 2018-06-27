//Author Jayendra Matarage
import java.util.Arrays;

class bubbleSort{
    public static void main(String[] args){
        //array for sort
        int arrayForSort[] = {10, 30, 20, 45, 85, 90, 75};
        System.out.println("------------------------------------------------------");
        System.out.println("|                      Bubble Sort                   |");
        System.out.println("------------------------------------------------------");
        System.out.println("               <- Before Sorting Array ->             ");
        //print array before sort
        for (int i = 0; i < arrayForSort.length; i++) {
            System.out.print(arrayForSort[i] + " ");
        }
        System.out.println("\n------------------------------------------------------");
        BubbleArraySort(arrayForSort);
        System.out.println("               <- After Sorting Array ->              ");
        //print array after sort
        for (int i = 0; i < arrayForSort.length; i++) {
            System.out.print(arrayForSort[i] + " ");
        }
        System.out.println("\n------------------------------------------------------");
    }

    public static void BubbleArraySort(int[] Arraydata){
        //varible for store array length
        int arrayLen = Arraydata.length;
        //loop till first to last of element of array
        for(int i = 0; i < arrayLen - 1; i++){
            //loop till last to first element of array
            for(int x = arrayLen -1 ; x > i; --x){
                //swap element of array if Arraydata[x] < Arraydata[x-1]
                if(Arraydata[x] < Arraydata[x-1]){
                    int temp = Arraydata[x-1];
                    Arraydata[x-1] = Arraydata[x];
                    Arraydata[x] = temp;
                }
            }
        }
    }
}