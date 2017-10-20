package com.library.model.data;


import com.library.model.data.interfaces.IHeader;

/**
 * Created by sung on 2017-10-19.
 */

public class BaseHeader implements IHeader {
    int viewType;
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
