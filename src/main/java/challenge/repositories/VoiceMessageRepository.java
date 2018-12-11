package challenge.repositories;

import challenge.models.VoiceMessaage;
import org.springframework.stereotype.Repository;

@Repository
public interface VoiceMessageRepository extends ChatRepository<VoiceMessaage> {
}
