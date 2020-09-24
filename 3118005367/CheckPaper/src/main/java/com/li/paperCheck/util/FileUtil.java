package com.li.paperCheck.util;

import com.li.paperCheck.exception.MyException;

import java.io.*;

public class FileUtil {
    private FileUtil(){
    }

    /**
     * 将目标文件文本转换为字符串
     * @param fileAddress
     * @return
     * @throws IOException
     */
    public static String fileContextTOString(String fileAddress){
        StringBuilder sb = new StringBuilder();
        try{
        BufferedReader in1 = new BufferedReader(new InputStreamReader(new FileInputStream(fileAddress), "UTF8"));
        String tool;
        while((tool = in1.readLine())!=null){
            sb.append(tool);
        }
        in1.close();
        }catch (Exception e){
            throw new MyException("将目标文件文本转换为字符串时发生错误。");
        }
        return new String(sb);
    }

    public static void outputAnswerFile(double answer,String answerFileAddress) {
        File file = new File(answerFileAddress);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write("文本相似度:");
            fw.write(new String(String.valueOf(answer)));
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new MyException("输出答案文件时发生错误！");
        }
    }
}
