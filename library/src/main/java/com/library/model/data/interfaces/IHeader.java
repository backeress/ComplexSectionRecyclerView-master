package com.library.model.data.interfaces;

/**
 * Created by sung on 2017-10-10.
 */

public interface IHeader {
    void setViewType(int viewType);
    int getViewType();
    void setCustomData(Object customData);
    Object getCustomData();
}
