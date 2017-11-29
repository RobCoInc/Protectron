package tech.secure.protectron.basic_user;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import tech.secure.protectron.ArrestActivity;
import tech.secure.protectron.R;
import tech.secure.protectron.database_helpers.DBHelper;

import static android.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER;

public class SearchArrestActivity extends AppCompatActivity {

    Cursor c;
    SimpleCursorAdapter cAdapter;

    DBHelper db;

    EditText mSearchText;

    ArrayList<String> aList;
    ListView mSearchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_arrest);

        mSearchText = (EditText) findViewById(R.id.arrest_search_text_edit);

        mSearchList = (ListView) findViewById(R.id.arrest_search_list);
        db = new DBHelper(this);
        aList = new ArrayList<>();

        mSearchText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                // unused
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // unused
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String enteredText = mSearchText.getText().toString();
                refreshList(enteredText);
            }
        });

        mSearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ArrestActivity.class);
                intent.putExtra("arrest_id", l);
                Log.d("", "onItemClick: " + l);
                startActivity(intent);
                finish();
            }
        });
    }
    public void refreshList(String text) {

        ArrayAdapter<String> adapte;
        try {
            c = db.searchArrestLikeText(text);
            cAdapter = new SimpleCursorAdapter(this, R.layout.support_simple_spinner_dropdown_item, c, new String[]{"description_name"}, new int[]{android.R.id.text1}, FLAG_REGISTER_CONTENT_OBSERVER);
            mSearchList.setAdapter(cAdapter);
        } catch (Exception e) {
            aList.clear();
            adapte = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, aList);
            mSearchList.setAdapter(adapte);
            Toast.makeText(SearchArrestActivity.this, "No Results Found", Toast.LENGTH_SHORT).show();
        }
    }
}
