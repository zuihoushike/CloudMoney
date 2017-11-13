package name.hd.cloudmoney.custom;

import java.text.DecimalFormat;

/**
 * Created by AkiNobunaga on 2017/11/13.
 */

public class FormatDouble {
    public static String f2(double x){
        return new DecimalFormat("0.00").format(x);
    }
}
