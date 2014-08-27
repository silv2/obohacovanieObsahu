package service;

import org.geonames.ToponymSearchResult;

/**
 * Created by Silvia on 7. 8. 2014.
 */
public interface GeonamesService {
    ToponymSearchResult findLocality(String name);
}
