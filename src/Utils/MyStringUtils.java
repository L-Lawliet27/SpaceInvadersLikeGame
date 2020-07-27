package Utils;

public class MyStringUtils{

    public static String invert(String inString){
        String outString = "";
        for (int i=0; i < inString.length(); i++)
            outString = inString.charAt(i) + outString;
    return outString;
    }

// There are several replace() methods in the String library class
    public static String replace(String base, String subIn, String subOut){
        int startSubIn = base.indexOf(subIn);
        if (startSubIn != -1) // subIn occurs at least once in base
            base = base.substring(0, startSubIn) + subOut + base.substring(startSubIn + subIn.length());
        return base;
    }


    public static String repeat(String elmnt, int length)
    {
        String result = "";
        for (int i = 0; i < length; i++)
        {
            result += elmnt;
        }
        return result;

    }


    public static String centre(String text, int len)
    {
        String out = String.format("%"+len+"s%s%"+len+"s", "",text,"");

        float mid = (out.length()/2);

        float start = mid - (len/2);

        float end = start + len;

        return out.substring((int)start, (int)end);
    }

}