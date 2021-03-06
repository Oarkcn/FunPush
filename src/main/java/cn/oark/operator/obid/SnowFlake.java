package cn.oark.operator.obid;

import cn.oark.utility.config.SysConfig;
import cn.oark.utility.config.SysPart;
import jdk.jfr.Unsigned;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class SnowFlake {

    /*  　雪花算法生成唯一ID    */
    /** 开始时间截 (2020-01-01) */
    private final long twepoch = 1577808000000L;

    /** 机器id所占的位数 */
    private final long workerIdBits = 4L;

    /** 数据标识id所占的位数 */
    private final long datacenterIdBits = 4L;

    /** 支持的最大机器id，结果是15 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /** 支持的最大数据标识id，结果是15 */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /** 序列在id中占的位数 */
    private final long sequenceBits = 9L;
    /** 随机占的位数 */
    private final long randomBit = 5L;
    /** 机器ID向左移14位 */
    private final long workerIdShift = sequenceBits+randomBit;

    /** 数据标识id向左移18位(14+4) */
    private final long datacenterIdShift = sequenceBits + randomBit + workerIdBits;

    /** 时间截向左移22位(14+4+4) */
    private final long timestampLeftShift = sequenceBits + randomBit + workerIdBits + datacenterIdBits;

    /** 生成序列的掩码，这里为9bit 16384 */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    /** 生成随机序列的掩码，这里与序列共享14位*/
    private final long randomMask = (-1L ^ (-1L << (randomBit))) << sequenceBits;
    /** 工作机器ID(0~15) */
    private long workerId = SysConfig.workerID;

    /** 数据中心ID(0~15) */
    private long datacenterId = SysConfig.centerID;

    /** 毫秒内序列(0~16383) */
    private long sequence = 0L;

    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;

    //==============================Constructors=====================================
    /**
     * 构造函数
     * @param workerId 工作ID (0~63)
     * @param datacenterId 数据中心ID (0~15)
     * @param　好像没什么用了，因为上面已经赋值，不需要再次进行赋值　验证放到读取配置时候做吧
     */
    public SnowFlake(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    // ==============================Methods==========================================
    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            //原来算法不能一直用来分配，否则必裂开
            sequence = (sequence + 1) & sequenceMask;
            //sequence = sequence+1L ;
            //毫秒内序列溢出
            if (sequence == 0 ) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }
        //System.out.print(timestamp + " " + lastTimestamp + " " +sequence + " ");
        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) //
                | (datacenterId << datacenterIdShift) //
                | (workerId << workerIdShift) //
                | sequence
                | (((new Random()).nextInt()) & randomMask );//随机数填充
    }
    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    //==============================Test=============================================
    /** 测试 已经弃用，改用下面的give方法 */

//    public static void main(String[] args) {
//        SnowFlake idWorker = new SnowFlake(SysConfig.workerID, SysConfig.centerID);
//        Long start = System.currentTimeMillis();
//        ArrayList<SnowFlake> snowFlakeList = new ArrayList<>(SysConfig.obidThreadNum);
//        for (int snowFlakeListCount = 0;snowFlakeListCount < SysConfig.obidThreadNum ;snowFlakeListCount++){
//            snowFlakeList.add(new SnowFlake(snowFlakeListCount,SysConfig.centerID));
//        }
//        ExecutorService executorService = Executors.newFixedThreadPool(SysConfig.obidThreadNum);
//        //ExecutorService executorService1 = new ThreadPoolExecutor(1,SysConfig.obidThreadNum,0L,TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(SysConfig.obidQueueNum));
//        AtomicBoolean flag= new AtomicBoolean(false);
//        for (int i = 0; i < 10000000; i++) {
//            int finalI = i;
//            /*flag.set(false);
//
//            while(!flag.get()){
//                try{
//                    executorService.execute(() ->{
//                        Long id = snowFlakeList.get(finalI % SysConfig.obidThreadNum).nextId();
//                        //System.out.println(Thread.currentThread().getName() + "，" + (System.currentTimeMillis()-start) + "ms 轮 执行" + Long.toHexString(id));
//                        //System.out.println((System.currentTimeMillis()-start) + "ms  " +Long.toHexString(id) );
//                        flag.set(true);
//                    });
//                }catch (RejectedExecutionException rejectedExecutionException){
//                    try {
//                        System.out.println("reject "+ finalI);
//                        Thread.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }finally {
//
//                }
//            }
//
//             */
//            executorService.execute(() ->{
//                Long id = snowFlakeList.get(finalI % SysConfig.obidThreadNum).nextId();
//                SysPart.obidList2.add(id);
//                //System.out.println(Thread.currentThread().getName() + "，" + (System.currentTimeMillis()-start) + "ms 轮 执行" + Long.toHexString(id));
//                //System.out.println((System.currentTimeMillis()-start) + "ms  " +Long.toHexString(id) );
//            });
//            //long id = idWorker.nextId();
//            //System.out.println(Long.toBinaryString(id));
//            //System.out.println((System.currentTimeMillis()-start) + "ms  " +Long.toHexString(id) );
//
//        }
//        executorService.shutdown();
//        try {
//            executorService.awaitTermination(200, TimeUnit.MINUTES);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println( (System.currentTimeMillis()-start) + "ms fini");
//    }
    /**     give方法，使用雪花算法多线程生成obid 具体参数见SysPart.java     **/
    public String give(int num){
        if(!SysConfig.isObidWorking) return "NoWorking";
        ExecutorService executorService = SysPart.obidExecutorService;
        Long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            int finalI = i;
            executorService.execute(() ->{
                Long id = SysPart.snowFlakeList.get(finalI % SysConfig.obidThreadNum).nextId();
                try {
                    SysPart.obidList2.offer(id,1,TimeUnit.MINUTES);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println(Thread.currentThread().getName() + "，" + (System.currentTimeMillis()-start) + "ms 轮 执行" + Long.toHexString(id));
                //System.out.println((System.currentTimeMillis()-start) + "ms  " +Long.toHexString(id) );
            });
        }
        return ((System.currentTimeMillis()-start) + "ms fini");
    }
    public void give(){
        ExecutorService executorService = SysPart.obidExecutorService;
        //Long start = System.currentTimeMillis();
        //for (int i = 0; i < num; i++) {
        //    int finalI = i;
            executorService.execute(() ->{
                Long id = SysPart.snowFlakeList.get(((new Random().nextInt())>>>4)%SysConfig.obidThreadNum).nextId();
                try {
                    SysPart.obidList2.offer(id,1,TimeUnit.MINUTES);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println(Thread.currentThread().getName() + "，" + (System.currentTimeMillis()-start) + "ms 轮 执行" + Long.toHexString(id));
                //System.out.println((System.currentTimeMillis()-start) + "ms  " +Long.toHexString(id) );
            });
    }

}
