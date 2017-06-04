package com.politics.exam.util;

import android.content.Context;

import com.politics.exam.PoliticsApplication;

public class SharedPreferenceUtil {
	private static final String SP_ANSWER_PROGRESS = "answer_progress";
	private static final String SP_LESSON_PROGRESS_KEY = "lesson";

	/**
	 * 保存单词学习进度
	 * @param progress
	 */
	public static void saveProgress(int groupId,int childId,int progress){
		PoliticsApplication.sContext.getSharedPreferences(SP_ANSWER_PROGRESS, Context.MODE_PRIVATE).edit()
				.putInt(SP_LESSON_PROGRESS_KEY + + groupId + childId,progress).commit();
	}

	/**
	 * 读取单词进度
	 * @return
	 */
	public static int loadProgress(int groupId,int childId){
		return PoliticsApplication.sContext.getSharedPreferences(SP_ANSWER_PROGRESS, Context.MODE_PRIVATE)
				.getInt(SP_LESSON_PROGRESS_KEY + groupId + childId,0);
	}
}
