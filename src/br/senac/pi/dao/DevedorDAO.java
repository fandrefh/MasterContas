package br.senac.pi.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.senac.pi.databasehelper.DatabaseHelper;
import br.senac.pi.model.Devedor;

public class DevedorDAO {
	
	private DatabaseHelper helper;
	private SQLiteDatabase db;
	
	public DevedorDAO(Context context) {
		helper = new DatabaseHelper(context);
	}
	
	private SQLiteDatabase getDB() {
		if (db == null) {
			db = helper.getWritableDatabase();
		}
		return db;
	}
	
	public void inserir(Devedor devedor) {
		
		getDB();//Recupera uma instância do Banco de Dados.
		
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.Devedor.NOME, devedor.getNome());
		values.put(DatabaseHelper.Devedor.TELEFONE, devedor.getTelefone());
		values.put(DatabaseHelper.Devedor.EMAIL, devedor.getEmail());
		
		//Realiza a inserção no Banco de Dados.
		db.insert(DatabaseHelper.Devedor.TABELA, null, values);
		
	}
	
	public List<Devedor> listarDevedores() {
		getDB();
		List<Devedor> listaDevedores = new ArrayList<Devedor>();
		Cursor cursor = db.rawQuery("SELECT * FROM devedor;", null);
		if(cursor.moveToFirst()) {
			do {
				Devedor devedor = new Devedor();
				devedor.setId(cursor.getInt(0));
				devedor.setNome(cursor.getString(1));
				devedor.setTelefone(cursor.getString(2));
				devedor.setEmail(cursor.getString(3));
				listaDevedores.add(devedor);
			} while(cursor.moveToNext());
		}
		return listaDevedores;
	}
	
	public void atualizar(int id, Devedor devedor){
		getDB();
		
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.Devedor.NOME, devedor.getNome());
		values.put(DatabaseHelper.Devedor.TELEFONE, devedor.getTelefone());
		values.put(DatabaseHelper.Devedor.EMAIL, devedor.getEmail());
	    
		String idEmString = String.valueOf(id);
	    db.update(DatabaseHelper.Devedor.TABELA, values, 
	    		"_id=?", new String[]{idEmString});
	}
	
	public void deletar(String id){
		getDB();
		
		String[] where = { id };
		db.delete(DatabaseHelper.Devedor.TABELA, "_id=?", where);
	}
	
	public void closeDB() {
		helper.close();
	}

}
