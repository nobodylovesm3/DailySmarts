package definitely.exammt.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import definitely.exammt.R;
import definitely.exammt.api.QuotesWrapper;
import definitely.exammt.databinding.FragmentDailyQuotesBinding;
import definitely.exammt.databinding.ItemLayoutCardBinding;
import definitely.exammt.db.RaidEntity;
import definitely.exammt.db.RaidsViewModel;
import definitely.exammt.model.GenerateDatabase;
import definitely.exammt.model.Raid;
import definitely.exammt.ui.adapters.RaidAdapter;


public class RaidsFragment extends Fragment {
    private FragmentDailyQuotesBinding fragmentBinding;
    private RaidsViewModel raidsViewModel;
    private definitely.exammt.ui.interfaces.OnFragmentIteractionListener onFragmentListener;
    private definitely.exammt.ui.interfaces.OnShareListener onShareListener;
    private boolean isAttending = false;
    private static RaidAdapter adapter;
    private ItemLayoutCardBinding itemBinding;
    private RaidEntity raidEntity = new RaidEntity();
    private List<Raid> data;

    public RaidsFragment() {
    }

    public static RaidsFragment newInstance() {
        RaidsFragment fragment = new RaidsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_daily_quotes
                , container
                , false);
        data = GenerateDatabase.getDatabase();

        raidsViewModel = ViewModelProviders.of(this).get(RaidsViewModel.class);

        updateData();
        setHasOptionsMenu(true);
        return fragmentBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof definitely.exammt.ui.interfaces.OnFragmentIteractionListener) {
            onFragmentListener = (definitely.exammt.ui.interfaces.OnFragmentIteractionListener) context;
        }
        if (context instanceof definitely.exammt.ui.interfaces.OnShareListener) {
            onShareListener = (definitely.exammt.ui.interfaces.OnShareListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onFragmentListener = null;
        onShareListener = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                updateData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    private void updateData() {
//        itemBinding = DataBindingUtil.bind(fragmentBinding.grpDailyQuote.getRoot());
//        QuotesWrapper.getInstance().getCurrentQuote("getQuote", "json", "en", new QuotesWrapper.DataListener<Raid>() {
//            @Override
//            public void onSuccess(Raid data) {
//                updateCurrentQuote(data);
//            }
//
//            @Override
//            public void onFailureError() {
//                Toast.makeText(getContext(), "Fail.", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    private void updateData(){
        itemBinding = DataBindingUtil.bind(fragmentBinding.grpDailyQuote.getRoot());
        GenerateDatabase db = new GenerateDatabase();
    }

    private void updateCurrentQuote(Raid data) {
        itemBinding.txtTitle.setText("123");
        itemBinding.txtDescription.setText("sample description");
        itemBinding.btnAttend.setImageResource(R.drawable.empty_recycle);

        setRaidEntity(data);

        itemBinding.btnAttend.setOnClickListener(v -> {
            if (!isAttending) {
                insertData(data);
            } else {
                deleteData(data);
            }
        });

        itemBinding.btnShare.setOnClickListener(v -> {
            onShareListener.onShare(data);
        });
    }

    private void deleteData(Raid data) {
        itemBinding.btnAttend.setImageResource(R.drawable.empty_recycle);
        isAttending = false;
        raidsViewModel.delete(raidEntity);
    }

    private void insertData(Raid data) {
        itemBinding.btnAttend.setImageResource(R.drawable.full_recycle);
        isAttending = true;
        raidsViewModel.insert(raidEntity);
    }

    private void setRaidEntity(Raid data) {
        raidEntity.setRaidTitle(data.getRaidTitle());
        raidEntity.setRaidDescription(data.getRaidDescription());
    }
}
