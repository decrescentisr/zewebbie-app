package com.example.decrescentisr.zewebbie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;

public class OrderActivity extends AppCompatActivity {

    AppCompatImageButton myImageButton;

    private int mQuantity = 0;
    private int mTotalPrice = 0;

    private static final int BASIC_PRICE = 250;
    private static final int ADVANCED_PRICE = 650;
    private static final int INTERMEDIATE_PRICE = 750;

    private static final int BUSINESS_LOGO_PRICE = 30;

    private static final String WEB_TYPE_BASIC = "Basic ($250)";
    private static final String WEB_TYPE_ADVANCED = "Advanced ($650)";
    private static final String WEB_TYPE_INTERMEDIATE = "Intermediate ($750)";

    private static final String ADDON_BUSINESS_LOGO = "Business Logo ($30)";


    private String mAddonType;
    private String mWebName = "";
    private String mType;

    public final static String EXTRA_TOTAL_PRICE = "com.example.decrescentisr.zewebbie.EXTRA_TOTAL_PRICE";
    public final static String EXTRA_WEB_NAME = "com.example.decrescentisr.zewebbie.EXTRA_WEB_NAME";
    public final static String EXTRA_TYPE = "com.example.decrescentisr.zewebbie.EXTRA_TYPE";
    public final static String EXTRA_ADDON_TYPE = "com.example.decrescentisr.zewebbie.EXTRA_ADDON_TYPE";
    public final static String EXTRA_QUANTITY = "com.example.decrescentisr.zewebbie.EXTRA_QUANTITY";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar menuToolbar = (Toolbar) findViewById(R.id.order_toolbar);
        setSupportActionBar(menuToolbar);

        myImageButton = (AppCompatImageButton) findViewById(R.id.appCompatImageButton);

        myImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentLoadNewActivity = new Intent(OrderActivity.this,MainActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });

        getSupportActionBar().setTitle(getString(R.string.order_title));

        Intent intent = getIntent();

        mWebName = intent.getStringExtra(MenuActivity.EXTRA_WEB_NAME);

        TextView teaNameTextView = (TextView) findViewById(R.id.web_name_text_view);
        teaNameTextView.setText(mWebName);

        TextView costTextView = (TextView) findViewById(R.id.cost_text_view);
        costTextView.setText(getString(R.string.initial_cost));

        setupTypeSpinner();
        setupAddonSpinner();

    }

    private void setupTypeSpinner(){
        Spinner mSizeSpinner = (Spinner) findViewById(R.id.web_type_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.web_type_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSizeSpinner.setAdapter(adapter);

        mSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mType = getString(R.string.web_type_basic);
                        break;
                    case 1:
                        mType = getString(R.string.web_type_advanced);
                        break;
                    case 2:
                        mType = getString(R.string.web_type_intermediate);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mType = getString(R.string.web_type_basic);
            }
        });
    }

    private void setupAddonSpinner(){
        Spinner mSizeSpinner = (Spinner) findViewById(R.id.addon_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.addon_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSizeSpinner.setAdapter(adapter);

        mSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mAddonType = getString(R.string.addon_type_none);
                        break;
                    case 1:
                        mAddonType = getString(R.string.addon_type_businesslogo);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mAddonType = getString(R.string.addon_type_none);
            }
        });
    }


    public void increment(View view){

        mQuantity = mQuantity + 1;
        displayQuantity(mQuantity);
        mTotalPrice = calculatePrice();
        displayCost(mTotalPrice);
    }

    public void decrement(View view){
        if (mQuantity > 0){

            mQuantity = mQuantity - 1;
            displayQuantity(mQuantity);
            mTotalPrice = calculatePrice();
            displayCost(mTotalPrice);

        }
    }

    private int calculatePrice(){
        switch (mType) {
            case WEB_TYPE_BASIC:
                mTotalPrice = mQuantity * BASIC_PRICE;
                break;
            case WEB_TYPE_ADVANCED:
                mTotalPrice = mQuantity * ADVANCED_PRICE;
                break;
            case WEB_TYPE_INTERMEDIATE:
                mTotalPrice = mQuantity * INTERMEDIATE_PRICE;
                break;
            case ADDON_BUSINESS_LOGO:
                mTotalPrice = BUSINESS_LOGO_PRICE + BASIC_PRICE * mQuantity;

                break;
        }
        return mTotalPrice;
    }

    private void displayQuantity(int numberOfWebs) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(numberOfWebs));
    }

    private void displayCost(int totalPrice) {
        TextView costTextView = (TextView) findViewById(
                R.id.cost_text_view);

        String convertPrice = NumberFormat.getCurrencyInstance().format(totalPrice);
        costTextView.setText(convertPrice);
    }

    public void submitOrder(View view) {

        Intent intent = new Intent(OrderActivity.this, OrderSummaryActivity.class);
        intent.putExtra(EXTRA_TOTAL_PRICE, mTotalPrice);
        intent.putExtra(EXTRA_WEB_NAME, mWebName);
        intent.putExtra(EXTRA_TYPE, mType);
        intent.putExtra(EXTRA_ADDON_TYPE, mAddonType);
        intent.putExtra(EXTRA_QUANTITY, mQuantity);

        startActivity(intent);
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
