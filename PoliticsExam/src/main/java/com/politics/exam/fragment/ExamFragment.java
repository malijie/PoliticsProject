package com.politics.exam.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.politics.exam.R;
import com.politics.exam.activity.SearchActivity;
import com.politics.exam.db.operator.BaseOperator;
import com.politics.exam.entity.ExamInfo;
import com.politics.exam.util.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by malijie on 2016/12/8.
 */

public class ExamFragment extends Fragment {
    private ListView lv = null;
    private List<ExamInfo> examList = new ArrayList<>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.exam_fragment, container, false);

        initData();
        initViews(v);

        return v;
    }

    private void initData() {
        examList = new BaseOperator().getExamInfos();
    }

    private void initViews(View v) {
        lv = (ListView) v.findViewById(R.id.id_exam_lv);
        lv.setAdapter(new ExamAdapter(examList));
    }

    private class ExamAdapter extends BaseAdapter{
        private List<ExamInfo> mEexamInfos = new ArrayList<>();
        public ExamAdapter(List<ExamInfo> examInfos){
            mEexamInfos = examInfos;
        }


        @Override
        public int getCount() {
            return examList.size();
        }

        @Override
        public Object getItem(int position) {
            return examList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView == null){
                convertView = Utils.getView(R.layout.exam_item);
                holder = new ViewHolder();
                holder.mTextContent = (TextView) convertView.findViewById(R.id.id_exam_item_text_content);
                holder.mTextTitle = (TextView) convertView.findViewById(R.id.id_exam_item_text_title);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.mTextContent.setText(mEexamInfos.get(position).getContent());
            holder.mTextTitle.setText("2010年全国考研政治真题");

            return convertView;
        }

        private class ViewHolder{
            public TextView mTextTitle = null;
            public TextView mTextContent = null;

        }
    }

}