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
        	boolean firstCharUppercase = false;
            String origPrefix = "";
            int vowelIndex = -1;
            if (startsWithVowel(a))
            { 
                String startedWithAVowel = a+"way";
                temp += startedWithAVowel+" "; 
            }
            else //starts with one or more consonants
            {   //get index of first vowel if its there.

            	String res = null;
               	for (int k=0; k<a.length();k++)
               	{
               		char nextChar = a.charAt(k); //extract next character from token
               		
               		if (k==0 && isUpperCase(nextChar))firstCharUppercase = true;

               		if (!isVowel(nextChar))
               		{	//accumulate consonants up to first vowel
               			origPrefix += nextChar;
               			continue;
               		}
               		else
               		{	//found a vowel
               			vowelIndex = k;
               			res = a.substring(vowelIndex) + origPrefix + "ay";
               			break;
               		}

               	}

               	if (firstCharUppercase)
               	{
               		String first = ""+res.charAt(0);

               		System.out.println("-->"+res);
               	}

               	//append current token to the phrase
               	temp += res+" ";
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

    public static boolean isUpperCase(char x)
    {
    	String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	String test = ""+x;
    	if (alpha.contains(test)) return true;
    	else return false;	
    }

	public static void main(String[] args){PigLatin p = new PigLatin();}
}