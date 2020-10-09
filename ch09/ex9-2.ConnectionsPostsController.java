// 리스트 9.2  ConnectionsPostsController.java에서 발췌

try {
    postSummaries = postServiceClient.getPosts(ids, restTemplate);
    response.setStatus(200);
    return postSummaries;
} catch (HttpServerErrorException e) {
    logger.info(utils.ipTag() + "Call to Posts service returned 500");
    response.setStatus(500);
    return null;
} catch (ResourceAccessException e) {
    logger.info(utils.ipTag() + "Call to Posts service timed out");
    response.setStatus(500);
    return null;
} catch (Exception e) {
    logger.info(utils.ipTag() + "Unexpected Exception: Exception Class "
	+ e.getClass() + e.getMessage());
    response.setStatus(500);
    return null;
}
