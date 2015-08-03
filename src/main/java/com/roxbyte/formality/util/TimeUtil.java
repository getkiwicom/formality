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

package com.roxbyte.formality.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class TimeUtil {

    public static String dateString(long millis, String datePattern){
        String formattedString = getSimpleDateFormat(datePattern).format(new Date(millis));
        return formattedString;
    }

    public static String dateStringLong(long millis){
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        String shortDate = dateFormat.format(new Date(millis));
        return shortDate;
    }

    public static Date dateByNumbers(int year, int month, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return calendar.getTime();
    }

    public static boolean isDateBefore(Date dateOne, Date dateTwo){
        return dateOne.before(dateTwo);
    }

    public static boolean isDateAfter(Date dateOne, Date dateTwo){
        return dateOne.after(dateTwo);
    }

    public static SimpleDateFormat getSimpleDateFormat(String datePattern){
        return new SimpleDateFormat(datePattern, Locale.getDefault());
    }
}
