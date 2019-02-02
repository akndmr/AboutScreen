package io.github.akndmr.aboutscreen;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Akın DEMİR on 30.01.2019.
 */
public class AboutData implements Parcelable {

    private String name, description, websiteLink, githubLink, facebookLink, twitterLink;
    private int websiteIconDrawableId, githubIconDrawableId, facebookIconDrawableId, twitterIconDrawableId, googlePlayIconDrawableId;
    private boolean enableGooglePlayLink;

    public AboutData() {
    }

    public AboutData(@NonNull String name,
                     @Nullable String description,
                     @Nullable String websiteLink,
                     @Nullable String githubLink,
                     @Nullable String facebookLink,
                     @Nullable String twitterLink,
                     @Nullable Integer websiteIconDrawableId,
                     @Nullable Integer githubIconDrawableId,
                     @Nullable Integer facebookIconDrawableId,
                     @Nullable Integer twitterIconDrawableId,
                     @Nullable Integer googlePlayIconDrawableId,
                     @NonNull Boolean enableGooglePlayLink) {
        this.name = name;
        this.description = description;
        this.websiteLink = websiteLink;
        this.githubLink = githubLink;
        this.facebookLink = facebookLink;
        this.twitterLink = twitterLink;
        this.websiteIconDrawableId = websiteIconDrawableId;
        this.githubIconDrawableId = githubIconDrawableId;
        this.facebookIconDrawableId = facebookIconDrawableId;
        this.twitterIconDrawableId = twitterIconDrawableId;
        this.googlePlayIconDrawableId = googlePlayIconDrawableId;
        this.enableGooglePlayLink = enableGooglePlayLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public int getWebsiteIconDrawableId() {
        return websiteIconDrawableId;
    }

    public void setWebsiteIconDrawableId(int websiteIconDrawableId) {
        this.websiteIconDrawableId = websiteIconDrawableId;
    }

    public int getGithubIconDrawableId() {
        return githubIconDrawableId;
    }

    public void setGithubIconDrawableId(int githubIconDrawableId) {
        this.githubIconDrawableId = githubIconDrawableId;
    }

    public int getFacebookIconDrawableId() {
        return facebookIconDrawableId;
    }

    public void setFacebookIconDrawableId(int facebookIconDrawableId) {
        this.facebookIconDrawableId = facebookIconDrawableId;
    }

    public int getTwitterIconDrawableId() {
        return twitterIconDrawableId;
    }

    public void setTwitterIconDrawableId(int twitterIconDrawableId) {
        this.twitterIconDrawableId = twitterIconDrawableId;
    }

    public int getGooglePlayIconDrawableId() {
        return googlePlayIconDrawableId;
    }

    public void setGooglePlayIconDrawableId(int googlePlayIconDrawableId) {
        this.googlePlayIconDrawableId = googlePlayIconDrawableId;
    }

    public boolean isEnableGooglePlayLink() {
        return enableGooglePlayLink;
    }

    public void setEnableGooglePlayLink(boolean enableGooglePlayLink) {
        this.enableGooglePlayLink = enableGooglePlayLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.websiteLink);
        dest.writeString(this.githubLink);
        dest.writeString(this.facebookLink);
        dest.writeString(this.twitterLink);
        dest.writeInt(this.websiteIconDrawableId);
        dest.writeInt(this.githubIconDrawableId);
        dest.writeInt(this.facebookIconDrawableId);
        dest.writeInt(this.twitterIconDrawableId);
        dest.writeInt(this.googlePlayIconDrawableId);
        dest.writeByte(this.enableGooglePlayLink ? (byte) 1 : (byte) 0);
    }

    protected AboutData(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.websiteLink = in.readString();
        this.githubLink = in.readString();
        this.facebookLink = in.readString();
        this.twitterLink = in.readString();
        this.websiteIconDrawableId = in.readInt();
        this.githubIconDrawableId = in.readInt();
        this.facebookIconDrawableId = in.readInt();
        this.twitterIconDrawableId = in.readInt();
        this.googlePlayIconDrawableId = in.readInt();
        this.enableGooglePlayLink = in.readByte() != 0;
    }

    public static final Parcelable.Creator<AboutData> CREATOR = new Parcelable.Creator<AboutData>() {
        @Override
        public AboutData createFromParcel(Parcel source) {
            return new AboutData(source);
        }

        @Override
        public AboutData[] newArray(int size) {
            return new AboutData[size];
        }
    };
}
