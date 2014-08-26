package api.dribble;

import junit.framework.TestCase;

import javax.inject.Inject;

import api.dribble.model.Shot;
import dagger.Module;
import dagger.ObjectGraph;
import retrofit.RetrofitError;

/**
 * Created by bhaskar on 2014-08-25.
 */
public class DribbbleApiTest extends TestCase {
    private static final String TAG = "DribbbleApiTest";

    @Module(injects = DribbbleApiTest.class, addsTo = DribbbleServiceModule.class)
    static class TestModule {}

    private ObjectGraph graph;

    @Inject
    DribbbleService service;

    @Override
    protected void setUp() throws Exception {
        graph = ObjectGraph.create(
                new DribbbleServiceModule("http://api.dribbble.com"),
                new TestModule()
        );

        graph.injectStatics();
        graph.inject(this);
    }

    public void testGetShotById() {
        final long id = 21603;

        try {
            Shot shot = service.getShot(id)
                    .toBlocking()
                    .single();

            assertNotNull("Id must be present", shot.id);
            assertEquals("Id does not match", shot.id, id);
            assertNotNull("Title must be present", shot.title);
            assertNotNull("Player must be present", shot.player);
            assertNotNull("Player id must be present", shot.player.id);
        } catch (RetrofitError e) {
            fail(e.getMessage());
        }
    }
}
