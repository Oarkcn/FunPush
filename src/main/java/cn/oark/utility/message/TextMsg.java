package cn.oark.utility.message;

public class TextMsg implements Message{
    String msgId;
    Long msgTime;
    String msgTitle;
    String msgContext;
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
