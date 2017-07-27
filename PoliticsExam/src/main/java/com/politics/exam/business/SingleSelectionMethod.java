package com.politics.exam.business;

import android.view.View;

import com.politics.exam.R;
import com.politics.exam.util.Logger;

/**
 * Created by malijie on 2017/7/27.
 */

public class SingleSelectionMethod extends SelectionMethod implements ISelectionMethod{
    private String mChoiceSingleAnswer;


    public SingleSelectionMethod(View view) {
        super(view);
    }

    @Override
    public void choice(String option) {
        mChoiceSingleAnswer = option;
        clearSelectionUI();
        updateSelectionUI(option);

        Logger.mlj("single===" + mChoiceSingleAnswer);
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
        mChoiceSingleAnswer = "";
    }
}
