import java.math.BigDecimal;

public class ConverterDB {
    public static String output (String s ){

        BigDecimal decimal = new BigDecimal(s);

        String binary = "";
        BigDecimal zero = new BigDecimal("0");
        BigDecimal dois = new BigDecimal("2");

        if (decimal.equals(0)){
            return "0";
        }
        while (decimal.compareTo(zero)==1){
            binary = decimal.remainder(dois)+ binary;
            decimal=decimal.divide(dois).setScale(0, BigDecimal.ROUND_DOWN);
        }
        return binary;
    }
}

