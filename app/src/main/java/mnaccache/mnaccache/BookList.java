package mnaccache.mnaccache;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by maeln on 23/02/17.
 */
public class BookList extends AppCompatActivity {
    private boolean deplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        View recyclerView = findViewById(R.id.book_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.book_detail_container) != null) {
            deplier = true;
        }
    }

    private void setupRecyclerView(final RecyclerView recyclerView) {
        List<Book> bs;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Library service = retrofit.create(Library.class);
        service.listBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                recyclerView.setAdapter(new BookAdapter(response.body(), deplier));
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.e("SIEZ", "Impossible de récupérer les livres.");
            }
        });
    }
}
