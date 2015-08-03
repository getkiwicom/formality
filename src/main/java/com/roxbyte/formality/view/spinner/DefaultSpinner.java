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

package com.roxbyte.formality.view.spinner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.roxbyte.formality.R;
import com.roxbyte.formality.form.Validation;
import com.roxbyte.formality.util.FormUtil;
import com.roxbyte.formality.view.Layout;
import com.roxbyte.formality.view.text.DefaultTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DefaultSpinner extends Spinner implements Layout{

    private Validation<Validation.Selection> validation;

    public DefaultSpinner(Context context) {
        super(context);
        init();
    }

    public DefaultSpinner(Context context, int mode) {
        super(context, mode);
        init();
    }

    public DefaultSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DefaultSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DefaultSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
        init();
    }

    public void initAdapter(String... items){
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, items);

        setAdapter(itemAdapter);
    }

    public void initCustomAdapter(String hint, String... items){
        initCustomAdapter(hint, null, items);
    }

    public void initCustomAdapter(String hint, CustomizeSpinner customizeSpinner, String... items){
        List<String> itemList = new ArrayList<>(Arrays.asList(items));
        itemList.add(hint);

        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(getContext(), R.layout.view_spinner_item, itemList);
        spinnerAdapter.setCustomizeSpinner(customizeSpinner);
        setAdapter(spinnerAdapter);
        setSelection(getCount());
    }

    public Validation<Validation.Selection> getValidation() {
        return validation;
    }

    public void validate(Validation.Response response) {
        validation.setValid(true);
        for (Map.Entry<Validation.Selection, Validation.Wrapper> errorMap : validation.getWrapperMap().entrySet()) {
            Validation.Wrapper wrapper = errorMap.getValue();
            wrapper.setErrorMessage(null);
            switch (errorMap.getKey()) {
                case REQUIRED:
                    requiredError(wrapper);
                    break;
            }

            if (wrapper.isError()) {
                response.error(wrapper);
                validation.setValid(false);
            }
        }
    }

    public String getSelectedDisplayName(){
        return String.valueOf(getSelectedItem());
    }

    @Override
    public void setMarginDp(int left, int top, int right, int bottom) {
        setLayoutParams(FormUtil.getMarginLinearDp(left, top, right, bottom, getResources()));
    }

    public interface CustomizeSpinner{

        void style(DefaultTextView defaultTextView);

    }

    private void requiredError(Validation.Wrapper wrapper){
        if (getSelectedItemPosition() == getAdapter().getCount()) {
            wrapper.setErrorMessage(validation.getErrorMessage(wrapper, R.string.error_required));
        }
    }

    private class SpinnerAdapter extends ArrayAdapter<String>{

        private CustomizeSpinner customizeSpinner;

        public SpinnerAdapter(Context context, int resource, List<String> strings) {
            super(context, resource, strings);
        }

        public SpinnerAdapter(Context context, int resource, int textViewResourceId, List<String> strings) {
            super(context, resource, textViewResourceId, strings);
        }

        public void setCustomizeSpinner(CustomizeSpinner customizeSpinner) {
            this.customizeSpinner = customizeSpinner;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DefaultTextView defaultTextView = (DefaultTextView) super.getView(position, convertView, parent);
            if(customizeSpinner != null){
                customizeSpinner.style(defaultTextView);
            }
            return defaultTextView;
        }

        @Override
        public int getCount() {
            int count = super.getCount();
            return count > 0 ? count - 1 : count;
        }

    }

    private void init(){
        validation = new Validation<>(this);
    }
}
