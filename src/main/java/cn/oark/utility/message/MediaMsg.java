package cn.oark.utility.message;

public class MediaMsg implements Message{
    String msgId;
    Long msgTime;
    String msgTitle;
    String msgContext;      //消息正文与标题 限制长度128+12K 多余截取
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
