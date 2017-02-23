package mnaccache.mnaccache;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by maeln on 23/02/17.
 */
public interface Library {
    @GET("books")
    Call<List<Book>> listBooks();
}
