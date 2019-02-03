# AboutScreen
A fully customizable Android About Screen UI.

> **Libraries** & **Contributors** sections are under development.

![](https://raw.githubusercontent.com/akndmr/AboutScreen/master/AboutScreen.gif)

## Setup

**Step 1.**
Add the JitPack repository to your build file**

**Gradle**
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
**Step 2.** 
Add the dependency

	dependencies {
	        implementation 'com.github.akndmr:AboutScreen:v1.0.0'
	}

## Types & Usage
Decide which type you will use:

**1. Personal AboutScreen**
If you want to add just your personal information (About Me) including your photo, name, description & social links, use this layout.

![AboutScreen library - Personal AboutScreen](https://raw.githubusercontent.com/akndmr/AboutScreen/master/PersonalAboutMe.PNG)

      <io.github.akndmr.aboutscreen.AboutScreenPersonalLayout  
          android:id="@+id/about_screen_layout"  
          android:layout_width="match_parent"  
          android:layout_height="wrap_content"/>

**2. Project AboutScreen**
If you want to add project information with developer(personal/company) details, use this layout.

![AboutScreen library - Project AboutScreen](https://raw.githubusercontent.com/akndmr/AboutScreen/master/ProjecAboutMe.PNG)

    <io.github.akndmr.aboutscreen.AboutScreenProjectLayout
          android:id="@+id/about_screen_layout"  
          android:layout_width="match_parent"  
          android:layout_height="wrap_content"/>

## Customization

**1. XML Customization**

**Personal AboutScreen**

| Attributes | Formats | Example | Scope
|--|--|--|--|
|  cardRadius| dimension| 8dp
|  cardElevation| dimension| 2dp
|  cardBackgroundColor| color| @color/primary or "#333"
|  textName, textDescription, textWebsite, textGithub, textFacebook, textTwitter, textGooglePlay| reference/string| @string/myname or "Akın Demir"
|  profilePhoto| reference/integer| @drawable/photo
|  iconWebsite, iconGithub, iconFacebook, iconTwitter, iconGooglePlay| reference/integer| @drawable/ic_website
|  urlWebsite, urlGithub, urlFacebook, urlTwitter | reference/string| @string/my_url or "akndmr.github.io"
|  **isGooglePlayLinkEnabled**| boolean| "true"
|  iconColor| color| @color/accent or "#eee" | All icons
|  iconColorWebsite, iconColorGithub, iconColorFacebook, iconColorTwitter, iconColorGooglePlay| color| @color/accent or "#eee"
|  textColor| color| @color/white or "#fff" | All texts
|  textColorLinks| color| @color/white or "#fff" | All links
|  textColorName, textColorDescription, textColorWebsite, textColorGithub, textColorFacebook, textColorTwitter, textColorGooglePlay| color| @color/primary or "#ddd"
|  textFontId| reference/integer| @font/roboto| All texts
|  textLinksFontId| reference/integer| @font/roboto| All links
|  textNameFontId, textDescriptionFontId| reference/integer| @font/roboto
|  textFontStyle| reference/string| @string/font_bold or "NORMAL" or "ITALIC" or "BOLD" or "BOLD_ITALIC"| All texts
|  textLinksFontStyle| reference/string| @string/font_bold or "NORMAL" or "ITALIC" or "BOLD" or "BOLD_ITALIC"| All links
|  textNameFontStyle, textDescriptionFontStyle| reference/string| @string/font_bold or "NORMAL" or "ITALIC" or "BOLD" or "BOLD_ITALIC"


**Project AboutScreen**

| Attributes | Formats | Example | Scope
|--|--|--|--|
|  cardRadiusProject| dimension| 8dp
|  cardElevationProject| dimension| 2dp
|  cardBackgroundColorProject| color| @color/primary or "#333"
|  textProjectName, textProjectDescription, textProjectVersion, textDevelopedBy, textDeveloperDescription, textProjectWebsite, textProjectGithub, textProjectFacebook, textProjectTwitter, textProjectGooglePlay| reference/string| @string/myname or "Akın Demir"
|  projectLogo| reference/integer| @drawable/logo_project
|  developerLogo| reference/integer| @drawable/logo_dev
|  iconProjectWebsite, iconProjectGithub, iconProjectFacebook, iconProjectTwitter, iconProjectGooglePlay| reference/integer| @drawable/ic_website
|  urlProjectWebsite, urlProjectGithub, urlProjectFacebook, urlProjectTwitter| reference/string| @string/my_url or "akndmr.github.io"
|  **isProjectGooglePlayLinkEnabled**| boolean| "true"
|  iconColorProject| color| @color/accent or "#eee" | All icons
|  iconColorProjectWebsite, iconColorProjectGithub, iconColorProjectFacebook, iconColorProjectTwitter, iconColorProjectGooglePlay| color| @color/accent or "#eee"
|  dividerColor| color| @color/accent or "#eee"
|  textColorProject| color| @color/white or "#fff" | All texts
|  textColorProjectLinks| color| @color/white or "#fff" | All links
|  textColorProjectName, textColorProjectVersion, textColorProjectDescription, textColorDevelopedBy, textColorDeveloperDescription, textColorProjectWebsite, textColorProjectGithub, textColorProjectFacebook, textColorProjectTwitter, textColorProjectGooglePlay| color| @color/primary or "#ddd"
|  textProjectFontId| reference/integer| @font/roboto| All texts
|  textProjectLinksFontId| reference/integer| @font/roboto| All links
|  textProjectNameFontId, textProjectDescriptionFontId, textProjectVersionFontId, textDevelopedByFontId, textDeveloperDescriptionFontId, textProjectLinksFontId| reference/integer| @font/roboto
|  textProjectFontStyle| reference/string| @string/font_bold or "NORMAL" or "ITALIC" or "BOLD" or "BOLD_ITALIC"| All texts
|  textProjectLinksFontStyle| reference/string| @string/font_bold or "NORMAL" or "ITALIC" or "BOLD" or "BOLD_ITALIC"| All links
|  textProjectNameFontStyle, textProjectDescriptionFontStyle, textProjectVersionFontStyle, textDevelopedByFontStyle, textDeveloperDescriptionFontStyle| reference/string| @string/font_bold or "NORMAL" or "ITALIC" or "BOLD" or "BOLD_ITALIC"
|  **shouldGetVersionFromGradle**| boolean| "true"


**2. JAVA**

    AboutScreenPersonalLayout asp= (AboutScreenPersonalLayout) findViewById(R.id.about_screen_layout);  
      
    asp.setDescriptionText("Akın is an Android Developer since...");
    asp.setLinkGithub("akndmr.github.io");
	asp.setWebsiteIcon(R.drawable.ic_website);
	asp.setWebsiteIconTintColor(R.color.colorIconDefault);
	asp.setGithubIconTintColor("#333");
	asp.setProfilePhotoDrawable(R.drawable.profile_placeholder);
	asp.setTextColor(R.color.colorLightGray);
	asp.showGooglePlayLink();

## NOTES

1. All icons have OnClickListeners set up already. You just need to add urls. 
Your urls don't have to include **http://** or **https://**. OnClickListeners check this case & fix it.
2. If you **enable Google Play link**, it will automatically direct user to app's market page via Google Play Store app. If can't find or an error occurs, it will open it in default browser. Google Play Store link will be set up with package name.
3. **shouldGetVersionFromGradle**  is false by default and you manually set version text value ***(textProjectVersion)*** . If you set as **true**, the version text value will be gotten from PackageManager.
4. All textviews' visibility is ***GONE*** by default. If you set a value, they will be visible.

## Apps Using AboutScreen
You can create an issue and type your app name, logo *(100x100 px)* url and link to market. We will add them in this list.

1. [Popvie](https://play.google.com/store/apps/dev?id=5768767299150574777)
![Popvie App](https://raw.githubusercontent.com/akndmr/project-ss/master/popvie_logo.png =100x100)

## License
MIT License

Copyright (c) 2019 Akın Demir

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
