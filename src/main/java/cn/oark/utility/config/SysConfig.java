package cn.oark.utility.config;

public class SysConfig {
    /*
        机器配置基础信息
     */
    public static Long workerID = 15L;
    public static Long centerID = 15L;
    public static String workerIP = "localhost";
    public static String workerPort = "22165";
    /*
        服务器功能开关
     */
    public static boolean isMsgWorking = true;
    public static boolean isObidWorking = true;
    public static boolean isMsgParsing = true;
    /*
        多机配合时使用，现在无用
     */
    public static String workMode = "0";    //多机配合时用
    public static String masterIP = "";
    public static String masterMode = "";
    /*
        推送使用的线程及队列长度
     */
    public static int msgThreadNum = 4;          //推送线程数
    public static int msgQueueNum = 1000;          //推送队列长度
    /*
        obid生成相关
     */
    public static int obidWaitQueueNum = 2048;  //生成等待队列的长度
    public static int obidThreadNum = 4;        //生成线程数
    public static int obidQueueNum = 1024;      //生成结果的缓存数

    /*
        处理
     */
}
