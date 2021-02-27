package cn.oark.utility.postal;

import cn.oark.utility.message.Message;
import cn.oark.utility.message.TextMsg;
import com.alibaba.fastjson.annotation.JSONField;

public interface Postal {
    @JSONField(name="postalID", ordinal = 1)
    Long postalID=0L;      //雪花算法生成的报文
    @JSONField(name="postalFrom", ordinal = 2)
    String postalFrom = "";  //报文发送方
    @JSONField(name="postalTo", ordinal = 3)
    String postalTo = "";    //报文接收方
    @JSONField(name="postalType", ordinal = 4)
    PostalType postalType = PostalType.Single;  //该邮包类型
    @JSONField(name="postalMsg", ordinal = 5)
    Message postalMsg = new TextMsg();  //该报文的信息正文
    @JSONField(name="postalWeight", ordinal = 6)
    byte postalWeight = 0;
}
