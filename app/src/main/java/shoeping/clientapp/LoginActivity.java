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

                if (getLoginId() != null) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("id", getLoginId());

                    setResult(RESULT_OK);
                    finish();
                } else {

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
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setTitle("안내");
            builder.setMessage("로그인 성공.");
            builder.setIcon(android.R.drawable.ic_dialog_info);
            AlertDialog dialog = builder.create();
            dialog.show();
            return string_id;
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setTitle("안내");
            builder.setMessage("로그인에 실패하였습니다.");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            AlertDialog dialog = builder.create();
            dialog.show();
            return null;
        }
    }

    private boolean checkPasswordIsCorrect(String id, String password) {


        if (id.equals(_editText_id.getText()) && password.equals(_editText_pw.getText())) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setTitle("안내");
            builder.setMessage("정보 일치.");
            builder.setIcon(android.R.drawable.ic_dialog_info);
            AlertDialog dialog = builder.create();
            dialog.show();

            return true;
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setTitle("안내");
            builder.setMessage("정보 불일치.");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            AlertDialog dialog = builder.create();
            dialog.show();
            return false;
        }
    }
}
