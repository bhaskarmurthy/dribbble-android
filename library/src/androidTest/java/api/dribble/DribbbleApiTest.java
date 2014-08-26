package api.dribble;

import junit.framework.TestCase;

import javax.inject.Inject;

import api.dribble.model.Shot;
import api.dribble.model.Shots;
import dagger.Module;
import dagger.ObjectGraph;

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
        Shot shot = service.getShot(id)
                .toBlocking()
                .single();

        assertNotNull("Id must be present", shot.id);
        assertEquals("Id does not match", shot.id, id);
        assertNotNull("Title must be present", shot.title);
        assertNotNull("Player must be present", shot.player);
        assertNotNull("Player id must be present", shot.player.id);
    }

    public void testGetReboundsByShotId() {
        final long id = 43424;
        Shots rebounds = service.getRebounds(id)
                .toBlocking()
                .single();

        assertNotNull("Total must be present", rebounds.total);
        assertNotNull("Shots must be present", rebounds.shots);
    }

    public void testGetReboundsByShotIdAndPage() {
        final long id = 43424;
        Shots rebounds = service.getRebounds(id, 2, 15)
                .toBlocking()
                .single();

        assertNotNull("Total must be present", rebounds.total);
        assertNotNull("Shots must be present", rebounds.shots);
        assertEquals("Must be on page 2", rebounds.page, 2);
        assertEquals("Must have 15 per page", rebounds.per_page, 15);
    }

    public void testGetPopularShots() {
        Shots shots = service.getPopularShots()
                .toBlocking()
                .single();

        assertNotNull("Total must be present", shots.total);
        assertNotNull("Shots must be present", shots.shots);
    }

    public void testGetNewShots() {
        Shots shots = service.getNewShots()
                .toBlocking()
                .single();

        assertNotNull("Total must be present", shots.total);
        assertNotNull("Shots must be present", shots.shots);
    }

    public void testGetAllShots() {
        Shots shots = service.getAllShots()
                .toBlocking()
                .single();

        assertNotNull("Total must be present", shots.total);
        assertNotNull("Shots must be present", shots.shots);
    }

    public void testGetPlayerShots() {
        Shots shots = service.getPlayerShots("simplebits")
                .toBlocking()
                .single();

        assertNotNull("Total must be present", shots.total);
        assertNotNull("Shots must be present", shots.shots);
    }

    public void testGetPlayerFollowingShots() {
        Shots shots = service.getPlayerFollowingShots("frogandcode")
                .toBlocking()
                .single();

        assertNotNull("Total must be present", shots.total);
        assertNotNull("Shots must be present", shots.shots);
    }

    public void testGetPlayerLikesShots() {
        Shots shots = service.getPlayerFollowingShots("frogandcode")
                .toBlocking()
                .single();

        assertNotNull("Total must be present", shots.total);
        assertNotNull("Shots must be present", shots.shots);
    }
}
