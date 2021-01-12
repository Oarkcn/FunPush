package cn.oark.utility.postal;

import cn.oark.utility.message.Message;

public class SinglePostal implements Postal{
    Long postalID;      //雪花算法生成的报文
    String postalFrom;  //报文发送方
    String postalTo;    //报文接收方
    PostalType postalType=PostalType.Single;  //该邮包类型
    Message postalMsg;  //该报文的信息正文
    byte postalWeight = 0;
    @Override
    public Long getID() {
        return null;
    }
}
