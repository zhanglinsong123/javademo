package com.javademo.util;

import com.javademo.model.dto.RedisAsyncResultDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class AsyncUtilTest {

    @Autowired
    private AsyncUtil asyncUtil;

    @Test
    void testGetResult() {
        // Setup
        final RedisAsyncResultDto expectedResult = new RedisAsyncResultDto();
        expectedResult.setTotal(0);
        expectedResult.setDone(0);
        expectedResult.setKey("key");
        expectedResult.setSuccess(false);
        expectedResult.setUrl("url");
        expectedResult.setFlag(false);
        expectedResult.setMsg("message");

        // Run the test
        final RedisAsyncResultDto result = asyncUtil.getResult("key");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSubmitTask() {
        // Setup
        final RedisAsyncResultDto expectedResult = new RedisAsyncResultDto();
        expectedResult.setTotal(0);
        expectedResult.setDone(0);
        expectedResult.setKey("key");
        expectedResult.setSuccess(false);
        expectedResult.setUrl("url");
        expectedResult.setFlag(false);
        expectedResult.setMsg("message");

        // Run the test
        final RedisAsyncResultDto result = asyncUtil.submitTask("key", () -> "value");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
