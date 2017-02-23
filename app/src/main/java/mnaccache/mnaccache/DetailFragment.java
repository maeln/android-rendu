package mnaccache.mnaccache;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by maeln on 23/02/17.
 */
public class DetailFragment extends Fragment {
    public static final String IDBOOK = "id";

    private Book book;

    public DetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(IDBOOK)) {
            book = getArguments().getParcelable(IDBOOK);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(book.getTitle());
            }

            ImageView coverPreview = (ImageView) activity.findViewById(R.id.detail_cover);
            if(coverPreview != null) {
                Glide.with(activity).load(book.getCover()).into(coverPreview);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.book_detail, container, false);

        if (book != null) {
            TextView isbnview = (TextView) rootView.findViewById(R.id.detail_isbn);
            if(isbnview != null)
                isbnview.setText("ISBN: " + book.getIsbn());

            TextView priceview = (TextView) rootView.findViewById(R.id.detail_price);
            if(priceview != null)
                priceview.setText("Prix Unitaire: " + book.getPrice() + "€");

            TextView titleview = (TextView) rootView.findViewById(R.id.detail_title);
            if(titleview != null)
                titleview.setText(book.getTitle());

            StringBuffer buf = new StringBuffer();
            for(String par : book.getSynopsis()) {
                buf.append(par + "\n\n");
            }
            ((TextView) rootView.findViewById(R.id.book_detail)).setText(buf.toString());
        }

        return rootView;
    }
}
