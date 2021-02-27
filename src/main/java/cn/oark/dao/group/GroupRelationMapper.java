package cn.oark.dao.group;

import cn.oark.utility.Sql.pojo.group.Group;
import cn.oark.utility.Sql.pojo.group.GroupRelation;

import java.util.List;

public interface GroupRelationMapper {
    int getGroupUserCount();
    int getPermissionByUid(String uid);
    String getGuidByUid(String uid);
    List<String> getUidByGuid(String guid);
    List<GroupRelation> getUidByGuidWithPermission(String Guid);
    boolean setNewRelation(GroupRelation relation);
    boolean setRelationInfo(GroupRelation relation);
    boolean deleteRelation(GroupRelation relation);
}
