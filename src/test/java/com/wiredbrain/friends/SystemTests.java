package com.wiredbrain.friends;

import com.wiredbrain.friends.model.Friend;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SystemTests {

    @Test
    public void testCreateReadDelete(){
        RestTemplate restTemplate = new RestTemplate();

        String url = "http:///localhost:8080/friend";

        Friend friend = new Friend("Gordon", "Moore");
        ResponseEntity<Friend> entity = restTemplate.postForEntity(url, friend, Friend.class);

        Friend[] friends = restTemplate.getForObject(url, Friend[].class);
        Assertions.assertThat(friends).extracting(Friend::getFirstName).containsOnly("Gordon");

        restTemplate.delete(url + "/" + entity.getBody().getId());
        org.assertj.core.api.Assertions.assertThat(restTemplate.getForObject(url, Friend[].class)).isEmpty();
    }
}
