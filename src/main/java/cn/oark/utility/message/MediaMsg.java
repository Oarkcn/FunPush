package cn.oark.utility.message;

import com.alibaba.fastjson.annotation.JSONField;

public class MediaMsg implements Message{
    @JSONField(name="msgId", ordinal = 1)
    String msgId;
    @JSONField(name="msgTime", ordinal = 2)
    Long msgTime;
    @JSONField(name="msgTitle", ordinal = 3)
    String msgTitle;
    @JSONField(name="msgContext", ordinal = 4)
    String msgContext;      //消息正文与标题 限制长度128+12K 多余截取
    @JSONField(name="msgType", ordinal = 5)
    String msgType="Media";
    @Override
    public String getMsgId() {
        return msgId;
    }
    public void setMsgId(String msgId){
        this.msgId = msgId;
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
