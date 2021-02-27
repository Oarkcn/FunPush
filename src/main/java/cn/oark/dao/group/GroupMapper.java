package cn.oark.dao.group;

import cn.oark.utility.Sql.pojo.admin.Admin;
import cn.oark.utility.Sql.pojo.group.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMapper {
    int getGroupCount();
    int getGroupStatus();
    int getGroupStatusById(String gu_guid);
    Group getInfoByid(String gu_guid);
    Group getInfoByName(String ma_account);
    boolean setNewGroup(Group group);
    boolean setGroupInfo(Group group);
    List<Group> getAllGroup();
}
