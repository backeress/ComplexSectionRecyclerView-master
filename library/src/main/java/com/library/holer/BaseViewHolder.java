package com.library.holer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sung on 2017-10-19.
 */

public abstract class BaseViewHolder <ITEM> extends RecyclerView.ViewHolder implements IViewHolder<ITEM> {
    public BaseViewHolder(View itemView) {

        super(itemView);
        //상속받은 뷰 홀더에서 ButterKnife 사용 할 경우
        //뷰홀더에서 ButterKnife 사용 할 때 오늘 날짜로 최신 버전을 사용해보니 오류가 발생한다.
        //뷰홀더에서 ButterKnife 사용 할 경우 아래의 버전으로 사용 하도록 한다.
        //build.gradle(Module)
        //compile 'com.jakewharton:butterknife:8.7.0'
        //annotationProcessor 'com.jakewharton:butterknife-compiler:8.7.0'

    }
    public BaseViewHolder(Context context, ViewGroup parent, int layout){
        super(LayoutInflater.from(context).inflate(layout, parent, false));
    }
    //기본 뷰타입 호출
    public abstract void onBindView(ITEM item);
    //커스텀 뷰타입 호출
    //커스텀 뷰타입이 하나 이상이라면, case 로 데이터를 형변환하여 처리 되도록 해야 한다.
    public abstract void onCustomBindView(Object item);

}