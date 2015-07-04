package br.senac.pi.databasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "mastercontas.db";
	private static final int VERSION_DB = 1;

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, VERSION_DB);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE IF NOT EXISTS devedor ("
				+ "_id integer primary key autoincrement,"
				+ "nome varchar(100) not null,"
				+ "telefone varchar(14) not null,"
				+ "email varchar(100) not null);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public static class Devedor {
		public static final String TABELA = "devedor";
		public static final String ID = "_id";
		public static final String NOME = "nome";
		public static final String TELEFONE = "telefone";
		public static final String EMAIL = "email";
		
		public static final String[] COLUNAS = new String[] {
			ID, NOME, TELEFONE, EMAIL
		};
	}

}
