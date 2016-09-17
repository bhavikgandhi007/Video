package com.initapp.vidmateguide.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.initapp.vidmateguide.R;
import com.initapp.vidmateguide.model.Items;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Big_Scal on 9/17/2016.
 */
public class VideoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_FOOTER = 3;
    Context context;
    ArrayList<Items> itemses;
    OnLoadMoreListener onLoadMoreListener;
    private boolean loading;
    private int lastVisibleItem, totalItemCount, categoryId, visibleThreshold = 6;

    public VideoListAdapter(Context context, final ArrayList<Items> itemses, RecyclerView recyclerView) {
        this.context = context;
        this.itemses = itemses;
        loading = false;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();


            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!loading
                            && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == TYPE_FOOTER) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.footer_loading, viewGroup, false);
            return new ProgressViewHolder(v);
        } else {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list_item, viewGroup, false);
            return new ViewHolderItem(v);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof ViewHolderItem) {
            Items items=itemses.get(i);
            if (!itemses.get(i).getSnippet().getThumbnails().getHigh().getUrl().isEmpty()) {
                ((ViewHolderItem) viewHolder).textLoading.setVisibility(View.VISIBLE);
                    Picasso.with(context).load(items.getSnippet().getThumbnails().getHigh().getUrl()).into(((ViewHolderItem) viewHolder).image_product, new Callback() {
                        @Override
                        public void onSuccess() {
                            ((ViewHolderItem) viewHolder).textLoading.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            ((ViewHolderItem) viewHolder).textLoading.setVisibility(View.VISIBLE);
                        }
                    });
            } else {
                ((ViewHolderItem) viewHolder).image_product.setVisibility(View.VISIBLE);
                ((ViewHolderItem) viewHolder).textLoading.setVisibility(View.VISIBLE);
            }

        } else if (viewHolder instanceof ProgressViewHolder) {
            ((ProgressViewHolder) viewHolder).progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return itemses.get(position) != null ? TYPE_ITEM : TYPE_FOOTER;
    }

    public boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return itemses.size();
    }

    public void setLoaded() {
        loading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }


    public interface ItemClickListener {
        void onClick(View view, int position, boolean isLongClick);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }

    class ViewHolderItem extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private ItemClickListener clickListener;
        private ImageView image_product;
        private TextView textLoading;
        public ViewHolderItem(View itemView) {
            super(itemView);
            image_product= (ImageView) itemView.findViewById(R.id.image_product);
            textLoading= (TextView) itemView.findViewById(R.id.textLoading);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onClick(v, getPosition(), true);
            return true;
        }
    }


}
