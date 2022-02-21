package ru.gb.demoelastic.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Service;
import ru.gb.demoelastic.doc.SubTitlesDoc;

@Service
public class SubTitlesService {
  private static final ObjectMapper mapper = new ObjectMapper();
  private static final String INDEX_NAME = "house";

  private final RestHighLevelClient client;

  public SubTitlesService(RestHighLevelClient client) {
    this.client = client;
  }

  public void indexSubTitles(SubTitlesDoc subTitlesDoc) throws IOException {
    IndexRequest request = new IndexRequest(INDEX_NAME);
    request.source(mapper.writeValueAsString(subTitlesDoc), XContentType.JSON);

    client.index(request, RequestOptions.DEFAULT);
  }

  public List<SubTitlesDoc> search(String searchString) throws IOException {
    SearchRequest request = new SearchRequest(INDEX_NAME);
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
        .query(QueryBuilders.queryStringQuery(searchString));
    request.source(searchSourceBuilder);

    HighlightBuilder highlightBuilder = new HighlightBuilder()
        .field(new HighlightBuilder.Field("text"));
    searchSourceBuilder.highlighter(highlightBuilder);

//    searchSourceBuilder.from(5).size(3); // Пагинация

    SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);

    return Arrays.stream(searchResponse.getHits().getHits())
        .map(hit -> {
          Map<String, Object> map = hit.getSourceAsMap();
          String text = (String) map.get("text");

          HighlightField highlightField = hit.getHighlightFields().get("text");
          if (highlightField != null && highlightField.fragments().length > 0) {
            text = highlightField.fragments()[0].toString();
          }

          return new SubTitlesDoc(
              (String) map.get("title"),
              (Integer) map.get("season"),
              (Integer) map.get("episode"),
              (String) map.get("subId"),
              (String) map.get("timeStamp"),
              text
          );

        }).collect(Collectors.toList());
  }
}
