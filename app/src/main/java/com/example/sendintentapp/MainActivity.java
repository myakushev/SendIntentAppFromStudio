package com.example.sendintentapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ResourceCursorAdapter;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "hello!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonMail();
        buttonWeb();
        buttonMap();
        buttonCall();
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = findViewById(R.id.send_text);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    private void buttonCall() {
        Button buttonCall = findViewById(R.id.button_call);
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse("tel: ");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                if (isIntentSafe(callIntent))
                    startActivity(callIntent);
                else
                    Toast.makeText(getApplicationContext(), "Your phone doesn's have app for dial calls!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buttonMap() {
        Button buttonMap = findViewById(R.id.button_map);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri location = Uri.parse("geo: 37.422219, -122.08364?z=14");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                if (isIntentSafe(mapIntent))
                    startActivity(mapIntent);
                else
                    Toast.makeText(getApplicationContext(), "Your phone doesn's have app for showing map!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buttonWeb() {
        Button buttonWeb = findViewById(R.id.button_web);
        buttonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webPage = Uri.parse("http://vk.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
                if (isIntentSafe(webIntent))
                    startActivity(webIntent);
                else
                    Toast.makeText(getApplicationContext(), "Your phone doesn's have app for web surfing!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buttonMail() {
        Button buttonMail = findViewById(R.id.button_mail);
        buttonMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "myakushev1@ya.ru", null));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"muakushev@yahoo.com", "raspil-spb@yandex.ru"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Send intent message");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Sent email via Android App");
                if (isIntentSafe(emailIntent))
                    startActivity(emailIntent);
                else
                    Toast.makeText(getApplicationContext(), "Your phone doesn's have app for email sending!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isIntentSafe(Intent intent) {
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        return activities.size() > 0;
    }
}
