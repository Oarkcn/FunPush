package cn.oark.utility.message;

public class MediaFactory implements Provider{

    @Override
    public Message get() {
        return new MediaMsg();
    }
}
