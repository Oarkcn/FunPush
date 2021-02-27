package cn.oark.coder.msg;

import cn.oark.utility.message.LogoMsg;
import cn.oark.utility.message.MediaMsg;
import cn.oark.utility.message.Message;
import cn.oark.utility.message.TextMsg;
import cn.oark.utility.postal.MultiPostal;
import cn.oark.utility.postal.Postal;
import cn.oark.utility.postal.SinglePostal;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class MsgDecoder {
    public Message DecodeMsg(String jsonString){
        JSONObject object = JSON.parseObject(jsonString);
        if(object.get("msgType") == "Logo"){
            return JSON.parseObject(jsonString, LogoMsg.class);
        }else if(object.get("msgType") == "Media"){
            return JSON.parseObject(jsonString, MediaMsg.class);
        }else if(object.get("msgType")=="Text"){
            return JSON.parseObject(jsonString,TextMsg.class);
        }
        return JSON.parseObject(jsonString,Message.class);
    }
    public Postal DecodePostal(String jsonString){
        JSONObject object = JSON.parseObject(jsonString);
        if(object.get("postalTo") instanceof List){
            return JSON.parseObject(jsonString, MultiPostal.class);
        }else{
            return JSON.parseObject(jsonString, SinglePostal.class);
        }

    }
//    public Object DecodeMsg(Object jsonObject){
//        return new TextMsg();
//    }
}
