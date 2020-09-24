package com.li.paperCheck.service.impl;

import com.li.paperCheck.exception.MyException;
import com.li.paperCheck.service.CosSimilarityService;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;


import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CosSimilarityServiceImpl implements CosSimilarityService {
    @Override
    public double getSimilarity(String context, String copyContext) {
        //初始文本
        Map<String,Integer> originalWeightMap = new HashMap<String,Integer>();
        //copy文本
        Map<String,Integer> copyWeightMap = new HashMap<String,Integer>();

        int weight = 0;
        String word = "";
        StringReader sr = new StringReader(context);
        IKSegmenter ikSegmenter = new IKSegmenter(sr,true);
        Lexeme lexeme = null;
        try{
        while((lexeme = ikSegmenter.next())!=null){
            word = formatWord(lexeme);
            if(word == null || word.equals("")){
                continue;
            }
            if(originalWeightMap.get(word) == null){
                originalWeightMap.put(word,1);
            }else {
                weight = originalWeightMap.get(word);
                originalWeightMap.put(word,++weight);
            }
        }
        }catch (Exception e){
        throw new MyException("获取相似度时出错！");
        }

        StringReader sr2 = new StringReader(copyContext);
        IKSegmenter ikSegmenter2 = new IKSegmenter(sr2,true);
        try{
        while((lexeme = ikSegmenter2.next())!=null){
            word = formatWord(lexeme);
            if(word == null || word.equals("")){
                continue;
            }
            if(copyWeightMap.get(word) == null){
                copyWeightMap.put(word,1);
            }else {
                weight = copyWeightMap.get(word);
                copyWeightMap.put(word,++weight);
            }
        }

        }catch (Exception e){
            throw new MyException("获取相似度时出错！");
        }

        //计算余弦公式分子部分
        double up=0,down1=0,down2=0,result = 0;
        double x=0,y=0;
        for(String key:originalWeightMap.keySet()){
            if(copyWeightMap.get(key) == null){
                continue;
            }
            x = originalWeightMap.get(key);
            y = copyWeightMap.get(key);
            up += x*y;
        }

        for(String key : originalWeightMap.keySet()){
            x = originalWeightMap.get(key).doubleValue();
            down1 += x*x;
        }
        down1 = Math.sqrt(down1);

        for(String key : copyWeightMap.keySet()){
            y = copyWeightMap.get(key).doubleValue();
            down2 += y*y;
        }
        down2 = Math.sqrt(down2);


        result = up/(down1*down2);
        return result;
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
}
