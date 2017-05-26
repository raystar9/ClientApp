package shoeping.clientapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static shoeping.clientapp.DetailsActivity.EXTRA_SERIAL_NUMBER;

public class LoginActivity extends AppCompatActivity {

    EditText _editText_id;
    EditText _editText_pw;
    DatabaseManager databaseManager = new DatabaseManager();
    String serialNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        serialNumber = getIntent().getStringExtra(EXTRA_SERIAL_NUMBER);

        Button loginButton = (Button) findViewById(R.id.loginBtn);

        _editText_id = (EditText) findViewById(R.id.idEdt);
        _editText_pw = (EditText) findViewById(R.id.passwordEdt);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLoginInfo();
            }
        });

        //
        databaseManager.setLoadCompleteListener(new DatabaseManager.LoadCompleteListener() {
            @Override
            public void onLoadComplete() {

                if(databaseManager.getIdToken() == null) {
                    Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                }
                else{
                    Context context = getApplicationContext();
                    Intent intent = new Intent(context, OrderActivity.class);
                    intent.putExtra(EXTRA_SERIAL_NUMBER, serialNumber);
                    intent.putExtra("idToken", databaseManager.getIdToken());
//                    intent.putExtra("size",)
                    context.startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onLoadFail() {

            }
        });
    }

    private void requestLoginInfo() {

        String _id = _editText_id.getText().toString();
        String _pw = _editText_pw.getText().toString();
        databaseManager.requestGetId(_id, _pw);
        //databaseManager.requestGetPw(_id, _pw);
    }
}
