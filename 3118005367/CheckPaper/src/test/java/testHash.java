import com.li.paperCheck.service.CosSimilarityService;
import com.li.paperCheck.service.impl.CosSimilarityServiceImpl;
import com.li.paperCheck.util.FileUtil;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import org.junit.Test;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;

public class testHash {
    public static void main(String[] args) {

    }

    @Test
    public void testResult(){
        CosSimilarityService cosSimilarityService = new CosSimilarityServiceImpl();
        double similarity = cosSimilarityService.getSimilarity(FileUtil.fileContextTOString("E:\\test\\orig.txt"), FileUtil.fileContextTOString("E:\\test\\orig_0.8_dis_10.txt"));
        System.out.println(similarity);
    }

    @Test
    public void testConvert() throws IOException {
        String s = FileUtil.fileContextTOString("E:/exam.sql");
        System.out.println(s);
    }

    @Test
    public void testHaxi(){
        System.out.println(hash("Hello"));
        System.out.println("Hello".hashCode());
    }

    @Test
    public void testIK() throws IOException {
        StringReader sr = new StringReader("<style>这只皮靴号码大了。那只号码合适.!!!swdeifk</style>");
        IKSegmenter ikSegmenter = new IKSegmenter(sr,true);
        Lexeme lexeme = null;
        while((lexeme = ikSegmenter.next())!=null){
            System.out.println(!formatWord(lexeme).equals(""));
        }
    }

    @Test
    public void testCos() throws IOException {
        CosSimilarityService cosSimilarityService = new CosSimilarityServiceImpl();
        double similarity = cosSimilarityService.getSimilarity("今天天气真好啊", "今天天气真好啊");
        System.out.println(similarity);
    }

    public String formatWord(Lexeme lexeme){
        if(lexeme == null){
            return "";
        }
        String word = lexeme.getLexemeText();
        String newWord = "";
        for (int i = 0;i < word.length();i++){
            char c = word.charAt(i);
            if(String.valueOf(c).matches("[\\u4e00-\\u9fa5]")){
                newWord += c;
            }
        }
        return newWord;
    }

    @Test
    public void testFormatWord(){

    }



    public BigInteger hash(String source) {
        if (source == null || source.length() == 0) {
            return new BigInteger("0");
        } else {
            char[] sourceArray = source.toCharArray();
            BigInteger x = BigInteger.valueOf(((long) sourceArray[0]) << 7);
            BigInteger m = new BigInteger("1000003");
            BigInteger mask = new BigInteger("2").pow(64).subtract(
                    new BigInteger("1"));
            for (char item : sourceArray) {
                BigInteger temp = BigInteger.valueOf((long) item);
                x = x.multiply(m).xor(temp).and(mask);
            }
            x = x.xor(new BigInteger(String.valueOf(source.length())));
            if (x.equals(new BigInteger("-1"))) {
                x = new BigInteger("-2");
            }
            return x;
        }
    }
}
