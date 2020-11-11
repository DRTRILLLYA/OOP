import java.util.*;

public class block3 {
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите номер задания: ");
        String zadanie = scan.nextLine();
        switch (zadanie)
        {
            case "1" :
                System.out.print("Число корней: " + n1(scan));
                break;
            case "2" :
                System.out.println("Результат: " + n2(scan));
                break;
            case "3" :
                System.out.println("Результат: " + n3(scan));
                break;
            case "4":
                n4(scan);
                break;
            case "5":
                System.out.println("Результат: " + n5(scan));
                break;
            case "6":
                System.out.println("Результат: " + n6(scan));
                break;
            case "7":
                System.out.println("Результат: " + n7(scan));
                break;
            case "8":
                n8(scan);
                break;
            case "9":
                System.out.println("Результат: " + n9(scan));
                break;
            case "10":
                System.out.println("Результат: " + n10(scan));
                break;
        }
        scan.close();
    }

    public static int n1(Scanner scan)
    {
        System.out.println("Находим число корней в квадратном уравнении");
        System.out.println("Введите число a: ");
        int a = scan.nextInt();
        System.out.println("Введите число b: ");
        int b = scan.nextInt();
        System.out.println("Введите число c: ");
        int c = scan.nextInt();
        int D = b*b - 4*a*c;
        if (D == 1)
        {
            return 1;
        }
        else if (D < 0)
        {
            return 0;
        }
        else
        {
            return 2;
        }
    }

    public static int n2(Scanner scan)
    {
        System.out.println("Находим второго расположения zip");
        System.out.println("Введите строку: ");
        String str = scan.nextLine();
        int c = 0;
        int res = 0;
        for (int i = 0; i < str.length(); i++)
        {
            char sym = str.charAt(i);
            int asc = (char) sym;
            if (asc == 122)
            {
                if (i < str.length() - 2)
                {
                    sym = str.charAt(i+1);
                    asc = (char) sym;
                    if (asc == 105)
                    {
                        sym = str.charAt(i+2);
                        asc = (char) sym;
                        if (asc == 112)
                        {
                            c++;
                            if (c == 2)
                            {
                                res = i;
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (res != 0)
        {
            return res;
        }
        else
        {
            return -1;
        }
    }

    public static boolean n3(Scanner scan)
    {
        System.out.println("Проверяем, совершенное ли число");
        System.out.println("Введите число: ");
        int num = scan.nextInt();
        int res = 0;
        for (int i = 1; i < num; i++)
        {
            if (num % i == 0)
            {
                res = res + i;
            }
        }
        if (num == res)
            return true;
        else
            return false;
    }

    public static void n4(Scanner scan)
    {
        System.out.println("Меняем первый и последний символ местами");
        System.out.println("Введите строку: ");
        String str = scan.nextLine();
        if (str.length() < 3)
        {
            System.out.println("Несовместимо");
        }
        if (str.charAt(0) == str.charAt(str.length() - 1))
        {
            System.out.println("Это пара");
        }
        else
        {
            char[] arr = str.toCharArray();
            char v = str.charAt(0);
            arr[0] = arr[str.length() - 1];
            arr[str.length() - 1] = v;
            str = String.valueOf(arr);
            System.out.println("Результат: " + str);
        }
    }

    public static boolean n5(Scanner scan)
    {
        System.out.println("Определяем является ли строка кодом");
        System.out.println("Введите строку: ");
        String str = scan.nextLine();
        int c = 0;
        char sym = str.charAt(0);
        int asc = (char) sym;
        if (asc == 35)
        {
            if (str.length() == 7)
            {
                for (int i = 1; i < str.length(); i++)
                {
                    sym = str.charAt(i);
                    asc = (char) sym;
                    if ((asc > 47 && asc < 58) || (asc > 64 && asc < 71) || (asc > 96 && asc < 103))
                    {
                        c++;
                    }
                }
                if (c == 6)
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
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public static boolean n6(Scanner scan)
    {
        System.out.println("Определяем число уникальных чисел");
        int c1 = 0;
        int c2 = 0;
        System.out.println("Введите размер 1 массива");
        int N1 = scan.nextInt();
        int arr1[] = new int[N1];
        System.out.println("Введите 1 массив");
        for (int i = 0; i < N1; i++)
        {
            arr1[i] = scan.nextInt();
        }
        System.out.println("Введите размер 2 массива");
        int N2 = scan.nextInt();
        int arr2[] = new int[N2];
        System.out.println("Введите 2 массив");
        for (int i = 0; i < N2; i++)
        {
            arr2[i] = scan.nextInt();
        }
        boolean found = false;
        for (int i = 0; i < N1; i++)
        {
            found = false;
            for (int j = 0; j < N1; j++)
            {
                if (i != j && arr1[i] == arr1[j])
                {
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                c1++;
            }
        }
        for (int i = 0; i < N2; i++)
        {
            found = false;
            for (int j = 0; j < N2; j++)
            {
                if (i != j && arr2[i] == arr2[j])
                {
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                c2++;
            }
        }
        if (c1 == c2)
            return true;
        else
            return false;
    }

    public static boolean n7(Scanner scan)
    {
        System.out.println("Введите число: ");
        int num = scan.nextInt();
        int sqr = num * num;
        int res = (int)Math.log10(sqr)+1;
        int m = res - res/2;
        int numr = sqr % (int)Math.pow(10, m);
        int numl = sqr / (int)Math.pow(10, m);
        if (numl + numr == num)
            return true;
        else
            return false;
    }

    public static void n8(Scanner scan)
    {
        System.out.println("Определяем наибольшую последователньось нулей");
        System.out.println("Введите строку: ");
        String str = scan.nextLine();
        int flag = 1;
        int c = 0;
        int maxnum = 0;
        for (int i = 0; i < str.length(); i++)
        {
            char sym = str.charAt(i);
            int asc = (char) sym;
            if (asc == 48)
            {
                c++;
            }
            if (asc == 49)
            {
                if (c > maxnum)
                {
                    maxnum = c;
                }
                c = 0;
            }
        }
        System.out.print("Результат: ");
        for (int i = 0; i < maxnum; i++)
        {
            System.out.print(0);
        }
    }

    public static int n9(Scanner scan)
    {
        System.out.println("Введите число: ");
        int num = scan.nextInt();
        int c = 0;
        for (int i = 1; i <= num; i++)
        {
            if (num % i == 0)
            {
                c++;
            }
        }
        if (c == 2)
        {
            return num;
        }
        else
        {
            while (c > 2)
            {
                c = 0;
                num++;
                for (int i = 1; i <= num; i++)
                {
                    if (num % i == 0)
                    {
                        c++;
                    }
                }
            }
            return num;
        }
    }

    public static boolean n10(Scanner scan)
    {
        System.out.println("Введите число X: ");
        int x = scan.nextInt();
        System.out.println("Введите число Y: ");
        int y = scan.nextInt();
        System.out.println("Введите число Z: ");
        int z = scan.nextInt();
        int g = 0;
        int c1 = 0;
        int c2 = 0;
        if (x > y)
        {
            g = x;
            c1 = y;
            c2 = z;
        }
        else
        {
            g = y;
            c1 = x;
            c2 = z;
        }
        if (z > g)
        {
            g = z;
            c1 = x;
            c2 = y;
        }
        if (g*g == c1*c1 + c2*c2)
            return true;
        else
            return false;
    }
}

