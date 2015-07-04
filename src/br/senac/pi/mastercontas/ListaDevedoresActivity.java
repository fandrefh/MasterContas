package br.senac.pi.mastercontas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract.Helpers;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import br.senac.pi.dao.DevedorDAO;
import br.senac.pi.databasehelper.DatabaseHelper;
import br.senac.pi.model.Devedor;

public class ListaDevedoresActivity extends ListActivity implements 
OnItemClickListener, OnClickListener {
	private List<Map<String, Object>> devedores;
	DevedorDAO dao = new DevedorDAO(this);
	
	private AlertDialog alertDialog;
	private AlertDialog alertConfirma;
	private int devedorSelecionado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String[] from = new String[] {DatabaseHelper.Devedor.NOME, 
				DatabaseHelper.Devedor.EMAIL};
		int[] to = new int[] {R.id.txtNomeDevedor, R.id.txtEmailDevedor};
		
		SimpleAdapter adapter = new SimpleAdapter(this, 
				listarDevedores(), R.layout.lista_devedores, from, to);
		
		setListAdapter(adapter);
		
		getListView().setOnItemClickListener(this);
		this.alertDialog = criaAlertDialog();
		this.alertConfirma = criaConfirmacaoDialog();
	}
	
	public List<Map<String, Object>> listarDevedores() {
		devedores = new ArrayList<Map<String, Object>>();
		List<Devedor> listaDevedores = dao.listarDevedores();
		for(Devedor devedor: listaDevedores) {
			Map<String, Object> itemDevedor = new HashMap<String, Object>();
			
			String nome = devedor.getNome();
			String email = devedor.getEmail();
			
			itemDevedor.put(DatabaseHelper.Devedor.NOME, nome);
			itemDevedor.put(DatabaseHelper.Devedor.EMAIL, email);
			
			devedores.add(itemDevedor);
		}
		return devedores;
	}

	@Override
	public void onClick(DialogInterface dialog, int item) {
		// TODO Auto-generated method stub
		switch (item) {
		case 0:
			startActivity(new Intent(this, DevedorActivity.class));
			break;
		case 1: //Edição do devedor
			startActivity(new Intent(this, DevedorActivity.class));
			break;
		case 2://Exclusão do item
			alertConfirma.show();
			break;
		case DialogInterface.BUTTON_POSITIVE:
			devedores.remove(devedorSelecionado);
			getListView().invalidateViews();
			break;
		case DialogInterface.BUTTON_NEGATIVE:
			alertConfirma.dismiss();
			break;
		default:
			break;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		// TODO Auto-generated method stub
		this.devedorSelecionado = position;
		Toast.makeText(this, "Item clicado: " + devedorSelecionado, Toast.LENGTH_LONG).show();
		alertDialog.show();
		
	}
	
	private AlertDialog criaAlertDialog() {
		final CharSequence[] items = {
				getString(R.string.novo_devedor),
				getString(R.string.editar_devedor),
				getString(R.string.remover_devedor)
		};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.acoes_devedor);
		builder.setItems(items, this);
		return builder.create();
	}
	
	private AlertDialog criaConfirmacaoDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.confirma_exclusao_devedor);
		builder.setPositiveButton(R.string.sim_confirmacao, this);
		builder.setNegativeButton(R.string.nao_confirmacao, this);
		
		return builder.create();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		dao.closeDB();
		super.onDestroy();
	}
}
