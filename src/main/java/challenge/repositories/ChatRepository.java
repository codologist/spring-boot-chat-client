package challenge.repositories;

import challenge.models.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository<T extends ChatMessage> extends JpaRepository<T, Integer> {
}
