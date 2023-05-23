package com.example.learningdemo.testDemo;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author sxc
 * @Description 获取字符串中（）的内容
 * @Date 2023/04/18
 */
public class GetTextInBracket {
    public static void main(String[] args) throws Exception {
        String input = "这是一个测试（测试字符串）（测试字符串2）（测试字符串3）";
        GetTextInBracket.extract(input);
    }

    public static void extract(String input) {
        Pattern pattern = Pattern.compile("\\（([^\\（\\）]+)\\）");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }
}
