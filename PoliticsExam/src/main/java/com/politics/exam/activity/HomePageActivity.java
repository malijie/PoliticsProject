package com.politics.exam.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.politics.exam.R;
import com.politics.exam.fragment.QuestionsFragment;
import com.politics.exam.fragment.ExamFragment;
import com.politics.exam.fragment.UserFragment;


/**
 * Created by malijie on 2016/12/8.
 */

public class HomePageActivity extends Activity implements View.OnClickListener{
    private QuestionsFragment mQuestionFragment;
    private ExamFragment mExamFragment;
    private UserFragment mUserFragment;
    private View mQuestionsLayout;
    private View mExamLayout;
    private View mUserLayout;
    private ImageView mQuestionsImage;
    private ImageView mExamImage;
    private ImageView mUserImage;
    private TextView mQuestionText;
    private TextView mExamText;
    private TextView mUserText;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home_page);
        // 初始化布局元素
        initViews();
        fragmentManager = getFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(0);
    }

    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */
    private void initViews() {
        mQuestionsLayout = findViewById(R.id.questions_layout);
        mExamLayout = findViewById(R.id.exam_layout);
        mUserLayout = findViewById(R.id.user_layout);
        mQuestionsImage = (ImageView) findViewById(R.id.message_image);
        mExamImage = (ImageView) findViewById(R.id.contacts_image);
        mUserImage = (ImageView) findViewById(R.id.news_image);
        mQuestionText = (TextView) findViewById(R.id.message_text);
        mExamText = (TextView) findViewById(R.id.contacts_text);
        mUserText = (TextView) findViewById(R.id.news_text);
        mQuestionsLayout.setOnClickListener(this);
        mExamLayout.setOnClickListener(this);
        mUserLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.questions_layout:
                // 当点击了消息tab时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.exam_layout:
                // 当点击了联系人tab时，选中第2个tab
                setTabSelection(1);
                break;
            case R.id.user_layout:
                // 当点击了动态tab时，选中第3个tab
                setTabSelection(2);
                break;
            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index
     *            每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                mQuestionsImage.setImageResource(R.mipmap.tab_chactor_selected);
                mQuestionText.setTextColor(Color.WHITE);
                if (mQuestionFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    mQuestionFragment = new QuestionsFragment();
                    transaction.add(R.id.content, mQuestionFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(mQuestionFragment);
                }
                break;
            case 1:
                // 当点击了联系人tab时，改变控件的图片和文字颜色
                mExamImage.setImageResource(R.mipmap.tab_huodong_selected);
                mExamText.setTextColor(Color.WHITE);
                if (mExamFragment == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    mExamFragment = new ExamFragment();
                    transaction.add(R.id.content, mExamFragment);
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(mExamFragment);
                }
                break;
            case 2:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                mUserImage.setImageResource(R.mipmap.tab_pinche_selected);
                mUserText.setTextColor(Color.WHITE);
                if (mUserFragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    mUserFragment = new UserFragment();
                    transaction.add(R.id.content, mUserFragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(mUserFragment);
                }
                break;
            default:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                mQuestionsImage.setImageResource(R.mipmap.tab_buy_selected);
                mQuestionText.setTextColor(Color.WHITE);
                if (mUserFragment == null) {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    mUserFragment = new UserFragment();
                    transaction.add(R.id.content, mUserFragment);
                } else {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    transaction.show(mUserFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        mQuestionsImage.setImageResource(R.mipmap.tab_chactor_normal);
        mQuestionText.setTextColor(Color.parseColor("#82858b"));
        mExamImage.setImageResource(R.mipmap.tab_huodong_normal);
        mExamText.setTextColor(Color.parseColor("#82858b"));
        mUserImage.setImageResource(R.mipmap.tab_pinche_normal);
        mUserText.setTextColor(Color.parseColor("#82858b"));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mQuestionFragment != null) {
            transaction.hide(mQuestionFragment);
        }
        if (mExamFragment != null) {
            transaction.hide(mExamFragment);
        }
        if (mUserFragment != null) {
            transaction.hide(mUserFragment);
        }
    }
}
