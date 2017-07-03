package i.iot_project_receiptapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.jhuster.imagecropper.CropIntent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by tsaiyunyun on 2017/6/4.
 */

public class TesseractActivity extends AppCompatActivity{

    public static final String CROPPED_IMAGE_FILEPATH = "/sdcard/cropped.jpg";

    private ImageView mImageView;
    //Bitmap image;
    private TessBaseAPI mTess;
    String datapath = "";
    private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tesseract);
        mImageView = (ImageView)findViewById(R.id.CroppedImageView);
    }

    public void onClickButton(View v) {
        startCropImage();
    }

    private void startCropImage() {


        // Create a CropIntent
        CropIntent intent = new CropIntent();

        // Set the source image filepath/URL and output filepath/URL (Optional)
        //intent.setImagePath("/sdcard/source.jpg");
        intent.setOutputPath(CROPPED_IMAGE_FILEPATH);

        // Set a fixed crop window size (Optional)
        //intent.setOutputSize(640,480);

        // set the max crop window size (Optional)
        //intent.setMaxOutputSize(800,600);

        // Set a fixed crop window's width/height aspect (Optional)
        //intent.setAspect(3,2);

        // start ImageCropper activity with certain request code and listen for result
        startActivityForResult(intent.getIntent(this), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 0) {
            Uri croppedUri = data.getExtras().getParcelable(MediaStore.EXTRA_OUTPUT);
            try {
                InputStream in = getContentResolver().openInputStream(croppedUri);
                Bitmap image = BitmapFactory.decodeStream(in);
                //mImageView.setImageBitmap(image);

                // Toast.makeText(this,"Crop success，saved at"+CROPPED_IMAGE_FILEPATH,Toast.LENGTH_LONG).show();
                String language = "eng";
                datapath = getFilesDir()+ "/tesseract/";
                //Toast.makeText(this,datapath,Toast.LENGTH_LONG).show();

                mTess = new TessBaseAPI();

                checkFile(new File(datapath + "tessdata/"));
                mTess.setPageSegMode(TessBaseAPI.PageSegMode.PSM_OSD_ONLY);
                mTess.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST,"0123456789");
                mTess.setVariable(TessBaseAPI.VAR_CHAR_BLACKLIST,"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmopqrstuvwxyz");
                mTess.init(datapath, language);
                String OCRresult = null;
                mTess.setImage(image);
                OCRresult = mTess.getUTF8Text();
                Toast.makeText(this,"Receipt number is "+mTess.getUTF8Text(),Toast.LENGTH_LONG).show();
                TextView OCRTextView = (TextView) findViewById(R.id.OCRTextView);
                OCRTextView.setText(OCRresult);


            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);

    }
    /* public void processImage(View view){

         String OCRresult = null;
         mTess.setImage(image);
         OCRresult = mTess.getUTF8Text();
         //Toast.makeText(this,"Receipt number is"+mTess.getUTF8Text(),Toast.LENGTH_LONG).show();
         TextView OCRTextView = (TextView) findViewById(R.id.OCRTextView);
         OCRTextView.setText(OCRresult);
     }
 */
    private void checkFile(File dir) {
        if (!dir.exists()&& dir.mkdirs()){
            copyFiles();
        }
        if(dir.exists()) {
            String datafilepath = datapath+ "/tessdata/eng.traineddata";
            File datafile = new File(datafilepath);

            if (!datafile.exists()) {
                copyFiles();
            }
        }
    }

    private void copyFiles() {
        try {
            String filepath = datapath + "/tessdata/eng.traineddata";
            AssetManager assetManager = getAssets();

            InputStream instream = assetManager.open("tessdata/eng.traineddata");
            OutputStream outstream = new FileOutputStream(filepath);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }


            outstream.flush();
            outstream.close();
            instream.close();

            File file = new File(filepath);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
