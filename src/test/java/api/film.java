package api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import org.junit.Assert;
import org.junit.Test;
import utils.ConfigReader;
import utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class film {

    @Test
    public void films1() throws URISyntaxException, IOException {
        HttpResponse response = Utils.getResponse("http","swapi.dev", "api/films",null);
        Assert.assertFalse(HttpStatus.SC_NOT_FOUND== response.getStatusLine().getStatusCode());
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseMap = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) responseMap.get("results");
        List<Object> filmTitles = new ArrayList<Object>();
        for (Map<String, Object> map : resultList) {
            filmTitles.add(map.get("title"));
        }

        System.out.println(filmTitles.toString());
        List<String> requirementTitles = new ArrayList<String>();

        requirementTitles.add("A New Hope");
        requirementTitles.add("The Empire Strikes Back");
        requirementTitles.add("Return of the Jedi");
        requirementTitles.add("The Phantom Menace");
        requirementTitles.add("Attack of the Clones");
        requirementTitles.add("Revenge of the Sith");

        for (int i = 0; i < requirementTitles.size(); i++) {
            Assert.assertEquals(requirementTitles.get(i), filmTitles.get(i));
        }

    }

    @Test
    public void films2() throws URISyntaxException, IOException {

        HttpResponse response = Utils.getResponse(ConfigReader.getProperty("scheme"),ConfigReader.getProperty("host"), "api/films",null);
        Assert.assertFalse(HttpStatus.SC_NOT_FOUND== response.getStatusLine().getStatusCode());

        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> responseMap = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) responseMap.get("results");

        List<Object> directorNames = new ArrayList<Object>();

        for (Map<String, Object> map : resultList) {
            directorNames.add(map.get("director"));
        }

        System.out.println(directorNames.toString());

        List<String> requirementDirectors = new ArrayList<String>();

        requirementDirectors.add("George Lucas");
        requirementDirectors.add("Irvin Kershner");
        requirementDirectors.add("Richard Marquand");
        requirementDirectors.add("George Lucas");
        requirementDirectors.add("George Lucas");
        requirementDirectors.add("George Lucas");


        for (int i = 0; i < directorNames.size(); i++) {
            Assert.assertEquals(requirementDirectors.get(i), directorNames.get(i));
        }

    }
}
