package Convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Convert {

    /**
     * chuyen mot xau ky tu sang kieu int.
     */
    public static int stringtoInt(String s) {
         int res = 0;
         char c = ' ';

         for (int i = 0; i < s.length(); ++i) {
             c = s.charAt(i);

             res = res * 10 + (c - '0');
         }

         return res;
    }

    /**
     * Lay cac so nguyen tu mot xau va tra ve mot mang la Integer.
     */
    public static List<Integer> takeNumberFromString(String s) {
        List <Integer> arr = new ArrayList<>();
        s = s + " ";

        String tmp = "";
        int Num = 0;
        Integer Int;

        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == ' ') {
                Num = stringtoInt(tmp);
                Int = new Integer(Num);
                arr.add(Int);
                tmp = "";
            }
            else {
                tmp = tmp + s.charAt(i);
            }
        }

        return arr;
    }
}
