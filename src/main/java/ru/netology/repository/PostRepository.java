package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArraySet;

@Repository
public class PostRepository {
  CopyOnWriteArraySet<Post> repository = new CopyOnWriteArraySet<>();
  int newId = 666;
  public List<Post> all() {

    return (List<Post>) repository;
  }

  public Optional<Post> getById(long id) {
    for (Post everyPost : repository) {
      if (everyPost.getId() == id) {
        return Optional.of(everyPost);
      }
    } throw new NotFoundException("Неверный ID");
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      post.setId(newId);
      newId++;
      repository.add(post);
    } else {
      boolean checkRepository = true;
      for (Post everyPost : repository) {
        if (everyPost.getId() == post.getId()) {
          everyPost.setContent(post.getContent());
          checkRepository = false;
        }
      } if (checkRepository) {
          throw new NotFoundException("Неверный ID");
      }
    }
    return post;
  }

  public void removeById(long id) {
    boolean checkRepository = true;
    for (Post everyPost : repository) {
      if (everyPost.getId() == id) {
        repository.remove(everyPost);
        checkRepository = false;
      }
    } if (checkRepository) {
      throw new NotFoundException("Неверный ID");
    }
  }
    }
