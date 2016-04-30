package transfer.test;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import transfer.anno.Transferable;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

//@MappedSuperclass
@Transferable(id = 1)
public class Entity_copy implements Serializable {

	public static final String NUM_INDEX = "num_idx";

	@Protobuf(fieldType = FieldType.INT64, order = 1, required = true)
	public Long id;

	@Protobuf(fieldType = FieldType.INT32, order = 2, required = true)
	private int uid;

	@Protobuf(fieldType = FieldType.INT32, order = 3, required = true)
	public int num;

	@Protobuf(fieldType = FieldType.STRING, order = 4, required = true)
	private String name;

	@Protobuf(fieldType = FieldType.BYTES, order = 5, required = true)
	public byte[] a = new byte[100];

	@Protobuf(fieldType = FieldType.FLOAT, order = 6, required = true)
	private float fval = 1.23f;

	private Date date;

	@Protobuf(fieldType = FieldType.STRING, order = 7, required = true)
	private String str;

	@Protobuf(fieldType = FieldType.BOOL, order = 8, required = true)
	private Boolean bool;


	public Entity_copy() {
//		doAfterLoad();
//		doAfterLoad();
	}


	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

//	@ChangeIndexes({ "num_idx" })
//	@ChangeFields({"num"})
	public int addNum(int num) {
		this.num += num;
		return this.num;
	}


	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getA() {
		return a;
	}

	public void setA(byte[] a) {
		this.a = a;
	}


	@Override
	public int hashCode() {
		return 305668771 + 1793910479 * this.getId().hashCode();
	}



	@Override
	public String toString() {
		return "测试";
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(!(obj instanceof Entity_copy)) {
			return false;
		}
		Entity_copy target = (Entity_copy) obj;
		return this.id.equals(target.id);
	}

	public static void main(String[] args) {

		for(Method method : Entity_copy.class.getDeclaredMethods() ) {
			System.out.println(method);
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void combine(Object object, boolean addMap) {
		
	}

	public float getFval() {
		return fval;
	}

	public void setFval(float fval) {
		this.fval = fval;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public Boolean getBool() {
		return bool;
	}

	public void setBool(Boolean bool) {
		this.bool = bool;
	}

}