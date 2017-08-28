package com.politics.exam.db.operator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.politics.exam.db.DBHelper;
import com.politics.exam.db.SQLContainer;
import com.politics.exam.entity.ExamInfo;
import com.politics.exam.entity.OptionInfo;
import com.politics.exam.entity.QuestionInfo;
import com.politics.exam.util.Logger;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malijie on 2017/6/3.
 */

public class BaseOperator{

    static SQLiteDatabase mDB = null;

    static{
        mDB = new DBHelper().getWritableDatabase();
    }

    public List<QuestionInfo> getQuestionsByChapterId(int chapterId) {
        List<QuestionInfo> questionInfos = new ArrayList<>();
        if (mDB != null) {
            Cursor cursor = mDB.rawQuery(SQLContainer.queryItemQuestions(chapterId), null);
            while (cursor.moveToNext()) {
                QuestionInfo questionInfo = new QuestionInfo();
                questionInfo.setQuestionId(cursor.getInt(cursor.getColumnIndex("QUESTION_ID")));
                questionInfo.setChapterParentId(cursor.getInt(cursor.getColumnIndex("CHAPTER_PARENT_ID")));
                questionInfo.setChapterId(cursor.getInt(cursor.getColumnIndex("CHAPTER_ID")));
                questionInfo.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
                questionInfo.setTitleImage(cursor.getString(cursor.getColumnIndex("TITLE_IMG")));
                questionInfo.setsNum(cursor.getInt(cursor.getColumnIndex("S_NUM")));
                questionInfo.setUnit(cursor.getString(cursor.getColumnIndex("UNIT")));
                questionInfo.setNumber(cursor.getString(cursor.getColumnIndex("NUMBER")));
                questionInfo.setYear(cursor.getString(cursor.getColumnIndex("YEAR")));
                questionInfo.setNumberNumber(cursor.getInt(cursor.getColumnIndex("NUMBER_NUMBER")));
                questionInfo.setNumberType(cursor.getString(cursor.getColumnIndex("NUMBER_TYPE")));
                questionInfo.setRestore(cursor.getString(cursor.getColumnIndex("RESTORE")));
                questionInfo.setExplain(cursor.getString(cursor.getColumnIndex("EXPLAIN")));
                questionInfo.setMediaUrl(cursor.getString(cursor.getColumnIndex("MEDIA_URL")));
                questionInfo.setMediaImage(cursor.getString(cursor.getColumnIndex("MEDIA_IMG")));
                questionInfo.setMediaId(cursor.getString(cursor.getColumnIndex("MEDIA_ID")));
                questionInfo.setType(cursor.getString(cursor.getColumnIndex("TYPE")));
                questionInfo.setAnswer(cursor.getString(cursor.getColumnIndex("ANSWER")));
                questionInfo.setSubjectName(cursor.getString(cursor.getColumnIndex("SUBJECT_NAME")));
                questionInfos.add(questionInfo);
            }
        }

        return questionInfos;
    }

    public List<OptionInfo> getOptionsByQuestionId(int questionId){
        List<OptionInfo> options = new ArrayList<>();
        Cursor cursor = mDB.rawQuery(SQLContainer.queryOptionsByQuestionId(questionId),null);
        while (cursor.moveToNext()) {
            OptionInfo optionInfo = new OptionInfo();
            optionInfo.setQuestionId(cursor.getInt(cursor.getColumnIndex("QUESTION_ID")));
            optionInfo.setKey(cursor.getString(cursor.getColumnIndex("KEY")));
            optionInfo.setValue(cursor.getString(cursor.getColumnIndex("VALUE")));
            options.add(optionInfo);
        }
        return options;
    }

    public void saveHistoryAnswer(int id,String options){
        mDB.execSQL(SQLContainer.updateHistoryAnswer(id,options));
    }

    public boolean isCompleteQuestion(int id){
        Cursor cursor = mDB.rawQuery(SQLContainer.queryQuestionById(id),null);
        if(cursor.moveToNext()){
            if(!TextUtils.isEmpty(cursor.getString(cursor.getColumnIndex("HISTORY_ANSWERS")))){
                return  true;
            }
        }
        return false;
    }

    public String getHistoryAnswers(int id){
        Cursor cursor = mDB.rawQuery(SQLContainer.queryQuestionById(id),null);
        if(cursor.moveToNext()){
            return cursor.getString(cursor.getColumnIndex("HISTORY_ANSWERS"));
        }

        return "";
    }

