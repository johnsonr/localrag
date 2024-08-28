package springrod.localrag

import org.springframework.ai.chat.memory.ChatMemory
import org.springframework.ai.chat.memory.InMemoryChatMemory
import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.embedding.EmbeddingModel
import org.springframework.ai.ollama.OllamaEmbeddingModel
import org.springframework.ai.ollama.api.OllamaApi
import org.springframework.ai.ollama.api.OllamaOptions
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.ai.openai.api.OpenAiApi
import org.springframework.ai.vectorstore.Neo4jVectorStore
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Explicit configuration for the chat application.
 * Starters make it hard to vary things.
 */
@Configuration
class ChatConfiguration {

    @Bean
    fun chatMemory(): ChatMemory {
        return InMemoryChatMemory()
    }

    @Bean
    fun chatModel(@Value("\${OPENAI_API_KEY}") apiKey: String): ChatModel {
        return OpenAiChatModel(OpenAiApi(apiKey))
    }


    @Bean
    fun embeddingModel(): EmbeddingModel {
        return OllamaEmbeddingModel(OllamaApi(),
            OllamaOptions.builder()
                .withModel("gemma2:2b")
                .build()
        )
    }
}