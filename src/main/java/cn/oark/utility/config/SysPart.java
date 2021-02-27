package cn.oark.utility.config;

import cn.oark.operator.obid.SnowFlake;
import cn.oark.utility.Sql.SqlSessionBuilder;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

public class SysPart {
    public static Base64.Decoder base64Decoder = Base64.getDecoder();
    public static Base64.Encoder base64Encoder = Base64.getEncoder();
    public static SnowFlake obidWorker = new SnowFlake(SysConfig.workerID, SysConfig.centerID);
    public static ArrayList<SnowFlake> snowFlakeList = new ArrayList<SnowFlake>(SysConfig.obidThreadNum);
    public static ExecutorService obidExecutorService = new ThreadPoolExecutor(SysConfig.obidThreadNum, SysConfig.obidThreadNum,0L, TimeUnit.MILLISECONDS,
                                      new ArrayBlockingQueue<>(SysConfig.obidWaitQueueNum));
    public static ArrayBlockingQueue<Long> obidList2 = new ArrayBlockingQueue<Long>(SysConfig.obidQueueNum);
    public static boolean isReady = false;

    //public static ExecutorService mainMessageExecutorService = new ThreadPoolExecutor(SysConfig.msgThreadNum,SysConfig.msgThreadNum,0L,TimeUnit.MILLISECONDS,
    //                                new ArrayBlockingQueue<>(SysConfig.msgQueueNum));
    public static ConcurrentHashMap<Long,Object> listOfCurrentConnection = new ConcurrentHashMap<Long,Object>(128);

    public static ChannelGroup onlineChannelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static SqlSessionFactory sqlSessionFactory = null;

    public static void  getReady(){
        /*
            初始化生成队列
         */
        if(!isReady){
            try{
                for (int snowFlakeListCount = 0;snowFlakeListCount < SysConfig.obidThreadNum ;snowFlakeListCount++){
                    snowFlakeList.add(new SnowFlake(snowFlakeListCount,SysConfig.centerID));
                }
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
                        isReady = true;
            }catch (Exception ex){
                isReady = false;
                System.out.println("初始化失败");
            }finally {

            }

        }


    }
}
