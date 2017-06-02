package com.politics.exam.db.operator;

import android.database.sqlite.SQLiteDatabase;

import com.politics.exam.db.DBHelper;
import com.politics.exam.entity.QuestionInfo;

import java.util.List;

/**
 * Created by malijie on 2017/6/2.
 */

public abstract class BaseDBOperator {

    public static final String CHAPTER_MAYUAN = "10";
    public static final String CHAPTER_MAOZHONGTE = "20";
    public static final String CHAPTER_SHIGANG = "30";
    public static final String CHAPTER_SIXIUYUFAJI = "40";
    public static final String CHAPTER_SHIZHENG = "41";

    public static SQLiteDatabase mDB = null;

    static {
        mDB = new DBHelper().getWritableDatabase();
    }

    public abstract  List<QuestionInfo> getChapterQuestions();


}
