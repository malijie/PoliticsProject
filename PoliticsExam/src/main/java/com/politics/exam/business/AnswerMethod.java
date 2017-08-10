package com.politics.exam.business;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.politics.exam.R;
import com.politics.exam.entity.QuestionInfo;

/**
 * Created by malijie on 2017/8/10.
 */

public class AnswerMethod implements IAnswerMethod{
    private LinearLayout mLayout = null;
    private TextView mTextAnswer = null;
    private TextView mTextOutline = null;
    private TextView mTextDetail = null;

    private QuestionInfo mCurrentInfo = null;

    public AnswerMethod(View view, QuestionInfo questionInfo){
        mLayout = (LinearLayout) view.findViewById(R.id.id_explain_detail_layout);
        mTextAnswer = (TextView) view.findViewById(R.id.id_explain_detail_text_answer);
        mTextOutline = (TextView) view.findViewById(R.id.id_explain_detail_text_outlie);
        mTextDetail = (TextView) view.findViewById(R.id.id_explain_detail_text_detail);
        mCurrentInfo = questionInfo;
    }


    @Override
    public void showAnswer() {
        mTextAnswer.setText(mCurrentInfo.getAnswer());
    }

    @Override
    public void showOutlineAnswer() {
        mTextOutline.setText(mCurrentInfo.getRestore());
    }

    @Override
    public void showAnswerDetail() {
        mTextDetail.setText(mCurrentInfo.getExplain());
    }

    @Override
    public void showAnswerUI(boolean isShow) {
        if(isShow){
            mLayout.setVisibility(View.VISIBLE);
            showAnswer();
            showOutlineAnswer();
            showAnswerDetail();
        }
    }
}
