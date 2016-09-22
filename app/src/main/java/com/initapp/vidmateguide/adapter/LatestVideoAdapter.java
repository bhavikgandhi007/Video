package com.initapp.vidmateguide.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.initapp.vidmateguide.R;
import com.initapp.vidmateguide.VideoDetailActivity;
import com.initapp.vidmateguide.model.VideoResult;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Big_Scal on 9/22/2016.
 */
public class LatestVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_FOOTER = 3;
    Context context;
    ArrayList<VideoResult.Items> itemses;
    OnLoadMoreListener onLoadMoreListener;
    private boolean loading;
    private int lastVisibleItem, totalItemCount, categoryId, visibleThreshold = 6;

    public LatestVideoAdapter(Context context, final ArrayList<VideoResult.Items> itemses, RecyclerView recyclerView) {
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
            final VideoResult.Items items = itemses.get(i);
            Log.i("TAG", "onBindViewHolder: " + items.getId());
            if (!itemses.get(i).getSnippet().getThumbnails().getMedium().getUrl().isEmpty()) {
                ((ViewHolderItem) viewHolder).image_product.getLayoutParams().height = 285;
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
            ((ViewHolderItem) viewHolder).setClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    if (isLongClick) {

                    } else {
                        Intent intent = new Intent(context, VideoDetailActivity.class);
                        intent.putExtra("videoid", items.getId());
                        context.startActivity(intent);
                    }
                }
            });

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
        private ImageView textLoading;

        public ViewHolderItem(View itemView) {
            super(itemView);
            image_product = (ImageView) itemView.findViewById(R.id.image_product);
            textLoading = (ImageView) itemView.findViewById(R.id.textLoading);
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
