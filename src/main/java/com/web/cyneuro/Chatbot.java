package com.web.cyneuro;

import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.core.env.Environment;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.lang.reflect.Field;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/chatbot")
@ComponentScan(basePackages = {"com.web.cyneuro"})
public class Chatbot {



	public static void setEnv(String key, String value) {
        try {
            Map<String, String> env = System.getenv();
            Class<?> cl = env.getClass();
            Field field = cl.getDeclaredField("m");
            field.setAccessible(true);
            Map<String, String> writableEnv = (Map<String, String>) field.get(env);
            writableEnv.put(key, value);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to set environment variable", e);
        }
	}

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public static String test(String key, String key2){
        System.out.print(key);
        System.out.print(key2);
        return "Success!";
    }

    // DialogFlow API Detect Intent sample with text inputs.
    @RequestMapping(value = "/getResponse", method = RequestMethod.POST)
	@ResponseBody
	  public static String detectIntentTexts(
	      String projectId, String text1, String sessionId, String languageCode)
	      throws IOException, ApiException {
        System.out.print(projectId);
        System.out.print(text1);
		String key = "GOOGLE_APPLICATION_CREDENTIALS";
        String value = "src/main/resources/vidura-chatbot-agent-incn-ec1c40b7d325.json";
        setEnv(key, value);
        ArrayList<String> texts = new ArrayList<String>();
	    texts.add(text1);
	    Map<String, QueryResult> queryResults = Maps.newHashMap();
        String response_text = "";
	    // Instantiates a client
	    try (SessionsClient sessionsClient = SessionsClient.create()) {
	      // Set the session name using the sessionId (UUID) and projectID (my-project-id)
	      SessionName session = SessionName.of(projectId, sessionId);
	      System.out.println("Session Path: " + session.toString());
	
	      // Detect intents for each text input
	      for (String text : texts) {
	        // Set the text (hello) and language code (en-US) for the query
	        TextInput.Builder textInput =
	            TextInput.newBuilder().setText(text).setLanguageCode(languageCode);
	
	        // Build the query with the TextInput
	        QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
	
	        // Performs the detect intent request
	        DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
	
	        // Display the query result
	        QueryResult queryResult = response.getQueryResult();
	
	        System.out.println("====================");
	        System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
	        System.out.format(
	            "Detected Intent: %s (confidence: %f)\n",
	            queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());
	        System.out.format(
	            "Fulfillment Text: '%s'\n",
	            queryResult.getFulfillmentMessagesCount() > 0
	                ? queryResult.getFulfillmentMessages(0).getText()
	                : "Triggered Default Fallback Intent");
            response_text = queryResult.getFulfillmentMessages(0).getText().toString();
	
	        queryResults.put(text, queryResult);
	      }
	    }
	    return response_text;
	  }


	@Autowired
	private Environment env;
	RestTemplate restTemplate = new RestTemplate();
	@RequestMapping(value = "/classifier", method = RequestMethod.POST)
	@ResponseBody
	public String classifier(String query){
		System.out.print(query);
		String url = env.getProperty("python.service.url");
		url += "/query_classify";
		System.out.println(url);

		Map<String, String> map = new HashMap<>();
		map.put("query", query);
		System.out.println(map);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JSONObject jsonObject = new JSONObject();
		jsonObject .put("query", query);

		HttpEntity<JSONObject> entity = new HttpEntity<>(jsonObject , headers);

		String result = null;
		try {
			result = restTemplate.postForObject(url, entity, String.class);
//    		System.out.print(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// @Autowired
	// private Environment env;
	// RestTemplate restTemplate = new RestTemplate();
	@RequestMapping(value = "/qdstm_response", method = RequestMethod.POST)
	@ResponseBody
	public String qdstm_response(String query){
		System.out.print(query);
		String url = env.getProperty("python.service.url");
		url += "/qdstm_response";
		System.out.println(url);

		Map<String, String> map = new HashMap<>();
		map.put("query", query);
		// map.put("data", )
		System.out.println(map);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JSONObject jsonObject = new JSONObject();
		jsonObject .put("query", query);

		HttpEntity<JSONObject> entity = new HttpEntity<>(jsonObject , headers);

		String result = null;
		try {
			result = restTemplate.postForObject(url, entity, String.class);
//    		System.out.print(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	    // public static void main(String args[]) throws ApiException, IOException {
	    //     String projectId = "vidura-chatbot-agent-incn";
        //     String texts = "what is knowcovid-19?";
	    //     String sessionId = "123456";
	    //     String languageCode = "en-US";
	    //     String queryResult = detectIntentTexts(projectId, texts, sessionId, languageCode);
        //     System.out.print(queryResult);
	    // }

}

