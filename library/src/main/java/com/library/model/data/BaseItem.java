package com.library.model.data;


import android.util.Log;

import com.library.model.data.interfaces.IItem;

/**
 * Created by sung on 2017-10-19.
 */

public class BaseItem implements IItem {

    int viewType;
    //커스텀뷰를 사용할 경우 사용할 Object 데이터
    //커스텀뷰를 사용할 경우 다양한 뷰 데이터 타입을 받을 수 있도록 한다.
    Object customData;

    public BaseItem()
    {
        //★★★★
        //뷰 타입은 절대 초기화하면 안된다.
        //리사이클뷰에서 this.viewType 값이 없으면, 헤더/아이템/푸터에 따라 기본 뷰를 출력 한다.
        //this.viewType 값이 초기화되면, 커스텀뷰 번호라고 인식하기 때문에 절대로 초기화해서는 안된다.
        //커스텀뷰를 초기화 할 경우 리사이클뷰 헤더/아이템/푸터를 생성할 떄 초기화 할 수 있다.
        //샘플: section1.addItem(new Item(ComplexSectionRecyclerViewAdapter.USER_ITEM_TYPE_CUSTOM_TEST,"커스텀 데이터"));
        //this.viewType;

        this.customData = null;
    }

    //커스텀 뷰 아이템인 경우에는 반드시 생성자로 변수가 초기화 되어야 한다.
    //커스텀 뷰 아이템 초기화를 위해 필요한 생성자
    public BaseItem(int viewType, Object customData)
    {
        this.viewType = viewType;
        this.customData = customData;
    }
    @Override
    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
    @Override
    public int getViewType() {
        return this.viewType;
    }

    @Override
    public void setCustomData(Object customData) {
        this.customData = customData;
    }


    @Override
    public Object getCustomData() {
        return this.customData;
    }
}
