package com.li.paperCheck;

import com.li.paperCheck.exception.MyException;
import com.li.paperCheck.service.CosSimilarityService;
import com.li.paperCheck.service.impl.CosSimilarityServiceImpl;
import com.li.paperCheck.util.FileUtil;

public class PaperCheckMain {
    public static void main(String[] args) {
        if(args[0] == null || args[1] == null || args[2] == null){
            throw new MyException("参数有误");
        }
        String originalString = FileUtil.fileContextTOString(args[0]);
        String copyString = FileUtil.fileContextTOString(args[1]);
        String answerFileAddress = args[2];
        process(originalString,copyString,answerFileAddress);
    }

    public static void process(String originalContextAddress,String copyContextAddress,String answerFileAddress){
        if(originalContextAddress == null || copyContextAddress == null || answerFileAddress == null){
            throw new MyException("参数有误");
        }
        String originalString = FileUtil.fileContextTOString(originalContextAddress);
        String copyString = FileUtil.fileContextTOString(copyContextAddress);
        if(copyString == null || copyString.equals("")){
            System.out.println("相似度为0%");
            return;
        }
        CosSimilarityService cosSimilarityService = new CosSimilarityServiceImpl();

        double similarity = cosSimilarityService.getSimilarity(originalString, copyString);
        FileUtil.outputAnswerFile(similarity,answerFileAddress);
        System.out.println("相似度为"+similarity+"%");


    }
}
