package shoeping.clientapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_SERIAL_NUMBER = "serialNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Resources resources = getResources();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        Button orderButton = (Button) findViewById(R.id.directOrderBtn);

        String serialNumber = getIntent().getStringExtra(EXTRA_SERIAL_NUMBER);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        });

        int picture = resources.getIdentifier(
                "@drawable/" + serialNumber, "drawable", getPackageName());
        ImageView placePicutre = (ImageView) findViewById(R.id.detailImg);
        placePicutre.setImageResource(picture);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
