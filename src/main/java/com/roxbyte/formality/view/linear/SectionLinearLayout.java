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
import android.view.View;

import com.roxbyte.formality.R;
import com.roxbyte.formality.util.FormUtil;
import com.roxbyte.formality.view.text.DefaultTextView;

public class SectionLinearLayout extends DefaultLinearLayout{

    private DefaultTextView textViewTitle;

    public SectionLinearLayout(Context context) {
        super(context);
        init();
    }

    public SectionLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SectionLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DefaultTextView getTitle() {
        return textViewTitle;
    }

    private void init(){
        View.inflate(getContext(), R.layout.view_section_linear_layout, this);
        textViewTitle = (DefaultTextView) findViewById(R.id.text_section_title);

        setLayoutParams(createLayoutParams());
        getLayoutParams().height = FormUtil.convertToPx(40, getResources());
    }
}
