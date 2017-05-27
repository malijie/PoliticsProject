package com.politics.exam.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.politics.exam.R;
import com.politics.exam.activity.QuestionDetailActivity;
import com.politics.exam.util.IntentManager;
import com.politics.exam.util.Logger;
import com.politics.exam.util.Utils;

/**
 * Created by malijie on 2017/5/26.
 */

public class MyExpandableListAdapter implements ExpandableListAdapter {
    private View mGroupView = null;
    private View mChildView = null;
    private TextView mTextQuestionNum = null;
    private TextView mTextSubject = null;
    private TextView mTextCharacter = null;


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
        mTextQuestionNum.setText("321题");
        mTextSubject.setText(mSubjects[groupPosition]);
        return mGroupView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        mChildView = Utils.getView(R.layout.expand_item_view);
        RelativeLayout layout = (RelativeLayout) mChildView.findViewById(R.id.id_expand_item_layout);
        mTextCharacter = (TextView) mChildView.findViewById(R.id.id_expand_item_text_title);
        mTextCharacter.setText(mCharacter[groupPosition][childPosition]);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentManager.startActivity(QuestionDetailActivity.class);
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

}