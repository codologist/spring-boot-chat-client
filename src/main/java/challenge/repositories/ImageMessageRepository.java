package challenge.repositories;

import challenge.models.ImageMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMessageRepository extends ChatRepository<ImageMessage> {
}
