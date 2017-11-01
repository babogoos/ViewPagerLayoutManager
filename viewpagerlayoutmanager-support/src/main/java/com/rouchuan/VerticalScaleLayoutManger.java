package com.rouchuan;

import android.view.View;

import rouchuan.customlayoutmanager.ViewPagerLayoutManager;

/**
 * Created by dion on 2017/10/21.
 */

public class VerticalScaleLayoutManger extends ViewPagerLayoutManager {

    private static final float SCALE_RATE = 1.2f;
    private int itemSpace = 0;

    public VerticalScaleLayoutManger(int itemSpace) {
        this(itemSpace, false);
    }

    public VerticalScaleLayoutManger(int itemSpace, boolean shouldReverseLayout) {
        super(shouldReverseLayout,ViewPagerLayoutManager.VERTICAL_MODE);
        this.itemSpace = itemSpace;
    }

    @Override
    protected float setInterval() {
        return (int) (mDecoratedChildHeight * ((SCALE_RATE - 1f) / 2f + 1) + itemSpace);
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void setItemViewProperty(View itemView, float targetOffset) {
        float scale = calculateScale((int) targetOffset + startTop);
        itemView.setScaleX(scale);
        itemView.setScaleY(scale);
    }

    /**
     * @param y start positon of the view you want scale
     * @return the scale rate of current scroll offset
     */
    private float calculateScale(int y) {
        int deltaY = Math.abs(y - (getVerticalSpace() - mDecoratedChildHeight) / 2);
        float diff = 0f;
        if ((mDecoratedChildHeight - deltaY) > 0) diff = mDecoratedChildHeight - deltaY;
        return (SCALE_RATE - 1f) / mDecoratedChildHeight * diff + 1;
    }
}
