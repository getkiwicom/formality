/*
 * Copyright (c) 2015 Roxbyte
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.roxbyte.formality.view.linear;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.roxbyte.formality.util.FormUtil;
import com.roxbyte.formality.view.Layout;

public class DefaultLinearLayout extends LinearLayout implements Layout {

    public DefaultLinearLayout(Context context) {
        super(context);
    }

    public DefaultLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefaultLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setMarginDp(int left, int top, int right, int bottom) {
        setLayoutParams(FormUtil.getMarginLinearDp(left, top, right, bottom, getResources()));
    }

    protected LinearLayout.LayoutParams createLayoutParams(){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        return layoutParams;
    }

    protected void defaultStyle(){
        setMarginDp(0, 0, 0, 0);
        setOrientation(VERTICAL);
    }

}
