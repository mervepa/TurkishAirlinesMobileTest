package Utilizations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import Utilizations.MobileElements;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

public class Utilizations {

    public String findMobileElementByKey(String key,String pageObject) throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(pageObject).getFile());

        String content = new String(Files.readAllBytes(file.toPath()));
        ObjectMapper mapper = new ObjectMapper();

        List<MobileElements> listElems = mapper.readValue(content, new TypeReference<List<MobileElements>>(){});
        for (int i=0; i< listElems.size(); i++){
            if (listElems.get(i).key.equals(key))
                return listElems.get(i).androidValue;
        }
        return "";
    }

}
