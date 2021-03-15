public class Primes {
    public static void main(String[] args)
    {
        for (int i = 2; i <= 100; i++)
        {
            boolean flag = isPrime(i);
            if (flag == true)
            {
                System.out.print(i + " ");
            }
        }

    }
    public static boolean isPrime(int num)
    {
        int ost = 0;
        boolean flag = true;
        for (int i = 2; i < num; i++)
        {
            ost = num % i;
            if (ost == 0)
            {
                flag = false;
                break;
            }
            else
            {
                flag = true;
            }
        }
        return flag;
    }
}