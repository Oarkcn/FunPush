package cn.oark.dao.user;

import java.sql.Date;
import java.sql.Time;

public class UserExtra {
    public String ua_id = "";
    public Date ua_regtime ;
    public String ua_sex = "";
    public Date ua_birth ;

    public String getUa_id() {
        return ua_id;
    }

    public void setUa_id(String ua_id) {
        this.ua_id = ua_id;
    }

    public Date getUa_regtime() {
        return ua_regtime;
    }

    public void setUa_regtime(Date ua_regtime) {
        this.ua_regtime = ua_regtime;
    }

    public String getUa_sex() {
        return ua_sex;
    }

    public void setUa_sex(String ua_sex) {
        this.ua_sex = ua_sex;
    }

    public Date getUa_birth() {
        return ua_birth;
    }

    public void setUa_birth(Date ua_birth) {
        this.ua_birth = ua_birth;
    }
}
