package cn.oark.utility.message;

import com.alibaba.fastjson.annotation.JSONField;

public class TextMsg implements Message{
    @JSONField(name="msgId", ordinal = 1)
    String msgId="";
    @JSONField(name="msgTime", ordinal = 2)
    Long msgTime=0L;
    @JSONField(name="msgTitle", ordinal = 3)
    String msgTitle="";
    @JSONField(name="msgContext", ordinal = 4)
    String msgContext="";
    @JSONField(name="msgType", ordinal = 5)
    String msgType="Text";  //文本消息，只有消息正文与标题 限制长度64+256 多余截取
    @Override
    public String getMsgId() {
        return msgId;
    }
    public void setMsgId(String mid){
        msgId = mid;
    }

    public Long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(Long msgTime) {
        this.msgTime = msgTime;
    }

    public String getMsgContext() {
        return msgContext;
    }

    public void setMsgContext(String msgContext) {
        this.msgContext = msgContext;
    }

    public String getMsgType() {
        return msgType;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }
}
