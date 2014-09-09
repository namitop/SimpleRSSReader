package com.example.shiraki_hirotomo.simplerssreader;

/**
 * Created by shiraki-hirotomo on 2014/09/08.
 */
public class Item {
    // 記事のタイトル
    private CharSequence mTitle;
    // 記事の本文
    private CharSequence mDescription;

    public Item() {
        mTitle = "";
        mDescription = "";
    }

    public CharSequence getDescription() {
        return mDescription;
    }

    public void setDescription(CharSequence description) {
        mDescription = description;
    }

    public CharSequence getTitle() {
        return mTitle;
    }

    public void setTitle(CharSequence title) {
        mTitle = title;
    }
}
