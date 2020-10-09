// 리스트 9.5  PostsServiceClient.java 내의 메소드

@Recover
public ArrayList<PostSummary> returnCached(
                                ResourceAccessException e,
				String ids, RestTemplate restTemplate)
                                                       throws Exception {
  logger.info("Failed ... Posts service - returning cached results");
  PostResults postResults = postResultsRepository.findOne(ids);
  ObjectMapper objectMapper = new ObjectMapper();
  ArrayList<PostSummary> postSummaries;
  try {
    postSummaries = objectMapper.readValue(
	                  postResults.getSummariesJson(),
			  new TypeReference<ArrayList<PostSummary>>() {});
  } catch (Exception ec) {
    logger.info("Exception on deserialization " + ec.getClass()
		+ " message = " + ec.getMessage());
    return null;
  }
  return postSummaries;
}
