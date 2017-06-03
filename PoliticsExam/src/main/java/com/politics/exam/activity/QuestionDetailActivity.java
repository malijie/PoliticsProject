package com.politics.exam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.politics.exam.R;
import com.politics.exam.db.operator.ChapterMYDBOperator;
import com.politics.exam.db.operator.ChapterMZTDBOperator;
import com.politics.exam.db.operator.ChapterSGDBOperator;
import com.politics.exam.db.operator.ChapterSXYFJDBOperator;
import com.politics.exam.db.operator.ChapterSZDBOperator;
import com.politics.exam.db.operator.IDBOperator;
import com.politics.exam.entity.QuestionInfo;
import com.politics.exam.util.Logger;

import java.util.List;

/**
 * Created by malijie on 2017/5/27.
 */

public class QuestionDetailActivity extends BaseActivity{
    private ViewPager mViewPager = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_detail);
        initViews();
        initData();
    }

    public IDBOperator mOperator = null;

    @Override
    public void initData() {
        Intent i = getIntent();
        int groupPosition = i.getIntExtra("groupPosition",0);
        int childPosition = i.getIntExtra("childPosition",0);
        mOperator = getOperator(groupPosition);

        List<QuestionInfo> questionInfos = mOperator.getQuestionsByChapterId(getChapterId(groupPosition,childPosition));
        Logger.mlj("size=" + questionInfos.size() + ",first item=" + questionInfos.get(0).getTitle() + ",last item=" + questionInfos.get(questionInfos.size()-1).getTitle());
    }

    @Override
    public void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.id_question_detail_view_pager);
    }

    private IDBOperator getOperator(int groupPosition){
        IDBOperator operator = null;
        switch (groupPosition){
            case 0:
                operator = new ChapterMYDBOperator();
                break;
            case 1:
                operator = new ChapterMZTDBOperator();
                break;
            case 2:
                operator = new ChapterSGDBOperator();
                break;
            case 3:
                operator = new ChapterSXYFJDBOperator();
                break;
            case 4:
                operator = new ChapterSZDBOperator();
                break;
            default:
                operator = new ChapterMYDBOperator();
        }
        return operator;
    }
    private int getChapterId(int groupPosition,int childPosition){
        int chapterId = 0;
        if(groupPosition == 0 && childPosition == 0){
            chapterId = 101;
        }else if(groupPosition == 0 && childPosition == 1){
            chapterId = 102;
        }else if(groupPosition == 0 && childPosition == 2){
            chapterId = 103;
        }else if(groupPosition == 0 && childPosition == 3){
            chapterId = 104;
        }else if(groupPosition == 0 && childPosition == 4){
            chapterId = 105;
        }else if(groupPosition == 0 && childPosition == 5){
            chapterId = 106;
        }else if(groupPosition == 0 && childPosition == 6){
            chapterId = 107;
        }else if(groupPosition == 0 && childPosition == 7){
            chapterId = 108;


        }else if(groupPosition == 1 && childPosition == 0){
            chapterId = 201;
        }else if(groupPosition == 1 && childPosition == 1){
            chapterId = 202;
        }else if(groupPosition == 1 && childPosition == 2){
            chapterId = 203;
        }else if(groupPosition == 1 && childPosition == 3){
            chapterId = 204;
        }else if(groupPosition == 1 && childPosition == 4){
            chapterId = 205;
        }else if(groupPosition == 1 && childPosition == 5){
            chapterId = 206;
        }else if(groupPosition == 1 && childPosition == 6){
            chapterId = 207;
        }else if(groupPosition == 1 && childPosition == 7){
            chapterId = 208;
        }else if(groupPosition == 1 && childPosition == 8){
            chapterId = 209;
        }else if(groupPosition == 1 && childPosition == 9){
            chapterId = 210;
        }else if(groupPosition == 1 && childPosition == 10){
            chapterId = 211;
        }else if(groupPosition == 1 && childPosition == 11){
            chapterId = 212;
        }


        else if(groupPosition == 2 && childPosition == 0){
            chapterId = 301;
        }else if(groupPosition == 2 && childPosition == 1){
            chapterId = 302;
        }else if(groupPosition == 2 && childPosition == 2){
            chapterId = 303;
        }else if(groupPosition == 2 && childPosition == 3){
            chapterId = 304;
        }else if(groupPosition == 2 && childPosition == 4){
            chapterId = 305;
        }else if(groupPosition == 2 && childPosition == 5){
            chapterId = 306;
        }else if(groupPosition == 2 && childPosition == 6){
            chapterId = 307;
        }else if(groupPosition == 2 && childPosition == 7){
            chapterId = 308;
        }else if(groupPosition == 2 && childPosition == 8){
            chapterId = 309;
        }else if(groupPosition == 2 && childPosition == 9){
            chapterId = 310;


        }else if(groupPosition == 3 && childPosition == 0){
            chapterId = 401;
        }else if(groupPosition == 3 && childPosition == 1){
            chapterId = 402;
        }else if(groupPosition == 3 && childPosition == 2){
            chapterId = 403;
        }else if(groupPosition == 3 && childPosition == 3){
            chapterId = 404;
        }else if(groupPosition == 3 && childPosition == 4){
            chapterId = 405;
        }else if(groupPosition == 3 && childPosition == 5){
            chapterId = 406;
        }else if(groupPosition == 3 && childPosition == 6){
            chapterId = 407;
        }else if(groupPosition == 3 && childPosition == 7){
            chapterId = 408;
        }else if(groupPosition == 3 && childPosition == 8){
            chapterId = 409;
        }


        else if(groupPosition == 4 && childPosition == 0){
            chapterId = 410;
        }else if(groupPosition == 4 && childPosition == 1){
            chapterId = 411;
        }else if(groupPosition == 4 && childPosition == 2){
            chapterId = 412;
        }else if(groupPosition == 4 && childPosition == 3){
            chapterId = 413;
        }else if(groupPosition == 4 && childPosition == 4){
            chapterId = 414;
        }else if(groupPosition == 4 && childPosition == 5){
            chapterId = 415;
        }else if(groupPosition == 4 && childPosition == 6){
            chapterId = 416;
        }else if(groupPosition == 4 && childPosition == 7){
            chapterId = 417;
        }else if(groupPosition == 4 && childPosition == 8){
            chapterId = 418;
        }else if(groupPosition == 4 && childPosition == 9){
            chapterId = 419;
        }else if(groupPosition == 4 && childPosition == 10){
            chapterId = 420;
        }
        return chapterId;
    }
}
