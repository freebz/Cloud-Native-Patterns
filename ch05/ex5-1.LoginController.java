// 리스트 5.1  LoginController.java

package com.corneliadavis.cloudnative.connectionsposts;

import ...

@RestController public class LoginController {

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public void whoareyou(
	@RequestParam(value="username", required=false) String username,
	 HttpServletResponse response) {

	if (username == null)
	    response.setStatus(400);
	else {
	    UUID uuid = UUID.randomUUID();
	    String userToken = uuid.toString();

	    CloudnativeApplication.validTokens.put(userToken, username);
	    response.addCookie(new cookie("userToken", userToken));
	}
    }
}
