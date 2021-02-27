package cn.oark.utility.Sql.pojo.admin;

public class Admin {
    private String ma_id = "";
    private String ma_name = "";
    private String ma_account = "";
    private int ma_status =0;
    private String ma_pwd = "";

    private String ma_salt = "";
    public String getMa_salt() {
        return ma_salt;
    }

    public void setMa_salt(String ma_salt) {
        this.ma_salt = ma_salt;
    }
    public String getMa_id() {
        return ma_id;
    }

    public void setMa_id(String ma_id) {
        this.ma_id = ma_id;
    }

    public String getMa_name() {
        return ma_name;
    }

    public void setMa_name(String ma_name) {
        this.ma_name = ma_name;
    }

    public String getMa_account() {
        return ma_account;
    }

    public void setMa_account(String ma_account) {
        this.ma_account = ma_account;
    }

    public int getMa_status() {
        return ma_status;
    }

    public void setMa_status(int ma_status) {
        this.ma_status = ma_status;
    }

    public String getMa_pwd() {
        return ma_pwd;
    }

    public void setMa_pwd(String ma_pwd) {
        this.ma_pwd = ma_pwd;
    }
}
