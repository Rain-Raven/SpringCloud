package com.zxa.config;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class GsonConfig {

    @Bean(name = "localGson")
    public Gson LocalGson(TypeAdapter<LocalDateTime> localDateTimeTypeAdapter, TypeAdapter<LocalDate> localDateTypeAdapter, TypeAdapter<LocalTime> localTimeTypeAdapter) {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, localDateTimeTypeAdapter)
                .registerTypeAdapter(LocalDate.class, localDateTypeAdapter)
                .registerTypeAdapter(LocalTime.class, localTimeTypeAdapter)
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();
    }

    @Bean(name = "standardGson")
    public Gson standardGson(TypeAdapter<LocalDateTime> localDateTimeTypeAdapter, TypeAdapter<LocalDate> localDateTypeAdapter, TypeAdapter<LocalTime> localTimeTypeAdapter) {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, localDateTimeTypeAdapter)
                .registerTypeAdapter(LocalDate.class, localDateTypeAdapter)
                .registerTypeAdapter(LocalTime.class, localTimeTypeAdapter)
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();
    }

    @Bean
    public TypeAdapter<LocalDateTime> localDateTimeTypeAdapter() {
        return new TypeAdapter<LocalDateTime>() {
            @Override
            public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
                if (localDateTime == null) {
                    setNull(jsonWriter);
                }
                jsonWriter.value(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }

            @Override
            public LocalDateTime read(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                String string = jsonReader.nextString();
                LocalDateTime localDateTime = LocalDateTime.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                return localDateTime;
            }
        };
    }

    @Bean
    public TypeAdapter<LocalDate> localDateTypeAdapter() {
        return new TypeAdapter<LocalDate>() {
            @Override
            public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
                if (localDate == null) {
                    setNull(jsonWriter);
                }
                jsonWriter.value(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }

            @Override
            public LocalDate read(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                String string = jsonReader.nextString();
                LocalDate localDate = LocalDate.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return localDate;
            }
        };
    }

    @Bean
    public TypeAdapter<LocalTime> localTimeTypeAdapter() {
        return new TypeAdapter<LocalTime>() {
            @Override
            public void write(JsonWriter jsonWriter, LocalTime localTime) throws IOException {
                if (localTime == null) {
                    setNull(jsonWriter);
                } else {
                    jsonWriter.value(localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                }
            }

            @Override
            public LocalTime read(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                String string = jsonReader.nextString();
                LocalTime localTime = LocalTime.parse(string, DateTimeFormatter.ofPattern("HH:mm:ss"));
                return localTime;
            }
        };
    }

    public static void setNull(JsonWriter jsonWriter) throws IOException {
        if (!jsonWriter.getSerializeNulls()) {
            jsonWriter.setSerializeNulls(true);
            jsonWriter.nullValue();
            jsonWriter.setSerializeNulls(false);
        } else {
            jsonWriter.nullValue();
        }
    }
}
