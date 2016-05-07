package transfer.test;

import transfer.ByteArray;
import transfer.Transfer;

/**
 * Created by Administrator on 2015/2/26.
 */
public class TestNull {

    public static void main(String[] args) {


        System.out.println(null instanceof Class<?>);

        byte[] bytes = Transfer.encode(null).toBytes();

        System.out.println(bytes.length);
        
        int[] a1 = new int[0];
        
        Integer[] a2 =  new Integer[0];
        
        System.out.println(int.class);
        
    }

}
