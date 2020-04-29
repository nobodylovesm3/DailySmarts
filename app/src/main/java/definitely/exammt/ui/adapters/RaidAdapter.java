package definitely.exammt.ui.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import definitely.exammt.R;
import definitely.exammt.databinding.ItemLayoutCardBinding;
import definitely.exammt.db.RaidEntity;
import definitely.exammt.model.Raid;
import definitely.exammt.ui.interfaces.OnFragmentIteractionListener;
import definitely.exammt.ui.interfaces.OnShareListener;

public class RaidAdapter extends RecyclerView.Adapter<RaidAdapter.RaidsViewHolder> {

    private List<RaidEntity> data;
    private OnFragmentIteractionListener onFragmentIteractionListener;
    private OnShareListener onShareListener;

    public RaidAdapter() {

    }

    public Raid convertRaidEntityToRaid(RaidEntity raidEntity) {
        Raid raid = new Raid(raidEntity.getRaidDescription(), raidEntity.getRaidTitle(),raidEntity.getImgUrl());
        return raid;

    }

    @NonNull
    @Override
    public RaidsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLayoutCardBinding binding;

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_layout_card, parent, false);
        return new RaidsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RaidsViewHolder holder, int position) {
        RaidEntity currentQuote = data.get(position);
        holder.bindEverything(currentQuote, onFragmentIteractionListener, onShareListener);
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        } else {
            return data.size();
        }
    }

    public void setData(List<RaidEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setListeners(OnFragmentIteractionListener onFragmentListener, OnShareListener onShareListener) {
        this.onFragmentIteractionListener = onFragmentListener;
        this.onShareListener = onShareListener;
    }

    public class RaidsViewHolder extends RecyclerView.ViewHolder {
        ItemLayoutCardBinding binding;

        public RaidsViewHolder(ItemLayoutCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bindEverything(RaidEntity raid, OnFragmentIteractionListener onFragmentIteractionListener, OnShareListener onShareListener) {
            binding.txtTitle.setText(raid.getRaidTitle());
            binding.txtDescription.setText(raid.getRaidDescription());
            binding.btnAttend.setImageResource(R.drawable.full_recycle);
            binding.btnAttend.setOnClickListener(v -> onFragmentIteractionListener.onDelete(raid));
            binding.btnShare.setOnClickListener(v -> onShareListener.onShare(convertRaidEntityToRaid(raid)));
        }

        public Raid convertRaidEntityToRaid(RaidEntity raidEntity) {
            Raid raid = new Raid(raidEntity.getRaidDescription(), raidEntity.getRaidTitle(),raidEntity.getImgUrl());
            return raid;

        }

    }
}
