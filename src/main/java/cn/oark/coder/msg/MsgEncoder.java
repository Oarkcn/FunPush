package cn.oark.coder.msg;

import cn.oark.utility.message.Message;
import com.alibaba.fastjson.JSON;

public class MsgEncoder {
    public String EncodeMsg(Object msg){
        return JSON.toJSONString(msg);
    }
}
