package simHashAlgo;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;

public class SimHash {
    //文本
    private String tokens;
    //维度
    private int hashbits;
    //签名
    private BigInteger intSimHash;

    public SimHash(String tokens, int hashbits) {
        this.tokens = tokens;
        this.hashbits = hashbits;
//        this.intSimHash = this.simHash();
    }

    private BigInteger simHash() throws IOException {
        int[] v = new int[this.hashbits];
        //将文本去掉格式
        StringReader sr = new StringReader(tokens);
        //使用中文分词器
        IKSegmenter ikSegmenter = new IKSegmenter(sr,true);
        Lexeme lexeme = null;
        while((lexeme = ikSegmenter.next()) != null){

            String s = new String();
        }
        return new BigInteger(String.valueOf(0));
    }


}
