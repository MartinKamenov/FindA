package com.example.martin.finda.gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.martin.finda.R;
import com.example.martin.finda.textEditor.TextEditorActivity;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class GalleryActivity extends AppCompatActivity {

    private static final int SELECT_PHOTO = 1;
    private ImageView imageView;
    private ImageView pictureOcrView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SELECT_PHOTO) {

            if (resultCode == RESULT_OK) {
                if (intent != null) {
                    // Get the URI of the selected file
                    final Uri uri = intent.getData();
                    useImage(uri);
                }
            }
            super.onActivityResult(requestCode, resultCode, intent);

        }
    }

    void useImage(Uri uri)
    {

        Bitmap photo = decodeUriToBitmap(getApplicationContext(), uri);
        if(photo==null) {
            Intent intent = new Intent(this, TextEditorActivity.class);
            intent.putExtra("foundText", "No text");
            startActivity(intent);
        }
        pictureOcrView = new ImageView(getApplicationContext());
        pictureOcrView.setImageBitmap(photo);
        Context context = getApplicationContext();
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

        Toast.makeText(GalleryActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, TextEditorActivity.class);
        intent.putExtra("foundText", stringBuilder.toString());
        startActivity(intent);
    }

    public static Bitmap decodeUriToBitmap(Context mContext, Uri sendUri) {
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
