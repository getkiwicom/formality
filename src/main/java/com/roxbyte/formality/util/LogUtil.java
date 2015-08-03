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

import android.util.Log;

public final class LogUtil {

    private static final String TAG = "Formality";
    private static final boolean DEBUG = true;

    public static void warning(String message){
        if(DEBUG){
            Log.w(LogUtil.concatenateTag(null), message);
        }
    }

    public static void warning(String message, String suffix){
        if(DEBUG){
            Log.w(LogUtil.concatenateTag(suffix), message);
        }
    }

    public static void error(String message){
        if(DEBUG){
            Log.e(LogUtil.concatenateTag(null), message);
        }
    }

    public static void error(String message, String suffix){
        if(DEBUG){
            Log.e(LogUtil.concatenateTag(suffix), message);
        }
    }


    private static String concatenateTag(String suffix){
        if(suffix != null){
            return TAG + " --- " + suffix;
        }

        return TAG;
    }

    private LogUtil() {

    }
}
