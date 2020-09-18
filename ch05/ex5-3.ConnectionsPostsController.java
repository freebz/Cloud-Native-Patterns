// 리스트 5.3  ConnectionsPostsController.java

@RequestMapping(method = RequestMethod.GET, value="/connectionsposts")
public Iterable<PostSummary> getByUsername(
    @CookieValue(value = "userToken", required=false) String token,
     HttpServletResponse response) {

    if (token == null)
	response.setStatus(401);
    else {
	String username =
	    CloudnativeApplication.validTokens.get(token);
	if (username == null)
	    response.setStatus(401);
	else {
	    // 연결과 게시물을 얻기 위한 코드
	    return postSummaries;
	}
    }
    return null;
}
