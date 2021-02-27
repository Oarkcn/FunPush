package cn.oark.coder.basecode;

public class EncodeBase32 {
    final static char[] digits = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8','9', 'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H','J', 'K', 'L','M', 'N',  'P',
            'R', 'S', 'T', 'U', 'W', 'X', 'Y','Z' };
    final static Long numMask = 0x1fL;
    public static String numToString(Long num){
        if (num == null) return "";
        if (num == 0  ) return  "0";
        char[]newWord = new char[13];
        String string = "0";
        byte flag = 12;
        while(num!=0){
            newWord[flag] = digits[(int) (num & numMask)];
            num = num >>> 5;
            flag --;
        }
        string = String.valueOf(newWord).toUpperCase();
        return string;
    }
    public static String hexToString(String s){
        if (s == null) return "";
        int num=0,opnum = 0,opnum2 = s.length()*4/5+1,flag = s.length()-1;
        char[] newWord = new char[opnum2];
        try{
            s=s.toLowerCase();
            opnum2--;
            while(flag >=0){

                num = (num ) | ((Integer.parseInt(s.substring(flag,flag+1),16))<<opnum);
                opnum+=4;
                flag-=1;
                if(opnum>=5){
                    newWord[opnum2]=digits[(int)(num & numMask)];
                    num = num >>> 5;
                    opnum2--;
                    opnum-=5;
                }
            }
            if(opnum >0) newWord[opnum2]=digits[(int)(num & numMask)];
        }catch (Exception exception){
            System.out.println("Error!" + exception.toString());
        }

        return String.valueOf(newWord).toUpperCase();
    }
}
