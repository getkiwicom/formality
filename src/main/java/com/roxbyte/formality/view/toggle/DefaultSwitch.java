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

package com.roxbyte.formality.view.toggle;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Switch;

import com.roxbyte.formality.util.FormUtil;
import com.roxbyte.formality.view.Layout;

public class DefaultSwitch extends Switch implements Layout{

    public DefaultSwitch(Context context) {
        super(context);
        init();
    }

    public DefaultSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DefaultSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setMarginDp(int left, int top, int right, int bottom) {
        setLayoutParams(FormUtil.getMarginLinearDp(left, top, right, bottom, getResources()));
    }

    private void init(){
        style();
    }

    private void style(){
        setTextOn("");
        setTextOff("");
    }
}
