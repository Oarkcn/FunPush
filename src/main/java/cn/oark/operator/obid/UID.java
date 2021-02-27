package cn.oark.operator.obid;

import java.util.UUID;
public class UID {
    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
