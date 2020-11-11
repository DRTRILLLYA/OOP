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
                n1(scan);
                break;
            case "2" :
                n2(scan);
                break;
            case "3" :
                n3(scan);
                break;
            case "4":
                n4(scan);
                break;
            case "5":
                n5(scan);
                break;
            case "6":
                System.out.println("Результат: " + n6(scan));
                break;
            case "7":
                n7(scan);
                break;
            case "8":
                System.out.println("Результат: " + n8(scan));
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

    public static void n1(Scanner scan)
    {
        System.out.println("Введите строку: ");
        String str = scan.nextLine();
        System.out.println("Введите число: ");
        int k = scan.nextInt();
        int ost = k;
        int c = 0;
        int b1 = 0;
        int b2;
        for (int i = 0; i < str.length(); i++)
        {
            char sym = str.charAt(i);
            int asc = (char) sym;
            if (i == str.length() - 1)
            {
                for (int j = b1; j < str.length(); j++)
                {
                    System.out.print(str.charAt(j));
                }
            }
            if (asc == 32)
            {
                b2 = i - 1;
                if (c != 0)
                {
                    if (ost >= c)
                    {
                        for (int j = b1; j <= b2; j++)
                        {
                            System.out.print(str.charAt(j));
                        }
                        ost = ost - c;
                        c = 0;
                        System.out.print(" ");
                    }
                    else
                    {
                        System.out.println(" ");
                        ost = k;
                        if (ost >= c)
                        {
                            for (int j = b1; j <= b2; j++)
                            {
                                System.out.print(str.charAt(j));
                            }
                            ost = k - c;
                            c = 0;
                            System.out.print(" ");
                        }

                    }
                }
                b1 = i + 1;
            }
            else
            {
                c++;
            }
        }
    }

    public static void n2(Scanner scan)
    {
        System.out.println("Распределяем скобки");
        System.out.println("Введите строку: ");
        String str = scan.nextLine();
        int c = 0;
        int res = 0;
        int lvl = 0;
        System.out.println("Результат: ");
        for (int i = 0; i < str.length(); i++)
        {
            char sym = str.charAt(i);
            int asc = (char) sym;
            if (asc == 40)
            {
                lvl++;
                System.out.print(str.charAt(i));
            }
            if (asc == 41)
            {
                lvl--;
                System.out.print(str.charAt(i));
            }
            if (lvl == 0)
            {
                System.out.print(", ");
            }
        }
    }

    public static void n3(Scanner scan)
    {
        System.out.println("Введите строку: ");
        String str = scan.nextLine();
        System.out.println("Выберите функцию: 1 - toCamelCase, 2 - toSnakeCase");
        int type = scan.nextInt();
        System.out.println("Результат: ");
        if (type == 1)
        {
            for (int i = 0; i < str.length(); i++)
            {
                char sym = str.charAt(i);
                int asc = (char) sym;
                if (asc == 95)
                {
                    i++;
                    sym = str.charAt(i);
                    asc = (char) sym;
                    int num = (int) asc-32;
                    System.out.print((char) num);
                }
                else
                {
                    System.out.print(str.charAt(i));
                }
            }
        }
        if (type == 2)
        {
            for (int i = 0; i < str.length(); i++)
            {
                if (i + 1 < str.length())
                {
                    char sym = str.charAt(i+1);
                    int asc = (char) sym;
                    if (asc > 64 && asc < 91)
                    {
                        System.out.print(str.charAt(i) + "_");
                        sym = str.charAt(i+1);
                        asc = (char) sym;
                        int num = (int) asc+32;
                        System.out.print((char) num);
                        i++;
                    }
                    else
                    {
                        System.out.print(str.charAt(i));
                    }
                }
                else
                {
                    System.out.print(str.charAt(i));
                }
            }
        }
    }

    public static void n4(Scanner scan)
    {
        System.out.println("Определяем зарплату");
        double arr[] = new double[4];
        double m = 0;
        double d = 0;
        System.out.println("Введите массив");
        for (int i = 0; i < 4; i++)
        {
            arr[i] = scan.nextDouble();
        }
        if (arr[0] <= 17)
        {
            m = (17 - arr[0]) * arr[2];
        }
        if (arr[1] >= 17)
        {
            d = (arr[1] - 17) * arr[2] * arr[3];
        }
        double sum = m + d;
        System.out.println("$" + sum);
    }

    public static void n5(Scanner scan)
    {
        System.out.println("Определяем ИМТ");
        String valm = " ";
        double mas = 0;
        String valh = " ";
        double heig = 0;
        System.out.println("Введите массу: ");
        String m = scan.nextLine();
        for (int i = 0; i < m.length(); i++)
        {
            char sym = m.charAt(i);
            int asc = (char) sym;
            if (asc == 32)
            {
                valm = m.substring(0, i);
            }
            if (asc == 112)
            {
                mas = Double.parseDouble(valm);
                mas = mas / 2.2;
                break;
            }
            if (asc == 107)
            {
                mas = Double.parseDouble(valm);
                break;
            }
        }
        System.out.println("Введите рост: ");
        String h = scan.nextLine();
        for (int i = 0; i < h.length(); i++)
        {
            char sym = h.charAt(i);
            int asc = (char) sym;
            if (asc == 32)
            {
                valh = h.substring(0, i);
            }
            if (asc == 105)
            {
                heig = Double.parseDouble(valh);
                heig = heig * 0.0254;
                break;
            }
            if (asc == 109)
            {
                heig = Double.parseDouble(valh);
                break;
            }
        }
        double res = mas / (heig * heig);
        String result = String.format("%.1f",res);
        if (res < 18.5)
        {
            System.out.println(result + " Underweight");
        }
        else if (res > 25)
        {
            System.out.println(result + " Overweight");
        }
        else
        {
            System.out.println(result + " Normal weight");
        }
    }

    public static int n6(Scanner scan)
    {
        System.out.println("Введите число:");
        int num = scan.nextInt();
        if (num < 10)
        {
            return 0;
        }
        else
        {
            int nextnum = 1;
            int c = 0;
            while (num > 0)
            {
                nextnum = nextnum * (num % 10);
                num = num / 10;
                if (num == 0)
                {
                    c++;
                    if (nextnum < 10)
                    {
                        break;
                    }
                    num = nextnum;
                    nextnum = 1;
                }
            }
            return c;
        }
    }

    public static void n7(Scanner scan)
    {
        System.out.println("Введите строку: ");
        String str = scan.nextLine();
        int c = 1;
        char[] arr = str.toCharArray();
        for (int i = 0; i < str.length(); i++)
        {
            if (arr[i] != '0')
            {
                for (int j = i+1; j < str.length(); j++)
                {
                    if (arr[i] == arr[j])
                    {
                        c++;
                        arr[j] = '0';
                    }
                }
                if (c > 1)
                {
                    System.out.print(arr[i] + "*" + c);
                }
                else
                {
                    System.out.print(arr[i]);
                }
                c = 1;
            }
        }
    }

    public static boolean n8(Scanner scan)
    {
        System.out.println("Введите 1 строку: ");
        String str1 = scan.nextLine();
        for (int i = str1.length() - 1; i > 0; i--)
        {
            char sym = str1.charAt(i);
            int asc = (char) sym;
            if (asc == 32)
            {
                str1 = str1.substring(i + 1, str1.length());
                break;
            }
        }
        System.out.println("Введите 2 строку: ");
        String str2 = scan.nextLine();
        for (int i = str2.length() - 1; i > 0; i--)
        {
            char sym = str2.charAt(i);
            int asc = (char) sym;
            if (asc == 32)
            {
                str2 = str2.substring(i + 1, str2.length());
                break;
            }
        }
        int cg = 0;
        int c = 0;
        for (int i = 0; i < str1.length(); i++)
        {
            char sym = str1.charAt(i);
            int asc = (char) sym;
            if (asc == 65 || asc == 69 || asc == 73 || asc == 79 || asc == 85 || asc == 89 || asc == 97 || asc == 101 || asc == 105 || asc == 111 || asc == 117 || asc == 121)
            {

                cg++;
            }
        }
        for (int i = 0; i < str1.length(); i++)
        {
            char sym = str1.charAt(i);
            int asc = (char) sym;
            if (asc == 65 || asc == 69 || asc == 73 || asc == 79 || asc == 85 || asc == 89 || asc == 97 || asc == 101 || asc == 105 || asc == 111 || asc == 117 || asc == 121)
            {
                for (int j = 0; j < str2.length(); j++)
                {
                    char sym2 = str2.charAt(j);
                    int asc2 = (char) sym2;
                    if (asc2 > 91)
                    {
                        if (asc2 == asc || asc2 == asc + 32)
                        {
                            c++;
                            break;
                        }
                    }
                    else
                    {
                        if (asc2 == asc || asc2 == asc - 32)
                        {
                            c++;
                            break;
                        }
                    }
                }
            }
        }
        if (cg == c)
            return true;
        else
            return false;
    }

    public static boolean n9(Scanner scan)
    {
        System.out.println("Введите число 1: ");
        int num1 = scan.nextInt();
        System.out.println("Введите число 2: ");
        int num2 = scan.nextInt();
        int tek1 = -1;
        int pred1 = -1;
        int tek2 = -1;
        int c1 = 0;
        int c2 = 0;
        boolean res = false;
        while (num1 > 0)
        {
            tek1 = num1 % 10;
            if (pred1 == tek1)
            {
                c1++;
                if (c1 == 2)
                {
                    while (num2 > 0)
                    {
                        tek2 = num2 % 10;
                        if (tek2 == tek1)
                        {
                            c2++;
                            if (c2 == 2)
                            {
                                res = true;
                                c2 = 0;
                                break;
                            }
                        }
                        num2 = num2 / 10;
                    }
                    c1 = 0;
                }
            }
            pred1 = tek1;
            num1 = num1 / 10;
        }
        if (res)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static int n10(Scanner scan)
    {
        System.out.println("Введите строку: ");
        String str = scan.nextLine();
        System.out.println("Введите символ: ");
        String se = scan.nextLine();
        int n = 0;
        int kon = 0;
        int c = 0;
        boolean flag = false;
        boolean found = false;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == se.charAt(0) && flag)
            {
                kon = i;
                flag = false;
                String sub = str.substring(n, kon);
                for (int j = 0; j < sub.length(); j++)
                {
                    found = false;
                    for (int k = 0; k < sub.length(); k++)
                    {
                        if (j != k && sub.charAt(j) == sub.charAt(k))
                        {
                            found = true;
                            break;
                        }
                    }
                    if (!found)
                    {
                        c++;
                    }
                }
                kon = 0;
                n = 0;
                i++;
            }
            if (i < str.length())
            {
                if (str.charAt(i) == se.charAt(0) && !flag)
                {
                    n = i;
                    flag = true;
                }
            }
        }
        return c;
    }
}

