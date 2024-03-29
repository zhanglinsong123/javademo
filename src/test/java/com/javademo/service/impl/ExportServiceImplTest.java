package com.javademo.service.impl;

import com.javademo.mapper.TestMapper;
import com.javademo.model.dto.RedisAsyncResultDto;
import com.javademo.model.request.ExportRequest;
import com.javademo.service.ExportService;
import com.javademo.util.AsyncUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ExportServiceImplTest {

    @Autowired
    private ExportService exportServiceImplUnderTest;

    @Test
    void testExportData() {
        // Setup
        final ExportRequest exportRequest = new ExportRequest();
        exportRequest.setExportType("exportType");
        exportRequest.setExportId("exportId");

//        when(mockTestMapper.queryAll()).thenReturn(Arrays.asList(new HashMap<>()));

        // Run the test
        final String result = exportServiceImplUnderTest.exportData(exportRequest, "key");

        // Verify the results
        assertThat(result).isEqualTo("我是oss url地址");
    }

    @Test
    void testExportData_TestMapperReturnsNoItems() {
        // Setup
        final ExportRequest exportRequest = new ExportRequest();
        exportRequest.setExportType("exportType");
        exportRequest.setExportId("exportId");

//        when(mockTestMapper.queryAll()).thenReturn(Collections.emptyList());

        // Run the test
        final String result = exportServiceImplUnderTest.exportData(exportRequest, "key");

        // Verify the results
        assertThat(result).isEqualTo("我是oss url地址");
    }

    @Test
    void testGetExportProgress() {
        // Setup
        final RedisAsyncResultDto expectedResult = new RedisAsyncResultDto();
        expectedResult.setTotal(0);
        expectedResult.setDone(0);
        expectedResult.setKey("key");
        expectedResult.setSuccess(false);
        expectedResult.setUrl("url");

        // Run the test
        final RedisAsyncResultDto result = exportServiceImplUnderTest.getExportProgress("key");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
