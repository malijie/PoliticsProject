package com.politics.exam.business;

import android.view.View;
import android.widget.ImageView;

import com.politics.exam.R;
import com.politics.exam.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malijie on 2017/7/27.
 */

public class MultiSelectionMethod extends SelectionMethod implements ISelectionMethod {
    private List<String> mChoiceMultiAnswers = new ArrayList<>();

    public MultiSelectionMethod(View view) {
        super(view);
    }

    @Override
    public void choice(String option) {
        handleSelectionAnswer(option);
        updateSelectionUI(option);

Logger.mlj("multi===" + mChoiceMultiAnswers);
    }

    private void updateSelectionUI(String option) {
        if(mChoiceMultiAnswers.contains(option)){
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
        }else{
            switch (option){
                case "A":
                    mImageSelectionA.setImageResource(R.mipmap.choice_a);
                    break;
                case "B":
                    mImageSelectionB.setImageResource(R.mipmap.choice_b);
                    break;
                case "C":
                    mImageSelectionC.setImageResource(R.mipmap.choice_c);
                    break;
                case "D":
                    mImageSelectionD.setImageResource(R.mipmap.choice_d);
                    break;
            }
        }

    }

    @Override
    public void clearData() {
        mChoiceMultiAnswers.clear();
    }

    private List<String> handleSelectionAnswer(String answer){
        if(!mChoiceMultiAnswers.contains(answer)){
            mChoiceMultiAnswers.add(answer);
        }else{
            mChoiceMultiAnswers.remove(answer);
        }
        return mChoiceMultiAnswers;
    }
}
