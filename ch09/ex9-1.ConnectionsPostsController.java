// 리스트 9.1  ConnectionsPostsController.java에서 발췌

int retryCount = 0;
while (implementRetries || retryCount == 0) {
  try {
    RestTemplate restTemp = restTemplateBuilder
                              .setConnectTimeout(connectTimeout)
                              .setReadTimeout(readTimeout)
                              .build();
    ResponseEntity<PostResult[]> respPosts
      = restTemp.getForEntity(postsUrl + ids + secretQueryParam,
			      PostResult[].class);
    if (respPosts.getStatusCode().is5xxServerError()) {
      response.setStatus(500);
      return null;
    } else {
      logger.info(utils.ipTag() + "Retrieved results from database");
      PostResult[] posts = respPosts.getBody();
      for (int i = 0; i < posts.length; i++)
	postSummaries.add(
	  new PostSummary(getUsername(posts[i].getUserId()),
	  posts[i].getTitle(), posts[i].getDate()));
      return postSummaries;
    }
  } catch (Exception e) {
    // 연결이 시간 초과됐을 때 발생
    // 단순한 구현을 위해
    // 간단히 다시 시도
    logger.info(utils.ipTag() +
      "On (" + retryCount + ") request to unhealthy posts service " +
      e.getMessage());
    if (implementRetries)
      retryCount++;
    else {
      logger.info(utils.ipTag() +
	"Not implementing retries - returning with a 500");
      response.setStatus(500);
      return null;
    }
  }
}
