package bluemwall.mhussain96.com.bluemwall.Database.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import bluemwall.mhussain96.com.bluemwall.Database.Recents;

/**
 * Created by Muhtamim Fuwad Nahid on 3/16/2018.
 */

public interface IRecentsDataSource {
    Flowable<List<Recents>> getAllRecents();
    void insertRecents(Recents... recents);
    void updateRecents(Recents... recents);
    void deleteRecents(Recents... recents);
    void deleteAllRecents();


}
