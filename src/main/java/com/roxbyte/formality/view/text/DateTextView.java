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

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

import com.roxbyte.formality.R;
import com.roxbyte.formality.form.Validation;
import com.roxbyte.formality.util.TimeUtil;
import com.roxbyte.formality.view.date.DateDialog;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public class DateTextView extends DefaultTextView implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private static final String REASON_DATE = "Reason must be a valid Date instance.";

    private Validation<Validation.Date> validation;

    private DateDialog dateDialog;
    private Date dateCurrent;
    private boolean dateSelected;

    public DateTextView(Context context) {
        super(context, null, android.R.attr.spinnerStyle);
        setPadding(15, 15, 50, 15);
        init();
    }

    public DateTextView(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.spinnerStyle);
        init();
    }

    public DateTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void onClick(View view) {
        dateDialog.show();
    }

    public Validation<Validation.Date> getValidation() {
        return validation;
    }

    public Date getDateCurrent() {
        return dateCurrent;
    }

    public void setTextDate(String dateString, String pattern){
        try {
            dateCurrent = TimeUtil.getSimpleDateFormat(pattern).parse(dateString);
            setText(TimeUtil.dateStringLong(dateCurrent.getTime()));
            dateDialog.setDate(dateCurrent);
            dateSelected = true;
        } catch (ParseException e) {
            setText(dateString);
            dateSelected = false;
            e.printStackTrace();
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        dateCurrent = TimeUtil.dateByNumbers(year, month, day);
        dateSelected = true;
        setText(TimeUtil.dateStringLong(dateCurrent.getTime()));
    }

    public void validate(Validation.Response response) {
        validation.setValid(true);
        for (Map.Entry<Validation.Date, Validation.Wrapper> errorMap : validation.getWrapperMap().entrySet()) {
            Validation.Wrapper wrapper = errorMap.getValue();
            wrapper.setErrorMessage(null);
            switch (errorMap.getKey()) {
                case REQUIRED:
                    requiredError(wrapper);
                    break;
                case MIN:
                    minError(wrapper);
                    break;
                case MAX:
                    maxError(wrapper);
                    break;
            }

            if (wrapper.isError()) {
                response.error(wrapper);
                validation.setValid(false);
            }
        }
    }

    private boolean isRequired(Validation.Wrapper wrapper){
        requiredError(wrapper);
        return wrapper.isSuccess();
    }

    private void requiredError(Validation.Wrapper wrapper){
        if (!dateSelected) {
            wrapper.setErrorMessage(validation.getErrorMessage(wrapper, R.string.error_required));
        }
    }

    private void minError(Validation.Wrapper wrapper) {
        if(isRequired(wrapper)){
            if(!(wrapper.getReason() instanceof Date)){
                throw new RuntimeException(REASON_DATE);
            }

            Date dateMin = (Date) wrapper.getReason();
            if(TimeUtil.isDateBefore(dateMin, dateCurrent)){
                wrapper.setErrorMessage(validation.getErrorMessage(wrapper, 0));
            }
        }
    }

    private void maxError(Validation.Wrapper wrapper) {
        if(!(wrapper.getReason() instanceof Date)){
            throw new RuntimeException(REASON_DATE);
        }

        Date dateMax = (Date) wrapper.getReason();
        if(TimeUtil.isDateAfter(dateMax, dateCurrent)){
            wrapper.setErrorMessage(validation.getErrorMessage(wrapper, 0));
        }
    }

    private void init() {
        validation = new Validation<>(this);
        dateDialog = new DateDialog(getContext(), this);
        setOnClickListener(this);
    }
}
