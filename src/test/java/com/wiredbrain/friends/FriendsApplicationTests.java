package com.wiredbrain.friends;

import com.wiredbrain.friends.controller.FriendController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) //JUnit is used..Springboot application is started inside the test
@SpringBootTest // This means springboot application is
class FriendsApplicationTests {

	@Autowired
	FriendController friendController;

	@Test
	void contextLoads() {
		Assert.assertNotNull(friendController);
	}

}
