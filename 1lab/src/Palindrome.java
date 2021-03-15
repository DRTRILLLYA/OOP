import java.util.Scanner;

public class Palindrome
{
    public static void main(String[] args)
    {
        System.out.println("Введите строку: ");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        System.out.print(str);
        if(isPalindrome(str) == true)
        {
            System.out.print(" это палиндром");
        }
        else
        {
            System.out.print(" это не палиндром");
        }
    }

    public static boolean isPalindrome(String str)
    {
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--)
        {
            result = result + str.charAt(i);
        }
        if(str.equals(result))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}