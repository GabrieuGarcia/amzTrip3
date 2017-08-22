package amztrip.cursoandroid.com.amztrip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/*
 * Created by Gabriel on 13/03/17.
 */

public class LoginPasswordActivity extends AppCompatActivity {

//    private static final String MANTER_CONECTADO = "manter_conectado";
    private EditText senha;
    private Button botaoEntrarDois;
    private CheckBox manterConectado;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_login);

        senha = (EditText) findViewById(R.id.edt_password);
        botaoEntrarDois = (Button) findViewById(R.id.btn_next_dois);
        botaoEntrarDois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrarOnClick();
            }
        });

//        manterConectado = (CheckBox) findViewById(R.id.manterConectado);
//        SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
//        boolean conectado = preferencias.getBoolean(MANTER_CONECTADO, false);

//        if(conectado){
//            startActivity(new Intent(this, DashboardActivity.class));
//        }

    }

    public void entrarOnClick() {

        String senhaInformada = senha.getText().toString();

        // Se user estiver certo vai para a proxima activity - senha
        if ("123".equals(senhaInformada)) {
            startActivity(new Intent(this, DashboardActivity.class));

//            SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
//            SharedPreferences.Editor editor = preferencias.edit();
//            editor.putBoolean(MANTER_CONECTADO, manterConectado.isChecked());
//            editor.commit();

        } else {

            String mensagemErro = getString(R.string.erro_autenticacao_senha);
            Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
            toast.show();

        }

    }


}
