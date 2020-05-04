package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import java.time.LocalDate;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CommentController {

  @Autowired
  private ImageService imageService;

  @Autowired
  private CommentService commentService;

  //This controller method is called when the request pattern is of type '/image/{imageId}/{imageTitle}/comments' and also the incoming request is of POST type
  //The method receives all the details of the image along with the comment to be stored in the database, and now the comment will be sent to the business logic to be persisted
  // in the database
  //After getting the comment, set the user of the comment by getting the logged in user from the Http Session
  //Set the local date on which the comment is posted
  //After storing the comment, this method directs to the same image page displaying all the comments

  @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
  public String addComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String title, @RequestParam("comment") String text, HttpSession session,
      Model model, final RedirectAttributes redirectAttributes) {
    Image image = imageService.getImage(imageId);

    if (text.isEmpty()) {
      String error = "Comment can not be empty";
      model.addAttribute("emptyCommentError", error);
      redirectAttributes.addAttribute("emptyCommentError", error);
      redirectAttributes.addFlashAttribute("emptyCommentError", error);
      return "redirect:/images/" + imageId + "/" + title;
    }

    User loggedInUser = (User) session.getAttribute("loggeduser");
    Comment comment = new Comment(); // Initializing comment object so all data can be stored
    comment.setImage(image);
    comment.setUser(loggedInUser);
    comment.setText(text);
    comment.setCreatedDate(LocalDate.now());  // Sending local date as per the requirement
    commentService.createComment(comment);
    return "redirect:/images/" + imageId + "/" + title;
  }

}
