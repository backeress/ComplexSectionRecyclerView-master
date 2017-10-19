package com.library.model.data;


/**
 * Created by sung on 2017-10-19.
 */

public class BaseItem implements IItem {

    int viewType;
    //커스텀뷰를 사용할 경우 사용할 Object 데이터
    //커스텀뷰를 사용할 경우 다양한 뷰 데이터 타입을 받을 수 있도록 한다.
    Object customData;
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
