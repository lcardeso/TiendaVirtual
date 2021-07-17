package com.example.demo.config;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {


    @Bean(name = "dozer-mapper")
    public Mapper mapper(@Value(value = "classpath*:mappings/*mapper.xml") Resource[] resourceArray) throws IOException {

        List<String> mappingFileUrlList = new ArrayList<>();
        for (Resource resource : resourceArray) {
            mappingFileUrlList.add(String.valueOf(resource.getURL()));
        }
        DozerBeanMapperBuilder builder = DozerBeanMapperBuilder.create();
        return builder.withMappingFiles(mappingFileUrlList).build();
    }
}
