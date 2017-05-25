package shoeping.clientapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        String idToken = getIntent().getStringExtra("idToken");
        String productToken = getIntent().getStringExtra("productToken");
        String name;
        String address;
        String phoneNo;

        ImageView imageView = (ImageView) findViewById(R.id.orderImv);

        final DatabaseManager databaseManager = DatabaseManager.getInstance();

        TextView textView_id = (TextView) findViewById(R.id.idTxv);
        EditText editText_address = (EditText) findViewById(R.id.addressEdt);
        EditText editText_phone = (EditText) findViewById(R.id.phoneEdt);
        EditText editText_comment = (EditText) findViewById(R.id.commentEdt);
        Button button_order = (Button) findViewById(R.id.orderBtn);



        imageView.setImageResource(
                getResources().getIdentifier(
                        "@drawable/" + productToken, "drawable", getPackageName()));

        databaseManager.requestUserInfo();

        databaseManager.setLoadCompleteListener(new DatabaseManager.LoadCompleteListener() {
            @Override
            public void onLoadComplete(boolean isData) {
                DatabaseManager.UserInfo userInfo = databaseManager.getUserInfo();
                name = userInfo.getName();
            }
        });

      /*  textView_id.setText();
        editText_address.setText()*/

        button_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
