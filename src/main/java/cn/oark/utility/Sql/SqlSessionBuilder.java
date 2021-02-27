package cn.oark.utility.Sql;

import cn.oark.utility.config.SysPart;
import org.apache.ibatis.session.SqlSession;

public class SqlSessionBuilder {
    public static SqlSession GetSqlSession(){
        if(!SysPart.isReady){
            SysPart.getReady();
        }
        if(SysPart.isReady) {
            return SysPart.sqlSessionFactory.openSession(true);
        }
        return null;
    }
}
