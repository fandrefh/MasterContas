package br.senac.pi.mastercontas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import br.senac.pi.dao.DevedorDAO;
import br.senac.pi.databasehelper.DatabaseHelper;
import br.senac.pi.model.Devedor;

public class DevedorActivity extends Activity {
	
	private DatabaseHelper helper;
	private EditText edtNome, edtTelefone, edtEmail;
	
	Devedor devedor = new Devedor();
	DevedorDAO dao = new DevedorDAO(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.devedor);
		
		edtNome = (EditText) findViewById(R.id.edtNome);
		edtTelefone = (EditText) findViewById(R.id.edtTelefone);
		edtEmail = (EditText) findViewById(R.id.edtEmail);
		
		helper = new DatabaseHelper(this);
	}
	
	public void cadastrarDevedor(View view) {
		
		devedor.setNome(edtNome.getText().toString());
		devedor.setTelefone(edtTelefone.getText().toString());
		devedor.setEmail(edtEmail.getText().toString());
		
		dao.inserir(devedor);
		
		startActivity(new Intent(this, ListaDevedoresActivity.class));
		
	}
	
	@Override
	protected void onDestroy() {
		helper.close();
		super.onDestroy();
	}
}
