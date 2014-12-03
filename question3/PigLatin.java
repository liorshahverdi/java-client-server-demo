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
               	for (int k=0; k<a.length();k++)
               	{
               		char n = a.charAt(k);
               		System.out.println("ch= "+n);
               		if (isVowel(n)) {
               			System.out.println("At last! A vowel! --> "+n);
               			break;
               		}
               		else
               		{
               			System.out.println(n+" is not a vowel");
               			firstVowelIndex++;
               			origPrefix+=n;
               			if (origPrefix.equals(a)){
               				System.out.println("At this moment, origPrefix equals a");
               			}
               			System.out.println("origPrefix is now\t"+origPrefix+"\ta is "+a);
               		}
               	}
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