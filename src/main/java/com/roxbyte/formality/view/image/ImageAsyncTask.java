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

package com.roxbyte.formality.view.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

    private ImageView imageView;
    private Listener listener;

    public ImageAsyncTask(ImageView imageView) {
        this(imageView, null);
    }

    public ImageAsyncTask(ImageView imageView, Listener listener) {
        this.listener = listener;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];

        try {
            InputStream inputStream = new URL(url).openStream();
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            Log.e("ImageTask", "Load image asynchronously error.", e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
        if (listener != null) {
            listener.onLoad(imageView);
        }
    }

    public interface Listener {

        void onLoad(ImageView imageView);

    }
}
