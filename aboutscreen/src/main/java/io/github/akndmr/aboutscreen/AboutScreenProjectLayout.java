package io.github.akndmr.aboutscreen;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akın DEMİR on 31.01.2019.
 */
public class AboutScreenProjectLayout extends ConstraintLayout {

    public static final String SPACE_CHAR = "\u00A0";
    public static final String FONT_STYLE_BOLD = "BOLD";
    public static final String FONT_STYLE_ITALIC = "ITALIC";
    public static final String FONT_STYLE_NORMAL = "NORMAL";
    public static final String FONT_STYLE_BOLD_ITALIC = "BOLD_ITALIC";

    private CardView mCardView;
    private LinearLayout mContainerWebsite, mContainerGithub, mContainerFacebook, mContainerTwitter, mContainerGooglePlay;
    private TextView mTextViewProjectName, mTextViewProjectDescription, mTextViewProjectVersion,
    mTextViewDeveloperName, mTextViewDeveloperDescription, mTextViewProjectWebsite, mTextViewProjectGithub,
    mTextViewProjectFacebook, mTextViewProjectTwitter, mTextViewProjectGooglePlay;
    private ImageView mLogoProject, mLogoDeveloper, mIconWebsite, mIconGithub, mIconFacebook, mIconTwitter, mIconGooglePlay;
    private View mDividerTop, mDividerBottom;

    private int mCardViewRadius, mCardViewElevation;
    private String mCardViewBackgroundColor;
    private String mProjectNameText, mProjectDescriptionText, mProjectVersionText, mProjectDeveloperNameText, mProjectDeveloperDescriptionText;
    private String mProjectWebsiteText, mProjectGithubText, mProjectFacebookText, mProjectTwitterText, mProjectGooglePlayText;
    private String mProjectWebsiteUrl, mProjectGithubUrl, mProjectFacebookUrl, mProjecTwitterUrl;
    private String mColorProjectName, mColorProjectDescription, mColorProjectVersion, mColorDeveloperName, mColorDeveloperDescription,
    mColorWebsiteText, mColorGithubText, mColorFacebookText, mColorTwitterText, mColorGooglePlayText,
    mColorWebsiteIcon, mColorGithubIcon, mColorFacebookIcon, mColorTwitterIcon, mCOlorGooglePlayIcon,
    mColorDividerTop, mColorDividerBottom, mColorProject, mColorProjectLinks;

    private boolean mIsGooglePlayLinkAdded, mShouldGetVersionFromGradle;
    private int mLogoProjectDrawableId, mLogoDeveloperDrawableId, mIconWebsiteDrawableId, mIconGithubDrawableId,
    mIconFacebookDrawableId, mIconTwitterDrawableId, mIconGooglePlayDrawableId;
    private int mProjectFontId, mProjectNameFontId, mProjectDescriptionFontId, mDeveloperNameFontId,
            mDeveloperDescriptionFontId, mProjectLinksFontId, mProjectVersionFontId;
    private String mProjectFontStyle, mProjectNameFontStyle, mProjectDescriptionFontStyle, mProjectVersionFontStyle,
            mDeveloperNameFontStyle, mDeveloperDescriptionFontStyle, mProjectLinksFontStyle;

    public AboutScreenProjectLayout(Context context) {
        super(context, null);
        initializeViews(context);
        bindViews();
    }

