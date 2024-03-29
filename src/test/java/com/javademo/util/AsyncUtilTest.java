package com.javademo.util;

import com.javademo.model.dto.RedisAsyncResultDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AsyncUtilTest {

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
        final RedisAsyncResultDto result = AsyncUtil.getResult("key");

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
        final RedisAsyncResultDto result = AsyncUtil.submitTask("key", () -> "value");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
