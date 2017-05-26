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

    UserInfo userInfo;

    String idToken;
    String serialNumber;
    String size;

    TextView textView_name;
    EditText editText_address;
    EditText editText_phone;
    EditText editText_comment;

    DatabaseManager getManager;
    DatabaseManager setManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        idToken = getIntent().getStringExtra("idToken");
        serialNumber= getIntent().getStringExtra("serialNumber");
        size= getIntent().getStringExtra("size");

        ImageView imageView = (ImageView) findViewById(R.id.orderImv);

        getManager = new DatabaseManager();
        setManager = new DatabaseManager();

        textView_name = (TextView) findViewById(R.id.nameTxv);
        editText_address = (EditText) findViewById(R.id.addressEdt);
        editText_phone = (EditText) findViewById(R.id.phoneEdt);
        editText_comment = (EditText) findViewById(R.id.commentEdt);
        Button button_order = (Button) findViewById(R.id.orderBtn);

        imageView.setImageResource(
                getResources().getIdentifier(
                        "@drawable/" + serialNumber, "drawable", getPackageName()));

        getManager.requestGetUserInfo();

        getManager.setLoadCompleteListener(new DatabaseManager.LoadCompleteListener() {
            @Override
            public void onLoadComplete(boolean isData) {
                userInfo = getManager.getUserInfo();
                textView_name.setText(userInfo.name);
                editText_address.setText(userInfo.address);
                editText_phone.setText(userInfo.phoneNo);
            }
        });

        button_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setManager.requestSetToOrder(idToken, serialNumber, size,
                        textView_name.getText().toString(),
                        editText_address.getText().toString(),
                        editText_phone.getText().toString(),
                        editText_comment.getText().toString());
            }
        });

        setManager.setLoadCompleteListener(new DatabaseManager.LoadCompleteListener() {
            @Override
            public void onLoadComplete(boolean isData) {
                Toast.makeText(OrderActivity.this, "주문 완료", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
