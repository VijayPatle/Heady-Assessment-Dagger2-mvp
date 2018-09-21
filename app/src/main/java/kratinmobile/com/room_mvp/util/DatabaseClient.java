package kratinmobile.com.room_mvp.util;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.List;

import kratinmobile.com.room_mvp.database.AppDatabase;
import kratinmobile.com.room_mvp.entity.User;

public class DatabaseClient {
    private WeakReference<Context> contextWeakReference;
    private static DatabaseClient mInstance;

    //our app database object
    private AppDatabase appDatabase;

    public void getAllUsers(DatabaseHandler<List<User>> handler) {
        new DatabaseAsyncTask<List<User>>(handler) {
            @Override
            protected List<User> executeMethod() {
                List<User> users = appDatabase.userDao().gatAllUser();
                return users;
            }
        }.execute();
    }


    public interface DatabaseHandler<T> {
        void onComplete(boolean success, T result);
    }


    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    private DatabaseClient(Context context) {
        this.contextWeakReference = new WeakReference<Context>(context);

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(contextWeakReference.get(), AppDatabase.class, "user-database").build();
    }
    private static abstract class DatabaseAsyncTask<T> extends AsyncTask<Void, Void, T> {

        private DatabaseHandler<T> handler;
        private RuntimeException error;

        public DatabaseAsyncTask(DatabaseHandler<T> handler) {
            this.handler = handler;
        }

        @Override
        protected T doInBackground(Void... params) {
            try {
                return executeMethod();
            } catch (RuntimeException error) {
                this.error = error;
                return null;
            }
        }

        protected abstract T executeMethod();

        @Override
        protected void onPostExecute(T result) {
            handler.onComplete(error == null, result);
        }
    }

    public void insertPositionAsync(final User user, DatabaseHandler<Void> handler) {
        new DatabaseAsyncTask<Void>(handler) {
            @Override
            protected Void executeMethod() {
                addUser(user);
                return null;
            }
        }.execute();
    }
    private  User addUser( User user) {
        try {
            appDatabase.userDao().insertAll(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
