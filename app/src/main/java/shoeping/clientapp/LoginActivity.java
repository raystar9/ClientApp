package shoeping.clientapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText _editText_id;
    EditText _editText_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.loginBtn);
        _editText_id = (EditText) findViewById(R.id.idEdt);
        _editText_pw = (EditText) findViewById(R.id.passwordEdt);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (getLoginId() != null) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("id", getLoginId());

                        setResult(RESULT_OK);
                        finish();
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private String getLoginId() {
        DatabaseManager databaseManager = DatabaseManager.getInstance();

        databaseManager.CheckIdPw();

        String string_id = databaseManager.getId();
        String string_pw = databaseManager.getPw();

        if (checkPasswordIsCorrect(string_id, string_pw)) {
            return string_id;
        } else {
            return null;
        }
    }

    private boolean checkPasswordIsCorrect(String id, String password) {

        String edtId = _editText_id.getText().toString();
        String edtPw = _editText_pw.getText().toString();
        if (id.equalsIgnoreCase(edtId) && password.equalsIgnoreCase(edtPw)) {
            return true;
        } else {
            return false;
        }
    }
}
