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

import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public final class FormUtil {

    public static LinearLayout.LayoutParams getMarginLinearDp(int left, int top, int right, int bottom, Resources resources){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(convertToPx(left, resources), convertToPx(top, resources),
                convertToPx(right, resources), convertToPx(bottom, resources));

        return layoutParams;
    }

    public static RelativeLayout.LayoutParams getMarginRelativeDp(int left, int top, int right, int bottom, Resources resources){
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(convertToPx(left, resources), convertToPx(top, resources),
                convertToPx(right, resources), convertToPx(bottom, resources));

        return layoutParams;
    }

    public static int convertToPx(int dp, Resources resources){
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return px;
    }

    public static void keyIterator(JSONObject jsonObject, Traverse traverse) throws JSONException {
        for(Iterator<String> iterator = jsonObject.keys(); iterator.hasNext();){
            String key = iterator.next();
            traverse.customizeJsonObject(jsonObject, key);
        }
    }

    public interface Traverse{
        
        void customizeJsonObject(JSONObject jsonObject, String key) throws JSONException;
        
    }
}
