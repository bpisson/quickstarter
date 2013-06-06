package eu.pisson.quickstarter.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.pisson.quickstarter.model.Element;

@Slf4j
@Service
public class DataService {

	@Autowired
	private Client client;

	public void addElement(String element) {
		ObjectMapper mapper = new ObjectMapper();
		String json;
		try {
			json = mapper.writeValueAsString(element);
		} catch (JsonProcessingException e) {
			log.error("Error while processing data " + element, e);
			return;
		}

		client.prepareIndex("test", "element").setSource(json).execute()
				.actionGet();
	}

	public List<String> getLastElements(int count) {
		SearchResponse response = client.prepareSearch("test")
				.setTypes("element").setFrom(0).setSize(count)
				.addSort("id", SortOrder.DESC).execute().actionGet();

		String[] elements = new String[response.getHits()
				.getHits().length];
		ObjectMapper mapper = new ObjectMapper();
		try {
			for (int i = 0; i < elements.length; i++) {
				elements[i] = mapper.readValue(
						response.getHits().getHits()[i].getSourceAsString(),
						Element.class).getName();
			}
			return Arrays.asList(elements);
		} catch (IOException e) {
			log.error("Error retrieving elements", e);
			return Collections.EMPTY_LIST;
		}
	}
}
