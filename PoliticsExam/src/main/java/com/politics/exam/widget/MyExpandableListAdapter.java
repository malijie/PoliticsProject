package com.politics.exam.widget;

import android.content.Intent;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.politics.exam.R;
import com.politics.exam.activity.QuestionDetailActivity;
import com.politics.exam.data.Profile;
import com.politics.exam.db.operator.ChapterMZTDBOperator;
import com.politics.exam.db.operator.ChapterMYDBOperator;
import com.politics.exam.db.operator.ChapterSGDBOperator;
import com.politics.exam.db.operator.ChapterSXYFJDBOperator;
import com.politics.exam.db.operator.ChapterSZDBOperator;
import com.politics.exam.db.operator.IDBOperator;
import com.politics.exam.entity.QuestionInfo;
import com.politics.exam.util.IntentManager;
import com.politics.exam.util.Logger;
import com.politics.exam.util.SharedPreferenceUtil;
import com.politics.exam.util.Utils;

import java.util.List;

import static com.politics.exam.data.Profile.MY_01;
import static com.politics.exam.data.Profile.MY_02;

/**
 * Created by malijie on 2017/5/26.
 */

public class MyExpandableListAdapter implements ExpandableListAdapter {
    private View mGroupView = null;
    private View mChildView = null;
    private TextView mTextQuestionNum = null;
    private TextView mTextSubject = null;
    private TextView mTextCharacter = null;
    private TextView mTextProcess = null;

    private IDBOperator mOperator = null;
    private int mQuestionCounts = 0;


    private String[] mSubjects = new String[]{
            "马原", "毛中特", "史纲", "思修与法基","时政"
    };


    private String[][] mCharacter = new String[][]{
            {"第一章 马克思主义是关于无产阶级和人类解放科学", "第二章 世界的物质性及其发展规律", "第三章 认识的本质及其发展规律",
             "第四章 人类社会及其发展规律", "第五章 资本主义的本质及其规律", "第六章 资本主义的发展及其趋势",
             "第七章 人类社会及其发展规律", "第八章 共产主义崇高理想及其最终实现"},

            {"第一章 马克思主义中国画两大理论成果", "第二章 新民主主义革命理论", "第三章 社会主义改造理论",
             "第四章 社会主义建设道路初步探索的理论成果", "第五章 建设中国特色社会主义总依据", "第六章 社会主义本质和建设中国特色社会主义总任务",
             "第七章 社会主义改革开放理论", "第八章 建设中国特色社会主义总布局","第九章 实现祖国完全统一的理论","第十章 中国特设社会主义外交和国际战略",
             "第十一章 建设中国特色社会主义的根本目的和依靠力量","第十二章 中国特色社会主义领导核心理论"},

            {"第一章 反对外国侵略战争", "第二章 对国家出路的早期探索", "第三章 辛亥革命与君主专制制度的终结",
             "第四章 开天辟地的大事变", "第五章 中国革命的新道路", "第六章 中华民族的抗日战争",
             "第七章 为新中国而奋斗", "第八章 社会主义基本制度在中国的确立", "第九章 社会主义建设在探索中曲折发展",
             "第十章 改革开放与现代化建设新时期"},

            {"绪论","第一章 追求远大理想 坚定崇高信念", "第二章 弘扬中国精神 共筑精神家园", "第三章 领悟人生真谛 创造人生价值",
                    "第四章 注重道德传承 加强道德实践", "第五章 遵守道德规范 锤炼高尚品格", "第六章 学习宪法法律 建设法制体系",
                    "第七章 树立法制观念 尊重法律权威", "第八章 行使法律权利 履行法律义务"},

            {"1月","2月", "3月", "4月","5月", "6月", "7月","8月", "9月","10月", "11月"}

    };

