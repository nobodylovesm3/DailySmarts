package definitely.exammt.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class RaidsRepository {
    private RaidsDao dao;
    private LiveData<List<RaidEntity>> allRaids;

    public RaidsRepository(Application application) {
        RaidsDatabase db = RaidsDatabase.getDatabase(application);
        dao = db.raidsDao();
        allRaids = dao.getAll();
    }

    LiveData<List<RaidEntity>> getAllQuotes() {
        return allRaids;
    }

    public void insert(RaidEntity raidEntity) {
        new insertAsyncTask(dao).execute(raidEntity);
    }

    public void delete(RaidEntity raidEntity) {
        new deleteAsyncTask(dao).execute(raidEntity);
    }

    private static class insertAsyncTask extends AsyncTask<RaidEntity, Void, Void> {

        private RaidsDao dao;

        insertAsyncTask(RaidsDao raidsDao) {
            dao = raidsDao;
        }

        @Override
        protected Void doInBackground(final RaidEntity... params) {
            long id = dao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<RaidEntity, Void, Boolean> {

        Exception error;
        private RaidsDao dao;

        deleteAsyncTask(RaidsDao raidsDao) {
            dao = raidsDao;
        }

        @Override
        protected Boolean doInBackground(final RaidEntity... params) {
            try {
                dao.delete(params[0]);
                return true;
            } catch (Exception e) {
                Exception error = e;
                return false;
            }
        }
    }
}
