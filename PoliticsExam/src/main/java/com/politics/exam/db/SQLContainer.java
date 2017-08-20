package com.politics.exam.db;

import com.politics.exam.util.Logger;

/**
 * Created by malijie on 2017/6/1.
 */

public class SQLContainer {
    private static final String QUESTION_INFO = "QUESTION_INFO_BEAN";
    private static final String QUESTION_OPTION = "QUESTION_OPTION_BEAN";

    public static String queryChapterQuestions(String subjectName){
        return "SELECT * FROM " + QUESTION_INFO + " WHERE SUBJECT_NAME='" + subjectName + "' AND YEAR=0 ORDER BY CHAPTER_ID, S_NUM";
    }

    public static String queryQuestionCountBySubjectName(String subjectName){
        return "SELECT COUNT(*) AS TOTAL_SUM FROM " + QUESTION_INFO + " WHERE SUBJECT_NAME='" + subjectName + "' AND YEAR=0 ORDER BY CHAPTER_ID, S_NUM";
    }

    public static String queryItemQuestions(int chapterId){
        return "SELECT * FROM " + QUESTION_INFO + " WHERE CHAPTER_ID='" + chapterId + "' AND YEAR=0 ORDER BY CHAPTER_ID, S_NUM";

    }

    public static String queryOptionsByQuestionId(int questionId){
        return "SELECT * FROM " + QUESTION_OPTION + " WHERE QUESTION_ID='" + questionId + "' ORDER BY QUESTION_ID, KEY";
    }

    public static String updateHistoryAnswer(int id, String options){
        return "UPDATE " + QUESTION_INFO + " SET HISTORY_ANSWERS='" + options + "' WHERE QUESTION_ID='" + id +"'";
    }

    public static String queryQuestionById(int questionId){
        return "SELECT * FROM " + QUESTION_INFO + " WHERE QUESTION_ID='" + questionId + "'";
    }

    public static String clearHistoryAnswersByChapterId(int id){
        return "UPDATE " + QUESTION_INFO + " SET HISTORY_ANSWERS=NULL WHERE CHAPTER_ID=" + id;
    }

    public static String searchQuesitonInfo(String keyword){
        return "SELECT * FROM " + QUESTION_INFO + " WHERE TITLE LIKE '%" + keyword + "%'";
    }

}
