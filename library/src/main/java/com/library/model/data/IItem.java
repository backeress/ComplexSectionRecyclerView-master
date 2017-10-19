package com.library.model.data;

/**
 * Created by sung on 2017-10-10.
 */

public interface IItem {

    //뷰탑입 반드시 구현되어야 한다.
    void setViewType(int viewType);
    int getViewType();
    //커스텀뷰를 사용할 경우 사용할 Object 데이터
    //커스텀뷰를 사용할 경우 다양한 뷰 데이터 타입을 받을 수 있도록 한다.
    void setCustomData(Object customData);
    Object getCustomData();
}
