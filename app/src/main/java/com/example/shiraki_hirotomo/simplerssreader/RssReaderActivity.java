package com.example.shiraki_hirotomo.simplerssreader;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;


public class RssReaderActivity extends ListActivity {
    public static final String RSS_FEED_URL = "http://create.kayac.com/feed/";
    //public static final String RSS_FEED_URL = "http://itpro.nikkeibp.co.jp/rss/ITpro.rdf";
    public static final int MENU_ITEM_RELOAD = Menu.FIRST;
    private ArrayList<Item> mItems;
    private RssListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_reader);

        // Itemオブジェクトを保持するためのリストを生成し、アダプタに追加する
        mItems = new ArrayList();
        mAdapter = new RssListAdapter(this, mItems);

//        // アダプタをリストビューにセットする
//        setListAdapter(mAdapter);
//
//        // サンプル用に空のItemオブジェクトをセットする
//        for (int i = 0; i < 10; i++) {
//            mAdapter.add(new Item());
//        }
        // タスクを起動する
        RssParserTask task = new RssParserTask(this, mAdapter);
        task.execute(RSS_FEED_URL);

    }

    // リストの項目を選択した時の処理
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        Item item = mItems.get(position);
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra("TITLE", item.getTitle());
        intent.putExtra("DESCRIPTION", item.getDescription());
        startActivity(intent);
    }

    // MENUボタンを押したときの処理
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        // デフォルトではアイテムを追加した順番通りに表示する
        menu.add(0, MENU_ITEM_RELOAD, 0, "更新");
        return result;
    }

    // MENUの項目を押したときの処理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 更新
            case MENU_ITEM_RELOAD:
                // アダプタを初期化し、タスクを起動する
                mItems = new ArrayList();
                mAdapter = new RssListAdapter(this, mItems);
                // タスクはその都度生成する
                RssParserTask task = new RssParserTask(this, mAdapter);
                task.execute(RSS_FEED_URL);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
