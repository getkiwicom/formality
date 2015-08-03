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

public class GroupLinearLayout extends DefaultLinearLayout{

    public GroupLinearLayout(Context context) {
        super(context);
        defaultStyle();
    }

    public GroupLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View view) {
        if(!(view instanceof Group)){
            throw new RuntimeException("View must be implemented by GroupSelector interface.");
        }

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();

                Group group = (Group) view;
                group.style();
                group.onClick(view);
            }
        });

        super.addView(view);
    }

    public interface Group {

        void style();

        void clear();

        void onClick(View view);

    }

    private void clear(){
        for(int i = 0; i < getChildCount(); i++){
            Group group = (Group) getChildAt(i);
            group.clear();
        }
    }
}
