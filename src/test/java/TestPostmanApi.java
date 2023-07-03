
import com.postman.rest.ApiExecutor;
import org.junit.Test;
import java.util.logging.Logger;

public class TestPostmanApi {

    private static final Logger LOGGER = Logger.getLogger(TestPostmanApi.class.getName());
    ApiExecutor executor = new ApiExecutor();
    public static String createdUId;


    @Test
    public void postSingleCollections() throws Exception {
        createdUId = executor.postSingleCollection();
        LOGGER.info("createdUId: " + createdUId);
    }

    @Test
    public void putSingleCollections() throws Exception {
        executor.putSingleCollection(createdUId);
    }

    @Test
    public void getAllCollections() throws Exception {
        executor.getAllCollections();
    }

    @Test
    public void getSingleCollections() throws Exception {
        executor.getSingleCollection(createdUId);
    }

    @Test
    public void deleteSingleCollections() throws Exception {
        executor.deleteSingleCollection(createdUId);
    }

    @Test
    public void getAllEnvironments() throws Exception {
        executor.getAllEnvironments();
    }
    @Test
    public void getAllMocks() throws Exception {
        executor.getAllMocks();
    }

    @Test
    public void getAllMonitors() throws Exception {
        executor.getAllMonitors();
    }

    @Test
    public void getAllWorkspaces() throws Exception {
        executor.getAllWorkspaces();
    }

    @Test
    public void getAllUsers() throws Exception {
        executor.getAllUsers();
    }

}
