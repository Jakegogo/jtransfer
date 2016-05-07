package transfer.test;

import com.jake.common.util.JsonUtils;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import transfer.ByteArray;
import transfer.Transfer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.Deflater;

/**
 * Created by Administrator on 2015/2/26.
 */
public class TestTransfer_copy {

    public static void main(String[] args) {

//        Config.registerClass(Entity.class, 1);
    	
//    	TransferConfig.preCompileDeserializer(Entity.class);
    	
        Entity entity = new Entity();
        entity.setId(System.currentTimeMillis());
        entity.setUid(-101);
        entity.setFval(2.34f);
        entity.setNum(5);
        entity.setStatus(AcountStatus.OPEN);
        entity.setDate(new Date());
        entity.setStr("jake");
        entity.setBool(true);
        entity.getFriends().add(1l);
        entity.getFriends().add(2l);
        entity.getFriends().add(3l);
        entity.setA(null);
        entity.setIArr(new int[]{4,5,6});

        List<Integer> obj = new ArrayList<Integer>();
        obj.add(123);
        obj.add(456);
        entity.setObj(obj);

        AcountStatus[] asa = new AcountStatus[]{AcountStatus.CLOSE, AcountStatus.OPEN};
        entity.setStatusHis(asa);


        byte[] bytes = Transfer.encode(entity, Entity.class).toBytes();
        System.out.println(bytes);
        System.out.println("length:" + bytes.length);

        Entity_copy entity1 = Transfer.decode(bytes, Entity_copy.class);
        System.out.println(entity1);
        System.out.println(entity1.getId());
        System.out.println(entity1.getUid());
        System.out.println(entity1.getDate());
        System.out.println(entity1.getStr());
        System.out.println(entity1.getBool());
        System.out.println(entity1.getFval());
    }
    

}
