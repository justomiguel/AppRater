package org.codechimp.appraterdemo;

import org.codechimp.apprater.AmazonMarket;
import org.codechimp.apprater.AppRater;
import org.codechimp.apprater.GoogleMarket;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;

public class MainActivity extends Activity {

    private AppRater appRater = new AppRater.Builder().build();
	private Button buttonTest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		
		buttonTest = (Button) findViewById(R.id.button1);
		
		buttonTest.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				// This forces display of the rate prompt.
				// It should only be used for testing purposes
                appRater.showRateDialog(v.getContext());
			}
		});


        // Optionally you can set the Market you want to use prior to calling app_launched
        // If setMarket not called it will default to Google Play
        // Current implementations are Google Play and Amazon App Store, you can add your own by implementing Market
        // AppRater.setMarket(new GoogleMarket());
        // AppRater.setMarket(new AmazonMarket());

        // This will keep a track of when the app was first used and whether to show a prompt
		// It should be the default implementation of AppRater
        appRater.appLaunched(this);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case (R.id.menu_ratenow): {
                appRater.rateNow(this);
                return true;
            }
        }
        return false;
    }
}
