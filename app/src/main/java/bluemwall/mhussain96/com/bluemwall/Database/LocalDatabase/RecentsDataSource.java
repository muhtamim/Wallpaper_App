package bluemwall.mhussain96.com.bluemwall.Database.LocalDatabase;

import java.util.List;

import io.reactivex.Flowable;
import bluemwall.mhussain96.com.bluemwall.Database.DataSource.IRecentsDataSource;
import bluemwall.mhussain96.com.bluemwall.Database.Recents;

/**
 * Created by Muhtamim Fuwad Nahid on 3/16/2018.
 */

public class RecentsDataSource implements IRecentsDataSource {

    private RecentsDAO recentsDAO;
    private static RecentsDataSource instance;

    public RecentsDataSource(RecentsDAO recentsDAO) {
        this.recentsDAO = recentsDAO;
    }

    public static RecentsDataSource getInstance(RecentsDAO recentsDAO){
        if (instance == null){
            instance = new RecentsDataSource(recentsDAO);
        }
        return instance;
    }

    @Override
    public Flowable<List<Recents>> getAllRecents() {
        return recentsDAO.getAllRecents();
    }

    @Override
    public void insertRecents(Recents... recents) {
        recentsDAO.insertRecents(recents);
    }

    @Override
    public void updateRecents(Recents... recents) {
        recentsDAO.updateRecents(recents);
    }

    @Override
    public void deleteRecents(Recents... recents) {
        recentsDAO.deleteRecents(recents);
    }

    @Override
    public void deleteAllRecents() {
        recentsDAO.deleteAllRecents();
    }
}