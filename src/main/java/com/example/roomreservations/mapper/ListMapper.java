package com.example.roomreservations.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ListMapper {
   public static ObjectMapper objectMapper =  new ObjectMapper();

   public static String convertListToJson(List<String> facilities) throws JsonProcessingException {
       return objectMapper.writeValueAsString(facilities);
   }

   public static List<String> convertJsonToList(String json) throws JsonProcessingException {
       return objectMapper.readValue(json, new TypeReference<List<String>>(){});
       }
   }

