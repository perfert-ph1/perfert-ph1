package com.li.paperCheck;

import com.li.paperCheck.service.CosSimilarityService;
import com.li.paperCheck.service.impl.CosSimilarityServiceImpl;
import com.li.paperCheck.util.FileUtil;

public class PaperCheckMain {
    public static void main(String[] args) {
        String originalString = FileUtil.fileContextTOString(args[0]);
        String copyString = FileUtil.fileContextTOString(args[1]);
        String answerFileAddress = args[2];
        CosSimilarityService cosSimilarityService = new CosSimilarityServiceImpl();
        double similarity = cosSimilarityService.getSimilarity(originalString, copyString);
        FileUtil.outputAnswerFile(similarity,answerFileAddress);
    }
}
