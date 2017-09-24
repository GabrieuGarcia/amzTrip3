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

public class LoginUserActivity extends AppCompatActivity {

    @BindView(R.id.usuario) EditText usuario;
    @BindView(R.id.btn_next) Button btnNext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_next)
    public void onClick(View view) {
        entrarOnClick();
    }

    public void entrarOnClick() {

        String usuarioInformado = usuario.getText().toString();
        String userLogin = getResources().getString(R.string.user_login);

        // Se user estiver certo vai para a proxima activity - senha
        if (userLogin.equals(usuarioInformado)) {
            startActivity(new Intent(this, LoginPasswordActivity.class));

        } else {

            String mensagemErro = getString(R.string.erro_autenticacao_usuario);
            Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
            toast.show();

        }

    }

}
