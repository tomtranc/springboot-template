package app.dao.impl;

import app.models.Post;
import app.models.User;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface BidRepository extends CrudRepository<Post, Integer> {

}
