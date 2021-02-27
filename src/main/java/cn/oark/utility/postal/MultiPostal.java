package cn.oark.utility.postal;

import cn.oark.utility.message.Message;
import cn.oark.utility.message.TextMsg;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;

public class MultiPostal implements Postal{
    @JSONField(name="postalID", ordinal = 1)
    private Long postalID=0L;      //雪花算法生成的报文
    @JSONField(name="postalFrom", ordinal = 2)
    private String postalFrom="";  //报文发送方
    @JSONField(name="postalTo", ordinal = 3)
    private ArrayList<String> postalTo=new ArrayList<String>();    //报文接收方
    @JSONField(name="postalType", ordinal = 4)
    private PostalType postalType=PostalType.Multi;  //该邮包类型
    @JSONField(name="postalMsg", ordinal = 5)
    private String postalMsg = "";  //该报文的信息正文
    @JSONField(name="postalWeight", ordinal = 6)
    private byte postalWeight = 0;

    public Long getPostalID() {
        return postalID;
    }

    public void setPostalID(Long postalID) {
        this.postalID = postalID;
    }

    public String getPostalFrom() {
        return postalFrom;
    }

    public void setPostalFrom(String postalFrom) {
        this.postalFrom = postalFrom;
    }

    public ArrayList<String> getPostalTo() {
        return postalTo;
    }

    public void setPostalTo(ArrayList<String> postalTo) {
        this.postalTo = postalTo;
    }

    public PostalType getPostalType() {
        return postalType;
    }

    public void setPostalType(PostalType postalType) {
        this.postalType = postalType;
    }

    public String getPostalMsg() {
        return postalMsg;
    }

    public void setPostalMsg(String postalMsg) {
        this.postalMsg = postalMsg;
    }

    public byte getPostalWeight() {
        return postalWeight;
    }

    public void setPostalWeight(byte postalWeight) {
        this.postalWeight = postalWeight;
    }
}
