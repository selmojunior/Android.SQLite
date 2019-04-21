package sqlite.cursoandroid.junio.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //criando o banco de dados
        SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

        //tabelas
        //bancoDados.execSQL("DROP TABLE pessoas");
        bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3))");

        //inserindo dados
        bancoDados.execSQL("DELETE FROM pessoas");
        bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Selmo Jr', 33), ('Barbara Hellen', 30), ('Sem nome', 45);");
        //bancoDados.execSQL("INSERT INTO pessoas (nome, idade) values ('Barbara Hellen', 30)");

        Cursor cursor = bancoDados.rawQuery("SELECT id, nome, idade from pessoas", null);

        int indiceColunaId = cursor.getColumnIndex("id");
        int indiceColunaNome = cursor.getColumnIndex("nome");
        int indiceColunaIdade = cursor.getColumnIndex("idade");

        cursor.moveToFirst();

        while (cursor != null) {
            Log.i("RESULTADO - Id: ", cursor.getString(indiceColunaId));
            Log.i("RESULTADO - Nome: ", cursor.getString(indiceColunaNome));
            Log.i("RESULTADO - Idade: ", cursor.getString(indiceColunaIdade));
            cursor.moveToNext();
        }

    }
}
