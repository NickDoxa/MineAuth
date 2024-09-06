package net.oasisgames.spring;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

@Configuration
public class DataSourceConfiguration {

    private static final String[] DEFAULT_CREDENTIALS = new String[] {
            "jdbc:postgresql://localhost:5432/postgres",
            "postgres",
            "starwars13"
    };

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder<?> datasource = DataSourceBuilder.create();
        String[] data = retrieveDataSources();
        System.out.println("DATA SOURCES: " + Arrays.toString(data));
        datasource.url(data[0]);
        datasource.username(data[1]);
        datasource.password(data[2]);
        return datasource.build();
    }

    private String[] retrieveDataSources() {
        String[] dataSources = new String[3];
        try {
            File file = new File("plugins/MineAuth/Server/DATASOURCE.txt");
            if (!file.exists()) {
                System.out.println("\n\nERROR: FILE NOT FOUND @ " + file.getAbsolutePath() + "\n\n");
                return DEFAULT_CREDENTIALS;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int i = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (i >= dataSources.length) break;
                dataSources[i++] = line;
            }
        } catch (IOException e) {
            System.out.println("UNABLE TO READ DATASOURCE!");
        }
        dataSources[0] = dataSources[0].substring(4);
        dataSources[1] = dataSources[1].substring(9);
        dataSources[2] = dataSources[2].substring(9);
        return dataSources;
    }

}