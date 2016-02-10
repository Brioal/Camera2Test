package com.brioal.camera2test;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private File imageFile ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Camera功能的实现", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void startSystem(View view) {
        File external = Environment.getExternalStorageDirectory();
        try {
             imageFile = new File(external.getCanonicalPath() + "/image.jpg");
            Uri uri = Uri.fromFile(imageFile);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageURI(Uri.fromFile(imageFile));
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            builder.setView(imageView);
            builder.create();
            builder.show();
        }
    }

    public void startCustom(View view) {
        startActivity(new Intent(MainActivity.this,Camera2Test.class));
    }
}
