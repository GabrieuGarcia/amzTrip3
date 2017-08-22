package amztrip.cursoandroid.com.amztrip;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 * Created by Gabriel on 13/03/17.
 */

public class LoginUserActivity extends AppCompatActivity {

    private EditText usuario;
    private Button botaoEntrar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        usuario = (EditText)findViewById(R.id.usuario);
        botaoEntrar = (Button)findViewById(R.id.btn_next);
        botaoEntrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                entrarOnClick();
            }
        });
    }


    public void entrarOnClick() {

        String usuarioInformado = usuario.getText().toString();

        // Se user estiver certo vai para a proxima activity - senha
        if ("gab".equals(usuarioInformado)) {
            startActivity(new Intent(this, LoginPasswordActivity.class));

        } else {

            String mensagemErro = getString(R.string.erro_autenticacao_usuario);
            Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
            toast.show();

        }

    }

}
