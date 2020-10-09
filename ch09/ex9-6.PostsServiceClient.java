// 리스트 9.6  PostsServiceClient.java 내의 메소드

@Retryable( value = ResourceAccessException.class,
	    maxAttempts = 3, backoff = @Backoff(delay = 500))
public ArrayList<PostSummary> getPosts(String ids,
				       RestTemplate restTemplate)
                                                      throws Exception {
  ArrayList<PostSummary> postSummaries = new ArrayList<PostSummary>();
  String secretQueryParam = "&secret=" + utils.getPostsSecret();
  logger.info("Trying getPosts: " + postsUrl + ids + secretQueryParam);
  ResponseEntity<ConnectionsPostsController.PostResult[]> respPosts = restTemplate.getForEntity(
        postsUrl + ids + secretQueryParam,
        ConnectionsPostsController.PostResult[].class);
  if (respPosts.getStatusCode().is5xxServerError()) {
    throw new HttpServerErrorException(respPosts.getStatusCode(),
		"Exception thrown in obtaining Posts");
  } else {
    ConnectionsPostsController.PostResult[] posts = respPosts.getBody();
    for (int i = 0; i < posts.length; i++)
      postSummaries.add(
	new PostSummary(getUsersname(posts[i].getUserId(), restTemplate),
	posts[i].getTitle(), posts[i].getDate()));
    // 잘못될 때를 고려해서 결과를 캐시에 저장
    ObjectMapper objectMapper = new ObjectMapper();
    String postSummariesJson =
             objectMapper.writeValueAsString(postSummaries);
    PostResults postResults = new PostResults(ids, postSummariesJson);
    postResultsRepository.save(postResults);
    return postSummaries;
  }
}
