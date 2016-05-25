package transfer.test.socket.codec.bio;

import transfer.ByteArray;
import transfer.Inputable;
import transfer.Outputable;
import transfer.exceptions.DeserializeException;
import transfer.exceptions.SerializeException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 编解码工具类
 * Created by Jake on 5/8 0008.
 */
public class CodecUtil {

    /**
     * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序，和和intToBytes（）配套使用
     *
     * @param src
     *            byte数组
     * @param offset
     *            从数组的第offset位开始
     * @return int数值
     */
    public static int bytesToInt(byte[] src, int offset) {
        int value;
        value = (int) ((src[offset] & 0xFF)
                | ((src[offset+1] & 0xFF)<<8)
                | ((src[offset+2] & 0xFF)<<16)
                | ((src[offset+3] & 0xFF)<<24));
        return value;
    }

    /**
     * 包装输出流
     * @param out 输出流
     * @return
     */
    public static Outputable wrapOutputable(final OutputStream out) {
        return new Outputable() {// ? 性能可能受到捕获异常的影响
            @Override
            public void putByte(byte byte1) {
                try {
                    out.write(new byte[]{byte1});
                } catch (IOException e) {
                    throw new SerializeException(e);
                }
            }

            @Override
            public void putBytes(byte[] bytes) {
                try {
                    out.write(bytes);
                } catch (IOException e) {
                    throw new SerializeException(e);
                }
            }

            @Override
            public void putBytes(byte[] bytes, int start, int length) {
                try {
                    out.write(bytes, start, length);
                } catch (IOException e) {
                    throw new SerializeException(e);
                }
            }
        };
    }

    /**
     * 包装输入流
     * @param in 输入流
     * @return
     */
    public static Inputable wrapInputable(final InputStream in) {
        return new Inputable() {
            @Override
            public byte getByte() {
                try {
                    return (byte) in.read();
                } catch (IOException e) {
                    throw new DeserializeException(e);
                }
            }

            @Override
            public void getBytes(byte[] bytes) {
                try {
                    in.read(bytes);
                } catch (IOException e) {
                    throw new DeserializeException(e);
                }
            }

            @Override
            public ByteArray getByteArray(int length) {
                byte[] bytes = new byte[length];
                try {
                    in.read(bytes);
                    return new ByteArray(bytes);
                } catch (IOException e) {
                    throw new DeserializeException(e);
                }
            }
        };
    }
    
    
    /**
     * 读取int
     * @param in 输入流
     * @return
     */
    public static int readInt(InputStream in) {
    	byte[] lenBytes = new byte[4];
        try {
            in.read(lenBytes);
        } catch (IOException e) {
            throw new DeserializeException(e);
        }
        return bytesToInt(lenBytes, 0);
    }

    
    /**
     * 输出int
     * @param out 输出流
     * @param val 值
     */
    public static void writeInt(OutputStream out, int val) {
		try {
			out.write((val >>> 0) & 0xFF);
			out.write((val >>> 8) & 0xFF);
			out.write((val >>> 16) & 0xFF);
			out.write((val >>> 24) & 0xFF);
		} catch (IOException e) {
			throw new SerializeException(e);
		}
    }
    

}
