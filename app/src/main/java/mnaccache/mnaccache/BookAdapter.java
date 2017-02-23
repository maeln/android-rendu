package mnaccache.mnaccache;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by maeln on 23/02/17.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Book> books;
    private boolean deplier;

    public BookAdapter(List<Book> ibooks, boolean ideplier) {
        deplier = ideplier;
        books = ibooks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Book book = books.get(position);

        holder.book = book;
        holder.title.setText(book.getTitle());
        holder.content.setText(book.getPrice() + "€");
        Glide.with(holder.view.getContext()).load(book.getCover()).into(holder.cover);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deplier) {
                    Bundle arguments = new Bundle();
                    arguments.putParcelable(DetailFragment.IDBOOK, holder.book);
                    DetailFragment fragment = new DetailFragment();
                    fragment.setArguments(arguments);
                    ((FragmentActivity) holder.view.getContext()).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.book_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Details.class);
                    intent.putExtra(DetailFragment.IDBOOK, holder.book);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(books != null) {
            return books.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        final TextView title;
        final TextView content;
        final ImageView cover;
        Book book;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            title = (TextView) view.findViewById(R.id.title);
            content = (TextView) view.findViewById(R.id.price);
            cover = (ImageView) view.findViewById(R.id.cover);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + content.getText() + "'";
        }
    }
}
