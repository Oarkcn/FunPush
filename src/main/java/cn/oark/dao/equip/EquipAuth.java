package cn.oark.dao.equip;

import java.sql.Date;

public class EquipAuth {
    private String eqid = "";
    private String eqkey = "";
    private Date eqtime;

    public String getEqid() {
        return eqid;
    }

    public void setEqid(String eqid) {
        this.eqid = eqid;
    }

    public String getEqkey() {
        return eqkey;
    }

    public void setEqkey(String eqkey) {
        this.eqkey = eqkey;
    }

    public Date getEqtime() {
        return eqtime;
    }

    public void setEqtime(Date eqtime) {
        this.eqtime = eqtime;
    }
}
