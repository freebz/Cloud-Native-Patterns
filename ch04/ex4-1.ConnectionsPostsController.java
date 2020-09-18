// 리스트 4.1  ConnectionsPostsController.java 내의 메서드

@RequestMapping(method = RequestMethod.GET,
		value="/connectionsposts/{username}")
public Iterable<PostSummary> getByUsername(
                       @PathVariable("username") String username,
		       HttpServletResponse response) {

    Iterable<PostSummary> postSummaries;
    logger.info("getting posts for user network " + username);

    postSummaries = mPostRepository.findForUsersConnections(username);

    return postSummaries;
}
