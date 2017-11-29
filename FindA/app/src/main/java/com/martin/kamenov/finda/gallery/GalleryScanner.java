package com.martin.kamenov.finda.gallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.martin.kamenov.finda.textEditor.TextEditorActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Martin on 29.11.2017 г..
 */

public class GalleryScanner {
    private final Activity activity;
    private ImageView pictureOcrView;

    public GalleryScanner(Activity activity) {
        this.activity = activity;
    }

    public void useImage(Uri uri)
    {

        Bitmap photo = decodeUriToBitmap(activity, uri);
        if(photo==null) {
            Intent intent = new Intent(activity, TextEditorActivity.class);
            intent.putExtra("foundText", "No text");
            activity.startActivity(intent);
            return;
        }
        pictureOcrView = new ImageView(activity.getApplicationContext());
        pictureOcrView.setImageBitmap(photo);
        Context context = activity.getApplicationContext();
        TextRecognizer ocrFrame = new TextRecognizer.Builder(context).build();
        Frame frame = new Frame.Builder().setBitmap(photo).build();
        if (ocrFrame.isOperational()){
        }
        SparseArray<TextBlock> textBlocks = ocrFrame.detect(frame);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < textBlocks.size(); i++) {
            TextBlock textBlock = textBlocks.get(textBlocks.keyAt(i));
            stringBuilder.append(textBlock.getValue().toString());
            stringBuilder.append('\n');

        }

        if(stringBuilder.toString().length()>0) {
            Toast.makeText(activity, stringBuilder.toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(activity, "No text was found.", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(activity, TextEditorActivity.class);
        intent.putExtra("foundText", stringBuilder.toString());
        activity.startActivity(intent);
    }

    private static Bitmap decodeUriToBitmap(Context mContext, Uri sendUri) {
        Bitmap getBitmap = null;
        try {
            InputStream image_stream;
            try {
                image_stream = mContext.getContentResolver().openInputStream(sendUri);
                getBitmap = BitmapFactory.decodeStream(image_stream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getBitmap;
    }
}
