package com.politics.exam.business;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.politics.exam.R;
import com.politics.exam.entity.OptionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malijie on 2017/7/27.
 */

public class SelectionMethod implements ISelectionMethod {
    private static final String OPTION_A = "A";
    private static final String OPTION_B = "B";
    private static final String OPTION_C = "C";
    private static final String OPTION_D = "D";
    private static final String SINGLE_SELECTION = "1";
    private static final String MULTI_SELECTION = "2";

    protected ImageView mImageSelectionA = null;
    protected ImageView mImageSelectionB = null;
    protected ImageView mImageSelectionC = null;
    protected ImageView mImageSelectionD = null;

    protected TextView mTextChoiceA = null;
    protected TextView mTextChoiceB = null;
    protected TextView mTextChoiceC = null;
    protected TextView mTextChoiceD = null;

    private ISelectionMethod choiceMethod;
    private List<OptionInfo> mOptions = new ArrayList<>();

    public SelectionMethod(){

    }

    public SelectionMethod(View view,List<OptionInfo> options){
        mOptions = options;
        mImageSelectionA = (ImageView) view.findViewById(R.id.id_question_detail_image_A);
        mImageSelectionB = (ImageView) view.findViewById(R.id.id_question_detail_image_B);
        mImageSelectionC = (ImageView) view.findViewById(R.id.id_question_detail_image_C);
        mImageSelectionD = (ImageView) view.findViewById(R.id.id_question_detail_image_D);

        mTextChoiceA = (TextView) view.findViewById(R.id.id_question_detail_text_choiceA);
        mTextChoiceB = (TextView) view.findViewById(R.id.id_question_detail_text_choiceB);
        mTextChoiceC = (TextView) view.findViewById(R.id.id_question_detail_text_choiceC);
        mTextChoiceD = (TextView) view.findViewById(R.id.id_question_detail_text_choiceD);

        mTextChoiceA.setOnClickListener(choiceAOnClickListener);
        mTextChoiceB.setOnClickListener(choiceBOnClickListener);
        mTextChoiceC.setOnClickListener(choiceCOnClickListener);
        mTextChoiceD.setOnClickListener(choiceDOnClickListener);

        mTextChoiceA.setText(mOptions.get(0).getValue());
        mTextChoiceB.setText(mOptions.get(1).getValue());
        mTextChoiceC.setText(mOptions.get(2).getValue());
        mTextChoiceD.setText(mOptions.get(3).getValue());
    }

    public String getMultiSelectionType(){
        return MULTI_SELECTION;
    }

    public String getSingleSelectionType(){
        return SINGLE_SELECTION;
    }

    //选择A
    private View.OnClickListener choiceAOnClickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            choice(OPTION_A);
        }
    };

    //选择B
    private View.OnClickListener choiceBOnClickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            choice(OPTION_B);
        }
    };

    //选择C
    private View.OnClickListener choiceCOnClickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            choice(OPTION_C);
        }
    };

    //选择D
    private View.OnClickListener choiceDOnClickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            choice(OPTION_D);
        }
    };


    public void setSelectionMethod(ISelectionMethod choiceMethod){
        this.choiceMethod = choiceMethod;
    }

    @Override
    public void choice(String option) {
        choiceMethod.choice(option);
    }

    @Override
    public void clearData() {
        choiceMethod.clearData();
    }


}
