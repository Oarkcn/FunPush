package cn.oark.utility.Sql.pojo.group;

public class GroupUser {
    private String gu_uid = "";
    private String gu_name = "";
    private String gu_uaccount = "";
    private int gu_ulevel = 0;
    private String gu_upwd = "";

    private String gu_usalt="";

    public String getGu_usalt() {
        return gu_usalt;
    }

    public void setGu_usalt(String gu_usalt) {
        this.gu_usalt = gu_usalt;
    }

    public String getGu_uid() {
        return gu_uid;
    }

    public void setGu_uid(String gu_uid) {
        this.gu_uid = gu_uid;
    }

    public String getGu_name() {
        return gu_name;
    }

    public void setGu_name(String gu_name) {
        this.gu_name = gu_name;
    }

    public String getGu_uaccount() {
        return gu_uaccount;
    }

    public void setGu_uaccount(String gu_uaccount) {
        this.gu_uaccount = gu_uaccount;
    }

    public int getGu_ulevel() {
        return gu_ulevel;
    }

    public void setGu_ulevel(int gu_ulevel) {
        this.gu_ulevel = gu_ulevel;
    }

    public String getGu_upwd() {
        return gu_upwd;
    }

    public void setGu_upwd(String gu_upwd) {
        this.gu_upwd = gu_upwd;
    }
}
