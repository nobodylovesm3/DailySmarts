package definitely.exammt;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.stetho.Stetho;

import definitely.exammt.databinding.MainActivityBinding;
import definitely.exammt.db.RaidEntity;
import definitely.exammt.ui.interfaces.OnFragmentIteractionListener;
import definitely.exammt.ui.interfaces.OnShareListener;
import definitely.exammt.model.Raid;
import definitely.exammt.ui.adapters.TabsPagerAdapter;


//fix layouts + add database items (hardcoded) + add ranking tab + add submit option + add profile (with levels, hardcoded, again)


public class MainActivity extends AppCompatActivity implements OnFragmentIteractionListener, OnShareListener {

    MainActivityBinding binding;
    private TabsPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        setSupportActionBar(binding.toolbar);
        setupTabs();
        Stetho.initializeWithDefaults(this);
    }

    private void setupTabs() {
        adapter = new TabsPagerAdapter(getSupportFragmentManager(), this);
        binding.viewPager.setAdapter(adapter);
        binding.grpTabs.setupWithViewPager(binding.viewPager);
    }

    @Override
    public void onDelete(RaidEntity raidEntity) {
        adapter.onDelete(raidEntity);
    }

    @Override
    public void onShare(Raid raid) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        startActivity(intent.createChooser(intent, "Share using... "));
    }
}
