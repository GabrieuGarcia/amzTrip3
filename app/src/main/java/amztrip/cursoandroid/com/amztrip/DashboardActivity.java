package amztrip.cursoandroid.com.amztrip;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Gabriel on 13/03/17.
 */

public class DashboardActivity extends AppCompatActivity{



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

    }

    public void selecionarOpcao (View view) {

        //com base na view que foi clicada iremos tomar a ação

        switch (view.getId()) {

            case R.id.nova_viagem:
                startActivity(new Intent(this, ViagemActivity.class));
            break;

            case R.id.novo_Custo:
                startActivity(new Intent(this, GastoActivity.class));
                break;

            case R.id.configuracoes:
                startActivity(new Intent(this, ConfiguracoesActivity.class));
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }



}
