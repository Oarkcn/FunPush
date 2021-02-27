package cn.oark.dao.group;

import cn.oark.utility.Sql.pojo.admin.Admin;
import cn.oark.utility.Sql.pojo.group.GroupUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupUserMapper {
    int getGroupUserCount();
    int getGroupUserStatus();
    int getGroupUserStatusById(String gu_uid);
    GroupUser verifyByid(@Param("gu_uid") String gu_uid, @Param("gu_upwd")String gu_upwd);
    String getSaltById(String gu_uid);
    GroupUser getInfoByid(String gu_uid);
    GroupUser getInfoByAccount(String gu_uaccount);
    boolean setNewGroupUser(GroupUser user);
    boolean setGroupUserInfo(GroupUser user);
    List<GroupUser> getAllGroupUser();
}
