package io.github.akndmr.aboutscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AboutScreenPersonalLayout aboutScreenPersonalLayout = (AboutScreenPersonalLayout) findViewById(R.id.about_screen_layout);

        aboutScreenPersonalLayout.setDescriptionText("this text is programmatically set");
        aboutScreenPersonalLayout.setWebsiteIcon(R.drawable.ic_website);
        aboutScreenPersonalLayout.setWebsiteIconTintColor(R.color.colorIconDefault);
        aboutScreenPersonalLayout.setGithubIconTintColor("#333");
        aboutScreenPersonalLayout.setProfilePhotoDrawable(R.drawable.profile_placeholder);
        aboutScreenPersonalLayout.setTextColor(R.color.colorIconDefault);
        aboutScreenPersonalLayout.showGooglePlayLink();

        ImageView iv = aboutScreenPersonalLayout.getProfilePhoto();
        Glide.with(this)
                .load(R.drawable.test_profile)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(iv);
    }

}
