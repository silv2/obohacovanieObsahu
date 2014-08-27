package service.impl;

import com.sun.syndication.feed.module.georss.GeoRSSModule;
import com.sun.syndication.feed.module.georss.GeoRSSUtils;
import com.sun.syndication.feed.module.georss.geometries.AbstractGeometry;
import com.sun.syndication.feed.module.georss.geometries.Polygon;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.geonames.*;
import service.GeonamesService;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Silvia on 7. 8. 2014.
 * http://forum.geonames.org/gforum/posts/list/288.page
 * http://www.georss.org/simple
 * http://www.javaxt.com/GeoRSS/
 * http://www.javaxt.com/javaxt-rss
 */
public class GeonamesImpl implements GeonamesService {

    @Override
    public ToponymSearchResult findLocality(String name)  {
        WebService.setUserName("silv");
        ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
        searchCriteria.setQ(name);

        ToponymSearchResult searchResult = null;
        try {
            searchResult = WebService.search(searchCriteria);
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (Toponym toponym : searchResult.getToponyms()) {
            System.out.println(toponym.getName()+" "+ toponym.getCountryName()+ " " + toponym.getLongitude() + " "
                    + toponym.getLatitude()+ " "+ toponym.getCountryCode() );
        }

        return searchResult;
    }

    public void tryingGeoRSS() throws IOException, FeedException {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(new URL(
                "http://www.geonames.org/recent-changes.xml")));

        List<SyndEntry> entries = feed.getEntries();
        for (SyndEntry entry : entries) {
            GeoRSSModule geoRSSModule = GeoRSSUtils.getGeoRSS(entry);
            AbstractGeometry abstractGeometry=geoRSSModule.getGeometry();
           // abstractGeometry.

            System.out.println(entry.getTitle() + " : lat="
                    + geoRSSModule.getPosition().getLatitude() + ",lng="
                    + geoRSSModule.getPosition().getLongitude() + ", desc="
                    + entry.getDescription().getValue() + "; time="
                    + entry.getPublishedDate());
        }

        Polygon polygon = new Polygon();

    }

}
