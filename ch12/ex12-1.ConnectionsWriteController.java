// 리스트 12.1  ConnectionsWriteController.java 내의 메소드

@RequestMapping(method = RequestMethod.POST, value="/users")
public void newUser(@RequestBody User newUser,
		    HttpServletResponse response) {

    logger.info("Have a new user with username " + newUser.getUsername());

    // 사용자 정보를 DB에 저장
    userRepository.save(newUser);

    // 카프카에 이벤트 전달
    UserEvent userEvent =
    new UserEvent("created",
		  newUser.getId(),
		  newUser.getName(),
		  newUser.getUsername());
    kafkaTemplate.send("user", userEvent);
}
