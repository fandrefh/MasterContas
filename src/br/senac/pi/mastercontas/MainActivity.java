package br.senac.pi.mastercontas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void selecionaOpcao(View view) {
    	switch (view.getId()) {
		case R.id.btnCadDevedor:
			startActivity(new Intent(this, DevedorActivity.class));
			break;
		case R.id.btnListaDevedor:
			startActivity(new Intent(this, ListaDevedoresActivity.class));
			break;

		default:
			break;
		}
    }
}
