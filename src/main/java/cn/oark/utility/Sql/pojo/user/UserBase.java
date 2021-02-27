package cn.oark.utility.Sql.pojo.user;

public class UserBase {
    public String ua_id = "";
    public String ua_name = "";
    public String ua_account = "";
    public int ua_level = 0;

    public String getUa_id() {
        return ua_id;
    }

    public void setUa_id(String ua_id) {
        this.ua_id = ua_id;
    }

    public String getUa_name() {
        return ua_name;
    }

    public void setUa_name(String ua_name) {
        this.ua_name = ua_name;
    }

    public String getUa_account() {
        return ua_account;
    }

    public void setUa_account(String ua_account) {
        this.ua_account = ua_account;
    }

    public int getUa_level() {
        return ua_level;
    }

    public void setUa_level(int ua_level) {
        this.ua_level = ua_level;
    }
}
