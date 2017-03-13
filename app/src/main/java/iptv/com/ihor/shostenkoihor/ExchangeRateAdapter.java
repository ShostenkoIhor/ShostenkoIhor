package iptv.com.ihor.shostenkoihor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class ExchangeRateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ExchangeRate> mList;
    private Context context;
    private int lastPosition;


    public ExchangeRateAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dollar, parent, false);
                return new DollarViewHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.other, parent, false);
                return new OtherViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ExchangeRate object = mList.get(position);
        if (object != null) {
            if (object.getCurrency().equals("USD")) {
                ((DollarViewHolder) holder).tvDollarName.setText(object.getCurrency().toString());
                ((DollarViewHolder) holder).tvDollarBuy.setText(object.getBuy().toString());
                ((DollarViewHolder) holder).tvDollarSale.setText(object.getSale().toString());
                Picasso.with(context)
                        .load("http://img-fotki.yandex.ru/get/26292/310023662.2371/0_589b3a_5e95bb76_orig")
                        .resize(200, 200)
                        .into(((DollarViewHolder) holder).ivDollar);


            } else {
                ((OtherViewHolder) holder).tvOtherName.setText(object.getCurrency().toString());
                ((OtherViewHolder) holder).tvOtherBuy.setText(object.getBuy().toString());
                ((OtherViewHolder) holder).tvOtherSale.setText(object.getSale().toString());
                Picasso.with(context)
                        .load("http://www.wwalls.ru/large/201210/31955.jpg")
                        .resize(80, 80)
                        .into(((OtherViewHolder) holder).ivOther);
                addAnimation((OtherViewHolder) holder, position);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    public static class DollarViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDollarBuy;
        private TextView tvDollarSale;
        private TextView tvDollarName;
        private ImageView ivDollar;

        public DollarViewHolder(View itemView) {
            super(itemView);
            tvDollarName = (TextView) itemView.findViewById(R.id.tvDollarName);
            tvDollarBuy = (TextView) itemView.findViewById(R.id.tvDollarBuy);
            tvDollarSale = (TextView) itemView.findViewById(R.id.tvDollarSale);
            ivDollar = (ImageView) itemView.findViewById(R.id.ivDollar);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mList != null) {
            ExchangeRate object = mList.get(position);
            if (object.getCurrency().equals("USD")) {
                return 0;
            }
        }
        return 1;
    }

    public static class OtherViewHolder extends RecyclerView.ViewHolder {
        private TextView tvOtherName;
        private TextView tvOtherBuy;
        private TextView tvOtherSale;
        private ImageView ivOther;
        private Button btOk;

        public OtherViewHolder(View itemView) {
            super(itemView);
            tvOtherName = (TextView) itemView.findViewById(R.id.tvOtherName);
            tvOtherBuy = (TextView) itemView.findViewById(R.id.tvOtherBuy);
            tvOtherSale = (TextView) itemView.findViewById(R.id.tvOtherSale);
            ivOther = (ImageView) itemView.findViewById(R.id.ivOther);
            btOk = (Button) itemView.findViewById(R.id.btOk);
        }
    }


    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    private void addAnimation(OtherViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }
    public void addCurrencyEntity(List<ExchangeRate> list) {
        mList=list;
        notifyItemInserted(getItemCount());
    }

}