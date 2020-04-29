package definitely.exammt.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {RaidEntity.class}, version = 1, exportSchema = false)
public abstract class RaidsDatabase extends RoomDatabase {

    public abstract RaidsDao raidsDao();

    private static RaidsDatabase INSTANCE;
    private static Callback roomCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new OpenDbAsync(INSTANCE).execute();
        }
    };

    public static RaidsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RaidsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RaidsDatabase.class, "raids")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static class OpenDbAsync extends AsyncTask<Void, Void, Void> {

        private final RaidsDao dao;

        OpenDbAsync(RaidsDatabase db) {
            dao = db.raidsDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            return null;
        }
    }
}


