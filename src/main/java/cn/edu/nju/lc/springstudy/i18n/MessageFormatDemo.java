package cn.edu.nju.lc.springstudy.i18n;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MessageFormatDemo {

    public static void main(String[] args) {

        int planet = 7;
        String event = "a disturbance in the Force";

        MessageFormat messageFormat = new MessageFormat("At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.");
        String result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        //Subformats that have already been created are not affected.
        messageFormat.setLocale(Locale.ENGLISH);
        messageFormat.applyPattern("At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.");
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        messageFormat.setFormat(1, new SimpleDateFormat("YYYY-MM-dd HH:mm:ss"));
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

    }

}
