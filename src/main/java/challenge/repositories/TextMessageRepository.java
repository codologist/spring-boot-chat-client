package challenge.repositories;

import challenge.models.TextMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextMessageRepository extends ChatRepository<TextMessage> {
}
