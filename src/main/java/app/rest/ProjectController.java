package app.rest;

import app.dao.impl.BidRepository;
import app.dao.impl.PostRepository;
import app.dao.impl.UserRepository;
import app.models.Bid;
import app.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="/rest")
public class ProjectController {

  @Autowired private UserRepository userRepository;
  @Autowired private PostRepository postRepository;
  @Autowired private BidRepository bidRepository;

  // TODO check user credential handling

  @GetMapping("/projects")
  public @ResponseBody Iterable<Post> index(HttpServletRequest request) {
    return postRepository.findAll();
  }

  @PostMapping("/projects")
  Post addProject(@RequestBody Post project) {

    return postRepository.save(project);
  }

  @PostMapping("/projects/{id}")
  Bid bidProject(@PathVariable String id, @RequestBody Bid bid) {
    // check authentication
    // check if lowest bid BindignMapager.checkLowestBid()
    // return transformedReponse(response);
    return bidRepository.save(bid);
  }

  @DeleteMapping("/projects/{id}")
  void deleteProject(@PathVariable Integer id) {
    postRepository.deleteById(id);
  }

}