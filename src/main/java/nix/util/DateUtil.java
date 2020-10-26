package nix.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class DateUtil {

    private final static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static DateFormat stringFormat = new SimpleDateFormat("yyyy-MM-dd");

//    public static Date stringToDate(String input){
//        Date date = null;
//        if(input.equals("")) return null;
//        try {
//            date = (Date) stringFormat.parse(input);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return date;
//    }

    public static int stringToInt(String input){
        int i ;
        try{
            i = Integer.parseInt(input);
        } catch(NumberFormatException e){
            return 0;
        }
        return i;
    }

    public static String timestampToString(Timestamp input){
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Timestamp(System.currentTimeMillis())).toString();
        return date;
    }


}
