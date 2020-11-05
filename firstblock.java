import java.util.*;

public class block1 {
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите номер задания: ");
        String zadanie = scan.nextLine();
        switch (zadanie)
        {
            case "1" :
                System.out.println("Остаток от деления = " + n1(scan));
                break;
            case "2" :
                System.out.println("Площадь треугольника = " + n2(scan));
                break;
            case "3" :
                System.out.println("Количество ног = " + n3(scan));
                break;
            case "4":
                System.out.println("Результат: " + n4(scan));
                break;
            case "5":
                System.out.println("Результат: " + n5(scan));
                break;
            case "6":
                System.out.println("ASCII значение = " + n6(scan));
                break;
            case "7":
                System.out.println("Сумма чисел от 1 до N = " + n7(scan));
                break;
            case "8":
                System.out.println("Максимально возможная третья сторона = " + n8(scan));
                break;
            case "9":
                System.out.println("Сумма кубов массива = " + n9(scan));
                break;
            case "10":
                System.out.println("Результат: " + n10(scan));
                break;
        }
        scan.close();
    }

    public static int n1(Scanner scan)
    {
        System.out.println("Найходим остаток от деления");
        System.out.println("Введите число a: ");
        int a = scan.nextInt();
        System.out.println("Введите число b: ");
        int b = scan.nextInt();
        int n = a % b;
        return n;
    }

    public static int n2(Scanner scan)
    {
        System.out.println("Находим площадь треугольника");
        System.out.println("Введите число a: ");
        int a = scan.nextInt();
        System.out.println("Введите число b: ");
        int b = scan.nextInt();
        int S = a * b / 2;
        return S;
    }

    public static int n3(Scanner scan)
    {
        System.out.println("Находим суммарное количество ног всех животных");
        System.out.println("Введите количество куриц: ");
        int numch = scan.nextInt();
        System.out.println("Введите количество коров: ");
        int numcow = scan.nextInt();
        System.out.println("Введите количество свиней: ");
        int numpig = scan.nextInt();
        int summ = 2*numch + 4*numcow + 4*numpig;
        return summ;
    }

    public static boolean n4(Scanner scan)
    {
        System.out.println("Введите параметр prob: ");
        int prob = scan.nextInt();
        System.out.println("Введите параметр prize: ");
        int prize = scan.nextInt();
        System.out.println("Введите параметр pay: ");
        int pay = scan.nextInt();
        if (prob * prize > pay)
            return true;
        else
            return false;
    }

    public static String n5(Scanner scan)
    {
        System.out.println("Введите число a: ");
        int a = scan.nextInt();
        System.out.println("Введите число b: ");
        int b = scan.nextInt();
        System.out.println("Введите число N: ");
        int N = scan.nextInt();
        if (a + b == N)
        {
            String res = "Выполнено сложение";
            return res;
        }
        else if (a - b == N)
        {
            String res = "Выполнено вычитание";
            return res;
        }
        else if (a * b == N)
        {
            String res = "Выполнено умножение";
            return res;
        }
        else if (a / b == N)
        {
            String res = "Выполнено деление";
            return res;
        }
        else
        {
            String res = "Выполнить невозможно";
            return res;
        }
    }

    public static int n6(Scanner scan)
    {
        System.out.println("Введите символ: ");
        char sym = scan.next().charAt(0);
        int asc = (char) sym;
        return asc;
    }

    public static int n7(Scanner scan)
    {
        System.out.println("Введите число N: ");
        int N = scan.nextInt();
        int res = N * (N + 1) / 2;
        return res;
    }

    public static int n8(Scanner scan)
    {
        System.out.println("Введите первую сторону: ");
        int a = scan.nextInt();
        System.out.println("Введите вторую сторону: ");
        int b = scan.nextInt();
        int c = a + b - 1;
        return c;
    }

    public static int n9(Scanner scan)
    {
        System.out.println("Введите размер массива");
        int N = scan.nextInt();
        int arr[] = new int[N];
        int sum = 0;
        System.out.println("Введите массив");
        for (int i = 0; i < N; i++)
        {
            arr[i] = scan.nextInt();
        }
        for (int i = 0; i < N; i++)
        {
            sum = sum + (int)Math.pow(arr[i], 3);
        }
        return sum;
    }

    public static boolean n10(Scanner scan)
    {
        System.out.println("Введите число a: ");
        int a = scan.nextInt();
        System.out.println("Введите число b: ");
        int b = scan.nextInt();
        System.out.println("Введите число c: ");
        int c = scan.nextInt();
        for (int i = 1; i < b; i++ ){
            a = a * 2;
        }
        if (a % c == 0)
            return true;
        else
            return false;
    }
}
