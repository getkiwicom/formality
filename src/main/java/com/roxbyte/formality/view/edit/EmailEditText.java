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

package com.roxbyte.formality.view.edit;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;

import com.roxbyte.formality.R;

public class EmailEditText extends DefaultEditText{

    public EmailEditText(Context context) {
        super(context);
        init();
    }

    public EmailEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmailEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setHint(getResources().getString(R.string.general_email));
        setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    }
}