    public void clearHistoryAnswersByChapterId(int chapterID){
        mDB.execSQL(SQLContainer.clearHistoryAnswersByChapterId(chapterID));
    }

    public List<QuestionInfo> getSearchResult(String keyword){
            Cursor cursor = mDB.rawQuery(SQLContainer.searchQuesitonInfo(keyword),null);
            List<QuestionInfo> mQuestionInfos = new ArrayList<>();

        while (cursor.moveToNext()) {
            QuestionInfo questionInfo = new QuestionInfo();
            questionInfo.setQuestionId(cursor.getInt(cursor.getColumnIndex("QUESTION_ID")));
            questionInfo.setChapterParentId(cursor.getInt(cursor.getColumnIndex("CHAPTER_PARENT_ID")));
            questionInfo.setChapterId(cursor.getInt(cursor.getColumnIndex("CHAPTER_ID")));
            questionInfo.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
            questionInfo.setTitleImage(cursor.getString(cursor.getColumnIndex("TITLE_IMG")));
            questionInfo.setsNum(cursor.getInt(cursor.getColumnIndex("S_NUM")));
            questionInfo.setUnit(cursor.getString(cursor.getColumnIndex("UNIT")));
            questionInfo.setNumber(cursor.getString(cursor.getColumnIndex("NUMBER")));
            questionInfo.setYear(cursor.getString(cursor.getColumnIndex("YEAR")));
            questionInfo.setNumberNumber(cursor.getInt(cursor.getColumnIndex("NUMBER_NUMBER")));
            questionInfo.setNumberType(cursor.getString(cursor.getColumnIndex("NUMBER_TYPE")));
            questionInfo.setRestore(cursor.getString(cursor.getColumnIndex("RESTORE")));
            questionInfo.setExplain(cursor.getString(cursor.getColumnIndex("EXPLAIN")));
            questionInfo.setMediaUrl(cursor.getString(cursor.getColumnIndex("MEDIA_URL")));
            questionInfo.setMediaImage(cursor.getString(cursor.getColumnIndex("MEDIA_IMG")));
            questionInfo.setMediaId(cursor.getString(cursor.getColumnIndex("MEDIA_ID")));
            questionInfo.setType(cursor.getString(cursor.getColumnIndex("TYPE")));
            questionInfo.setAnswer(cursor.getString(cursor.getColumnIndex("ANSWER")));
            questionInfo.setSubjectName(cursor.getString(cursor.getColumnIndex("SUBJECT_NAME")));
            mQuestionInfos.add(questionInfo);
        }
        return mQuestionInfos;
    }

    public String getChapterTitleById(int chapterId){
        Cursor cursor = mDB.rawQuery(SQLContainer.getChapterTitleById(chapterId),null);
        String title = "";
        while (cursor.moveToNext()) {
            title = cursor.getString(cursor.getColumnIndex("TITLE"));
        }
        return title;
    }

    public List<ExamInfo> getExamInfos(){
        List<ExamInfo> examInfos = new ArrayList<>();
        if (mDB != null) {
            Cursor cursor = mDB.rawQuery(SQLContainer.getExamInfos(), null);
            while (cursor.moveToNext()) {
                ExamInfo examInfo = new ExamInfo();
                examInfo.setId(cursor.getInt(cursor.getColumnIndex("id")));
                examInfo.setContent(cursor.getString(cursor.getColumnIndex("content")));
                examInfo.setType(cursor.getString(cursor.getColumnIndex("type")));
                examInfo.setExplain(cursor.getString(cursor.getColumnIndex("explain")));
                examInfo.setKey(cursor.getString(cursor.getColumnIndex("key")));
                examInfo.setPaperId(cursor.getInt(cursor.getColumnIndex("paper_id")));
                examInfo.setOption(cursor.getString(cursor.getColumnIndex("option")));
                examInfos.add(examInfo);
            }
        }

        return examInfos;
    }

    public String getExamContentByYear(String year){
Logger.mlj("sql=" + SQLContainer.getExamInfos());
        Cursor cursor = mDB.rawQuery(SQLContainer.getExamInfos(), null);
        cursor.moveToNext();
Logger.mlj("cursor=" + cursor);
        return cursor.getString(cursor.getColumnIndex("content"));

    }

}
