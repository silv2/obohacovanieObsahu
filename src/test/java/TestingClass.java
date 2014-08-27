import com.sun.syndication.io.FeedException;
import org.junit.Test;
import service.impl.DbPediaImpl;
import service.impl.GeonamesImpl;

import java.io.IOException;

/**
 * Created by Silvia on 7. 8. 2014.
 */

public class TestingClass {

    @Test
    public void testGeonames() throws IOException, FeedException {
        GeonamesImpl geonames=new GeonamesImpl();
        try {
            geonames.findLocality("spissky");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGeoRSS() throws IOException, FeedException {
        GeonamesImpl geonames=new GeonamesImpl();
        geonames.tryingGeoRSS();
    }

    @Test
    public void testDbPedia() {
        DbPediaImpl dbPedia = new DbPediaImpl();
        dbPedia.getMetadata("Spiš Castle");
    }
}
