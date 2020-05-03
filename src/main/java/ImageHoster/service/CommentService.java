package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  @Autowired
  private CommentRepository commentRepository;

  //The method calls the createComment() method in the Repository and passes the comment to be added in the database
  public Comment createComment(Comment comment) {
    return commentRepository.createComment(comment);
  }

  //The method calls the getAllComments() method in the Repository and returns the all comments on an image as a list
  public List<Comment> getAllComments(Integer imageId){
    return commentRepository.getAllComments(imageId);
  }

}
