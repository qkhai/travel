package cn.it;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {

    public String data2Str(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat();
        return sdf.format(date);
    }

    public Date str2Data(String str){
        SimpleDateFormat sdf = new SimpleDateFormat();
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
