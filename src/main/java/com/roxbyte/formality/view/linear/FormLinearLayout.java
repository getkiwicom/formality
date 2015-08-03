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

import com.roxbyte.formality.form.FormCreation;
import com.roxbyte.formality.form.Validation;
import com.roxbyte.formality.view.Layout;
import com.roxbyte.formality.view.edit.DefaultEditText;
import com.roxbyte.formality.view.spinner.DefaultSpinner;
import com.roxbyte.formality.view.text.DateTextView;

public class FormLinearLayout extends DefaultLinearLayout {

    private FormCreation formCreation;

    public FormLinearLayout(Context context) {
        super(context);
        init();
    }

    public FormLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FormLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        formCreation = new FormCreation(getContext());
    }

    public View createFormView(Layout layout) {
        return createFormView(layout, null);
    }

    public View createFormView(Layout layout, Object tag) {
        View view = (View) layout;

        if (tag != null) {
            view.setTag(tag);
        }

        addView(view);
        return view;
    }

    public View createView(View view) {
        addView(view);
        return view;
    }

    public View createView(int resourceId) {
        View view = formCreation.createView(resourceId);
        addView(view);
        return view;
    }

    public View findView(Object tag) {
        return findViewWithTag(tag);
    }

    public View findView(int formalityResourceId) {
        return findViewWithTag(formalityResourceId);
    }

    public void validate(Validation.Response response) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);

            if (view instanceof DefaultEditText) {
                DefaultEditText defaultEditText = (DefaultEditText) view;
                defaultEditText.validate(response);
                if (!defaultEditText.getValidation().isValid()) {
                    return;
                }
            } else if(view instanceof DefaultSpinner){
                DefaultSpinner defaultSpinner = (DefaultSpinner) view;
                defaultSpinner.validate(response);
                if(!defaultSpinner.getValidation().isValid()){
                    return;
                }
            } else if(view instanceof DateTextView){
                DateTextView dateTextView = (DateTextView) view;
                dateTextView.validate(response);
                if(!dateTextView.getValidation().isValid()){
                    return;
                }
            }
        }

        response.success();
    }
}
