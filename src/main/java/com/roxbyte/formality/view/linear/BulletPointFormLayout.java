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

import com.roxbyte.formality.R;
import com.roxbyte.formality.view.image.DefaultImageView;
import com.roxbyte.formality.view.text.DefaultTextView;

public class BulletPointFormLayout extends FormLinearLayout {

    public BulletPointFormLayout(Context context) {
        super(context);
    }

    public BulletPointFormLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BulletPointFormLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void traverse(InflationListener inflationListener, int... resources){
        for(int resource: resources){

            DefaultLinearLayout defaultLinearLayout = (DefaultLinearLayout) createView(R.layout.inflate_bullet_point);
            DefaultImageView imageViewPoint = (DefaultImageView) defaultLinearLayout.findViewById(R.id.image_bullet_point);
            DefaultTextView textViewPoint = (DefaultTextView) defaultLinearLayout.findViewById(R.id.text_bullet_point);
            textViewPoint.setText(getResources().getString(resource));

            inflationListener.inflated(imageViewPoint, textViewPoint);
        }
    }

    public interface InflationListener {

        void inflated(DefaultImageView imageViewPoint, DefaultTextView textViewPoint);

    }
}
