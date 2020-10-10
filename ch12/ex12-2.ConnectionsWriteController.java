// 리스트 12.2  ConnectionsWriteController.java 내의 메소드

@RequestMapping(method = RequestMethod.POST, value="/users")
public void newUser(@RequestBody User newUser,
		    HttpServletResponse response) {

    logger.info("Have a new user with username " + newUser.getUsername());

    // 사용자 정보를 DB에 저장
    userRepository.save(newUser);

    // 새 사용자에 대해 관심 그룹이 알도록 함
    // 게시물은 새 사용자를 인지해야 함
    try {

	RestTemplate restTemplate = new RestTemplate();
	restTemplate.postForEntity(postsControllerUrl+"/users",
				   newUser, String.class);
    } catch (Exception e) {
	// 지금은 아무것도 안 함 - 이벤트 로그를 추가할 때 이 알려진 문제는
	// 사라질 것임
	logger.info("problem sending change event to Posts");
    }

    // 연결 게시물은 새 사용자를 인지해야 함
    try {

	RestTemplate restTemplate = new RestTemplate();
	restTemplate.postForEntity(connectionsPostsControllerUrl+"/users",
				   newUser, String.class);
    } catch (Exception e) {
	// 지금은 아무것도 안 함 - 이벤트 로그를 추가할 때 이 알려진 문제는
	// 사라질 것임
	logger.info("problem sending change event to ConnsPosts");
    }
}
