package cn.oark.dao.admin;

import cn.oark.coder.encrypt.password.Password;
import cn.oark.operator.obid.UID;
import cn.oark.utility.Sql.SqlSessionBuilder;
import cn.oark.utility.Sql.pojo.admin.Admin;
import org.apache.ibatis.session.SqlSession;

public class TestAdminMapper {
    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.setMa_id(UID.getUUID32());
        admin.setMa_account("admin@oark.cn");
        admin.setMa_name("oark");
        admin.setMa_status(4);
        admin.setMa_pwd("123456");
        Password.GenerateNewPwd(admin);
        try(SqlSession session = SqlSessionBuilder.GetSqlSession()){
            AdminMapper adminMapper = session.getMapper(AdminMapper.class);
            System.out.println(adminMapper.setNewAdmin(admin));
            System.out.println(adminMapper.getAdminCount());
            System.out.println(adminMapper.getAdminStatus());
            Admin admin1 = new Admin();
            admin1.setMa_pwd("123456");
            admin1.setMa_id(admin.getMa_id());
            admin1.setMa_salt(adminMapper.getSaltById(admin1.getMa_id()));
            Password.EncodePwd(admin1);
            Admin admin2 = adminMapper.verifyByid(admin1.getMa_id(),admin1.getMa_pwd());
            admin2.setMa_salt(adminMapper.getSaltById(admin2.getMa_id()));
            admin2.setMa_status(8);
            admin2.setMa_pwd("123456");
            Password.EncodePwd(admin2);
            System.out.println(adminMapper.setAdminInfo(admin2));
        }

    }
}
