package cn.oark.dao.admin;

import cn.oark.utility.Sql.pojo.admin.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    int getAdminCount();
    int getAdminStatus();
    int getAdminStatusById(String ma_id);
    Admin verifyByid(@Param("ma_id") String ma_id, @Param("ma_pwd")String ma_pwd);
    String getSaltById(String ma_id);
    Admin getInfoByid(String ma_id);
    Admin getInfoByAccount(String ma_account);
    boolean setNewAdmin(Admin admin);
    boolean setAdminInfo(Admin admin);
    List<Admin> getAllAdmin();
}
