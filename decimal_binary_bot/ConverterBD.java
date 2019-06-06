
import java.math.BigInteger;

public class ConverterBD {

public static String output (String s ){

    int counter = 0;
    while (counter <= s.length() - 1) {
        if ((s.charAt(counter) == '1') || (s.charAt(counter) == '0')) {
            counter++;
        } else {
            return("The number you typed is not binary! \nTry it again, use /markup. ");
        }
    }
        BigInteger decimal = new BigInteger("0");
        BigInteger base= new BigInteger("2");
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)== '1'){
                decimal=decimal.add(base.pow(s.length()-1 -i));
            }
        }
        return decimal.toString();
    }
}