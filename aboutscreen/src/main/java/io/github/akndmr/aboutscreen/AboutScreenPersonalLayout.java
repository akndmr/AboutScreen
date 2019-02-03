package io.github.akndmr.aboutscreen;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akın DEMİR on 30.01.2019.
 */
public class AboutScreenPersonalLayout extends ConstraintLayout {

    public static final String BLANK_LINK = "blank_link";
    public static final String SPACE_CHAR = "\u00A0";
    public static final String STATE_ABOUT_DATA = "state_about_data";
    public static final String STATE_SUPER_CLASS = "state_super_class";
    public static final String STATE_IS_FROM_XML = "state_is_from_xml";
    public static final String STATE_IS_GOOGLE_PLAY_ENABLED = "state_is_google_play_enabled";
    public static final String STATE_LINK_WEBSITE = "state_link_website";
    public static final String STATE_LINK_GITHUB = "state_link_github";

    public static final String FONT_STYLE_BOLD = "BOLD";
    public static final String FONT_STYLE_ITALIC = "ITALIC";
    public static final String FONT_STYLE_NORMAL = "NORMAL";
    public static final String FONT_STYLE_BOLD_ITALIC = "BOLD_ITALIC";

    private LinearLayout mContainerWebsite, mContainerGithub, mContainerFacebook,
    mContainerTwitter, mContainerGooglePlay;

    private ImageView mImageViewProfilePhoto;

    private CardView mCardView;

    private TextView mName, mDescription, mWebsite, mGithub, mFacebook, mTwitter, mGooglePlay;
    private ImageView mWebsiteIcon, mGithubIcon, mFacebookIcon, mTwitterIcon, mGooglePlayIcon;

    private String mLinkWebsite, mLinkGithub, mLinkFacebook, mLinkTwitter, mLinkGooglePlay;
    private String mNameText, mDescriptionText;

    private String websiteText;
    private String githubText;
    private String facebookText;
    private String twitterText;
    private String googlePlayText;
    private String websiteUrl;
    private String githubUrl;
    private String facebookUrl;
    private String twitterUrl;

    private int mPhotoDrawableId, mWebsiteDrawableId, mGithubDrawableId, mFacebookDrawableId, mTwitterDrawableId, mGooglePlayDrawableId;
    private String mLinksColorHex, mWebsiteColorHex, mGithubColorHex, mFacebookColorHex, mTwitterColorHex, mGooglePlayColorHex;
    private String mWebsiteTextColorHex, mGithubTextColorHex, mFacebookTextColorHex, mTwitterTextColorHex, mGooglePlayTextColorHex;
    private String mNameTextColorHex, mDescriptionTextColorHex;
    private String mTextColorHex, mTextColorLinksHex;
    private int mLinksFontId, mNameFontId, mDescriptionFontId, mTextFontId;
    private String mLinksFontStyle, mNameFontStyle, mDescriptionFontStyle, mTextFontStyle;
    private int mCardViewRadius, mCardViewElevation;
    private String mCardViewBackgroundColor;

    private boolean isGooglePlayLinkAdded;

    public AboutScreenPersonalLayout(Context context) {
        super(context, null);
        initializeViews(context);
        bindViews();
        setListeners();
    }

