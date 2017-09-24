package amztrip.cursoandroid.com.amztrip.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import amztrip.cursoandroid.com.amztrip.R;

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

            case R.id.gastos:
                startActivity(new Intent(this, GastoListActivity.class));
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
