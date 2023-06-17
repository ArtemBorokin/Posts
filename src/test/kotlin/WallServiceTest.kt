import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import ru.netology.Comment
import ru.netology.Likes
import ru.netology.Post
import ru.netology.WallService


object WallService {
    private var posts = emptyArray<Post>()

}

class WallServiceTest {

    val post = WallService.add(Post(text = "being", comment = Comment(), likes = Likes()))

    @Before
    fun clearBeforeTest() {
        WallService.clear()
        //Здесь ещё можно сразу добавлять тестовый пост, чтобы в каждом тесте не прописывать
    }

    @Test
    fun add() {
        //val post = WallService.add(Post(comment = Comment(), likes = Likes()))
        var test = true

        if (post.id == 0) test = false

        assertTrue(test)
    }

    @Test
    fun updateTrue() {
        //val post = WallService.add(Post(text = "being", comment = Comment(), likes = Likes()))
        var test = true
        val updatePost = post.copy(text = "end")

        test = WallService.update(updatePost)

        assertTrue(test)
    }

    @Test
    fun updateFalse() {
        val post = Post(text = "being", comment = Comment(), likes = Likes())
        var test = true
        val updatePost = post.copy(text = "end")

        test = WallService.update(updatePost)

        assertFalse(test)
    }
}