import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        char [] resultArray = inputLine.toCharArray();
        int sizeIntArray = 0;
        for (int i = 0; i < resultArray.length; i++){
            if ((int)resultArray[i] >= sizeIntArray)
                sizeIntArray = (int)resultArray[i];
        }
        int [] intArray = new int[sizeIntArray + 1];
        int uniqueElements = 0;
        for (int i = 0; i < resultArray.length; i++){
            if (intArray[(int)resultArray[i]] == 0)
                uniqueElements++;
            if (intArray[(int)resultArray[i]] < 999)
                intArray[(int)resultArray[i]]++;
        }
        if (uniqueElements == 0) {
            System.err.println("Invalid args");
            System.exit(-1);
        }
        char[] names = new char[uniqueElements];
        int[] arrNonZeroInts = new int[uniqueElements];
        int l = 0;
        for (int i = 0; i < intArray.length; i++){
            if (intArray[i] != 0 && l < uniqueElements) {
                names[l] = (char) i;
                arrNonZeroInts[l] = intArray[i];
                l++;
            }
        }
        ft_sort(names, arrNonZeroInts);
        ft_draw_barGraph(names, arrNonZeroInts);
    }

    public static void ft_draw_barGraph(char [] names, int [] ints){
        int l = 0;
        float sep = (float) ints[0] / 10;
        if (names.length < 10)
            l = names.length;
        else
            l = 10;
        int save = 0;
        for (int k = 0; k < l; k++) {
            if (ints[0] == ints[k]) {
                save++;
                if (ints[0] > 99)
                    System.out.print("" + ints[k]);
                else if (ints[0] > 9)
                    System.out.print("  " + ints[k]);
                else
                    System.out.print("    " + ints[k]);
            }
        }
        System.out.println();
        int countOfPrintable = save;
        for(int i = 10; i != 0; i--) {
            int countOfOutput = 0;

            for (int k = 0; k < l; k++) {
                if (ints[k] >= (sep * i))
                    countOfOutput++;
                else if ((ints[k] < (sep * i)) && (ints[k] >= (sep * (i - 1)))) {
                    countOfPrintable++;
                }
            }
            for (int k = 0; k < countOfOutput; k++) {
                if (ints[k] > 99)
                    System.out.print("" + "  #");
                else if (ints[k] > 9)
                    System.out.print(" " + "  #");
                else
                    System.out.print("  " + "  #");
            }
            for (int k = save; k < countOfPrintable && k < l; k++){
                if (ints[k] > 99)
                    System.out.print("" + ints[k]);
                else if (ints[k] > 9)
                    System.out.print("  " + ints[k]);
                else
                    System.out.print("    " + ints[k]);

            }
            System.out.println();
            save = countOfPrintable;
        }
        for (int i = 0; i < l; i++){
            if (ints[i] > 99)
                System.out.print("  " + names[i]);
            else if (ints[i] > 9)
                System.out.print("   " + names[i]);
            else
                System.out.print("    " + names[i]);
        }
        System.out.println();
    }

    public static void ft_sort(char[] names, int[] intArray){
        int i = 0;
        int temp = 0;
        char tempChar;
        int indexTemp = 0;
        while (i < names.length){
            int l = i + 1;
            temp = intArray[i];
            tempChar = names[i];
            indexTemp = i;
            while (l < names.length){
                if (temp < intArray[l]) {
                    temp = intArray[l];
                    tempChar = names[l];
                    indexTemp = l;
                } else if (temp == intArray[l]){
                    if (names[indexTemp] > names[l]) {
                        temp = intArray[l];
                        tempChar = names[l];
                        indexTemp = l;
                    }
                }
                l++;
            }
            intArray[indexTemp] = intArray[i];
            intArray[i] = temp;
            names[indexTemp] = names[i];
            names[i] = tempChar;
            i++;
        }
    }
}