    public AboutScreenPersonalLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        setupAttributes(attrs);
        initializeViews(context);
        bindViews();
        setListeners();
        setAttributesAndValues();
    }

    public AboutScreenPersonalLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setupAttributes(attrs);
        initializeViews(context);
        bindViews();
        setListeners();
        setAttributesAndValues();
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.about_screen_personal, this);
    }

    private void setupAttributes(AttributeSet attrs) {
        // Obtain a typed array of attributes
        TypedArray t = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.AboutScreenPersonal, 0, 0);
        // Extract custom attributes into member variables
        try {

            mCardViewRadius             = t.getDimensionPixelSize(R.styleable.AboutScreenPersonal_cardRadius,0);
            mCardViewElevation          = t.getDimensionPixelSize(R.styleable.AboutScreenPersonal_cardElevation,0);
            mCardViewBackgroundColor    = t.getString(R.styleable.AboutScreenPersonal_cardBackgroundColor);

            isGooglePlayLinkAdded       = t.getBoolean(R.styleable.AboutScreenPersonal_isGooglePlayLinkEnabled, false);

            mNameText                   = t.getString(R.styleable.AboutScreenPersonal_textName);
            mDescriptionText            = t.getString(R.styleable.AboutScreenPersonal_textDescription);
            websiteText                 = t.getString(R.styleable.AboutScreenPersonal_textWebsite);
            githubText                  = t.getString(R.styleable.AboutScreenPersonal_textGithub);
            facebookText                = t.getString(R.styleable.AboutScreenPersonal_textFacebook);
            twitterText                 = t.getString(R.styleable.AboutScreenPersonal_textTwitter);
            googlePlayText              = t.getString(R.styleable.AboutScreenPersonal_textGooglePlay);

            websiteUrl                  = t.getString(R.styleable.AboutScreenPersonal_urlWebsite);
            githubUrl                   = t.getString(R.styleable.AboutScreenPersonal_urlGithub);
            facebookUrl                 = t.getString(R.styleable.AboutScreenPersonal_urlFacebook);
            twitterUrl                  = t.getString(R.styleable.AboutScreenPersonal_urlTwitter);

            mPhotoDrawableId            = t.getResourceId(R.styleable.AboutScreenPersonal_profilePhoto, 0);
            mWebsiteDrawableId          = t.getResourceId(R.styleable.AboutScreenPersonal_iconWebsite, R.drawable.ic_website);
            mGithubDrawableId           = t.getResourceId(R.styleable.AboutScreenPersonal_iconGithub, R.drawable.ic_github);
            mFacebookDrawableId         = t.getResourceId(R.styleable.AboutScreenPersonal_iconFacebook, R.drawable.ic_facebook);
            mTwitterDrawableId          = t.getResourceId(R.styleable.AboutScreenPersonal_iconTwitter, R.drawable.ic_twitter);
            mGooglePlayDrawableId       = t.getResourceId(R.styleable.AboutScreenPersonal_iconGooglePlay, R.drawable.ic_rate_us);

            mLinksColorHex              = t.getString(R.styleable.AboutScreenPersonal_iconColor);
            mWebsiteColorHex            = t.getString(R.styleable.AboutScreenPersonal_iconColorWebsite);
            mGithubColorHex             = t.getString(R.styleable.AboutScreenPersonal_iconColorGithub);
            mFacebookColorHex           = t.getString(R.styleable.AboutScreenPersonal_iconColorFacebook);
            mTwitterColorHex            = t.getString(R.styleable.AboutScreenPersonal_iconColorTwitter);
            mGooglePlayColorHex         = t.getString(R.styleable.AboutScreenPersonal_iconColorGooglePlay);

            mTextColorHex               = t.getString(R.styleable.AboutScreenPersonal_textColor);
            mTextColorLinksHex          = t.getString(R.styleable.AboutScreenPersonal_textColorLinks);
            mWebsiteTextColorHex        = t.getString(R.styleable.AboutScreenPersonal_textColorWebsite);
            mGithubTextColorHex         = t.getString(R.styleable.AboutScreenPersonal_textColorGithub);
            mFacebookTextColorHex       = t.getString(R.styleable.AboutScreenPersonal_textColorFacebook);
            mTwitterTextColorHex        = t.getString(R.styleable.AboutScreenPersonal_textColorTwitter);
            mGooglePlayTextColorHex     = t.getString(R.styleable.AboutScreenPersonal_textColorGooglePlay);
            mNameTextColorHex           = t.getString(R.styleable.AboutScreenPersonal_textColorName);
            mDescriptionTextColorHex    = t.getString(R.styleable.AboutScreenPersonal_textColorDescription);

            mTextFontId                 = t.getResourceId(R.styleable.AboutScreenPersonal_textFontId, 0);
            mLinksFontId                = t.getResourceId(R.styleable.AboutScreenPersonal_textLinksFontId, 0);
            mNameFontId                 = t.getResourceId(R.styleable.AboutScreenPersonal_textNameFontId, 0);
            mDescriptionFontId          = t.getResourceId(R.styleable.AboutScreenPersonal_textDescriptionFontId, 0);

            mTextFontStyle              = t.getString(R.styleable.AboutScreenPersonal_textFontStyle);
            mLinksFontStyle             = t.getString(R.styleable.AboutScreenPersonal_textLinksFontStyle);
            mNameFontStyle              = t.getString(R.styleable.AboutScreenPersonal_textNameFontStyle);
            mDescriptionFontStyle       = t.getString(R.styleable.AboutScreenPersonal_textDescriptionFontStyle);

        } finally {
            // TypedArray objects are shared and must be recycled.
            t.recycle();
        }
    }

    private void bindViews(){
        mCardView               = this.findViewById(R.id.akn_profile_cardview);
        mContainerWebsite       = this.findViewById(R.id.akn_ll_website_container);
        mContainerGithub        = this.findViewById(R.id.akn_ll_github_container);
        mContainerFacebook      = this.findViewById(R.id.akn_ll_facebook_container);
        mContainerTwitter       = this.findViewById(R.id.akn_ll_twitter_container);
        mContainerGooglePlay    = this.findViewById(R.id.akn_ll_rate_us_container);

        mName                   = this.findViewById(R.id.tv_profile_name);
        mDescription            = this.findViewById(R.id.tv_profile_description);
        mWebsite                = this.findViewById(R.id.tv_website);
        mGithub                 = this.findViewById(R.id.tv_github);
        mFacebook               = this.findViewById(R.id.tv_facebook);
        mTwitter                = this.findViewById(R.id.tv_twitter);
        mGooglePlay             = this.findViewById(R.id.tv_rate_us);

        mImageViewProfilePhoto  = this.findViewById(R.id.iv_profile_image);
        mWebsiteIcon            = this.findViewById(R.id.iv_icon_website);
        mGithubIcon             = this.findViewById(R.id.iv_icon_github);
        mFacebookIcon           = this.findViewById(R.id.iv_icon_facebook);
        mTwitterIcon            = this.findViewById(R.id.iv_icon_twitter);
        mGooglePlayIcon         = this.findViewById(R.id.iv_icon_rate_us);
    }

    private void setListeners() {
        // Listeners
        mContainerWebsite.setOnClickListener(mOnClickListener);
        mContainerGithub.setOnClickListener(mOnClickListener);
        mContainerFacebook.setOnClickListener(mOnClickListener);
        mContainerTwitter.setOnClickListener(mOnClickListener);
        mContainerGooglePlay.setOnClickListener(mOnClickListener);
    }

    private void setAttributesAndValues(){
        // Set cardview attributes
        if(mCardViewRadius != 0){
            mCardView.setRadius(mCardViewRadius);
        }
        if(mCardViewElevation != 0){
            mCardView.setCardElevation(mCardViewElevation);
        }
        if(mCardViewBackgroundColor != null){
            mCardView.setCardBackgroundColor(Color.parseColor(mCardViewBackgroundColor));
        }

        // Set text values
        if(mNameText != null){
            mName.setVisibility(VISIBLE);
            mName.setText(mNameText);
        }
        if(mDescriptionText != null){
            mDescription.setVisibility(VISIBLE);
            mDescription.setText(mDescriptionText);
        }

        if(websiteText != null){
            mWebsite.setText(websiteText);
        }
        if(githubText != null){
            mGithub.setText(githubText);
        }
        if(facebookText != null){
            mFacebook.setText(facebookText);
        }
        if(twitterText != null){
            mTwitter.setText(twitterText);
        }
        if(googlePlayText != null){
            mGooglePlay.setText(googlePlayText);
        }

        // Set image resources
        if(mPhotoDrawableId != 0){
            mImageViewProfilePhoto.setVisibility(VISIBLE);
            mImageViewProfilePhoto.setImageResource(mPhotoDrawableId);
        }

        if(websiteUrl != null){
            mContainerWebsite.setVisibility(VISIBLE);
            mLinkWebsite = websiteUrl;
            mWebsiteIcon.setImageResource(mWebsiteDrawableId);
        }
        if(githubUrl != null){
            mContainerGithub.setVisibility(VISIBLE);
            mLinkGithub = githubUrl;
            mGithubIcon.setImageResource(mGithubDrawableId);
        }
        if(facebookUrl != null){
            mContainerFacebook.setVisibility(VISIBLE);
            mLinkFacebook = facebookUrl;
            mFacebookIcon.setImageResource(mFacebookDrawableId);
        }
        if(twitterUrl != null){
            mContainerTwitter.setVisibility(VISIBLE);
            mLinkTwitter = twitterUrl;
            mTwitterIcon.setImageResource(mTwitterDrawableId);
        }
        if(isGooglePlayLinkAdded){
            mContainerGooglePlay.setVisibility(VISIBLE);
            mGooglePlayIcon.setImageResource(mGooglePlayDrawableId);
        }

        // Set icon tint colors
        if(mLinksColorHex != null){
            setIconTintColor(mWebsiteIcon, mLinksColorHex);
            setIconTintColor(mGithubIcon, mLinksColorHex);
            setIconTintColor(mFacebookIcon, mLinksColorHex);
            setIconTintColor(mTwitterIcon, mLinksColorHex);
            setIconTintColor(mGooglePlayIcon, mLinksColorHex);
        }
        else{
            if(mWebsiteColorHex != null)
                setIconTintColor(mWebsiteIcon, mWebsiteColorHex);
            if(mGithubColorHex != null)
                setIconTintColor(mGithubIcon, mGithubColorHex);
            if(mFacebookColorHex != null)
                setIconTintColor(mFacebookIcon, mFacebookColorHex);
            if(mTwitterColorHex != null)
                setIconTintColor(mTwitterIcon, mTwitterColorHex);
            if(mGooglePlayColorHex != null)
                setIconTintColor(mGooglePlayIcon, mGooglePlayColorHex);
        }


        // Set text colors
        if(mTextColorHex != null){
            List<TextView> textViewList = new ArrayList<>();
            textViewList.add(mName);
            textViewList.add(mDescription);
            textViewList.add(mWebsite);
            textViewList.add(mGithub);
            textViewList.add(mFacebook);
            textViewList.add(mTwitter);
            textViewList.add(mGooglePlay);

            setAllTextViewsColor(textViewList, mTextColorHex);
        }
        else{
            if(mNameTextColorHex != null)
                setTextViewColor(mName, mNameTextColorHex);
            if(mDescriptionTextColorHex != null)
                setTextViewColor(mDescription, mDescriptionTextColorHex);
            if(mTextColorLinksHex != null){
                List<TextView> textViewLinksList = new ArrayList<>();
                textViewLinksList.add(mWebsite);
                textViewLinksList.add(mGithub);
                textViewLinksList.add(mFacebook);
                textViewLinksList.add(mTwitter);
                textViewLinksList.add(mGooglePlay);

                setAllTextViewsColor(textViewLinksList, mTextColorLinksHex);
            }
            else{
                if(mWebsiteTextColorHex != null)
                    setTextViewColor(mWebsite, mWebsiteTextColorHex);
                if(mGithubTextColorHex != null)
                    setTextViewColor(mGithub, mGithubTextColorHex);
                if(mFacebookTextColorHex != null)
                    setTextViewColor(mFacebook, mFacebookTextColorHex);
                if(mTwitterTextColorHex != null)
                    setTextViewColor(mTwitter, mTwitterTextColorHex);
                if(mGooglePlayTextColorHex != null)
                    setTextViewColor(mGooglePlay, mGooglePlayTextColorHex);
            }

        }


        // Set fonts
        if(mTextFontId != 0){
            mName.setTypeface(ResourcesCompat.getFont(getContext(), mTextFontId));
            mDescription.setTypeface(ResourcesCompat.getFont(getContext(), mTextFontId));
            mWebsite.setTypeface(ResourcesCompat.getFont(getContext(), mTextFontId));
            mGithub.setTypeface(ResourcesCompat.getFont(getContext(), mTextFontId));
            mFacebook.setTypeface(ResourcesCompat.getFont(getContext(), mTextFontId));
            mTwitter.setTypeface(ResourcesCompat.getFont(getContext(), mTextFontId));
            mGooglePlay.setTypeface(ResourcesCompat.getFont(getContext(), mTextFontId));
        }
        else{
            if(mLinksFontId != 0){
                mWebsite.setTypeface(ResourcesCompat.getFont(getContext(), mLinksFontId));
                mGithub.setTypeface(ResourcesCompat.getFont(getContext(), mLinksFontId));
                mFacebook.setTypeface(ResourcesCompat.getFont(getContext(), mLinksFontId));
                mTwitter.setTypeface(ResourcesCompat.getFont(getContext(), mLinksFontId));
                mGooglePlay.setTypeface(ResourcesCompat.getFont(getContext(), mLinksFontId));
            }

            if(mNameFontId != 0){
                mName.setTypeface(ResourcesCompat.getFont(getContext(), mNameFontId));
            }

            if(mDescriptionFontId != 0){
                mDescription.setTypeface(ResourcesCompat.getFont(getContext(), mDescriptionFontId));
            }
        }


        // Set font styles
        if(mTextFontStyle != null){ // all texts
            switch (mTextFontStyle) {
                case FONT_STYLE_NORMAL:
                    mName.setTypeface(mName.getTypeface(), Typeface.NORMAL);
                    mDescription.setTypeface(mDescription.getTypeface(), Typeface.NORMAL);
                    mWebsite.setTypeface(mWebsite.getTypeface(), Typeface.NORMAL);
                    mGithub.setTypeface(mGithub.getTypeface(), Typeface.NORMAL);
                    mFacebook.setTypeface(mFacebook.getTypeface(), Typeface.NORMAL);
                    mTwitter.setTypeface(mTwitter.getTypeface(), Typeface.NORMAL);
                    mGooglePlay.setTypeface(mGooglePlay.getTypeface(), Typeface.NORMAL);
                    break;
                case FONT_STYLE_ITALIC:
                    mName.setTypeface(mName.getTypeface(), Typeface.ITALIC);
                    mDescription.setTypeface(mDescription.getTypeface(), Typeface.ITALIC);
                    mWebsite.setTypeface(mWebsite.getTypeface(), Typeface.ITALIC);
                    mGithub.setTypeface(mGithub.getTypeface(), Typeface.ITALIC);
                    mFacebook.setTypeface(mFacebook.getTypeface(), Typeface.ITALIC);
                    mTwitter.setTypeface(mTwitter.getTypeface(), Typeface.ITALIC);
                    mGooglePlay.setTypeface(mGooglePlay.getTypeface(), Typeface.ITALIC);
                    break;
                case FONT_STYLE_BOLD:
                    mName.setTypeface(mName.getTypeface(), Typeface.BOLD);
                    mDescription.setTypeface(mDescription.getTypeface(), Typeface.BOLD);
                    mWebsite.setTypeface(mWebsite.getTypeface(), Typeface.BOLD);
                    mGithub.setTypeface(mGithub.getTypeface(), Typeface.BOLD);
                    mFacebook.setTypeface(mFacebook.getTypeface(), Typeface.BOLD);
                    mTwitter.setTypeface(mTwitter.getTypeface(), Typeface.BOLD);
                    mGooglePlay.setTypeface(mGooglePlay.getTypeface(), Typeface.BOLD);
                    break;
                case FONT_STYLE_BOLD_ITALIC:
                    String nameText         = mName.getText().toString() + SPACE_CHAR;
                    String descriptionText  = mDescription.getText().toString() + SPACE_CHAR;
                    String websiteText = mWebsite.getText().toString() + SPACE_CHAR;
                    String githubText = mGithub.getText().toString() + SPACE_CHAR;
                    String facebookText = mFacebook.getText().toString() + SPACE_CHAR;
                    String twitterText = mTwitter.getText().toString() + SPACE_CHAR;
                    String googlePlayText = mGooglePlay.getText().toString() + SPACE_CHAR;

                    mName.setText(nameText);
                    mDescription.setText(descriptionText);
                    mWebsite.setText(websiteText);
                    mGithub.setText(githubText);
                    mFacebook.setText(facebookText);
                    mTwitter.setText(twitterText);
                    mGooglePlay.setText(googlePlayText);

                    mName.setTypeface(mName.getTypeface(), Typeface.BOLD_ITALIC);
                    mDescription.setTypeface(mDescription.getTypeface(), Typeface.BOLD_ITALIC);
                    mWebsite.setTypeface(mWebsite.getTypeface(), Typeface.BOLD_ITALIC);
                    mGithub.setTypeface(mGithub.getTypeface(), Typeface.BOLD_ITALIC);
                    mFacebook.setTypeface(mFacebook.getTypeface(), Typeface.BOLD_ITALIC);
                    mTwitter.setTypeface(mTwitter.getTypeface(), Typeface.BOLD_ITALIC);
                    mGooglePlay.setTypeface(mGooglePlay.getTypeface(), Typeface.BOLD_ITALIC);

                    invalidate();
                    break;
            }
        }
        else{
            if(mLinksFontStyle != null){ // links text
                switch (mLinksFontStyle) {
                    case FONT_STYLE_NORMAL:
                        mWebsite.setTypeface(mWebsite.getTypeface(), Typeface.NORMAL);
                        mGithub.setTypeface(mGithub.getTypeface(), Typeface.NORMAL);
                        mFacebook.setTypeface(mFacebook.getTypeface(), Typeface.NORMAL);
                        mTwitter.setTypeface(mTwitter.getTypeface(), Typeface.NORMAL);
                        mGooglePlay.setTypeface(mGooglePlay.getTypeface(), Typeface.NORMAL);
                        break;
                    case FONT_STYLE_ITALIC:
                        mWebsite.setTypeface(mWebsite.getTypeface(), Typeface.ITALIC);
                        mGithub.setTypeface(mGithub.getTypeface(), Typeface.ITALIC);
                        mFacebook.setTypeface(mFacebook.getTypeface(), Typeface.ITALIC);
                        mTwitter.setTypeface(mTwitter.getTypeface(), Typeface.ITALIC);
                        mGooglePlay.setTypeface(mGooglePlay.getTypeface(), Typeface.ITALIC);
                        break;
                    case FONT_STYLE_BOLD:
                        mWebsite.setTypeface(mWebsite.getTypeface(), Typeface.BOLD);
                        mGithub.setTypeface(mGithub.getTypeface(), Typeface.BOLD);
                        mFacebook.setTypeface(mFacebook.getTypeface(), Typeface.BOLD);
                        mTwitter.setTypeface(mTwitter.getTypeface(), Typeface.BOLD);
                        mGooglePlay.setTypeface(mGooglePlay.getTypeface(), Typeface.BOLD);
                        break;
                    case FONT_STYLE_BOLD_ITALIC:
                        String websiteText = mWebsite.getText().toString() + SPACE_CHAR;
                        String githubText = mGithub.getText().toString() + SPACE_CHAR;
                        String facebookText = mFacebook.getText().toString() + SPACE_CHAR;
                        String twitterText = mTwitter.getText().toString() + SPACE_CHAR;
                        String googlePlayText = mGooglePlay.getText().toString() + SPACE_CHAR;

                        mWebsite.setText(websiteText);
                        mGithub.setText(githubText);
                        mFacebook.setText(facebookText);
                        mTwitter.setText(twitterText);
                        mGooglePlay.setText(googlePlayText);

                        mWebsite.setTypeface(mWebsite.getTypeface(), Typeface.BOLD_ITALIC);
                        mGithub.setTypeface(mGithub.getTypeface(), Typeface.BOLD_ITALIC);
                        mFacebook.setTypeface(mFacebook.getTypeface(), Typeface.BOLD_ITALIC);
                        mTwitter.setTypeface(mTwitter.getTypeface(), Typeface.BOLD_ITALIC);
                        mGooglePlay.setTypeface(mGooglePlay.getTypeface(), Typeface.BOLD_ITALIC);

                        invalidate();
                        break;
                }
            }

            if(mNameFontStyle != null){
                switch (mNameFontStyle) {
                    case FONT_STYLE_NORMAL:
                        mName.setTypeface(mName.getTypeface(), Typeface.NORMAL);
                        break;
                    case FONT_STYLE_ITALIC:
                        mName.setTypeface(mName.getTypeface(), Typeface.ITALIC);
                        break;
                    case FONT_STYLE_BOLD:
                        mName.setTypeface(mName.getTypeface(), Typeface.BOLD);
                        break;
                    case FONT_STYLE_BOLD_ITALIC:
                        String nameText         = mName.getText().toString() + SPACE_CHAR;
                        mName.setText(nameText);
                        mName.setTypeface(mName.getTypeface(), Typeface.BOLD_ITALIC);
                        invalidate();
                        break;
                }
            }
            if(mDescriptionFontStyle != null){
                switch (mDescriptionFontStyle) {
                    case FONT_STYLE_NORMAL:
                        mDescription.setTypeface(mDescription.getTypeface(), Typeface.NORMAL);
                        break;
                    case FONT_STYLE_ITALIC:
                        mDescription.setTypeface(mDescription.getTypeface(), Typeface.ITALIC);
                        break;
                    case FONT_STYLE_BOLD:
                        mDescription.setTypeface(mDescription.getTypeface(), Typeface.BOLD);
                        break;
                    case FONT_STYLE_BOLD_ITALIC:
                        String descriptionText  = mDescription.getText().toString() + SPACE_CHAR;
                        mDescription.setText(descriptionText);
                        mDescription.setTypeface(mDescription.getTypeface(), Typeface.BOLD_ITALIC);
                        invalidate();
                        break;
                }
            }
        }
    }

    private void setIconTintColor(ImageView icon, String colorHex){

        String colorHexFixed;

        if(colorHex.length() == 4){
            char[] colorChar = colorHex.toCharArray();
            char[] colorCharFixed = new char[7];
            if(colorChar[0] == '#'){
                colorCharFixed[0] = colorChar[0];
                for(int i = 1; i < colorCharFixed.length; i++){
                    colorCharFixed[i] = colorChar[1];
                }
            }
            colorHexFixed = String.copyValueOf(colorCharFixed);
        }
        else{
            colorHexFixed = null;
        }

        DrawableCompat.setTint(
                icon.getDrawable(),
                Color.parseColor(colorHexFixed == null ? colorHex : colorHexFixed));
    }

    private void setTextViewColor(TextView textView, String colorHex){
        String colorHexFixed;

        if(colorHex.length() == 4){
            char[] colorChar = colorHex.toCharArray();
            char[] colorCharFixed = new char[7];
            if(colorChar[0] == '#'){
                colorCharFixed[0] = colorChar[0];
                for(int i = 1; i < colorCharFixed.length; i++){
                    colorCharFixed[i] = colorChar[1];
                }
            }
            colorHexFixed = String.copyValueOf(colorCharFixed);
        }
        else{
            colorHexFixed = null;
        }

        textView.setTextColor(Color.parseColor(colorHexFixed == null ? colorHex : colorHexFixed));
    }

    private void setTextViewColor(TextView textView, int colorId){
        textView.setTextColor(ContextCompat.getColor(getContext(), colorId));
    }

    private void setAllTextViewsColor(List<TextView> textViewList, String colorHex){
        String colorHexFixed;

        if(colorHex.length() == 4){
            char[] colorChar = colorHex.toCharArray();
            char[] colorCharFixed = new char[7];
            if(colorChar[0] == '#'){
                colorCharFixed[0] = colorChar[0];
                for(int i = 1; i < colorCharFixed.length; i++){
                    colorCharFixed[i] = colorChar[1];
                }
            }
            colorHexFixed = String.copyValueOf(colorCharFixed);
        }
        else{
            colorHexFixed = null;
        }

        for(TextView tv : textViewList){
            tv.setTextColor(Color.parseColor(colorHexFixed == null ? colorHex : colorHexFixed));
        }
    }

    private void setAllTextViewsColor(List<TextView> textViewList, int colorId){
        for(TextView tv : textViewList){
            tv.setTextColor(ContextCompat.getColor(getContext(), colorId));
        }
    }

    View.OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            int id = v.getId();
            if(id == R.id.akn_ll_website_container){
                if(mLinkWebsite != null){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fixUrls(mLinkWebsite)));
                    getContext().startActivity(browserIntent);
                }
            }
            else if(id == R.id.akn_ll_github_container){
                if(mLinkGithub != null){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fixUrls(mLinkGithub)));
                    getContext().startActivity(browserIntent);
                }
            }
            else if(id == R.id.akn_ll_facebook_container){
                if(mLinkFacebook != null){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fixUrls(mLinkFacebook)));
                    getContext().startActivity(browserIntent);
                }
            }
            else if(id == R.id.akn_ll_twitter_container){
                if(mLinkTwitter != null){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fixUrls(mLinkTwitter)));
                    getContext().startActivity(browserIntent);
                }
            }
            else if(id == R.id.akn_ll_rate_us_container){

                if(isGooglePlayLinkAdded){
                    String appId = getContext().getPackageName();
                    try {
                        getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appId)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appId)));
                    }
                }
                else if(mLinkGooglePlay != null){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mLinkGooglePlay));
                    getContext().startActivity(browserIntent);

                }
            }
        }
    };


    private String fixUrls(String url){
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            return Uri.parse("http://" + url).toString();
        }
        else
            return url;
    }

    // // Set & Get texts ***************************************************
    public String getWebsiteText() {
        return mWebsite.getText().toString();
    }

    public void setWebsiteText(String websiteText) {
        mWebsite.setText(websiteText);
        invalidate();
    }

    public String getNameText() {
        return mName.getText().toString();
    }

    public void setNameText(String nameText) {
        mName.setVisibility(VISIBLE);
        mName.setText(nameText);
        invalidate();
    }

    public String getDescriptionText() {
        return mDescription.getText().toString();
    }

    public void setDescriptionText(String descriptionText) {
        mDescription.setVisibility(VISIBLE);
        mDescription.setText(descriptionText);
        invalidate();
    }

    public String getGithubText() {
        return mGithub.getText().toString();
    }

    public void setGithubText(String githubText) {
        mGithub.setText(githubText);
        invalidate();
    }

    public String getFacebookText() {
        return mFacebook.getText().toString();
    }

    public void setFacebookText(String facebookText) {
        mFacebook.setText(facebookText);
        invalidate();
    }

    public String getTwitterText() {
        return mTwitter.getText().toString();
    }

    public void setTwitterText(String twitterText) {
        mTwitter.setText(twitterText);
        invalidate();
    }

    public String getGooglePlayText() {
        return mGooglePlay.getText().toString();
    }

    public void setGooglePlayText(String googlePlayText) {
        mGooglePlay.setText(googlePlayText);
        invalidate();
    }

    public String getLinkWebsite() {
        if(mLinkWebsite == null){
            Log.e(getContext().getClass().getSimpleName(), "Website link is null");
        }
        return mLinkWebsite != null ? mLinkWebsite : "";
    }

    public void setLinkWebsite(String linkWebsite) {
        mLinkWebsite = linkWebsite;
        invalidate();
    }

    public String getLinkGithub() {
        if(mLinkGithub == null){
            Log.e(getContext().getClass().getSimpleName(), "Github link is null");
        }
        return mLinkGithub != null ? mLinkGithub : "";
    }

    public void setLinkGithub(String linkGithub) {
        mLinkGithub = linkGithub;
        invalidate();
    }

    public String getLinkFacebook() {
        if(mLinkFacebook == null){
            Log.e(getContext().getClass().getSimpleName(), "Facebook link is null");
        }
        return mLinkFacebook != null ? mLinkFacebook : "";
    }

    public void setLinkFacebook(String linkFacebook) {
        mLinkFacebook = linkFacebook;
        invalidate();
    }

    public String getLinkTwitter() {
        if(mLinkTwitter == null){
            Log.e(getContext().getClass().getSimpleName(), "Twitter link is null");
        }
        return mLinkTwitter != null ? mLinkTwitter : "";
    }

    public void setLinkTwitter(String linkTwitter) {
        mLinkTwitter = linkTwitter;
        invalidate();
    }

    public String getLinkGooglePlay() {
        if(mLinkGooglePlay == null){
            Log.e(getContext().getClass().getSimpleName(), "Google Play link is null");
        }
        return mLinkGooglePlay != null ? mLinkGooglePlay : "";
    }

    public void setLinkGooglePlay(String linkGooglePlay) {
        mLinkGooglePlay = linkGooglePlay;
        invalidate();
    }

    // Set link icons ***************************************************
    public void setWebsiteIcon(int drawableId){
       mWebsiteIcon.setImageResource(drawableId);
       invalidate();
    }

    public void setGithubIcon(int drawableId){
        mGithubIcon.setImageResource(drawableId);
        invalidate();
    }

    public void setFacebookIcon(int drawableId){
        mFacebookIcon.setImageResource(drawableId);
        invalidate();
    }

    public void setTwitterIcon(int drawableId){
        mTwitterIcon.setImageResource(drawableId);
        invalidate();
    }

    public void setGooglePlayIcon(int drawableId){
        mGooglePlayIcon.setImageResource(drawableId);
        invalidate();
    }

    // Show links ***************************************************
    public void showWebsiteLink(){
        mContainerWebsite.setVisibility(VISIBLE);
        invalidate();
    }

    public void showGithubLink(){
        mContainerGithub.setVisibility(VISIBLE);
        invalidate();
    }

    public void showFacebookLink(){
        mContainerFacebook.setVisibility(VISIBLE);
        invalidate();
    }

    public void showTwitterLink(){
        mContainerTwitter.setVisibility(VISIBLE);
        invalidate();
    }

    public void showGooglePlayLink(){
        mContainerGooglePlay.setVisibility(VISIBLE);
        invalidate();
    }

    // Set link icon tint colors ***************************************************

    public void setWebsiteIconTintColor(int colorId){
        DrawableCompat.setTint(mWebsiteIcon.getDrawable(), ContextCompat.getColor(getContext(), colorId));
        invalidate();
    }
    public void setGithubIconTintColor(int colorId){
        DrawableCompat.setTint(mGithubIcon.getDrawable(), ContextCompat.getColor(getContext(), colorId));
        invalidate();
    }
    public void setFacebookIconTintColor(int colorId){
        DrawableCompat.setTint(mFacebookIcon.getDrawable(), ContextCompat.getColor(getContext(), colorId));
        invalidate();
    }
    public void setTwitterIconTintColor(int colorId){
        DrawableCompat.setTint(mTwitterIcon.getDrawable(), ContextCompat.getColor(getContext(), colorId));
        invalidate();
    }
    public void setGooglePlayIconTintColor(int colorId){
        DrawableCompat.setTint(mGooglePlayIcon.getDrawable(), ContextCompat.getColor(getContext(), colorId));
        invalidate();
    }

    public void setWebsiteIconTintColor(String colorHexCode){
        setIconTintColor(mWebsiteIcon, colorHexCode);
        invalidate();
    }
    public void setGithubIconTintColor(String colorHexCode){
        setIconTintColor(mGithubIcon, colorHexCode);
        invalidate();
    }
    public void setFacebookIconTintColor(String colorHexCode){
        setIconTintColor(mFacebookIcon, colorHexCode);
        invalidate();
    }
    public void setTwitterIconTintColor(String colorHexCode){
        setIconTintColor(mTwitterIcon, colorHexCode);
        invalidate();
    }
    public void setGooglePlayIconTintColor(String colorHexCode){
        setIconTintColor(mGooglePlayIcon, colorHexCode);
        invalidate();
    }

    // Get profile picture and icon imageviews ***************************************************
    public ImageView getProfilePhoto(){
        mImageViewProfilePhoto.setVisibility(VISIBLE);
        return mImageViewProfilePhoto;
    }
    public ImageView getWebsiteIcon(){
        return mWebsiteIcon;
    }
    public ImageView getGithubIcon(){
        return mGithubIcon;
    }
    public ImageView getFacebookIcon(){
        return mFacebookIcon;
    }
    public ImageView getTwitterIcon(){
        return mTwitterIcon;
    }
    public ImageView getGooglePlayIcon(){
        return mGooglePlayIcon;
    }

    // Set photo & icon drawables ***************************************************
    public void setProfilePhotoDrawable(int drawableId){
        mImageViewProfilePhoto.setVisibility(VISIBLE);
        mImageViewProfilePhoto.setImageResource(drawableId);
        invalidate();
    }
    public void setWebsiteIconDrawable(int drawableId){
        mWebsiteIcon.setImageResource(drawableId);
        invalidate();
    }
    public void setGithubIconDrawable(int drawableId){
        mGithubIcon.setImageResource(drawableId);
        invalidate();
    }
    public void setFacebookIconDrawable(int drawableId){
        mFacebookIcon.setImageResource(drawableId);
        invalidate();
    }
    public void setTwitterIconDrawable(int drawableId){
        mTwitterIcon.setImageResource(drawableId);
        invalidate();
    }
    public void setGooglePlayIconDrawable(int drawableId){
        mGooglePlayIcon.setImageResource(drawableId);
        invalidate();
    }

    // Set text colors ***************************************************
    public void setTextColor(int colorId){
        List<TextView> textViewList = new ArrayList<>();
        textViewList.add(mName);
        textViewList.add(mDescription);
        textViewList.add(mWebsite);
        textViewList.add(mGithub);
        textViewList.add(mFacebook);
        textViewList.add(mTwitter);
        textViewList.add(mGooglePlay);

        setAllTextViewsColor(textViewList, colorId);
    }
    public void setTextColorName(int colorId){
        setTextViewColor(mName, colorId);
        invalidate();
    }
    public void setTextColorDescription(int colorId){
        setTextViewColor(mDescription, colorId);
        invalidate();
    }
    public void setTextColorWebsite(int colorId){
        setTextViewColor(mWebsite, colorId);
        invalidate();
    }
    public void setTextColorGithub(int colorId){
        setTextViewColor(mGithub, colorId);
        invalidate();
    }
    public void setTextColorFacebook(int colorId){
        setTextViewColor(mFacebook, colorId);
        invalidate();
    }
    public void setTextColorTwitter(int colorId){
        setTextViewColor(mTwitter, colorId);
        invalidate();
    }
    public void setTextColorGooglePlay(int colorId){
        setTextViewColor(mGooglePlay, colorId);
        invalidate();
    }

    public void setTextColor(String colorHexCode){
        List<TextView> textViewList = new ArrayList<>();
        textViewList.add(mName);
        textViewList.add(mDescription);
        textViewList.add(mWebsite);
        textViewList.add(mGithub);
        textViewList.add(mFacebook);
        textViewList.add(mTwitter);
        textViewList.add(mGooglePlay);

        setAllTextViewsColor(textViewList, colorHexCode);
    }
    public void setTextColorName(String colorHexCode){
        setTextViewColor(mName, colorHexCode);
        invalidate();
    }
    public void setTextColorDescription(String colorHexCode){
        setTextViewColor(mDescription, colorHexCode);
        invalidate();
    }
    public void setTextColorWebsite(String colorHexCode){
        setTextViewColor(mWebsite, colorHexCode);
        invalidate();
    }
    public void setTextColorGithub(String colorHexCode){
        setTextViewColor(mGithub, colorHexCode);
        invalidate();
    }
    public void setTextColorFacebook(String colorHexCode){
        setTextViewColor(mFacebook, colorHexCode);
        invalidate();
    }
    public void setTextColorTwitter(String colorHexCode){
        setTextViewColor(mTwitter, colorHexCode);
        invalidate();
    }
    public void setTextColorGooglePlay(String colorHexCode){
        setTextViewColor(mGooglePlay, colorHexCode);
        invalidate();
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        super.dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        super.dispatchThawSelfOnly(container);
    }
}
