package amztrip.cursoandroid.com.amztrip.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import amztrip.cursoandroid.com.amztrip.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 * Created by Gabriel on 13/03/17.
 */

public class LoginPasswordActivity extends AppCompatActivity {

    @BindView(R.id.btn_next_senha) Button botaoEntrarSenha;
    @BindView(R.id.edt_password) EditText senha;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_login);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_next_senha)
    public void onClick(View view) {
        entrarOnClick();
    }

    public void entrarOnClick() {

        String senhaInformada = senha.getText().toString();
        String userPassword = getResources().getString(R.string.user_password);

        // Se user estiver certo vai para a proxima activity - senha
        if (userPassword.equals(senhaInformada)) {
            startActivity(new Intent(this, DashboardActivity.class));


        } else {

            String mensagemErro = getString(R.string.erro_autenticacao_senha);
            Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
            toast.show();

        }

    }


}
