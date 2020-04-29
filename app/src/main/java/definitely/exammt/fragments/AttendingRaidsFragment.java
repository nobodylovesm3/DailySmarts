package definitely.exammt.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import definitely.exammt.R;
import definitely.exammt.ui.adapters.RaidAdapter;
import definitely.exammt.databinding.FragmentFavBinding;
import definitely.exammt.db.RaidEntity;
import definitely.exammt.db.RaidsViewModel;
import definitely.exammt.ui.interfaces.OnFragmentIteractionListener;
import definitely.exammt.ui.interfaces.OnShareListener;

public class AttendingRaidsFragment extends Fragment {
    private RaidsViewModel viewModel;
    private RaidAdapter raidAdapter;
    private FragmentFavBinding binding;
    private OnFragmentIteractionListener onFragmentListener;
    private OnShareListener onShareListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_fav, container, false);

        viewModel = ViewModelProviders.of(this).get(RaidsViewModel.class);
        setupViews();

        return binding.getRoot();
    }

    public void onDelete(RaidEntity raidEntity) {
        viewModel.delete(raidEntity);
        raidAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentIteractionListener) {
            onFragmentListener = (OnFragmentIteractionListener) context;
        }
        if (context instanceof OnShareListener) {
            onShareListener = (OnShareListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onFragmentListener = null;
        onShareListener = null;
    }

    public void setupViews() {
        binding.recyclerViewQuotes.setLayoutManager(new LinearLayoutManager(getContext()));

        raidAdapter = new RaidAdapter();
        raidAdapter.setListeners(onFragmentListener, onShareListener);
        binding.recyclerViewQuotes.setAdapter(raidAdapter);

        viewModel.getAll().observe(this, (List<RaidEntity> raids) -> {
            raidAdapter.setData(raids);
        });
    }


}
