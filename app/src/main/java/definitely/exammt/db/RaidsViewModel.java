package definitely.exammt.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class RaidsViewModel extends AndroidViewModel {
    private RaidsRepository raidsRepository;
    private LiveData<List<RaidEntity>> allRaids;

    public RaidsViewModel(@NonNull Application application) {
        super(application);
        raidsRepository = new RaidsRepository(application);
        allRaids = raidsRepository.getAllQuotes();
    }

    public LiveData<List<RaidEntity>> getAll() {
        return allRaids;
    }

    public void insert(RaidEntity raidEntity) {
        raidsRepository.insert(raidEntity);
    }


    public void delete(RaidEntity raidEntity) {
        raidsRepository.delete(raidEntity);
    }
}
