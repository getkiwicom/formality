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
import android.graphics.PorterDuff;
import android.util.AttributeSet;

import com.roxbyte.formality.R;
import com.roxbyte.formality.view.image.DefaultImageView;

public class PageControlLayout extends FormLinearLayout{

    public PageControlLayout(Context context) {
        super(context);
    }

    public PageControlLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PageControlLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initPageDots(int pageCount, int startPosition){
        for(int i = 0; i < pageCount; i++){
            DefaultImageView imageViewDot = (DefaultImageView) createView(R.layout.inflate_image_dot);

            if(i == startPosition){
                imageViewDot.setColorFilter(getResources().getColor(R.color.gray_dark), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    public void updatePageDots(int position){
        int pageCount = getChildCount();

        for(int i = 0; i < pageCount; i++){
            DefaultImageView imageViewDot = (DefaultImageView) getChildAt(i);
            imageViewDot.clearColorFilter();

            if(position == i){
                imageViewDot.setColorFilter(getResources().getColor(R.color.gray_dark), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }
}
