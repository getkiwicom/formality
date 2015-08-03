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

package com.roxbyte.formality.view.date;

import android.app.DatePickerDialog;
import android.content.Context;

import java.util.Calendar;
import java.util.Date;

public class DateDialog {

    private Context context;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private DatePickerDialog datePickerDialog;

    public DateDialog(Context context, DatePickerDialog.OnDateSetListener onDateSetListener) {
        this.context = context;
        this.onDateSetListener = onDateSetListener;
        initToday();
    }

    public void initToday() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        initCustom(year, month, day);
    }

    public void initSubEighteen() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR) - 18;
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        initCustom(year, month, day);
    }

    public void setDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        datePickerDialog.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    public void initCustom(int year, int month, int day) {
        datePickerDialog = new DatePickerDialog(context, onDateSetListener, year, month, day);
    }

    public void show(){
        datePickerDialog.show();
    }
}
