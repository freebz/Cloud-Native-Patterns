// 리스트 7.3  ConnectionsPostsController.java 내의 메소드

@RequestMapping(method = RequestMethod.GET, value="/Connections' Posts")
public Iterable<PostSummary> getByUsername(
    @CookieValue(value = "userToken", required=false) String token,
    HttpServletResponse response) {

// <간결함을 위해 생략함>

    // 연결 가져오기
    String secretQueryParam
	= "?secret=" + utils.getConnectionsSecret();
    ResponseEntity<ConnectionResult[]> respConns
	= restTemplate.getForEntity(
	    connectionsUrl + username + secretQueryParam,
	    ConnectionResult[].class);
    // <간결함을 위해 생략함>

    secretQueryParam = "&secret=" + utils.getPostsSecret();
    // 연결에 해당하는 게시물 가져오기
    ResponseEntity<PostResult[]> respPosts
	= restTemplate.getForEntity(
	    postsUrl + ids + secretQueryParam,
	    PostResult[].class);
    // <간결함을 위해 생략함>
}
