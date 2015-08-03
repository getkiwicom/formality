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

package com.roxbyte.formality.view.image;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.roxbyte.formality.util.LogUtil;
import com.roxbyte.formality.util.FormUtil;
import com.roxbyte.formality.view.Layout;

public class DefaultImageView extends ImageView implements Layout{

    public DefaultImageView(Context context) {
        super(context);
        init();
    }

    public DefaultImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DefaultImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

    }

    @Override
    public void setMarginDp(int left, int top, int right, int bottom) {
        setLayoutParams(FormUtil.getMarginLinearDp(left, top, right, bottom, getResources()));
    }

    public void setImageWithUrl(String urlString){
        setImageWithUrl(urlString, 0);
    }

    public void setImageWithUrl(String urlString, int backgroundResourcePlaceholder){
        setImageWithUrl(urlString, backgroundResourcePlaceholder, null);
    }

    public void setImageWithUrl(String urlString, int backgroundResourcePlaceholder, ImageAsyncTask.Listener listener){
        if(backgroundResourcePlaceholder != 0){
            setBackgroundResource(backgroundResourcePlaceholder);
        }

        new ImageAsyncTask(this, listener).execute(urlString);
    }

    public int getWidthBitmap(){
        if(getDrawable() == null){
            LogUtil.error("Drawable is null, cannot obtain width", getClass().getCanonicalName());
            return 0;
        }

        return getDrawable().getIntrinsicWidth();
    }

    public int getHeightBitmap(){
        if(getDrawable() == null){
            LogUtil.error("Drawable is null, cannot obtain height", getClass().getCanonicalName());
            return 0;
        }

        return getDrawable().getIntrinsicHeight();
    }

}