    private static final int INDEX_MAYUAN = 0;//马原
    private static final int INDEX_MAOZHONGTE = 1;//毛中特
    private static final int INDEX_SHIGANG = 2; //史纲
    private static final int INDEX_SIXIUYUFANGJI = 3;//思修与法基
    private static final int INDEX_SHIZHENG = 4;//时政


    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return mSubjects.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mCharacter[groupPosition].length;
    }

    @Override
    public String getGroup(int groupPosition) {
        return mSubjects[groupPosition];
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return mCharacter[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        mGroupView = Utils.getView(R.layout.expand_group_view);
        mTextQuestionNum = (TextView) mGroupView.findViewById(R.id.id_expand_group_text_question_num);
        mTextSubject = (TextView) mGroupView.findViewById(R.id.id_expand_group_text_title);

        mQuestionCounts = getGroupQuestionCount(groupPosition);

        mTextQuestionNum.setText(String.valueOf(mQuestionCounts) + "题");
        mTextSubject.setText(mSubjects[groupPosition]);
        return mGroupView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        mChildView = Utils.getView(R.layout.expand_item_view);
        RelativeLayout layout = (RelativeLayout) mChildView.findViewById(R.id.id_expand_item_layout);
        mTextCharacter = (TextView) mChildView.findViewById(R.id.id_expand_item_text_title);
        mTextProcess = (TextView) mChildView.findViewById(R.id.id_expand_item_text_num);

        int questionCount = getQuestionTotalCount(groupPosition,childPosition);
        int process = SharedPreferenceUtil.loadProgress(groupPosition,childPosition);

        mTextProcess.setText( process + "/"+ questionCount);
        mTextCharacter.setText(mCharacter[groupPosition][childPosition]);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("groupPosition",groupPosition);
                i.putExtra("childPosition",childPosition);
                IntentManager.startActivity(i,QuestionDetailActivity.class);
            }
        });
        return mChildView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }



    @Override
    public void onGroupExpanded(int groupPosition) {
        List<QuestionInfo> questionInfos = null;
        switch (groupPosition){
            case INDEX_MAYUAN:
                mOperator = new ChapterMYDBOperator();
                questionInfos = mOperator.getChapterQuestions();
                break;
            case INDEX_MAOZHONGTE:
                mOperator = new ChapterMZTDBOperator();
                questionInfos = mOperator.getChapterQuestions();

                break;
            case INDEX_SHIGANG:
                mOperator = new ChapterSGDBOperator();
                questionInfos = mOperator.getChapterQuestions();

                break;
            case INDEX_SIXIUYUFANGJI:
                mOperator = new ChapterSXYFJDBOperator();
                questionInfos = mOperator.getChapterQuestions();

                break;
            case INDEX_SHIZHENG:
                mOperator = new ChapterSZDBOperator();
                questionInfos = mOperator.getChapterQuestions();

                break;
        }
        Logger.mlj("size=" + questionInfos.size() +  "last item=" + questionInfos.get(questionInfos.size()-1).getTitle());
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    private int getGroupQuestionCount(int groupIndex){
        switch (groupIndex){
            case INDEX_MAYUAN:
                mOperator = new ChapterMYDBOperator();
                mQuestionCounts = mOperator.getQuestionCount();
                break;
            case INDEX_MAOZHONGTE:
                mOperator = new ChapterMZTDBOperator();
                mQuestionCounts = mOperator.getQuestionCount();

                break;
            case INDEX_SHIGANG:
                mOperator = new ChapterSGDBOperator();
                mQuestionCounts = mOperator.getQuestionCount();

                break;
            case INDEX_SIXIUYUFANGJI:
                mOperator = new ChapterSXYFJDBOperator();
                mQuestionCounts = mOperator.getQuestionCount();

                break;
            case INDEX_SHIZHENG:
                mOperator = new ChapterSZDBOperator();
                mQuestionCounts = mOperator.getQuestionCount();

                break;
        }
        return mQuestionCounts;
    }

    private int getQuestionTotalCount(int groupPosition,int childPosition){
        int count = 0;
        if(groupPosition == 0 && childPosition == 0){
            count = Profile.MY_01;
        }else if(groupPosition == 0 && childPosition == 1){
            count = Profile.MY_02;
        }else if(groupPosition == 0 && childPosition == 2){
            count = Profile.MY_03;
        }else if(groupPosition == 0 && childPosition == 3){
            count = Profile.MY_04;
        }else if(groupPosition == 0 && childPosition == 4){
            count = Profile.MY_05;
        }else if(groupPosition == 0 && childPosition == 5){
            count = Profile.MY_06;
        }else if(groupPosition == 0 && childPosition == 6){
            count = Profile.MY_07;
        }else if(groupPosition == 0 && childPosition == 7){
            count = Profile.MY_08;


        }else if(groupPosition == 1 && childPosition == 0){
            count = Profile.MZT_01;
        }else if(groupPosition == 1 && childPosition == 1){
            count = Profile.MZT_02;
        }else if(groupPosition == 1 && childPosition == 2){
            count = Profile.MZT_03;
        }else if(groupPosition == 1 && childPosition == 3){
            count = Profile.MZT_04;
        }else if(groupPosition == 1 && childPosition == 4){
            count = Profile.MZT_05;
        }else if(groupPosition == 1 && childPosition == 5){
            count = Profile.MZT_06;
        }else if(groupPosition == 1 && childPosition == 6){
            count = Profile.MZT_07;
        }else if(groupPosition == 1 && childPosition == 7){
            count = Profile.MZT_08;
        }else if(groupPosition == 1 && childPosition == 8){
            count = Profile.MZT_09;
        }else if(groupPosition == 1 && childPosition == 9){
            count = Profile.MZT_10;
        }else if(groupPosition == 1 && childPosition == 10){
            count = Profile.MZT_11;
        }else if(groupPosition == 1 && childPosition == 11){
            count = Profile.MZT_12;
        }


        else if(groupPosition == 2 && childPosition == 0){
            count = Profile.SG_01;
        }else if(groupPosition == 2 && childPosition == 1){
            count = Profile.SG_02;
        }else if(groupPosition == 2 && childPosition == 2){
            count = Profile.SG_03;
        }else if(groupPosition == 2 && childPosition == 3){
            count = Profile.SG_04;
        }else if(groupPosition == 2 && childPosition == 4){
            count = Profile.SG_05;
        }else if(groupPosition == 2 && childPosition == 5){
            count = Profile.SG_06;
        }else if(groupPosition == 2 && childPosition == 6){
            count = Profile.SG_07;
        }else if(groupPosition == 2 && childPosition == 7){
            count = Profile.SG_08;
        }else if(groupPosition == 2 && childPosition == 8){
            count = Profile.SG_09;
        }else if(groupPosition == 2 && childPosition == 9){
            count = Profile.SG_10;


        }else if(groupPosition == 3 && childPosition == 0){
            count = Profile.SXYFJ_00;
        }else if(groupPosition == 3 && childPosition == 1){
            count = Profile.SXYFJ_01;
        }else if(groupPosition == 3 && childPosition == 2){
            count = Profile.SXYFJ_02;
        }else if(groupPosition == 3 && childPosition == 3){
            count = Profile.SXYFJ_03;
        }else if(groupPosition == 3 && childPosition == 4){
            count = Profile.SXYFJ_04;
        }else if(groupPosition == 3 && childPosition == 5){
            count = Profile.SXYFJ_05;
        }else if(groupPosition == 3 && childPosition == 6){
            count = Profile.SXYFJ_06;
        }else if(groupPosition == 3 && childPosition == 7){
            count = Profile.SXYFJ_07;
        }else if(groupPosition == 3 && childPosition == 8){
            count = Profile.SXYFJ_08;
        }


        else if(groupPosition == 4 && childPosition == 0){
            count = Profile.SZ_01;
        }else if(groupPosition == 4 && childPosition == 1){
            count = Profile.SZ_02;
        }else if(groupPosition == 4 && childPosition == 2){
            count = Profile.SZ_03;
        }else if(groupPosition == 4 && childPosition == 3){
            count = Profile.SZ_04;
        }else if(groupPosition == 4 && childPosition == 4){
            count = Profile.SZ_05;
        }else if(groupPosition == 4 && childPosition == 5){
            count = Profile.SZ_06;
        }else if(groupPosition == 4 && childPosition == 6){
            count = Profile.SZ_07;
        }else if(groupPosition == 4 && childPosition == 7){
            count = Profile.SZ_08;
        }else if(groupPosition == 4 && childPosition == 8){
            count = Profile.SZ_09;
        }else if(groupPosition == 4 && childPosition == 9){
            count = Profile.SZ_10;
        }else if(groupPosition == 4 && childPosition == 10){
            count = Profile.SZ_11;
        }
        return count;
    }

}