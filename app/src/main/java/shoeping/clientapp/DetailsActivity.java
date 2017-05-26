package shoeping.clientapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_SERIAL_NUMBER = "serialNumber";

    ArrayAdapter<String> list;

    Spinner spinner;

    String serialNumber;
    String[] blank = {"example", "cancel"};
    String[] sizeList;

    DatabaseManager databaseManager;

    String size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Resources resources = getResources();
        databaseManager = new DatabaseManager();
        Button orderButton = (Button) findViewById(R.id.directOrderBtn);

        serialNumber = getIntent().getStringExtra(EXTRA_SERIAL_NUMBER);

        spinner = (Spinner) findViewById(R.id.selectSize);
        spinner.setPrompt("사이즈 선택");

        list = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, blank);
        spinner.setAdapter(list);
        spinner.setOnItemSelectedListener(this);

        size = null;

        databaseManager.requestGetAvailableSize(serialNumber);

        //TODO: 여기에 Spinner와, 그 값을 받아서 넘기는 코드 추가.

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(size!=null)
                {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.putExtra("size", size);
                    intent.putExtra(EXTRA_SERIAL_NUMBER, serialNumber);
                    context.startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "사이즈 미지정 오류", Toast.LENGTH_SHORT).show();
                }
            }
        });

        databaseManager.setLoadCompleteListener(new DatabaseManager.LoadCompleteListener() {
            @Override
            public void onLoadComplete() {

                newAdapter();
            }

            @Override
            public void onLoadFail() {

            }
        });

        int picture = resources.getIdentifier(
                "@drawable/" + serialNumber, "drawable", getPackageName());
        ImageView placePicutre = (ImageView) findViewById(R.id.detailImg);
        placePicutre.setImageResource(picture);
    }

    public void newAdapter()
    {
        blank = null;
        blank = new String[databaseManager.getSizeInfo().length];
        blank = databaseManager.getSizeInfo();
        list = new ArrayAdapter<> (this, android.R.layout.simple_spinner_item, blank);
        spinner.setAdapter(list);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView tv = (TextView)view;
        TextView bb = (TextView)findViewById(R.id.chosenSize);
        bb.setText(tv.getText());
        size = tv.getText().toString();
        Toast.makeText(getApplicationContext(), "size : "+size, Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0)
    {

    }
}