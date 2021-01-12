package cn.oark.operator.obid;

import cn.oark.utility.config.SysConfig;

import java.util.Random;

public class TestRandom {
    static void test(){

        String rand;
        Random r = new Random();
        rand =  Integer.toString(r.nextInt(1000));
        rand= System.currentTimeMillis()+rand + SysConfig.workerID.toString();
        System.out.println(rand);
        return ;
    }
    public static void main(String[] args){
        int rand;
        Long start = System.currentTimeMillis();
        for (int i = 0;i<1000000;i++){
            test();
        }
        Long stop = System.currentTimeMillis();
        System.out.println(stop-start);
    }
}
