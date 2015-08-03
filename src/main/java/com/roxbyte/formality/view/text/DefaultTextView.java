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

package com.roxbyte.formality.view.text;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.roxbyte.formality.R;
import com.roxbyte.formality.util.FormUtil;
import com.roxbyte.formality.view.Layout;

public class DefaultTextView extends TextView implements Layout{

    public DefaultTextView(Context context) {
        super(context);
    }

    public DefaultTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFont(attrs);
    }

    public DefaultTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFont(attrs);
    }

    @Override
    public void setMarginDp(int left, int top, int right, int bottom) {
        setLayoutParams(FormUtil.getMarginLinearDp(left, top, right, bottom, getResources()));
    }

    public String getTextString(){
        return getText().toString();
    }

    public void setFontAssetPath(String fontAssetPath){
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), fontAssetPath);
        setTypeface(typeface);
    }

    private void initFont(AttributeSet attributeSet){
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.DefaultTextView);

        String fontAssetPath = typedArray.getString(R.styleable.DefaultTextView_font_asset_path);
        if(fontAssetPath != null){
            setFontAssetPath(fontAssetPath);
        }

        typedArray.recycle();

    }

}
