package shoeping.clientapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    String _name;
    String _adress;
    String _phoneNo;

    TextView textView_name;
    EditText editText_address;
    EditText editText_phone;
    EditText editText_comment;

    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        String idToken = getIntent().getStringExtra("idToken");
        String serialNumber = getIntent().getStringExtra("serialNumber");

        ImageView imageView = (ImageView) findViewById(R.id.orderImv);

        databaseManager = DatabaseManager.getInstance();

        textView_name = (TextView) findViewById(R.id.nameTxv);
        editText_address = (EditText) findViewById(R.id.addressEdt);
        editText_phone = (EditText) findViewById(R.id.phoneEdt);
        editText_comment = (EditText) findViewById(R.id.commentEdt);
        Button button_order = (Button) findViewById(R.id.orderBtn);

        imageView.setImageResource(
                getResources().getIdentifier(
                        "@drawable/" + serialNumber, "drawable", getPackageName()));

        databaseManager.requestGetUserInfo();

        databaseManager.setLoadCompleteListener(new DatabaseManager.LoadCompleteListener() {
            @Override
            public void onLoadComplete(boolean isData) {
                DatabaseManager.UserInfo userInfo = databaseManager.getUserInfo();
                _name = userInfo.name;
                _adress = userInfo.address;
                _phoneNo = userInfo.phoneNo;
            }
        });


        button_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseManager.requestSetUserInfo();
            }
        });

        databaseManager.setLoadCompleteListener(new DatabaseManager.LoadCompleteListener() {
            @Override
            public void onLoadComplete(boolean isData) {
                Toast.makeText(OrderActivity.this, "주문 완료", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
