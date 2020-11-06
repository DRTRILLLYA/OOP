import java.util.*;

public class block2 {
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите номер задания: ");
        String zadanie = scan.nextLine();
        switch (zadanie)
        {
            case "1" :
                System.out.print("Результат: ");
                n1(scan);
                break;
            case "2" :
                System.out.println("Разница между макисмальным и минимальным = " + n2(scan));
                break;
            case "3" :
                System.out.println("Результат: " + n3(scan));
                break;
            case "4":
                System.out.println("Результат: ");
                n4(scan);
                break;
            case "5":
                System.out.println("Результат: " + n5(scan));
                break;
            case "6":
                System.out.println("Соответствующее число Фибоначчи = " + n6(scan));
                break;
            case "7":
                System.out.println("Результат: " + n7(scan));
                break;
            case "8":
                System.out.println("Результат: " + n8(scan));
                break;
            case "9":
                System.out.println("Результат: " + n9(scan));
                break;
            case "10":
                System.out.println("Количество полей = " + n10(scan));
                break;
        }
        scan.close();
    }

    public static void n1(Scanner scan)
    {
        System.out.println("Повторяем каждый символ в строке n раз");
        System.out.println("Введите cтроку: ");
        String text = scan.nextLine();
        System.out.println("Введите число n: ");
        int n = scan.nextInt();
        for (int i = 0; i < text.length(); i++)
        {
            for (int j = 0; j < n; j++)
            {
                System.out.print(text.charAt(i));
            }
        }
    }

    public static int n2(Scanner scan)
    {
        System.out.println("Находим разницу между самым маленьким и большим числом");
        System.out.println("Введите размер массива");
        int N = scan.nextInt();
        int arr[] = new int[N];
        int min = 9999;
        int max = -9999;
        System.out.println("Введите массив");
        for (int i = 0; i < N; i++)
        {
            arr[i] = scan.nextInt();
            if (arr[i] > max)
            {
                max = arr[i];
            }
            if (arr[i] < min)
            {
                min = arr[i];
            }
        }
        int res = Math.abs(max) - Math.abs(min);
        return res;
    }

    public static boolean n3(Scanner scan)
    {
        System.out.println("Проверяем, целое ли среднее значение всех элементов массива");
        System.out.println("Введите размер массива");
        int N = scan.nextInt();
        int arr[] = new int[N];
        int sum = 0;
        System.out.println("Введите массив");
        for (int i = 0; i < N; i++)
        {
            arr[i] = scan.nextInt();
            sum = sum + arr[i];
        }
        if ((sum / N) % 1 == 0)
            return true;
        else
            return false;
    }

    public static void n4(Scanner scan)
    {
        System.out.println("Находим суммы элементов массива с предыдущими элементами");
        System.out.println("Введите размер массива");
        int N = scan.nextInt();
        int arr[] = new int[N];
        System.out.println("Введите массив");
        for (int i = 0; i < N; i++)
        {
            arr[i] = scan.nextInt();
        }
        for (int i = 0; i < N; i++)
        {
            int j = i;
            int sum = 0;
            while (j >= 0)
            {
                sum = sum + arr[j];
                j--;
            }
            System.out.print(sum + " ");
        }
    }

    public static int n5(Scanner scan)
    {
        System.out.println("Находим число цифр после запятой");
        System.out.println("Введите число: ");
        double a = scan.nextDouble();
        int c = 0;
        while (a % 1 != 0)
        {
            a = a * 10;
            c++;
        }
        return c;
    }

    public static int n6(Scanner scan)
    {
        System.out.println("Введите число: ");
        int num = scan.nextInt();
        int first = 1;
        int second = 2;
        int x = 0;
        int numf = 1;
        for (int i = 1; i < num; i++)
        {
            x = second;
            second = second + first;
            first = x;
            numf = first;
        }
        return numf;
    }

    public static boolean n7(Scanner scan)
    {
        System.out.println("Введите индекс: ");
        String text = scan.nextLine();
        int c = 0;
        for (int i = 0; i < text.length(); i++)
        {
            char sym = text.charAt(i);
            int asc = (char) sym;
            if (asc > 47 && asc < 58)
            {
                c++;
            }
        }
        if (c == 5)
            return true;
        else
            return false;
    }

    public static boolean n8(Scanner scan)
    {
        System.out.println("Введите первую строку: ");
        String str1 = scan.nextLine();
        System.out.println("Введите вторую строку: ");
        String str2 = scan.nextLine();
        if ((str1.charAt(0) == str2.charAt(str2.length()-1)) && (str2.charAt(0) == str1.charAt(str1.length()-1)))
            return true;
        else
            return false;
    }

    public static boolean n9(Scanner scan)
    {
        System.out.println("Введите слово: ");
        String str1 = scan.nextLine();
        System.out.println("Введите суффикс/префикс: ");
        String str2 = scan.nextLine();
        int c = 0;
        char sym = str2.charAt(0);
        int asc = (char) sym;
        if (asc == 45)
        {
            for (int i = str2.length() - 1; i > 0; i--)
            {
                if (str1.charAt(i) == str2.charAt(i))
                {
                    c++;
                }
            }
            if (c == (str2.length() - 1))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            for (int i = 0; i < str2.length() - 1; i++)
            {
                if (str1.charAt(i) == str2.charAt(i))
                {
                    c++;
                }
            }
            if (c == (str2.length() - 1))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public static int n10(Scanner scan)
    {
        System.out.println("Введите число шагов: ");
        int a = scan.nextInt();
        if (a % 2 == 0)
        {
            return a;
        }
        else
        {
            return (a + 2);
        }
    }
}