    public AboutScreenProjectLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        setupAttributes(attrs);
        initializeViews(context);
        bindViews();
        setAttributesAndValues();
    }

    public AboutScreenProjectLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupAttributes(attrs);
        initializeViews(context);
        bindViews();
        setAttributesAndValues();
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.about_screen_project, this);
    }

    private void setupAttributes(AttributeSet attrs) {

        // Obtain a typed array of attributes
        TypedArray t = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.AboutScreenProject, 0, 0);

        try {
            // Extract custom attributes into member variables
            mCardViewRadius                     = t.getDimensionPixelSize(R.styleable.AboutScreenProject_cardRadiusProject,0);
            mCardViewElevation                  = t.getDimensionPixelSize(R.styleable.AboutScreenProject_cardElevationProject,0);
            mCardViewBackgroundColor            = t.getString(R.styleable.AboutScreenProject_cardBackgroundColorProject);

            mProjectNameText                    = t.getString(R.styleable.AboutScreenProject_textProjectName);
            mProjectDescriptionText             = t.getString(R.styleable.AboutScreenProject_textProjectDescription);
            mProjectVersionText                 = t.getString(R.styleable.AboutScreenProject_textProjectVersion);
            mProjectDeveloperNameText           = t.getString(R.styleable.AboutScreenProject_textDeveloperName);
            mProjectDeveloperDescriptionText    = t.getString(R.styleable.AboutScreenProject_textDeveloperDescription);
            mProjectWebsiteText                 = t.getString(R.styleable.AboutScreenProject_textProjectWebsite);
            mProjectGithubText                  = t.getString(R.styleable.AboutScreenProject_textProjectGithub);
            mProjectFacebookText                = t.getString(R.styleable.AboutScreenProject_textProjectFacebook);
            mProjectTwitterText                 = t.getString(R.styleable.AboutScreenProject_textProjectTwitter);
            mProjectGooglePlayText              = t.getString(R.styleable.AboutScreenProject_textProjectGooglePlay);

            mProjectWebsiteUrl                  = t.getString(R.styleable.AboutScreenProject_urlProjectWebsite);
            mProjectGithubUrl                   = t.getString(R.styleable.AboutScreenProject_urlProjectGithub);
            mProjectFacebookUrl                 = t.getString(R.styleable.AboutScreenProject_urlProjectFacebook);
            mProjecTwitterUrl                   = t.getString(R.styleable.AboutScreenProject_urlProjectTwitter);

            mLogoProjectDrawableId              = t.getResourceId(R.styleable.AboutScreenProject_projectLogo, 0);
            mLogoDeveloperDrawableId            = t.getResourceId(R.styleable.AboutScreenProject_developerLogo, 0);
            mIconWebsiteDrawableId              = t.getResourceId(R.styleable.AboutScreenProject_iconProjectWebsite, 0);
            mIconGithubDrawableId               = t.getResourceId(R.styleable.AboutScreenProject_iconProjectGithub, 0);
            mIconFacebookDrawableId             = t.getResourceId(R.styleable.AboutScreenProject_iconProjectFacebook, 0);
            mIconTwitterDrawableId              = t.getResourceId(R.styleable.AboutScreenProject_iconProjectTwitter, 0);
            mIconGooglePlayDrawableId           = t.getResourceId(R.styleable.AboutScreenProject_iconProjectGooglePlay, 0);

            mColorProject                       = t.getString(R.styleable.AboutScreenProject_textColorProject);
            mColorProjectLinks                  = t.getString(R.styleable.AboutScreenProject_textColorProjectLinks);
            mColorProjectName                   = t.getString(R.styleable.AboutScreenProject_textColorProjectName);
            mColorProjectDescription            = t.getString(R.styleable.AboutScreenProject_textColorProjectDescription);
            mColorProjectVersion                = t.getString(R.styleable.AboutScreenProject_textColorProjectVersion);
            mColorDeveloperName                 = t.getString(R.styleable.AboutScreenProject_textColorDeveloperName);
            mColorDeveloperDescription          = t.getString(R.styleable.AboutScreenProject_textColorDeveloperDescription);
            mColorWebsiteText                   = t.getString(R.styleable.AboutScreenProject_textColorProjectWebsite);
            mColorGithubText                    = t.getString(R.styleable.AboutScreenProject_textColorProjectGithub);
            mColorFacebookText                  = t.getString(R.styleable.AboutScreenProject_textColorProjectFacebook);
            mColorTwitterText                   = t.getString(R.styleable.AboutScreenProject_textColorProjectTwitter);
            mColorGooglePlayText                = t.getString(R.styleable.AboutScreenProject_textColorProjectGooglePlay);
            mColorWebsiteIcon                   = t.getString(R.styleable.AboutScreenProject_iconColorProjectWebsite);
            mColorGithubIcon                    = t.getString(R.styleable.AboutScreenProject_iconColorProjectGithub);
            mColorFacebookIcon                  = t.getString(R.styleable.AboutScreenProject_iconColorProjectFacebook);
            mColorTwitterIcon                   = t.getString(R.styleable.AboutScreenProject_iconColorProjectTwitter);
            mCOlorGooglePlayIcon                = t.getString(R.styleable.AboutScreenProject_iconColorProjectGooglePlay);
            mColorDividerTop                    = t.getString(R.styleable.AboutScreenProject_dividerColor);
            mColorDividerBottom                 = t.getString(R.styleable.AboutScreenProject_dividerColor);

            mProjectFontId                      = t.getResourceId(R.styleable.AboutScreenProject_textProjectFontId, 0);
            mProjectNameFontId                  = t.getResourceId(R.styleable.AboutScreenProject_textProjectNameFontId, 0);
            mProjectDescriptionFontId           = t.getResourceId(R.styleable.AboutScreenProject_textProjectDescriptionFontId, 0);
            mProjectVersionFontId               = t.getResourceId(R.styleable.AboutScreenProject_textProjectVersionFontId, 0);
            mDeveloperNameFontId                = t.getResourceId(R.styleable.AboutScreenProject_textDeveloperNameFontId, 0);
            mDeveloperDescriptionFontId         = t.getResourceId(R.styleable.AboutScreenProject_textDeveloperDescriptionFontId, 0);
            mProjectLinksFontId                 = t.getResourceId(R.styleable.AboutScreenProject_textProjectLinksFontId, 0);

            mProjectFontStyle                   = t.getString(R.styleable.AboutScreenProject_textProjectFontStyle);
            mProjectNameFontStyle               = t.getString(R.styleable.AboutScreenProject_textProjectNameFontStyle);
            mProjectDescriptionFontStyle        = t.getString(R.styleable.AboutScreenProject_textProjectDescriptionFontStyle);
            mProjectVersionFontStyle            = t.getString(R.styleable.AboutScreenProject_textProjectVersionFontStyle);
            mDeveloperNameFontStyle             = t.getString(R.styleable.AboutScreenProject_textDeveloperNameFontStyle);
            mDeveloperDescriptionFontStyle      = t.getString(R.styleable.AboutScreenProject_textDeveloperDescriptionFontStyle);
            mProjectLinksFontStyle              = t.getString(R.styleable.AboutScreenProject_textProjectLinksFontStyle);

            mIsGooglePlayLinkAdded              = t.getBoolean(R.styleable.AboutScreenProject_isProjectGooglePlayLinkEnabled,false);
            mShouldGetVersionFromGradle         = t.getBoolean(R.styleable.AboutScreenProject_shouldGetVersionFromGradle,false);

        }finally {
            // TypedArray objects are shared and must be recycled.
            t.recycle();
        }

    }

    private void bindViews(){
        mCardView                       = findViewById(R.id.akn_project_profile_cardview);

        mContainerWebsite               = findViewById(R.id.akn_project_ll_website_container);
        mContainerGithub                = findViewById(R.id.akn_project_ll_github_container);
        mContainerFacebook              = findViewById(R.id.akn_project_ll_facebook_container);
        mContainerTwitter               = findViewById(R.id.akn_project_ll_twitter_container);
        mContainerGooglePlay            = findViewById(R.id.akn_project_ll_rate_us_container);

        mTextViewProjectName            = findViewById(R.id.tv_project_project_name);
        mTextViewProjectDescription     = findViewById(R.id.tv_project_project_description);
        mTextViewProjectVersion         = findViewById(R.id.tv_project_project_version);
        mTextViewDeveloperName          = findViewById(R.id.tv_project_developed_by);
        mTextViewDeveloperDescription   = findViewById(R.id.tv_project_developer_description);
        mTextViewProjectWebsite         = findViewById(R.id.tv_project_website);
        mTextViewProjectGithub          = findViewById(R.id.tv_project_github);
        mTextViewProjectFacebook        = findViewById(R.id.tv_project_facebook);
        mTextViewProjectTwitter         = findViewById(R.id.tv_project_twitter);
        mTextViewProjectGooglePlay      = findViewById(R.id.tv_project_rate_us);

        mLogoProject                    = findViewById(R.id.iv_project_project_logo);
        mLogoDeveloper                  = findViewById(R.id.iv_project_developer_logo);
        mIconWebsite                    = findViewById(R.id.iv_project_icon_website);
        mIconGithub                     = findViewById(R.id.iv_project_icon_github);
        mIconFacebook                   = findViewById(R.id.iv_project_icon_facebook);
        mIconTwitter                    = findViewById(R.id.iv_project_icon_twitter);
        mIconGooglePlay                 = findViewById(R.id.iv_project_icon_rate_us);

        mDividerTop                     = findViewById(R.id.dividerTop);
        mDividerBottom                  = findViewById(R.id.dividerBottom);
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
        if(mProjectNameText != null){
            mTextViewProjectName.setVisibility(VISIBLE);
            mTextViewProjectName.setText(mProjectNameText);
        }
        if(mProjectDescriptionText != null){
            mTextViewProjectDescription.setVisibility(VISIBLE);
            mTextViewProjectDescription.setText(mProjectDescriptionText);
        }
        if(mShouldGetVersionFromGradle){
            String versionName = "";
            try {
                 versionName = getContext().getPackageManager()
                        .getPackageInfo(getContext().getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            mTextViewProjectVersion.setVisibility(VISIBLE);
            mTextViewProjectVersion.setText(versionName);
        }
        else if(mProjectVersionText != null){
            mTextViewProjectVersion.setVisibility(VISIBLE);
            mDividerTop.setVisibility(VISIBLE);
            mTextViewProjectVersion.setText(mProjectVersionText);

        }
        if(mProjectDeveloperNameText != null){
            mDividerTop.setVisibility(VISIBLE);
            mTextViewDeveloperName.setVisibility(VISIBLE);
            mTextViewDeveloperName.setText(mProjectDeveloperNameText);
        }
        if(mProjectDeveloperDescriptionText != null){
            mDividerTop.setVisibility(VISIBLE);
            mTextViewDeveloperDescription.setVisibility(VISIBLE);
            mTextViewDeveloperDescription.setText(mProjectDeveloperDescriptionText);
        }
        if(mProjectWebsiteText != null){
            mTextViewProjectWebsite.setText(mProjectWebsiteText);
        }
        if(mProjectGithubText != null){
            mTextViewProjectGithub.setText(mProjectGithubText);
        }
        if(mProjectFacebookText != null){
            mTextViewProjectFacebook.setText(mProjectFacebookText);
        }
        if(mProjectTwitterText != null){
            mTextViewProjectTwitter.setText(mProjectTwitterText);
        }
        if(mProjectGooglePlayText != null){
            mTextViewProjectGooglePlay.setText(mProjectGooglePlayText);
        }

        // Set drawables
        if(mLogoProjectDrawableId != 0){
            mLogoProject.setVisibility(VISIBLE);
            mLogoProject.setImageResource(mLogoProjectDrawableId);
        }
        if(mLogoDeveloperDrawableId != 0){
            mLogoDeveloper.setVisibility(VISIBLE);
            mDividerTop.setVisibility(VISIBLE);
            mLogoDeveloper.setImageResource(mLogoDeveloperDrawableId);
        }
        if(mProjectWebsiteUrl != null){
            mContainerWebsite.setVisibility(VISIBLE);
            mDividerBottom.setVisibility(VISIBLE);
            if(mIconWebsiteDrawableId != 0)
                mIconWebsite.setImageResource(mIconWebsiteDrawableId);
        }
        if(mProjectGithubUrl != null){
            mContainerGithub.setVisibility(VISIBLE);
            mDividerBottom.setVisibility(VISIBLE);
            if(mIconGithubDrawableId != 0)
                mIconGithub.setImageResource(mIconGithubDrawableId);
        }
        if(mProjectFacebookUrl != null){
            mContainerFacebook.setVisibility(VISIBLE);
            mDividerBottom.setVisibility(VISIBLE);
            if(mIconFacebookDrawableId != 0)
                mIconFacebook.setImageResource(mIconFacebookDrawableId);
        }
        if(mProjecTwitterUrl != null){
            mContainerTwitter.setVisibility(VISIBLE);
            mDividerBottom.setVisibility(VISIBLE);
            if(mIconTwitterDrawableId != 0)
                mIconTwitter.setImageResource(mIconTwitterDrawableId);
        }
        if(mIsGooglePlayLinkAdded){
            mContainerGooglePlay.setVisibility(VISIBLE);
            mDividerBottom.setVisibility(VISIBLE);
            if(mIconGooglePlayDrawableId != 0)
                mIconGooglePlay.setImageResource(mIconGooglePlayDrawableId);
        }

        // Set text colors
        if(mColorProject != null){
            List<TextView> textViewList = new ArrayList<>();
            textViewList.add(mTextViewProjectName);
            textViewList.add(mTextViewProjectDescription);
            textViewList.add(mTextViewProjectVersion);
            textViewList.add(mTextViewDeveloperName);
            textViewList.add(mTextViewDeveloperDescription);
            textViewList.add(mTextViewProjectWebsite);
            textViewList.add(mTextViewProjectGithub);
            textViewList.add(mTextViewProjectFacebook);
            textViewList.add(mTextViewProjectTwitter);
            textViewList.add(mTextViewProjectGooglePlay);

            setAllTextViewsColor(textViewList, mColorProject);
        }
        else{
            if(mColorProjectName != null){
                setTextViewColor(mTextViewProjectName, mColorProjectName);
            }
            if(mColorProjectDescription != null){
                setTextViewColor(mTextViewProjectDescription, mColorProjectDescription);
            }
            if(mColorDeveloperName != null){
                setTextViewColor(mTextViewDeveloperName, mColorDeveloperName);
            }
            if(mColorDeveloperDescription != null){
                setTextViewColor(mTextViewDeveloperDescription, mColorDeveloperDescription);
            }
            if(mColorProjectLinks != null){
                List<TextView> textViewLinksList = new ArrayList<>();
                textViewLinksList.add(mTextViewProjectWebsite);
                textViewLinksList.add(mTextViewProjectGithub);
                textViewLinksList.add(mTextViewProjectFacebook);
                textViewLinksList.add(mTextViewProjectTwitter);
                textViewLinksList.add(mTextViewProjectGooglePlay);

                setAllTextViewsColor(textViewLinksList, mColorProjectLinks);
            }
            else{
                if(mColorWebsiteText != null){
                    setTextViewColor(mTextViewProjectWebsite, mColorWebsiteText);
                }
                if(mColorGithubText != null){
                    setTextViewColor(mTextViewProjectGithub, mColorGithubText);
                }
                if(mColorFacebookText != null){
                    setTextViewColor(mTextViewProjectFacebook, mColorFacebookText);
                }
                if(mColorTwitterText != null){
                    setTextViewColor(mTextViewProjectTwitter, mColorTwitterText);
                }
                if(mColorGooglePlayText != null){
                    setTextViewColor(mTextViewProjectGooglePlay, mColorGooglePlayText);
                }
            }

        }

        // Set icon tint / divider colors
        if(mColorWebsiteIcon != null)
            setIconTintColor(mIconWebsite, mColorWebsiteIcon);
        if(mColorGithubIcon != null)
            setIconTintColor(mIconGithub, mColorGithubIcon);
        if(mColorFacebookIcon != null)
            setIconTintColor(mIconFacebook, mColorFacebookIcon);
        if(mColorTwitterIcon != null)
            setIconTintColor(mIconTwitter, mColorTwitterIcon);
        if(mCOlorGooglePlayIcon != null)
            setIconTintColor(mIconGooglePlay, mCOlorGooglePlayIcon);
        if(mColorDividerTop != null)
            setBackgroundColor(mDividerTop, mColorDividerTop);
        if(mColorDividerBottom != null)
            setBackgroundColor(mDividerBottom, mColorDividerBottom);


        // Set font families of texts
        if(mProjectFontId != 0){
            mTextViewProjectName.setTypeface(ResourcesCompat.getFont(getContext(), mProjectFontId));
            mTextViewProjectDescription.setTypeface(ResourcesCompat.getFont(getContext(), mProjectFontId));
            mTextViewProjectVersion.setTypeface(ResourcesCompat.getFont(getContext(), mProjectFontId));
            mTextViewDeveloperName.setTypeface(ResourcesCompat.getFont(getContext(), mProjectFontId));
            mTextViewDeveloperDescription.setTypeface(ResourcesCompat.getFont(getContext(), mProjectFontId));
            mTextViewProjectWebsite.setTypeface(ResourcesCompat.getFont(getContext(), mProjectFontId));
            mTextViewProjectGithub.setTypeface(ResourcesCompat.getFont(getContext(), mProjectFontId));
            mTextViewProjectFacebook.setTypeface(ResourcesCompat.getFont(getContext(), mProjectFontId));
            mTextViewProjectTwitter.setTypeface(ResourcesCompat.getFont(getContext(), mProjectFontId));
            mTextViewProjectGooglePlay.setTypeface(ResourcesCompat.getFont(getContext(), mProjectFontId));
        }
        else{
            if(mProjectNameFontId != 0){
                mTextViewProjectName.setTypeface(ResourcesCompat.getFont(getContext(), mProjectNameFontId));
            }
            if(mProjectDescriptionFontId != 0){
                mTextViewProjectDescription.setTypeface(ResourcesCompat.getFont(getContext(), mProjectDescriptionFontId));
            }
            if(mProjectVersionFontId != 0){
                mTextViewProjectVersion.setTypeface(ResourcesCompat.getFont(getContext(), mProjectVersionFontId));
            }
            if(mDeveloperNameFontId != 0){
                mTextViewDeveloperName.setTypeface(ResourcesCompat.getFont(getContext(), mDeveloperNameFontId));
            }
            if(mDeveloperDescriptionFontId != 0){
                mTextViewDeveloperDescription.setTypeface(ResourcesCompat.getFont(getContext(), mDeveloperDescriptionFontId));
            }

            if(mProjectLinksFontId != 0){
                // set links font
                mTextViewProjectWebsite.setTypeface(ResourcesCompat.getFont(getContext(), mProjectLinksFontId));
                mTextViewProjectGithub.setTypeface(ResourcesCompat.getFont(getContext(), mProjectLinksFontId));
                mTextViewProjectFacebook.setTypeface(ResourcesCompat.getFont(getContext(), mProjectLinksFontId));
                mTextViewProjectTwitter.setTypeface(ResourcesCompat.getFont(getContext(), mProjectLinksFontId));
                mTextViewProjectGooglePlay.setTypeface(ResourcesCompat.getFont(getContext(), mProjectLinksFontId));
            }
        }

        // Set font styles
        if(mProjectFontStyle != null){
            switch (mProjectFontStyle) {
                case FONT_STYLE_NORMAL:
                    mTextViewProjectName.setTypeface(mTextViewProjectName.getTypeface(),                    Typeface.NORMAL);
                    mTextViewProjectDescription.setTypeface(mTextViewProjectDescription.getTypeface(),      Typeface.NORMAL);
                    mTextViewProjectVersion.setTypeface(mTextViewProjectVersion.getTypeface(),              Typeface.NORMAL);
                    mTextViewDeveloperName.setTypeface(mTextViewDeveloperName.getTypeface(),                Typeface.NORMAL);
                    mTextViewDeveloperDescription.setTypeface(mTextViewDeveloperDescription.getTypeface(),  Typeface.NORMAL);
                    mTextViewProjectWebsite.setTypeface(mTextViewProjectWebsite.getTypeface(),              Typeface.NORMAL);
                    mTextViewProjectGithub.setTypeface(mTextViewProjectGithub.getTypeface(),                Typeface.NORMAL);
                    mTextViewProjectFacebook.setTypeface(mTextViewProjectFacebook.getTypeface(),            Typeface.NORMAL);
                    mTextViewProjectTwitter.setTypeface(mTextViewProjectTwitter.getTypeface(),              Typeface.NORMAL);
                    mTextViewProjectGooglePlay.setTypeface(mTextViewProjectGooglePlay.getTypeface(),        Typeface.NORMAL);
                    break;
                case FONT_STYLE_ITALIC:
                    mTextViewProjectName.setTypeface(mTextViewProjectName.getTypeface(),                    Typeface.ITALIC);
                    mTextViewProjectDescription.setTypeface(mTextViewProjectDescription.getTypeface(),      Typeface.ITALIC);
                    mTextViewProjectVersion.setTypeface(mTextViewProjectVersion.getTypeface(),              Typeface.ITALIC);
                    mTextViewDeveloperName.setTypeface(mTextViewDeveloperName.getTypeface(),                Typeface.ITALIC);
                    mTextViewDeveloperDescription.setTypeface(mTextViewDeveloperDescription.getTypeface(),  Typeface.ITALIC);
                    mTextViewProjectWebsite.setTypeface(mTextViewProjectWebsite.getTypeface(),              Typeface.ITALIC);
                    mTextViewProjectGithub.setTypeface(mTextViewProjectGithub.getTypeface(),                Typeface.ITALIC);
                    mTextViewProjectFacebook.setTypeface(mTextViewProjectFacebook.getTypeface(),            Typeface.ITALIC);
                    mTextViewProjectTwitter.setTypeface(mTextViewProjectTwitter.getTypeface(),              Typeface.ITALIC);
                    mTextViewProjectGooglePlay.setTypeface(mTextViewProjectGooglePlay.getTypeface(),        Typeface.ITALIC);
                    break;
                case FONT_STYLE_BOLD:
                    mTextViewProjectName.setTypeface(mTextViewProjectName.getTypeface(),                    Typeface.BOLD);
                    mTextViewProjectDescription.setTypeface(mTextViewProjectDescription.getTypeface(),      Typeface.BOLD);
                    mTextViewProjectVersion.setTypeface(mTextViewProjectVersion.getTypeface(),              Typeface.BOLD);
                    mTextViewDeveloperName.setTypeface(mTextViewDeveloperName.getTypeface(),                Typeface.BOLD);
                    mTextViewDeveloperDescription.setTypeface(mTextViewDeveloperDescription.getTypeface(),  Typeface.BOLD);
                    mTextViewProjectWebsite.setTypeface(mTextViewProjectWebsite.getTypeface(),              Typeface.BOLD);
                    mTextViewProjectGithub.setTypeface(mTextViewProjectGithub.getTypeface(),                Typeface.BOLD);
                    mTextViewProjectFacebook.setTypeface(mTextViewProjectFacebook.getTypeface(),            Typeface.BOLD);
                    mTextViewProjectTwitter.setTypeface(mTextViewProjectTwitter.getTypeface(),              Typeface.BOLD);
                    mTextViewProjectGooglePlay.setTypeface(mTextViewProjectGooglePlay.getTypeface(),        Typeface.BOLD);
                    break;
                case FONT_STYLE_BOLD_ITALIC:
                    String nameText                 = mTextViewProjectName.getText().toString()             + SPACE_CHAR;
                    String descriptionText          = mTextViewProjectDescription.getText().toString()      + SPACE_CHAR;
                    String versionText              = mTextViewProjectVersion.getText().toString()          + SPACE_CHAR;
                    String developerNameText        = mTextViewDeveloperName.getText().toString()           + SPACE_CHAR;
                    String developerDescriptionText = mTextViewDeveloperDescription.getText().toString()    + SPACE_CHAR;
                    String websiteText              = mTextViewProjectWebsite.getText().toString()          + SPACE_CHAR;
                    String githubText               = mTextViewProjectGithub.getText().toString()           + SPACE_CHAR;
                    String facebookText             = mTextViewProjectFacebook.getText().toString()         + SPACE_CHAR;
                    String twitterText              = mTextViewProjectTwitter.getText().toString()          + SPACE_CHAR;
                    String googlePlayText           = mTextViewProjectGooglePlay.getText().toString()       + SPACE_CHAR;

                    mTextViewProjectName.setText(nameText);
                    mTextViewProjectDescription.setText(descriptionText);
                    mTextViewProjectVersion.setText(versionText);
                    mTextViewDeveloperName.setText(developerNameText);
                    mTextViewDeveloperDescription.setText(developerDescriptionText);
                    mTextViewProjectWebsite.setText(websiteText);
                    mTextViewProjectGithub.setText(githubText);
                    mTextViewProjectFacebook.setText(facebookText);
                    mTextViewProjectTwitter.setText(twitterText);
                    mTextViewProjectGooglePlay.setText(googlePlayText);

                    mTextViewProjectName.setTypeface(mTextViewProjectName.getTypeface(),                    Typeface.BOLD_ITALIC);
                    mTextViewProjectDescription.setTypeface(mTextViewProjectDescription.getTypeface(),      Typeface.BOLD_ITALIC);
                    mTextViewProjectVersion.setTypeface(mTextViewProjectVersion.getTypeface(),              Typeface.BOLD_ITALIC);
                    mTextViewDeveloperName.setTypeface(mTextViewDeveloperName.getTypeface(),                Typeface.BOLD_ITALIC);
                    mTextViewDeveloperDescription.setTypeface(mTextViewDeveloperDescription.getTypeface(),  Typeface.BOLD_ITALIC);
                    mTextViewProjectWebsite.setTypeface(mTextViewProjectWebsite.getTypeface(),              Typeface.BOLD_ITALIC);
                    mTextViewProjectGithub.setTypeface(mTextViewProjectGithub.getTypeface(),                Typeface.BOLD_ITALIC);
                    mTextViewProjectFacebook.setTypeface(mTextViewProjectFacebook.getTypeface(),            Typeface.BOLD_ITALIC);
                    mTextViewProjectTwitter.setTypeface(mTextViewProjectTwitter.getTypeface(),              Typeface.BOLD_ITALIC);
                    mTextViewProjectGooglePlay.setTypeface(mTextViewProjectGooglePlay.getTypeface(),        Typeface.BOLD_ITALIC);

                    invalidate();
                    break;
            }
        }
        else{
            if(mProjectLinksFontStyle != null){
                switch (mProjectLinksFontStyle) {
                    case FONT_STYLE_NORMAL:
                        mTextViewProjectWebsite.setTypeface(mTextViewProjectWebsite.getTypeface(),              Typeface.NORMAL);
                        mTextViewProjectGithub.setTypeface(mTextViewProjectGithub.getTypeface(),                Typeface.NORMAL);
                        mTextViewProjectFacebook.setTypeface(mTextViewProjectFacebook.getTypeface(),            Typeface.NORMAL);
                        mTextViewProjectTwitter.setTypeface(mTextViewProjectTwitter.getTypeface(),              Typeface.NORMAL);
                        mTextViewProjectGooglePlay.setTypeface(mTextViewProjectGooglePlay.getTypeface(),        Typeface.NORMAL);
                        break;
                    case FONT_STYLE_ITALIC:
                        mTextViewProjectWebsite.setTypeface(mTextViewProjectWebsite.getTypeface(),              Typeface.ITALIC);
                        mTextViewProjectGithub.setTypeface(mTextViewProjectGithub.getTypeface(),                Typeface.ITALIC);
                        mTextViewProjectFacebook.setTypeface(mTextViewProjectFacebook.getTypeface(),            Typeface.ITALIC);
                        mTextViewProjectTwitter.setTypeface(mTextViewProjectTwitter.getTypeface(),              Typeface.ITALIC);
                        mTextViewProjectGooglePlay.setTypeface(mTextViewProjectGooglePlay.getTypeface(),        Typeface.ITALIC);
                        break;
                    case FONT_STYLE_BOLD:
                        mTextViewProjectWebsite.setTypeface(mTextViewProjectWebsite.getTypeface(),              Typeface.BOLD);
                        mTextViewProjectGithub.setTypeface(mTextViewProjectGithub.getTypeface(),                Typeface.BOLD);
                        mTextViewProjectFacebook.setTypeface(mTextViewProjectFacebook.getTypeface(),            Typeface.BOLD);
                        mTextViewProjectTwitter.setTypeface(mTextViewProjectTwitter.getTypeface(),              Typeface.BOLD);
                        mTextViewProjectGooglePlay.setTypeface(mTextViewProjectGooglePlay.getTypeface(),        Typeface.BOLD);
                        break;
                    case FONT_STYLE_BOLD_ITALIC:
                        String websiteText = mTextViewProjectWebsite.getText().toString()       + SPACE_CHAR;
                        String githubText = mTextViewProjectGithub.getText().toString()         + SPACE_CHAR;
                        String facebookText = mTextViewProjectFacebook.getText().toString()     + SPACE_CHAR;
                        String twitterText = mTextViewProjectTwitter.getText().toString()       + SPACE_CHAR;
                        String googlePlayText = mTextViewProjectGooglePlay.getText().toString() + SPACE_CHAR;

                        mTextViewProjectWebsite.setText(websiteText);
                        mTextViewProjectGithub.setText(githubText);
                        mTextViewProjectFacebook.setText(facebookText);
                        mTextViewProjectTwitter.setText(twitterText);
                        mTextViewProjectGooglePlay.setText(googlePlayText);

                        mTextViewProjectWebsite.setTypeface(mTextViewProjectWebsite.getTypeface(),              Typeface.BOLD_ITALIC);
                        mTextViewProjectGithub.setTypeface(mTextViewProjectGithub.getTypeface(),                Typeface.BOLD_ITALIC);
                        mTextViewProjectFacebook.setTypeface(mTextViewProjectFacebook.getTypeface(),            Typeface.BOLD_ITALIC);
                        mTextViewProjectTwitter.setTypeface(mTextViewProjectTwitter.getTypeface(),              Typeface.BOLD_ITALIC);
                        mTextViewProjectGooglePlay.setTypeface(mTextViewProjectGooglePlay.getTypeface(),        Typeface.BOLD_ITALIC);

                        invalidate();
                        break;
                }
            }

            if(mProjectFontStyle != null){
                switch (mProjectFontStyle) {
                    case FONT_STYLE_NORMAL:
                        mTextViewProjectName.setTypeface(mTextViewProjectName.getTypeface(), Typeface.NORMAL);
                        break;
                    case FONT_STYLE_ITALIC:
                        mTextViewProjectName.setTypeface(mTextViewProjectName.getTypeface(), Typeface.ITALIC);
                        break;
                    case FONT_STYLE_BOLD:
                        mTextViewProjectName.setTypeface(mTextViewProjectName.getTypeface(), Typeface.BOLD);
                        break;
                    case FONT_STYLE_BOLD_ITALIC:
                        String nameText         = mTextViewProjectName.getText().toString() + SPACE_CHAR;
                        mTextViewProjectName.setText(nameText);
                        mTextViewProjectName.setTypeface(mTextViewProjectName.getTypeface(), Typeface.BOLD_ITALIC);
                        invalidate();
                        break;
                }
            }

            if(mProjectDescriptionFontStyle != null){
                switch (mProjectDescriptionFontStyle) {
                    case FONT_STYLE_NORMAL:
                        mTextViewProjectDescription.setTypeface(mTextViewProjectDescription.getTypeface(), Typeface.NORMAL);
                        break;
                    case FONT_STYLE_ITALIC:
                        mTextViewProjectDescription.setTypeface(mTextViewProjectDescription.getTypeface(), Typeface.ITALIC);
                        break;
                    case FONT_STYLE_BOLD:
                        mTextViewProjectDescription.setTypeface(mTextViewProjectDescription.getTypeface(), Typeface.BOLD);
                        break;
                    case FONT_STYLE_BOLD_ITALIC:
                        String descriptionText         = mTextViewProjectDescription.getText().toString() + SPACE_CHAR;
                        mTextViewProjectDescription.setText(descriptionText);
                        mTextViewProjectDescription.setTypeface(mTextViewProjectDescription.getTypeface(), Typeface.BOLD_ITALIC);
                        invalidate();
                        break;
                }
            }

            if(mDeveloperNameFontStyle != null){
                switch (mDeveloperNameFontStyle) {
                    case FONT_STYLE_NORMAL:
                        mTextViewDeveloperName.setTypeface(mTextViewDeveloperName.getTypeface(), Typeface.NORMAL);
                        break;
                    case FONT_STYLE_ITALIC:
                        mTextViewDeveloperName.setTypeface(mTextViewDeveloperName.getTypeface(), Typeface.ITALIC);
                        break;
                    case FONT_STYLE_BOLD:
                        mTextViewDeveloperName.setTypeface(mTextViewDeveloperName.getTypeface(), Typeface.BOLD);
                        break;
                    case FONT_STYLE_BOLD_ITALIC:
                        String nameText         = mTextViewDeveloperName.getText().toString() + SPACE_CHAR;
                        mTextViewDeveloperName.setText(nameText);
                        mTextViewDeveloperName.setTypeface(mTextViewDeveloperName.getTypeface(), Typeface.BOLD_ITALIC);
                        invalidate();
                        break;
                }
            }

            if(mDeveloperDescriptionFontStyle != null){
                switch (mDeveloperDescriptionFontStyle) {
                    case FONT_STYLE_NORMAL:
                        mTextViewDeveloperDescription.setTypeface(mTextViewDeveloperDescription.getTypeface(), Typeface.NORMAL);
                        break;
                    case FONT_STYLE_ITALIC:
                        mTextViewDeveloperDescription.setTypeface(mTextViewDeveloperDescription.getTypeface(), Typeface.ITALIC);
                        break;
                    case FONT_STYLE_BOLD:
                        mTextViewDeveloperDescription.setTypeface(mTextViewDeveloperDescription.getTypeface(), Typeface.BOLD);
                        break;
                    case FONT_STYLE_BOLD_ITALIC:
                        String descriptionText         = mTextViewDeveloperDescription.getText().toString() + SPACE_CHAR;
                        mTextViewDeveloperDescription.setText(descriptionText);
                        mTextViewDeveloperDescription.setTypeface(mTextViewDeveloperDescription.getTypeface(), Typeface.BOLD_ITALIC);
                        invalidate();
                        break;
                }
            }
        }
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

    private void setBackgroundColor(View view,  String colorHex){
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
        view.setBackgroundColor(Color.parseColor(colorHexFixed == null ? colorHex : colorHexFixed));
    }

    View.OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            int id = v.getId();
            if(id == R.id.akn_project_ll_website_container){
                if(mProjectWebsiteUrl != null){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fixUrls(mProjectWebsiteUrl)));
                    getContext().startActivity(browserIntent);
                }
            }
            else if(id == R.id.akn_project_ll_github_container){
                if(mProjectGithubUrl != null){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fixUrls(mProjectGithubUrl)));
                    getContext().startActivity(browserIntent);
                }
            }
            else if(id == R.id.akn_project_ll_facebook_container){
                if(mProjectFacebookUrl != null){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fixUrls(mProjectFacebookUrl)));
                    getContext().startActivity(browserIntent);
                }
            }
            else if(id == R.id.akn_project_ll_twitter_container){
                if(mProjecTwitterUrl != null){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fixUrls(mProjecTwitterUrl)));
                    getContext().startActivity(browserIntent);
                }
            }
            else if(id == R.id.akn_project_ll_rate_us_container){

                if(mIsGooglePlayLinkAdded){
                    String appId = getContext().getPackageName();
                    try {
                        getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appId)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appId)));
                    }
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


    // SETTERS & GETTERS

    public void setProjectNameText(String projectNameText) {
        mTextViewProjectName.setText(projectNameText);
        invalidate();
    }

    public void setProjectDescriptionText(String projectDescriptionText) {
        mTextViewProjectDescription.setText(projectDescriptionText);
        invalidate();
    }

    public void setProjectVersionText(String projectVersionText) {
        mTextViewProjectVersion.setText(projectVersionText);
        invalidate();
    }

    public void setProjectDeveloperNameText(String projectDeveloperNameText) {
        mTextViewDeveloperName.setText(projectDeveloperNameText);
        invalidate();
    }

    public void setProjectDeveloperDescriptionText(String projectDeveloperDescriptionText) {
        mTextViewDeveloperDescription.setText(projectDeveloperDescriptionText);
        invalidate();
    }

    public void setProjectWebsiteText(String projectWebsiteText) {
        mTextViewProjectWebsite.setText(projectWebsiteText);
        invalidate();
    }

    public void setProjectGithubText(String projectGithubText) {
        mTextViewProjectGithub.setText(projectGithubText);
        invalidate();
    }

    public void setProjectFacebookText(String projectFacebookText) {
        mTextViewProjectFacebook.setText(projectFacebookText);
        invalidate();
    }

    public void setProjectTwitterText(String projectTwitterText) {
        mTextViewProjectTwitter.setText(projectTwitterText);
        invalidate();
    }

    public void setProjectGooglePlayText(String projectGooglePlayText) {
        mTextViewProjectGooglePlay.setText(projectGooglePlayText);
        invalidate();
    }

    public String getProjectNameText() {
        return mTextViewProjectName.getText().toString();
    }

    public String getProjectDescriptionText() {
        return mTextViewProjectDescription.getText().toString();
    }

    public String getProjectVersionText() {
        return mTextViewProjectVersion.getText().toString();
    }

    public String getProjectDeveloperNameText() {
        return mTextViewDeveloperName.getText().toString();
    }

    public String getProjectDeveloperDescriptionText() {
        return mTextViewDeveloperDescription.getText().toString();
    }

    public String getProjectWebsiteText() {
        return mTextViewProjectWebsite.getText().toString();
    }

    public String getProjectGithubText() {
        return mTextViewProjectGithub.getText().toString();
    }

    public String getProjectFacebookText() {
        return mTextViewProjectFacebook.getText().toString();
    }

    public String getProjectTwitterText() {
        return mTextViewProjectTwitter.getText().toString();
    }

    public String getProjectGooglePlayText() {
        return mTextViewProjectGooglePlay.getText().toString();
    }

    // URLs

    public void setProjectWebsiteUrl(String projectWebsiteUrl) {
        mProjectWebsiteUrl = projectWebsiteUrl;
    }

    public void setProjectGithubUrl(String projectGithubUrl) {
        mProjectGithubUrl = projectGithubUrl;
    }

    public void setProjectFacebookUrl(String projectFacebookUrl) {
        mProjectFacebookUrl = projectFacebookUrl;
    }

    public void setProjecTwitterUrl(String projecTwitterUrl) {
        mProjecTwitterUrl = projecTwitterUrl;
    }

    public String getProjectWebsiteUrl() {
        if(mProjectWebsiteUrl == null){
            Log.e(getContext().getClass().getSimpleName(), "Website link is null");
        }
        return mProjectWebsiteUrl != null ? mProjectWebsiteUrl : "";
    }

    public String getProjectGithubUrl() {
        if(mProjectGithubUrl == null){
            Log.e(getContext().getClass().getSimpleName(), "Github link is null");
        }
        return mProjectGithubUrl != null ? mProjectGithubUrl : "";
    }

    public String getProjectFacebookUrl() {
        if(mProjectFacebookUrl == null){
            Log.e(getContext().getClass().getSimpleName(), "Facebook link is null");
        }
        return mProjectFacebookUrl != null ? mProjectFacebookUrl : "";
    }

    public String getProjecTwitterUrl() {
        if(mProjecTwitterUrl == null){
            Log.e(getContext().getClass().getSimpleName(), "Twitter link is null");
        }
        return mProjecTwitterUrl != null ? mProjecTwitterUrl : "";
    }

    // Set drawables & link icons

    public void setLogoProjectDrawableId(int logoProjectDrawableId) {
        mLogoProject.setImageResource(logoProjectDrawableId);
        invalidate();
    }

    public void setLogoDeveloperDrawableId(int logoDeveloperDrawableId) {
        mLogoDeveloper.setImageResource(logoDeveloperDrawableId);
        invalidate();
    }

    public void setIconWebsiteDrawableId(int iconWebsiteDrawableId) {
        mIconWebsite.setImageResource(iconWebsiteDrawableId);
        invalidate();
    }

    public void setIconGithubDrawableId(int iconGithubDrawableId) {
        mIconGithub.setImageResource(iconGithubDrawableId);
        invalidate();
    }

    public void setIconFacebookDrawableId(int iconFacebookDrawableId) {
        mIconFacebook.setImageResource(iconFacebookDrawableId);
        invalidate();
    }

    public void setIconTwitterDrawableId(int iconTwitterDrawableId) {
        mIconTwitter.setImageResource(iconTwitterDrawableId);
        invalidate();
    }

    public void setIconGooglePlayDrawableId(int iconGooglePlayDrawableId) {
        mIconGooglePlay.setImageResource(iconGooglePlayDrawableId);
        invalidate();
    }

    public ImageView getLogoProject() {
        mLogoProject.setVisibility(VISIBLE);
        return mLogoProject;
    }

    public ImageView getLogoDeveloper() {
        mLogoDeveloper.setVisibility(VISIBLE);
        return mLogoDeveloper;
    }

    public ImageView getIconWebsite() {
        if(mIconWebsite == null){
            Log.e(getContext().getClass().getSimpleName(), "Website icon is null or hidden");
        }
        return mIconWebsite;
    }

    public ImageView getIconGithub() {
        if(mIconGithub == null){
            Log.e(getContext().getClass().getSimpleName(), "Github icon is null or hidden");
        }
        return mIconGithub;
    }

    public ImageView getIconFacebook() {
        if(mIconFacebook == null){
            Log.e(getContext().getClass().getSimpleName(), "Facebook icon is null or hidden");
        }
        return mIconFacebook;
    }

    public ImageView getIconTwitter() {
        if(mIconTwitter == null){
            Log.e(getContext().getClass().getSimpleName(), "Twitter icon is null or hidden");
        }
        return mIconTwitter;
    }

    public ImageView getIconGooglePlay() {
        if(mIconGooglePlay == null){
            Log.e(getContext().getClass().getSimpleName(), "Google Play icon is null or hidden");
        }
        return mIconGooglePlay;
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
        DrawableCompat.setTint(mIconWebsite.getDrawable(), ContextCompat.getColor(getContext(), colorId));
        invalidate();
    }
    public void setGithubIconTintColor(int colorId){
        DrawableCompat.setTint(mIconGithub.getDrawable(), ContextCompat.getColor(getContext(), colorId));
        invalidate();
    }
    public void setFacebookIconTintColor(int colorId){
        DrawableCompat.setTint(mIconFacebook.getDrawable(), ContextCompat.getColor(getContext(), colorId));
        invalidate();
    }
    public void setTwitterIconTintColor(int colorId){
        DrawableCompat.setTint(mIconTwitter.getDrawable(), ContextCompat.getColor(getContext(), colorId));
        invalidate();
    }
    public void setGooglePlayIconTintColor(int colorId){
        DrawableCompat.setTint(mIconGooglePlay.getDrawable(), ContextCompat.getColor(getContext(), colorId));
        invalidate();
    }

    public void setWebsiteIconTintColor(String colorHexCode){
        setIconTintColor(mIconWebsite, colorHexCode);
        invalidate();
    }
    public void setGithubIconTintColor(String colorHexCode){
        setIconTintColor(mIconGithub, colorHexCode);
        invalidate();
    }
    public void setFacebookIconTintColor(String colorHexCode){
        setIconTintColor(mIconFacebook, colorHexCode);
        invalidate();
    }
    public void setTwitterIconTintColor(String colorHexCode){
        setIconTintColor(mIconTwitter, colorHexCode);
        invalidate();
    }
    public void setGooglePlayIconTintColor(String colorHexCode){
        setIconTintColor(mIconGooglePlay, colorHexCode);
        invalidate();
    }

    // Set photo & icon drawables ***************************************************
    public void setProjectLogoDrawable(int drawableId){
        mLogoProject.setVisibility(VISIBLE);
        mLogoProject.setImageResource(drawableId);
        invalidate();
    }
    public void setDeveloperLogoDrawable(int drawableId){
        mLogoDeveloper.setVisibility(VISIBLE);
        mLogoDeveloper.setImageResource(drawableId);
        invalidate();
    }
    public void setWebsiteIconDrawable(int drawableId){
        mIconWebsite.setImageResource(drawableId);
        invalidate();
    }
    public void setGithubIconDrawable(int drawableId){
        mIconGithub.setImageResource(drawableId);
        invalidate();
    }
    public void setFacebookIconDrawable(int drawableId){
        mIconFacebook.setImageResource(drawableId);
        invalidate();
    }
    public void setTwitterIconDrawable(int drawableId){
        mIconTwitter.setImageResource(drawableId);
        invalidate();
    }
    public void setGooglePlayIconDrawable(int drawableId){
        mIconGooglePlay.setImageResource(drawableId);
        invalidate();
    }

    // Set text colors ***************************************************
    public void setTextColor(int colorId){
        List<TextView> textViewList = new ArrayList<>();
        textViewList.add(mTextViewProjectName);
        textViewList.add(mTextViewProjectDescription);
        textViewList.add(mTextViewDeveloperName);
        textViewList.add(mTextViewDeveloperDescription);
        textViewList.add(mTextViewProjectWebsite);
        textViewList.add(mTextViewProjectGithub);
        textViewList.add(mTextViewProjectFacebook);
        textViewList.add(mTextViewProjectTwitter);
        textViewList.add(mTextViewProjectGooglePlay);

        setAllTextViewsColor(textViewList, colorId);
    }
    public void setTextColorProjectName(int colorId){
        setTextViewColor(mTextViewProjectName, colorId);
        invalidate();
    }
    public void setTextColorProjectDescription(int colorId){
        setTextViewColor(mTextViewProjectDescription, colorId);
        invalidate();
    }
    public void setTextColorDeveloperName(int colorId){
        setTextViewColor(mTextViewDeveloperName, colorId);
        invalidate();
    }
    public void setTextColorDeveloperDescription(int colorId){
        setTextViewColor(mTextViewDeveloperDescription, colorId);
        invalidate();
    }
    public void setTextColorWebsite(int colorId){
        setTextViewColor(mTextViewProjectWebsite, colorId);
        invalidate();
    }
    public void setTextColorGithub(int colorId){
        setTextViewColor(mTextViewProjectGithub, colorId);
        invalidate();
    }
    public void setTextColorFacebook(int colorId){
        setTextViewColor(mTextViewProjectFacebook, colorId);
        invalidate();
    }
    public void setTextColorTwitter(int colorId){
        setTextViewColor(mTextViewProjectTwitter, colorId);
        invalidate();
    }
    public void setTextColorGooglePlay(int colorId){
        setTextViewColor(mTextViewProjectGooglePlay, colorId);
        invalidate();
    }

    public void setTextColor(String colorHexCode){
        List<TextView> textViewList = new ArrayList<>();
        textViewList.add(mTextViewProjectName);
        textViewList.add(mTextViewProjectDescription);
        textViewList.add(mTextViewDeveloperName);
        textViewList.add(mTextViewDeveloperDescription);
        textViewList.add(mTextViewProjectWebsite);
        textViewList.add(mTextViewProjectGithub);
        textViewList.add(mTextViewProjectFacebook);
        textViewList.add(mTextViewProjectTwitter);
        textViewList.add(mTextViewProjectGooglePlay);

        setAllTextViewsColor(textViewList, colorHexCode);
    }
    public void setTextColorName(String colorHexCode){
        setTextViewColor(mTextViewProjectName, colorHexCode);
        invalidate();
    }
    public void setTextColorDescription(String colorHexCode){
        setTextViewColor(mTextViewProjectDescription, colorHexCode);
        invalidate();
    }
    public void setTextColorDeveloperName(String colorHexCode){
        setTextViewColor(mTextViewDeveloperName, colorHexCode);
        invalidate();
    }
    public void setTextColorDeveloperDescription(String colorHexCode){
        setTextViewColor(mTextViewDeveloperDescription, colorHexCode);
        invalidate();
    }
    public void setTextColorWebsite(String colorHexCode){
        setTextViewColor(mTextViewProjectWebsite, colorHexCode);
        invalidate();
    }
    public void setTextColorGithub(String colorHexCode){
        setTextViewColor(mTextViewProjectGithub, colorHexCode);
        invalidate();
    }
    public void setTextColorFacebook(String colorHexCode){
        setTextViewColor(mTextViewProjectFacebook, colorHexCode);
        invalidate();
    }
    public void setTextColorTwitter(String colorHexCode){
        setTextViewColor(mTextViewProjectTwitter, colorHexCode);
        invalidate();
    }
    public void setTextColorGooglePlay(String colorHexCode){
        setTextViewColor(mTextViewProjectGooglePlay, colorHexCode);
        invalidate();
    }
}
