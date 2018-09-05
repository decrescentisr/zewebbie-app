package com.example.decrescentisr.zewebbie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * Created by decrescentisr on 7/27/2017.
 */

public class OrderSummaryActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        Toolbar menuToolbar = (Toolbar) findViewById(R.id.order_summary_toolbar);
        setSupportActionBar(menuToolbar);

        getSupportActionBar().setTitle(getString(R.string.order_summary_title));

        Intent intent = getIntent();
        String teaName = intent.getStringExtra(OrderActivity.EXTRA_WEB_NAME);
        int price = intent.getIntExtra(OrderActivity.EXTRA_TOTAL_PRICE, 0);
        String size = intent.getStringExtra(OrderActivity.EXTRA_TYPE);
        String milkType = intent.getStringExtra(OrderActivity.EXTRA_ADDON_TYPE);
        int quantity = intent.getIntExtra(OrderActivity.EXTRA_QUANTITY, 0);

        displayOrderSummary(teaName, price, size, milkType, quantity);
    }

    private void displayOrderSummary(String teaName, int price, String size, String milkType, int quantity){

        TextView webNameTextView = (TextView) findViewById(
                R.id.summary_web_name);
        webNameTextView.setText(teaName);

        TextView quantityTextView = (TextView) findViewById(
                R.id.summary_quantity);
        quantityTextView.setText(String.valueOf(quantity));

        TextView typeTextView = (TextView) findViewById(
                R.id.summary_web_type);
        typeTextView.setText(size);

        TextView addonTextView = (TextView) findViewById(
                R.id.summary_addon_type);
        addonTextView.setText(milkType);


        TextView priceTextView = (TextView) findViewById(
                R.id.summary_total_price);

        String convertPrice = NumberFormat.getCurrencyInstance().format(price);
        priceTextView.setText(convertPrice);

    }

    public void sendEmail(View view){

        String emailMessage = getString(R.string.email_message);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.order_summary_email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, emailMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            return true;
        } else if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);

            return true;
        } else if (id == R.id.action_order){
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


