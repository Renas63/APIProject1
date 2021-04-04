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

public class people {
    @Test
    public void films1() throws URISyntaxException, IOException {
        List<Object> peopleNames = new ArrayList<Object>();
        for (int i = 1; i <= 9; i++) {
            String page = "page="+i;

            HttpResponse response = Utils.getResponse(ConfigReader.getProperty("scheme"),ConfigReader.getProperty("host"), "api/people", page);
            Assert.assertFalse(HttpStatus.SC_NOT_FOUND== response.getStatusLine().getStatusCode());
            Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = objectMapper.readValue(response.getEntity().getContent(),
                    new TypeReference<Map<String, Object>>() {
                    });
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) responseMap.get("results");
            for (Map<String, Object> map : resultList) {
                peopleNames.add(map.get("name"));
            }
        }
        System.out.println(peopleNames.toString());
    }
}
