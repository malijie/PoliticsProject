package com.politics.exam.db.operator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.politics.exam.db.DBHelper;
import com.politics.exam.db.SQLContainer;
import com.politics.exam.entity.QuestionInfo;

import java.util.List;

/**
 * Created by malijie on 2017/6/3.
 */

public class BaseOperator{

    static SQLiteDatabase mDB = null;

    static{
        mDB = new DBHelper().getWritableDatabase();
    }

}
