package com.politics.exam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.politics.exam.util.IntentManager;
import com.politics.exam.util.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by malijie on 2017/5/27.
 */

public class QuestionDetailActivity extends BaseActivity{
    private static final String OPTION_A = "A";
    private static final String OPTION_B = "B";
    private static final String OPTION_C = "C";
    private static final String OPTION_D = "D";

    private static final int SINGLE_CHOICE = 1;
    private static final int MULTI_CHOICE = 2;

    private ViewPager mViewPager = null;
    private List<View> mViews = null;
    private TextView mTextQuestionTitle = null;
    private TextView mTextChapterTitle = null;
    private TextView mTextChapter = null;
    private TextView mTextChoiceA = null;
    private TextView mTextChoiceB = null;
    private TextView mTextChoiceC = null;
    private TextView mTextChoiceD = null;
    private ImageView mImageChoiceA = null;
    private ImageView mImageChoiceB = null;
    private ImageView mImageChoiceC = null;
    private ImageView mImageChoiceD = null;


    private String chapterTitle;
    private ImageButton mButtonBack;
    private Button mButtonCommit = null;

    private QuestionInfo mCurrentQuestionInfo;
    private List<String> mChoiceMultiAnswers = new ArrayList<>();
    private String mChoiceSingleAnswer;
    private boolean isFirstIn = true;
    private int mQuestionType = SINGLE_CHOICE;


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
        chapterTitle = i.getStringExtra("chapterTitle");
        mOperator = getOperator(groupPosition);
        mQuestionInfos = mOperator.getQuestionsByChapterId(getChapterId(groupPosition,childPosition));

    }


    @Override
    public void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.id_question_detail_view_pager);
        mTextChapter = (TextView) findViewById(R.id.id_title_bar_text_title);


        mViews = new ArrayList<>();
        for(int i=0;i<mQuestionInfos.size();i++){
            View v = Utils.getView(R.layout.question_detail_item);
            mViews.add(v);

        }

        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateContent(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mButtonBack = (ImageButton) findViewById(R.id.id_title_bar_button_back);
        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentManager.finishActivity(QuestionDetailActivity.this);
            }
        });

        if(isFirstIn){
            updateContent(0);
            isFirstIn = false;
        }

    }


    private List<OptionInfo> mOptions = new ArrayList<>();

    private void updateContent(int position){
        mChoiceMultiAnswers.clear();
        mOptions.clear();

        mTextQuestionTitle = (TextView) mViews.get(position).findViewById(R.id.id_question_detail_text_title);
        mTextChoiceA = (TextView) mViews.get(position).findViewById(R.id.id_question_detail_text_choiceA);
        mTextChoiceB = (TextView) mViews.get(position).findViewById(R.id.id_question_detail_text_choiceB);
        mTextChoiceC = (TextView) mViews.get(position).findViewById(R.id.id_question_detail_text_choiceC);
        mTextChoiceD = (TextView) mViews.get(position).findViewById(R.id.id_question_detail_text_choiceD);
        mTextChapterTitle = (TextView) mViews.get(position).findViewById(R.id.id_question_detail_text_character);
        mButtonCommit = (Button) mViews.get(position).findViewById(R.id.id_question_detail_button_commit);
        mTextChoiceA = (TextView) mViews.get(position).findViewById(R.id.id_question_detail_text_choiceA);
        mTextChoiceB = (TextView) mViews.get(position).findViewById(R.id.id_question_detail_text_choiceB);
        mTextChoiceC = (TextView) mViews.get(position).findViewById(R.id.id_question_detail_text_choiceC);
        mTextChoiceD = (TextView) mViews.get(position).findViewById(R.id.id_question_detail_text_choiceD);
        mImageChoiceA = (ImageView) mViews.get(position).findViewById(R.id.id_question_detail_image_A);
        mImageChoiceB = (ImageView) mViews.get(position).findViewById(R.id.id_question_detail_image_B);
        mImageChoiceC = (ImageView) mViews.get(position).findViewById(R.id.id_question_detail_image_C);
        mImageChoiceD = (ImageView) mViews.get(position).findViewById(R.id.id_question_detail_image_D);


        mTextChoiceA.setOnClickListener(choiceAOnClickListener);
        mTextChoiceB.setOnClickListener(choiceBOnClickListener);
        mTextChoiceC.setOnClickListener(choiceCOnClickListener);
        mTextChoiceD.setOnClickListener(choiceDOnClickListener);
        mButtonCommit.setOnClickListener(commitOnClickListener);


        mTextChapterTitle.setText(chapterTitle);

        mImageChoiceA.setImageResource(R.mipmap.choice_a);
        mImageChoiceB.setImageResource(R.mipmap.choice_b);
        mImageChoiceC.setImageResource(R.mipmap.choice_c);
        mImageChoiceD.setImageResource(R.mipmap.choice_d);

        mTextChapter.setText(mQuestionInfos.get(position).getSubjectName());
        mOptions = mOperator.getOptionsByQuestionId(mQuestionInfos.get(position).getQuestionId());

        mTextChoiceA.setText(mOptions.get(0).getValue());
        mTextChoiceB.setText(mOptions.get(1).getValue());
        mTextChoiceC.setText(mOptions.get(2).getValue());
        mTextChoiceD.setText(mOptions.get(3).getValue());

        mCurrentQuestionInfo = mQuestionInfos.get(position);

        if(mCurrentQuestionInfo.getAnswer().contains(",")){
            mTextQuestionTitle.setText(getContentStyle(position + 1,mQuestionInfos.get(position).getNumber(),mQuestionInfos.get(position).getTitle()) + " (多选)");
            mQuestionType = MULTI_CHOICE;
        }else{
            mTextQuestionTitle.setText(getContentStyle(position + 1,mQuestionInfos.get(position).getNumber(),mQuestionInfos.get(position).getTitle()));
            mQuestionType = SINGLE_CHOICE;
        }

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

    //选择A
    private View.OnClickListener choiceAOnClickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mQuestionType == MULTI_CHOICE){
                handleChoiceAnswer(OPTION_A);
            }else if(mQuestionType == SINGLE_CHOICE){
                mChoiceSingleAnswer = OPTION_A;
            }

            updateOptionUI(OPTION_A);
        }
    };

    //选择B
    private View.OnClickListener choiceBOnClickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mQuestionType == MULTI_CHOICE){
                handleChoiceAnswer(OPTION_B);

            }else if(mQuestionType == SINGLE_CHOICE){
                mChoiceSingleAnswer = OPTION_B;
            }
            updateOptionUI(OPTION_B);
        }
    };

    //选择C
    private View.OnClickListener choiceCOnClickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mQuestionType == MULTI_CHOICE){
                handleChoiceAnswer(OPTION_C);
            }else if(mQuestionType == SINGLE_CHOICE){
                mChoiceSingleAnswer = OPTION_C;
            }
            updateOptionUI(OPTION_C);


        }
    };

    //选择D
    private View.OnClickListener choiceDOnClickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mQuestionType == MULTI_CHOICE){
                handleChoiceAnswer(OPTION_D);
            }else if(mQuestionType == SINGLE_CHOICE){
                mChoiceSingleAnswer = OPTION_D;
            }

            updateOptionUI(OPTION_D);


        }
    };

    private void updateOptionUI(String option) {
        switch (option){
            case OPTION_A:
                if(mQuestionType == MULTI_CHOICE){
                    if(mChoiceMultiAnswers.contains(OPTION_A)){
                        mImageChoiceA.setImageResource(R.mipmap.option_selected);
                    }else{
                        mImageChoiceA.setImageResource(R.mipmap.choice_a);
                    }
                }else if(mQuestionType == SINGLE_CHOICE){
                    clearChoiceUI();
                    mImageChoiceA.setImageResource(R.mipmap.option_selected);
                }

                break;

            case OPTION_B:
                if(mQuestionType == MULTI_CHOICE) {
                    if (mChoiceMultiAnswers.contains(OPTION_B)) {
                        mImageChoiceB.setImageResource(R.mipmap.option_selected);
                    } else {
                        mImageChoiceB.setImageResource(R.mipmap.choice_b);
                    }
                }else if(mQuestionType == SINGLE_CHOICE){
                    clearChoiceUI();
                    mImageChoiceB.setImageResource(R.mipmap.option_selected);
                }
                break;

            case OPTION_C:
                if(mQuestionType == MULTI_CHOICE) {
                    if(mChoiceMultiAnswers.contains(OPTION_C)){
                        mImageChoiceC.setImageResource(R.mipmap.option_selected);
                    }else{
                        mImageChoiceC.setImageResource(R.mipmap.choice_c);
                    }
                }else if(mQuestionType == SINGLE_CHOICE){
                    clearChoiceUI();
                    mImageChoiceC.setImageResource(R.mipmap.option_selected);
                }

                break;

            case OPTION_D:
                if(mQuestionType == MULTI_CHOICE) {
                    if(mChoiceMultiAnswers.contains(OPTION_D)){
                        mImageChoiceD.setImageResource(R.mipmap.option_selected);
                    }else{
                        mImageChoiceD.setImageResource(R.mipmap.choice_d);
                    }
                }else if(mQuestionType == SINGLE_CHOICE){
                    clearChoiceUI();
                    mImageChoiceD.setImageResource(R.mipmap.option_selected);
                }

                break;
        }
    }



    private View.OnClickListener commitOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(checkAnswer(mChoiceMultiAnswers)){

            }
        }
    };


    private List<String> handleChoiceAnswer(String answer){
        if(!mChoiceMultiAnswers.contains(answer)){
            mChoiceMultiAnswers.add(answer);
        }else{
            mChoiceMultiAnswers.remove(answer);
        }
        return mChoiceMultiAnswers;
    }

    private boolean checkAnswer(List<String> answers){
         for(int i=0;i<answers.size();i++){
             if(!mCurrentQuestionInfo.getAnswer().contains(answers.get(i))){
                    return false;
             }
         }
         return true;
    }

    private void clearChoiceUI(){
        mImageChoiceA.setImageResource(R.mipmap.choice_a);
        mImageChoiceB.setImageResource(R.mipmap.choice_b);
        mImageChoiceC.setImageResource(R.mipmap.choice_c);
        mImageChoiceD.setImageResource(R.mipmap.choice_d);
    }

    private class MultiChoice implements IChoiceOptionListener{

        @Override
        public void choiceOption(String option) {
            handleChoiceAnswer(option);
            if(mChoiceMultiAnswers.contains(option)){
                mImageChoiceA.setImageResource(R.mipmap.option_selected);
            }else{
                mImageChoiceA.setImageResource(R.mipmap.choice_a);
            }
        }
    }

    private class SingleChoice implements IChoiceOptionListener{

        @Override
        public void choiceOption(String option) {
            mChoiceSingleAnswer = option;
            clearChoiceUI();
            mImageChoiceA.setImageResource(R.mipmap.option_selected);
        }
    }

}
