package cn.oark.utility.postal;

public class PostalFactory {
    public static Postal getPostal(PostalType postalType){
        if(postalType == PostalType.Single)
            return new SinglePostal();
        else if(postalType == PostalType.Multi)
            return new MultiPostal();
        else
            return new SinglePostal();
    }
}
