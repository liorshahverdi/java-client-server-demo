import java.util.*;

public class PigLatin{

	private Scanner sc;

	public PigLatin()
	{
		sc = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String inp = sc.nextLine();
		String[] t = inp.split(" ");
		String output = translate(t);
		System.out.println("Out-> "+ output);
	}
	
	public static String translate (String[] tokens)
    {
        String temp = "";

        //convert each string in the phrase to pig latin
        for (String a : tokens){
        	boolean foundVowel = false;
            int firstVowelIndex = 0;
            String origPrefix = "";
            if (startsWithVowel(a))
            { 
                String startedWithAVowel = a+"way";
                temp += startedWithAVowel+" "; 
            }
            else 
            {   //get index of first vowel
               	
            }
        }

        return temp;
    } 

    public static boolean isVowel(char s)
    {
        if ((s =='a') || (s == 'A') ||
            (s =='e') || (s == 'E') ||
            (s =='i') || (s == 'I') ||
            (s =='o')  || (s == 'O') ||
            (s =='u')  || (s == 'U')) return true;
        else return false;
    }

    public static boolean startsWithVowel(String s)
    {
        if (s.startsWith("a") || s.startsWith("A") ||
            s.startsWith("e") || s.startsWith("E") ||
            s.startsWith("i") || s.startsWith("I") ||
            s.startsWith("o") || s.startsWith("O") ||
            s.startsWith("u") || s.startsWith("U") ) return true;
        else return false;
    }

	public static void main(String[] args){
		PigLatin p = new PigLatin();

	}
}