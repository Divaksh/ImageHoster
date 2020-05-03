package ImageHoster.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

  @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
  public String addComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String title, @RequestParam("comment") String text, HttpSession session){

    return "images/image";
  }

}
