package cn.oark.operator.test;

import cn.oark.operator.obid.UID;
import cn.oark.utility.config.SysPart;

public class testListConn {
    public static void main(String[] args) throws Exception {
        Long start = System.currentTimeMillis();
        System.out.println("启动于 " + start);
        SysPart.getReady();
        System.out.println("初始化用时 " + (System.currentTimeMillis()-start));
        start = System.currentTimeMillis();
        for (int i = 0;i<10000000;i++){
            SysPart.obidWorker.give();
        }
        Long[] list = new Long[10000000];
        System.out.println("生成用时 " + (System.currentTimeMillis()-start));
        start = System.currentTimeMillis();
        for (int i = 0;i<10000000;i++){
            list[i] = SysPart.obidList2.poll();
            SysPart.listOfCurrentConnection.put(list[i],(UID.getUUID32()));
        }
        System.out.println("装载用时 " + (System.currentTimeMillis()-start));
        start = System.currentTimeMillis();
        for(int i = 0; i < 10000000;i++){
            SysPart.listOfCurrentConnection.get(list[i]);
        }
        System.out.println("查询用时 " + (System.currentTimeMillis()-start));
    }
}
