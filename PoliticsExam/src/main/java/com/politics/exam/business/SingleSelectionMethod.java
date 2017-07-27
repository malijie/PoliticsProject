package com.politics.exam.business;

import android.view.View;

import com.politics.exam.R;
import com.politics.exam.entity.OptionInfo;
import com.politics.exam.entity.QuestionInfo;
import com.politics.exam.util.Logger;

import java.util.List;

/**
 * Created by malijie on 2017/7/27.
 */

public class SingleSelectionMethod extends SelectionMethod implements ISelectionMethod{
    private String mSelectionAnswer;
    private QuestionInfo mQuestionInfo;

    public SingleSelectionMethod(View view , QuestionInfo questionInfo, List<OptionInfo> options) {
        super(view,questionInfo,options);
        mQuestionInfo = questionInfo;
    }

    @Override
    public void choice(String option) {
        mSelectionAnswer = option;
        clearSelectionUI();
        updateSelectionUI(option);
    }

    private void clearSelectionUI(){
        mImageSelectionA.setImageResource(R.mipmap.choice_a);
        mImageSelectionB.setImageResource(R.mipmap.choice_b);
        mImageSelectionC.setImageResource(R.mipmap.choice_c);
        mImageSelectionD.setImageResource(R.mipmap.choice_d);
    }

    private void updateSelectionUI(String option){
        switch (option){
            case "A":
                mImageSelectionA.setImageResource(R.mipmap.option_selected);
                break;

            case "B":
                mImageSelectionB.setImageResource(R.mipmap.option_selected);
                break;

            case "C":
                mImageSelectionC.setImageResource(R.mipmap.option_selected);
                break;

            case "D":
                mImageSelectionD.setImageResource(R.mipmap.option_selected);
                break;
        }

    }

    @Override
    public void clearData() {
        mSelectionAnswer = "";
    }

    @Override
    public String getSelectionType() {
        return SINGLE_SELECTION;
    }

    @Override
    public void checkAnswers(String selection) {
        if(selection.equals(mQuestionInfo.getAnswer())){
            showRightOption(selection);
        }else{
            updateWrongOptionUI(selection);
            showRightOption(mQuestionInfo.getAnswer());
        }
    }

    @Override
    public String getSelection() {
        return mSelectionAnswer;
    }

    private void showRightOption(String answer) {
        switch (answer){
            case "A":
                mImageSelectionA.setImageResource(R.mipmap.answer_right);
                break;

            case "B":
                mImageSelectionB.setImageResource(R.mipmap.answer_right);
                break;

            case "C":
                mImageSelectionC.setImageResource(R.mipmap.answer_right);
                break;

            case "D":
                mImageSelectionD.setImageResource(R.mipmap.answer_right);
                break;
        }
    }

    private void updateWrongOptionUI(String option){
        switch (option){
            case "A":
                mImageSelectionA.setImageResource(R.mipmap.answer_wrong);
                break;

            case "B":
                mImageSelectionB.setImageResource(R.mipmap.answer_wrong);
                break;

            case "C":
                mImageSelectionC.setImageResource(R.mipmap.answer_wrong);
                break;

            case "D":
                mImageSelectionD.setImageResource(R.mipmap.answer_wrong);
                break;
        }
    }
}
