package shoeping.clientapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText _editText_id;
    EditText _editText_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.loginBtn);
        _editText_id = (EditText) findViewById(R.id.idEdt);
        _editText_pw = (EditText) findViewById(R.id.pwEdt);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("id", getLoginId());

                startActivityForResult();

            }
        });
    }

    private String getLoginId() {
        DatabaseManager databaseManager = DatabaseManager.getInstance();

        String string_id = databaseManager.getId();
        String string_pw = databaseManager.getPw();

        if (checkPasswordIsCorrect(string_id, string_pw)) {
            return string_id;
        } else {
            Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private boolean checkPasswordIsCorrect(String id, String password) {


        if (id.equals(_editText_id.getText()) && password.equals(_editText_pw.getText())) {
            return true;
        } else {
            return false;
        }
    }
}
