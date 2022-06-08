import java.util.Scanner;

public class Program {
    private static final String WEEK = "Week ";
    private static final String SEP = "42";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String week = sc.nextLine();
        int weekNum = 1;
        long allGrades = 0;

        while (weekNum <= 18 && !week.equals(SEP)){
            if (!week.equals(WEEK + weekNum))
                System.exit(IllegalArgument());
            allGrades = packGrade(getMinGrade(sc), allGrades, weekNum);
            weekNum++;
            week = sc.nextLine();
			if (week.equals(SEP))
				break;
        }
        for(int i = 1; i < weekNum; i++){
            System.out.print(WEEK + i + " ");
            constructGraph(takeGradeFromPackage(i, allGrades));
        }
    }

    public static int takeGradeFromPackage(int index, long allGrades){
        int res;

        for(int i = 1; i < index; i++){
            allGrades = allGrades / 10;
        }
        res = (int)(allGrades % 10);
        return res;
    }

    public static long packGrade(int minGrade, long allGrade, int index){
        long res;
        long powOfTen = 1;

        for(int i = 1; i < index; i++){
            powOfTen = powOfTen * 10;
        }
        res = allGrade + (minGrade * powOfTen);
        return res;
    }


    public static int getMinGrade(Scanner scanner){
        int min = scanner.nextInt();
        int current;

        for (int i = 0; i < 4; i++){
            current = scanner.nextInt();
			if (current > 9 || min > 9)
				System.exit(IllegalArgument());
            if (current < min)
                min = current;
        }
        scanner.nextLine();
        return min;
    }

    public static void constructGraph(int minGrade){
        for(int i = 0; i < minGrade; i++)
            System.out.print("=");
        System.out.println(">");
    }

    private static int IllegalArgument(){
        System.err.println("IllegalArgument");
        return (-1);
    }
}
