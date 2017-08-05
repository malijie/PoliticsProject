package com.politics.exam.db.operator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.politics.exam.db.DBHelper;
import com.politics.exam.db.SQLContainer;
import com.politics.exam.entity.OptionInfo;
import com.politics.exam.entity.QuestionInfo;

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

}
