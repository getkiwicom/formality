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
import android.util.AttributeSet;
import android.util.Patterns;
import android.widget.EditText;

import com.roxbyte.formality.R;
import com.roxbyte.formality.form.Validation;
import com.roxbyte.formality.util.FormUtil;
import com.roxbyte.formality.view.Layout;

import java.util.Map;

public class DefaultEditText extends EditText implements Layout {

    private boolean errorSymbolEnabled;
    private Validation<Validation.Input> validation;

    public DefaultEditText(Context context) {
        super(context);
        init();
    }

    public DefaultEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DefaultEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setMarginDp(int left, int top, int right, int bottom) {
        setLayoutParams(FormUtil.getMarginLinearDp(left, top, right, bottom, getResources()));
    }

    public void setErrorSymbolEnabled(boolean errorSymbolEnabled) {
        this.errorSymbolEnabled = errorSymbolEnabled;
    }

    public String getTextString() {
        return getText().toString().trim();
    }

    public Validation<Validation.Input> getValidation() {
        return validation;
    }

    public void validate(Validation.Response response) {
        validation.setValid(true);
        for (Map.Entry<Validation.Input, Validation.Wrapper> errorMap : validation.getWrapperMap().entrySet()) {
            Validation.Wrapper wrapper = errorMap.getValue();
            wrapper.setErrorMessage(null);
            switch (errorMap.getKey()) {
                case REQUIRED:
                    requiredError(wrapper);
                    break;
                case MAX:
                    maxError(wrapper);
                    break;
                case MIN:
                    minError(wrapper);
                    break;
                case EMAIL:
                    emailError(wrapper);
                    break;
            }

            if (wrapper.isError()) {
                response.error(wrapper);
                validation.setValid(false);

                if(errorSymbolEnabled){
                    setError(wrapper.getErrorMessage());
                }
            }
        }
    }

    private void init() {
        validation = new Validation<>(this);
    }

    private void requiredError(Validation.Wrapper wrapper){
        if (getTextString().length() == 0) {
            wrapper.setErrorMessage(validation.getErrorMessage(wrapper, R.string.error_required));
        }
    }

    private void maxError(Validation.Wrapper wrapper){
        int max = (int) wrapper.getReason();
        if (getTextString().length() > max) {
            wrapper.setErrorMessage(validation.getErrorMessage(wrapper, R.string.error_max, max));
        }
    }

    private void minError(Validation.Wrapper wrapper) {
        int min = (int) wrapper.getReason();
        if (getTextString().length() < min) {
            wrapper.setErrorMessage(validation.getErrorMessage(wrapper, R.string.error_min, min));
        }
    }

    private void emailError(Validation.Wrapper wrapper) {
        String reason = (String) wrapper.getReason();
        if (reason == null) {
            if (!Patterns.EMAIL_ADDRESS.matcher(getTextString()).matches()) {
                wrapper.setErrorMessage(validation.getErrorMessage(wrapper, R.string.error_email_match));
            }
        } else {
            if (reason.matches(getTextString())) {
                wrapper.setErrorMessage(validation.getErrorMessage(wrapper, R.string.error_email_match));
            }
        }
    }
}
