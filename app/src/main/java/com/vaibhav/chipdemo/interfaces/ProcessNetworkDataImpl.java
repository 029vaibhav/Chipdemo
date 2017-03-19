package com.vaibhav.chipdemo.interfaces;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.vaibhav.chipdemo.R;

/**
 * Created by vaibhav on 19/3/17.
 */

public class ProcessNetworkDataImpl implements ProcessNetworkData<String> {

    @Override
    public void ProcessData(String s, Context context, View view) {

        {
            if (s.contains(",")) // check comman in string
            {

                SpannableStringBuilder ssb = new SpannableStringBuilder(s.replace(",", " "));
                // split string which comma
                String chips[] = s.trim().split(",");
                int x = 0;
                for (String c : chips) {
                    LayoutInflater lf = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                    TextView textView = (TextView) lf.inflate(R.layout.chips_edittext, null);
                    textView.setText(c); // set text

                    // capture bitmapt of genreated textview
                    int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                    textView.measure(spec, spec);
                    textView.layout(0, 0, textView.getMeasuredWidth(), textView.getMeasuredHeight());
                    Bitmap b = Bitmap.createBitmap(textView.getWidth(), textView.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(b);
                    canvas.translate(-textView.getScrollX(), -textView.getScrollY());
                    textView.draw(canvas);
                    textView.setDrawingCacheEnabled(true);
                    Bitmap cacheBmp = textView.getDrawingCache();
                    Bitmap viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
                    textView.destroyDrawingCache();  // destory drawable
                    // create bitmap drawable for imagespan
                    BitmapDrawable bmpDrawable = new BitmapDrawable(viewBmp);
                    bmpDrawable.setBounds(0, 0, bmpDrawable.getIntrinsicWidth(), bmpDrawable.getIntrinsicHeight());
                    // create and set imagespan
                    ssb.setSpan(new ImageSpan(bmpDrawable), x, x + c.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    x = x + c.length() + 1;
                }
                // set chips span
                MultiAutoCompleteTextView view1 = (MultiAutoCompleteTextView) view;
                view1.setText(ssb);

            }


        }

    }
}
