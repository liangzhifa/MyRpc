import com.zhifa.rpc.codec.Decoder;
import com.zhifa.rpc.codec.Encoder;
import com.zhifa.rpc.codec.impl.JsonDecoder;
import com.zhifa.rpc.codec.impl.JsonEncoder;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-12 10:53
 */

public class EncodeTest {

    private String name;


    public static void main(String[] args) {

        Encoder encoder = new JsonEncoder();
        EncodeTest encodeTest = new EncodeTest();
        byte[] encode = encoder.encode(encodeTest);
        Decoder decoder = new JsonDecoder();
        EncodeTest decode = decoder.decode(encode, EncodeTest.class);
        System.out.println(decode==encodeTest);

    }
}
