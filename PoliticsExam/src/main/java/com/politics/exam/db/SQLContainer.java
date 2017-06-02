package com.politics.exam.db;

/**
 * Created by malijie on 2017/6/1.
 */

public class SQLContainer {
    private static final String QUESTION_INFO = "QUESTION_INFO_BEAN";
    private static final String QUESTION_OPTION = "QUESTION_OPTION_BEAN";

    public static String queryChapterQuestionByCid(String cid){
        return "SELECT * FROM " + QUESTION_INFO + " WHERE CHAPTER_ID LIKE'" + cid + "%' ORDER BY CHAPTER_ID, S_NUM";
    }
}
