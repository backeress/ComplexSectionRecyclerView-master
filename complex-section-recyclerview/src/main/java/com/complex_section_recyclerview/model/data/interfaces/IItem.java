package com.complex_section_recyclerview.model.data.interfaces;

/**
 * Created by sung on 2017-10-20.
 */

public interface IItem {
    void setViewType(int viewType);
    int getViewType();
    void setCustomData(Object customData);
    Object getCustomData();
}