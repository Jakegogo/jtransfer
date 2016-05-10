package transfer.test.socket.codec.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import transfer.ByteArray;
import transfer.Transfer;
import transfer.exceptions.DeserializeException;
import transfer.test.request.Request;
import transfer.test.request.RequestHeader;

/**
 * 可转发的Request
 */
class ReTransferableRequest extends Request {
    private final ByteArray inputable;

    private volatile RequestHeader header;
    
    private volatile Object body;

    private int len;

    public ReTransferableRequest(final InputStream in) {
        
        byte[] lenBytes = new byte[4];
        try {
            in.read(lenBytes);
        } catch (IOException e) {
            throw new DeserializeException(e);
        }
        this.len = CodecUtil.bytesToInt(lenBytes, 0);
        
        byte[] bytes = new byte[this.len];
        try {
            in.read(bytes);
            this.inputable = new ByteArray(bytes);
        } catch (IOException e) {
            throw new DeserializeException(e);
        }
        
    }

    @Override
    public RequestHeader getHeader() {
    	if (header != null) {
    		return header;
    	}
        return header = Transfer.decode(inputable, RequestHeaderDTO.class).getHeader();
    }

    @Override
    public Object getBody() {
    	if (body != null) {
    		return body;
    	}
        return body = Transfer.decode(inputable);
    }

    /**
     * 传输到输出流
     * @param out OutputStream
     */
    public void transferTo(OutputStream out) {
    	this.inputable.flushToOutputable(CodecUtil.wrapOutputable(out));
    }
    

    public int getLen() {
        return len;
    }
}
