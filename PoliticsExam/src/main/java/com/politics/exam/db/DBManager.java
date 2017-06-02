package com.politics.exam.db;

import com.politics.exam.db.operator.BaseDBOperator;
import com.politics.exam.db.operator.ChapteMZTDBOperator;
import com.politics.exam.db.operator.ChapterMYDBOperator;
import com.politics.exam.db.operator.ChapterSGDBOperator;
import com.politics.exam.db.operator.ChapterSXYFJDBOperator;
import com.politics.exam.db.operator.ChapterSZDBOperator;
import com.politics.exam.entity.QuestionInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.politics.exam.PoliticsApplication.sContext;

/**
 * Created by malijie on 2017/6/1.
 */

public class DBManager {
    public static void copyDB2Phone(){
        String dbFileName = DBConstants.DB_PATH + DBConstants.DB_NAME;
        File dbFile = new File(dbFileName);
        InputStream is = null;
        FileOutputStream os = null;
        if(!dbFile.exists()){
            dbFile.getParentFile().mkdirs();
            try{
                os = new FileOutputStream(dbFileName);
                is = sContext.getResources().getAssets().open(DBConstants.DB_NAME);
                byte[] buffer = new byte[1024];
                int count = 0;
                while((count=is.read(buffer))>0){
                    os.write(buffer, 0, count);
                    os.flush();
                }
            }catch(FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    is.close();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static BaseDBOperator mDBOperator = null;
    /**
     * 获取马原章节所有的题
     * @return
     */
    public static List<QuestionInfo> getMYChapterQuestions(){
        mDBOperator = new ChapterMYDBOperator();
        return mDBOperator.getChapterQuestions();
    }

    /**
     * 获取毛中特章节所有的题
     * @return
     */
    public static List<QuestionInfo> getMZTChapterQuestions(){
        mDBOperator = new ChapteMZTDBOperator();
        return mDBOperator.getChapterQuestions();
    }

    /**
     * 获取史纲章节所有的题
     * @return
     */
    public static List<QuestionInfo> getSGChapterQuestions(){
        mDBOperator = new ChapterSGDBOperator();
        return mDBOperator.getChapterQuestions();
    }

    /**
     * 获取思修与法基章节所有的题
     * @return
     */
    public static List<QuestionInfo> getSXYFJChapterQuestions(){
        mDBOperator = new ChapterSXYFJDBOperator();
        return mDBOperator.getChapterQuestions();
    }

    /**
     * 获取时政章节所有的题
     * @return
     */
    public static List<QuestionInfo> getSZChapterQuestions(){
        mDBOperator = new ChapterSZDBOperator();
        return mDBOperator.getChapterQuestions();
    }


}
