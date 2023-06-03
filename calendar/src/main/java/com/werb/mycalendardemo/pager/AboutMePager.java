package com.werb.mycalendardemo.pager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.werb.mycalendardemo.R;
import com.werb.mycalendardemo.R2;
import com.werb.mycalendardemo.utils.BusProvider;
import com.werb.mycalendardemo.utils.Events;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by acer-pc on 2016/5/14.
 */
public class AboutMePager extends BasePager implements View.OnClickListener {

    @BindView(R2.id.about_bg)
    LinearLayout about_bg;
    @BindView(R2.id.github)
    TextView github;
    @BindView(R2.id.weibo)
    TextView weibo;

    public AboutMePager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.aboutme_pager, null);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void initData() {
        about_bg.setOnClickListener(v -> BusProvider.getInstance().send(new Events.AgendaListViewTouchedEvent()));

        github.setOnClickListener(this);
        weibo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.github) {
            Intent it1 = new Intent(Intent.ACTION_VIEW, Uri.parse(github.getText().toString()));
            it1.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
            mActivity.startActivity(it1);
        } else if (id == R.id.weibo) {
            Intent it2 = new Intent(Intent.ACTION_VIEW, Uri.parse(weibo.getText().toString()));
            it2.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
            mActivity.startActivity(it2);
        }
    }
}
