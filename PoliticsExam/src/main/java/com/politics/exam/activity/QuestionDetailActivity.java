package com.politics.exam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.politics.exam.R;
import com.politics.exam.db.operator.ChapterMYDBOperator;
import com.politics.exam.db.operator.ChapterMZTDBOperator;
import com.politics.exam.db.operator.ChapterSGDBOperator;
import com.politics.exam.db.operator.ChapterSXYFJDBOperator;
import com.politics.exam.db.operator.ChapterSZDBOperator;
import com.politics.exam.db.operator.IDBOperator;
import com.politics.exam.entity.OptionInfo;
import com.politics.exam.entity.QuestionInfo;
import com.politics.exam.util.Logger;
import com.politics.exam.util.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by malijie on 2017/5/27.
 */

public class QuestionDetailActivity extends BaseActivity{
    private ViewPager mViewPager = null;
    private List<View> mViews = null;
    private TextView mTextTitle = null;
    private TextView mTextChapter = null;
    private TextView mTextChoiceA = null;
    private TextView mTextChoiceB = null;
    private TextView mTextChoiceC = null;
    private TextView mTextChoiceD = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_detail);
        initData();
        initViews();
    }

    public IDBOperator mOperator = null;
    private List<QuestionInfo> mQuestionInfos;

    @Override
    public void initData() {
        Intent i = getIntent();
        int groupPosition = i.getIntExtra("groupPosition",0);
        int childPosition = i.getIntExtra("childPosition",0);
        mOperator = getOperator(groupPosition);

        mQuestionInfos = mOperator.getQuestionsByChapterId(getChapterId(groupPosition,childPosition));

    }

    @Override
    public void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.id_question_detail_view_pager);
        mTextChapter = (TextView) findViewById(R.id.id_title_bar_text_title);

        mTextChoiceA = (TextView) findViewById(R.id.id_question_detail_text_choiceA);
        mTextChoiceB = (TextView) findViewById(R.id.id_question_detail_text_choiceB);
        mTextChoiceC = (TextView) findViewById(R.id.id_question_detail_text_choiceC);
        mTextChoiceD = (TextView) findViewById(R.id.id_question_detail_text_choiceD);

        mViews = new ArrayList<>();
        Logger.mlj("size=" + mQuestionInfos.size());
        for(int i=0;i<mQuestionInfos.size();i++){
            View v = Utils.getView(R.layout.question_detail_item);
            mViews.add(v);

        }

        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position == 0){
                    updateContent(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                updateContent(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private List<OptionInfo> mOptions = null;

    private void updateContent(int position){
        mTextTitle = (TextView) mViews.get(position).findViewById(R.id.id_question_detail_text_title);
        mTextTitle.setText(mQuestionInfos.get(position).getTitle().trim());
        mTextTitle.setText(getContentStyle(position + 1,mQuestionInfos.get(position).getNumber(),mQuestionInfos.get(position).getTitle()));

        mTextChapter.setText(mQuestionInfos.get(position).getSubjectName());
        mOptions = mOperator.getOptionsByQuestionId(mQuestionInfos.get(position).getQuestionId());
Logger.mlj("mOptions==" + mOptions);

        mTextChoiceA.setText(mOptions.get(0).getKey());
        mTextChoiceB.setText(mOptions.get(1).getKey());
        mTextChoiceC.setText(mOptions.get(2).getKey());
        mTextChoiceD.setText(mOptions.get(3).getKey());

    }


    PagerAdapter mAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViews.get(position));
            return mViews.get(position);
        }
    };


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
