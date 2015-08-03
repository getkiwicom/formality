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

package com.roxbyte.formality.form;

import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class Validation<K> {

    public static final int DEFAULT_MIN = 8;

    private Map<K, Wrapper> wrapperMap;
    private boolean valid;
    private View view;

    public Validation(View view) {
        this.view = view;
        wrapperMap = new HashMap<>();
    }

    public void addValidation(K keyValidation) {
        addValidation(keyValidation, new Validation.Wrapper(null, 0, view));
    }

    public void addValidation(K keyValidation, Object reason) {
        addValidation(keyValidation, new Validation.Wrapper(reason, 0, view));
    }

    public void addValidation(K keyValidation, Object reason, int errorResourceId) {
        addValidation(keyValidation, new Validation.Wrapper(reason, errorResourceId, view));
    }

    public Map<K, Wrapper> getWrapperMap() {
        return wrapperMap;
    }

    public String getErrorMessage(Validation.Wrapper errorWrapper, int resourceId, Object... params) {
        if (errorWrapper.getResourceId() == 0) {
            return view.getResources().getString(resourceId, params);
        }

        return view.getResources().getString(errorWrapper.getResourceId());
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public enum Input{
        REQUIRED, MIN, MAX, EMAIL
    }

    public enum Selection{
        REQUIRED
    }

    public enum Date{
        REQUIRED, MIN, MAX
    }

    public interface Response{

        void success();

        void error(Wrapper wrapper);
    }

    public static class Wrapper {

        private Object reason;
        private int resourceId;
        private String errorMessage;
        private View view;

        public Wrapper(Object reason, int resourceId, View view) {
            this.reason = reason;
            this.resourceId = resourceId;
            this.view = view;
        }

        public Object getReason() {
            return reason;
        }

        public int getResourceId() {
            return resourceId;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public boolean isSuccess() {
            return !isError();
        }

        public boolean isError() {
            return errorMessage != null && errorMessage.trim().length() > 0;
        }

        public View getView() {
            return view;
        }
    }

    private void addValidation(K keyValidation, Wrapper wrapper){
        wrapperMap.put(keyValidation, wrapper);
    }
}
