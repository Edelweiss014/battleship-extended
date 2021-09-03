package client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameRoomController {

    @GetMapping("/api/gameroom/creategamejoin")
    public int CreateNewGameJoin(@RequestParam(value = "username", defaultValue = "Username") String username) {
        return 0;
    }
}
