package service.impl;

import com.hp.hpl.jena.query.*;
import service.DbPediaService;

/**
 * Created by Silvia on 7. 8. 2014.
 */
public class DbPediaImpl implements DbPediaService{

    @Override
    public void getMetadata(String name){

        String service = "http://dbpedia.org/sparql";

        /* query v SPARQL, ktorá vyberie všetky dáta, kde sa label zhoduje s tým čo je v stringu name
        vypíše stránku, na ktorej sa tieto informácie nachádzajú, jeho abstrakt, do akej kategórie a krajiny patrí a jeho názov
        tieto tri posledné zložky sú voliteľné, takže ak sa v tomto objekte taká informácia nenachádza, tak ostane prázdna a nenastane chyba
        */

        String sparqlQueryString1 = "PREFIX dbpprop:<http://dbpedia.org/property/> " +
                "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/> " +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT * WHERE {\n" +
                "  ?x rdfs:label \""+name+"\"@en.\n" +
                "  ?x dbpedia-owl:abstract ?abstract.\n" +
                "  OPTIONAL { ?x dbpprop:category ?category. }\n" +
                "  OPTIONAL { ?x dbpprop:country ?country. }\n" +
                "  OPTIONAL { ?x dbpprop:commons ?commons. }\n" +
                "  FILTER (LANG(?abstract) = 'en')\n" +
                "}";

        Query query = QueryFactory.create(sparqlQueryString1);
        QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
        ResultSet results = qe.execSelect();
        ResultSetFormatter.out(System.out, results, query);
        qe.close();


    }
}
