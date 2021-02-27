package cn.oark.coder.encrypt.password;

import cn.oark.coder.Sha256.Sha256;
import cn.oark.operator.obid.UID;
import cn.oark.utility.Sql.pojo.admin.Admin;

public class Password {
    public static void GenerateNewPwd(Admin admin){
        admin.setMa_salt(UID.getUUID32());
        admin.setMa_pwd(Sha256.getSHA256(admin.getMa_pwd()+admin.getMa_salt()));
    }
    public static void EncodePwd(Admin admin){
        admin.setMa_pwd(Sha256.getSHA256(admin.getMa_pwd()+admin.getMa_salt()));
    }
}
